package net.shawfire.scs;

/**
 * Utils encapsulated some generic static functions
 * like printing which requires an abstraction for testing
 * with mockito mocks.
 */
public class Utils {

    /**
     * Use only System.out.print so that all output can be mocked and tested at one point.
     */
    public static void print(String val) {
        System.out.print(val);
    }

    public static void println(String val) {
        print(val + "\n");
    }
}
