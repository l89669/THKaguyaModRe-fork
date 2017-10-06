/*   1:    */ package thKaguyaMod.entity.living;
/*   2:    */ 
/*   3:    */ import java.util.Random;
/*   4:    */ import net.minecraft.entity.IEntityLivingData;
/*   5:    */ import net.minecraft.item.Item;
/*   6:    */ import net.minecraft.world.World;
/*   7:    */ import thKaguyaMod.init.THKaguyaItems;
/*   8:    */ 
/*   9:    */ public class EntitySunFlowerFairy
/*  10:    */   extends EntityTHFairy
/*  11:    */ {
/*  12:    */   public EntitySunFlowerFairy(World world)
/*  13:    */   {
/*  14: 17 */     super(world);
/*  15:    */     
/*  16: 19 */     setSize(0.9F, 1.7F);
/*  17:    */     
/*  18: 21 */     this.experienceValue = 30;
/*  19:    */     
/*  20: 23 */     setForm((byte)10);
/*  21: 24 */     setMaxHP(20.0F);
/*  22: 25 */     setHealth(20.0F);
/*  23:    */   }
/*  24:    */   
/*  25:    */   public IEntityLivingData onSpawnWithEgg(IEntityLivingData entityLivingData)
/*  26:    */   {
/*  27: 31 */     Object p_110161_1_1 = super.onSpawnWithEgg(entityLivingData);
/*  28:    */     
/*  29:    */ 
/*  30: 34 */     EntityFamiliar familiar = new EntityFamiliar(this.worldObj);
/*  31: 35 */     familiar.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
/*  32:    */     
/*  33: 37 */     this.worldObj.spawnEntityInWorld(familiar);
/*  34:    */     
/*  35: 39 */     familiar.mountEntity(this);
/*  36:    */     
/*  37: 41 */     return (IEntityLivingData)p_110161_1_1;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public void onUpdate()
/*  41:    */   {
/*  42: 61 */     if (getHealth() <= 0.0F)
/*  43:    */     {
/*  44: 63 */       this.motionX = 0.0D;
/*  45: 64 */       this.motionY = 0.0D;
/*  46: 65 */       this.motionZ = 0.0D;
/*  47:    */     }
/*  48: 68 */     if (this.ticksExisted <= this.lastTime) {
/*  49: 70 */       return;
/*  50:    */     }
/*  51: 74 */     super.onUpdate();
/*  52: 75 */     if (this.attackCounter > this.danmakuSpan) {
/*  53: 77 */       this.attackCounter = 0;
/*  54:    */     }
/*  55:    */   }
/*  56:    */   
/*  57:    */   protected Item getDropItem()
/*  58:    */   {
/*  59: 86 */     if (getForm() < 0) {
/*  60: 88 */       return null;
/*  61:    */     }
/*  62: 92 */     return THKaguyaItems.power_item;
/*  63:    */   }
/*  64:    */   
/*  65:    */   protected void dropFewItems(boolean hasBeenAttackedByPlayer, int lootingLevel)
/*  66:    */   {
/*  67:100 */     super.dropFewItems(hasBeenAttackedByPlayer, lootingLevel);
/*  68:102 */     if (getForm() < 0) {
/*  69:104 */       return;
/*  70:    */     }
/*  71:107 */     if (hasBeenAttackedByPlayer)
/*  72:    */     {
/*  73:110 */       dropShotItem(2, this.rand.nextInt(12) + lootingLevel * 2, 7, 8, 2, 0, 0, 2);
/*  74:    */       
/*  75:112 */       dropPowerUpItem(this.rand.nextInt(20) + this.rand.nextInt(3 + lootingLevel * 2));
/*  76:    */       
/*  77:114 */       dropPointItem(this.rand.nextInt(20) + this.rand.nextInt(3 + lootingLevel * 2));
/*  78:117 */       if (this.rand.nextInt(100) < 5) {
/*  79:119 */         dropExtendItem(pos(), angle(this.rotationYaw, -90.0F));
/*  80:122 */       } else if (this.rand.nextInt(100) < 25) {
/*  81:124 */         dropSpellCardItem(pos(), angle(this.rotationYaw, -90.0F));
/*  82:    */       }
/*  83:    */     }
/*  84:    */   }
/*  85:    */   
/*  86:    */   public int getMaxSpawnedInChunk()
/*  87:    */   {
/*  88:135 */     return 3;
/*  89:    */   }
/*  90:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.living.EntitySunFlowerFairy
 * JD-Core Version:    0.7.0.1
 */