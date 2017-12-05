package ru.vyukov.bakapa.mysql;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.util.SocketUtils;
import pl.domzal.junit.docker.rule.DockerRule;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseLocationDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseUserCredentialsDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static junit.framework.TestCase.fail;
import static org.springframework.jdbc.datasource.init.ScriptUtils.executeSqlScript;
import static ru.vyukov.bakapa.dto.backups.target.impl.DatabaseLocationDTO.databaseLocation;
import static ru.vyukov.bakapa.dto.backups.target.impl.DatabaseUserCredentialsDTO.userCredentials;

/**
 * @author Oleg Vyukov
 */
@Slf4j
@Ignore
@RunWith(Parameterized.class)
public class MysqlDumpTest {

    @Parameterized.Parameters(name = "mysql-server version {0}")
    public static Iterable<String> data() {
//        return asList("latest", "8", "5");
        return asList("latest");
    }

    private static final DatabaseLocationDTO location = databaseLocation().host("127.0.0.1").port(randomPort()).database(randomDatabase()).build();

    private static final DatabaseUserCredentialsDTO credentials = userCredentials().username("root").password("qwerty").build();

    private final static DatabaseBackupTargetDTO.DatabaseBackupTargetDTOBuilder backupTargetBuilder = DatabaseBackupTargetDTO.localhostMysql().toBuilder()
            .userCredentials(credentials).location(location);


    @Rule
    public DockerRule mysqlServerRule;


    private MysqlDump underTest;

    public MysqlDumpTest(String version) throws SQLException, IOException {

        mysqlServerRule = DockerRule.builder()
                .imageName("mysql:" + version)
                .expose(String.valueOf(location.getPort()), "3306")
                .env("MYSQL_ROOT_PASSWORD", credentials.getPassword())
                .env("MYSQL_DATABASE", location.getDatabase())
                .imageAlwaysPull(true)
                .build();

    }

    @Before
    public void setUp() throws Exception {
        Connection connection = getConnection();

        executeSqlScript(connection, new ClassPathResource("demo.sql"));
    }

    @Test
    public void dumpAll() throws Exception {
        underTest = new MysqlDump(backupTargetBuilder.build());

        InputStream inputStream = underTest.dump();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        IOUtils.copy(inputStream, output);

        String dumpedSql = new String(output.toByteArray());
        System.out.println(dumpedSql);
    }


    @Test
    public void dumpIgnoreTables() throws Exception {
        underTest = new MysqlDump(backupTargetBuilder.build());
        underTest.dump();
        fail("not  implemended");
    }


    private static String randomDatabase() {
        return MysqlDumpTest.class.getSimpleName() + RandomStringUtils.randomAlphabetic(5);
    }

    private static Integer randomPort() {
        return SocketUtils.findAvailableTcpPort();
    }

    private Connection getConnection() throws SQLException {
        //delay mysql started
        String url = null;
        Error lastException = null;
        //10 attempts with an interval per 3 second
        for (int i = 0; i < 10; i++) {
            sleep3();
            try {
                url = createUrl(location);
                return DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
            } catch (Exception e) {
                log.error(format("Connect to %s %s of 10 problem ", url, i));
                lastException = new AssertionError(e);
            }
        }
        throw lastException;
    }

    private void sleep3() {
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }
    }

    private String createUrl(DatabaseLocationDTO location) {
        return "jdbc:mysql://" + location.getHost() +
                ":" + location.getPort() + "/" + location.getDatabase();
    }
}