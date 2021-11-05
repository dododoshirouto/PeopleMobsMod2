package site.dodoneko.peoplemobsmod2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_CowModel;

public class PMM2_CowRenderer<T extends MobEntity> extends PMM2_BipedRenderer<T, PMM2_CowModel<T>>
{
    private static final ResourceLocation ENTITY_TEXTURES = new ResourceLocation("textures/entity/cow/cow-chan.png");

    public PMM2_CowRenderer(EntityRendererManager renderManager) {
        super(renderManager, new PMM2_CowModel<T>(0.0F), 0.5F, false);
    }


    protected ResourceLocation getEntityTexture(T entity) {
       return ENTITY_TEXTURES;
    }
}
