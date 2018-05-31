package edu.stanford;

import org.junit.*;

import java.io.File;

// This is a placeholder test until real methods and appropriate tests are developed
public class RialtoLoadTest {

    @Test
    public void testMain() {
        String[] args = {"grid.433695.e.nt"};
        File agent = new File(this.getClass().getResource(args[0]).getFile());
        Assert.assertTrue(agent.exists());

    }
}