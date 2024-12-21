package dev.m2t.dbmanager.dto;

public class BaseResponse<T> {
    private Boolean success;
    private String message;
    private Boolean showNotification;
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(Boolean success, String message, Boolean showNotification) {
        this.success = success;
        this.message = message;
        this.showNotification = showNotification;
    }

    public BaseResponse(Boolean success, String message, Boolean showNotification, T data) {
        this.success = success;
        this.message = message;
        this.showNotification = showNotification;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getShowNotification() {
        return showNotification;
    }

    public void setShowNotification(Boolean showNotification) {
        this.showNotification = showNotification;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
