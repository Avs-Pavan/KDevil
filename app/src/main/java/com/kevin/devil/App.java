package com.kevin.devil;

import android.app.Application;

import com.kevin.devil.models.DevilConfig;

public class App extends Application {
    final String serverUri = "tcp://134.209.144.25:1883";

    @Override
    public void onCreate() {
        super.onCreate();
        Devil.breath(
                new DevilConfig(
                        true,
                        true,
                        serverUri,
                        getApplicationContext(),
                        "Tag",
                        "Will_topic",
                        "123456"));
    }
}
