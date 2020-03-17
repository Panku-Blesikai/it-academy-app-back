package validators;


import com.example.demo.validator.NameValidator;
import org.junit.Test;

public class NameValidatorTest {
    private NameValidator nameValidator = new NameValidator();

    @Test(expected = Exception.class)
    public void shouldFailWhenStringIsEmpty() {
        nameValidator.validate("");
    }

    @Test(expected = Exception.class)
    public void shouldFailWhenStringContainsSpacesOnly() {
        nameValidator.validate("          ");
    }

    @Test(expected = Exception.class)
    public void shouldFailWhenContainsNotLettersOnly() {
        nameValidator.validate("A86 gs");
    }
}

