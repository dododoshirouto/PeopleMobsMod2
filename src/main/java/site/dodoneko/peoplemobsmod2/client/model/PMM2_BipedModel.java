/**
 *
 */
package site.dodoneko.peoplemobsmod2.client.model;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import site.dodoneko.peoplemobsmod2.util.PMM2_Math;

import com.mojang.blaze3d.platform.GlStateManager;

/**
 * @author DODONEKO
 * @param <T>
 * @net.minecraft.client.renderer.entity.model
 */
public class PMM2_BipedModel<T extends Entity> extends EntityModel<T> implements IHasArm, IHasHead
{

    public PMM2_RendererModel bipedHead;
    public PMM2_RendererModel bipedBody;
    public PMM2_RendererModel bipedRightArm;
    public PMM2_RendererModel bipedLeftArm;
    public PMM2_RendererModel bipedRightLeg;
    public PMM2_RendererModel bipedLeftLeg;

    public PMM2_RendererModel bipedLeftArmwear;
    public PMM2_RendererModel bipedRightArmwear;
    public PMM2_RendererModel bipedLeftLegwear;
    public PMM2_RendererModel bipedRightLegwear;
    public PMM2_RendererModel bipedBodywear;
    public PMM2_RendererModel bipedHeadwear;

    public PMM2_RendererModel bipedUpperBoob;
    public PMM2_RendererModel bipedLowerBoob;
    public PMM2_RendererModel bipedUpperBoobwear;
    public PMM2_RendererModel bipedLowerBoobwear;
    public PMM2_RendererModel bipedRightEyelid;
    public PMM2_RendererModel bipedLeftEyelid;
    public PMM2_RendererModel bipedAhoge;
    public PMM2_RendererModel bipedKemomimi;
    public PMM2_RendererModel bipedShippo;

    public PMM2_RendererModel bipedArmorLeftArm;
    public PMM2_RendererModel bipedArmorRightLeg;
    public PMM2_RendererModel bipedArmorLeftLeg;
    public PMM2_RendererModel bipedArmorRightArm;

    public PMM2_RendererModel bipedLeftHand;
    public PMM2_RendererModel bipedRightHand;

    public boolean ahogeSwing = true;
    public boolean kemomimiSwing = true;
    public boolean shippoSwing = true;
    public boolean boobsSwing = true;
    public boolean doTwinkles = true;
    public boolean doWalkBounding = true;
    public float modelScale = 1.0F;
    public float walkSpeed = 1.0F;
    /** 0.0...1.0 */
    public float boobHeight = 0.5F;
    public boolean childBoob = false;
    public boolean childHeadwear = false;

    protected Map<Integer, Float> twinklesTimes = new HashMap<Integer, Float>();
    protected Map<Integer, Boolean> twinkledNow = new HashMap<Integer, Boolean>();

    public float limbSwing;
    public float limbSwingAmount;
    public float ageInTicks;
    public float netHeadYaw;
    public float headPitch;
    public float scaleFactor;
    public T entityIn;

    public boolean isSneak;
    public float swimAnimation;
    public PMM2_BipedModel.ArmPose leftArmPose = PMM2_BipedModel.ArmPose.EMPTY;
    public PMM2_BipedModel.ArmPose rightArmPose = PMM2_BipedModel.ArmPose.EMPTY;

    public int entityId;
    public Vec3d entityMotion;
    public boolean entityOnGround;
    public boolean entityIsInWater;
    public int entityHurtTime;
    public boolean isAggressive;

    public PMM2_BipedModel()
    {
        this(0.0F);
    }

    public PMM2_BipedModel(float modelSize)
    {
        this(modelSize, 0.0F, 64, 64);
    }

    @SuppressWarnings ("unchecked")
    public PMM2_BipedModel(float modelSize, float addScale, int textureWidthIn, int textureHeightIn)
    {
        this.textureWidth = textureWidthIn;
        this.textureHeight = textureHeightIn;

        this.walkSpeed = 1.1F / this.modelScale;

        this.bipedHead = new PMM2_RendererModel((EntityModel<Entity>)this, 0, 0);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, modelSize);
        this.bipedHead.setRotationPoint(0.0F, 0.0F + addScale, 0.0F);
        this.bipedHeadwear = new PMM2_RendererModel((EntityModel<Entity>)this, 32, 0);
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, modelSize + 0.5F);
        this.bipedBody = new PMM2_RendererModel((EntityModel<Entity>)this, 16, 16);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, modelSize);
        this.bipedBody.setRotationPoint(0.0F, 0.0F + addScale, 0.0F);
        this.bipedBodywear = new PMM2_RendererModel((EntityModel<Entity>)this, 16, 32);
        this.bipedBodywear.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, modelSize + 0.25F);

        this.bipedLeftArm = new PMM2_RendererModel((EntityModel<Entity>)this, 32, 48);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, modelSize);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.5F + addScale, 0.0F);
        this.bipedRightArm = new PMM2_RendererModel((EntityModel<Entity>)this, 40, 16);
        this.bipedRightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, modelSize);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.5F + addScale, 0.0F);
        this.bipedLeftArmwear = new PMM2_RendererModel((EntityModel<Entity>)this, 48, 48);
        this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, modelSize + 0.25F);
        this.bipedRightArmwear = new PMM2_RendererModel((EntityModel<Entity>)this, 40, 32);
        this.bipedRightArmwear.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, modelSize + 0.25F);

        this.bipedLeftHand = new PMM2_RendererModel((EntityModel<Entity>)this, 0, 0);
        this.bipedLeftHand.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, modelSize);
        this.bipedLeftHand.setRotationPoint(5.0F, 10.5F + addScale, 1.0F);
        this.bipedRightHand = new PMM2_RendererModel((EntityModel<Entity>)this, 0, 0);
        this.bipedRightHand.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, modelSize);
        this.bipedRightHand.setRotationPoint(-5.0F, 10.5F + addScale, 1.0F);

        this.bipedArmorLeftArm = new PMM2_RendererModel((EntityModel<Entity>)this, 40, 16);
        this.bipedArmorLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, modelSize);
        this.bipedArmorLeftArm.mirror = true;
        this.bipedArmorRightArm = new PMM2_RendererModel((EntityModel<Entity>)this, 40, 16);
        this.bipedArmorRightArm.addBox(-2.0F, -2.0F, -2.0F, 4, 12, 4, modelSize);

        this.bipedLeftLeg = new PMM2_RendererModel((EntityModel<Entity>)this, 16, 48);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, modelSize);
        this.bipedLeftLeg.setRotationPoint(2.2F, 12.0F + addScale, 0.0F);
        this.bipedRightLeg = new PMM2_RendererModel((EntityModel<Entity>)this, 0, 16);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, modelSize);
        this.bipedRightLeg.setRotationPoint(-2.2F, 12.0F + addScale, 0.0F);
        this.bipedRightLegwear = new PMM2_RendererModel((EntityModel<Entity>)this, 0, 32);
        this.bipedRightLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, modelSize + 0.25F);
        this.bipedLeftLegwear = new PMM2_RendererModel((EntityModel<Entity>)this, 0, 48);
        this.bipedLeftLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, modelSize + 0.25F);

        this.bipedArmorLeftLeg = new PMM2_RendererModel((EntityModel<Entity>)this, 0, 16);
        this.bipedArmorLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, modelSize);
        this.bipedArmorLeftLeg.mirror = true;
        this.bipedArmorRightLeg = new PMM2_RendererModel((EntityModel<Entity>)this, 0, 16);
        this.bipedArmorRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, modelSize);


        this.bipedUpperBoob = new PMM2_RendererModel((EntityModel<Entity>)this, 16, 21);
        this.bipedUpperBoob.addBox(-4.0F, 0.0F, -4.0F, 8, 4, 4, modelSize - 0.01F);
        this.bipedUpperBoob.setRotationPoint(0.0F, 0.0F, -2.0F);
        this.bipedLowerBoob = new PMM2_RendererModel((EntityModel<Entity>)this, 16, 25);
        this.bipedLowerBoob.addBox(-4.0F, 0.0F, -4.0F, 8, 3, 4, modelSize - 0.02F);
        this.bipedLowerBoob.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.bipedUpperBoobwear = new PMM2_RendererModel((EntityModel<Entity>)this, 16, 36);
        this.bipedUpperBoobwear.addBox(-4.0F, 0.125F, -4.125F, 8, 4, 4, modelSize + 0.24F);
        this.bipedLowerBoobwear = new PMM2_RendererModel((EntityModel<Entity>)this, 16, 40);
        this.bipedLowerBoobwear.addBox(-4.0F, 0.125F, -4.125F, 8, 3, 4, modelSize + 0.23F);
        this.bipedShippo = new PMM2_RendererModel((EntityModel<Entity>)this, 54, 16);
        this.bipedShippo.addQuad(-5.0F, 0.0F, 0.0F, 10, 12, modelSize, true);
        this.bipedShippo.setRotationPoint(0.0F, 10.0F + addScale, 2F);

        this.bipedLeftEyelid = new PMM2_RendererModel((EntityModel<Entity>)this, 12, 16);
        this.bipedLeftEyelid.addBox(-3.0F, -4.0F, 2.1F, 2, 2, 2, modelSize);
        this.bipedLeftEyelid.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedLeftEyelid.setRotateAngle(0.0F, PMM2_Math.PI, 0.0F);
        this.bipedRightEyelid = new PMM2_RendererModel((EntityModel<Entity>)this, 12, 16);
        this.bipedRightEyelid.addBox(-3.0F, -4.0F, -4.1F, 2, 2, 2, modelSize);
        this.bipedRightEyelid.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.bipedAhoge = new PMM2_RendererModel((EntityModel<Entity>)this, 24, 0);
        this.bipedAhoge.addBox(-3.5F, -3.0F, 0.0F, 7, 3, 1, modelSize);
        this.bipedAhoge.setRotationPoint(0.0F, -8.0F, 1.0F);
        this.bipedKemomimi = new PMM2_RendererModel((EntityModel<Entity>)this, 24, 4);
        this.bipedKemomimi.addBox(-3.5F, -3.0F, -1.0F, 7, 3, 1, modelSize);
        this.bipedKemomimi.setRotationPoint(0.0F, -8.0F, -1.0F);


        this.bipedBody.addChild(this.bipedBodywear);
        this.bipedBody.addChild(this.bipedRightArm);
        this.bipedBody.addChild(this.bipedLeftArm);
        this.bipedBody.addChild(this.bipedRightLeg);
        this.bipedBody.addChild(this.bipedLeftLeg);
        this.bipedBody.addChild(this.bipedUpperBoob);
        this.bipedBody.addChild(this.bipedShippo);
        this.bipedUpperBoob.addChild(this.bipedLowerBoob);

        this.bipedHead.addChild(this.bipedHeadwear);
        this.bipedHead.addChild(this.bipedAhoge);
        this.bipedHead.addChild(this.bipedKemomimi);
        this.bipedHead.addChild(this.bipedLeftEyelid);
        this.bipedHead.addChild(this.bipedRightEyelid);
        this.bipedLeftArm.addChild (this.bipedLeftArmwear);
        this.bipedRightArm.addChild(this.bipedRightArmwear);
        this.bipedLeftLeg.addChild (this.bipedLeftLegwear);
        this.bipedRightLeg.addChild(this.bipedRightLegwear);
        this.bipedUpperBoob.addChild(this.bipedUpperBoobwear);
        this.bipedLowerBoob.addChild(this.bipedLowerBoobwear);
        this.bipedLeftArm.addChild (this.bipedArmorLeftArm);
        this.bipedRightArm.addChild(this.bipedArmorRightArm);
        this.bipedLeftArm.addChild (this.bipedLeftHand);
        this.bipedRightArm.addChild(this.bipedRightHand);
        this.bipedLeftLeg.addChild (this.bipedArmorLeftLeg);
        this.bipedRightLeg.addChild(this.bipedArmorRightLeg);

        this.bipedArmorLeftArm.showModel = false;
        this.bipedArmorRightArm.showModel = false;
        this.bipedArmorLeftLeg.showModel = false;
        this.bipedArmorRightLeg.showModel = false;
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.entityIn = entityIn;
        this.isAggressive = ((MobEntity) entityIn).isAggressive();
        this.limbSwing = limbSwing;
        this.limbSwingAmount = limbSwingAmount;
        this.ageInTicks = (float)Util.milliTime()/100 + this.entityId*10;
        this.netHeadYaw = netHeadYaw;
        this.headPitch = headPitch;
        this.scaleFactor = scale;
        this.rightArmPose = ArmPose.EMPTY;
        this.leftArmPose = ArmPose.EMPTY;
        ItemStack itemstack = ((LivingEntity) entityIn).getHeldItem(Hand.MAIN_HAND);
        ItemStack itemstackOff = ((LivingEntity) entityIn).getHeldItem(Hand.OFF_HAND);

        if (((LivingEntity) entityIn).getPrimaryHand() == HandSide.RIGHT) {
            if (itemstack.getItem() instanceof net.minecraft.item.BowItem) {
                this.rightArmPose = ArmPose.BOW_AND_ARROW;
            } else
            if ((itemstack.getItem() instanceof net.minecraft.item.BlockItem)) {
                this.rightArmPose = ArmPose.BLOCK;
            } else
            if (!(itemstack.getItem() instanceof net.minecraft.item.AirItem)) {
                this.rightArmPose = ArmPose.ITEM;
            }
            if (itemstackOff.getItem() instanceof net.minecraft.item.BowItem) {
                this.leftArmPose = ArmPose.BOW_AND_ARROW;
            } else
            if ((itemstackOff.getItem() instanceof net.minecraft.item.BlockItem)) {
                this.leftArmPose = ArmPose.BLOCK;
            } else
            if (!(itemstackOff.getItem() instanceof net.minecraft.item.AirItem)) {
                this.leftArmPose = ArmPose.ITEM;
            }
        } else {
            if (itemstack.getItem() instanceof net.minecraft.item.BowItem) {
                this.leftArmPose = ArmPose.BOW_AND_ARROW;
            } else
            if ((itemstack.getItem() instanceof net.minecraft.item.BlockItem)) {
                this.leftArmPose = ArmPose.BLOCK;
            } else
            if (!(itemstack.getItem() instanceof net.minecraft.item.AirItem)) {
                this.leftArmPose = ArmPose.ITEM;
            }
            if (itemstackOff.getItem() instanceof net.minecraft.item.BowItem) {
                this.rightArmPose = ArmPose.BOW_AND_ARROW;
            } else
            if ((itemstackOff.getItem() instanceof net.minecraft.item.BlockItem)) {
                this.rightArmPose = ArmPose.BLOCK;
            } else
            if (!(itemstackOff.getItem() instanceof net.minecraft.item.AirItem)) {
                this.rightArmPose = ArmPose.ITEM;
            }
        }



        limbSwing *= this.walkSpeed + (this.isChild? 0.2F: 0.0F);
        if (this.doWalkBounding)
        {
            GlStateManager.translatef(0.0F, MathHelper.abs(MathHelper.cos(limbSwing * 1.3314F)) * limbSwingAmount * this.modelScale * 0.0625F * 4 - 0.0625F * 2 * limbSwingAmount * (this.isChild? 0.5F: 1F), 0.0F);
        }
//        scale *= this.modelScale;
        this.setRotationAngles();
        GlStateManager.pushMatrix();

        if (this.isChild)
        {
            this.bipedHeadwear.isHidden = !this.childHeadwear;
            this.bipedLowerBoob.isHidden = !this.childBoob;
            this.bipedUpperBoob.isHidden = !this.childBoob;
            this.bipedLowerBoobwear.isHidden = !this.childBoob;
            this.bipedUpperBoobwear.isHidden = !this.childBoob;

            GlStateManager.translatef(0.0F, 1.5F * (1F-this.modelScale) +3F/16F, 0.0F);
            GlStateManager.scalef(0.75F * this.modelScale, 0.75F * this.modelScale, 0.75F * this.modelScale);
            GlStateManager.translatef(0.0F, 16.0F * this.scaleFactor * this.modelScale, 0.0F);
            this.bipedHead.render(this.scaleFactor, this.entityIn.hashCode());
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.0F, 1.5F * (1F-this.modelScale) +3F/16F, 0.0F);
            GlStateManager.scalef(0.5F * this.modelScale, 0.5F * this.modelScale, 0.5F * this.modelScale);
            GlStateManager.translatef(0.0F, 24.0F * this.scaleFactor * this.modelScale, 0.0F);
            this.bipedBody.render(this.scaleFactor, this.entityIn.hashCode());
        }
        else
        {
            GlStateManager.translatef(0.0F, -1.5F * this.modelScale + 1.5F, 0.0F);
            GlStateManager.scalef(this.modelScale, this.modelScale, this.modelScale);

            this.bipedHeadwear.isHidden = false;
            if (boobHeight > 0.000012)
            {
                if (boobHeight < 0.999996F)
                {

                    this.bipedLowerBoob.isHidden = false;
                    this.bipedUpperBoob.isHidden = false;
                    this.bipedLowerBoobwear.isHidden = false;
                    this.bipedUpperBoobwear.isHidden = false;
                }
                else {
                    this.bipedLowerBoob.isHidden = true;
                    this.bipedUpperBoob.isHidden = false;
                    this.bipedLowerBoobwear.isHidden = true;
                    this.bipedUpperBoobwear.isHidden = false;
                }
            }
            else {
                this.bipedLowerBoob.isHidden = true;
                this.bipedUpperBoob.isHidden = true;
                this.bipedLowerBoobwear.isHidden = true;
                this.bipedUpperBoobwear.isHidden = true;
            }

            this.bipedHead.render(this.scaleFactor, this.entityId);
            this.bipedBody.render(this.scaleFactor, this.entityId);
        }
        GlStateManager.popMatrix();
    }

    public void setRotationAngles()
    {
        for (RendererModel renderer : boxList) {
            if (renderer.getClass() == PMM2_RendererModel.class)
            ((PMM2_RendererModel)renderer).reset();
        }

        if ( setPreAnimations() ) {
            return;
        }

        // 頭の回転
        this.setHeadYawAndPitch();

        // 何もしてないときのモーション
        this.setStayAnimations();

        // 歩いてるときのモーション
        if (this.limbSwingAmount > 0.01F)
        {
            this.setWalkingAnimations();
        }

        // 乗ってる時のモーション
        if (this.isSitting)
        {
            this.setRidingAnimations();
        }

        // 手に何か持ってるとき
        this.setHasAnythingAnimations(this.leftArmPose, this.rightArmPose);

        // 叩く時のモーション
        if (this.swingProgress > 0.0F)
        {
            this.setSwingProgressAnimations();
        }

        // スニーク時のモーション
        if ( ((LivingEntity) this.entityIn).getPose()==Pose.SNEAKING )
        {
            this.setSneakAnimations();
        }

        // Not work.
        // 繁殖可能時のモーション
        if (this.entityIn instanceof AnimalEntity) {
            if (((AnimalEntity) this.entityIn).isInLove()) {
                setLoveAnimations();
            }
        }

        // プレイヤーに迫ってくるときのモーション
        if (this.isAggressive) {
            setAggressiveAnimations();
        }

        // ダメージ時のモーション
        if (this.entityHurtTime > 1)
        {
            this.setDamagedAnimations();
        }

        // 空中にいるの時のモーション
        else if (!this.entityOnGround)
        {
            // 水中にいるとき
            if (this.entityIsInWater)
            {
                this.setSwimmingAnimations();
            } else
            {
                this.setJumpAnimations();
            }
        }

        this.setAddAnimations();

        // 死亡時のモーション
        if ( ((LivingEntity) this.entityIn).getPose()==Pose.DYING ) {
            setDeadAnimations();
        }

        this.setBoobsAnimations();
        this.setShippoAnimations();
        this.setAhogeAnimations();
        this.setTwinkleAnimations();

        this.setPostAnimations();


        // 足の角度による接続部の整合性
        float gapLY = 0.0F, gapRY = 0.0F;
        if (this.bipedLeftLeg.rotateAngleX < 0.0F)
        {
            this.bipedLeftLeg.rotationPointY += gapLY = (float)Math.sin(this.bipedLeftLeg.rotateAngleX) * 2.0F;
            this.bipedLeftLeg.rotationPointZ += (1F - (float)Math.cos(this.bipedLeftLeg.rotateAngleX)) * 2.0F;
        }
        else
        {
            // 0.523599rad '=, 30deg
            this.bipedLeftLeg.rotationPointY += gapLY = (float)Math.sin(-Math.min(this.bipedLeftLeg.rotateAngleX, 0.523599D)) * 2.0F;
            this.bipedLeftLeg.rotationPointZ -= (1F - (float)Math.cos(-Math.min(this.bipedLeftLeg.rotateAngleX, 0.523599D))) * 2.0F;
        }
        if (this.bipedRightLeg.rotateAngleX < 0.0F)
        {
            this.bipedRightLeg.rotationPointY += gapRY = (float)Math.sin(this.bipedRightLeg.rotateAngleX) * 2.0F;
            this.bipedRightLeg.rotationPointZ += (1F - (float)Math.cos(this.bipedRightLeg.rotateAngleX)) * 2.0F;
        }
        else
        {
            this.bipedRightLeg.rotationPointY += gapRY = (float)Math.sin(-Math.min(this.bipedRightLeg.rotateAngleX, 0.523599D)) * 2.0F;
            this.bipedRightLeg.rotationPointZ -= (1F - (float)Math.cos(-Math.min(this.bipedRightLeg.rotateAngleX, 0.523599D))) * 2.0F;
        }
        float y = Math.min(gapLY, gapRY);
        this.bipedBody.rotationPointY -= y;
        this.bipedHead.rotationPointY -= y;
    }




    /** 頭の回転 */
    protected void setHeadYawAndPitch()
    {
        this.bipedHead.rotateAngleY = this.netHeadYaw * PMM2_Math.Deg2Rad;
        this.bipedHead.rotateAngleX = this.headPitch * PMM2_Math.Deg2Rad;
    }

    /** しっぽのアニメーション */
    protected void setShippoAnimations()
    {
        if (this.shippoSwing) {
            this.bipedShippo.rotateAngleX = 0.5F + 0.4F * (float)this.entityIn.getMotion().length()*10F;
            if (this.doWalkBounding) this.bipedShippo.rotateAngleX += MathHelper.abs(MathHelper.cos(limbSwing * 1.3314F - 1.3F)) * this.limbSwingAmount * 0.8F;
        }
    }

    /** あほげとけもみみのアニメーション */
    protected void setAhogeAnimations()
    {
        float f1 = -0.6F * (float)this.entityIn.getMotion().length()*10F;
        if (this.doWalkBounding) f1 += MathHelper.abs(MathHelper.cos(limbSwing * 1.3314F - 1.3F)) * this.limbSwingAmount;
        if (this.ahogeSwing) this.bipedAhoge.rotateAngleX = f1;
        if (this.kemomimiSwing) this.bipedKemomimi.rotateAngleX = f1 * 0.65F;
    }

    /** おっぱい部分のアニメーション */
    protected void setBoobsAnimations()
    {
    	
        if (this.bipedUpperBoob.initRotateAngleX == 0.0F)
        {
            this.bipedUpperBoob.initRotationPointY = this.bipedUpperBoob.rotationPointY = this.boobHeight;
            this.bipedUpperBoob.initRotateAngleX = this.bipedUpperBoob.rotateAngleX = -(float)Math.asin((double)this.boobHeight * 0.707106781F) + 1.570796326F;
            float f = this.boobHeight;
            if (this.boobHeight > 0.5F) f = 1.0F - f;
            this.bipedLowerBoob.initRotateAngleX = this.bipedLowerBoob.rotateAngleX = -(this.bipedUpperBoob.rotateAngleX - PMM2_Math.PI_Half) * (2 + f);
        }
        
        if (this.boobsSwing) {
	        this.bipedUpperBoob.rotationPointY +=
	                Math.max(
	                    Math.min(
	                        (float)-(this.bipedBody.rotationPointY + this.entityIn.getMotion().y) * this.boobHeight * 1.5F,
	                        1.0F
	                    ),
	                    -0.8F
	                );
	        this.bipedUpperBoob.rotateAngleX +=
	                (float)Math.max(
	                    Math.min(
	                        (float)-(this.bipedBody.rotationPointY + this.entityIn.getMotion().y) * this.boobHeight * 0.4F * Math.PI,
	                        0.25F * Math.PI
	                    ),
	                    0.05F * Math.PI
	                );

	        float f = this.boobHeight;
	        if (this.boobHeight > 0.5F) f = 1.0F - f;
	        this.bipedLowerBoob.rotateAngleX = -(this.bipedUpperBoob.rotateAngleX - PMM2_Math.PI_Half) * (2 + f);
        }

    }

    /** まばたき */
    protected void setTwinkleAnimations()
    {
        if (!this.twinklesTimes.containsKey(entityId))
        {
            if (this.twinklesTimes.size() > 300) { this.twinklesTimes.clear(); }
            this.twinklesTimes.put(entityId, (float)Math.random()*8F);
        }
        if (!this.twinkledNow.containsKey(entityId))
        {
            if (this.twinkledNow.size() > 300) { this.twinkledNow.clear(); }
            this.twinkledNow.put(entityId, false);
        }
        float twTime = this.twinklesTimes.get(entityId);
        twTime -= 0.05F;
        float hash = (Util.milliTime()/10) %1;
        if (this.twinkledNow.get(this.entityId))
        {
            if (twTime < 0.0F)
            {
                this.twinkledNow.put(this.entityId, false);
                twTime = (float)Math.random() * 8 + hash;
            }
        }
        else
        {
            if (twTime < 0.0F)
            {
                this.twinkledNow.put(this.entityId, true);
                twTime = (float)Math.random() + hash;
            }
        }

        this.twinklesTimes.put(entityId, twTime);
        if (this.twinkledNow.get(this.entityId)) {
            this.bipedLeftEyelid.rotationPointZ = this.bipedRightEyelid.rotationPointZ = 0F;
            this.bipedLeftEyelid.rotationPointY = this.bipedRightEyelid.rotationPointY = 0F;
        } else {
            this.bipedLeftEyelid.rotationPointZ = this.bipedRightEyelid.rotationPointZ = 0.2F;
            this.bipedLeftEyelid.rotationPointY = this.bipedRightEyelid.rotationPointY = -2F;
        }

    }

    /** 何もしてないときのモーション */
    protected void setStayAnimations()
    {
        this.bipedRightArm.rotateAngleZ = MathHelper.cos(this.ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ  = -MathHelper.cos(this.ageInTicks * 0.09F) * 0.05F - 0.05F;
        this.bipedRightArm.rotateAngleX = MathHelper.sin(this.ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX  = -MathHelper.sin(this.ageInTicks * 0.067F) * 0.05F;
    }

    /** 水中にいるときのモーション */
    protected void setSwimmingAnimations()
    {
        float f1 = (float)this.entityIn.getMotion().length();
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(this.ageInTicks * 0.6F) * -0.5F + 0.2F + 0.4F*(1-f1);
        this.bipedLeftLeg .rotateAngleX = MathHelper.cos(this.ageInTicks * 0.6F + PMM2_Math.PI) * -0.5F + 0.2F + 0.4F*(1-f1);
        this.bipedRightLeg.rotateAngleZ =  0.05F;
        this.bipedLeftLeg .rotateAngleZ = -0.05F;

        this.bipedRightArm.rotateAngleZ = MathHelper.cos(this.ageInTicks * 0.45F) * (0.3F + 0.8F*f1) + 1 + 0.3F*f1;
        this.bipedLeftArm .rotateAngleZ = MathHelper.cos(this.ageInTicks * 0.45F + PMM2_Math.PI) * (0.3F + 0.8F*f1) - 1 - 0.3F*f1;
        this.bipedRightArm.rotateAngleX = (MathHelper.cos(this.ageInTicks * 0.45F) * -0.8F - 1) * (1-f1);
        this.bipedLeftArm.rotateAngleX = (MathHelper.cos(this.ageInTicks * 0.45F) * -0.8F - 1) * (1-f1);

        this.bipedBody.rotateAngleX = 0.2F + 1.1F*f1;
        this.bipedHead.rotationPointZ = this.bipedBody.rotationPointZ = -6F*(float)this.entityIn.getMotion().length();
    }

    // Not Work.
    /** 繁殖状態時のモーション */
    protected void setLoveAnimations() {
        this.bipedRightArm.rotateAngleZ = -15f * PMM2_Math.Deg2Rad;
        this.bipedLeftArm.rotateAngleZ  =  15f * PMM2_Math.Deg2Rad;
        this.bipedRightArm.rotateAngleX = -20f * PMM2_Math.Deg2Rad;
        this.bipedLeftArm.rotateAngleX  = -20f * PMM2_Math.Deg2Rad;
        this.bipedRightArm.rotationPointZ = -1.5f;
        this.bipedLeftArm.rotationPointZ  = -1.5f;

        this.bipedRightLeg.rotateAngleZ = -2f * PMM2_Math.Deg2Rad;
        this.bipedLeftLeg.rotateAngleZ  =  2f * PMM2_Math.Deg2Rad;
        this.bipedRightLeg.rotateAngleX = -0.2F * PMM2_Math.PI;
        this.bipedLeftLeg.rotateAngleX  = -0.2F * PMM2_Math.PI;

        this.bipedBody.rotateAngleX  = 0.07F * PMM2_Math.PI;

        float f1 = this.ageInTicks/2;
        this.bipedBody.rotateAngleZ  = (MathHelper.sin(f1) * 0.157F);
        this.bipedHead.rotateAngleZ  = (MathHelper.sin(f1) * 0.157F);
        this.bipedBody.rotationPointX  -= (float)( Math.sin(Math.sin(f1)*0.157F) - Math.sin(Math.sin(f1)*0.25F) ) *8F;
        this.bipedHead.rotationPointX  -= (float)( Math.sin(Math.sin(f1)*0.157F) - Math.sin(Math.sin(f1)*0.25F) ) *8F;
        this.bipedRightLeg.rotateAngleZ -= (MathHelper.sin(f1) * 0.25F);
        this.bipedLeftLeg.rotateAngleZ  -= (MathHelper.sin(f1) * 0.25F);

        this.bipedLeftEyelid.rotationPointZ = this.bipedRightEyelid.rotationPointZ = 0F;
        this.bipedLeftEyelid.rotationPointY = this.bipedRightEyelid.rotationPointY = 0F;
    }

    /** onGroundじゃない時のモーション */
    protected void setJumpAnimations()
    {
        float fallingSpeed = MathHelper.clamp((float)this.entityMotion.y, -1.0F, 1.0F);
        this.bipedLeftArm.rotateAngleZ  = -(80F - 30F*fallingSpeed) * PMM2_Math.Deg2Rad;
        this.bipedRightArm.rotateAngleZ =  (80F - 30F*fallingSpeed) * PMM2_Math.Deg2Rad;
        this.bipedLeftLeg.rotateAngleY = -fallingSpeed / 1.5F;
        this.bipedLeftLeg.rotateAngleY =  fallingSpeed / 1.5F;
        this.bipedLeftLeg.rotateAngleX  = fallingSpeed * 1.3F;
        this.bipedRightLeg.rotateAngleX = fallingSpeed * 1.3F;
    }

    /** ダメージ受けた時のアニメーション  */
    protected void setDamagedAnimations()
    {
        this.setWalkingAnimations();
        this.bipedHead.rotateAngleX = 15F * PMM2_Math.Deg2Rad;
        this.bipedLeftLeg .rotateAngleX -= 20F * PMM2_Math.Deg2Rad;
        this.bipedRightLeg.rotateAngleX -= 20F * PMM2_Math.Deg2Rad;
        this.bipedLeftArm .rotateAngleZ -= 80F * PMM2_Math.Deg2Rad;
        this.bipedRightArm.rotateAngleZ += 80F * PMM2_Math.Deg2Rad;
        this.bipedLeftArm .rotateAngleY -= 80F * PMM2_Math.Deg2Rad;
        this.bipedRightArm.rotateAngleY += 80F * PMM2_Math.Deg2Rad;
    }

    /** 歩いてる時のモーション */
    protected void setWalkingAnimations()
    {
        this.bipedRightArm.rotateAngleY = 0.5F * this.limbSwingAmount;
        this.bipedLeftArm .rotateAngleY = -0.5F * this.limbSwingAmount;
        this.bipedRightArm.rotateAngleX = MathHelper.cos(this.limbSwing * 1.3324F + (float)Math.PI) * 2.0F * this.limbSwingAmount * 0.5F;
        this.bipedLeftArm .rotateAngleX = MathHelper.cos(this.limbSwing * 1.3324F) * 2.0F * this.limbSwingAmount * 0.5F;
        this.bipedRightArm.rotateAngleZ = 0.3F * this.limbSwingAmount;
        this.bipedLeftArm .rotateAngleZ = -0.3F * this.limbSwingAmount;
        this.bipedRightLeg.rotateAngleZ =  0.075F * this.limbSwingAmount;
        this.bipedLeftLeg .rotateAngleZ = -0.075F * this.limbSwingAmount;
        this.bipedRightLeg.rotateAngleY =  0.15F * this.limbSwingAmount;
        this.bipedLeftLeg .rotateAngleY = -0.15F * this.limbSwingAmount;
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(this.limbSwing * 1.3324F) * 1.4F * this.limbSwingAmount;
        this.bipedLeftLeg .rotateAngleX = MathHelper.cos(this.limbSwing * 1.3324F + (float)Math.PI) * 1.4F * this.limbSwingAmount;
        this.bipedRightLeg.rotationPointZ = MathHelper.sin(this.limbSwing * 1.3324F) * 1F * this.limbSwingAmount + 1F * this.limbSwingAmount;
        this.bipedLeftLeg .rotationPointZ = MathHelper.sin(this.limbSwing * 1.3324F + (float)Math.PI) * 1F * this.limbSwingAmount + 1F * this.limbSwingAmount;

        if (this.doWalkBounding)
        {
            this.bipedBody.rotationPointY += MathHelper.abs(MathHelper.cos(limbSwing * 1.3314F)) * limbSwingAmount * this.modelScale * 0.0625F * 4 - 0.0625F * 2 * limbSwingAmount * (this.isChild? 0.5F: 1F) * this.scaleFactor;
            this.bipedHead.rotationPointY += MathHelper.abs(MathHelper.cos(limbSwing * 1.3314F)) * limbSwingAmount * this.modelScale * 0.0625F * 4 - 0.0625F * 2 * limbSwingAmount * (this.isChild? 0.5F: 1F) * this.scaleFactor;
        }
    }

    /** 何かに乗ってる時のモーション */
    protected void setRidingAnimations()
    {
        this.bipedRightArm.rotateAngleX = -((float)Math.PI / 5F);
        this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 5F);
        this.bipedRightLeg.rotateAngleX = -1.4137167F;
        this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
        this.bipedRightLeg.rotateAngleZ = 0.07853982F;
        this.bipedLeftLeg.rotateAngleX = -1.4137167F;
        this.bipedLeftLeg.rotateAngleY = -((float)Math.PI / 10F);
        this.bipedLeftLeg.rotateAngleZ = -0.07853982F;
    }

    /** 手に何か持ってる時のアニメーション */
    protected void setHasAnythingAnimations(ArmPose leftArmPose, ArmPose rightArmPose)
    {
        switch (leftArmPose)
        {
        case BLOCK:
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - 0.9424779F;
            this.bipedLeftArm.rotateAngleY = 0.5235988F;
            break;
        case ITEM:
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
            this.bipedLeftArm.rotateAngleY = 0.0F;
            break;
        case BOW_AND_ARROW:
            this.bipedRightArm.rotateAngleY = -0.1F - 0.4F;
            this.bipedLeftArm .rotateAngleY =  0.1F;
            this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F);
            this.bipedLeftArm .rotateAngleX = -((float)Math.PI / 2F);
            if (this.isAggressive) {
                this.bipedRightArm.rotateAngleY += this.bipedHead.rotateAngleY;
                this.bipedLeftArm .rotateAngleY += this.bipedHead.rotateAngleY;
                this.bipedRightArm.rotateAngleX += this.bipedHead.rotateAngleX;
                this.bipedLeftArm .rotateAngleX += this.bipedHead.rotateAngleX;
            } else {
                this.bipedRightArm.rotateAngleX += (float)Math.PI / 4F;
                this.bipedLeftArm .rotateAngleX += (float)Math.PI / 4F;
            }
            break;
        default:
            break;
        }

        switch (rightArmPose)
        {
        case BLOCK:
            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - 0.9424779F;
            this.bipedRightArm.rotateAngleY = -0.5235988F;
            break;
        case ITEM:
            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
            this.bipedRightArm.rotateAngleY = 0.0F;
            break;
        case BOW_AND_ARROW:
            this.bipedRightArm.rotateAngleY = -0.1F;
            this.bipedLeftArm .rotateAngleY =  0.1F + 0.4F;
            this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F);
            this.bipedLeftArm .rotateAngleX = -((float)Math.PI / 2F);
            if (this.isAggressive) {
                this.bipedRightArm.rotateAngleY += this.bipedHead.rotateAngleY;
                this.bipedLeftArm .rotateAngleY += this.bipedHead.rotateAngleY;
                this.bipedRightArm.rotateAngleX += this.bipedHead.rotateAngleX;
                this.bipedLeftArm .rotateAngleX += this.bipedHead.rotateAngleX;
            } else {
                this.bipedRightArm.rotateAngleX += (float)Math.PI / 4F;
                this.bipedLeftArm .rotateAngleX += (float)Math.PI / 4F;
            }
            break;
        default:
            break;
        }
    }

    /** 叩くときのモーション*/
    protected void setSwingProgressAnimations()
    {
        HandSide enumhandside = this.GetHandSide((T)this.entityIn);
        PMM2_RendererModel modelrenderer = (PMM2_RendererModel) this.getArmForSide(enumhandside);
        float f1 = this.swingProgress;
        this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float)Math.PI * 2F)) * 0.2F;

        if (enumhandside == HandSide.LEFT)
        {
            this.bipedBody.rotateAngleY *= -1.0F;
        }

        this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
        this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
        this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
        this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
        this.bipedRightArm.rotateAngleY = this.bipedBody.rotateAngleY;
        this.bipedLeftArm.rotateAngleY = this.bipedBody.rotateAngleY;
        this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
        f1 = 1.0F - this.swingProgress;
        f1 = f1 * f1;
        f1 = f1 * f1;
        f1 = 1.0F - f1;
        float f2 = MathHelper.sin(f1 * (float)Math.PI);
        float f3 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
        modelrenderer.rotateAngleX = (float)(modelrenderer.rotateAngleX - (f2 * 1.2D + f3));
        modelrenderer.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
        modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F;
    }

    /** スニーク時のモーション */
    protected void setSneakAnimations()
    {
        this.bipedBody.rotateAngleX += 0.5F;
        this.bipedRightArm.rotateAngleX += 0.2F;
        this.bipedLeftArm.rotateAngleX += 0.2F;
        this.bipedRightArm.rotateAngleZ += 0.1F;
        this.bipedLeftArm.rotateAngleZ -= 0.1F;
        this.bipedRightLeg.rotateAngleX -= 0.75F;
        this.bipedLeftLeg.rotateAngleX -= 0.75F;
        this.bipedHead.rotationPointY += 3.0F;
        this.bipedBody.rotationPointY += 3.0F;
        this.bipedHead.rotationPointZ -= 1.5F;
        this.bipedBody.rotationPointZ -= 1.5F;
    }


    protected void setAggressiveAnimations()
    {
        this.bipedRightArm.rotateAngleX = MathHelper.cos(this.limbSwing * 2.6648F - 1.4F) * 0.15F - 1.5F;
        this.bipedLeftArm.rotateAngleX  = MathHelper.cos(this.limbSwing * 2.6648F - 1.4F) * 0.15F - 1.5F;
        this.bipedRightArm.rotationPointY = 2.5F + MathHelper.cos(this.limbSwing * 2.6648F - 0.7F) * 0.5F + 0.5F;
        this.bipedLeftArm.rotationPointY  = 2.5F + MathHelper.cos(this.limbSwing * 2.6648F - 0.7F) * 0.5F + 0.5F;
    }


    protected void setDeadAnimations() {
        this.bipedRightArm.rotateAngleZ =  15f * PMM2_Math.Deg2Rad;
        this.bipedLeftArm.rotateAngleZ  =  -15f * PMM2_Math.Deg2Rad;
        this.bipedRightArm.rotateAngleX = -130f * PMM2_Math.Deg2Rad;
        this.bipedLeftArm.rotateAngleX  = -130f * PMM2_Math.Deg2Rad;
        this.bipedRightArm.rotationPointZ = -1.5f;
        this.bipedLeftArm.rotationPointZ  = -1.5f;

        this.bipedRightLeg.rotateAngleZ = -2f * PMM2_Math.Deg2Rad;
        this.bipedLeftLeg.rotateAngleZ  =  2f * PMM2_Math.Deg2Rad;
        this.bipedRightLeg.rotateAngleX = -0.2F * PMM2_Math.PI;
        this.bipedLeftLeg.rotateAngleX  = -0.2F * PMM2_Math.PI;

        this.bipedBody.rotateAngleX  = 0.07F * PMM2_Math.PI;
        this.bipedHead.rotateAngleY  = (MathHelper.sin(ageInTicks/5) * 0.1F * PMM2_Math.PI);
        this.bipedHead.rotateAngleX  = 0.09F * PMM2_Math.PI;

        this.bipedLeftEyelid.rotationPointZ = this.bipedRightEyelid.rotationPointZ = 0F;
        this.bipedLeftEyelid.rotationPointY = this.bipedRightEyelid.rotationPointY = 0F;
    }

    protected void setAddAnimations()
    {
    }

    protected boolean setPreAnimations()
    {
        return false;
    }

    protected void setPostAnimations()
    {
    }




    @Override
    public void setModelAttributes(EntityModel<T> model)
    {
        super.setModelAttributes(model);

        ((PMM2_BipedModel<T>)model).leftArmPose = this.leftArmPose;
        ((PMM2_BipedModel<T>)model).rightArmPose = this.rightArmPose;
        ((PMM2_BipedModel<T>)model).isSneak = this.isSneak;

        if (model instanceof PMM2_BipedModel)
        {
            PMM2_BipedModel<T> modelbiped = (PMM2_BipedModel<T>)model;
            this.isSneak = modelbiped.isSneak;
            modelbiped.entityId = modelbiped.entityIn.hashCode();
            modelbiped.entityMotion = new Vec3d( modelbiped.entityIn.posX-modelbiped.entityIn.prevPosX, modelbiped.entityIn.posY-modelbiped.entityIn.prevPosY, modelbiped.entityIn.posZ-modelbiped.entityIn.prevPosZ );
            modelbiped.entityOnGround = modelbiped.entityIn.onGround;
            modelbiped.entityIsInWater = modelbiped.entityIn.isInWater();
            modelbiped.entityHurtTime = ((MobEntity)modelbiped.entityIn).hurtTime;
            modelbiped.isChild = ((LivingEntity) modelbiped.entityIn).isChild();
        }
    }


    public void func_217148_a(PMM2_BipedModel<T> p_217148_1_) {
       p_217148_1_.setModelAttributes(p_217148_1_);
       p_217148_1_.leftArmPose = this.leftArmPose;
       p_217148_1_.rightArmPose = this.rightArmPose;
       p_217148_1_.isSneak = this.isSneak;
    }

    public void setVisible(boolean visible) {
       this.bipedHead.showModel = visible;
       this.bipedBody.showModel = visible;
       this.bipedRightArm.showModel = visible;
       this.bipedLeftArm.showModel = visible;
       this.bipedRightLeg.showModel = visible;
       this.bipedLeftLeg.showModel = visible;

       this.bipedLeftArmwear.showModel = visible;
       this.bipedRightArmwear.showModel = visible;
       this.bipedLeftLegwear.showModel = visible;
       this.bipedRightLegwear.showModel = visible;
       this.bipedBodywear.showModel = visible;
       this.bipedHeadwear.showModel = visible;

       this.bipedUpperBoob.showModel = visible;
       this.bipedLowerBoob.showModel = visible;
       this.bipedUpperBoobwear.showModel = visible;
       this.bipedLowerBoobwear.showModel = visible;
       this.bipedRightEyelid.showModel = visible;
       this.bipedLeftEyelid.showModel = visible;
       this.bipedAhoge.showModel = visible;

       this.bipedArmorLeftArm.showModel = visible;
       this.bipedArmorRightArm.showModel = visible;
       this.bipedArmorLeftLeg.showModel = visible;
       this.bipedArmorRightLeg.showModel = visible;
    }


    protected RendererModel getArmForSide(HandSide side) {
       return side == HandSide.LEFT ? this.bipedLeftArm : this.bipedRightArm;
    }

    protected HandSide GetHandSide(T p_217147_1_) {
       HandSide handside = ((LivingEntity)p_217147_1_).getPrimaryHand();
       return ((LivingEntity)p_217147_1_).swingingHand == Hand.MAIN_HAND ? handside : handside.opposite();
    }

    public static enum ArmPose {
       EMPTY,
       ITEM,
       BLOCK,
       BOW_AND_ARROW,
       THROW_SPEAR,
       CROSSBOW_CHARGE,
       CROSSBOW_HOLD;
    }

    public void postRenderArm(float scale, HandSide side) {
        this.getArmForSide(side).postRender(scale);
    }

    public RendererModel func_205072_a() {
        return this.bipedHead;
    }

    protected HandSide func_217147_a(T p_217147_1_) {
       HandSide handside = ((LivingEntity) p_217147_1_).getPrimaryHand();
       return ((LivingEntity) p_217147_1_).swingingHand == Hand.MAIN_HAND ? handside : handside.opposite();
    }

    public void CopyAll(PMM2_BipedModel<?> a) {
        a.bipedHead.copyCompletly(this.bipedHead);
        a.bipedBody.copyCompletly(this.bipedBody);
        a.bipedRightArm.copyCompletly(this.bipedRightArm);
        a.bipedLeftArm.copyCompletly(this.bipedLeftArm);
        a.bipedRightLeg.copyCompletly(this.bipedRightLeg);
        a.bipedLeftLeg.copyCompletly(this.bipedLeftLeg);
        a.bipedLeftArmwear.copyCompletly(this.bipedLeftArmwear);
        a.bipedRightArmwear.copyCompletly(this.bipedRightArmwear);
        a.bipedLeftLegwear.copyCompletly(this.bipedLeftLegwear);
        a.bipedRightLegwear.copyCompletly(this.bipedRightLegwear);
        a.bipedBodywear.copyCompletly(this.bipedBodywear);
        a.bipedHeadwear.copyCompletly(this.bipedHeadwear);
        a.bipedUpperBoob.copyCompletly(this.bipedUpperBoob);
        a.bipedLowerBoob.copyCompletly(this.bipedLowerBoob);
        a.bipedUpperBoobwear.copyCompletly(this.bipedUpperBoobwear);
        a.bipedLowerBoobwear.copyCompletly(this.bipedLowerBoobwear);
        a.bipedRightEyelid.copyCompletly(this.bipedRightEyelid);
        a.bipedLeftEyelid.copyCompletly(this.bipedLeftEyelid);
        a.bipedAhoge.copyCompletly(this.bipedAhoge);
        a.bipedArmorRightArm.copyCompletly(this.bipedArmorRightArm);
        a.bipedArmorLeftArm.copyCompletly(this.bipedArmorLeftArm);
        a.bipedArmorRightLeg.copyCompletly(this.bipedArmorRightLeg);
        a.bipedArmorLeftLeg.copyCompletly(this.bipedArmorLeftLeg);
    }
}
