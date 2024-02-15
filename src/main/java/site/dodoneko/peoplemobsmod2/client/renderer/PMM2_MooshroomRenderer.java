package site.dodoneko.peoplemobsmod2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.util.ResourceLocation;
import site.dodoneko.peoplemobsmod2.client.layers.PMM2_BlockOnHeadLayer;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_CowModel;

public class PMM2_MooshroomRenderer<T extends MobEntity> extends PMM2_BipedRenderer<MooshroomEntity, PMM2_CowModel<MooshroomEntity>>
{
    private static final ResourceLocation ENTITY_TEXTURES_r = new ResourceLocation("textures/entity/cow/red_mooshroom-chan.png");
    private static final ResourceLocation ENTITY_TEXTURES_b = new ResourceLocation("textures/entity/cow/brown_mooshroom-chan.png");

    public PMM2_MooshroomRenderer(EntityRendererManager renderManager) {
        super(renderManager, new PMM2_CowModel<MooshroomEntity>(0.0F), 0.5F, false);
      this.addLayer(new PMM2_BlockOnHeadLayer<MooshroomEntity, PMM2_CowModel<MooshroomEntity>>(this, true));
    }


    protected ResourceLocation getEntityTexture(MooshroomEntity entity) {
        switch (entity.getMooshroomType()) {
            case RED:
                return ENTITY_TEXTURES_r;
            case BROWN:
                return ENTITY_TEXTURES_b;
            default:
                return ENTITY_TEXTURES_r;
        }
    }
}
