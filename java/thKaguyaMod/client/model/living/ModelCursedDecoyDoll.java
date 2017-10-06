/*   1:    */ package thKaguyaMod.client.model.living;
/*   2:    */ 
/*   3:    */ import net.minecraft.client.model.ModelBase;
/*   4:    */ import net.minecraft.client.model.ModelRenderer;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ 
/*   7:    */ public class ModelCursedDecoyDoll
/*   8:    */   extends ModelBase
/*   9:    */ {
/*  10:    */   ModelRenderer bipedBody;
/*  11:    */   ModelRenderer bipedRightArm;
/*  12:    */   ModelRenderer bipedLeftArm;
/*  13:    */   ModelRenderer bipedRightLeg;
/*  14:    */   ModelRenderer bipedLeftLeg;
/*  15:    */   ModelRenderer skirtTop;
/*  16:    */   ModelRenderer bipedHead;
/*  17:    */   ModelRenderer longHair;
/*  18:    */   ModelRenderer leftBraid;
/*  19:    */   ModelRenderer rightBraid;
/*  20:    */   ModelRenderer rightRibbon;
/*  21:    */   ModelRenderer leftRibbon;
/*  22:    */   
/*  23:    */   public ModelCursedDecoyDoll()
/*  24:    */   {
/*  25: 25 */     this.textureWidth = 64;
/*  26: 26 */     this.textureHeight = 64;
/*  27:    */     
/*  28: 28 */     this.bipedBody = new ModelRenderer(this, 32, 8);
/*  29: 29 */     this.bipedBody.addBox(-3.0F, 0.0F, -2.0F, 6, 6, 4);
/*  30: 30 */     this.bipedBody.setRotationPoint(0.0F, 10.0F, 0.0F);
/*  31: 31 */     this.bipedBody.setTextureSize(64, 64);
/*  32: 32 */     this.bipedBody.mirror = true;
/*  33: 33 */     setRotation(this.bipedBody, 0.0F, 0.0F, 0.0F);
/*  34: 34 */     this.bipedRightArm = new ModelRenderer(this, 32, 0);
/*  35: 35 */     this.bipedRightArm.addBox(0.0F, -1.0F, -1.0F, 4, 6, 2);
/*  36: 36 */     this.bipedRightArm.setRotationPoint(-4.0F, 11.0F, 0.0F);
/*  37: 37 */     this.bipedRightArm.setTextureSize(64, 64);
/*  38: 38 */     this.bipedRightArm.mirror = true;
/*  39: 39 */     setRotation(this.bipedRightArm, 0.0F, 0.0F, 0.5235988F);
/*  40: 40 */     this.bipedLeftArm = new ModelRenderer(this, 32, 0);
/*  41: 41 */     this.bipedLeftArm.addBox(-4.0F, -1.0F, -1.0F, 4, 6, 2);
/*  42: 42 */     this.bipedLeftArm.setRotationPoint(4.0F, 11.0F, 0.0F);
/*  43: 43 */     this.bipedLeftArm.setTextureSize(64, 64);
/*  44: 44 */     this.bipedLeftArm.mirror = true;
/*  45: 45 */     setRotation(this.bipedLeftArm, 0.0F, 0.0F, -0.5235988F);
/*  46: 46 */     this.bipedRightLeg = new ModelRenderer(this, 36, 22);
/*  47: 47 */     this.bipedRightLeg.addBox(-1.0F, 0.0F, -2.0F, 3, 7, 4);
/*  48: 48 */     this.bipedRightLeg.setRotationPoint(-2.0F, 17.0F, 0.0F);
/*  49: 49 */     this.bipedRightLeg.setTextureSize(64, 64);
/*  50: 50 */     this.bipedRightLeg.mirror = true;
/*  51: 51 */     setRotation(this.bipedRightLeg, 0.0F, 0.0F, 0.0F);
/*  52: 52 */     this.bipedLeftLeg = new ModelRenderer(this, 36, 22);
/*  53: 53 */     this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 3, 7, 4);
/*  54: 54 */     this.bipedLeftLeg.setRotationPoint(2.0F, 17.0F, 0.0F);
/*  55: 55 */     this.bipedLeftLeg.setTextureSize(64, 64);
/*  56: 56 */     this.bipedLeftLeg.mirror = true;
/*  57: 57 */     setRotation(this.bipedLeftLeg, 0.0F, 0.0F, 0.0F);
/*  58: 58 */     this.skirtTop = new ModelRenderer(this, 0, 16);
/*  59: 59 */     this.skirtTop.addBox(-4.0F, 0.0F, -5.0F, 8, 7, 6);
/*  60: 60 */     this.skirtTop.setRotationPoint(0.0F, 16.0F, 2.0F);
/*  61: 61 */     this.skirtTop.setTextureSize(64, 64);
/*  62: 62 */     this.skirtTop.mirror = true;
/*  63: 63 */     setRotation(this.skirtTop, 0.0F, 0.0F, 0.0F);
/*  64: 64 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/*  65: 65 */     this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
/*  66: 66 */     this.bipedHead.setRotationPoint(0.0F, 10.0F, 0.0F);
/*  67: 67 */     this.bipedHead.setTextureSize(64, 64);
/*  68: 68 */     this.bipedHead.mirror = true;
/*  69: 69 */     setRotation(this.bipedHead, 0.0F, 0.0F, 0.0F);
/*  70: 70 */     this.longHair = new ModelRenderer(this, 0, 49);
/*  71: 71 */     this.longHair.addBox(-5.0F, 0.0F, -5.0F, 10, 7, 8);
/*  72: 72 */     this.longHair.setRotationPoint(0.0F, 4.0F, 2.0F);
/*  73: 73 */     this.longHair.setTextureSize(64, 64);
/*  74: 74 */     this.longHair.mirror = true;
/*  75: 75 */     setRotation(this.longHair, 0.0F, 0.0F, 0.0F);
/*  76: 76 */     this.leftBraid = new ModelRenderer(this, 0, 32);
/*  77: 77 */     this.leftBraid.addBox(0.0F, 0.0F, -1.0F, 1, 6, 1);
/*  78: 78 */     this.leftBraid.setRotationPoint(3.0F, 5.0F, -3.0F);
/*  79: 79 */     this.leftBraid.setTextureSize(64, 64);
/*  80: 80 */     this.leftBraid.mirror = true;
/*  81: 81 */     setRotation(this.leftBraid, -0.244346F, 0.0F, 0.0F);
/*  82: 82 */     this.rightBraid = new ModelRenderer(this, 0, 32);
/*  83: 83 */     this.rightBraid.addBox(-1.0F, 0.0F, -1.0F, 1, 6, 1);
/*  84: 84 */     this.rightBraid.setRotationPoint(-3.0F, 5.0F, -3.0F);
/*  85: 85 */     this.rightBraid.setTextureSize(64, 64);
/*  86: 86 */     this.rightBraid.mirror = true;
/*  87: 87 */     setRotation(this.rightBraid, -0.244346F, 0.0F, 0.0F);
/*  88: 88 */     this.leftRibbon = new ModelRenderer(this, 46, 59);
/*  89: 89 */     this.leftRibbon.addBox(0.0F, -3.0F, 0.0F, 5, 4, 1);
/*  90: 90 */     this.leftRibbon.setRotationPoint(-4.0F, 4.0F, 0.0F);
/*  91: 91 */     this.leftRibbon.setTextureSize(64, 64);
/*  92: 92 */     this.leftRibbon.mirror = true;
/*  93: 93 */     setRotation(this.leftRibbon, 0.446143F, 0.0F, -0.7853982F);
/*  94: 94 */     this.rightRibbon = new ModelRenderer(this, 46, 59);
/*  95: 95 */     this.rightRibbon.addBox(0.0F, -3.0F, -1.0F, 5, 4, 1);
/*  96: 96 */     this.rightRibbon.setRotationPoint(-4.0F, 4.0F, 0.0F);
/*  97: 97 */     this.rightRibbon.setTextureSize(64, 64);
/*  98: 98 */     this.rightRibbon.mirror = true;
/*  99: 99 */     setRotation(this.rightRibbon, -0.4461411F, 3.141593F, 0.349066F);
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/* 103:    */   {
/* 104:104 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 105:105 */     setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 106:106 */     this.bipedBody.render(f5);
/* 107:107 */     this.bipedRightArm.render(f5);
/* 108:108 */     this.bipedLeftArm.render(f5);
/* 109:109 */     this.bipedRightLeg.render(f5);
/* 110:110 */     this.bipedLeftLeg.render(f5);
/* 111:111 */     this.skirtTop.render(f5);
/* 112:112 */     this.bipedHead.render(f5);
/* 113:113 */     this.longHair.render(f5);
/* 114:114 */     this.leftBraid.render(f5);
/* 115:115 */     this.rightBraid.render(f5);
/* 116:116 */     this.leftRibbon.render(f5);
/* 117:117 */     this.rightRibbon.render(f5);
/* 118:    */   }
/* 119:    */   
/* 120:    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/* 121:    */   {
/* 122:122 */     model.rotateAngleX = x;
/* 123:123 */     model.rotateAngleY = y;
/* 124:124 */     model.rotateAngleZ = z;
/* 125:    */   }
/* 126:    */   
/* 127:    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/* 128:    */   {
/* 129:129 */     super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 130:    */   }
/* 131:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.living.ModelCursedDecoyDoll
 * JD-Core Version:    0.7.0.1
 */