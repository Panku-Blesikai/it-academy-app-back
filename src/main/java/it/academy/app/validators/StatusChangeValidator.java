package it.academy.app.validators;

import it.academy.app.exception.ValidationException;
import it.academy.app.shared.ErrorMessages;

public class StatusChangeValidator {

    public void checkIsStatusInProgress(String status) {
        if (!status.equals("PERŽIŪRIMA")) {
            throw new ValidationException(ErrorMessages.invalidStatus);
        }
    }

}
