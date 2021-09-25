package site.dodoneko.peoplemobsmod2.client.renderer;

import site.dodoneko.peoplemobsmod2.client.model.PMM2_SkeletonModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.util.ResourceLocation;

public class PMM2_SkeletonRenderer<T extends AbstractSkeletonEntity> extends PMM2_BipedRenderer<T, PMM2_SkeletonModel<T>>
{
    private static final ResourceLocation ENTITY_TEXTURES = new ResourceLocation("textures/entity/skeleton/skeleton-chan.png");

    public PMM2_SkeletonRenderer(EntityRendererManager renderManager) {
        super(renderManager, new PMM2_SkeletonModel<T>(0.0f), new PMM2_SkeletonModel<T>(0.5f, 64, 32), new PMM2_SkeletonModel<T>(1.0f, 64, 32), 0.5F);
    }


    protected ResourceLocation getEntityTexture(T entity) {
       return ENTITY_TEXTURES;
    }
}
