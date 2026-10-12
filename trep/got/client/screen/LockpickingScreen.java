/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.minecraft.class_2338
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_3675
 *  net.minecraft.class_437
 *  net.minecraft.class_8710
 */
package trep.got.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_2338;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_3675;
import net.minecraft.class_437;
import net.minecraft.class_8710;
import trep.got.client.dialogue.DialogueSceneClientState;
import trep.got.network.OpenLockpickingPayload;
import trep.got.network.RequestLockpickingResultPayload;

@Environment(value=EnvType.CLIENT)
public final class LockpickingScreen
extends class_437 {
    private static final class_2960 TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/lockpicking/lockpicking.png");
    private static final int TEXTURE_WIDTH = 256;
    private static final int TEXTURE_HEIGHT = 64;
    private static final int BACKGROUND_WIDTH = 144;
    private static final int BACKGROUND_HEIGHT = 64;
    private static final int VIEWPORT_X = 5;
    private static final int VIEWPORT_Y = 5;
    private static final int VIEWPORT_WIDTH = 134;
    private static final int VIEWPORT_HEIGHT = 51;
    private static final int PICK_SOURCE_X = 144;
    private static final int PICK_SOURCE_Y = 30;
    private static final int PICK_WIDTH = 32;
    private static final int PICK_HEIGHT = 6;
    private static final int LOCK_SOURCE_Y = 0;
    private static final int LOCK_ICON_SIZE = 16;
    private static final int LOCKED_SOURCE_X = 144;
    private static final int UNLOCKED_SOURCE_X = 160;
    private static final int GATE_SOURCE_X = 190;
    private static final int GATE_SOURCE_STEP = 16;
    private static final int GATE_SOURCE_WIDTH = 2;
    private static final int GATE_SOURCE_HEIGHT = 64;
    private static final float PICK_SPEED = 0.62f;
    private static final float PICK_VERTICAL_LERP = 0.35f;
    private static final int TARGET_LOCK_X = 118;
    private static final int TARGET_LOCK_Y = 17;
    private final class_2338 chestPos;
    private final int lockCount;
    private final float speedMultiplier;
    private final String difficultyId;
    private final List<Gate> gates;
    private int scale = 3;
    private int backgroundX;
    private int backgroundY;
    private float pickX = -32.0f;
    private float pickY;
    private float targetPickY = this.pickY = 22.5f;
    private boolean resolved;
    private boolean successful;

    private LockpickingScreen(OpenLockpickingPayload payload) {
        super((class_2561)class_2561.method_43471((String)"screen.agob.lockpicking"));
        this.chestPos = payload.chestPos();
        this.lockCount = class_3532.method_15340((int)payload.lockCount(), (int)3, (int)6);
        this.speedMultiplier = class_3532.method_15363((float)payload.speedMultiplier(), (float)1.0f, (float)1.25f);
        this.difficultyId = LockpickingScreen.normalizeDifficulty(payload.difficultyId());
        this.gates = LockpickingScreen.buildGates(this.lockCount, payload.seed());
    }

    public static void open(OpenLockpickingPayload payload, class_310 client) {
        client.execute(() -> client.method_1507((class_437)new LockpickingScreen(payload)));
    }

    public static void interruptForCaught(int ownerEntityId, class_310 client) {
        client.execute(() -> {
            class_437 patt0$temp = client.field_1755;
            if (patt0$temp instanceof LockpickingScreen) {
                LockpickingScreen screen = (LockpickingScreen)patt0$temp;
                screen.resolved = true;
            }
            client.method_1507(null);
            client.execute(() -> DialogueSceneClientState.openLockpickingCaught(ownerEntityId, client));
        });
    }

    protected void method_57734(float delta) {
    }

    public boolean method_25421() {
        return false;
    }

    public void method_25393() {
        if (this.resolved) {
            return;
        }
        this.updateTargetFromKeys();
        this.pickY += (this.targetPickY - this.pickY) * 0.35f;
        this.pickX += 0.62f * this.speedMultiplier;
        float pickTipX = this.pickX + 32.0f;
        for (int index = this.gates.size() - 1; index >= 0; --index) {
            Gate gate = this.gates.get(index);
            if (pickTipX < gate.x()) continue;
            if (!this.pickFitsGate(gate)) {
                this.fail();
                return;
            }
            if (!(pickTipX >= gate.x() + 2.0f)) continue;
            this.gates.remove(index);
        }
        if (this.pickX + 32.0f >= 118.0f) {
            this.succeed();
        }
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        this.method_25420(context, mouseX, mouseY, delta);
        this.updateLayout();
        this.updateTargetFromMouse(mouseY);
        RenderSystem.enableBlend();
        this.drawDifficultyTitle(context);
        this.drawRegion(context, 0, 0, 144, 64, this.backgroundX, this.backgroundY, 144 * this.scale, 64 * this.scale);
        int viewportX = this.backgroundX + 5 * this.scale;
        int viewportY = this.backgroundY + 5 * this.scale;
        context.method_44379(viewportX, viewportY, viewportX + 134 * this.scale, viewportY + 51 * this.scale);
        for (Gate gate : this.gates) {
            this.drawGate(context, viewportX, viewportY, gate);
        }
        this.drawRegion(context, this.successful ? 160 : 144, 0, 16, 16, viewportX + 118 * this.scale, viewportY + 17 * this.scale, 16 * this.scale, 16 * this.scale);
        this.drawLockpick(context, viewportX, viewportY);
        context.method_44380();
        RenderSystem.disableBlend();
    }

    public void method_16014(double mouseX, double mouseY) {
        this.updateTargetFromMouse(mouseY);
        super.method_16014(mouseX, mouseY);
    }

    public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        this.updateTargetFromMouse(mouseY);
        return true;
    }

    public boolean method_25404(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.fail();
            return true;
        }
        return super.method_25404(keyCode, scanCode, modifiers);
    }

    public void method_25419() {
        if (!this.resolved) {
            this.sendResult(false);
        }
        super.method_25419();
    }

    public void method_25432() {
        if (!this.resolved) {
            this.sendResult(false);
        }
        super.method_25432();
    }

    private void succeed() {
        this.successful = true;
        this.sendResult(true);
        class_310.method_1551().method_1507(null);
    }

    private void fail() {
        this.sendResult(false);
        class_310.method_1551().method_1507(null);
    }

    private void sendResult(boolean success) {
        if (this.resolved) {
            return;
        }
        this.resolved = true;
        ClientPlayNetworking.send((class_8710)new RequestLockpickingResultPayload(this.chestPos, success));
    }

    private boolean pickFitsGate(Gate gate) {
        float holeTop = 25.5f + gate.offsetY() - (float)gate.holeHeight() / 2.0f;
        float holeBottom = holeTop + (float)gate.holeHeight();
        return this.pickY >= holeTop && this.pickY + 6.0f <= holeBottom;
    }

    private void updateLayout() {
        int maxScale = Math.min((this.field_22789 - 24) / 144, (this.field_22790 - 24) / 64);
        this.scale = Math.max(1, Math.min(4, maxScale));
        this.backgroundX = (this.field_22789 - 144 * this.scale) / 2;
        this.backgroundY = (this.field_22790 - 64 * this.scale) / 2;
    }

    private void updateTargetFromMouse(double mouseY) {
        int viewportY = this.backgroundY + 5 * this.scale;
        float localY = (float)((mouseY - (double)viewportY) / (double)this.scale) - 3.0f;
        this.targetPickY = class_3532.method_15363((float)localY, (float)0.0f, (float)45.0f);
    }

    private void updateTargetFromKeys() {
        class_310 client = class_310.method_1551();
        if (client.method_22683() == null) {
            return;
        }
        long handle = client.method_22683().method_4490();
        if (class_3675.method_15987((long)handle, (int)265) || class_3675.method_15987((long)handle, (int)87)) {
            this.targetPickY = Math.max(0.0f, this.targetPickY - 1.25f);
        }
        if (class_3675.method_15987((long)handle, (int)264) || class_3675.method_15987((long)handle, (int)83)) {
            this.targetPickY = Math.min(45.0f, this.targetPickY + 1.25f);
        }
    }

    private void drawRegion(class_332 context, int sourceX, int sourceY, int sourceWidth, int sourceHeight, int x, int y, int width, int height) {
        context.method_25293(TEXTURE, x, y, width, height, (float)sourceX, (float)sourceY, sourceWidth, sourceHeight, 256, 64);
    }

    private void drawDifficultyTitle(class_332 context) {
        String difficultyText = this.difficultyDisplayName();
        String suffix = " Difficulty";
        int difficultyWidth = this.field_22793.method_1727(difficultyText);
        int suffixWidth = this.field_22793.method_1727(suffix);
        int x = (this.field_22789 - difficultyWidth - suffixWidth) / 2;
        int y = Math.max(4, this.backgroundY - 15);
        this.drawTextWithCustomShadow(context, difficultyText, x, y, this.difficultyColor(), this.difficultyShadowColor());
        this.drawTextWithCustomShadow(context, suffix, x + difficultyWidth, y, -12632257, -15395563);
    }

    private void drawTextWithCustomShadow(class_332 context, String text, int x, int y, int color, int shadowColor) {
        context.method_51433(this.field_22793, text, x + 1, y + 1, shadowColor, false);
        context.method_51433(this.field_22793, text, x, y, color, false);
    }

    private String difficultyDisplayName() {
        return switch (this.difficultyId) {
            case "easy" -> "Easy";
            case "hard" -> "Hard";
            default -> "Medium";
        };
    }

    private int difficultyColor() {
        return switch (this.difficultyId) {
            case "easy" -> -11678111;
            case "hard" -> -1881526;
            default -> -1455798;
        };
    }

    private int difficultyShadowColor() {
        return switch (this.difficultyId) {
            case "easy" -> -15516902;
            case "hard" -> -11922155;
            default -> -11911920;
        };
    }

    private void drawLockpick(class_332 context, int viewportX, int viewportY) {
        int pickY = viewportY + Math.round(this.pickY * (float)this.scale);
        if (this.pickX > 0.0f) {
            int extensionWidth = Math.min(Math.round(this.pickX * (float)this.scale), 134 * this.scale);
            this.drawRegion(context, 144, 30, 1, 6, viewportX, pickY, extensionWidth, 6 * this.scale);
        }
        this.drawRegion(context, 144, 30, 32, 6, viewportX + Math.round(this.pickX * (float)this.scale), pickY, 32 * this.scale, 6 * this.scale);
    }

    private void drawGate(class_332 context, int viewportX, int viewportY, Gate gate) {
        int gateX = viewportX + Math.round(gate.x() * (float)this.scale);
        int gateY = viewportY + Math.round((25.5f + gate.offsetY() - 32.0f) * (float)this.scale);
        int gateWidth = 2 * this.scale;
        int gateHeight = 64 * this.scale;
        int viewportBottom = viewportY + 51 * this.scale;
        if (gateY > viewportY) {
            this.drawRegion(context, gate.sourceX(), 0, 2, 1, gateX, viewportY, gateWidth, gateY - viewportY);
        }
        this.drawRegion(context, gate.sourceX(), 0, 2, 64, gateX, gateY, gateWidth, gateHeight);
        int gateBottom = gateY + gateHeight;
        if (gateBottom < viewportBottom) {
            this.drawRegion(context, gate.sourceX(), 63, 2, 1, gateX, gateBottom, gateWidth, viewportBottom - gateBottom);
        }
    }

    private static List<Gate> buildGates(int lockCount, long seed) {
        Random random = new Random(seed);
        ArrayList<Gate> result = new ArrayList<Gate>(lockCount);
        float spacing = 134.0f / (float)(lockCount + 1);
        for (int index = 0; index < lockCount; ++index) {
            int level = class_3532.method_15340((int)(index + 1), (int)1, (int)4);
            int holeHeight = 16 - level * 2;
            float x = spacing * (float)(index + 1);
            float maxOffset = Math.max(0.0f, (float)(51 - holeHeight) / 2.0f - 2.0f);
            float offsetY = (random.nextFloat() * 2.0f - 1.0f) * maxOffset;
            result.add(new Gate(x, offsetY, holeHeight, 190 + (level - 1) * 16));
        }
        return result;
    }

    private static String normalizeDifficulty(String difficultyId) {
        return switch (difficultyId == null ? "" : difficultyId) {
            case "easy", "medium", "hard" -> difficultyId;
            default -> "medium";
        };
    }

    @Environment(value=EnvType.CLIENT)
    private record Gate(float x, float offsetY, int holeHeight, int sourceX) {
    }
}

