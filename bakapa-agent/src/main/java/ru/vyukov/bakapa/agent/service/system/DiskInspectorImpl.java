package ru.vyukov.bakapa.agent.service.system;

import com.jakewharton.byteunits.BinaryByteUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.SystemUtils.getJavaIoTmpDir;

/**
 * @author Oleg Vyukov
 */
@Slf4j
@Service
public class DiskInspectorImpl implements DiskInspector {

    @Override
    public long getFreeSpace() {
        long freeSpace = getJavaIoTmpDir().getFreeSpace();
        return freeSpace < 0 ? 0 : freeSpace;
    }

    @Override
    synchronized public boolean available(long bytes) {
        if (getFreeSpace() - bytes > 0) {
            return true;
        } else {
            log.warn("Denied allocation of " + bytes + " bytes. Free space = " + getFreeSpace());
            return false;
        }
    }

    @Override
    public boolean available(long count, BinaryByteUnit unit) {
        return available(unit.toBytes(count));
    }
}
