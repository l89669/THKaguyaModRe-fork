/*  1:   */ package thKaguyaMod;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.creativetab.CreativeTabs;
/*  6:   */ import net.minecraft.item.Item;
/*  7:   */ import thKaguyaMod.init.THKaguyaItems;
/*  8:   */ 
/*  9:   */ public class CreativeTabSpellCard
/* 10:   */   extends CreativeTabs
/* 11:   */ {
/* 12:   */   public CreativeTabSpellCard(String type)
/* 13:   */   {
/* 14:16 */     super(type);
/* 15:   */   }
/* 16:   */   
/* 17:   */   @SideOnly(Side.CLIENT)
/* 18:   */   public String getTranslatedTabLabel()
/* 19:   */   {
/* 20:35 */     return "SpellCard";
/* 21:   */   }
/* 22:   */   
/* 23:   */   public Item getTabIconItem()
/* 24:   */   {
/* 25:41 */     return THKaguyaItems.spell_card;
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.CreativeTabSpellCard
 * JD-Core Version:    0.7.0.1
 */