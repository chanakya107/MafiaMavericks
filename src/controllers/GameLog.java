package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameLog {
    private static final List<String> log = new ArrayList<String>();

    public static void add(String logMessage) {
        log.add(getCurrentTime() + " - " + logMessage);
    }

    public static List<String> getLog() {
        return log;
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("hh:mm:ss a");
        Date now = new Date();
        return sdfDate.format(now);
    }

    public static List<String> getClientLog() {
        List<String> clientLog = new ArrayList<String>();
        for (String log1 : log) {
            if (!log1.contains("voted"))
                clientLog.add(log1);
        }
        return clientLog;
    }
}