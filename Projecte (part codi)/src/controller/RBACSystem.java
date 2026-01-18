package controller;

import model.AccessRequest;
import model.Resource;
import model.User;
import model.enums.RequestStatus;
import policy.LocationPolicy;
import policy.SecurityPolicy;
import service.AccessController;
import service.AuditLogger;
import service.AuthenticationManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RBACSystem {

    private final AuthenticationManager gestorAutenticacio = new AuthenticationManager();
    private final AccessController controladorAcces = new AccessController();
    private final AuditLogger registre = new AuditLogger("audit.log");

    private final List<Resource> recursos = new ArrayList<Resource>();
    private final List<SecurityPolicy> politiques = new ArrayList<SecurityPolicy>();

    private User usuariActual;

    public void initialize() {
        gestorAutenticacio.seedDemoUsers();
        recursos.addAll(Resource.demoResources());

        List<String> ips = new ArrayList<String>();
        ips.add("127.0.0.1");
        ips.add("192.168.1.10");

        politiques.add(new LocationPolicy("IPsPermeses", true, ips));
        controladorAcces.setPolicies(politiques);

        registre.log("Sistema inicialitzat correctament");
    }

    public boolean iniciarSessio(String nomUsuari, String contrasenya) {
        User usuari = gestorAutenticacio.authenticate(nomUsuari, contrasenya);

        if (usuari == null) {
            registre.log("Inici de sessió fallit per usuari = " + nomUsuari);
            return false;
        }

        usuariActual = usuari;
        registre.log("Inici de sessió OK: " + usuariActual.getUsername() + " (" + usuariActual.getRole() + ")");
        return true;
    }

    public void tancarSessio() {
        if (usuariActual != null) {
            registre.log("Tancament de sessió: " + usuariActual.getUsername());
        }
        usuariActual = null;
    }

    public boolean sessioIniciada() {
        return usuariActual != null;
    }

    public User getUsuariActual() {
        return usuariActual;
    }

    public List<Resource> getRecursos() {
        return new ArrayList<Resource>(recursos);
    }

    public AccessRequest demanarAcces(Resource recurs, String ip) {
        if (usuariActual == null) {
            throw new IllegalStateException("Iniciar sessió abans de demanar accés");
        }

        if (recurs == null) {
            throw new IllegalArgumentException("Selecciona un recurs vàlid");
        }

        AccessRequest peticio = new AccessRequest(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                usuariActual,
                recurs
        );

        boolean rolOk = controladorAcces.verifyPermission(usuariActual, recurs);
        boolean politiquesOk = controladorAcces.checkPolicies(peticio, ip);

        if (rolOk && politiquesOk) {
            peticio.setStatus(RequestStatus.APPROVED);
        } else {
            peticio.setStatus(RequestStatus.DENIED);
        }

        registre.logAccess(peticio, ip, rolOk, politiquesOk);
        return peticio;
    }

    public String llegirLogFinal(int maxLinies) {
        return registre.readTail(maxLinies);
    }
}
