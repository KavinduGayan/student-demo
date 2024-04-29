package com.example.studentdemo.error;

public enum ErrorCode {
    NOT_FOUND("NOT_FOUND", "The requested resource was not found"),
    BAD_REQUEST("BAD_REQUEST", "Invalid request made"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "An unexpected error occurred"),
    ILLEGAL_ARGUMENT("ILLEGAL_ARGUMENT", "Argument provided is not valid"),
    DATA_INTEGRITY_VIOLATION("DATA_INTEGRITY_VIOLATION", "Data integrity violation");

    private final String code;
    private final String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}