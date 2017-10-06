/*  1:   */ package thKaguyaMod.client.model;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.model.ModelBase;
/*  6:   */ import net.minecraft.client.model.ModelRenderer;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ 
/*  9:   */ @SideOnly(Side.CLIENT)
/* 10:   */ public class ModelHisou
/* 11:   */   extends ModelBase
/* 12:   */ {
/* 13:   */   public ModelRenderer[] hisou;
/* 14:   */   
/* 15:   */   public ModelHisou()
/* 16:   */   {
/* 17:35 */     this.hisou = new ModelRenderer[3];
/* 18:   */     
/* 19:37 */     this.hisou[0] = new ModelRenderer(this, 32, 0);
/* 20:   */     
/* 21:   */ 
/* 22:   */ 
/* 23:   */ 
/* 24:42 */     this.hisou[0].addBox(-2.0F, -12.0F, -2.0F, 4, 8, 4, 0.0F);
/* 25:   */     
/* 26:   */ 
/* 27:45 */     this.hisou[1] = new ModelRenderer(this, 0, 0);
/* 28:46 */     this.hisou[1].addBox(-2.0F, -4.0F, -2.0F, 4, 28, 4, 0.0F);
/* 29:   */     
/* 30:48 */     this.hisou[2] = new ModelRenderer(this, 32, 16);
/* 31:49 */     this.hisou[2].addBox(-2.0F, -20.0F, 0.0F, 12, 12, 0, 0.0F);
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
/* 35:   */   {
/* 36:55 */     for (int i = 0; i < 3; i++) {
/* 37:57 */       this.hisou[i].render(size);
/* 38:   */     }
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.ModelHisou
 * JD-Core Version:    0.7.0.1
 */