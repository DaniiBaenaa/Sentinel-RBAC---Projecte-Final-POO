package policy;

import model.AccessRequest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LocationPolicy extends SecurityPolicy {

    private final Set<String> ipsPermeses = new HashSet<String>();

    public LocationPolicy(String nom, boolean activa, List<String> ips) {
        super(nom, activa);
        ipsPermeses.addAll(ips);
    }

    @Override
    public boolean evaluate(AccessRequest peticio, String ip) {
        if (!activa) {
            return true;
        }

        if (ip == null || ip.isBlank()) {
            return false;
        }

        return ipsPermeses.contains(ip.trim());
    }
}

