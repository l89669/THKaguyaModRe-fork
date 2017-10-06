/*  1:   */ package thKaguyaMod.entity.living;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.block.Block;
/*  5:   */ import net.minecraft.entity.EntityAgeable;
/*  6:   */ import net.minecraft.entity.passive.EntityVillager;
/*  7:   */ import net.minecraft.util.AxisAlignedBB;
/*  8:   */ import net.minecraft.util.MathHelper;
/*  9:   */ import net.minecraft.world.World;
/* 10:   */ import thKaguyaMod.THKaguyaCore;
/* 11:   */ 
/* 12:   */ public class EntityRinnosuke
/* 13:   */   extends EntityVillager
/* 14:   */ {
/* 15:   */   public EntityRinnosuke(World world)
/* 16:   */   {
/* 17:15 */     super(world);
/* 18:16 */     setProfession(THKaguyaCore.villagerRinnosukeId);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public EntityRinnosuke(World world, int profession)
/* 22:   */   {
/* 23:21 */     super(world, THKaguyaCore.villagerRinnosukeId);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public int getProfession()
/* 27:   */   {
/* 28:26 */     return THKaguyaCore.villagerRinnosukeId;
/* 29:   */   }
/* 30:   */   
/* 31:   */   protected boolean canDespawn()
/* 32:   */   {
/* 33:33 */     return true;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public int getMaxSpawnedInChunk()
/* 37:   */   {
/* 38:40 */     return 1;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public EntityVillager createChild(EntityAgeable p_90011_1_)
/* 42:   */   {
/* 43:46 */     return null;
/* 44:   */   }
/* 45:   */   
/* 46:   */   public boolean isMating()
/* 47:   */   {
/* 48:52 */     return false;
/* 49:   */   }
/* 50:   */   
/* 51:   */   public boolean getCanSpawnHere()
/* 52:   */   {
/* 53:59 */     if (!super.getCanSpawnHere()) {
/* 54:61 */       return false;
/* 55:   */     }
/* 56:64 */     if (this.rand.nextInt(100) < 98) {
/* 57:66 */       return false;
/* 58:   */     }
/* 59:69 */     int yPosition = MathHelper.floor_double(this.boundingBox.minY);
/* 60:70 */     int xPosition = MathHelper.floor_double(this.posX);
/* 61:71 */     int zPosition = MathHelper.floor_double(this.posZ);
/* 62:72 */     Block pointBlock = this.worldObj.getBlock(xPosition, yPosition - 1, zPosition);
/* 63:75 */     if ((this.worldObj.getBlock(xPosition - 1, yPosition - 1, zPosition) == pointBlock) && 
/* 64:76 */       (this.worldObj.getBlock(xPosition + 1, yPosition - 1, zPosition) == pointBlock) && 
/* 65:77 */       (this.worldObj.getBlock(xPosition, yPosition - 1, zPosition - 1) == pointBlock) && 
/* 66:78 */       (this.worldObj.getBlock(xPosition, yPosition - 1, zPosition + 1) == pointBlock)) {
/* 67:80 */       return false;
/* 68:   */     }
/* 69:83 */     return true;
/* 70:   */   }
/* 71:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.living.EntityRinnosuke
 * JD-Core Version:    0.7.0.1
 */