package ru.vyukov.bakapa.dump.mysql;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.NullOutputStream;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.core.io.ClassPathResource;
import pl.domzal.junit.docker.rule.DockerRule;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseBackupOptionsDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseLocationDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseUserCredentialsDTO;
import ru.vyukov.bakapa.dump.ProcessDumpResult;
import ru.vyukov.bakapa.dump.TestDumplLogger;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.SECONDS;
import static junit.framework.TestCase.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertFalse;
import static org.springframework.jdbc.datasource.init.ScriptUtils.executeSqlScript;
import static ru.vyukov.bakapa.dto.backups.target.impl.DatabaseBackupOptionsDTO.backupOptions;
import static ru.vyukov.bakapa.dto.backups.target.impl.DatabaseLocationDTO.databaseLocation;
import static ru.vyukov.bakapa.dto.backups.target.impl.DatabaseUserCredentialsDTO.userCredentials;

/**
 * @author Oleg Vyukov
 */
@Slf4j
@RunWith(Parameterized.class)
public class MySqlDumpTest extends  AbstractDatabaseDumpTest {

    @Parameterized.Parameters(name = "mysql-server version {0}")
    public static Iterable<String> data() {
        return asList("latest", "8", "5");
    }

    private static final DatabaseLocationDTO location = databaseLocation().host("127.0.0.1").port(randomPort()).database(randomDatabase()).build();

    private static final DatabaseUserCredentialsDTO credentials = userCredentials().username("root").password("qwerty").build();

    private DatabaseBackupTargetDTO.DatabaseBackupTargetDTOBuilder backupTargetBuilder = DatabaseBackupTargetDTO.localhostMysql().toBuilder()
            .userCredentials(credentials).location(location);


    @Rule
    public DockerRule mysqlServerRule;


    private MySqlDump underTest;

    public MySqlDumpTest(String version) throws SQLException, IOException {
        PrintStream nullStream = new PrintStream(new NullOutputStream());

        mysqlServerRule = DockerRule.builder()
                .imageName("mysql:" + version)
                .expose(String.valueOf(location.getPort()), "3306")
                .env("MYSQL_ROOT_PASSWORD", credentials.getPassword())
                .env("MYSQL_DATABASE", location.getDatabase())
                .imageAlwaysPull(true)
                .stdoutWriter(nullStream)
                .stderrWriter(nullStream)
                .build();

    }

    @Before
    public void setUp() throws Exception {
        Connection connection = getConnection();

        executeSqlScript(connection, new ClassPathResource("demo.sql"));
    }

    @Test
    public void dumpAll() throws Exception {
        DatabaseBackupOptionsDTO options = backupOptions().build();
        underTest = new MySqlDump(backupTargetBuilder.options(options).build());

        ProcessDumpResult dumpResult = underTest.dump();
        InputStream inputStream = dumpResult.getInputStream();
        TestDumplLogger.log(dumpResult.getErrorStream());

        String dumpedSql = IOUtils.toString(inputStream, Charset.defaultCharset());
        assertThat(dumpedSql, allOf(

                //test table
                containsString("one"),
                containsString("two"),

                //ignored_table
                containsString("five"),
                containsString("six")
        ));
        dumpResult.waitFor(30, SECONDS);
        assertTrue(dumpResult.isSuccess());
    }


    @Test
    public void dumpIgnoreTables() throws Exception {

        DatabaseBackupOptionsDTO options = backupOptions().excludeTable("test_ignore_table").build();
        underTest = new MySqlDump(backupTargetBuilder.options(options).build());

        ProcessDumpResult dumpResult = underTest.dump();
        InputStream inputStream = dumpResult.getInputStream();
        TestDumplLogger.log(dumpResult.getErrorStream());

        String dumpedSql = IOUtils.toString(inputStream, Charset.defaultCharset());
        assertThat(dumpedSql, allOf(

                //test table
                containsString("one"),
                containsString("two")
        ));

        assertThat(dumpedSql, allOf(
                not(containsString("four")),
                not(containsString("five")),
                not(containsString("six"))
        ));
        dumpResult.waitFor(30, SECONDS);
        assertTrue(dumpResult.isSuccess());
    }


    @Test
    public void testWrongCredentials() throws Exception {
        DatabaseUserCredentialsDTO credentials = userCredentials().username("root").password("wrong").build();
        underTest = new MySqlDump(backupTargetBuilder.userCredentials(credentials).build());

        ProcessDumpResult dumpResult = underTest.dump();
        InputStream inputStream = dumpResult.getInputStream();
        TestDumplLogger.log(dumpResult.getErrorStream());

        String dumpedSql = IOUtils.toString(inputStream, Charset.defaultCharset());
        assertEquals("", dumpedSql);

        dumpResult.waitFor(30, SECONDS);
        assertFalse(dumpResult.isSuccess());
    }


    private Connection getConnection() throws SQLException {
        //delay mysql started
        String url = null;
        Error lastException = null;
        //10 attempts with an interval per 3 second
        for (int i = 0; i < 10; i++) {
            sleep5();
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

    private String createUrl(DatabaseLocationDTO location) {
        return "jdbc:mysql://" + location.getHost() +
                ":" + location.getPort() + "/" + location.getDatabase();
    }
}