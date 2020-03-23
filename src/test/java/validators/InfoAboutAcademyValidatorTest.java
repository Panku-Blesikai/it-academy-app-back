package validators;


import it.academy.app.validators.InfoAboutAcademyValidator;
import org.junit.Test;

public class InfoAboutAcademyValidatorTest {
    private InfoAboutAcademyValidator infoAboutAcademyValidator = new InfoAboutAcademyValidator();

    @Test(expected = Exception.class)
    public void shouldFailWhenFieldIsEmpty() {
        infoAboutAcademyValidator.validate("");
    }

    @Test(expected = Exception.class)
    public void shouldFailWhenFieldContainsSpacesOnly() {
        infoAboutAcademyValidator.validate("             ");
    }
}
