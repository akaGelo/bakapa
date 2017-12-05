package ru.vyukov.bakapa.dump;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Oleg Vyukov
 */
@Slf4j
public class TestDumplLogger {


    public static void log(InputStream errorStream) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    logImpl(errorStream);
                } catch (IOException e) {
                    log.error("Log exception", e);
                }
            }
        }).start();
    }

    private static void logImpl(InputStream errorStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream));
        String line = null;
        while (null != (line = bufferedReader.readLine())) {
            log.warn(line);
        }
    }


}
