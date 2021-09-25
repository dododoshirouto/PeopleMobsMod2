package site.dodoneko.peoplemobsmod2.client.model;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import site.dodoneko.peoplemobsmod2.util.PMM2_Math;

import com.mojang.blaze3d.platform.GlStateManager;



public class PMM2_RendererModel extends RendererModel
{
    public boolean easeMotion = true;

    public float initRotateAngleX = 0.0F;
    public float initRotateAngleY = 0.0F;
    public float initRotateAngleZ = 0.0F;

    public float initRotationPointX = 0.0F;
    public float initRotationPointY = 0.0F;
    public float initRotationPointZ = 0.0F;

    private int texOffX, texOffY;

    public PMM2_RendererModel parent = null;
    public Map<Integer, Vector6> renderMap = new HashMap<Integer, Vector6>();
    private int displayList;
    private boolean compiled;

    public PMM2_RendererModel(EntityModel<Entity> model, String boxNameIn) {
        super(model, boxNameIn);
//        this.textureWidth = 64.0F;
//        this.textureHeight = 64.0F;
        this.parent = null;
    }
    public PMM2_RendererModel(EntityModel<Entity> model)
    {
        this(model, (String)null);
    }
    public PMM2_RendererModel(EntityModel<Entity> model, int texOffX, int texOffY)
    {
        this(model);
        this.setTextureOffset(texOffX, texOffY);
        this.texOffX = texOffX;
        this.texOffY = texOffY;
    }
    public PMM2_RendererModel(EntityModel<Entity> model, boolean show)
    {
        super(model, (String)null);
        this.showModel = show;
        this.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.parent = null;
    }

    public void addQuad(float offX, float offY, float offZ, int width, int height, float scaleFactor) {
       this.cubeList.add(new PMM2_ModelQuad(this, texOffX, texOffY, offX, offY, offZ, width, height, scaleFactor));
    }
    public void addQuad(float offX, float offY, float offZ, int width, int height, float scaleFactor, boolean mirror) {
        this.cubeList.add(new PMM2_ModelQuad(this, texOffX, texOffY, offX, offY, offZ, width, height, scaleFactor, mirror));
     }

    public void reset()
    {
        this.rotateAngleX = this.initRotateAngleX;
        this.rotateAngleY = this.initRotateAngleY;
        this.rotateAngleZ = this.initRotateAngleZ;
        this.rotationPointX = this.initRotationPointX;
        this.rotationPointY = this.initRotationPointY;
        this.rotationPointZ = this.initRotationPointZ;
    }

    public void setRotateAngle(float rotateAngleXIn,float rotateAngleYIn, float rotateAngleZIn)
    {
        this.initRotateAngleX = this.rotateAngleX = rotateAngleXIn;
        this.initRotateAngleY = this.rotateAngleY = rotateAngleYIn;
        this.initRotateAngleZ = this.rotateAngleZ = rotateAngleZIn;
    }

    @Override
    public void setRotationPoint(float rotationPointXIn, float rotationPointYIn, float rotationPointZIn)
    {
        this.initRotationPointX = this.rotationPointX = rotationPointXIn;
        this.initRotationPointY = this.rotationPointY = rotationPointYIn;
        this.initRotationPointZ = this.rotationPointZ = rotationPointZIn;
    }

    public void copy(PMM2_RendererModel src)
    {
        this.rotateAngleX = src.rotateAngleX;
        this.rotateAngleY = src.rotateAngleY;
        this.rotateAngleZ = src.rotateAngleZ;
        this.rotationPointX = src.rotationPointX;
        this.rotationPointY = src.rotationPointY;
        this.rotationPointZ = src.rotationPointZ;
    }

    public void copyAngle(PMM2_RendererModel src)
    {
        this.rotateAngleX = src.rotateAngleX;
        this.rotateAngleY = src.rotateAngleY;
        this.rotateAngleZ = src.rotateAngleZ;
    }

    public void copyPoint(PMM2_RendererModel src)
    {
        this.rotationPointX = src.rotationPointX;
        this.rotationPointY = src.rotationPointY;
        this.rotationPointZ = src.rotationPointZ;
    }

    public void copyDisplayed(PMM2_RendererModel src)
    {
        this.isHidden = src.isHidden;
        this.showModel = src.showModel;
    }

    public void copyCompletly(PMM2_RendererModel src)
    {
        this.isHidden = src.isHidden;
        this.rotateAngleX = src.rotateAngleX;
        this.rotateAngleY = src.rotateAngleY;
        this.rotateAngleZ = src.rotateAngleZ;
        this.rotationPointX = src.rotationPointX;
        this.rotationPointY = src.rotationPointY;
        this.rotationPointZ = src.rotationPointZ;
        this.initRotateAngleX = src.initRotateAngleX;
        this.initRotateAngleY = src.initRotateAngleY;
        this.initRotateAngleZ = src.initRotateAngleZ;
        this.initRotationPointX = src.initRotationPointX;
        this.initRotationPointY = src.initRotationPointY;
        this.initRotationPointZ = src.initRotationPointZ;
        this.showModel = src.showModel;
    }

    public void setMatrix(float scale, boolean global) {
//        Logger.getGlobal().info(""+scale);
        if (this.parent != null && global) {
            this.parent.setMatrix(scale, true);
        }

        GlStateManager.translatef(this.offsetX, this.offsetY, this.offsetZ);

        if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F)
        {
            if (this.rotationPointX == 0.0F && this.rotationPointY == 0.0F && this.rotationPointZ == 0.0F)
            {
                return;
            }
            else
            {
                GlStateManager.translatef(this.rotationPointX * scale, this.rotationPointY * scale, this.rotationPointZ * scale);

                return;
            }
        }
        else
        {
            GlStateManager.translatef(this.rotationPointX * scale, this.rotationPointY * scale, this.rotationPointZ * scale);

            if (this.rotateAngleZ != 0.0F)
            {
                GlStateManager.rotatef(this.rotateAngleZ * (180F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
            }

            if (this.rotateAngleY != 0.0F)
            {
                GlStateManager.rotatef(this.rotateAngleY * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
            }

            if (this.rotateAngleX != 0.0F)
            {
                GlStateManager.rotatef(this.rotateAngleX * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
            }

            return;
        }
    }

    public void render(float scale, int entityHash) {
        render(scale, entityHash, this.easeMotion);
    }

    public void render(float scale, int entityHash, boolean doEaseCalc) {
        Vector6 prevData;

        if (!this.isHidden)
        {
                if (!this.renderMap.containsKey(entityHash))
                {
                    this.renderMap.put(entityHash, new Vector6(this));
                }
                prevData = this.renderMap.get(entityHash);

                if (doEaseCalc) {
                    float speed = 0.35F;
                    this.rotateAngleX = PMM2_Math.lerpRotation(prevData.rx, this.rotateAngleX, speed);
                    this.rotateAngleY = PMM2_Math.lerpRotation(prevData.ry, this.rotateAngleY, speed);
                    this.rotateAngleZ = PMM2_Math.lerpRotation(prevData.rz, this.rotateAngleZ, speed);
                    this.rotationPointX = PMM2_Math.lerp(prevData.px, this.rotationPointX, speed);
                    this.rotationPointY = PMM2_Math.lerp(prevData.py, this.rotationPointY, speed);
                    this.rotationPointZ = PMM2_Math.lerp(prevData.pz, this.rotationPointZ, speed);
                    this.renderMap.put(entityHash, new Vector6(this));
                }

                if (!this.compiled)
                {
                    this.compileDisplayList(scale);
                }

                GlStateManager.pushMatrix();
                this.setMatrix(scale, false);


                if (this.showModel) GlStateManager.callList(this.displayList);

                if (this.childModels != null)
                {
                    for (int i = 0; i < this.childModels.size(); ++i)
                    {
                        ((PMM2_RendererModel)this.childModels.get(i)).render(scale, entityHash, doEaseCalc);
                    }
                }

                GlStateManager.popMatrix();

//                this.renderMap.get(entityHash).set(this).changedTime = Util.milliTime();
                // TODO: if changedTime + 1min > SystemTime -> removed from renderMap.
        }
    }


    private void compileDisplayList(float scale)
    {
        this.displayList = GLAllocation.generateDisplayLists(1);
        GlStateManager.newList(this.displayList, 4864);
        BufferBuilder vertexbuffer = Tessellator.getInstance().getBuffer();

        for (int i = 0; i < this.cubeList.size(); ++i)
        {
            ((ModelBox)this.cubeList.get(i)).render(vertexbuffer, scale);
        }

        GlStateManager.endList();
        this.compiled = true;
    }

    @Override
    public PMM2_RendererModel setTextureSize(int textureWidthIn, int textureHeightIn)
    {
        return (PMM2_RendererModel)super.setTextureSize(textureWidthIn, textureHeightIn);
    }

    @Override
    public void addChild(RendererModel child) {
        ((PMM2_RendererModel)child).parent = this;
        super.addChild((RendererModel)child);
    }


    private class Vector6
    {
        public float px, py, pz, rx, ry, rz;

        public Vector6(PMM2_RendererModel renderer)
        {
            this.px = renderer.rotationPointX;
            this.py = renderer.rotationPointY;
            this.pz = renderer.rotationPointZ;
            this.rx = renderer.rotateAngleX;
            this.ry = renderer.rotateAngleY;
            this.rz = renderer.rotateAngleZ;
        }
    }
}
