/*   1:    */ package thKaguyaMod.gui;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import java.util.Iterator;
/*   6:    */ import java.util.List;
/*   7:    */ import net.minecraft.client.Minecraft;
/*   8:    */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*   9:    */ import net.minecraft.client.gui.GuiButton;
/*  10:    */ import net.minecraft.client.gui.GuiMainMenu;
/*  11:    */ import net.minecraft.client.gui.GuiScreen;
/*  12:    */ import net.minecraft.client.multiplayer.WorldClient;
/*  13:    */ import net.minecraft.client.resources.I18n;
/*  14:    */ import net.minecraft.entity.player.EntityPlayer;
/*  15:    */ import net.minecraft.util.EnumChatFormatting;
/*  16:    */ import net.minecraft.util.ResourceLocation;
/*  17:    */ import net.minecraft.world.World;
/*  18:    */ import net.minecraft.world.storage.WorldInfo;
/*  19:    */ import org.lwjgl.opengl.GL11;
/*  20:    */ 
/*  21:    */ @SideOnly(Side.CLIENT)
/*  22:    */ public class GuiSpellCardMode
/*  23:    */   extends GuiScreen
/*  24:    */ {
/*  25: 24 */   private static final ResourceLocation bg_texture = new ResourceLocation("thkaguyamod", "textures/gui/danmaku_crafting_table.png");
/*  26:    */   private int field_146347_a;
/*  27: 36 */   private boolean field_146346_f = false;
/*  28:    */   private static final String __OBFID = "CL_00000690";
/*  29:    */   
/*  30:    */   public GuiSpellCardMode(EntityPlayer player, World world, int x, int y, int z) {}
/*  31:    */   
/*  32:    */   public void initGui()
/*  33:    */   {
/*  34: 44 */     this.buttonList.clear();
/*  35: 48 */     if (this.mc.isIntegratedServerRunning()) {
/*  36: 50 */       this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, I18n.format("deathScreen.deleteWorld", new Object[0])));
/*  37:    */     } else {
/*  38: 54 */       this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, I18n.format("deathScreen.leaveServer", new Object[0])));
/*  39:    */     }
/*  40: 59 */     this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 72, I18n.format("deathScreen.respawn", new Object[0])));
/*  41: 60 */     this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, I18n.format("deathScreen.titleScreen", new Object[0])));
/*  42: 62 */     if (this.mc.getSession() == null) {
/*  43: 64 */       ((GuiButton)this.buttonList.get(1)).enabled = false;
/*  44:    */     }
/*  45:    */     GuiButton guibutton;
/*  46: 70 */     for (Iterator iterator = this.buttonList.iterator(); iterator.hasNext(); guibutton.enabled = false) {
/*  47: 72 */       guibutton = (GuiButton)iterator.next();
/*  48:    */     }
/*  49:    */   }
/*  50:    */   
/*  51:    */   protected void keyTyped(char par1, int par2) {}
/*  52:    */   
/*  53:    */   protected void actionPerformed(GuiButton p_146284_1_)
/*  54:    */   {
/*  55: 83 */     switch (p_146284_1_.id)
/*  56:    */     {
/*  57:    */     case 0: 
/*  58: 86 */       this.mc.thePlayer.respawnPlayer();
/*  59: 87 */       this.mc.displayGuiScreen((GuiScreen)null);
/*  60: 88 */       break;
/*  61:    */     }
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void confirmClicked(boolean par1, int par2)
/*  65:    */   {
/*  66: 98 */     if (par1)
/*  67:    */     {
/*  68:100 */       this.mc.theWorld.sendQuittingDisconnectingPacket();
/*  69:101 */       this.mc.loadWorld((WorldClient)null);
/*  70:102 */       this.mc.displayGuiScreen(new GuiMainMenu());
/*  71:    */     }
/*  72:    */     else
/*  73:    */     {
/*  74:106 */       this.mc.thePlayer.respawnPlayer();
/*  75:107 */       this.mc.displayGuiScreen((GuiScreen)null);
/*  76:    */     }
/*  77:    */   }
/*  78:    */   
/*  79:    */   public void drawScreen(int par1, int par2, float par3)
/*  80:    */   {
/*  81:116 */     drawGradientRect(0, 0, this.width, this.height, 1615855616, -1602211792);
/*  82:117 */     GL11.glPushMatrix();
/*  83:118 */     GL11.glScalef(2.0F, 2.0F, 2.0F);
/*  84:119 */     boolean flag = this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled();
/*  85:120 */     String s = flag ? I18n.format("deathScreen.title.hardcore", new Object[0]) : I18n.format("deathScreen.title", new Object[0]);
/*  86:121 */     drawCenteredString(this.fontRendererObj, s, this.width / 2 / 2, 30, 16777215);
/*  87:122 */     GL11.glPopMatrix();
/*  88:124 */     if (flag) {
/*  89:126 */       drawCenteredString(this.fontRendererObj, I18n.format("deathScreen.hardcoreInfo", new Object[0]), this.width / 2, 144, 16777215);
/*  90:    */     }
/*  91:129 */     drawCenteredString(this.fontRendererObj, I18n.format("deathScreen.score", new Object[0]) + ": " + EnumChatFormatting.YELLOW + this.mc.thePlayer.getScore(), this.width / 2, 100, 16777215);
/*  92:130 */     super.drawScreen(par1, par2, par3);
/*  93:    */   }
/*  94:    */   
/*  95:    */   public boolean doesGuiPauseGame()
/*  96:    */   {
/*  97:138 */     return false;
/*  98:    */   }
/*  99:    */   
/* 100:    */   public void updateScreen()
/* 101:    */   {
/* 102:146 */     super.updateScreen();
/* 103:147 */     this.field_146347_a += 1;
/* 104:150 */     if (this.field_146347_a == 20)
/* 105:    */     {
/* 106:    */       GuiButton guibutton;
/* 107:152 */       for (Iterator iterator = this.buttonList.iterator(); iterator.hasNext(); guibutton.enabled = true) {
/* 108:154 */         guibutton = (GuiButton)iterator.next();
/* 109:    */       }
/* 110:    */     }
/* 111:    */   }
/* 112:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.gui.GuiSpellCardMode
 * JD-Core Version:    0.7.0.1
 */