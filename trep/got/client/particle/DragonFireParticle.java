/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
 *  net.minecraft.class_2396
 *  net.minecraft.class_2400
 *  net.minecraft.class_3999
 *  net.minecraft.class_4002
 *  net.minecraft.class_4003
 *  net.minecraft.class_638
 *  net.minecraft.class_703
 *  net.minecraft.class_707
 */
package trep.got.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.class_2396;
import net.minecraft.class_2400;
import net.minecraft.class_3999;
import net.minecraft.class_4002;
import net.minecraft.class_4003;
import net.minecraft.class_638;
import net.minecraft.class_703;
import net.minecraft.class_707;
import trep.got.registry.GotParticles;

@Environment(value=EnvType.CLIENT)
public final class DragonFireParticle
extends class_4003 {
    private DragonFireParticle(class_638 world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, class_4002 sprites) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);
        this.field_28786 = 0.9f;
        this.field_17867 = 0.8f + this.field_3840.method_43057() * 0.55f;
        this.field_3847 = 5 + this.field_3840.method_43048(3);
        this.field_3861 = 1.0f;
        this.field_3842 = 0.68f + this.field_3840.method_43057() * 0.18f;
        this.field_3859 = 0.22f + this.field_3840.method_43057() * 0.08f;
        this.field_3841 = 0.92f;
        this.method_18141(sprites.method_18139(this.field_3840));
    }

    public static void initialize() {
        ParticleFactoryRegistry.getInstance().register((class_2396)GotParticles.DRAGON_FIRE, Factory::new);
    }

    public float method_18132(float tickDelta) {
        float life = ((float)this.field_3866 + tickDelta) / (float)this.field_3847;
        return this.field_17867 * (1.0f - life * 0.65f);
    }

    public int method_3068(float tint) {
        return 0xF000F0;
    }

    public class_3999 method_18122() {
        return class_3999.field_17829;
    }

    @Environment(value=EnvType.CLIENT)
    private static final class Factory
    implements class_707<class_2400> {
        private final class_4002 sprites;

        private Factory(class_4002 sprites) {
            this.sprites = sprites;
        }

        public class_703 createParticle(class_2400 parameters, class_638 world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new DragonFireParticle(world, x, y, z, velocityX, velocityY, velocityZ, this.sprites);
        }
    }
}

