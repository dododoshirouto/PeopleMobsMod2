package site.dodoneko.peoplemobsmod2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_BipedModel;

public class PMM2_SilverfishRenderer<T extends MobEntity> extends PMM2_BipedRenderer<T, PMM2_BipedModel<T>>
{
    private static ResourceLocation ENTITY_TEXTURES = new ResourceLocation("textures/entity/silverfish-chan.png");

    public PMM2_SilverfishRenderer(EntityRendererManager renderManager) {
        super(renderManager, new PMM2_BipedModel<T>(0.0f), 0.5F, false);
        PMM2_BipedModel<T> model = this.getEntityModel();
        model.boobHeight = 0.8F;
        model.modelScale = 0.35F;
        model.useChildModel = true;
    }


    protected ResourceLocation getEntityTexture(T entity) {
        return ENTITY_TEXTURES;
    }
}
