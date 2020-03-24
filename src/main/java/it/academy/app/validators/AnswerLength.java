package it.academy.app.validators;

public enum AnswerLength {
    shortInfoAnswer(256),
    longQuestionAnswer(1024);
    private final int value;

    AnswerLength(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}