/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_2350
 *  net.minecraft.class_243
 *  net.minecraft.class_2960
 *  net.minecraft.class_3532
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Vector3f
 *  org.joml.Vector3fc
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
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import trep.got.entity.DragonEntity;
import trep.got.entity.DragonModelType;
import trep.got.entity.dragon.DragonModelPartGroup;
import trep.got.entity.dragon.DragonModelParts;
import trep.got.entity.dragon.DragonOrigin;

public final class DragonModelData {
    public static final class_2960 MODEL_ID = class_2960.method_60655((String)"agob", (String)"dragon");
    private static final Map<DragonModelType, DragonModelData> INSTANCES = new EnumMap<DragonModelType, DragonModelData>(DragonModelType.class);
    private static final Map<String, DragonModelData> MODULAR_INSTANCES = new HashMap<String, DragonModelData>();
    private final GroupNode root;
    private final int textureWidth;
    private final int textureHeight;
    private final class_243 seatOffset;
    private final float groundOffset;
    private final Map<String, AnimationClip> animations;

    private DragonModelData(GroupNode root, int textureWidth, int textureHeight, class_243 seatOffset, float groundOffset, Map<String, AnimationClip> animations) {
        this.root = root;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.seatOffset = seatOffset;
        this.groundOffset = groundOffset;
        this.animations = Map.copyOf(animations);
    }

    public static DragonModelData get() {
        return DragonModelData.get(DragonModelType.DEFAULT);
    }

    public static DragonModelData get(DragonEntity dragon) {
        if (dragon == null) {
            return DragonModelData.get(DragonModelType.DEFAULT);
        }
        DragonModelParts parts = dragon.getModelParts();
        if (parts.isDefault()) {
            return DragonModelData.get(dragon.getBaseModelType());
        }
        DragonModelType baseModelType = dragon.getBaseModelType();
        DragonModelType animationModelType = dragon.getAnimationModelType();
        String key = baseModelType.id() + "|" + animationModelType.id() + "|" + parts.encode();
        return MODULAR_INSTANCES.computeIfAbsent(key, ignored -> DragonModelData.compose(baseModelType, parts));
    }

    public static DragonModelData get(DragonModelType type) {
        DragonModelType resolvedType = type == null ? DragonModelType.DEFAULT : type;
        return INSTANCES.computeIfAbsent(resolvedType, DragonModelData::load);
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

    public class_243 seatOffset() {
        return this.seatOffset;
    }

    public class_243 seatOffset(DragonEntity dragon, float tickDelta) {
        Matrix4f transform;
        AnimationState state = new AnimationState(dragon.getWalkAnimationTime(tickDelta), dragon.getFlyAnimationTime(tickDelta), dragon.getDiveAnimationTime(tickDelta), dragon.getIdleAnimationTime(tickDelta), dragon.getWalkAnimationWeight(), dragon.getFlyAnimationWeight(), dragon.getDiveAnimationWeight(), dragon.getFireBreathAnimationProgress(tickDelta));
        Vector3f seatOrigin = DragonModelData.findGroupOrigin(this, this.root, "seat", state, tickDelta, transform = new Matrix4f().translate(0.0f, this.groundOffset, 0.0f), new Vector3f());
        if (seatOrigin == null) {
            return this.seatOffset;
        }
        return new class_243((double)seatOrigin.x, (double)seatOrigin.y + 0.18, (double)seatOrigin.z);
    }

    public class_243 fireOutputOffset(DragonEntity dragon, float tickDelta) {
        Matrix4f transform;
        AnimationState state = new AnimationState(dragon.getWalkAnimationTime(tickDelta), dragon.getFlyAnimationTime(tickDelta), dragon.getDiveAnimationTime(tickDelta), dragon.getIdleAnimationTime(tickDelta), dragon.getWalkAnimationWeight(), dragon.getFlyAnimationWeight(), dragon.getDiveAnimationWeight(), dragon.getFireBreathAnimationProgress(tickDelta));
        Matrix4f outputTransform = DragonModelData.findGroupTransform(this, this.root, "output", state, tickDelta, transform = new Matrix4f().translate(0.0f, this.groundOffset, 0.0f), new Vector3f());
        if (outputTransform != null) {
            Vector3f output = outputTransform.transformPosition(0.0f, 0.0f, 0.0f, new Vector3f());
            return new class_243((double)output.x, (double)output.y, (double)output.z);
        }
        Matrix4f jawTransform = DragonModelData.findGroupTransform(this, this.root, "jaw", state, tickDelta, transform, new Vector3f());
        if (jawTransform == null) {
            return new class_243(0.0, 1.25, -3.95);
        }
        Vector3f output = jawTransform.transformPosition(0.0f, 0.0f, -0.6875f, new Vector3f());
        return new class_243((double)output.x, (double)output.y, (double)output.z);
    }

    public class_243 fireOutputDirection(DragonEntity dragon, float tickDelta) {
        Matrix4f transform;
        AnimationState state = new AnimationState(dragon.getWalkAnimationTime(tickDelta), dragon.getFlyAnimationTime(tickDelta), dragon.getDiveAnimationTime(tickDelta), dragon.getIdleAnimationTime(tickDelta), dragon.getWalkAnimationWeight(), dragon.getFlyAnimationWeight(), dragon.getDiveAnimationWeight(), dragon.getFireBreathAnimationProgress(tickDelta));
        Matrix4f outputTransform = DragonModelData.findGroupTransform(this, this.root, "output", state, tickDelta, transform = new Matrix4f().translate(0.0f, this.groundOffset, 0.0f), new Vector3f());
        if (outputTransform != null) {
            Vector3f direction = outputTransform.transformDirection(0.0f, 0.0f, -1.0f, new Vector3f()).normalize();
            return new class_243((double)direction.x, (double)direction.y, (double)direction.z);
        }
        Matrix4f jawTransform = DragonModelData.findGroupTransform(this, this.root, "jaw", state, tickDelta, transform, new Vector3f());
        if (jawTransform == null) {
            return new class_243(0.0, 0.0, -1.0);
        }
        Vector3f direction = jawTransform.transformDirection(0.0f, 0.0f, -1.0f, new Vector3f()).normalize();
        return new class_243((double)direction.x, (double)direction.y, (double)direction.z);
    }

    public float groundOffset() {
        return this.groundOffset;
    }

    public Optional<AnimationClip> animation(String name) {
        return Optional.ofNullable(this.animations.get(name));
    }

    public PoseSample poseForGroup(GroupNode group, float walkAnimationTime, float flyAnimationTime, float diveAnimationTime, float idleAnimationTime, float walkWeight, float flyWeight, float diveWeight, float fireBreathProgress) {
        return DragonModelData.resolvePose(this, group, new AnimationState(walkAnimationTime, flyAnimationTime, diveAnimationTime, idleAnimationTime, walkWeight, flyWeight, diveWeight, fireBreathProgress), 1.0f);
    }

    private static DragonModelData load(DragonModelType type) {
        DragonModelData dragonModelData;
        block10: {
            InputStream stream = DragonModelData.class.getResourceAsStream(type.resourcePath());
            try {
                if (stream == null) {
                    throw new IllegalStateException("Missing dragon model resource " + type.resourcePath());
                }
                JsonObject rootJson = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
                JsonObject resolution = rootJson.getAsJsonObject("resolution");
                int textureWidth = resolution.get("width").getAsInt();
                int textureHeight = resolution.get("height").getAsInt();
                Map<String, CubeNode> cubesById = DragonModelData.parseCubes(rootJson.getAsJsonArray("elements"));
                Map<String, GroupTemplate> groupsById = DragonModelData.parseGroups(rootJson.getAsJsonArray("groups"));
                GroupNode root = DragonModelData.parseOutliner(rootJson.getAsJsonArray("outliner"), cubesById, groupsById);
                if (root == null) {
                    throw new IllegalStateException("Dragon model outliner did not contain a root group");
                }
                GroupTemplate seat = groupsById.values().stream().filter(group -> "seat".equals(group.name())).findFirst().orElseThrow(() -> new IllegalStateException("Dragon model missing seat group"));
                float minY = DragonModelData.findMinRenderedY(root);
                dragonModelData = new DragonModelData(root, textureWidth, textureHeight, new class_243((double)seat.origin().x() / 16.0, (double)seat.origin().y() / 16.0 + 0.18, (double)seat.origin().z() / 16.0), -minY, DragonModelData.parseAnimations(rootJson.getAsJsonArray("animations")));
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
                    throw new IllegalStateException("Failed to read dragon model data for " + type.id(), exception);
                }
            }
            stream.close();
        }
        return dragonModelData;
    }

    private static DragonModelData compose(DragonModelType baseModelType, DragonModelParts parts) {
        DragonModelData base = DragonModelData.get(baseModelType);
        GroupNode baseBody = DragonModelData.findGroup(base.root(), "body").orElseThrow(() -> new IllegalStateException("Dragon model missing body group"));
        ArrayList<RenderNode> bodyChildren = new ArrayList<RenderNode>();
        DragonModelData.copyBodyGroups(base, bodyChildren, "player");
        DragonModelData.appendPart(bodyChildren, parts, baseModelType, DragonModelPartGroup.NECK);
        DragonModelData.appendPart(bodyChildren, parts, baseModelType, DragonModelPartGroup.WINGS);
        DragonModelData.appendPart(bodyChildren, parts, baseModelType, DragonModelPartGroup.LEGS);
        DragonModelData.appendPart(bodyChildren, parts, baseModelType, DragonModelPartGroup.TAIL);
        DragonModelData.appendPart(bodyChildren, parts, baseModelType, DragonModelPartGroup.TORSO);
        DragonModelData.appendPart(bodyChildren, parts, baseModelType, DragonModelPartGroup.STOMACH);
        DragonModelData.copyBodyGroups(base, bodyChildren, "seat");
        GroupNode body = new GroupNode(baseBody.uuid(), baseBody.name(), DragonModelData.copy(baseBody.origin()), DragonModelData.copy(baseBody.rotation()), List.copyOf(bodyChildren));
        GroupNode root = new GroupNode(base.root().uuid(), base.root().name(), DragonModelData.copy(base.root().origin()), DragonModelData.copy(base.root().rotation()), List.of(body));
        GroupNode seat = DragonModelData.findGroup(root, "seat").orElseThrow(() -> new IllegalStateException("Dragon modular model missing seat group"));
        float minY = DragonModelData.findMinRenderedY(root);
        return new DragonModelData(root, base.textureWidth(), base.textureHeight(), new class_243((double)seat.origin().x() / 16.0, (double)seat.origin().y() / 16.0 + 0.18, (double)seat.origin().z() / 16.0), -minY, DragonModelData.composeAnimations(baseModelType, parts));
    }

    private static Map<String, AnimationClip> composeAnimations(DragonModelType baseModelType, DragonModelParts parts) {
        DragonOrigin origin;
        HashMap<String, AnimationClip> result = new HashMap<String, AnimationClip>(DragonModelData.get((DragonModelType)((origin = baseModelType.origin()) == DragonOrigin.VALYRIAN ? baseModelType : DragonModelType.DEFAULT)).animations);
        for (DragonModelPartGroup group : DragonModelPartGroup.values()) {
            DragonModelType sourceModelType;
            if (!group.isModular() || !DragonModelData.usesVariantAnimations(group, sourceModelType = parts.sourceModelType(group, origin))) continue;
            DragonModelData source = DragonModelData.get(sourceModelType);
            DragonModelData.mergeAnimationBones(result, source.animations, DragonModelData.partGroupUuids(source, group));
        }
        return Map.copyOf(result);
    }

    private static boolean usesVariantAnimations(DragonModelPartGroup group, DragonModelType sourceModelType) {
        if (sourceModelType == DragonModelType.SPIKY) {
            return true;
        }
        return sourceModelType == DragonModelType.LONGNECK && group == DragonModelPartGroup.WINGS;
    }

    private static void mergeAnimationBones(Map<String, AnimationClip> target, Map<String, AnimationClip> source, List<String> boneUuids) {
        if (boneUuids.isEmpty()) {
            return;
        }
        for (Map.Entry<String, AnimationClip> entry : source.entrySet()) {
            AnimationClip sourceClip = entry.getValue();
            AnimationClip targetClip = target.get(entry.getKey());
            HashMap<String, BoneAnimation> bones = targetClip == null ? new HashMap<String, BoneAnimation>() : new HashMap<String, BoneAnimation>(targetClip.bones());
            for (String boneUuid : boneUuids) {
                BoneAnimation sourceBone = sourceClip.bones().get(boneUuid);
                if (sourceBone == null) continue;
                bones.put(boneUuid, sourceBone);
            }
            if (targetClip == null) {
                target.put(entry.getKey(), new AnimationClip(sourceClip.name(), sourceClip.length(), sourceClip.loop(), Map.copyOf(bones)));
                continue;
            }
            target.put(entry.getKey(), new AnimationClip(targetClip.name(), targetClip.length(), targetClip.loop(), Map.copyOf(bones)));
        }
    }

    private static List<String> partGroupUuids(DragonModelData source, DragonModelPartGroup group) {
        List<GroupNode> groups = DragonModelData.partRootGroups(source, group);
        ArrayList<String> uuids = new ArrayList<String>();
        for (GroupNode rootGroup : groups) {
            DragonModelData.collectGroupUuids(rootGroup, uuids);
        }
        return List.copyOf(uuids);
    }

    private static List<GroupNode> partRootGroups(DragonModelData source, DragonModelPartGroup group) {
        return switch (group) {
            case DragonModelPartGroup.NECK -> DragonModelData.firstBodyGroups(source, "neck", "neck_1");
            case DragonModelPartGroup.WINGS -> {
                List<GroupNode> groups = DragonModelData.firstBodyGroups(source, "wings");
                if (groups.isEmpty()) {
                    yield DragonModelData.bodyGroups(source, "foreleg_left", "foreleg_right");
                }
                yield groups;
            }
            case DragonModelPartGroup.LEGS -> {
                List<GroupNode> groups = DragonModelData.firstBodyGroups(source, "legs");
                if (groups.isEmpty()) {
                    yield DragonModelData.bodyGroups(source, "hindleg_left", "hindleg_right");
                }
                yield groups;
            }
            case DragonModelPartGroup.TAIL -> {
                List<GroupNode> groups = DragonModelData.firstBodyGroups(source, "tail");
                if (groups.isEmpty()) {
                    yield DragonModelData.childGroups(source, "torso", "tail_1");
                }
                yield groups;
            }
            case DragonModelPartGroup.TORSO -> {
                ArrayList<GroupNode> groups = new ArrayList<GroupNode>();
                groups.addAll(DragonModelData.firstBodyGroups(source, "torso"));
                groups.addAll(DragonModelData.bodyGroups(source, "scales"));
                yield groups;
            }
            case DragonModelPartGroup.STOMACH -> DragonModelData.firstBodyGroups(source, "stomach");
            default -> List.of();
        };
    }

    private static void collectGroupUuids(GroupNode group, List<String> uuids) {
        uuids.add(group.uuid());
        for (RenderNode child : group.children()) {
            if (!(child instanceof GroupNode)) continue;
            GroupNode childGroup = (GroupNode)child;
            DragonModelData.collectGroupUuids(childGroup, uuids);
        }
    }

    private static void appendPart(List<RenderNode> bodyChildren, DragonModelParts parts, DragonModelType baseModelType, DragonModelPartGroup group) {
        DragonModelData source = DragonModelData.get(parts.sourceModelType(group, baseModelType.origin()));
        switch (group) {
            case NECK: {
                DragonModelData.copyFirstBodyGroups(source, bodyChildren, List.of("neck", "neck_1"));
                break;
            }
            case WINGS: {
                if (DragonModelData.copyFirstBodyGroups(source, bodyChildren, List.of("wings"))) break;
                DragonModelData.copyBodyGroups(source, bodyChildren, "foreleg_left", "foreleg_right");
                break;
            }
            case LEGS: {
                if (DragonModelData.copyFirstBodyGroups(source, bodyChildren, List.of("legs"))) break;
                DragonModelData.copyBodyGroups(source, bodyChildren, "hindleg_left", "hindleg_right");
                break;
            }
            case TAIL: {
                if (DragonModelData.copyFirstBodyGroups(source, bodyChildren, List.of("tail"))) break;
                DragonModelData.copyFilteredBodyGroup(source, bodyChildren, "torso", List.of("tail_1"));
                break;
            }
            case TORSO: {
                if (!DragonModelData.copyFirstBodyGroups(source, bodyChildren, List.of("torso"), List.of("tail_1"))) {
                    DragonModelData.copyBodyGroups(source, bodyChildren, "scales");
                }
                DragonModelData.copyBodyGroups(source, bodyChildren, "scales");
                break;
            }
            case STOMACH: {
                if (DragonModelData.copyFirstBodyGroups(source, bodyChildren, List.of("stomach"))) break;
                DragonModelData.copyBodyCubes(source, bodyChildren, "belly", "chest_left", "chest_right");
                break;
            }
        }
    }

    private static boolean copyFirstBodyGroups(DragonModelData source, List<RenderNode> output, List<String> names) {
        return DragonModelData.copyFirstBodyGroups(source, output, names, List.of());
    }

    private static boolean copyFirstBodyGroups(DragonModelData source, List<RenderNode> output, List<String> names, List<String> excludedChildren) {
        for (String name : names) {
            List<RenderNode> matches = DragonModelData.bodyGroups(source, name).stream().map(group -> DragonModelData.copyNode(group, excludedChildren)).toList();
            if (matches.isEmpty()) continue;
            output.addAll(matches);
            return true;
        }
        return false;
    }

    private static void copyFilteredBodyGroup(DragonModelData source, List<RenderNode> output, String groupName, List<String> includedChildren) {
        DragonModelData.bodyGroups(source, groupName).stream().map(child -> DragonModelData.copyNodeWithOnly(child, includedChildren)).forEach(output::add);
    }

    private static void copyBodyGroups(DragonModelData source, List<RenderNode> output, String ... names) {
        for (String name : names) {
            DragonModelData.bodyGroups(source, name).stream().map(DragonModelData::copyNode).forEach(output::add);
        }
    }

    private static void copyBodyCubes(DragonModelData source, List<RenderNode> output, String ... names) {
        LinkedHashMap<String, Boolean> wanted = new LinkedHashMap<String, Boolean>();
        for (String name : names) {
            wanted.put(name, true);
        }
        DragonModelData.bodyChildren(source).stream().filter(child -> {
            CubeNode cube;
            return child instanceof CubeNode && wanted.containsKey((cube = (CubeNode)child).name());
        }).map(DragonModelData::copyNode).forEach(output::add);
    }

    private static List<RenderNode> bodyChildren(DragonModelData source) {
        return DragonModelData.findGroup(source.root(), "body").map(GroupNode::children).orElse(List.of());
    }

    private static List<GroupNode> firstBodyGroups(DragonModelData source, String ... names) {
        for (String name : names) {
            List<GroupNode> groups = DragonModelData.bodyGroups(source, name);
            if (groups.isEmpty()) continue;
            return groups;
        }
        return List.of();
    }

    private static List<GroupNode> bodyGroups(DragonModelData source, String ... names) {
        LinkedHashMap<String, Boolean> wanted = new LinkedHashMap<String, Boolean>();
        for (String name : names) {
            wanted.put(name, true);
        }
        return DragonModelData.bodyChildren(source).stream().filter(child -> {
            GroupNode group;
            return child instanceof GroupNode && wanted.containsKey((group = (GroupNode)child).name());
        }).map(child -> (GroupNode)child).toList();
    }

    private static List<GroupNode> childGroups(DragonModelData source, String parentName, String ... childNames) {
        LinkedHashMap<String, Boolean> wanted = new LinkedHashMap<String, Boolean>();
        for (String name : childNames) {
            wanted.put(name, true);
        }
        return DragonModelData.bodyGroups(source, parentName).stream().flatMap(group -> group.children().stream()).filter(child -> {
            GroupNode childGroup;
            return child instanceof GroupNode && wanted.containsKey((childGroup = (GroupNode)child).name());
        }).map(child -> (GroupNode)child).toList();
    }

    private static Optional<GroupNode> findGroup(GroupNode group, String name) {
        if (name.equals(group.name())) {
            return Optional.of(group);
        }
        for (RenderNode child : group.children()) {
            GroupNode childGroup;
            Optional<GroupNode> found;
            if (!(child instanceof GroupNode) || !(found = DragonModelData.findGroup(childGroup = (GroupNode)child, name)).isPresent()) continue;
            return found;
        }
        return Optional.empty();
    }

    private static RenderNode copyNode(RenderNode node) {
        return DragonModelData.copyNode(node, List.of());
    }

    private static RenderNode copyNode(RenderNode node, List<String> excludedGroupNames) {
        if (node instanceof GroupNode) {
            GroupNode group = (GroupNode)node;
            List<RenderNode> children = group.children().stream().filter(child -> {
                GroupNode childGroup;
                return !(child instanceof GroupNode) || !excludedGroupNames.contains((childGroup = (GroupNode)child).name());
            }).map(child -> DragonModelData.copyNode(child, excludedGroupNames)).toList();
            return new GroupNode(group.uuid(), group.name(), DragonModelData.copy(group.origin()), DragonModelData.copy(group.rotation()), List.copyOf(children));
        }
        if (node instanceof CubeNode) {
            CubeNode cube = (CubeNode)node;
            return new CubeNode(cube.name(), DragonModelData.copy(cube.from()), DragonModelData.copy(cube.to()), DragonModelData.copy(cube.origin()), DragonModelData.copy(cube.rotation()), cube.mirrorUv(), cube.faces());
        }
        return node;
    }

    private static RenderNode copyNodeWithOnly(RenderNode node, List<String> includedGroupNames) {
        if (node instanceof GroupNode) {
            GroupNode group = (GroupNode)node;
            List<RenderNode> children = group.children().stream().filter(child -> {
                GroupNode childGroup;
                return child instanceof GroupNode && includedGroupNames.contains((childGroup = (GroupNode)child).name());
            }).map(DragonModelData::copyNode).toList();
            return new GroupNode(group.uuid(), group.name(), DragonModelData.copy(group.origin()), DragonModelData.copy(group.rotation()), List.copyOf(children));
        }
        return DragonModelData.copyNode(node);
    }

    private static Vector3f copy(Vector3f vector) {
        return new Vector3f((Vector3fc)vector);
    }

    private static float findMinRenderedY(GroupNode root) {
        float minY = DragonModelData.findMinRenderedY(root, new Matrix4f(), new Vector3f());
        return minY == Float.POSITIVE_INFINITY ? 0.0f : minY;
    }

    private static float findMinRenderedY(RenderNode node, Matrix4f parentTransform, Vector3f parentOrigin) {
        if (node instanceof GroupNode) {
            GroupNode group = (GroupNode)node;
            if ("player".equals(group.name())) {
                return Float.POSITIVE_INFINITY;
            }
            Matrix4f transform = DragonModelData.applyModelTransform(parentTransform, group.origin(), group.rotation(), parentOrigin);
            float minY = Float.POSITIVE_INFINITY;
            for (RenderNode child : group.children()) {
                minY = Math.min(minY, DragonModelData.findMinRenderedY(child, transform, group.origin()));
            }
            return minY;
        }
        if (node instanceof CubeNode) {
            CubeNode cube = (CubeNode)node;
            Matrix4f transform = DragonModelData.applyModelTransform(parentTransform, cube.origin(), cube.rotation(), parentOrigin);
            float minY = Float.POSITIVE_INFINITY;
            for (float x : new float[]{cube.from().x(), cube.to().x()}) {
                for (float y : new float[]{cube.from().y(), cube.to().y()}) {
                    for (float z : new float[]{cube.from().z(), cube.to().z()}) {
                        Vector3f corner = transform.transformPosition((x - cube.origin().x()) / 16.0f, (y - cube.origin().y()) / 16.0f, (z - cube.origin().z()) / 16.0f, new Vector3f());
                        minY = Math.min(minY, corner.y());
                    }
                }
            }
            return minY;
        }
        return Float.POSITIVE_INFINITY;
    }

    private static Matrix4f applyModelTransform(Matrix4f parentTransform, Vector3f origin, Vector3f rotation, Vector3f parentOrigin) {
        return new Matrix4f((Matrix4fc)parentTransform).translate((origin.x() - parentOrigin.x()) / 16.0f, (origin.y() - parentOrigin.y()) / 16.0f, (origin.z() - parentOrigin.z()) / 16.0f).rotateZ(rotation.z() * ((float)Math.PI / 180)).rotateY(rotation.y() * ((float)Math.PI / 180)).rotateX(rotation.x() * ((float)Math.PI / 180));
    }

    private static Map<String, AnimationClip> parseAnimations(JsonArray animations) {
        HashMap<String, AnimationClip> result = new HashMap<String, AnimationClip>();
        if (animations == null) {
            return result;
        }
        for (JsonElement animationElement : animations) {
            JsonObject animationObject = animationElement.getAsJsonObject();
            String name = animationObject.get("name").getAsString();
            float length = animationObject.get("length").getAsFloat();
            boolean loop = animationObject.has("loop") && "loop".equals(animationObject.get("loop").getAsString());
            HashMap<String, BoneAnimation> bones = new HashMap<String, BoneAnimation>();
            JsonObject animators = animationObject.getAsJsonObject("animators");
            if (animators != null) {
                for (Map.Entry entry : animators.entrySet()) {
                    JsonObject animator = ((JsonElement)entry.getValue()).getAsJsonObject();
                    ArrayList<Keyframe> rotationFrames = new ArrayList<Keyframe>();
                    ArrayList<Keyframe> positionFrames = new ArrayList<Keyframe>();
                    JsonArray keyframes = animator.getAsJsonArray("keyframes");
                    if (keyframes != null) {
                        for (JsonElement keyframeElement : keyframes) {
                            JsonObject keyframeObject = keyframeElement.getAsJsonObject();
                            if (!keyframeObject.has("data_points") || keyframeObject.getAsJsonArray("data_points").isEmpty()) continue;
                            JsonObject dataPoint = keyframeObject.getAsJsonArray("data_points").get(0).getAsJsonObject();
                            Vector3f value = new Vector3f(dataPoint.get("x").getAsFloat(), dataPoint.get("y").getAsFloat(), dataPoint.get("z").getAsFloat());
                            Keyframe keyframe = new Keyframe(keyframeObject.get("time").getAsFloat(), value);
                            String channel = keyframeObject.get("channel").getAsString();
                            if ("rotation".equals(channel)) {
                                rotationFrames.add(keyframe);
                                continue;
                            }
                            if (!"position".equals(channel)) continue;
                            positionFrames.add(keyframe);
                        }
                    }
                    if (rotationFrames.isEmpty() && positionFrames.isEmpty()) continue;
                    bones.put((String)entry.getKey(), new BoneAnimation(DragonModelData.sortFrames(rotationFrames), DragonModelData.sortFrames(positionFrames), length, loop));
                }
            }
            result.put(name, new AnimationClip(name, length, loop, Map.copyOf(bones)));
        }
        return result;
    }

    private static List<Keyframe> sortFrames(List<Keyframe> frames) {
        frames.sort((left, right) -> Float.compare(left.time(), right.time()));
        return List.copyOf(frames);
    }

    private static Map<String, CubeNode> parseCubes(JsonArray elements) {
        HashMap<String, CubeNode> cubesById = new HashMap<String, CubeNode>();
        for (JsonElement element : elements) {
            JsonObject object = element.getAsJsonObject();
            if (!object.has("uuid") || !"cube".equals(object.get("type").getAsString())) continue;
            Vector3f from = DragonModelData.readVector(object.getAsJsonArray("from"));
            Vector3f to = DragonModelData.readVector(object.getAsJsonArray("to"));
            Vector3f origin = object.has("origin") ? DragonModelData.readVector(object.getAsJsonArray("origin")) : new Vector3f();
            Vector3f rotation = object.has("rotation") ? DragonModelData.readVector(object.getAsJsonArray("rotation")) : new Vector3f();
            EnumMap<class_2350, FaceUv> faces = new EnumMap<class_2350, FaceUv>(class_2350.class);
            JsonObject faceObject = object.getAsJsonObject("faces");
            for (Map.Entry faceEntry : faceObject.entrySet()) {
                class_2350 direction = DragonModelData.parseDirection((String)faceEntry.getKey());
                if (direction == null) continue;
                JsonArray uvArray = ((JsonElement)faceEntry.getValue()).getAsJsonObject().getAsJsonArray("uv");
                faces.put(direction, new FaceUv(uvArray.get(0).getAsFloat(), uvArray.get(1).getAsFloat(), uvArray.get(2).getAsFloat(), uvArray.get(3).getAsFloat()));
            }
            cubesById.put(object.get("uuid").getAsString(), new CubeNode(object.get("name").getAsString(), from, to, origin, rotation, object.has("mirror_uv") && object.get("mirror_uv").getAsBoolean(), Map.copyOf(faces)));
        }
        return cubesById;
    }

    private static Map<String, GroupTemplate> parseGroups(JsonArray groups) {
        HashMap<String, GroupTemplate> groupsById = new HashMap<String, GroupTemplate>();
        for (JsonElement element : groups) {
            JsonObject object = element.getAsJsonObject();
            String uuid = object.get("uuid").getAsString();
            groupsById.put(uuid, new GroupTemplate(uuid, object.get("name").getAsString(), object.has("origin") ? DragonModelData.readVector(object.getAsJsonArray("origin")) : new Vector3f(), object.has("rotation") ? DragonModelData.readVector(object.getAsJsonArray("rotation")) : new Vector3f()));
        }
        return groupsById;
    }

    private static GroupNode parseOutliner(JsonArray outliner, Map<String, CubeNode> cubesById, Map<String, GroupTemplate> groupsById) {
        for (JsonElement child : outliner) {
            RenderNode node = DragonModelData.parseOutlinerNode(child, cubesById, groupsById);
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
        for (JsonElement child : object.getAsJsonArray("children")) {
            RenderNode parsed = DragonModelData.parseOutlinerNode(child, cubesById, groupsById);
            if (parsed == null) continue;
            children.add(parsed);
        }
        return new GroupNode(template.uuid(), template.name(), template.origin(), template.rotation(), List.copyOf(children));
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

    private static Vector3f findGroupOrigin(DragonModelData model, GroupNode group, String targetName, AnimationState state, float tickDelta, Matrix4f parentTransform, Vector3f parentOrigin) {
        Matrix4f transform = DragonModelData.findGroupTransform(model, group, targetName, state, tickDelta, parentTransform, parentOrigin);
        return transform == null ? null : transform.transformPosition(0.0f, 0.0f, 0.0f, new Vector3f());
    }

    private static Matrix4f findGroupTransform(DragonModelData model, GroupNode group, String targetName, AnimationState state, float tickDelta, Matrix4f parentTransform, Vector3f parentOrigin) {
        PoseSample pose = DragonModelData.resolvePose(model, group, state, tickDelta);
        Matrix4f transform = new Matrix4f((Matrix4fc)parentTransform).translate((pose.position().x() - parentOrigin.x) / 16.0f, (pose.position().y() - parentOrigin.y) / 16.0f, (pose.position().z() - parentOrigin.z) / 16.0f).rotateZ(pose.rotation().z() * ((float)Math.PI / 180)).rotateY(pose.rotation().y() * ((float)Math.PI / 180)).rotateX(pose.rotation().x() * ((float)Math.PI / 180));
        if (targetName.equals(group.name())) {
            return transform;
        }
        for (RenderNode child : group.children()) {
            GroupNode childGroup;
            Matrix4f result;
            if (!(child instanceof GroupNode) || (result = DragonModelData.findGroupTransform(model, childGroup = (GroupNode)child, targetName, state, tickDelta, transform, pose.position())) == null) continue;
            return result;
        }
        return null;
    }

    private static PoseSample resolvePose(DragonModelData model, GroupNode group, AnimationState state, float tickDelta) {
        Vector3f origin = new Vector3f((Vector3fc)group.origin());
        Vector3f rotation = new Vector3f((Vector3fc)group.rotation());
        DragonModelData.applyClip(model, group.uuid(), "walk", state.walkAnimationTime(), state.walkWeight(), origin, rotation);
        DragonModelData.applyClip(model, group.uuid(), "fly", state.flyAnimationTime(), state.flyWeight(), origin, rotation);
        DragonModelData.applyClip(model, group.uuid(), "dive", state.diveAnimationTime(), state.diveWeight(), origin, rotation);
        float idleWeight = Math.max(0.0f, 1.0f - Math.max(state.walkWeight(), Math.max(state.flyWeight(), state.diveWeight())));
        if (model.animation("idle").isPresent()) {
            DragonModelData.applyClip(model, group.uuid(), "idle", state.idleAnimationTime(), idleWeight, origin, rotation);
        } else {
            DragonModelData.applyIdle(group.name(), origin, rotation, state.idleAnimationTime(), idleWeight);
        }
        if (model.animation("fire").isPresent()) {
            DragonModelData.applyFireAnimation(model, group.uuid(), state.fireBreathWeight(), origin, rotation);
        } else {
            DragonModelData.applyFireBreath(group.name(), rotation, state.fireBreathWeight());
        }
        return new PoseSample(origin, rotation);
    }

    private static void applyClip(DragonModelData model, String boneUuid, String animationName, float animationTime, float weight, Vector3f origin, Vector3f rotation) {
        if (weight <= 0.0f) {
            return;
        }
        model.animation(animationName).ifPresent(clip -> {
            PoseSample sample = clip.sample(boneUuid, animationTime);
            origin.lerp((Vector3fc)new Vector3f((Vector3fc)origin).add((Vector3fc)sample.position()), weight);
            rotation.lerp((Vector3fc)new Vector3f((Vector3fc)rotation).add((Vector3fc)sample.rotation()), weight);
        });
    }

    private static void applyFireAnimation(DragonModelData model, String boneUuid, float progress, Vector3f origin, Vector3f rotation) {
        if (progress <= 0.0f) {
            return;
        }
        model.animation("fire").ifPresent(clip -> {
            float sampleTime = class_3532.method_15363((float)progress, (float)0.0f, (float)1.0f) * clip.length();
            PoseSample sample = clip.sample(boneUuid, sampleTime);
            origin.add((Vector3fc)sample.position());
            rotation.add((Vector3fc)sample.rotation());
        });
    }

    private static void applyIdle(String groupName, Vector3f origin, Vector3f rotation, float animationTime, float weight) {
        if (weight <= 0.0f) {
            return;
        }
        float bodyWave = class_3532.method_15374((float)(animationTime * 1.6f)) * weight;
        if ("body".equals(groupName)) {
            origin.add(0.0f, bodyWave * 0.45f, 0.0f);
            rotation.add(bodyWave * 1.1f, 0.0f, 0.0f);
        } else if (groupName.startsWith("neck")) {
            rotation.add(bodyWave * 1.5f, class_3532.method_15374((float)(animationTime * 0.9f)) * 1.2f * weight, 0.0f);
        } else if ("head".equals(groupName)) {
            rotation.add(bodyWave * 1.0f, class_3532.method_15374((float)(animationTime * 0.85f)) * 1.3f * weight, 0.0f);
        } else if (groupName.startsWith("tail")) {
            rotation.add(0.0f, class_3532.method_15374((float)(animationTime * 1.2f + (float)groupName.hashCode() * 0.05f)) * 3.0f * weight, 0.0f);
        } else if ("wing_left".equals(groupName)) {
            rotation.add(0.0f, 0.0f, class_3532.method_15374((float)animationTime) * 0.8f * weight);
        } else if ("wing_right".equals(groupName)) {
            rotation.add(0.0f, 0.0f, -class_3532.method_15374((float)animationTime) * 0.8f * weight);
        }
    }

    private static void applyFireBreath(String groupName, Vector3f rotation, float weight) {
        if (weight <= 0.0f) {
            return;
        }
        float clampedWeight = class_3532.method_15363((float)weight, (float)0.0f, (float)1.0f);
        float xRotation = switch (groupName) {
            case "neck" -> -10.0f * clampedWeight;
            case "neck_1" -> -10.0f * clampedWeight;
            case "neck_2" -> -4.0f * clampedWeight;
            case "head" -> 7.5f * clampedWeight;
            case "jaw" -> -27.5f * clampedWeight;
            default -> 0.0f;
        };
        rotation.add(xRotation, 0.0f, 0.0f);
    }

    public record GroupNode(String uuid, String name, Vector3f origin, Vector3f rotation, List<RenderNode> children) implements RenderNode
    {
    }

    private record AnimationState(float walkAnimationTime, float flyAnimationTime, float diveAnimationTime, float idleAnimationTime, float walkWeight, float flyWeight, float diveWeight, float fireBreathWeight) {
    }

    public record AnimationClip(String name, float length, boolean loop, Map<String, BoneAnimation> bones) {
        public PoseSample sample(String boneUuid, float timeSeconds) {
            BoneAnimation bone = this.bones.get(boneUuid);
            if (bone == null) {
                return PoseSample.IDENTITY;
            }
            float length = bone.length() > 0.0f ? bone.length() : this.length;
            boolean loop = bone.loop();
            float sampleTime = loop && length > 0.0f ? class_3532.method_15341((float)timeSeconds, (float)length) : class_3532.method_15363((float)timeSeconds, (float)0.0f, (float)length);
            return new PoseSample(bone.samplePosition(sampleTime), bone.sampleRotation(sampleTime));
        }
    }

    public record PoseSample(Vector3f position, Vector3f rotation) {
        public static final PoseSample IDENTITY = new PoseSample(new Vector3f(), new Vector3f());
    }

    private record GroupTemplate(String uuid, String name, Vector3f origin, Vector3f rotation) {
    }

    public record BoneAnimation(List<Keyframe> rotationFrames, List<Keyframe> positionFrames, float length, boolean loop) {
        public Vector3f sampleRotation(float time) {
            return BoneAnimation.sample(this.rotationFrames, time);
        }

        public Vector3f samplePosition(float time) {
            return BoneAnimation.sample(this.positionFrames, time);
        }

        private static Vector3f sample(List<Keyframe> frames, float time) {
            if (frames.isEmpty()) {
                return new Vector3f();
            }
            if (frames.size() == 1 || time <= frames.get(0).time()) {
                return new Vector3f((Vector3fc)frames.get(0).value());
            }
            for (int index = 0; index < frames.size() - 1; ++index) {
                Keyframe current = frames.get(index);
                Keyframe next = frames.get(index + 1);
                if (!(time <= next.time())) continue;
                float delta = next.time() - current.time();
                float alpha = delta <= 1.0E-5f ? 0.0f : (time - current.time()) / delta;
                return new Vector3f(class_3532.method_16439((float)alpha, (float)current.value().x(), (float)next.value().x()), class_3532.method_16439((float)alpha, (float)current.value().y(), (float)next.value().y()), class_3532.method_16439((float)alpha, (float)current.value().z(), (float)next.value().z()));
            }
            return new Vector3f((Vector3fc)frames.get(frames.size() - 1).value());
        }
    }

    public static sealed interface RenderNode
    permits GroupNode, CubeNode {
    }

    public record CubeNode(String name, Vector3f from, Vector3f to, Vector3f origin, Vector3f rotation, boolean mirrorUv, Map<class_2350, FaceUv> faces) implements RenderNode
    {
    }

    public record Keyframe(float time, Vector3f value) {
    }

    public record FaceUv(float u0, float v0, float u1, float v1) {
    }
}

