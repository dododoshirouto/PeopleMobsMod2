/**
 *
 */
package site.dodoneko.peoplemobsmod2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_CreeperModel;

import com.mojang.blaze3d.platform.GlStateManager;
public class PMM2_CreeperRenderer<T extends CreeperEntity> extends PMM2_BipedRenderer<T, PMM2_CreeperModel<T>>
{
    private static final ResourceLocation ENTITY_TEXTURES =  new ResourceLocation("textures/entity/creeper/creeper-chan.png");
//    private static final ResourceLocation ENTITY_TEXTURES =  new ResourceLocation("textures/entity/sample-chan.png");

    public PMM2_CreeperRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new PMM2_CreeperModel<T>(0.0f), 0.5F, false);
    }

    @Override
    protected void preRenderCallback(T entitylivingbaseIn, float partialTickTime)
    {
        float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);
        float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        f = f * f;
        f = f * f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        GlStateManager.scalef(f2, f3, f2);
    }



    @Override
    protected int getColorMultiplier(T entitylivingbaseIn, float lightBrightness, float partialTickTime)
    {
        float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);

        if ((int)(f * 10.0F) % 2 == 0)
        {
            return 0;
        }
        else
        {
            int i = (int)(f * 0.2F * 255.0F);
            i = MathHelper.clamp(i, 0, 255);
            return i << 24 | 822083583;
        }
    }

    protected ResourceLocation getEntityTexture(T entity) {
       return ENTITY_TEXTURES;
    }
}
