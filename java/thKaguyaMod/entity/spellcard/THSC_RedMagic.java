/*   1:    */ package thKaguyaMod.entity.spellcard;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.Entity;
/*   4:    */ import net.minecraft.entity.EntityLivingBase;
/*   5:    */ import net.minecraft.util.MovingObjectPosition;
/*   6:    */ import net.minecraft.util.Vec3;
/*   7:    */ import net.minecraft.world.World;
/*   8:    */ import thKaguyaMod.ShotData;
/*   9:    */ import thKaguyaMod.THShotLib;
/*  10:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  11:    */ import thKaguyaMod.entity.shot.ISpecialShot;
/*  12:    */ import thKaguyaMod.registry.SpecialShotRegistry;
/*  13:    */ 
/*  14:    */ public class THSC_RedMagic
/*  15:    */   extends THSpellCard
/*  16:    */   implements ISpecialShot
/*  17:    */ {
/*  18:    */   public static final int SPECIAL_REDMAGIC01 = 2300;
/*  19:    */   public static final int SPECIAL_REDMAGIC02 = 2301;
/*  20:    */   
/*  21:    */   public THSC_RedMagic()
/*  22:    */   {
/*  23: 24 */     setNeedLevel(4);
/*  24: 25 */     setRemoveTime(20);
/*  25: 26 */     setEndTime(190);
/*  26: 27 */     setOriginalUserName(9);
/*  27:    */     
/*  28: 29 */     SpecialShotRegistry.registerSpecialShot(THSC_RedMagic.class, 2300);
/*  29: 30 */     SpecialShotRegistry.registerSpecialShot(THSC_RedMagic.class, 2301);
/*  30:    */   }
/*  31:    */   
/*  32:    */   public void spellcard_main()
/*  33:    */   {
/*  34: 36 */     if (this.time == 5)
/*  35:    */     {
/*  36: 38 */       float angle2 = 0.0F;
/*  37: 39 */       ShotData shotData = shot(30, 4, THShotLib.SIZE[30], 14.0F, 3, 30, 2300);
/*  38: 40 */       for (int i = 0; i < 3; i++)
/*  39:    */       {
/*  40: 42 */         Vec3 angle = THShotLib.getVecFromAngle(this.user.rotationYaw, this.user.rotationPitch + angle2, 1.0D);
/*  41: 43 */         THShotLib.createCircleShot(this.user, this.user, pos_User(), angle, -5.0F, 20, 0.9D, 0.2D, 0.0D, gravity_Zero(), shotData, 6, 0.7D);
/*  42:    */         
/*  43:    */ 
/*  44: 46 */         angle = THShotLib.getVecFromAngle(this.user.rotationYaw + 90.0F, this.user.rotationPitch + 90.0F + angle2, 1.0D);
/*  45: 47 */         THShotLib.createCircleShot(this.user, this.user, pos_User(), angle, -5.0F, 20, 0.9D, 0.2D, 0.0D, gravity_Zero(), shotData, 6, 0.7D);
/*  46: 48 */         angle2 += 60.0F;
/*  47:    */       }
/*  48:    */     }
/*  49: 51 */     else if (this.time == 75)
/*  50:    */     {
/*  51: 53 */       float angle2 = 0.0F;
/*  52: 54 */       ShotData shotData = shot(30, 4, THShotLib.SIZE[30], 14.0F, 3, 30, 2300);
/*  53: 55 */       for (int i = 0; i < 3; i++)
/*  54:    */       {
/*  55: 57 */         Vec3 angle = THShotLib.getVecFromAngle(this.user.rotationYaw, this.user.rotationPitch + angle2, 1.0D);
/*  56: 58 */         THShotLib.createCircleShot(this.user, this.user, pos_User(), angle, 5.0F, 20, 0.9D, 0.2D, 0.0D, gravity_Zero(), shotData, 6, 0.7D);
/*  57:    */         
/*  58: 60 */         angle = THShotLib.getVecFromAngle(this.user.rotationYaw + 90.0F, this.user.rotationPitch + 90.0F + angle2, 1.0D);
/*  59: 61 */         THShotLib.createCircleShot(this.user, this.user, pos_User(), angle, 5.0F, 20, 0.9D, 0.2D, 0.0D, gravity_Zero(), shotData, 6, 0.7D);
/*  60: 62 */         angle2 += 60.0F;
/*  61:    */       }
/*  62:    */     }
/*  63:    */   }
/*  64:    */   
/*  65:    */   public void specialShot_move(World world, int id, EntityTHShot shot)
/*  66:    */   {
/*  67: 69 */     switch (id)
/*  68:    */     {
/*  69:    */     case 2300: 
/*  70: 72 */       if (shot.ticksExisted % 3 == 0)
/*  71:    */       {
/*  72: 74 */         Vec3 rotate2 = THShotLib.getVectorFromRotation(shot.rotate, shot.angle, 90.0F + shot.ticksExisted * 3.0F);
/*  73:    */         
/*  74: 76 */         ShotData shotData = ShotData.shot(5, 0, THShotLib.SIZE[5], 9.0F, 20, 30 - shot.ticksExisted + 120, 2301);
/*  75: 77 */         THShotLib.createShot(shot.user, shot, THShotLib.pos_Entity(shot), rotate2, shot.getAngleZ(), shot.rotate, 0.0F, 9999, 0.001D, 0.0D, 0.02D, gravity_Zero(), shotData);
/*  76:    */       }
/*  77: 78 */       break;
/*  78:    */     case 2301: 
/*  79: 81 */       if (shot.ticksExisted >= 30)
/*  80:    */       {
/*  81: 83 */         shot.shotSpeed = 0.1D;
/*  82: 84 */         shot.shotLimitSpeed = 0.3D;
/*  83: 85 */         shot.specialEnd();
/*  84:    */       }
/*  85:    */       break;
/*  86:    */     }
/*  87:    */   }
/*  88:    */   
/*  89:    */   public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot, MovingObjectPosition movingObjectPosition)
/*  90:    */   {
/*  91: 98 */     return false;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public boolean specialShot_hitEntity(World world, int id, EntityTHShot shot, Entity entity_Hit)
/*  95:    */   {
/*  96:105 */     return false;
/*  97:    */   }
/*  98:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_RedMagic
 * JD-Core Version:    0.7.0.1
 */