/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import net.minecraft.world.World;
/*  4:   */ import thKaguyaMod.entity.shot.EntityMusouFuuin;
/*  5:   */ 
/*  6:   */ public class THSC_MusouFuuin
/*  7:   */   extends THSpellCard
/*  8:   */ {
/*  9:   */   public THSC_MusouFuuin()
/* 10:   */   {
/* 11:12 */     setNeedLevel(5);
/* 12:13 */     setEndTime(90);
/* 13:14 */     setRemoveTime(70);
/* 14:15 */     setOriginalUserName(0);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void spellcard_main()
/* 18:   */   {
/* 19:21 */     if ((this.time % 2 == 0) && (this.time >= 15) && (this.time < 49)) {
/* 20:23 */       if ((this.user != null) && (this.target != null))
/* 21:   */       {
/* 22:25 */         EntityMusouFuuin musouFuuin = new EntityMusouFuuin(this.world, this.user, this.user, pos_User(), angle(this.time * 33.0F, 0.0F), 0.0F, rotate_Default(), 0.0F, 9999, 2.0D, 0.0D, -0.1D, gravity_Zero(), 2.0F, 9.0F, this.target);
/* 23:26 */         if (!this.world.isRemote) {
/* 24:28 */           this.world.spawnEntityInWorld(musouFuuin);
/* 25:   */         }
/* 26:   */       }
/* 27:   */     }
/* 28:   */   }
/* 29:   */   
/* 30:   */   public int getSpellCardCircleColor()
/* 31:   */   {
/* 32:38 */     return 9;
/* 33:   */   }
/* 34:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_MusouFuuin
 * JD-Core Version:    0.7.0.1
 */