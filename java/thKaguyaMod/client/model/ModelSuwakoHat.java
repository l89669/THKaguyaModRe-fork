/*  1:   */ package thKaguyaMod.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelBiped;
/*  4:   */ import net.minecraft.client.model.ModelRenderer;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ 
/*  7:   */ public class ModelSuwakoHat
/*  8:   */   extends ModelBiped
/*  9:   */ {
/* 10:   */   ModelRenderer hatBase;
/* 11:   */   ModelRenderer eyeright;
/* 12:   */   ModelRenderer eyeleft;
/* 13:   */   ModelRenderer brim;
/* 14:   */   
/* 15:   */   public ModelSuwakoHat(float scale, float yOffset, int width, int height)
/* 16:   */   {
/* 17:16 */     this.textureWidth = width;
/* 18:17 */     this.textureHeight = height;
/* 19:   */     
/* 20:   */ 
/* 21:20 */     this.hatBase = new ModelRenderer(this, 0, 0);
/* 22:21 */     this.hatBase.addBox(-4.0F, -9.0F, -4.0F, 8, 4, 8, scale);
/* 23:22 */     this.hatBase.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 24:23 */     this.hatBase.setTextureSize(64, 32);
/* 25:24 */     this.hatBase.mirror = true;
/* 26:25 */     this.bipedHead.addChild(this.hatBase);
/* 27:26 */     setRotation(this.hatBase, 0.0F, 0.0F, 0.0F);
/* 28:27 */     this.eyeright = new ModelRenderer(this, 0, 0);
/* 29:28 */     this.eyeright.addBox(-1.0F, -2.0F, -1.0F, 2, 2, 2, scale);
/* 30:29 */     this.eyeright.setRotationPoint(-4.0F, -9.0F, -4.0F);
/* 31:30 */     this.eyeright.setTextureSize(64, 32);
/* 32:31 */     this.eyeright.mirror = true;
/* 33:32 */     this.hatBase.addChild(this.eyeright);
/* 34:33 */     setRotation(this.eyeright, 0.0F, 0.0F, 0.0F);
/* 35:34 */     this.eyeleft = new ModelRenderer(this, 24, 0);
/* 36:35 */     this.eyeleft.addBox(-1.0F, -2.0F, -1.0F, 2, 2, 2, scale);
/* 37:36 */     this.eyeleft.setRotationPoint(4.0F, -9.0F, -4.0F);
/* 38:37 */     this.eyeleft.setTextureSize(64, 32);
/* 39:38 */     this.eyeleft.mirror = true;
/* 40:39 */     this.hatBase.addChild(this.eyeleft);
/* 41:40 */     setRotation(this.eyeleft, 0.0F, 0.0F, 0.0F);
/* 42:41 */     this.brim = new ModelRenderer(this, 0, 16);
/* 43:42 */     this.brim.addBox(-6.0F, -1.0F, -6.0F, 12, 1, 12, scale);
/* 44:43 */     this.brim.setRotationPoint(0.0F, -5.0F, 0.0F);
/* 45:44 */     this.brim.setTextureSize(64, 32);
/* 46:45 */     this.brim.mirror = true;
/* 47:46 */     this.hatBase.addChild(this.brim);
/* 48:47 */     setRotation(this.brim, 0.0F, 0.0F, 0.0F);
/* 49:   */   }
/* 50:   */   
/* 51:   */   public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
/* 52:   */   {
/* 53:53 */     setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
/* 54:54 */     this.bipedHead.render(size);
/* 55:   */   }
/* 56:   */   
/* 57:   */   private void setRotation(ModelRenderer model, float x, float y, float z)
/* 58:   */   {
/* 59:59 */     model.rotateAngleX = x;
/* 60:60 */     model.rotateAngleY = y;
/* 61:61 */     model.rotateAngleZ = z;
/* 62:   */   }
/* 63:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.ModelSuwakoHat
 * JD-Core Version:    0.7.0.1
 */