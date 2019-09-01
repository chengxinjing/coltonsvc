package com.colton.gemfire;

import org.apache.geode.distributed.LocatorLauncher;

public class LocatorApplication {
    public static void main(String[] args) {
        LocatorLauncher.main(new String[]{"start","locator","--port=31431"});
    }
}
