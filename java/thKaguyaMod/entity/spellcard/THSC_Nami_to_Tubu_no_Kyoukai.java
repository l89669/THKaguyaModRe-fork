/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import net.minecraft.util.MathHelper;
/*  4:   */ import thKaguyaMod.ShotData;
/*  5:   */ import thKaguyaMod.THShotLib;
/*  6:   */ 
/*  7:   */ public class THSC_Nami_to_Tubu_no_Kyoukai
/*  8:   */   extends THSpellCard
/*  9:   */ {
/* 10:   */   private float yaw;
/* 11:   */   private float pitch;
/* 12:   */   
/* 13:   */   public THSC_Nami_to_Tubu_no_Kyoukai()
/* 14:   */   {
/* 15:16 */     setNeedLevel(5);
/* 16:17 */     setRemoveTime(40);
/* 17:18 */     setEndTime(320);
/* 18:19 */     setOriginalUserName(21);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void spellcard_main()
/* 22:   */   {
/* 23:25 */     if (this.time == 0)
/* 24:   */     {
/* 25:27 */       this.yaw = 0.0F;
/* 26:28 */       this.pitch = 0.0F;
/* 27:   */     }
/* 28:30 */     if ((this.time >= 5) && (this.time < 280))
/* 29:   */     {
/* 30:32 */       this.yaw += MathHelper.cos((this.time + 75) / 50.0F) * 17.0F;
/* 31:33 */       this.pitch += MathHelper.sin(MathHelper.sin((this.time + 75) / 50.0F) * 3.14F) * 17.0F;
/* 32:34 */       float size = 0.3F;
/* 33:35 */       float damage = 9.0F;
/* 34:36 */       ShotData shot = ShotData.shot(5, 4, size, damage, 0, 40);
/* 35:   */       
/* 36:38 */       THShotLib.createSphereShot(this.user, this.user, pos_User(), getVecFromAngle(this.yaw, this.pitch), 0.0F, rotate_Default(), 0.0F, 9999, 0.2D, 0.6D, 0.05D, gravity_Zero(), shot, 12, 2.0D, 0.0F);
/* 37:   */     }
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_Nami_to_Tubu_no_Kyoukai
 * JD-Core Version:    0.7.0.1
 */