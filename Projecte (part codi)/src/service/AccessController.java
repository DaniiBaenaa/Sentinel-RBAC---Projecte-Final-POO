package service;

import model.AccessRequest;
import model.Resource;
import model.User;
import model.enums.ClassificationLevel;
import model.enums.RoleType;
import policy.SecurityPolicy;

import java.util.ArrayList;
import java.util.List;

public class AccessController {

    private List<SecurityPolicy> politiques = new ArrayList<SecurityPolicy>();

    public void setPolicies(List<SecurityPolicy> policies) {
        this.politiques = new ArrayList<SecurityPolicy>(policies);
    }

    public boolean verifyPermission(User usuari, Resource recurs) {
        RoleType rol = usuari.getRole();
        ClassificationLevel nivell = recurs.getSensitivity();

        if (rol == RoleType.ADMIN) {
            return true;
        }

        if (rol == RoleType.EMPLOYEE) {
            return nivell == ClassificationLevel.PUBLIC || nivell == ClassificationLevel.INTERNAL;
        }

        if (rol == RoleType.GUEST) {
            return nivell == ClassificationLevel.PUBLIC;
        }

        return false;
    }