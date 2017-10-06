/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.Entity;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.util.MovingObjectPosition;
/*  6:   */ import net.minecraft.util.Vec3;
/*  7:   */ import net.minecraft.world.World;
/*  8:   */ import thKaguyaMod.ShotData;
/*  9:   */ import thKaguyaMod.THShotLib;
/* 10:   */ import thKaguyaMod.entity.shot.EntityTHShot;
/* 11:   */ import thKaguyaMod.entity.shot.ISpecialShot;
/* 12:   */ import thKaguyaMod.registry.SpecialShotRegistry;
/* 13:   */ 
/* 14:   */ public class THSC_StardustReverie
/* 15:   */   extends THSpellCard
/* 16:   */   implements ISpecialShot
/* 17:   */ {
/* 18:   */   public static final int SPECIAL_STARDUST01 = 1200;
/* 19:   */   
/* 20:   */   public THSC_StardustReverie()
/* 21:   */   {
/* 22:23 */     setNeedLevel(4);
/* 23:24 */     setRemoveTime(60);
/* 24:25 */     setEndTime(120);
/* 25:26 */     setOriginalUserName(1);
/* 26:   */     
/* 27:28 */     SpecialShotRegistry.registerSpecialShot(THSC_StardustReverie.class, 1200);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void spellcard_main()
/* 31:   */   {
/* 32:34 */     if (this.time < 5) {
/* 33:34 */       return;
/* 34:   */     }
/* 35:36 */     if (this.time == 5)
/* 36:   */     {
/* 37:38 */       int[] way = { 4, 4, 7, 9, 10 };
/* 38:   */       
/* 39:40 */       THShotLib.createCircleShot(this.user, this.user, pos_User(), this.user
/* 40:41 */         .getLookVec(), 0.3D, 0.3D, 0.0D, gravity_Zero(), 
/* 41:42 */         shot(9, 9, 1.2F, 7.0F, 2, 120, 1200), way[this.level], 0.2D, 0.0F);
/* 42:   */     }
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void specialShot_move(World world, int id, EntityTHShot shot)
/* 46:   */   {
/* 47:48 */     switch (id)
/* 48:   */     {
/* 49:   */     case 1200: 
/* 50:51 */       ShotData shotData = ShotData.shot(shot.getShotForm(), shot.getShotColor(), 0.3F, 5.0F, 10, 50);
/* 51:52 */       shot.setShotRotationYaw(2.0F);
/* 52:53 */       Vec3 vec = THShotLib.getVectorFromRotation(shot.rotate, shot.angle, shot.ticksExisted * 13.0F);
/* 53:54 */       THShotLib.createShot(shot.user, shot, THShotLib.pos_Entity(shot), angle(vec.xCoord, vec.yCoord, vec.zCoord), shot.getAngleZ(), shot.rotate, 0.0F, 9999, 0.1D, 0.3D, 0.03D, THShotLib.gravity_Zero(), shotData);
/* 54:55 */       break;
/* 55:   */     }
/* 56:   */   }
/* 57:   */   
/* 58:   */   public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot, MovingObjectPosition movingObjectPosition)
/* 59:   */   {
/* 60:65 */     return false;
/* 61:   */   }
/* 62:   */   
/* 63:   */   public boolean specialShot_hitEntity(World world, int id, EntityTHShot shot, Entity entity_Hit)
/* 64:   */   {
/* 65:71 */     switch (id)
/* 66:   */     {
/* 67:   */     case 1200: 
/* 68:74 */       return true;
/* 69:   */     }
/* 70:76 */     return false;
/* 71:   */   }
/* 72:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_StardustReverie
 * JD-Core Version:    0.7.0.1
 */