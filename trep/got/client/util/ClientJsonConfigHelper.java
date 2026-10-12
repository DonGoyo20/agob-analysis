/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 */
package trep.got.client.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Optional;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import trep.got.GotMod;

@Environment(value=EnvType.CLIENT)
public final class ClientJsonConfigHelper {
    private ClientJsonConfigHelper() {
    }

    public static Optional<JsonObject> load(class_310 client, String fileName, String failureMessage) {
        Optional<JsonObject> optional;
        block9: {
            Path path = ClientJsonConfigHelper.configPath(client, fileName);
            if (!Files.exists(path, new LinkOption[0])) {
                return Optional.empty();
            }
            BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            try {
                optional = Optional.of(JsonParser.parseReader((Reader)reader).getAsJsonObject());
                if (reader == null) break block9;
            }
            catch (Throwable throwable) {
                try {
                    if (reader != null) {
                        try {
                            ((Reader)reader).close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (IOException | IllegalStateException e) {
                    GotMod.LOGGER.warn(failureMessage, (Throwable)e);
                    return Optional.empty();
                }
            }
            ((Reader)reader).close();
        }
        return optional;
    }

    public static void save(class_310 client, String fileName, JsonObject root, String failureMessage) {
        Path path = ClientJsonConfigHelper.configPath(client, fileName);
        try {
            Files.createDirectories(path.getParent(), new FileAttribute[0]);
            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, new OpenOption[0]);){
                writer.write(root.toString());
            }
        }
        catch (IOException e) {
            GotMod.LOGGER.warn(failureMessage, (Throwable)e);
        }
    }

    private static Path configPath(class_310 client, String fileName) {
        return client.field_1697.toPath().resolve("config").resolve(fileName);
    }
}

