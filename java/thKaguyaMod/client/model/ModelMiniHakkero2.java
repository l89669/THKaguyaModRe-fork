/*  1:   */ package thKaguyaMod.client.model;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.model.ModelBase;
/*  6:   */ import net.minecraft.client.model.ModelRenderer;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ 
/*  9:   */ @SideOnly(Side.CLIENT)
/* 10:   */ public class ModelMiniHakkero2
/* 11:   */   extends ModelBase
/* 12:   */ {
/* 13:   */   public ModelRenderer[] miniHakkero;
/* 14:   */   
/* 15:   */   public ModelMiniHakkero2()
/* 16:   */   {
/* 17:35 */     this.miniHakkero = new ModelRenderer[8];
/* 18:   */     
/* 19:37 */     float pi8 = 0.7853982F;
/* 20:38 */     float angle = 0.0F;
/* 21:40 */     for (int i = 0; i < 8; i++)
/* 22:   */     {
/* 23:42 */       this.miniHakkero[i] = new ModelRenderer(this, 32, 0);
/* 24:   */       
/* 25:   */ 
/* 26:45 */       this.miniHakkero[i].addBox(-4.0F, -10.0F, -0.001F, 8, 2, 6, 0.0F);
/* 27:   */       
/* 28:   */ 
/* 29:48 */       this.miniHakkero[i].setRotationPoint(0.0F, 0.0F, 0.0F);
/* 30:49 */       this.miniHakkero[i].rotateAngleZ = angle;
/* 31:50 */       angle += pi8;
/* 32:   */     }
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
/* 36:   */   {
/* 37:57 */     for (int i = 0; i < 8; i++) {
/* 38:59 */       this.miniHakkero[i].render(size);
/* 39:   */     }
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void setRotationAngles(float movement, float far, float tick, float yaw, float pitch, float size, Entity entity) {}
/* 43:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.ModelMiniHakkero2
 * JD-Core Version:    0.7.0.1
 */