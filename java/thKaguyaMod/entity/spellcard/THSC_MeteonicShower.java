/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.util.Vec3;
/*  6:   */ import thKaguyaMod.THShotLib;
/*  7:   */ 
/*  8:   */ public class THSC_MeteonicShower
/*  9:   */   extends THSpellCard
/* 10:   */ {
/* 11:   */   public THSC_MeteonicShower()
/* 12:   */   {
/* 13:16 */     setNeedLevel(1);
/* 14:17 */     setRemoveTime(10);
/* 15:18 */     setEndTime(60);
/* 16:19 */     setOriginalUserName(1);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void spellcard_main()
/* 20:   */   {
/* 21:25 */     if ((this.time > 10) && (this.time < 40) && (this.time % 2 == 0))
/* 22:   */     {
/* 23:27 */       Vec3 angle = this.user.getLookVec();
/* 24:28 */       if (!this.target.isDead) {
/* 25:30 */         angle = THShotLib.angle_ToPos(pos_User(), pos_Target());
/* 26:   */       }
/* 27:32 */       Random rand = new Random();
/* 28:33 */       int[] colors = { 0, 2, 3, 4 };
/* 29:34 */       int color = colors[rand.nextInt(4)];
/* 30:35 */       float size = 0.2F + rand.nextFloat() * 0.4F;
/* 31:36 */       int damage = (int)(size * 15.0F);
/* 32:   */       
/* 33:38 */       THShotLib.createRandomRingShot(this.user, this.card, pos_User(angle, 0.7D), angle, 0.0F, 9999, 0.4D, 0.6D, 0.1D, gravity_Zero(), shot(9, color, size, damage, 3, 40), 1, 0.0D, 40.0F);
/* 34:39 */       THShotLib.playShotSound(this.user);
/* 35:   */     }
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_MeteonicShower
 * JD-Core Version:    0.7.0.1
 */