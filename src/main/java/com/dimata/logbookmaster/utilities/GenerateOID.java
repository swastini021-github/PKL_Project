package com.dimata.logbookmaster.utilities;

import java.util.Date;

public class GenerateOID {

    private static int appIdx = 3;
    private static GenerateOID generateOID = null;
    static long lastOID = 0;

    public GenerateOID() {

    }

    public GenerateOID(int appidx) {
        getGenerateOID();
        appIdx = appidx;
    }

    public GenerateOID getGenerateOID() {
        if (generateOID == null) {
            generateOID = new GenerateOID();
        }
        return generateOID;
    }

    public int getAppIndex() {
        return appIdx;
    }

    synchronized public static long generateOID() {
        Date dateGenerated = new Date();
        long oid = dateGenerated.getTime() + (0x0100000000000000L * appIdx);
        while (lastOID == oid) {
            try {
                Object lock = new Object();
                lock.wait(1);
            } catch (Exception e) {
            }
            dateGenerated = new Date();
            oid = dateGenerated.getTime() + (0x0100000000000000L * appIdx);
        }
        lastOID = oid;
        return oid;
    }

}
