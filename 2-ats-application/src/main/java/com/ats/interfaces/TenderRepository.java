package com.ats.interfaces;

import com.ats.entities.Tender;

import java.util.List;

public interface TenderRepository {
        void saveTender(Tender tender);
        List<String> loadTender();
        void deleteTenderById(String tenderId);
}
