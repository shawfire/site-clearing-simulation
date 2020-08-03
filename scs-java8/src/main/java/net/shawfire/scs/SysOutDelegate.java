package net.shawfire.scs;

/**
 * SysOutDelegate provided a mechanism by which the println routine can be replace for testing purposes.
 */
@FunctionalInterface
public interface SysOutDelegate {
    void println(String val);
}
