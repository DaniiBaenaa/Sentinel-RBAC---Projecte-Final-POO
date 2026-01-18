package policy;

import model.AccessRequest;

public abstract class SecurityPolicy {
    protected String nom;
    protected boolean activa;

    public SecurityPolicy(String nom, boolean activa) {
        this.nom = nom;
        this.activa = activa;
    }

    public abstract boolean evaluate(AccessRequest peticio, String ip);
}
