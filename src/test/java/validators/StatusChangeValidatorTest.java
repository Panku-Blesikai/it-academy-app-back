package validators;

import it.academy.app.exception.ValidationException;
import it.academy.app.shared.Status;
import it.academy.app.validators.StatusChangeValidator;
import org.junit.Test;

public class StatusChangeValidatorTest {

    private StatusChangeValidator  statusChangeValidator = new StatusChangeValidator();

    @Test(expected = ValidationException.class)
    public void ShouldFailIfStatusNotInProgress(){
        statusChangeValidator.checkIsStatusInProgress(Status.DECLINED.getStatusInLithuanian());
    }

    @Test
    public void ShouldPassIfStatusInProgress(){
        statusChangeValidator.checkIsStatusInProgress(Status.INPROGRESS.getStatusInLithuanian());
    }

}
