package com.ats.interfaces;

import java.util.List;

public interface ResumeService {
    List<String> loadAllResumes();
    String setSelectedJobAdvertisement(String id);

}
