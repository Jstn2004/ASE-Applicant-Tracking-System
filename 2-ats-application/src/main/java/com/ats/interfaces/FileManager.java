package com.ats.interfaces;

import com.ats.entities.JobAdvertisement;

import java.util.List;

public interface FileManager {
    List<String> loadResumeFiles();

    void saveLeaderboardFile(String jobTitle, List<String> lines);
}
