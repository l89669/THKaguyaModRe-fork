/*  1:   */ package thKaguyaMod.client.model;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.model.ModelBase;
/*  6:   */ import net.minecraft.client.model.ModelRenderer;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ 
/*  9:   */ @SideOnly(Side.CLIENT)
/* 10:   */ public class ModelPrivateSquare
/* 11:   */   extends ModelBase
/* 12:   */ {
/* 13:   */   public ModelRenderer watchBase;
/* 14:   */   public ModelRenderer watchCenter;
/* 15:   */   public ModelRenderer watchHandle;
/* 16:   */   public ModelRenderer watchCover;
/* 17:   */   
/* 18:   */   public ModelPrivateSquare()
/* 19:   */   {
/* 20:45 */     this.watchBase = new ModelRenderer(this, 0, 0);
/* 21:   */     
/* 22:   */ 
/* 23:   */ 
/* 24:   */ 
/* 25:50 */     this.watchBase.addBox(-6.0F, -6.0F, -2.0F, 12, 12, 4, 0.0F);
/* 26:   */     
/* 27:   */ 
/* 28:53 */     this.watchCenter = new ModelRenderer(this, 28, 14);
/* 29:54 */     this.watchCenter.addBox(-8.0F, -8.0F, -1.0F, 16, 16, 2, 0.0F);
/* 30:   */     
/* 31:   */ 
/* 32:57 */     this.watchHandle = new ModelRenderer(this, 32, 0);
/* 33:58 */     this.watchHandle.addBox(-4.0F, 8.0F, 0.0F, 8, 6, 0, 0.0F);
/* 34:   */     
/* 35:   */ 
/* 36:61 */     this.watchCover = new ModelRenderer(this, 48, 16);
/* 37:62 */     this.watchCover.addBox(-8.0F, -8.0F, 0.0F, 16, 16, 0, 0.0F);
/* 38:63 */     this.watchCover.setRotationPoint(0.0F, -16.0F, -4.0F);
/* 39:64 */     this.watchCover.rotateAngleX = 0.5235988F;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
/* 43:   */   {
/* 44:70 */     this.watchBase.render(size);
/* 45:71 */     this.watchCenter.render(size);
/* 46:72 */     this.watchHandle.render(size);
/* 47:73 */     this.watchCover.render(size);
/* 48:   */   }
/* 49:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.ModelPrivateSquare
 * JD-Core Version:    0.7.0.1
 */