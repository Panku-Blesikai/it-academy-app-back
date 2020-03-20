package com.example.demo.validator;

import com.example.demo.applicationForm.Status;

public class StatusChangeValidator {

    public void checkIsStatusInProgress(String status) {
        if (!status.equals(Status.INPROGRESS.name())) {
            throw new ValidationException(ErrorMessages.invalidStatus);
        }
    }

}
