/*
 * Decompiled with CFR 0.152.
 */
package trep.got.util;

import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public final class ClasspathResourceHelper {
    private ClasspathResourceHelper() {
    }

    public static List<String> listJsonResources(ClassLoader classLoader, String resourceDirectory) {
        try {
            ArrayList<String> files = new ArrayList<String>();
            Enumeration<URL> resources = classLoader.getResources(resourceDirectory);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                files.addAll(ClasspathResourceHelper.listJsonResources(url, resourceDirectory));
            }
            return files.stream().distinct().sorted(Comparator.naturalOrder()).toList();
        }
        catch (Exception exception) {
            throw new IllegalStateException("Failed to enumerate JSON resources in " + resourceDirectory, exception);
        }
    }

    public static boolean resourceExists(ClassLoader classLoader, String path) {
        return classLoader.getResource(path) != null;
    }

    private static List<String> listJsonResources(URL url, String resourceDirectory) throws Exception {
        URI uri = url.toURI();
        FileSystem fileSystem = null;
        try {
            List<String> list;
            block16: {
                Path directory;
                if ("jar".equals(uri.getScheme())) {
                    try {
                        fileSystem = FileSystems.getFileSystem(uri);
                    }
                    catch (FileSystemNotFoundException ignored) {
                        fileSystem = FileSystems.newFileSystem(uri, Map.of());
                    }
                    directory = fileSystem.provider().getPath(uri);
                } else {
                    directory = Path.of(uri);
                }
                if (!Files.isDirectory(directory, new LinkOption[0])) {
                    List<String> ignored = List.of();
                    return ignored;
                }
                Stream<Path> stream = Files.list(directory);
                try {
                    list = stream.filter(x$0 -> Files.isRegularFile(x$0, new LinkOption[0])).map(path -> path.getFileName().toString()).filter(name -> name.endsWith(".json")).map(name -> resourceDirectory + "/" + name).toList();
                    if (stream == null) break block16;
                }
                catch (Throwable throwable) {
                    if (stream != null) {
                        try {
                            stream.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                stream.close();
            }
            return list;
        }
        finally {
            if (fileSystem != null && fileSystem.isOpen()) {
                fileSystem.close();
            }
        }
    }
}

