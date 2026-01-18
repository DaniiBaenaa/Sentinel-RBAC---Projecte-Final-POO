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

}
