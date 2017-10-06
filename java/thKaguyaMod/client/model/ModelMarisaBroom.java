/*  1:   */ package thKaguyaMod.client.model;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.model.ModelBase;
/*  6:   */ import net.minecraft.client.model.ModelRenderer;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ 
/*  9:   */ @SideOnly(Side.CLIENT)
/* 10:   */ public class ModelMarisaBroom
/* 11:   */   extends ModelBase
/* 12:   */ {
/* 13:12 */   public ModelRenderer[] broom = new ModelRenderer[5];
/* 14:   */   
/* 15:   */   public ModelMarisaBroom()
/* 16:   */   {
/* 17:16 */     this.broom[0] = new ModelRenderer(this, 0, 8);
/* 18:17 */     this.broom[1] = new ModelRenderer(this, 0, 0);
/* 19:18 */     this.broom[2] = new ModelRenderer(this, 0, 0);
/* 20:19 */     this.broom[3] = new ModelRenderer(this, 0, 0);
/* 21:20 */     this.broom[4] = new ModelRenderer(this, 0, 0);
/* 22:21 */     byte b0 = 24;
/* 23:22 */     byte b1 = 6;
/* 24:23 */     byte b2 = 20;
/* 25:24 */     byte b3 = 4;
/* 26:25 */     this.broom[0].addBox(-b0 / 2, -b2 / 2 + 2, -3.0F, b0, b2 - 4, 4, 0.0F);
/* 27:26 */     this.broom[0].setRotationPoint(0.0F, b3, 0.0F);
/* 28:27 */     this.broom[1].addBox(-b0 / 2 + 2, -b1 - 1, -1.0F, b0 - 4, b1, 2, 0.0F);
/* 29:28 */     this.broom[1].setRotationPoint(-b0 / 2 + 1, b3, 0.0F);
/* 30:29 */     this.broom[2].addBox(-b0 / 2 + 2, -b1 - 1, -1.0F, b0 - 4, b1, 2, 0.0F);
/* 31:30 */     this.broom[2].setRotationPoint(b0 / 2 - 1, b3, 0.0F);
/* 32:31 */     this.broom[3].addBox(-b0 / 2 + 2, -b1 - 1, -1.0F, b0 - 4, b1, 2, 0.0F);
/* 33:32 */     this.broom[3].setRotationPoint(0.0F, b3, -b2 / 2 + 1);
/* 34:33 */     this.broom[4].addBox(-b0 / 2 + 2, -b1 - 1, -1.0F, b0 - 4, b1, 2, 0.0F);
/* 35:34 */     this.broom[4].setRotationPoint(0.0F, b3, b2 / 2 - 1);
/* 36:35 */     this.broom[0].rotateAngleX = 1.570796F;
/* 37:36 */     this.broom[1].rotateAngleY = 4.712389F;
/* 38:37 */     this.broom[2].rotateAngleY = 1.570796F;
/* 39:38 */     this.broom[3].rotateAngleY = 3.141593F;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
/* 43:   */   {
/* 44:44 */     for (int i = 0; i < 5; i++) {
/* 45:46 */       this.broom[i].render(size);
/* 46:   */     }
/* 47:   */   }
/* 48:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.ModelMarisaBroom
 * JD-Core Version:    0.7.0.1
 */