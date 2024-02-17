package site.dodoneko.peoplemobsmod2.client.layers;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_SheepModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_SheepWoolLayer<T extends SheepEntity> extends LayerRenderer<T, PMM2_SheepModel<T>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/sheep/sheep-chan_fur.png");
    private final PMM2_SheepModel<T> woolModel = new PMM2_SheepModel<T>();

    public PMM2_SheepWoolLayer(IEntityRenderer<T, PMM2_SheepModel<T>> p_i50925_1_) {
        super(p_i50925_1_);
        woolModel.isFar = true;
        woolModel.bodyModel = p_i50925_1_.getEntityModel();
        woolModel.doWalkBounding = false;
        woolModel.easeMotion = false;
    }

    public void render(T entityIn, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks,
            float netHeadYaw, float headPitch, float scale) {
        if (!entityIn.getSheared() && !entityIn.isInvisible()) {
            this.bindTexture(TEXTURE);
            if (entityIn.hasCustomName() && "jeb_".equals(entityIn.getName().getUnformattedComponentText())) {
                int i1 = 25;
                int i = entityIn.ticksExisted / i1 + entityIn.getEntityId();
                int j = DyeColor.values().length;
                int k = i % j;
                int l = (i + 1) % j;
                float f = ((float) (entityIn.ticksExisted % i1) + partialTick) / (float)i1;
                float[] afloat1 = T.getDyeRgb(DyeColor.byId(k));
                float[] afloat2 = T.getDyeRgb(DyeColor.byId(l));
                GlStateManager.color3f(afloat1[0] * (1.0F - f) + afloat2[0] * f,
                        afloat1[1] * (1.0F - f) + afloat2[1] * f, afloat1[2] * (1.0F - f) + afloat2[2] * f);
            } else {
                float[] afloat = T.getDyeRgb(entityIn.getFleeceColor());
                GlStateManager.color3f(afloat[0], afloat[1], afloat[2]);
            }

            this.woolModel.entityIn = entityIn;
            this.getEntityModel().setModelAttributes(this.woolModel);
            // this.sheepModel.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
            this.woolModel.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    public boolean shouldCombineTextures() {
        return true;
    }
}
