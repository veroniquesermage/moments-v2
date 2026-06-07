package com.moments.core.exception;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void shouldReturn404ForResourceNotFoundException() {
        UUID id = UUID.randomUUID();
        ResourceNotFoundException ex = new ResourceNotFoundException("Activity", id);

        Response response = handler.toResponse(ex);

        assertEquals(404, response.getStatus());
        ApiErrorResponse body = (ApiErrorResponse) response.getEntity();
        assertEquals(404, body.status());
        assertEquals("RESOURCE_NOT_FOUND", body.error());
        assertEquals("Activity not found: " + id, body.message());
        assertEquals("Activity", body.details().get("resource"));
        assertEquals(String.valueOf(id), body.details().get("id"));
        assertNotNull(body.timestamp());
    }

    @Test
    void shouldReturn400ForInvalidRequestException() {
        InvalidRequestException ex = new InvalidRequestException("Invalid data");

        Response response = handler.toResponse(ex);

        assertEquals(400, response.getStatus());
        ApiErrorResponse body = (ApiErrorResponse) response.getEntity();
        assertEquals(400, body.status());
        assertEquals("INVALID_REQUEST", body.error());
        assertEquals("Invalid data", body.message());
        assertTrue(body.details().isEmpty());
    }

    @Test
    void shouldReturn409ForBusinessRuleException() {
        BusinessRuleException ex = new BusinessRuleException("Rule violated");

        Response response = handler.toResponse(ex);

        assertEquals(409, response.getStatus());
        ApiErrorResponse body = (ApiErrorResponse) response.getEntity();
        assertEquals(409, body.status());
        assertEquals("BUSINESS_RULE_VIOLATION", body.error());
        assertEquals("Rule violated", body.message());
    }

    @Test
    void shouldReturn400ForConstraintViolationException() {
        Set<ConstraintViolation<?>> violations = Set.of();
        ConstraintViolationException ex = new ConstraintViolationException("Validation failed", violations);

        Response response = handler.toResponse(ex);

        assertEquals(400, response.getStatus());
        ApiErrorResponse body = (ApiErrorResponse) response.getEntity();
        assertEquals(400, body.status());
        assertEquals("VALIDATION_ERROR", body.error());
    }

    @Test
    void shouldPreserveStatusForWebApplicationException() {
        WebApplicationException ex = new WebApplicationException("Forbidden", 403);

        Response response = handler.toResponse(ex);

        assertEquals(403, response.getStatus());
        ApiErrorResponse body = (ApiErrorResponse) response.getEntity();
        assertEquals(403, body.status());
        assertEquals("HTTP_ERROR", body.error());
    }

    @Test
    void shouldReturn500ForUnknownException() {
        RuntimeException ex = new RuntimeException("Something went wrong");

        Response response = handler.toResponse(ex);

        assertEquals(500, response.getStatus());
        ApiErrorResponse body = (ApiErrorResponse) response.getEntity();
        assertEquals(500, body.status());
        assertEquals("INTERNAL_SERVER_ERROR", body.error());
        assertEquals("An unexpected error occurred", body.message());
    }

    @Test
    void shouldReturnApiErrorResponseWithTimestamp() {
        RuntimeException ex = new RuntimeException("Error");

        Response response = handler.toResponse(ex);

        ApiErrorResponse body = (ApiErrorResponse) response.getEntity();
        assertNotNull(body.timestamp());
    }
}
