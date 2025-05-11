package com.ats.resumes;

import com.ats.interfaces.FileManagerConfiguration;

import java.io.File;
import java.util.Objects;

public class FileManagerConfigurationImpl implements FileManagerConfiguration {

    @Override
    public String getInputFolderPath() {
        return Objects.requireNonNull(getClass().getClassLoader().getResource("input")).getPath();
    }



    @Override
    public String getOutputFolderPath() {
        File outputDir = new File("output");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
        return outputDir.getAbsolutePath();
    }
}
