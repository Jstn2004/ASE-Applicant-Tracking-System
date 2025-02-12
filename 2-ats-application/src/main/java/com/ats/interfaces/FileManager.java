package com.ats.interfaces;

import java.util.List;

public interface FileManager {
    List<String> loadResumeFiles();
    void saveLeaderboardFile(String path, String content);
}
