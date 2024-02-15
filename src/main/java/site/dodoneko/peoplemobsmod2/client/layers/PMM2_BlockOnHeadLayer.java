package site.dodoneko.peoplemobsmod2.client.layers;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_BipedModel;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_RendererModel;
import site.dodoneko.peoplemobsmod2.util.PMM2_Math;

@OnlyIn(Dist.CLIENT)
public class PMM2_BlockOnHeadLayer<T extends MobEntity, M extends PMM2_BipedModel<T>> extends LayerRenderer<T, M> {
    public PMM2_BlockOnHeadLayer(IEntityRenderer<T, M> renderer, boolean texturesCombined) {
        super(renderer);
        this.texturesCombined = texturesCombined;
    }

    private boolean texturesCombined = true;

    private BlockState getBlockState(T entityIn) {
        if (entityIn instanceof MooshroomEntity) {
            return ((MooshroomEntity) entityIn).getMooshroomType().getRenderState();
        }
        return null;
    }

    @Override
    public void render(T entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_,
        float p_212842_6_, float p_212842_7_, float p_212842_8_) {
        GlStateManager.enableRescaleNormal();
        GlStateManager.pushMatrix();

        M model = this.getEntityModel();
        PMM2_RendererModel head = model.bipedHead;
        PMM2_RendererModel ahoge = model.bipedAhoge;

        boolean isChild = entityIn.isChild();
        float pixelToScale = (model.scaleFactor * model.modelScale);

        BlockState blockstate = this.getBlockState(entityIn);

        GlStateManager.translatef(0.0F, 6F * pixelToScale, 0.0F);

        if (isChild) {
            GlStateManager.translatef(0.0F, 16.0F * pixelToScale, 0.0F);
        }

        GlStateManager.translatef((head.rotationPointX)*pixelToScale,(head.rotationPointY)*pixelToScale,(head.rotationPointZ)*pixelToScale);

        GlStateManager.rotatef( (head.rotateAngleY)*PMM2_Math.Rad2deg, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef( (head.rotateAngleX)*PMM2_Math.Rad2deg, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotatef( (head.rotateAngleZ)*PMM2_Math.Rad2deg, 0.0F, 0.0F, 1.0F);

        GlStateManager.translatef(0*pixelToScale, -8F*pixelToScale, 0*pixelToScale);
        
        GlStateManager.rotatef( (ahoge.rotateAngleX+0.0F)*PMM2_Math.Rad2deg, 1.0F, 0.0F, 0.0F);

        float f = 0.5F;
        GlStateManager.scalef(-f, -f, f);

        int i = entityIn.getBrightnessForRender();
        int j = i % 65536;
        int k = i / 65536;

        GlStateManager.translatef(-0.5F, 0f, 0.5f);

        GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) j, (float) k);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
        Minecraft.getInstance().getBlockRendererDispatcher().renderBlockBrightness(blockstate, 1.0F);

        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
    }

    @Override
    public boolean shouldCombineTextures() {
        return texturesCombined;
    }
    
}
