/*   1:    */ package thKaguyaMod.entity.spellcard;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import java.util.Random;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ import net.minecraft.entity.EntityLivingBase;
/*   7:    */ import net.minecraft.util.AxisAlignedBB;
/*   8:    */ import net.minecraft.util.MovingObjectPosition;
/*   9:    */ import net.minecraft.util.Vec3;
/*  10:    */ import net.minecraft.world.World;
/*  11:    */ import thKaguyaMod.THShotLib;
/*  12:    */ import thKaguyaMod.entity.shot.EntityHomingAmulet;
/*  13:    */ import thKaguyaMod.entity.shot.EntityMusouFuuin;
/*  14:    */ import thKaguyaMod.entity.shot.EntityTHLaser;
/*  15:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  16:    */ import thKaguyaMod.entity.shot.ISpecialShot;
/*  17:    */ import thKaguyaMod.registry.SpecialShotRegistry;
/*  18:    */ 
/*  19:    */ public class THSC_PerfectFreeze
/*  20:    */   extends THSpellCard
/*  21:    */   implements ISpecialShot
/*  22:    */ {
/*  23:    */   public static final int SPECIAL_FREEZE = 900;
/*  24:    */   
/*  25:    */   public THSC_PerfectFreeze()
/*  26:    */   {
/*  27: 28 */     setNeedLevel(1);
/*  28: 29 */     setRemoveTime(20);
/*  29: 30 */     setEndTime(110);
/*  30: 31 */     setOriginalUserName(4);
/*  31:    */     
/*  32:    */ 
/*  33: 34 */     SpecialShotRegistry.registerSpecialShot(THSC_PerfectFreeze.class, 900);
/*  34:    */   }
/*  35:    */   
/*  36:    */   public void spellcard_main()
/*  37:    */   {
/*  38: 40 */     if (this.time < 5) {
/*  39: 42 */       return;
/*  40:    */     }
/*  41: 46 */     if (this.time < 58)
/*  42:    */     {
/*  43: 48 */       int num = 4 + this.level;
/*  44: 49 */       if (num > 10) {
/*  45: 49 */         num = 10;
/*  46:    */       }
/*  47: 51 */       for (int i = 0; i < num; i++)
/*  48:    */       {
/*  49: 53 */         Vec3 angle = THShotLib.angle_Random();
/*  50: 54 */         THShotLib.createShot(this.user, this.user, pos_User(angle, 1.0D), angle, 0.0F, this.level * 0.1D, 0.5D, 0.03D, gravity_Zero(), shot(4, 9, THShotLib.SIZE[4], 4.0F, 5, 120));
/*  51:    */       }
/*  52: 56 */       THShotLib.playShotSound(this.user);
/*  53:    */     }
/*  54: 60 */     if (this.time == 64)
/*  55:    */     {
/*  56: 62 */       List list = this.world.getEntitiesWithinAABBExcludingEntity(this.user, this.user.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(20.0D, 20.0D, 20.0D));
/*  57: 63 */       for (int k = 0; k < list.size(); k++)
/*  58:    */       {
/*  59: 65 */         Entity entity = (Entity)list.get(k);
/*  60: 66 */         if (((entity instanceof EntityTHShot)) && (!(entity instanceof EntityTHLaser))) {
/*  61: 68 */           if ((!(entity instanceof EntityHomingAmulet)) && (!(entity instanceof EntityMusouFuuin)))
/*  62:    */           {
/*  63: 70 */             EntityTHShot shot = (EntityTHShot)entity;
/*  64: 71 */             THShotLib.createShot(this.user, this.user, THShotLib.pos_Entity(shot), getVecFromAngle(this.rand.nextFloat() * 360.0F, this.rand.nextFloat() * 180.0F - 90.0F), 0.0F, 0.0001D, 0.0001D, 0.0D, 
/*  65: 72 */               gravity_Zero(), shot(shot.getShotForm(), 7, shot.getShotSize(), shot.shotDamage, 0, shot.getShotEndTime() - shot.ticksExisted + 50, 900));
/*  66: 73 */             shot.setDead();
/*  67:    */           }
/*  68:    */         }
/*  69:    */       }
/*  70:    */     }
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void specialShot_move(World world, int id, EntityTHShot shot)
/*  74:    */   {
/*  75: 83 */     switch (id)
/*  76:    */     {
/*  77:    */     case 900: 
/*  78: 86 */       if (shot.ticksExisted >= 20)
/*  79:    */       {
/*  80: 88 */         shot.shotSpeed = 0.01D;
/*  81: 89 */         shot.shotLimitSpeed = 0.3D;
/*  82: 90 */         shot.shotAcceleration = 0.03D;
/*  83: 91 */         shot.specialEnd();
/*  84:    */       }
/*  85:    */       break;
/*  86:    */     }
/*  87:    */   }
/*  88:    */   
/*  89:    */   public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot, MovingObjectPosition movingObjectPosition)
/*  90:    */   {
/*  91:103 */     return false;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public boolean specialShot_hitEntity(World world, int id, EntityTHShot shot, Entity entity_Hit)
/*  95:    */   {
/*  96:110 */     return false;
/*  97:    */   }
/*  98:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_PerfectFreeze
 * JD-Core Version:    0.7.0.1
 */