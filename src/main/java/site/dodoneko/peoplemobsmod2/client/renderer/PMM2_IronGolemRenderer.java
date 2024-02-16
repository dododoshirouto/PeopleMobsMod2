package site.dodoneko.peoplemobsmod2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_BipedModel;

public class PMM2_IronGolemRenderer<T extends MobEntity> extends PMM2_BipedRenderer<T, PMM2_BipedModel<T>>
{
    private static ResourceLocation ENTITY_TEXTURES = new ResourceLocation("textures/entity/iron_golem-chan.png");

    public PMM2_IronGolemRenderer(EntityRendererManager renderManager) {
        super(renderManager, new PMM2_BipedModel<T>(0.0f), 0.5F, false);
        PMM2_BipedModel<T> model = this.getEntityModel();
        model.boobHeight = 0.8F;
        model.modelScale = 1.2F;
    }


    protected ResourceLocation getEntityTexture(T entity) {
        return ENTITY_TEXTURES;
    }
}
