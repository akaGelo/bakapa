package ru.vyukov.bakapa.agent.service.system;

import com.jakewharton.byteunits.BinaryByteUnit;
import com.jakewharton.byteunits.ByteUnit;

/**
 * The physical disk is monitored and the required location is counted
 *
 * @author Oleg Vyukov
 */
public interface DiskInspector {


    /**
     * @return (physical free space) - (allocated bytes) or 0
     */
    public long getFreeSpace();

    public boolean available(long bytes);

    public boolean available(long count, BinaryByteUnit unit);
}
