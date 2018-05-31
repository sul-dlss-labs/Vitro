package edu.stanford;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class RialtoLoadTest {

    @Test
    public void testMain() {
        String[] args = {"grid.433695.e.nt"};
        File agent = new File(this.getClass().getResource(args[0]).getFile());
        Assert.assertTrue(agent.exists());
    }
}