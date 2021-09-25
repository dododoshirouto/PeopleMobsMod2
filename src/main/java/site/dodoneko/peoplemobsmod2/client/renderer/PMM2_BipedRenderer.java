package site.dodoneko.peoplemobsmod2.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.client.layers.PMM2_BipedArmorLayer;
import site.dodoneko.peoplemobsmod2.client.model.PMM2_BipedModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_BipedRenderer<T extends MobEntity, M extends PMM2_BipedModel<T>> extends MobRenderer<T, M> {

   private static final ResourceLocation ENTITY_TEXTURES = new ResourceLocation("textures/entity/sample-chan.png");

   public PMM2_BipedRenderer(EntityRendererManager renderManagerIn, M modelBipedIn, float shadowSize, boolean setLayers) {
      super(renderManagerIn, modelBipedIn, shadowSize);
      if (setLayers) {
          super.addLayer(new HeadLayer<T,M>(this));
          super.addLayer(new ElytraLayer<T,M>(this));
          super.addLayer(new HeldItemLayer<T,M>(this));
      }
   }

   public PMM2_BipedRenderer(EntityRendererManager renderManagerIn, M modelBipedIn, M modelLeggings, M modelArmor, float shadowSize) {
      this(renderManagerIn, modelBipedIn, shadowSize, true);
      this.addLayer(new PMM2_BipedArmorLayer<T,M,M>(this, modelLeggings, modelArmor) );
   }

   @Override
   public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks)
   {
       M model = (M)this.getEntityModel();
       model.entityId = entity.hashCode();
       model.entityMotion = new Vec3d( entity.posX-entity.prevPosX, entity.posY-entity.prevPosY, entity.posZ-entity.prevPosZ );
       model.entityOnGround = entity.onGround;
       model.entityIsInWater = entity.isInWater();
       model.entityHurtTime = entity.hurtTime;

       super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(T entity) {
      return ENTITY_TEXTURES;
   }
}
