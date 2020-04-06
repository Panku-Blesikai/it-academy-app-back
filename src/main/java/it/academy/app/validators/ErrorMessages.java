package it.academy.app.validators;

public enum ErrorMessages {

    fieldIsEmpty {
        @Override
        public String toString() {
            return "Field is empty";
        }
    },
    illegalCharactersUsed {
        @Override
        public String toString() {
            return "Field is empty";
        }
    },
    moreThanTwoSurnames {
        @Override
        public String toString() {
            return "Invalid surname input, more than two surnames";
        }
    },
    invalidEmailFormat {
        @Override
        public String toString() {
            return "Email format is invalid";
        }
    },
    invalidTelNumberLength {
        @Override
        public String toString() {
            return "Telephone number length is incorrect";
        }
    },
    invalidTelNumberFormat {
        @Override
        public String toString() {
            return "Telephone number format is invalid";
        }
    },
    invalidInputSize {
        @Override
        public String toString() {
            return "Input size is invalid";
        }
    },
    invalidStatus {
        @Override
        public String toString() {
            return "Only 'IN PROGRESS' status can be changed";
        }
    }

}
