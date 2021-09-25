package site.dodoneko.peoplemobsmod2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_SampleModel;

public class PMM2_SampleRenderer<T extends MobEntity> extends PMM2_BipedRenderer<T, PMM2_SampleModel<T>>
{
    private static final ResourceLocation ENTITY_TEXTURES = new ResourceLocation("textures/entity/sample-chan.png");

    public PMM2_SampleRenderer(EntityRendererManager renderManager) {
        super(renderManager, new PMM2_SampleModel<T>(0.0f), 0.5F, false);
    }


    protected ResourceLocation getEntityTexture(T entity) {
       return ENTITY_TEXTURES;
    }
}
