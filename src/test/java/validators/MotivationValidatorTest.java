package validators;

import com.example.demo.validator.MotivationValidator;
import org.junit.Test;

public class MotivationValidatorTest {
    private MotivationValidator motivationValidator = new MotivationValidator();

    @Test(expected = Exception.class)
    public void shouldFailWhenFieldIsEmpty() {
        motivationValidator.validate("");
    }

    @Test(expected = Exception.class)
    public void shouldFailWhenFieldContainsSpacesOnly() {
        motivationValidator.validate("                    ");
    }
}

