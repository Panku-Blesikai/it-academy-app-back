package validators;

import it.academy.app.validators.StatusChangeValidator;
import org.junit.Test;

public class StatusChangeValidatorTest {

    private StatusChangeValidator  statusChangeValidator = new StatusChangeValidator();

    @Test(expected = Exception.class)
    public void ShouldFailIfStatusNotInProgress(){
        statusChangeValidator.checkIsStatusInProgress("DECLINED");
    }

    @Test
    public void ShouldPassIfStatusInProgress(){
        statusChangeValidator.checkIsStatusInProgress("INPROGRESS");
    }

}
