package io.github.shafr.dockertestframework;

import io.github.shafr.dockertestframework.annotations.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@DockerHost()
@Image("alpine")
public class MethodLevelTest extends DockerTest{

    @Test()
    @CommandLineArgument({"ls", "-ltrh"})
    public void testCommandLineArgument(){
        String[] actual = dockerController.inspectContainer().cmd().toArray(new String[0]);
        Assert.assertEquals(actual[0], "ls");
        Assert.assertEquals(actual[1], "-ltrh");
    }

    @Test
    @EntryPoint("/bin/sh")
    public void testEntryPoint(){
        String[] actual = dockerController.inspectContainer().entrypoint().toArray(new String[0]);
        Assert.assertEquals(actual[0], "/bin/sh");
    }

    @Test
    @Environment("key=value")
    @Environment("key2=value2")
    public void testEnvironment(){
        String[] actual = dockerController.inspectContainer().env().toArray(new String[0]);
        Assert.assertEquals(actual[0], "key=value");
        Assert.assertEquals(actual[1], "key2=value2");
        Assert.assertEquals(actual[2], "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin");
    }

    @Test
    public void testImage(){
        String actual = dockerController.inspectContainer().image();
        Assert.assertEquals(actual, "alpine");
    }
}
