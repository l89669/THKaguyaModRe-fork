/*  1:   */ package thKaguyaMod.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelBiped;
/*  4:   */ import net.minecraft.client.model.ModelRenderer;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ 
/*  7:   */ public class ModelMarisaHat
/*  8:   */   extends ModelBiped
/*  9:   */ {
/* 10:   */   ModelRenderer hatBase;
/* 11:   */   ModelRenderer hatBase2;
/* 12:   */   ModelRenderer hatBase3;
/* 13:   */   ModelRenderer hatBase4;
/* 14:   */   ModelRenderer brim;
/* 15:   */   ModelRenderer ribbonRight;
/* 16:   */   ModelRenderer ribbonLeft;
/* 17:   */   
/* 18:   */   public ModelMarisaHat(float scale, float yOffset, int width, int height)
/* 19:   */   {
/* 20:19 */     this.textureWidth = width;
/* 21:20 */     this.textureHeight = height;
/* 22:   */     
/* 23:22 */     this.hatBase = new ModelRenderer(this, 0, 0);
/* 24:23 */     this.hatBase.addBox(-4.0F, -8.0F, -4.0F, 8, 4, 8, scale);
/* 25:24 */     this.hatBase.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 26:25 */     this.hatBase.setTextureSize(64, 32);
/* 27:26 */     this.hatBase.mirror = true;
/* 28:27 */     this.bipedHead.addChild(this.hatBase);
/* 29:28 */     setRotation(this.hatBase, 0.0F, 0.0F, 0.0F);
/* 30:29 */     this.hatBase2 = new ModelRenderer(this, 32, 2);
/* 31:30 */     this.hatBase2.addBox(-3.0F, -3.0F, -3.0F, 6, 3, 6, scale);
/* 32:31 */     this.hatBase2.setRotationPoint(0.0F, -7.0F, 0.0F);
/* 33:32 */     this.hatBase2.setTextureSize(64, 32);
/* 34:33 */     this.hatBase2.mirror = true;
/* 35:34 */     this.hatBase.addChild(this.hatBase2);
/* 36:35 */     setRotation(this.hatBase2, -0.2792527F, 0.0F, 0.0F);
/* 37:36 */     this.hatBase3 = new ModelRenderer(this, 48, 16);
/* 38:37 */     this.hatBase3.addBox(-2.0F, -3.0F, -2.0F, 4, 3, 4, scale);
/* 39:38 */     this.hatBase3.setRotationPoint(0.0F, -9.0F, 0.0F);
/* 40:39 */     this.hatBase3.setTextureSize(64, 32);
/* 41:40 */     this.hatBase3.mirror = true;
/* 42:41 */     this.hatBase.addChild(this.hatBase3);
/* 43:42 */     setRotation(this.hatBase3, -0.541052F, 0.0F, 0.0F);
/* 44:43 */     this.hatBase4 = new ModelRenderer(this, 48, 24);
/* 45:44 */     this.hatBase4.addBox(-1.0F, -3.0F, -1.0F, 2, 3, 2, scale);
/* 46:45 */     this.hatBase4.setRotationPoint(0.0F, -11.0F, 1.0F);
/* 47:46 */     this.hatBase4.setTextureSize(64, 32);
/* 48:47 */     this.hatBase4.mirror = true;
/* 49:48 */     this.hatBase.addChild(this.hatBase4);
/* 50:49 */     setRotation(this.hatBase4, -0.8203047F, 0.0F, 0.0F);
/* 51:50 */     this.brim = new ModelRenderer(this, 0, 16);
/* 52:51 */     this.brim.addBox(-6.0F, 0.0F, -6.0F, 12, 1, 12, scale);
/* 53:52 */     this.brim.setRotationPoint(0.0F, -6.0F, 0.0F);
/* 54:53 */     this.brim.setTextureSize(64, 32);
/* 55:54 */     this.brim.mirror = true;
/* 56:55 */     this.hatBase.addChild(this.brim);
/* 57:56 */     setRotation(this.brim, 0.0F, 0.0F, 0.0F);
/* 58:57 */     this.ribbonRight = new ModelRenderer(this, 44, 12);
/* 59:58 */     this.ribbonRight.addBox(0.0F, -2.0F, -1.0F, 4, 3, 1, scale);
/* 60:59 */     this.ribbonRight.setRotationPoint(0.5F, -6.0F, -4.0F);
/* 61:60 */     this.ribbonRight.setTextureSize(64, 32);
/* 62:61 */     this.ribbonRight.mirror = true;
/* 63:62 */     this.hatBase.addChild(this.ribbonRight);
/* 64:63 */     setRotation(this.ribbonRight, 0.0F, 0.0F, -0.349066F);
/* 65:64 */     this.ribbonLeft = new ModelRenderer(this, 32, 12);
/* 66:65 */     this.ribbonLeft.addBox(-4.0F, -2.0F, -1.0F, 4, 3, 1, scale);
/* 67:66 */     this.ribbonLeft.setRotationPoint(-0.5F, -6.0F, -4.0F);
/* 68:67 */     this.ribbonLeft.setTextureSize(64, 32);
/* 69:68 */     this.ribbonLeft.mirror = true;
/* 70:69 */     this.hatBase.addChild(this.ribbonLeft);
/* 71:70 */     setRotation(this.ribbonLeft, 0.0F, 0.0F, 0.349066F);
/* 72:   */   }
/* 73:   */   
/* 74:   */   public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
/* 75:   */   {
/* 76:76 */     setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
/* 77:77 */     this.bipedHead.render(size);
/* 78:   */   }
/* 79:   */   
/* 80:   */   private void setRotation(ModelRenderer model, float x, float y, float z)
/* 81:   */   {
/* 82:82 */     model.rotateAngleX = x;
/* 83:83 */     model.rotateAngleY = y;
/* 84:84 */     model.rotateAngleZ = z;
/* 85:   */   }
/* 86:   */   
/* 87:   */   public void setRotationAngles(float movement, float far, float tick, float yaw, float pitch, float size, Entity entity)
/* 88:   */   {
/* 89:90 */     super.setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
/* 90:   */   }
/* 91:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.ModelMarisaHat
 * JD-Core Version:    0.7.0.1
 */