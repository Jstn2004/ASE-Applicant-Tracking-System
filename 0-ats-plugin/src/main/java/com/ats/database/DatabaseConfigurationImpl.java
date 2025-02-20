package com.ats.database;

import com.ats.interfaces.DatabaseConfiguration;

import java.io.File;
import java.util.Objects;

public class DatabaseConfigurationImpl implements DatabaseConfiguration {

        private static final String FILE_PATH;
        static {
            String currentDirectory = Objects.requireNonNull(DatabaseConfigurationImpl.class.getClassLoader().getResource("db")).getPath();
            FILE_PATH = new File(currentDirectory, "JobAdvertisementDatabase.txt").getAbsolutePath();
        }

        @Override
        public String getDatabaseFilePath() {
            return FILE_PATH;
        }

}
