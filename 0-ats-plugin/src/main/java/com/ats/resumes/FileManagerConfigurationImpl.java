package com.ats.resumes;

import com.ats.interfaces.FileManagerConfiguration;

import java.util.Objects;

public class FileManagerConfigurationImpl implements FileManagerConfiguration {

    @Override
    public String getInputFolderPath() {
        return Objects.requireNonNull(getClass().getClassLoader().getResource("input")).getPath();
    }

    @Override
    public String getOutputFolderPath() {
        return "";
    }
}
