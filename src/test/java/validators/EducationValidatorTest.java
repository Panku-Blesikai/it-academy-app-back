package validators;

import it.academy.app.validators.EducationValidator;
import org.junit.Test;

public class EducationValidatorTest {
    private EducationValidator educationValidator = new EducationValidator();

    @Test(expected = Exception.class)
    public void shouldFailWhenFieldIsEmpty() {
        educationValidator.validate("");
    }

    @Test(expected = Exception.class)
    public void shouldFailWhenEducationContainsSpacesOnly() {
        educationValidator.validate("          ");
    }

    @Test
    public void shouldReturnSuccess() {
        educationValidator.validate("My University");
    }
}
