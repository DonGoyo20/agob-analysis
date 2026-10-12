/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package trep.got.skill;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import trep.got.skill.SkillLevelCurve;

public final class SkillDataLoader {
    private static final String LEVEL_CURVE_PATH = "data/agob/skills/level_curve.json";

    private SkillDataLoader() {
    }

    public static SkillLevelCurve loadLevelCurve() {
        SkillLevelCurve skillLevelCurve;
        block9: {
            InputStream stream = SkillDataLoader.class.getClassLoader().getResourceAsStream(LEVEL_CURVE_PATH);
            try {
                if (stream == null) {
                    throw new IllegalStateException("Missing skill level curve resource data/agob/skills/level_curve.json");
                }
                JsonObject root = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
                skillLevelCurve = new SkillLevelCurve(root.get("min_level").getAsInt(), root.get("max_level").getAsInt(), root.get("base_experience").getAsInt(), root.get("exponent").getAsDouble());
                if (stream == null) break block9;
            }
            catch (Throwable throwable) {
                try {
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
                catch (IOException e) {
                    throw new IllegalStateException("Failed to read skill level curve resource data/agob/skills/level_curve.json", e);
                }
            }
            stream.close();
        }
        return skillLevelCurve;
    }
}

