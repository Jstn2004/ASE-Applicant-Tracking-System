
package com.ats;

import com.ats.entities.EvaluationCriterion;
import com.ats.evaluationCriterionService.AbilityKeywordCreator;
import com.ats.evaluationCriterionService.EvaluationCriterionFactory;
import com.ats.vo.Ability;
import com.ats.vo.Keyword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AbilityKeywordCreatorTest {

    private AbilityKeywordCreator creator;

    @BeforeEach
    void setUp() {
        Logger logger = Logger.getLogger("TestLogger");
        creator = new AbilityKeywordCreator(logger);
    }

    @Test
    void testGenerateAbility_validInput() {
        String input = "Java;80";
        Ability ability = creator.generateAbility(input);
        assertNotNull(ability);
        assertEquals("java", ability.getAbility());
        assertEquals(80, ability.getPoints());
    }

    @Test
    void testGenerateAbility_invalidInput() {
        assertNull(creator.generateAbility(null));
        assertNull(creator.generateAbility(""));
    }

    @Test
    void testGenerateKeyword_validInput() {
        String input = "Backend;90";
        Keyword keyword = creator.generateKeyword(input);
        assertNotNull(keyword);
        assertEquals("Backend", keyword.getKeyword());
        assertEquals(90, keyword.getPoints());
    }

    @Test
    void testGenerateKeyword_invalidInput() {
        assertNull(creator.generateKeyword(null));
        assertNull(creator.generateKeyword(""));
    }


}