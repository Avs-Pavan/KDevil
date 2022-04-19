package com.kevin.devil;

import com.google.gson.Gson;
import com.kevin.devil.models.DevilMessage;

public class Hermes {
    private static Gson gson;

    public Hermes() {
        gson = new Gson();
    }

    public void sendMessage(DevilMessage message) {
        RemoteDevil.scream(gson.toJson(message), "M/" + message.getUserId());
    }
}

