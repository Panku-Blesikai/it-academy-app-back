package it.academy.app.validators;

import it.academy.app.exception.ValidationException;
import it.academy.app.shared.Status;

public class StatusChangeValidator {

    public void checkIsStatusInProgress(String status) {
        if (!status.equals(Status.INPROGRESS.getStatusInLithuanian())) {
            throw new ValidationException(ErrorMessages.invalidStatus);
        }
    }

}
