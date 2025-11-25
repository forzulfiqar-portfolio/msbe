package com.example.common_lib;

public class SubmitResult<T> {

    private boolean success;
    private String message;
    private T data;

    // Default constructor (needed by Jackson)
    public SubmitResult() {}

    // Full constructor
    public SubmitResult(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Static factory method for success
    public static <T> SubmitResult<T> ok(T data) {
        return new SubmitResult<>(true, "OK", data);
    }

    // Static factory method for failure
    public static <T> SubmitResult<T> fail(String message) {
        return new SubmitResult<>(false, message, null);
    }

    // Getters (Jackson uses these to serialize to JSON)
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    // Optional: Setters if needed (not mandatory for read-only responses)
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}
