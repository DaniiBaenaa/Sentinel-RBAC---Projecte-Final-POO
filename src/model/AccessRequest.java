package model;

import java.time.LocalDateTime;
import model.enums.RequestStatus;

public class AccessRequest {
    private final String id;
    private final LocalDateTime timestamp;
    private final User requestor;
    private final Resource target;
    private RequestStatus status;

    public AccessRequest(String id, LocalDateTime timestamp, User requestor, Resource target) {
        this.id = id;
        this.timestamp = timestamp;
        this.requestor = requestor;
        this.target = target;
        this.status = RequestStatus.PENDING;
    }

    public String getId() { return id; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public User getRequestor() { return requestor; }
    public Resource getTarget() { return target; }
    public RequestStatus getStatus() { return status; }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}

