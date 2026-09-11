package com.tads20262.catalago.resource.exceptions;

import java.time.Instant;

public class StandardError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
