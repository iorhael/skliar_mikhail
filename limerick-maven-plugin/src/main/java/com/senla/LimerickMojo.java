package com.senla;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "limerick", defaultPhase = LifecyclePhase.COMPILE)
public class LimerickMojo extends AbstractMojo {

    public void execute() {
        getLog().info("\u001B[33mLambert, Lambert - what a...\u001B[0m");
    }

}
