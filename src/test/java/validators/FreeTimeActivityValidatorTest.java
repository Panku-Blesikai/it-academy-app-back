package validators;

import it.academy.app.validators.FreeTimeActivityValidator;
import org.junit.Test;

public class FreeTimeActivityValidatorTest {
    private FreeTimeActivityValidator freeTimeActivityValidator = new FreeTimeActivityValidator();

    @Test(expected = Exception.class)
    public void shouldFailWhenFreeTimeActivityFieldIsEmpty() {
        freeTimeActivityValidator.validate("");
    }

    @Test(expected = Exception.class)
    public void shouldFailWhenFreeTimeActivityFieldContainsSpacesOnly() {
        freeTimeActivityValidator.validate("          ");
    }
}
