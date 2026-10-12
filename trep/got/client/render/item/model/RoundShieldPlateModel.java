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
public final class RoundShieldPlateModel
extends GotShieldPlateModel {
    public static final RoundShieldPlateModel INSTANCE = new RoundShieldPlateModel();
    private static final List<GotShieldPlateModel.Quad> PLATE = List.of(new GotShieldPlateModel.Quad(-8.0f, 0.0f, 0.0f, 6.0f, 0.390625f, 0.0625f, 0.265625f, 0.15625f), new GotShieldPlateModel.Quad(-8.0f, -6.0f, 0.0f, 0.0f, 0.375f, 0.15625f, 0.25f, 0.0625f), new GotShieldPlateModel.Quad(0.0f, -6.0f, 8.0f, 0.0f, 0.265625f, 0.125f, 0.390625f, 0.03125f), new GotShieldPlateModel.Quad(0.0f, 0.0f, 8.0f, 6.0f, 0.265625f, 0.078125f, 0.390625f, 0.171875f), new GotShieldPlateModel.Quad(0.0f, 6.0f, 6.0f, 8.0f, 0.28125f, 0.109375f, 0.375f, 0.140625f), new GotShieldPlateModel.Quad(-6.0f, 6.0f, 0.0f, 8.0f, 0.375f, 0.109375f, 0.28125f, 0.140625f), new GotShieldPlateModel.Quad(-6.0f, -8.0f, 0.0f, -6.0f, 0.375f, 0.140625f, 0.28125f, 0.109375f), new GotShieldPlateModel.Quad(0.0f, -8.0f, 6.0f, -6.0f, 0.265625f, 0.09375f, 0.359375f, 0.0625f));

    private RoundShieldPlateModel() {
    }

    @Override
    protected List<GotShieldPlateModel.Quad> getPlateQuads() {
        return PLATE;
    }
}

