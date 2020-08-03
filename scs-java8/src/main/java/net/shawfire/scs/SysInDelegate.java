package net.shawfire.scs;

/**
 * SysInDelegate provided a mechanism by which the readLine routine can be replace for testing purposes.
 */
@FunctionalInterface
public interface SysInDelegate {
    String readLine() throws java.io.IOException;
}
