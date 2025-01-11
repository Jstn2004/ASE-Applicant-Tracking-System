package com.ats.datenbank;

import com.ats.interfaces.DatabaseConfiguration;

import java.io.File;

public class DatabaseConfigurationImpl implements DatabaseConfiguration {

        private static final String FILE_PATH;
        static {
            String classPath = DatabaseConfigurationImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            File currentDirectory = new File(classPath).getParentFile();
            if (currentDirectory.getAbsolutePath().contains("target")) {
                currentDirectory = currentDirectory.getParentFile();
            }
            FILE_PATH = new File(currentDirectory, "TenderDatabase.txt").getAbsolutePath();
        }

        @Override
        public String getDatabaseFilePath() {
            return FILE_PATH;
        }

}
