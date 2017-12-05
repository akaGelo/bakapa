package ru.vyukov.bakapa.mysql;

import ru.vyukov.bakapa.nativeutils.AbstractDumpUtilProcessBuilderTest;
import ru.vyukov.bakapa.nativeutils.DumpUtilProcessBuilder;

import java.io.IOException;

/**
 * @author Oleg Vyukov
 */
public class MysqlDumpProcessBuilderTest extends AbstractDumpUtilProcessBuilderTest {


    @Override
    protected DumpUtilProcessBuilder<?> getEmbeddedProcessBuilder() throws IOException {
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