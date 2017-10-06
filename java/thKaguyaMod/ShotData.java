/*   1:    */ package thKaguyaMod;
/*   2:    */ 
/*   3:    */ public class ShotData
/*   4:    */ {
/*   5:    */   public int form;
/*   6:    */   public int color;
/*   7:    */   public float damage;
/*   8:    */   public float size;
/*   9:    */   public int delay;
/*  10:    */   public int end;
/*  11:    */   public int special;
/*  12:    */   
/*  13:    */   public ShotData(int form, int color, float size, float damage, int delay, int end, int special)
/*  14:    */   {
/*  15: 47 */     this.form = form;
/*  16: 48 */     this.color = color;
/*  17: 49 */     this.damage = damage;
/*  18: 50 */     this.size = size;
/*  19: 51 */     this.delay = delay;
/*  20: 52 */     this.end = end;
/*  21: 53 */     this.special = special;
/*  22:    */   }
/*  23:    */   
/*  24:    */   public static ShotData shot(int form, int color, float size, float damage, int delay, int end, int special)
/*  25:    */   {
/*  26: 69 */     form %= 32;
/*  27: 70 */     color %= 16;
/*  28: 71 */     return new ShotData(form, color, size, damage, delay, end, special);
/*  29:    */   }
/*  30:    */   
/*  31:    */   public static ShotData shot(int form, int color, float size, float damage, int delay, int end)
/*  32:    */   {
/*  33: 76 */     return shot(form, color, size, damage, delay, end, 0);
/*  34:    */   }
/*  35:    */   
/*  36:    */   public static ShotData shot(int form, int color, float size, float damage)
/*  37:    */   {
/*  38: 81 */     return shot(form, color, size, damage, 0, 80);
/*  39:    */   }
/*  40:    */   
/*  41:    */   public static ShotData shot(int form, int color)
/*  42:    */   {
/*  43: 86 */     return shot(form, color, 0);
/*  44:    */   }
/*  45:    */   
/*  46:    */   public static ShotData shot(int form, int color, int delay)
/*  47:    */   {
/*  48: 91 */     return shot(form, color, delay, 80);
/*  49:    */   }
/*  50:    */   
/*  51:    */   public static ShotData shot(int form, int color, int delay, int end)
/*  52:    */   {
/*  53: 96 */     return shot(form, color, delay, end, 0);
/*  54:    */   }
/*  55:    */   
/*  56:    */   public static ShotData shot(int form, int color, int delay, int end, int special)
/*  57:    */   {
/*  58:101 */     float size = THShotLib.SIZE[(form % 32)];
/*  59:102 */     float damage = THShotLib.DAMAGE[(form % 32)];
/*  60:103 */     return shot(form, color, size, damage, delay, end, special);
/*  61:    */   }
/*  62:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.ShotData
 * JD-Core Version:    0.7.0.1
 */