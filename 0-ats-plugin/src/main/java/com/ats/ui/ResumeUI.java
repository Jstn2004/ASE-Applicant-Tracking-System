package com.ats.ui;

import com.ats.ResumeController;

public class ResumeUI {

    private MainConsole mainConsole;
    private final ResumeController resumeController;

    public ResumeUI(MainConsole mainConsole, ResumeController resumeController)
    {
        this.mainConsole = mainConsole;
        this.resumeController = resumeController;
    }

    public void loadAllResumes()
    {
        resumeController.loadAllResumes();
    }

    public void setMainConsole(MainConsole mainConsole) {
        this.mainConsole = mainConsole;
    }
}
