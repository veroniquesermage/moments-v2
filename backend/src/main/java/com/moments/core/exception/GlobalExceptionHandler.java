package com.moments.core.exception;

import java.time.OffsetDateTime;
import java.util.Map;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof ResourceNotFoundException resourceNotFoundException) {
            return buildResponse(
                Response.Status.NOT_FOUND,
                "RESOURCE_NOT_FOUND",
                resourceNotFoundException.getMessage(),
                Map.of(
                    "resource", resourceNotFoundException.getResource(),
                    "id", String.valueOf(resourceNotFoundException.getId())
                )
            );
        }

        if (exception instanceof InvalidRequestException) {
            return buildResponse(
                Response.Status.BAD_REQUEST,
                "INVALID_REQUEST",
                exception.getMessage(),
                Map.of()
            );
        }

        if (exception instanceof BusinessRuleException) {
            return buildResponse(
                Response.Status.CONFLICT,
                "BUSINESS_RULE_VIOLATION",
                exception.getMessage(),
                Map.of()
            );
        }

        if (exception instanceof ConstraintViolationException) {
            return buildResponse(
                Response.Status.BAD_REQUEST,
                "VALIDATION_ERROR",
                exception.getMessage(),
                Map.of()
            );
        }

        if (exception instanceof WebApplicationException webApplicationException) {
            Response response = webApplicationException.getResponse();
            Response.Status status = Response.Status.fromStatusCode(response.getStatus());
            return buildResponse(
                status != null ? status : Response.Status.INTERNAL_SERVER_ERROR,
                "HTTP_ERROR",
                webApplicationException.getMessage(),
                Map.of()
            );
        }

        return buildResponse(
            Response.Status.INTERNAL_SERVER_ERROR,
            "INTERNAL_SERVER_ERROR",
            "An unexpected error occurred",
            Map.of()
        );
    }

    private Response buildResponse(Response.Status status, String error, String message, Map<String, Object> details) {
        return Response.status(status)
            .entity(new ApiErrorResponse(
                OffsetDateTime.now(),
                status.getStatusCode(),
                error,
                message,
                details
            ))
            .build();
    }
}
