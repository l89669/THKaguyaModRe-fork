/*   1:    */ package thKaguyaMod.client.render;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.renderer.Tessellator;
/*   6:    */ import net.minecraft.client.renderer.entity.Render;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.ResourceLocation;
/*   9:    */ import org.lwjgl.opengl.GL11;
/*  10:    */ import thKaguyaMod.entity.spellcard.EntitySpellCard;
/*  11:    */ import thKaguyaMod.entity.spellcard.THSpellCard;
/*  12:    */ import thKaguyaMod.registry.SpellCardRegistry;
/*  13:    */ 
/*  14:    */ @SideOnly(Side.CLIENT)
/*  15:    */ public class RenderSpellCard
/*  16:    */   extends Render
/*  17:    */ {
/*  18:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  19:    */   {
/*  20: 28 */     renderSpellCard((EntitySpellCard)entity, x, y, z, yaw, pitch);
/*  21:    */   }
/*  22:    */   
/*  23:    */   public void renderSpellCard(EntitySpellCard spellCard, double x, double y, double z, float yaw, float pitch)
/*  24:    */   {
/*  25: 34 */     GL11.glPushMatrix();
/*  26: 35 */     bindEntityTexture(spellCard);
/*  27: 36 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  28: 37 */     GL11.glDisable(2896);
/*  29: 38 */     float sizeRate = 1.0F;
/*  30: 39 */     GL11.glScalef(sizeRate, sizeRate, sizeRate);
/*  31: 40 */     Tessellator tessellator = Tessellator.instance;
/*  32: 41 */     int color2 = 0;
/*  33: 42 */     float rvl = 0.109375F;
/*  34: 43 */     float rul = 0.0F;
/*  35: 44 */     float rvr = 0.890625F;
/*  36: 45 */     float rur = 1.0F;
/*  37: 46 */     float fvl = 0.25F;
/*  38: 47 */     float ful = 0.0F;
/*  39: 48 */     float fvr = 0.5F;
/*  40: 49 */     float fur = 0.75F;
/*  41: 50 */     GL11.glRotatef(spellCard.ticksExisted * 21.0F, 0.0F, 1.0F, 0.0F);
/*  42: 51 */     GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
/*  43:    */     
/*  44:    */ 
/*  45: 54 */     tessellator.startDrawingQuads();
/*  46: 55 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  47: 56 */     tessellator.addVertexWithUV(0.2000000029802322D, 0.300000011920929D, 0.0D, rvl, rul);
/*  48: 57 */     tessellator.addVertexWithUV(-0.2000000029802322D, 0.300000011920929D, 0.0D, rvr, rul);
/*  49: 58 */     tessellator.addVertexWithUV(-0.2000000029802322D, -0.300000011920929D, 0.0D, rvr, rur);
/*  50: 59 */     tessellator.addVertexWithUV(0.2000000029802322D, -0.300000011920929D, 0.0D, rvl, rur);
/*  51: 60 */     tessellator.draw();
/*  52:    */     
/*  53: 62 */     tessellator.startDrawingQuads();
/*  54: 63 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  55: 64 */     tessellator.addVertexWithUV(-0.2000000029802322D, 0.300000011920929D, 0.0D, rvl, rul);
/*  56: 65 */     tessellator.addVertexWithUV(0.2000000029802322D, 0.300000011920929D, 0.0D, rvr, rul);
/*  57: 66 */     tessellator.addVertexWithUV(0.2000000029802322D, -0.300000011920929D, 0.0D, rvr, rur);
/*  58: 67 */     tessellator.addVertexWithUV(-0.2000000029802322D, -0.300000011920929D, 0.0D, rvl, rur);
/*  59: 68 */     tessellator.draw();
/*  60:    */     
/*  61: 70 */     bindTexture(getFrameTexture(spellCard));
/*  62:    */     
/*  63:    */ 
/*  64: 73 */     tessellator.startDrawingQuads();
/*  65: 74 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  66: 75 */     tessellator.addVertexWithUV(0.2000000029802322D, 0.300000011920929D, 0.001D, rvl, rul);
/*  67: 76 */     tessellator.addVertexWithUV(-0.2000000029802322D, 0.300000011920929D, 0.001D, rvr, rul);
/*  68: 77 */     tessellator.addVertexWithUV(-0.2000000029802322D, -0.300000011920929D, 0.001D, rvr, rur);
/*  69: 78 */     tessellator.addVertexWithUV(0.2000000029802322D, -0.300000011920929D, 0.001D, rvl, rur);
/*  70: 79 */     tessellator.draw();
/*  71:    */     
/*  72: 81 */     tessellator.startDrawingQuads();
/*  73: 82 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  74: 83 */     tessellator.addVertexWithUV(-0.2000000029802322D, 0.300000011920929D, -0.001D, rvl, rul);
/*  75: 84 */     tessellator.addVertexWithUV(0.2000000029802322D, 0.300000011920929D, -0.001D, rvr, rul);
/*  76: 85 */     tessellator.addVertexWithUV(0.2000000029802322D, -0.300000011920929D, -0.001D, rvr, rur);
/*  77: 86 */     tessellator.addVertexWithUV(-0.2000000029802322D, -0.300000011920929D, -0.001D, rvl, rur);
/*  78: 87 */     tessellator.draw();
/*  79:    */     
/*  80: 89 */     GL11.glEnable(2896);
/*  81: 90 */     GL11.glPopMatrix();
/*  82:    */   }
/*  83:    */   
/*  84:    */   protected ResourceLocation getEntityTexture(Entity entity)
/*  85:    */   {
/*  86: 98 */     return getEntityTexture((EntitySpellCard)entity);
/*  87:    */   }
/*  88:    */   
/*  89:    */   protected ResourceLocation getEntityTexture(EntitySpellCard spellCard)
/*  90:    */   {
/*  91:103 */     String domain = "thkaguyamod";
/*  92:104 */     domain = SpellCardRegistry.getSpellCardModDomain(spellCard.getSpellCardNumber());
/*  93:105 */     return new ResourceLocation(domain, "textures/items/spellCard/" + SpellCardRegistry.getSpellCardName(spellCard.getSpellCardNumber()) + ".png");
/*  94:    */   }
/*  95:    */   
/*  96:    */   protected ResourceLocation getFrameTexture(EntitySpellCard spellCard)
/*  97:    */   {
/*  98:110 */     String domain = "thkaguyamod";
/*  99:111 */     domain = SpellCardRegistry.getSpellCardModDomain(spellCard.getSpellCardNumber());
/* 100:112 */     return new ResourceLocation(domain, "textures/items/spellCard/spellcard_lv" + getLevel(spellCard.getSpellCardNumber()) + ".png");
/* 101:    */   }
/* 102:    */   
/* 103:    */   protected int getLevel(int spellCardID)
/* 104:    */   {
/* 105:122 */     int needLevel = 1;
/* 106:    */     Class<?> spellcard;
/* 107:126 */     if ((spellcard = SpellCardRegistry.getSpellCardClass(spellCardID)) != null) {
/* 108:    */       try
/* 109:    */       {
/* 110:129 */         THSpellCard useSpellCard = (THSpellCard)spellcard.newInstance();
/* 111:    */         
/* 112:131 */         needLevel = useSpellCard.getNeedLevel();
/* 113:    */       }
/* 114:    */       catch (InstantiationException e)
/* 115:    */       {
/* 116:134 */         e.printStackTrace();
/* 117:    */       }
/* 118:    */       catch (IllegalAccessException e)
/* 119:    */       {
/* 120:136 */         e.printStackTrace();
/* 121:    */       }
/* 122:    */     }
/* 123:140 */     return needLevel;
/* 124:    */   }
/* 125:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.RenderSpellCard
 * JD-Core Version:    0.7.0.1
 */