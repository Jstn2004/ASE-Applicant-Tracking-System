import com.ats.entities.Applicant;
import com.ats.entities.EvaluationCriterion;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.entities.criteria.EvaluationExperience;
import com.ats.entities.criteria.EvaluationKeywords;
import com.ats.services.ResumeAnalyser;
import com.ats.vo.Ability;
import com.ats.vo.Keyword;
import com.ats.vo.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResumeAnalyserTest {

    private ResumeAnalyser analyser;
    private Applicant applicant;

    @BeforeEach
    public void setUp() {
        analyser = new ResumeAnalyser(Logger.getLogger(ResumeAnalyserTest.class.getName()));
        applicant = new Applicant(
                "Max Mustermann",
                "max.mustermann@example.com",
                "+49123456789",
                "Musterstraße 1, 12345 Musterstadt",
                new Resume(""),
                100
        );

    }

    @Test
    public void testAnalyseAbilityContant() {
        List<EvaluationCriterion> abilitiesList = List.of(
                new EvaluationAbilities(
                        "Programmiersprachen",
                        0,
                        List.of(new Ability("C#", 100), new Ability("Docker", 50)),
                        5
                ),
                new EvaluationAbilities(
                        "Sprachen",
                        0,
                        List.of(new Ability("Englisch", 50), new Ability("Deutsch", 100)),
                        3
                )
        );
        String contant = "- Programmiersprachen: Python, Docker, Java, C#\n" +
                "- Sprachen: Deutsch, Englisch\n" +
                "- Sonstige: Teamfähigkeit, Problemlösung, schnelles Lernen";

        int result = this.analyser.analyseAbilitiesInResume(contant, abilitiesList, applicant);
        assertEquals(1200, result);

    }

    @Test
    public void testAnalyseExperienceContant() {
        List<EvaluationCriterion> experience = List.of(new EvaluationExperience(
                "Berufserfahrungen",
                80,
                2,
                8
        ));

        String contant = " - Werkstudent Softwareentwicklung bei TechCorp (2022 - 2023)\n" +
                "  - Entwicklung und Wartung von Webanwendungen\n" +
                "  - Optimierung bestehender Codebasis\n" +
                "- Praktikum IT-Support bei ITSolutions (2021 - 2022)\n" +
                "  - Fehleranalyse und Behebung technischer Probleme\n" +
                "  - Kundenberatung und Unterstützung";

        int result = this.analyser.analyseExperienceInResume(contant, experience);
        assertEquals(640, result);
    }

    @Test
    public void testresumeAnalyser() {
        List<EvaluationCriterion> listOfKeywords = List.of(new EvaluationKeywords("Schlüsselwörter", 1, List.of(new Keyword("fleißig", 10)), 8));
        String contant = "Name: Tom Mustermann\n" +
                        "E-Mail: max.mustermann@email.com\n" +
                        "Telefon: +49 170 1234567\n" +
                        "Adresse: Musterstraße 1, 12345 Musterstadt, Deutschland\n" +
                        "\n" +
                        "## Fähigkeiten\n" +
                        "\n" +
                        "- Programmiersprachen: Python, Docker,  Java, C#\n" +
                        "- Sprachen: Deutsch, Englisch\n" +
                        "- Sonstige: Teamfähigkeit, Problemlösung, fleißig\n" +
                        "\n" +
                        "## Erfahrung\n" +
                        "\n" +
                        "- Werkstudent Softwareentwicklung bei TechCorp (2022 - 2023)\n" +
                        "  - Entwicklung und Wartung von Webanwendungen\n" +
                        "  - Optimierung bestehender Codebasis\n" +
                        "- Praktikum IT-Support bei ITSolutions (2021 - 2022)\n" +
                        "  - Fehleranalyse und Behebung technischer Probleme\n" +
                        "  - Kundenberatung und Unterstützung\n" +
                        "\n" +
                        "## Hobbys & Interessen\n" +
                        "\n" +
                        "- Wandern in den Alpen\n" +
                        "- Schach spielen und Turniere besuchen\n" +
                        "- Musikproduktion und DJing\n" +
                        "\n";

        int result = this.analyser.analyseKeywordsInResume(contant,listOfKeywords);
        assertEquals(80, result);
    }


}
