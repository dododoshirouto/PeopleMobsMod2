package site.dodoneko.peoplemobsmod2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_SquidModel;

import com.mojang.blaze3d.platform.GlStateManager;

public class PMM2_SquidRenderer<T extends SquidEntity> extends PMM2_BipedRenderer<T, PMM2_SquidModel<T>>
{
    private static final ResourceLocation ENTITY_TEXTURES = new ResourceLocation("textures/entity/squid-chan.png");

    public PMM2_SquidRenderer(EntityRendererManager renderManager) {
        super(renderManager, new PMM2_SquidModel<T>(0.0f), 0.5F, false);
    }


    protected void applyRotations(SquidEntity entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        this.getEntityModel().partialTicks = partialTicks;

        float f = MathHelper.lerp(partialTicks, entityLiving.prevSquidPitch, entityLiving.squidPitch);
        float f1 = MathHelper.lerp(partialTicks, entityLiving.prevSquidYaw, entityLiving.squidYaw);
        GlStateManager.translatef(0.0F, 0.5F, 0.0F);
        GlStateManager.rotatef(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(f + 90F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotatef(f1, 0.0F, 0.0F, 1.0F);
        GlStateManager.translatef(0.0F, -0.5F, 0.5F);
    }

    protected ResourceLocation getEntityTexture(T entity) {
        return ENTITY_TEXTURES;
    }
}
