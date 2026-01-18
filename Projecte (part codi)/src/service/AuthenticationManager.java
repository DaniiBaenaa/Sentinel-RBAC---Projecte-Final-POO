package service;

import model.User;
import model.enums.RoleType;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationManager {

    private final List<User> usuaris = new ArrayList<User>();

    public void seedDemoUsers() {
        usuaris.clear();

        usuaris.add(new User("U1", "admin", PasswordUtil.sha256("admin123"), RoleType.ADMIN, true));
        usuaris.add(new User("U2", "empleado", PasswordUtil.sha256("empleado23"), RoleType.EMPLOYEE, true));
        usuaris.add(new User("U3", "guest", PasswordUtil.sha256("guest123"), RoleType.GUEST, true));
    }
