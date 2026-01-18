package model;

import model.enums.ClassificationLevel;

import java.util.ArrayList;
import java.util.List;

public class Resource {
    private final String resourceId;
    private final String filepath;
    private final ClassificationLevel sensitivity;

    public Resource(String resourceId, String filepath, ClassificationLevel sensitivity) {
        this.resourceId = resourceId;
        this.filepath = filepath;
        this.sensitivity = sensitivity;
    }

    public String getResourceId() { return resourceId; }
    public String getFilepath() { return filepath; }
    public ClassificationLevel getSensitivity() { return sensitivity; }

    @Override
    public String toString() {
        return resourceId + " [" + sensitivity + "] " + filepath;
    }

    public static List<Resource> demoResources() {
        List<Resource> llista = new ArrayList<Resource>();
        llista.add(new Resource("R1", "/docs/public.txt", ClassificationLevel.PUBLIC));
        llista.add(new Resource("R2", "/docs/internal.txt", ClassificationLevel.INTERNAL));
        llista.add(new Resource("R3", "/docs/confidential.txt", ClassificationLevel.CONFIDENTIAL));
        return llista;
    }
}
