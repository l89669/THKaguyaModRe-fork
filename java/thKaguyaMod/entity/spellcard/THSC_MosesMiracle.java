/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.EntityLivingBase;
/*  4:   */ import thKaguyaMod.THShotLib;
/*  5:   */ 
/*  6:   */ public class THSC_MosesMiracle
/*  7:   */   extends THSpellCard
/*  8:   */ {
/*  9:   */   public THSC_MosesMiracle()
/* 10:   */   {
/* 11:12 */     setNeedLevel(2);
/* 12:13 */     setRemoveTime(80);
/* 13:14 */     setEndTime(180);
/* 14:15 */     setOriginalUserName(41);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void spellcard_main()
/* 18:   */   {
/* 19:21 */     if (this.time % 2 == 0) {
/* 20:23 */       return;
/* 21:   */     }
/* 22:25 */     double angle = this.time * 6.0D;
/* 23:26 */     float baseAngle = this.user.rotationYaw;
/* 24:27 */     if (this.target != null) {
/* 25:29 */       baseAngle = (float)Math.atan2(this.card.posX - this.target.posX, -(this.card.posZ - this.target.posZ)) / 3.141593F * 180.0F;
/* 26:   */     }
/* 27:32 */     for (int i = -5; i < 6; i++)
/* 28:   */     {
/* 29:34 */       double baseX = this.cardPosX - Math.sin(angle / 180.0D * 3.141592979431152D) * Math.cos(baseAngle / 180.0F * 3.141593F) * 3.0D;
/* 30:35 */       double baseZ = this.cardPosZ + Math.cos(angle / 180.0D * 3.141592979431152D) * Math.sin(baseAngle / 180.0F * 3.141593F) * 3.0D;
/* 31:36 */       double sideX = -Math.sin((baseAngle + 90.0F) / 180.0F * 3.141593F);
/* 32:37 */       double sideZ = Math.cos((baseAngle + 90.0F) / 180.0F * 3.141593F);
/* 33:38 */       THShotLib.createShot(this.user, this.user, pos(baseX + sideX * 2.5D, this.cardPosY + i, baseZ + sideZ * 2.5D), THShotLib.angle_ToPos(pos_Card(), THShotLib.pos_Living(this.target)), 0.0F, 0.9D, 0.9D, 0.0D, gravity_Zero(), shot(5, 1, 0.8F, 7.0F, 0, 50));
/* 34:39 */       THShotLib.createShot(this.user, this.user, pos(baseX - sideX * 2.5D, this.cardPosY + i, baseZ - sideZ * 2.5D), THShotLib.angle_ToPos(pos_Card(), THShotLib.pos_Living(this.target)), 0.0F, 0.9D, 0.9D, 0.0D, gravity_Zero(), shot(5, 1, 0.8F, 7.0F, 0, 50));
/* 35:   */     }
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_MosesMiracle
 * JD-Core Version:    0.7.0.1
 */