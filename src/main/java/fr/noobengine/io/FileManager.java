package fr.noobengine.io;

import java.io.File;

public class FileManager {
    private final File root;

    public FileManager(File rootFolder) {
        if(!rootFolder.isDirectory()) {
            throw new IllegalArgumentException("The given root is not a folder : " + rootFolder.getPath());
        }

        this.root = rootFolder;
    }

    public File get(String path) {
        return new File(root, path);
    }
}
