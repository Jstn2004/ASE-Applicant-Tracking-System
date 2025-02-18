package com.ats.services;

import com.ats.entities.Applicant;
import com.ats.interfaces.ResumeService;
import com.ats.vo.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicantCreater {

    public List<Applicant> getApplicantFromResume(List<String> resumes)
    {
        List<Applicant> applicants = new ArrayList<>();
        String nameRegex = "Name: (\\S+)";
        String emailRegex = "E-Mail: (\\S+)";
        String phoneRegex = "Telefon: (\\+\\d{2} \\d{3} \\d{7})";
        String addressRegex = "Adresse: (.+)";

        resumes.forEach(resume -> {
            String name = extractData(resume, nameRegex);
            String email = extractData(resume, emailRegex);
            String phone = extractData(resume, phoneRegex);
            String address = extractData(resume, addressRegex);
            Resume resume1 = new Resume(resume);
            Applicant applicant = new Applicant(name,email,phone,address,resume1, 0);
            applicants.add(applicant);
        });

        return applicants;
    }

    public static String extractData(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "Not found";
    }

}
