/*  1:   */ package thKaguyaMod;
/*  2:   */ 
/*  3:   */ public class LaserData
/*  4:   */ {
/*  5:   */   public int form;
/*  6:   */   public int color;
/*  7:   */   public float damage;
/*  8:   */   public float width;
/*  9:   */   public float length;
/* 10:   */   public int delay;
/* 11:   */   public int end;
/* 12:   */   public int special;
/* 13:   */   
/* 14:   */   public LaserData(int form, int color, float width, float length, float damage, int delay, int end, int special)
/* 15:   */   {
/* 16:51 */     this.form = form;
/* 17:52 */     this.color = color;
/* 18:53 */     this.damage = damage;
/* 19:54 */     this.width = width;
/* 20:55 */     this.length = length;
/* 21:56 */     this.delay = delay;
/* 22:57 */     this.end = end;
/* 23:58 */     this.special = special;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public static LaserData laser(int form, int color, float width, float length, float damage, int delay, int end, int special)
/* 27:   */   {
/* 28:63 */     return new LaserData(form, color, width, length, damage, delay, end, special);
/* 29:   */   }
/* 30:   */   
/* 31:   */   public static LaserData laser(int form, int color, float width, float length, float damage, int delay, int end)
/* 32:   */   {
/* 33:68 */     return laser(form, color, width, length, damage, delay, end, 0);
/* 34:   */   }
/* 35:   */   
/* 36:   */   public static LaserData laser(int form, int color, float width, float length, float damage, int delay)
/* 37:   */   {
/* 38:73 */     return laser(form, color, width, length, damage, delay, 80);
/* 39:   */   }
/* 40:   */   
/* 41:   */   public static LaserData laser(int form, int color, float width, float length, float damage)
/* 42:   */   {
/* 43:78 */     return laser(form, color, width, length, damage, 0, 80);
/* 44:   */   }
/* 45:   */   
/* 46:   */   public static LaserData laser(int color, float width, float length, float damage, int delay, int end, int special)
/* 47:   */   {
/* 48:83 */     return new LaserData(0, color, width, length, damage, delay, end, special);
/* 49:   */   }
/* 50:   */   
/* 51:   */   public static LaserData laser(int color, float width, float length, float damage, int delay, int end)
/* 52:   */   {
/* 53:88 */     return laser(color, width, length, damage, delay, end, 0);
/* 54:   */   }
/* 55:   */   
/* 56:   */   public static LaserData laser(int color, float width, float length, float damage, int delay)
/* 57:   */   {
/* 58:93 */     return laser(color, width, length, damage, delay, 80);
/* 59:   */   }
/* 60:   */   
/* 61:   */   public static LaserData laser(int color, float width, float length, float damage)
/* 62:   */   {
/* 63:98 */     return laser(color, width, length, damage, 0, 80);
/* 64:   */   }
/* 65:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.LaserData
 * JD-Core Version:    0.7.0.1
 */