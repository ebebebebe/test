package test.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorResponse {
    private String message;
    public static ApiErrorResponse from(Exception e) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setMessage(e.getMessage());
        return response;
    }

    public static ApiErrorResponse from(String message) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setMessage(message);
        return response;
    }
}
