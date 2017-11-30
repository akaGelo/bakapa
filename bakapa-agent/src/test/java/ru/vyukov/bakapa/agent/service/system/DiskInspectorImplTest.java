package ru.vyukov.bakapa.agent.service.system;

import com.jakewharton.byteunits.BinaryByteUnit;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static com.jakewharton.byteunits.BinaryByteUnit.BYTES;
import static com.jakewharton.byteunits.BinaryByteUnit.MEBIBYTES;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Oleg Vyukov
 */
@RunWith(MockitoJUnitRunner.class)
public class DiskInspectorImplTest {


    @InjectMocks
    private DiskInspectorImpl underTest;

    @Test
    public void getFreeSpace() throws Exception {
        long freeSpace = underTest.getFreeSpace();
        long expectedFreeSpace = getRealFreeSpace();
        assertEquals(expectedFreeSpace, freeSpace);
    }

    @Test
    public void available() throws Exception {
        assertTrue(underTest.available(10));
        assertFalse(underTest.available(getRealFreeSpace() + 1));
        assertFalse(underTest.available(getRealFreeSpace() ));
    }

    @Test
    public void availableUnit() throws Exception {
        assertTrue(underTest.available(1, MEBIBYTES));
        assertFalse(underTest.available(getRealFreeSpace() + 1, BYTES));
    }

    private long getRealFreeSpace() {
        return SystemUtils.getJavaIoTmpDir().getFreeSpace();
    }

}