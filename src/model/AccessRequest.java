package model;

import java.time.LocalDateTime;
import model.enums.RequestStatus;

public class AccessRequest {
    private final String id;
    private final LocalDateTime timestamp;
    private final User requestor;
    private final Resource target;
    private RequestStatus status;
