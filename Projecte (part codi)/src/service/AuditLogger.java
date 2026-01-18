package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import model.AccessRequest;

public class AuditLogger {

    private final Path ruta;

    public AuditLogger(String nomFitxer) {
        ruta = Path.of(nomFitxer);

        try {
            if (!Files.exists(ruta)) {
                Files.createFile(ruta);
            }
        } catch (IOException e) {
            throw new RuntimeException("No s'ha pogut crear el fitxer de log");
        }
    }

    public void log(String missatge) {
        afegirLinia("[INFO] " + missatge + "\n");
    }

    public void logAccess(AccessRequest peticio, String ip, boolean rolOk, boolean politiquesOk) {
        String linia = "[ACCESS] usuari=" + peticio.getRequestor().getUsername()
                + " rol=" + peticio.getRequestor().getRole()
                + " recurs=" + peticio.getTarget().getResourceId()
                + " ip=" + ip
                + " rolOk=" + rolOk
                + " politiquesOk=" + politiquesOk
                + " estat=" + peticio.getStatus()
                + "\n";

        afegirLinia(linia);
    }

    private void afegirLinia(String text) {
        try {
            Files.writeString(ruta, text, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error escrivint al log: " + e.getMessage());
        }
    }

    public String readTail(int maxLinies) {
        try {
            List<String> linies = Files.readAllLines(ruta);

            int inici = 0;
            if (linies.size() > maxLinies) {
                inici = linies.size() - maxLinies;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = inici; i < linies.size(); i++) {
                sb.append(linies.get(i)).append("\n");
            }
            return sb.toString();

        } catch (IOException e) {
            return "No s'ha pogut llegir el log";
        }
    }
}

