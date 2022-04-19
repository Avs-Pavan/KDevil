package com.kevin.devil;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.kevin.devil.models.CodeLocation;
import com.kevin.devil.models.DevilConfig;
import com.kevin.devil.models.DevilException;
import com.kevin.devil.models.DevilFailedRequest;
import com.kevin.devil.models.DevilLog;
import com.kevin.devil.models.DevilRequest;

import retrofit2.Call;

public final class Devil {

    private static Hermes hermes;

    public static Hermes getHermes() {
        return hermes;
    }

    private static DevilConfig sConfig;

    private static Gson gson;

    class Topics {
        final static String DEBUG = "D/DEBUG";
        final static String ERROR = "D/ERROR";
        final static String VERBOSE = "D/VERBOSE";
        final static String INFO = "D/INFO";
        final static String REQUEST = "D/REQUEST";
        final static String FAILED_REQUEST = "D/FAILED_REQUEST";
        final static String EXCEPTION = "D/EXCEPTION";
        final static String ALIVE = "D/ALIVE";
        final static String DEAD = "D/DEAD";
    }

    public static void breath(@NonNull DevilConfig devilConfig) {
        sConfig = devilConfig;
        gson = new Gson();
        hermes = new Hermes();
        if (sConfig.isRealTimeEnabled())
            RemoteDevil.breath(devilConfig);
    }

    public static void ex(@NonNull String msg, Exception exception) {
        if (sConfig.isLocalEnabled())
            Log.e(sConfig.getTag(), msg + "\n" + new CodeLocation(exception.getStackTrace()) + "\n" + exception);
        if (sConfig.isRealTimeEnabled())
            RemoteDevil.scream(gson.toJson(new DevilException(Topics.EXCEPTION, msg, new CodeLocation(exception.getStackTrace()), exception + "")), Topics.EXCEPTION);
    }

    public static void d(@NonNull String msg, Object... args) {
        Log.e("Devil", "Config error...");
        if (sConfig == null)
            Log.d("Devil", "Config error...");
        else {
            if (sConfig.isLocalEnabled())
                Log.d(sConfig.getTag(), getCodeLocation() + formatMessage(msg, args));
            if (sConfig.isRealTimeEnabled())
                RemoteDevil.scream(gson.toJson(new DevilLog(Topics.DEBUG, msg, getCodeLocation())), Topics.DEBUG);
        }
    }


    public static void e(@NonNull String msg, Object... args) {
        if (sConfig.isLocalEnabled())
            Log.e(sConfig.getTag(), getCodeLocation().toString() + formatMessage(msg, args));
        if (sConfig.isRealTimeEnabled()) {
            DevilLog devilLog = new DevilLog(Topics.ERROR, msg, getCodeLocation());
            RemoteDevil.scream(gson.toJson(devilLog), Topics.ERROR);
        }
    }

    public static void i(@NonNull String msg, Object... args) {
        if (sConfig.isLocalEnabled())
            Log.i(sConfig.getTag(), getCodeLocation().toString() + formatMessage(msg, args));
        if (sConfig.isRealTimeEnabled()) {
            DevilLog devilLog = new DevilLog(Topics.INFO, msg, getCodeLocation());
            RemoteDevil.scream(gson.toJson(devilLog), Topics.INFO);
        }
    }

    public static void v(@NonNull String msg, Object... args) {
        if (sConfig.isLocalEnabled())
            Log.v(sConfig.getTag(), getCodeLocation().toString() + formatMessage(msg, args));
        if (sConfig.isRealTimeEnabled()) {
            DevilLog devilLog = new DevilLog(Topics.VERBOSE, msg, getCodeLocation());
            RemoteDevil.scream(gson.toJson(devilLog), Topics.VERBOSE);
        }
    }


    public static void pr(DevilRequest devilRequest) {
        if (sConfig.isLocalEnabled())
            if (devilRequest.getCode() < 400)
                Log.d(sConfig.getTag(), gson.toJson(devilRequest));
            else
                Log.e(sConfig.getTag(), gson.toJson(devilRequest));
        if (sConfig.isRealTimeEnabled())
            RemoteDevil.scream(gson.toJson(devilRequest), Topics.REQUEST);
    }

    public static void wtf(Call call, Throwable throwable) {
        if (sConfig.isLocalEnabled())
            Log.e(sConfig.getTag(), call.request().url() + "\n" + throwable.getLocalizedMessage() + "\n" + getCodeLocation().toString());
        if (sConfig.isRealTimeEnabled())
            RemoteDevil.scream(gson.toJson(new DevilFailedRequest(getCodeLocation(), call.request().url() + "", throwable.getLocalizedMessage() + "")), Topics.FAILED_REQUEST);
    }

    private static String formatMessage(@NonNull String msg, Object... args) {
        return args.length == 0 ? msg : String.format(msg, args);
    }


    private static CodeLocation getCodeLocation() {
        return getCodeLocation(3);
    }


    private static CodeLocation getCodeLocation(int depth) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        StackTraceElement[] filteredStackTrace = new StackTraceElement[stackTrace.length - depth];
        System.arraycopy(stackTrace, depth, filteredStackTrace, 0, filteredStackTrace.length);
        return new CodeLocation(filteredStackTrace);
    }


    public static void kill() {
        RemoteDevil.kill();
    }

}

