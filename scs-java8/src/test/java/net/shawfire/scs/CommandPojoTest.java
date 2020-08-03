package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class CommandPojoTest {

    @Test
    public void testEquals() {
        CommandPojo commandPojo = new CommandPojo(CommandType.ADVANCE, Optional.of(4));
        Assert.assertEquals("advance 4", commandPojo.toString());
        Assert.assertEquals(new CommandPojo(CommandType.ADVANCE, Optional.of(4)), commandPojo);
        Assert.assertEquals(new CommandPojo(CommandType.QUIT), CommandType.QUIT);
    }

}