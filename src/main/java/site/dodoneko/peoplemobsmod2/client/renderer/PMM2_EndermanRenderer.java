package site.dodoneko.peoplemobsmod2.client.renderer;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.util.ResourceLocation;
import site.dodoneko.peoplemobsmod2.client.layers.PMM2_EndermanEyesLayer;
import site.dodoneko.peoplemobsmod2.client.layers.PMM2_HeldBlockLayer;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_EndermanModel;

// @SuppressWarnings("unused")
public class PMM2_EndermanRenderer<T extends EndermanEntity> extends PMM2_BipedRenderer<T, PMM2_EndermanModel<T>>
      implements IEntityRenderer<T, PMM2_EndermanModel<T>> {
   private static final ResourceLocation ENTITY_TEXTURES = new ResourceLocation(
         "textures/entity/enderman/enderman-chan.png");
   private final Random rnd = new Random();

   // @SuppressWarnings({ "rawtypes", "unchecked" })
   public PMM2_EndermanRenderer(EntityRendererManager renderManager) {
      super(renderManager, new PMM2_EndermanModel<T>(0.0f), 0.5F, false);
      this.addLayer(new PMM2_EndermanEyesLayer<>(this));
      this.addLayer(new PMM2_HeldBlockLayer(this));
   }

   public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
      BlockState blockstate = entity.getHeldBlockState();
      PMM2_EndermanModel<T> endermanmodel = this.getEntityModel();
      endermanmodel.isCarrying = blockstate != null;
      endermanmodel.isAttacking = entity.isScreaming();
      if (entity.isScreaming()) {
         // double d0 = 0.02D;
         x += this.rnd.nextGaussian() * 0.02D;
         z += this.rnd.nextGaussian() * 0.02D;
      }

      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(T entity) {
      return ENTITY_TEXTURES;
   }
}
