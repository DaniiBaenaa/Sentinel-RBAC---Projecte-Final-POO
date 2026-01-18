package model;

import model.enums.RoleType;

public class User {
    private final String id;
    private final String username;
    private final String passwordHash;
    private RoleType role;
    private boolean active;

    public User(String id, String username, String passwordHash, RoleType role, boolean active) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.active = active;
    }

    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public RoleType getRole() { return role; }
    public boolean isActive() { return active; }

    public void setRole(RoleType role) { this.role = role; }
    public void setActive(boolean active) { this.active = active; }
}
