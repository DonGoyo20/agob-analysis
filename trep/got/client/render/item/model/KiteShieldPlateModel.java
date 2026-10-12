/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 */
package trep.got.client.render.item.model;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import trep.got.client.render.item.model.GotShieldPlateModel;

@Environment(value=EnvType.CLIENT)
public final class KiteShieldPlateModel
extends GotShieldPlateModel {
    public static final KiteShieldPlateModel INSTANCE = new KiteShieldPlateModel();
    private static final List<GotShieldPlateModel.Quad> PLATE = List.of(new GotShieldPlateModel.Quad(-5.0f, 0.0f, 5.0f, 10.0f, 0.234375f, 0.03125f, 0.390625f, 0.1875f), new GotShieldPlateModel.Quad(-3.0f, -6.0f, -1.0f, -1.0f, 0.3125f, 0.171875f, 0.28125f, 0.25f), new GotShieldPlateModel.Quad(1.0f, -6.0f, 3.0f, -1.0f, 0.390625f, 0.265625f, 0.359375f, 0.34375f), new GotShieldPlateModel.Quad(-1.0f, -7.0f, 1.0f, -1.0f, 0.328125f, 0.21875f, 0.296875f, 0.3125f), new GotShieldPlateModel.Quad(-3.0f, -1.0f, 3.0f, 0.0f, 0.25f, 0.046875f, 0.34375f, 0.0625f), new GotShieldPlateModel.Quad(0.0f, -10.0f, 1.0f, -7.0f, 0.328125f, 0.203125f, 0.3125f, 0.25f), new GotShieldPlateModel.Quad(-1.0f, -10.0f, 0.0f, -7.0f, 0.28125f, 0.109375f, 0.296875f, 0.15625f));

    private KiteShieldPlateModel() {
    }

    @Override
    protected List<GotShieldPlateModel.Quad> getPlateQuads() {
        return PLATE;
    }
}

