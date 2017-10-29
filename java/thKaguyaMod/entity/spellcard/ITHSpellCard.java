package thKaguyaMod.entity.spellcard;

/** スペルカードを定義するインターフェース */
public interface ITHSpellCard 
{
	
	/**
	 * スペルカードの個別の処理を行う
	 */
	public void spellcard_main();
	
	/**
	 * スペルカードを宣言するときに必要なレベルと消費するレベルを返す
	 * @return
	 */
	public int getSpellCardLevel();
	
}