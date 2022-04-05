package com.kevin.devil;

import android.content.Context;
import android.widget.Toast;

public class DevilShouter {
    public static void scream(Context c, String message) {
        Toast.makeText(c, message, Toast.LENGTH_LONG).show();
    }
}
