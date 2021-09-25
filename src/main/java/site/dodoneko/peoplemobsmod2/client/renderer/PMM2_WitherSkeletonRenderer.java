package site.dodoneko.peoplemobsmod2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.util.ResourceLocation;

import com.mojang.blaze3d.platform.GlStateManager;

public class PMM2_WitherSkeletonRenderer<T extends AbstractSkeletonEntity> extends PMM2_SkeletonRenderer<T>
{
    private static final ResourceLocation ENTITY_TEXTURES = new ResourceLocation("textures/entity/skeleton/wither_skeleton-chan.png");

    public PMM2_WitherSkeletonRenderer(EntityRendererManager renderManager) {
        super(renderManager);
        this.getEntityModel().boobHeight = 0.75F;
    }

    protected ResourceLocation getEntityTexture(T entity) {
       return ENTITY_TEXTURES;
    }

    protected void preRenderCallback(T entitylivingbaseIn, float partialTickTime) {
       GlStateManager.scalef(1.2F, 1.2F, 1.2F);
    }
}
