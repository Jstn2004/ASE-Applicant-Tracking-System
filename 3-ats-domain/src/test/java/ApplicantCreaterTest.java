
import com.ats.entities.Applicant;
import com.ats.services.ApplicantCreater;
import com.ats.vo.Resume;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicantCreaterTest {

    @Test
    public void testGetApplicantFromValidResume() {
        ApplicantCreater creater = new ApplicantCreater();

        String sampleResume = """
                Name: MaxMustermann
                E-Mail: max@mustermann.de
                Telefon: +49 123 1234567
                Adresse: Musterstraße 1, 12345 Musterstadt
                Berufserfahrung: Java Entwickler bei Firma XYZ
                """;

        List<Applicant> applicants = creater.getApplicantFromResume(List.of(sampleResume));

        assertEquals(1, applicants.size());

        Applicant applicant = applicants.get(0);

        assertEquals("MaxMustermann", applicant.getName());
        assertEquals("max@mustermann.de", applicant.getEmail());
        assertEquals("+49 123 1234567", applicant.getPhoneNumber());
        assertEquals("Musterstraße 1, 12345 Musterstadt", applicant.getAddress());
        assertNotNull(applicant.getResume());
        assertTrue(applicant.getResume() instanceof Resume);
    }

    @Test
    public void testGetApplicantFromIncompleteResume() {
        ApplicantCreater creater = new ApplicantCreater();

        String incompleteResume = """
                Name: LisaTest
                E-Mail: lisa@test.de
                Adresse: Straße 5, 98765 Stadt
                """;

        List<Applicant> applicants = creater.getApplicantFromResume(List.of(incompleteResume));
        Applicant applicant = applicants.get(0);

        assertEquals("LisaTest", applicant.getName());
        assertEquals("lisa@test.de", applicant.getEmail());
        assertEquals("Not found", applicant.getPhoneNumber());
        assertEquals("Straße 5, 98765 Stadt", applicant.getAddress());
    }

    @Test
    public void testGetApplicantFromMultipleResumes() {
        ApplicantCreater creater = new ApplicantCreater();

        String r1 = "Name: A\nE-Mail: a@x.de\nTelefon: +49 111 1111111\nAdresse: Aweg 1";
        String r2 = "Name: B\nE-Mail: b@x.de\nTelefon: +49 222 2222222\nAdresse: Bweg 2";

        List<Applicant> applicants = creater.getApplicantFromResume(List.of(r1, r2));

        assertEquals(2, applicants.size());
        assertEquals("A", applicants.get(0).getName());
        assertEquals("B", applicants.get(1).getName());
    }
}