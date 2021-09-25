/**
 *
 */
package site.dodoneko.peoplemobsmod2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_ChickenModel;

/**
 * @author DODONEKO
 * @net.minecraft.client.renderer.entity.CreeperRenderer
 */
@OnlyIn(Dist.CLIENT)
public class PMM2_ChickenRenderer<T extends ChickenEntity> extends PMM2_BipedRenderer<T, PMM2_ChickenModel<T>>
{
//    private static final ResourceLocation CREEPER_TEXTURES = new ResourceLocation("textures/entity/creeper/creeper.png");
    private static final ResourceLocation ENTITY_TEXTURES = new ResourceLocation("textures/entity/chicken-chan.png");

    public PMM2_ChickenRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new PMM2_ChickenModel<T>(0F), 0.3F, false);
    }


    protected float handleRotationFloat(T livingBase, float partialTicks)
    {
       float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
       float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
       return (MathHelper.sin(f) + 1.0F) * f1;
    }

    protected ResourceLocation getEntityTexture(T entity) {
       return ENTITY_TEXTURES;
    }
}
