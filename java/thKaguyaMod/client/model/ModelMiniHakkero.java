/*  1:   */ package thKaguyaMod.client.model;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.model.ModelBase;
/*  6:   */ import net.minecraft.client.model.ModelRenderer;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ 
/*  9:   */ @SideOnly(Side.CLIENT)
/* 10:   */ public class ModelMiniHakkero
/* 11:   */   extends ModelBase
/* 12:   */ {
/* 13:   */   public ModelRenderer[] miniHakkero;
/* 14:   */   
/* 15:   */   public ModelMiniHakkero()
/* 16:   */   {
/* 17:36 */     this.miniHakkero = new ModelRenderer[2];
/* 18:   */     
/* 19:38 */     this.miniHakkero[0] = new ModelRenderer(this, 0, 15);
/* 20:   */     
/* 21:   */ 
/* 22:   */ 
/* 23:   */ 
/* 24:43 */     this.miniHakkero[0].addBox(-8.0F, -8.0F, 0.0F, 16, 16, 1, 0.0F);
/* 25:44 */     this.miniHakkero[1] = new ModelRenderer(this, 0, 15);
/* 26:45 */     this.miniHakkero[1].addBox(-8.0F, -8.0F, 5.0F, 16, 16, 1, 0.0F);
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
/* 30:   */   {
/* 31:51 */     for (int i = 0; i < 2; i++) {
/* 32:53 */       this.miniHakkero[i].render(size);
/* 33:   */     }
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void setRotationAngles(float movement, float far, float tick, float yaw, float pitch, float size, Entity entity) {}
/* 37:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.ModelMiniHakkero
 * JD-Core Version:    0.7.0.1
 */