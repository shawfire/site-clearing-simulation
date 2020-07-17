package net.shawfire.scs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Instruction {

    private static BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    private static SysInDelegate sysInDelegate = () -> reader.readLine();

    public String getIntruction() throws java.io.IOException {
        return sysInDelegate.readLine();
    }

    protected static void setSysInDelegate(SysInDelegate val) {
        sysInDelegate = val;
    }
}
