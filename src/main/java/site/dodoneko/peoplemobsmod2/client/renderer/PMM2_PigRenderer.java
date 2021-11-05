package site.dodoneko.peoplemobsmod2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_PigModel;

public class PMM2_PigRenderer<T extends MobEntity> extends PMM2_BipedRenderer<T, PMM2_PigModel<T>>
{
    private static final ResourceLocation ENTITY_TEXTURES = new ResourceLocation("textures/entity/pig/pig-chan.png");

    public PMM2_PigRenderer(EntityRendererManager renderManager) {
        super(renderManager, new PMM2_PigModel<T>(0.0F), 0.5F, true);
    }


    protected ResourceLocation getEntityTexture(T entity) {
       return ENTITY_TEXTURES;
    }
}
