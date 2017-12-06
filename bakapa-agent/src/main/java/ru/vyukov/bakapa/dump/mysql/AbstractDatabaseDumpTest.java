package ru.vyukov.bakapa.dump.mysql;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.SocketUtils;

/**
 * @author Oleg Vyukov
 */
abstract public class AbstractDatabaseDumpTest {

    protected static String randomDatabase() {
        return AbstractDatabaseDumpTest.class.getSimpleName() + RandomStringUtils.randomAlphabetic(5);
    }

    protected static Integer randomPort() {
        return SocketUtils.findAvailableTcpPort();
    }

    protected void sleep5() {
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }
    }
}
