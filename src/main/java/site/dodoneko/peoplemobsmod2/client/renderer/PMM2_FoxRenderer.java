package site.dodoneko.peoplemobsmod2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.util.ResourceLocation;
import site.dodoneko.peoplemobsmod2.client.layers.PMM2_FoxHeldItemLayer;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_FoxModel;

public class PMM2_FoxRenderer<T extends FoxEntity> extends PMM2_BipedRenderer<T, PMM2_FoxModel<T>> {
    private static final ResourceLocation ENTITY_TEXTURES = new ResourceLocation("textures/entity/fox/fox-chan.png");
    private static final ResourceLocation ENTITY_TEXTURES_snow = new ResourceLocation(
            "textures/entity/fox/snow_fox-chan.png");

    public PMM2_FoxRenderer(EntityRendererManager renderManager) {
        super(renderManager, new PMM2_FoxModel<T>(0.0f), 0.5F, false);
        this.addLayer(new PMM2_FoxHeldItemLayer<T>(this));
    }

    protected ResourceLocation getEntityTexture(T entity) {
        return entity.getVariantType() == FoxEntity.Type.RED ? ENTITY_TEXTURES : ENTITY_TEXTURES_snow;
    }
}
