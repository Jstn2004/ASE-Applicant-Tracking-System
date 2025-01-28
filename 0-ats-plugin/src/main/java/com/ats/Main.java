package com.ats;

import com.ats.ui.MainConsole;

import java.io.IOException;
import java.util.logging.*;

public class Main {
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(Main.class.getName());

        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        for (Handler handler : handlers) {
            if (handler instanceof ConsoleHandler) {
                rootLogger.removeHandler(handler);
            }
        }
        try {
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            System.err.println("Fehler beim Erstellen des FileHandler: " + e.getMessage());
        }

        MainConsole appKonsole = new MainConsole(logger);
        appKonsole.startCLI();
    }
}
