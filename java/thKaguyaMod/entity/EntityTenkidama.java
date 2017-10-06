/*  1:   */ package thKaguyaMod.entity;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import java.util.Random;
/*  6:   */ import net.minecraft.entity.item.EntityXPOrb;
/*  7:   */ import net.minecraft.entity.player.EntityPlayer;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class EntityTenkidama
/* 11:   */   extends EntityXPOrb
/* 12:   */ {
/* 13:   */   private int xpValue;
/* 14:   */   private int xpTargetColor;
/* 15:   */   
/* 16:   */   public EntityTenkidama(World world, double x, double y, double z, int size)
/* 17:   */   {
/* 18:27 */     super(world);
/* 19:28 */     setSize(0.5F, 0.5F);
/* 20:29 */     this.yOffset = (this.height / 2.0F);
/* 21:30 */     setPosition(x, y, z);
/* 22:31 */     this.rotationYaw = ((float)(Math.random() * 360.0D));
/* 23:32 */     this.motionX = ((float)(Math.random() * 0.2000000029802322D - 0.1000000014901161D) * 2.0F);
/* 24:33 */     this.motionY = ((float)(Math.random() * 0.2D) * 2.0F);
/* 25:34 */     this.motionZ = ((float)(Math.random() * 0.2000000029802322D - 0.1000000014901161D) * 2.0F);
/* 26:35 */     this.xpValue = size;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public EntityTenkidama(World par1World)
/* 30:   */   {
/* 31:40 */     super(par1World);
/* 32:41 */     setSize(0.25F, 0.25F);
/* 33:42 */     this.yOffset = (this.height / 2.0F);
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
/* 37:   */   {
/* 38:50 */     if (!this.worldObj.isRemote) {
/* 39:52 */       if ((this.field_70532_c == 0) && (par1EntityPlayer.xpCooldown == 0))
/* 40:   */       {
/* 41:54 */         par1EntityPlayer.xpCooldown = 2;
/* 42:55 */         playSound("random.orb", 0.1F, 0.5F * ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.8F));
/* 43:56 */         par1EntityPlayer.onItemPickup(this, 1);
/* 44:57 */         par1EntityPlayer.addExperience(this.xpValue);
/* 45:58 */         setDead();
/* 46:   */       }
/* 47:   */     }
/* 48:   */   }
/* 49:   */   
/* 50:   */   @SideOnly(Side.CLIENT)
/* 51:   */   public int getTextureByXP()
/* 52:   */   {
/* 53:71 */     return this.xpValue >= 3 ? 1 : this.xpValue >= 7 ? 2 : this.xpValue >= 17 ? 3 : this.xpValue >= 37 ? 4 : this.xpValue >= 73 ? 5 : this.xpValue >= 149 ? 6 : this.xpValue >= 307 ? 7 : this.xpValue >= 617 ? 8 : this.xpValue >= 1237 ? 9 : this.xpValue >= 2477 ? 10 : 0;
/* 54:   */   }
/* 55:   */   
/* 56:   */   public static int getXPSplit(int par0)
/* 57:   */   {
/* 58:79 */     return par0 >= 3 ? 3 : par0 >= 7 ? 7 : par0 >= 17 ? 17 : par0 >= 37 ? 37 : par0 >= 73 ? 73 : par0 >= 149 ? 149 : par0 >= 307 ? 307 : par0 >= 617 ? 617 : par0 >= 1237 ? 1237 : par0 >= 2477 ? 2477 : 1;
/* 59:   */   }
/* 60:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.EntityTenkidama
 * JD-Core Version:    0.7.0.1
 */