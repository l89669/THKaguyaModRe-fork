/*  1:   */ package thKaguyaMod.client.model;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.model.ModelBase;
/*  6:   */ import net.minecraft.client.model.ModelRenderer;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ 
/*  9:   */ @SideOnly(Side.CLIENT)
/* 10:   */ public class ModelKinkakuzi
/* 11:   */   extends ModelBase
/* 12:   */ {
/* 13:   */   public ModelRenderer kinkakuzi;
/* 14:   */   
/* 15:   */   public ModelKinkakuzi()
/* 16:   */   {
/* 17:36 */     this.kinkakuzi = new ModelRenderer(this, 0, 0);
/* 18:   */     
/* 19:   */ 
/* 20:39 */     this.kinkakuzi.addBox(-15.0F, -15.0F, -2.0F, 30, 30, 2, 0.0F);
/* 21:40 */     this.kinkakuzi.rotateAngleX = 1.570796F;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
/* 25:   */   {
/* 26:46 */     this.kinkakuzi.render(size);
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void setRotationAngles(float movement, float far, float tick, float yaw, float pitch, float size, Entity entity) {}
/* 30:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.ModelKinkakuzi
 * JD-Core Version:    0.7.0.1
 */