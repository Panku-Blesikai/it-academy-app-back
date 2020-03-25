package validators;

import it.academy.app.validators.ThreePartyAgreementValidator;
import org.junit.Test;

public class ThreePartyAgreementValidatorTest {
    private ThreePartyAgreementValidator threePartyAgreementValidator = new ThreePartyAgreementValidator();

    @Test(expected = Exception.class)
    public void shouldFailWhenFieldIsEmpty() {
        threePartyAgreementValidator.validate("");
    }

    @Test(expected = Exception.class)
    public void shouldFailWhenAgreementContainsSpacesOnly() {
        threePartyAgreementValidator.validate("                    ");
    }

}
