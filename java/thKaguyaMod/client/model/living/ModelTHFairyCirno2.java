/*   1:    */ package thKaguyaMod.client.model.living;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.model.ModelBase;
/*   6:    */ import net.minecraft.client.model.ModelRenderer;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.MathHelper;
/*   9:    */ import thKaguyaMod.entity.living.EntityTHFairy;
/*  10:    */ 
/*  11:    */ @SideOnly(Side.CLIENT)
/*  12:    */ public class ModelTHFairyCirno2
/*  13:    */   extends ModelBase
/*  14:    */ {
/*  15:    */   ModelRenderer rightWing;
/*  16:    */   ModelRenderer leftWing;
/*  17:    */   ModelRenderer rightWing2;
/*  18:    */   ModelRenderer leftWing2;
/*  19:    */   ModelRenderer rightWing3;
/*  20:    */   ModelRenderer leftWing3;
/*  21:    */   
/*  22:    */   public ModelTHFairyCirno2()
/*  23:    */   {
/*  24: 25 */     this.textureWidth = 64;
/*  25: 26 */     this.textureHeight = 64;
/*  26:    */     
/*  27:    */ 
/*  28:    */ 
/*  29: 30 */     this.rightWing = new ModelRenderer(this, 0, 48);
/*  30: 31 */     this.rightWing.addBox(-14.0F, 0.0F, 0.0F, 14, 3, 3);
/*  31: 32 */     this.rightWing.setRotationPoint(-1.0F, -2.0F, 2.0F);
/*  32:    */     
/*  33:    */ 
/*  34: 35 */     setRotation(this.rightWing, 0.0F, 0.4886922F, 0.314159F);
/*  35:    */     
/*  36: 37 */     this.leftWing = new ModelRenderer(this, 0, 48);
/*  37: 38 */     this.leftWing.addBox(0.0F, 0.0F, 0.0F, 14, 3, 3);
/*  38: 39 */     this.leftWing.setRotationPoint(0.0F, -2.0F, 2.0F);
/*  39:    */     
/*  40:    */ 
/*  41: 42 */     setRotation(this.leftWing, 0.0F, -0.4886922F, -0.314159F);
/*  42:    */     
/*  43: 44 */     this.rightWing2 = new ModelRenderer(this, 0, 48);
/*  44: 45 */     this.rightWing2.addBox(-14.0F, 0.0F, 0.0F, 14, 3, 3);
/*  45: 46 */     this.rightWing2.setRotationPoint(-1.0F, 1.0F, 2.0F);
/*  46:    */     
/*  47:    */ 
/*  48: 49 */     setRotation(this.rightWing2, 0.0F, 0.4886922F, -0.314159F);
/*  49:    */     
/*  50: 51 */     this.leftWing2 = new ModelRenderer(this, 0, 48);
/*  51: 52 */     this.leftWing2.addBox(0.0F, 0.0F, 0.0F, 14, 3, 3);
/*  52: 53 */     this.leftWing2.setRotationPoint(1.0F, 1.0F, 2.0F);
/*  53:    */     
/*  54:    */ 
/*  55: 56 */     setRotation(this.leftWing2, 0.0F, -0.4886922F, 0.314159F);
/*  56: 57 */     this.rightWing3 = new ModelRenderer(this, 0, 48);
/*  57: 58 */     this.rightWing3.addBox(-14.0F, 0.0F, 0.0F, 14, 3, 3);
/*  58: 59 */     this.rightWing3.setRotationPoint(-1.0F, 0.0F, 2.0F);
/*  59:    */     
/*  60:    */ 
/*  61: 62 */     setRotation(this.rightWing3, 0.0F, 0.4886922F, 0.0F);
/*  62: 63 */     this.leftWing3 = new ModelRenderer(this, 0, 48);
/*  63: 64 */     this.leftWing3.addBox(0.0F, 0.0F, 0.0F, 14, 3, 3);
/*  64: 65 */     this.leftWing3.setRotationPoint(1.0F, 0.0F, 2.0F);
/*  65:    */     
/*  66:    */ 
/*  67: 68 */     setRotation(this.leftWing3, 0.0F, -0.4886922F, 0.0F);
/*  68:    */   }
/*  69:    */   
/*  70:    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*  71:    */   {
/*  72: 76 */     super.render(entity, f, f1, f2, f3, f4, f5);
/*  73: 77 */     setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/*  74:    */     
/*  75:    */ 
/*  76:    */ 
/*  77:    */ 
/*  78:    */ 
/*  79:    */ 
/*  80: 84 */     this.rightWing.render(f5);
/*  81: 85 */     this.rightWing2.render(f5);
/*  82: 86 */     this.rightWing3.render(f5);
/*  83: 87 */     this.leftWing.render(f5);
/*  84: 88 */     this.leftWing2.render(f5);
/*  85: 89 */     this.leftWing3.render(f5);
/*  86:    */     
/*  87: 91 */     EntityTHFairy var8 = (EntityTHFairy)entity;
/*  88:    */     
/*  89:    */ 
/*  90:    */ 
/*  91: 95 */     this.rightWing.setRotationPoint(0.0F, -3.0F, 2.0F);
/*  92: 96 */     this.leftWing.setRotationPoint(0.0F, -3.0F, 2.0F);
/*  93: 97 */     this.rightWing2.setRotationPoint(-1.0F, 0.0F, 2.0F);
/*  94: 98 */     this.leftWing2.setRotationPoint(1.0F, 0.0F, 2.0F);
/*  95: 99 */     this.rightWing3.setRotationPoint(-1.0F, -1.5F, 2.0F);
/*  96:100 */     this.leftWing3.setRotationPoint(1.0F, -1.5F, 2.0F);
/*  97:    */     
/*  98:    */ 
/*  99:103 */     this.rightWing.rotateAngleY = (MathHelper.cos(f2 * 0.3F) * 3.141593F * 0.1F);
/* 100:104 */     this.leftWing.rotateAngleY = (-this.rightWing.rotateAngleY);
/* 101:105 */     this.rightWing2.rotateAngleY = (MathHelper.cos(f2 * 0.3F) * 3.141593F * 0.1F);
/* 102:106 */     this.leftWing2.rotateAngleY = (-this.rightWing2.rotateAngleY);
/* 103:107 */     this.rightWing3.rotateAngleY = (MathHelper.cos(f2 * 0.3F) * 3.141593F * 0.1F);
/* 104:108 */     this.leftWing3.rotateAngleY = (-this.rightWing3.rotateAngleY);
/* 105:    */   }
/* 106:    */   
/* 107:    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/* 108:    */   {
/* 109:116 */     model.rotateAngleX = x;
/* 110:117 */     model.rotateAngleY = y;
/* 111:118 */     model.rotateAngleZ = z;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity)
/* 115:    */   {
/* 116:123 */     super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
/* 117:    */   }
/* 118:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.living.ModelTHFairyCirno2
 * JD-Core Version:    0.7.0.1
 */