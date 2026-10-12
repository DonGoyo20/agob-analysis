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
public final class SquareShieldPlateModel
extends GotShieldPlateModel {
    public static final SquareShieldPlateModel INSTANCE = new SquareShieldPlateModel();
    private static final List<GotShieldPlateModel.Quad> PLATE = List.of(new GotShieldPlateModel.Quad(-5.0f, -5.0f, 5.0f, 5.0f, 0.234375f, 0.03125f, 0.390625f, 0.1875f));

    private SquareShieldPlateModel() {
    }

    @Override
    protected List<GotShieldPlateModel.Quad> getPlateQuads() {
        return PLATE;
    }
}

