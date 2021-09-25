package site.dodoneko.peoplemobsmod2.client.model;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.model.PositionTextureVertex;
import net.minecraft.client.renderer.model.TexturedQuad;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PMM2_ModelQuad extends ModelBox {
//   private final PositionTextureVertex[] vertices;
   private final TexturedQuad[] quads;
   public final float posX1;
   public final float posY1;
   public final float posZ1;
   public final float posX2;
   public final float posY2;
   public final float posZ2;
   private boolean mirror = false;

   public PMM2_ModelQuad(RendererModel renderer, int texU, int texV, float x, float y, float z, int dx, int dy, float delta) {
      this(renderer, texU, texV, x, y, z, dx, dy, delta, renderer.mirror);
   }

   public PMM2_ModelQuad(RendererModel renderer, int texU, int texV, float x, float y, float z, int dx, int dy, float delta, boolean mirror) {
      super(renderer, texU, texV, x, y, z, dx, dy, 0, delta);
      this.posX1 = x;
      this.posY1 = y;
      this.posZ1 = z;
      this.posX2 = x + (float)dx;
      this.posY2 = y + (float)dy;
      this.posZ2 = z;
//      this.vertices = new PositionTextureVertex[8];
      this.quads = new TexturedQuad[6];
      float f = x + (float)dx;
      float f1 = y + (float)dy;
      float f2 = z;
      x = x - delta;
      y = y - delta;
      z = z - delta;
      f = f + delta;
      f1 = f1 + delta;
      f2 = f2 + delta;
//      if (mirror) {
//         float f3 = f;
//         f = x;
//         x = f3;
//      }
      this.mirror = mirror;

      PositionTextureVertex positiontexturevertex7 = new PositionTextureVertex(x, y, z, 0.0F, 0.0F);
      PositionTextureVertex positiontexturevertex = new PositionTextureVertex(f, y, z, 0.0F, 8.0F);
      PositionTextureVertex positiontexturevertex1 = new PositionTextureVertex(f, f1, z, 8.0F, 8.0F);
      PositionTextureVertex positiontexturevertex2 = new PositionTextureVertex(x, f1, z, 8.0F, 0.0F);
      PositionTextureVertex positiontexturevertex3 = new PositionTextureVertex(x, y, f2, 0.0F, 0.0F);
      PositionTextureVertex positiontexturevertex4 = new PositionTextureVertex(f, y, f2, 0.0F, 8.0F);
      PositionTextureVertex positiontexturevertex5 = new PositionTextureVertex(f, f1, f2, 8.0F, 8.0F);
      PositionTextureVertex positiontexturevertex6 = new PositionTextureVertex(x, f1, f2, 8.0F, 0.0F);
//      this.vertices[0] = positiontexturevertex7;
//      this.vertices[1] = positiontexturevertex;
//      this.vertices[2] = positiontexturevertex1;
//      this.vertices[3] = positiontexturevertex2;
//      this.vertices[4] = positiontexturevertex3;
//      this.vertices[5] = positiontexturevertex4;
//      this.vertices[6] = positiontexturevertex5;
//      this.vertices[7] = positiontexturevertex6;
      // Å´ç∂ñ 
//      this.quads[0] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex4, positiontexturevertex, positiontexturevertex1, positiontexturevertex5}, texU, texV, texU + dx, texV + dy, renderer.textureWidth, renderer.textureHeight);
      // Å´âEñ 
//      this.quads[1] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex7, positiontexturevertex3, positiontexturevertex6, positiontexturevertex2}, texU, texV, texU + dx, texV + dy, renderer.textureWidth, renderer.textureHeight);
      // Å´è„ñ 
//      this.quads[2] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex4, positiontexturevertex3, positiontexturevertex7, positiontexturevertex}, texU, texV, texU + dx, texV + dy, renderer.textureWidth, renderer.textureHeight);
      // Å´â∫ñ 
//      this.quads[3] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex1, positiontexturevertex2, positiontexturevertex6, positiontexturevertex5}, texU, texV, texU + dx, texV + dy, renderer.textureWidth, renderer.textureHeight);
      // Å´ê≥ñ 
      this.quads[4] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex, positiontexturevertex7, positiontexturevertex2, positiontexturevertex1}, texU, texV, texU + dx, texV + dy, renderer.textureWidth, renderer.textureHeight);
      // Å´å„ÇÎ
      this.quads[5] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex3, positiontexturevertex4, positiontexturevertex5, positiontexturevertex6}, texU, texV, texU + dx, texV + dy, renderer.textureWidth, renderer.textureHeight);
//      if (mirror) {
//         for(TexturedQuad texturedquad : this.quads) {
//            texturedquad.flipFace();
//         }
//      }

   }

   @Override
   public void render(BufferBuilder renderer, float scale) {
//      for(TexturedQuad texturedquad : this.quads) {
//         texturedquad.draw(renderer, scale);
//      }
       this.quads[(this.mirror?5:4)].draw(renderer, scale);
   }

   @Override
   public PMM2_ModelQuad setBoxName(String name) {
      this.boxName = name;
      return this;
   }
}
