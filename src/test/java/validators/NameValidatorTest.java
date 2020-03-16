package validators;

import com.example.demo.validator.NameValidator;
import org.junit.Test;

public class NameValidatorTest {
    private NameValidator nameValidator = new NameValidator();

    @Test(expected = Exception.class)
    public void shouldFailWhenStringIsEmpty() throws Exception {
        nameValidator.validate("", "Field is mandatory");
    }

    @Test(expected = Exception.class)
    public void shouldFailWhenContainsNotLettersOnly() throws Exception {
        nameValidator.validate("A86 gs", "Name must contain letters only");
    }
}
