/*  1:   */ package thKaguyaMod.client.render;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.renderer.Tessellator;
/*  6:   */ import net.minecraft.client.renderer.entity.Render;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.util.ResourceLocation;
/*  9:   */ import org.lwjgl.opengl.GL11;
/* 10:   */ import thKaguyaMod.client.THKaguyaRenderLib;
/* 11:   */ import thKaguyaMod.entity.EntityTHItem;
/* 12:   */ 
/* 13:   */ @SideOnly(Side.CLIENT)
/* 14:   */ public class RenderTHItem
/* 15:   */   extends Render
/* 16:   */ {
/* 17:21 */   private ResourceLocation smallPower = new ResourceLocation("thkaguyamod", "textures/items/material/SmallPowerUpItem.png");
/* 18:22 */   private ResourceLocation bigPower = new ResourceLocation("thkaguyamod", "textures/items/material/BigPowerUpItem.png");
/* 19:23 */   private ResourceLocation point = new ResourceLocation("thkaguyamod", "textures/items/material/THPointItem.png");
/* 20:24 */   private ResourceLocation bomb = new ResourceLocation("thkaguyamod", "textures/items/material/BombItem.png");
/* 21:25 */   private ResourceLocation extend = new ResourceLocation("thkaguyamod", "textures/items/material/ExtendItem.png");
/* 22:   */   
/* 23:   */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/* 24:   */   {
/* 25:33 */     renderTHItem((EntityTHItem)entity, x, y, z, yaw, pitch);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void renderTHItem(EntityTHItem item, double x, double y, double z, float yaw, float pitch)
/* 29:   */   {
/* 30:39 */     GL11.glPushMatrix();
/* 31:40 */     bindEntityTexture(item);
/* 32:41 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/* 33:42 */     GL11.glDisable(2896);
/* 34:43 */     float sizeRate = 2.0F;
/* 35:44 */     GL11.glScalef(sizeRate, sizeRate, sizeRate);
/* 36:45 */     Tessellator tessellator = Tessellator.instance;
/* 37:46 */     int color2 = 0;
/* 38:47 */     float rvl = 0.0F;
/* 39:48 */     float rul = 0.0F;
/* 40:49 */     float rvr = 1.0F;
/* 41:50 */     float rur = 1.0F;
/* 42:   */     
/* 43:52 */     THKaguyaRenderLib.getGLRotatefByTherdPersonView(this.renderManager);
/* 44:   */     
/* 45:   */ 
/* 46:55 */     tessellator.startDrawingQuads();
/* 47:56 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 48:57 */     tessellator.addVertexWithUV(0.2000000029802322D, 0.2000000029802322D, 0.0D, rvr, rul);
/* 49:58 */     tessellator.addVertexWithUV(-0.2000000029802322D, 0.2000000029802322D, 0.0D, rvl, rul);
/* 50:59 */     tessellator.addVertexWithUV(-0.2000000029802322D, -0.2000000029802322D, 0.0D, rvl, rur);
/* 51:60 */     tessellator.addVertexWithUV(0.2000000029802322D, -0.2000000029802322D, 0.0D, rvr, rur);
/* 52:   */     
/* 53:62 */     tessellator.draw();
/* 54:   */     
/* 55:64 */     GL11.glEnable(2896);
/* 56:65 */     GL11.glPopMatrix();
/* 57:   */   }
/* 58:   */   
/* 59:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 60:   */   {
/* 61:73 */     return getEntityTexture((EntityTHItem)entity);
/* 62:   */   }
/* 63:   */   
/* 64:   */   protected ResourceLocation getEntityTexture(EntityTHItem item)
/* 65:   */   {
/* 66:78 */     switch (item.getItemType())
/* 67:   */     {
/* 68:   */     case 1: 
/* 69:81 */       return this.smallPower;
/* 70:   */     case 2: 
/* 71:83 */       return this.bigPower;
/* 72:   */     case 4: 
/* 73:85 */       return this.point;
/* 74:   */     case 10: 
/* 75:87 */       return this.bomb;
/* 76:   */     case 11: 
/* 77:89 */       return this.extend;
/* 78:   */     }
/* 79:91 */     return this.smallPower;
/* 80:   */   }
/* 81:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.RenderTHItem
 * JD-Core Version:    0.7.0.1
 */