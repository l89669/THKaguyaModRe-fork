/*  1:   */ package thKaguyaMod.client.model;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.model.ModelBase;
/*  6:   */ import net.minecraft.client.model.ModelRenderer;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.util.MathHelper;
/*  9:   */ 
/* 10:   */ @SideOnly(Side.CLIENT)
/* 11:   */ public class ModelYuukaParasol
/* 12:   */   extends ModelBase
/* 13:   */ {
/* 14:   */   public ModelRenderer[] stick;
/* 15:   */   public ModelRenderer[] handle;
/* 16:   */   
/* 17:   */   public ModelYuukaParasol()
/* 18:   */   {
/* 19:37 */     this.stick = new ModelRenderer[4];
/* 20:38 */     for (int i = 0; i < 4; i++)
/* 21:   */     {
/* 22:40 */       this.stick[i] = new ModelRenderer(this, 0, 0);
/* 23:41 */       this.stick[i].addBox(-2.0F, i * 28, -2.0F, 4, 28, 4, 0.0F);
/* 24:   */     }
/* 25:44 */     this.handle = new ModelRenderer[3];
/* 26:   */     
/* 27:46 */     this.handle[0] = new ModelRenderer(this, 32, 16);
/* 28:   */     
/* 29:   */ 
/* 30:   */ 
/* 31:   */ 
/* 32:51 */     this.handle[0].addBox(-3.0F, -10.0F, -3.0F, 6, 10, 6, 0.0F);
/* 33:   */     
/* 34:53 */     this.handle[1] = new ModelRenderer(this, 32, 16);
/* 35:54 */     this.handle[1].addBox(0.0F, 0.0F, -6.0F, 6, 10, 6, 0.0F);
/* 36:55 */     this.handle[1].setRotationPoint(-3.0F, -10.0F, -3.0F);
/* 37:56 */     this.handle[1].rotateAngleX = 2.094395F;
/* 38:   */     
/* 39:58 */     this.handle[2] = new ModelRenderer(this, 32, 16);
/* 40:59 */     this.handle[2].addBox(0.0F, 0.0F, -6.0F, 6, 10, 6, 0.0F);
/* 41:60 */     this.handle[2].setRotationPoint(-3.0F, -10.0F + MathHelper.cos(2.094395F) * 10.0F, -3.0F + MathHelper.sin(2.094395F) * 10.0F);
/* 42:61 */     this.handle[2].rotateAngleX = 1.047198F;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
/* 46:   */   {
/* 47:67 */     for (int i = 0; i < 4; i++) {
/* 48:69 */       this.stick[i].render(size);
/* 49:   */     }
/* 50:71 */     for (int i = 0; i < 3; i++) {
/* 51:73 */       this.handle[i].render(size);
/* 52:   */     }
/* 53:   */   }
/* 54:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.ModelYuukaParasol
 * JD-Core Version:    0.7.0.1
 */