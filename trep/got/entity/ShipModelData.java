/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_2350
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2960
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Vector3f
 */
package trep.got.entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import trep.got.entity.VesselVariant;
import trep.got.vehicle.platform.PlatformHull;
import trep.got.vehicle.platform.PlatformHullFactory;

public final class ShipModelData {
    public static final class_2960 MODEL_ID = class_2960.method_60655((String)"agob", (String)"ship");
    private static final double SEAT_Y = 0.375;
    private static final Map<VesselVariant, ShipModelData> INSTANCES = new HashMap<VesselVariant, ShipModelData>();
    private static final Pattern SEAT_NAME_PATTERN = Pattern.compile("seat\\d+");
    private final GroupNode root;
    private final int textureWidth;
    private final int textureHeight;
    private final class_243 pivot;
    private final List<SeatAnchor> seats;
    private final List<CollisionShape> collisions;
    private final List<class_238> collisionBoxes;
    private final PlatformHull platformHull;

    private ShipModelData(GroupNode root, int textureWidth, int textureHeight, class_243 pivot, List<SeatAnchor> seats, List<CollisionShape> collisions, List<class_238> collisionBoxes) {
        this.root = root;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.pivot = pivot;
        this.seats = List.copyOf(seats);
        this.collisions = List.copyOf(collisions);
        this.collisionBoxes = List.copyOf(collisionBoxes);
        this.platformHull = PlatformHullFactory.mergeBoxes(this.collisionBoxes, 0.3);
    }

    public static ShipModelData get() {
        return ShipModelData.get(VesselVariant.SHIP);
    }

    public static ShipModelData get(VesselVariant variant) {
        return INSTANCES.computeIfAbsent(variant, ShipModelData::load);
    }

    public GroupNode root() {
        return this.root;
    }

    public int textureWidth() {
        return this.textureWidth;
    }

    public int textureHeight() {
        return this.textureHeight;
    }

    public class_243 pivot() {
        return this.pivot;
    }

    public List<SeatAnchor> seats() {
        return this.seats;
    }

    public List<CollisionShape> collisions() {
        return this.collisions;
    }

    public List<class_238> collisionBoxes() {
        return this.collisionBoxes;
    }

    public PlatformHull platformHull() {
        return this.platformHull;
    }

    private static ShipModelData load(VesselVariant variant) {
        ShipModelData shipModelData;
        block10: {
            String resourcePath = variant.modelResourcePath();
            InputStream stream = ShipModelData.class.getResourceAsStream(resourcePath);
            try {
                if (stream == null) {
                    throw new IllegalStateException("Missing ship model resource " + resourcePath);
                }
                JsonObject rootJson = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
                JsonObject resolution = rootJson.getAsJsonObject("resolution");
                int textureWidth = resolution.get("width").getAsInt();
                int textureHeight = resolution.get("height").getAsInt();
                Map<String, CubeNode> cubesById = ShipModelData.parseCubes(rootJson.getAsJsonArray("elements"));
                Map<String, GroupTemplate> groupsById = ShipModelData.parseGroups(rootJson.getAsJsonArray("groups"));
                GroupNode root = ShipModelData.parseOutliner(rootJson.getAsJsonArray("outliner"), cubesById, groupsById);
                if (root == null) {
                    throw new IllegalStateException("Ship model outliner did not contain a root group");
                }
                ArrayList<CollisionShape> rawCollisions = new ArrayList<CollisionShape>();
                ShipModelData.collectCollisionShapes(root, new Matrix4f(), new Vector3f(0.0f, 0.0f, 0.0f), rawCollisions);
                List<CollisionShape> collisions = ShipModelData.aggregateCollisionShapes(rawCollisions);
                List<class_238> collisionBoxes = ShipModelData.aggregateCollisionBoxes(rawCollisions);
                ShipModelData.addSupplementalCollisionBoxes(variant, collisionBoxes);
                List<SeatAnchor> seats = ShipModelData.parseSeats(groupsById, variant, collisionBoxes);
                class_243 pivot = ShipModelData.computePivot(collisions);
                shipModelData = new ShipModelData(root, textureWidth, textureHeight, pivot, seats, collisions, collisionBoxes);
                if (stream == null) break block10;
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
                catch (IOException exception) {
                    throw new IllegalStateException("Failed reading ship model", exception);
                }
            }
            stream.close();
        }
        return shipModelData;
    }

    private static List<SeatAnchor> parseSeats(Map<String, GroupTemplate> groupsById, VesselVariant variant, List<class_238> collisionBoxes) {
        List<SeatAnchor> seats = groupsById.values().stream().filter(group -> SEAT_NAME_PATTERN.matcher(group.name()).matches()).sorted(Comparator.comparing(GroupTemplate::name)).map(group -> new SeatAnchor(group.name(), new class_243((double)group.origin().x() / 16.0, 0.375 + variant.seatYOffset(), (double)group.origin().z() / 16.0))).toList();
        if (!seats.isEmpty()) {
            return seats;
        }
        class_238 bounds = collisionBoxes.isEmpty() ? new class_238(-1.0, 0.0, -1.0, 1.0, 1.0, 1.0) : ShipModelData.combineBoxes(collisionBoxes);
        return variant.createFallbackSeats(bounds);
    }

    private static class_238 combineBoxes(List<class_238> boxes) {
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;
        for (class_238 box : boxes) {
            minX = Math.min(minX, box.field_1323);
            minY = Math.min(minY, box.field_1322);
            minZ = Math.min(minZ, box.field_1321);
            maxX = Math.max(maxX, box.field_1320);
            maxY = Math.max(maxY, box.field_1325);
            maxZ = Math.max(maxZ, box.field_1324);
        }
        return new class_238(minX, minY, minZ, maxX, maxY, maxZ);
    }

    private static class_243 computePivot(List<CollisionShape> collisions) {
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;
        for (CollisionShape collision : collisions) {
            for (Vector3f corner : collision.localCorners()) {
                minX = Math.min(minX, (double)corner.x());
                minY = Math.min(minY, (double)corner.y());
                minZ = Math.min(minZ, (double)corner.z());
                maxX = Math.max(maxX, (double)corner.x());
                maxY = Math.max(maxY, (double)corner.y());
                maxZ = Math.max(maxZ, (double)corner.z());
            }
        }
        return new class_243((minX + maxX) * 0.5, (minY + maxY) * 0.5, (minZ + maxZ) * 0.5);
    }

    private static Map<String, CubeNode> parseCubes(JsonArray elements) {
        HashMap<String, CubeNode> cubesById = new HashMap<String, CubeNode>();
        for (JsonElement element : elements) {
            JsonObject object = element.getAsJsonObject();
            if (!object.has("uuid") || !"cube".equals(object.get("type").getAsString())) continue;
            Vector3f from = ShipModelData.readVector(object.getAsJsonArray("from"));
            Vector3f to = ShipModelData.readVector(object.getAsJsonArray("to"));
            Vector3f origin = object.has("origin") ? ShipModelData.readVector(object.getAsJsonArray("origin")) : new Vector3f();
            Vector3f rotation = object.has("rotation") ? ShipModelData.readVector(object.getAsJsonArray("rotation")) : new Vector3f();
            EnumMap<class_2350, FaceUv> faces = new EnumMap<class_2350, FaceUv>(class_2350.class);
            JsonObject faceObject = object.getAsJsonObject("faces");
            for (Map.Entry faceEntry : faceObject.entrySet()) {
                class_2350 direction = ShipModelData.parseDirection((String)faceEntry.getKey());
                if (direction == null) continue;
                JsonArray uvArray = ((JsonElement)faceEntry.getValue()).getAsJsonObject().getAsJsonArray("uv");
                faces.put(direction, new FaceUv(uvArray.get(0).getAsFloat(), uvArray.get(1).getAsFloat(), uvArray.get(2).getAsFloat(), uvArray.get(3).getAsFloat()));
            }
            cubesById.put(object.get("uuid").getAsString(), new CubeNode(object.get("name").getAsString(), from, to, origin, rotation, Map.copyOf(faces)));
        }
        return cubesById;
    }

    private static Map<String, GroupTemplate> parseGroups(JsonArray groups) {
        HashMap<String, GroupTemplate> groupsById = new HashMap<String, GroupTemplate>();
        for (JsonElement element : groups) {
            JsonObject object = element.getAsJsonObject();
            String uuid = object.get("uuid").getAsString();
            String name = object.get("name").getAsString();
            Vector3f origin = object.has("origin") ? ShipModelData.readVector(object.getAsJsonArray("origin")) : new Vector3f();
            Vector3f rotation = object.has("rotation") ? ShipModelData.readVector(object.getAsJsonArray("rotation")) : new Vector3f();
            groupsById.put(uuid, new GroupTemplate(uuid, name, origin, rotation));
        }
        return groupsById;
    }

    private static GroupNode parseOutliner(JsonArray outliner, Map<String, CubeNode> cubesById, Map<String, GroupTemplate> groupsById) {
        for (JsonElement child : outliner) {
            RenderNode node = ShipModelData.parseOutlinerNode(child, cubesById, groupsById);
            if (!(node instanceof GroupNode)) continue;
            GroupNode group = (GroupNode)node;
            return group;
        }
        return null;
    }

    private static RenderNode parseOutlinerNode(JsonElement element, Map<String, CubeNode> cubesById, Map<String, GroupTemplate> groupsById) {
        if (element.isJsonPrimitive()) {
            return cubesById.get(element.getAsString());
        }
        JsonObject object = element.getAsJsonObject();
        String uuid = object.get("uuid").getAsString();
        GroupTemplate template = groupsById.get(uuid);
        if (template == null) {
            return cubesById.get(uuid);
        }
        ArrayList<RenderNode> children = new ArrayList<RenderNode>();
        JsonArray childArray = object.getAsJsonArray("children");
        for (JsonElement child : childArray) {
            RenderNode childNode = ShipModelData.parseOutlinerNode(child, cubesById, groupsById);
            if (childNode == null) continue;
            children.add(childNode);
        }
        return new GroupNode(template.name(), template.origin(), template.rotation(), List.copyOf(children));
    }

    private static void collectCollisionShapes(GroupNode group, Matrix4f parentTransform, Vector3f parentOrigin, List<CollisionShape> collisions) {
        Matrix4f groupTransform = new Matrix4f((Matrix4fc)parentTransform).translate((group.origin().x() - parentOrigin.x()) / 16.0f, (group.origin().y() - parentOrigin.y()) / 16.0f, (group.origin().z() - parentOrigin.z()) / 16.0f).rotateXYZ((float)Math.toRadians(group.rotation().x()), (float)Math.toRadians(group.rotation().y()), (float)Math.toRadians(group.rotation().z()));
        for (RenderNode child : group.children()) {
            if (child instanceof GroupNode) {
                GroupNode childGroup = (GroupNode)child;
                ShipModelData.collectCollisionShapes(childGroup, groupTransform, group.origin(), collisions);
                continue;
            }
            CubeNode cube = (CubeNode)child;
            float width = Math.abs(cube.to().x() - cube.from().x());
            float height = Math.abs(cube.to().y() - cube.from().y());
            float depth = Math.abs(cube.to().z() - cube.from().z());
            if (width <= 0.0f || height <= 0.0f || depth <= 0.0f) continue;
            Matrix4f cubeTransform = new Matrix4f((Matrix4fc)groupTransform).translate((cube.origin().x() - group.origin().x()) / 16.0f, (cube.origin().y() - group.origin().y()) / 16.0f, (cube.origin().z() - group.origin().z()) / 16.0f).rotateXYZ((float)Math.toRadians(cube.rotation().x()), (float)Math.toRadians(cube.rotation().y()), (float)Math.toRadians(cube.rotation().z()));
            Vector3f[] corners = new Vector3f[8];
            int index = 0;
            for (int xIndex = 0; xIndex < 2; ++xIndex) {
                for (int yIndex = 0; yIndex < 2; ++yIndex) {
                    for (int zIndex = 0; zIndex < 2; ++zIndex) {
                        float x = ((xIndex == 0 ? cube.from().x() : cube.to().x()) - cube.origin().x()) / 16.0f;
                        float y = ((yIndex == 0 ? cube.from().y() : cube.to().y()) - cube.origin().y()) / 16.0f;
                        float z = ((zIndex == 0 ? cube.from().z() : cube.to().z()) - cube.origin().z()) / 16.0f;
                        corners[index++] = cubeTransform.transformPosition(new Vector3f(x, y, z));
                    }
                }
            }
            collisions.add(new CollisionShape(cube.name(), corners));
        }
    }

    private static List<CollisionShape> aggregateCollisionShapes(List<CollisionShape> rawCollisions) {
        ArrayList<CollisionShape> collisions = new ArrayList<CollisionShape>();
        for (CollisionShape shape : rawCollisions) {
            if (!ShipModelData.isCollisionCube(shape.name())) continue;
            collisions.add(shape);
        }
        return collisions;
    }

    private static List<class_238> aggregateCollisionBoxes(List<CollisionShape> rawCollisions) {
        ArrayList<class_238> boxes = new ArrayList<class_238>();
        for (CollisionShape shape : rawCollisions) {
            if (!ShipModelData.isCollisionCube(shape.name())) continue;
            float minX = Float.POSITIVE_INFINITY;
            float minY = Float.POSITIVE_INFINITY;
            float minZ = Float.POSITIVE_INFINITY;
            float maxX = Float.NEGATIVE_INFINITY;
            float maxY = Float.NEGATIVE_INFINITY;
            float maxZ = Float.NEGATIVE_INFINITY;
            for (Vector3f corner : shape.localCorners()) {
                minX = Math.min(minX, corner.x());
                minY = Math.min(minY, corner.y());
                minZ = Math.min(minZ, corner.z());
                maxX = Math.max(maxX, corner.x());
                maxY = Math.max(maxY, corner.y());
                maxZ = Math.max(maxZ, corner.z());
            }
            boxes.add(new class_238((double)minX, (double)minY, (double)minZ, (double)maxX, (double)maxY, (double)maxZ));
        }
        return boxes;
    }

    private static void addSupplementalCollisionBoxes(VesselVariant variant, List<class_238> collisionBoxes) {
        if (variant == VesselVariant.LONGSHIP) {
            collisionBoxes.add(new class_238(-1.05, 0.62, -7.9, 1.05, 0.86, -3.1));
        }
    }

    private static boolean isCollisionCube(String name) {
        String lower = name.toLowerCase();
        return !lower.contains("sail") && !lower.startsWith("yard");
    }

    private static CollisionShape createBoundsShape(String name, List<Vector3f> corners) {
        float minX = Float.POSITIVE_INFINITY;
        float minY = Float.POSITIVE_INFINITY;
        float minZ = Float.POSITIVE_INFINITY;
        float maxX = Float.NEGATIVE_INFINITY;
        float maxY = Float.NEGATIVE_INFINITY;
        float maxZ = Float.NEGATIVE_INFINITY;
        for (Vector3f corner : corners) {
            minX = Math.min(minX, corner.x());
            minY = Math.min(minY, corner.y());
            minZ = Math.min(minZ, corner.z());
            maxX = Math.max(maxX, corner.x());
            maxY = Math.max(maxY, corner.y());
            maxZ = Math.max(maxZ, corner.z());
        }
        float centerX = (minX + maxX) * 0.5f;
        float centerZ = (minZ + maxZ) * 0.5f;
        float halfWidth = (maxX - minX) * 0.25f;
        float halfDepth = (maxZ - minZ) * 0.25f;
        float halfHeight = (maxY - minY) * 0.5f;
        minX = centerX - halfWidth;
        maxX = centerX + halfWidth;
        minZ = centerZ - halfDepth;
        maxZ = centerZ + halfDepth;
        maxY = minY + halfHeight;
        Vector3f[] shapeCorners = new Vector3f[8];
        int index = 0;
        for (int xIndex = 0; xIndex < 2; ++xIndex) {
            for (int yIndex = 0; yIndex < 2; ++yIndex) {
                for (int zIndex = 0; zIndex < 2; ++zIndex) {
                    shapeCorners[index++] = new Vector3f(xIndex == 0 ? minX : maxX, yIndex == 0 ? minY : maxY, zIndex == 0 ? minZ : maxZ);
                }
            }
        }
        return new CollisionShape(name, shapeCorners);
    }

    private static Vector3f readVector(JsonArray array) {
        return new Vector3f(array.get(0).getAsFloat(), array.get(1).getAsFloat(), array.get(2).getAsFloat());
    }

    private static class_2350 parseDirection(String name) {
        return switch (name) {
            case "north" -> class_2350.field_11043;
            case "south" -> class_2350.field_11035;
            case "east" -> class_2350.field_11034;
            case "west" -> class_2350.field_11039;
            case "up" -> class_2350.field_11036;
            case "down" -> class_2350.field_11033;
            default -> null;
        };
    }

    public record GroupNode(String name, Vector3f origin, Vector3f rotation, List<RenderNode> children) implements RenderNode
    {
    }

    public record CollisionShape(String name, Vector3f[] localCorners) {
    }

    public record FaceUv(float u0, float v0, float u1, float v1) {
    }

    public record CubeNode(String name, Vector3f from, Vector3f to, Vector3f origin, Vector3f rotation, Map<class_2350, FaceUv> faces) implements RenderNode
    {
    }

    private record GroupTemplate(String uuid, String name, Vector3f origin, Vector3f rotation) {
    }

    public static sealed interface RenderNode
    permits GroupNode, CubeNode {
    }

    public record SeatAnchor(String name, class_243 localPos) {
    }
}

