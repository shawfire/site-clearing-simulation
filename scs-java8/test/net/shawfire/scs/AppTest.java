package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

    String lastSysOutmessage;

    @Before
    public void injectLastSysOutDelegate() {
        App.setSysOutDelegate((val) -> lastSysOutmessage = val);
    }

    @Test
    public void givenTwoInputParameter_shouldAskForOne() throws Exception {
        App.main(new String[] {"a", "b"});

        Assert.assertEquals(lastSysOutmessage, App.MustPassFileName);
    }

    @Test
    public void givenOneInputParameters_shouldReadBack() throws Exception {
        App.main(new String[] {"a"});

        Assert.assertEquals(lastSysOutmessage, App.FileNameLabel + "a");
    }

}