package validators;

import com.example.demo.validator.ExperienceValidator;
import org.junit.Test;

public class ExperienceValidatorTest {
    private ExperienceValidator experienceValidator = new ExperienceValidator();

    @Test(expected = Exception.class)
    public void shouldFailWhenFieldIsEmpty() {
        experienceValidator.validate("");
    }

    @Test(expected = Exception.class)
    public void shouldFailWhenFieldContainsSpacesOnly() {
        experienceValidator.validate("               ");
    }
}
