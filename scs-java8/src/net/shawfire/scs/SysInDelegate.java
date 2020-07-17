package net.shawfire.scs;

@FunctionalInterface
public interface SysInDelegate {
    String readLine() throws java.io.IOException;
}
