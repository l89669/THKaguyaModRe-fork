/*  1:   */ package thKaguyaMod.client.model;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.model.ModelBase;
/*  6:   */ import net.minecraft.client.model.ModelRenderer;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ 
/*  9:   */ @SideOnly(Side.CLIENT)
/* 10:   */ public class ModelSilverKnife
/* 11:   */   extends ModelBase
/* 12:   */ {
/* 13:   */   public ModelRenderer[] knife;
/* 14:   */   
/* 15:   */   public ModelSilverKnife()
/* 16:   */   {
/* 17:37 */     this.knife = new ModelRenderer[3];
/* 18:   */     
/* 19:39 */     this.knife[0] = new ModelRenderer(this, 0, 0);
/* 20:   */     
/* 21:   */ 
/* 22:   */ 
/* 23:   */ 
/* 24:44 */     this.knife[0].addBox(-4.0F, -5.0F, -1.0F, 7, 1, 3, 0.0F);
/* 25:45 */     this.knife[0].rotateAngleX = 1.570796F;
/* 26:   */     
/* 27:   */ 
/* 28:48 */     this.knife[1] = new ModelRenderer(this, 0, 16);
/* 29:49 */     this.knife[1].addBox(-2.0F, -9.0F, 0.0F, 3, 14, 1, 0.0F);
/* 30:50 */     this.knife[1].rotateAngleX = 1.570796F;
/* 31:   */     
/* 32:   */ 
/* 33:53 */     this.knife[2] = new ModelRenderer(this, 32, 16);
/* 34:54 */     this.knife[2].addBox(-1.0F, 5.0F, 0.0F, 1, 1, 1, 0.0F);
/* 35:55 */     this.knife[2].rotateAngleX = 1.570796F;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
/* 39:   */   {
/* 40:62 */     for (int i = 0; i < 3; i++) {
/* 41:64 */       this.knife[i].render(size);
/* 42:   */     }
/* 43:   */   }
/* 44:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.ModelSilverKnife
 * JD-Core Version:    0.7.0.1
 */