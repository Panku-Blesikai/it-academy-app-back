package validators;

import com.example.demo.validator.ThreePartAgreementValidator;
import org.junit.Test;

public class ThreePartAgreementValidatorTest {
    private ThreePartAgreementValidator threePartAgreementValidator = new ThreePartAgreementValidator();

    @Test(expected = Exception.class)
    public void shouldFailWhenFieldIsEmpty() {
        threePartAgreementValidator.validate("");
    }

    @Test(expected = Exception.class)
    public void shouldFailWhenAgreementContainsSpacesOnly() {
        threePartAgreementValidator.validate("                    ");
    }

}
