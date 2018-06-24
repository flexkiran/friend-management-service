package com.assignment.friendmanagement.model.response;

public class OperationSuccessResponse {

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public OperationSuccessResponse(boolean success) {
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationSuccessResponse that = (OperationSuccessResponse) o;

        return success == that.success;
    }

    @Override
    public int hashCode() {
        return (success ? 1 : 0);
    }
}

