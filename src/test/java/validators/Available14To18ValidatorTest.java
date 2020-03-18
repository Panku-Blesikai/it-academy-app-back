package validators;

import com.example.demo.validator.Available14To18Validator;
import org.junit.Test;

public class Available14To18ValidatorTest {
    private Available14To18Validator available14To18Validator = new Available14To18Validator();

    @Test(expected = Exception.class)
    public void shouldFailWhenFieldIsEmpty() {
        available14To18Validator.validate("");
    }

    @Test(expected = Exception.class)
    public void shouldFailWhenFieldContainsSpacesOnly() {
        available14To18Validator.validate("             ");
    }
}
