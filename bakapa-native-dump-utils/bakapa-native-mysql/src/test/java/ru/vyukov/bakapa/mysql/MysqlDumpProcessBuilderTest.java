package ru.vyukov.bakapa.mysql;

import ru.vyukov.bakapa.nativeutils.DumpUtilPrecessBuilder;
import ru.vyukov.bakapa.test.AbstractDumpUtilProcessBuilderTest;

import java.io.IOException;

/**
 * @author Oleg Vyukov
 */
public class MysqlDumpProcessBuilderTest extends AbstractDumpUtilProcessBuilderTest {


    @Override
    protected DumpUtilPrecessBuilder<?> getEmbeddedProcessBuilder() throws IOException {
        return MysqlDumpProcessBuilder.embeddedBuilder();
    }


    @Override
    public String getAssertThatVersion() {
        return "mysqldump  Ver";
    }

    @Override
    public String getVersionArg() {
        return "--version";
    }
}