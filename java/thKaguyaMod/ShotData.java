package thKaguyaMod;

/**
 * 弾の性質設定
 * 弾の生成時にこれで主に動作以外の弾独自の性質を設定。
 * */
public class ShotData 
{		
	/**
	 * 弾の形状。THShotLib.FORM_SMALLなどで取得
	 */
	public int form;
	
	/**
	 * 弾の色。THShotLib.REDなどで取得
	 */
	public int color;
	
	/**
	 * 弾のダメージ。2.0Fでハート１つ分。THShotLib.DAMAGE[form]で形状ごとのデフォルトのダメージになる
	 */
	public float damage;
	
	/**
	 * 弾のサイズ。見た目ではなく当たり判定で、1.0Fで１メートルくらいになる。THShotLib.SIZE[form]で形状ごとのデフォルトのサイズになる
	 */
	public float size;
	
	/**
	 * 弾の遅延時間。1/20秒でこの時間だけ出現が遅れる（動作なし、当たり判定なし）
	 */
	public int delay;
	
	/**
	 * 弾の消滅時間。1/20秒。遅延時間は含まれない
	 */
	public int end;
	
	/**
	 * 特殊動作。EntityTHShot内に記述はあるが現状複雑で柔軟性はないので説明は省く
	 */
	public int special;
	
	
	public ShotData(int form, int color, float size, float damage, int delay, int end, int special)
	{
		this.form = form;
		this.color = color;
		this.damage = damage;
		this.size = size;
		this.delay = delay;
		this.end = end;
		this.special = special;
	}
	
	/**
	 * 全てのパラメータを設定できる弾の性質データ生成処理
	 * @param form		: 弾の形状
	 * @param color	: 弾の色
	 * @param size		: 弾の大きさ
	 * @param damage	: 弾のダメージ
	 * @param delay	: 弾の遅延時間
	 * @param end		: 弾の消滅時間
	 * @param special	: 特殊弾ID
	 * @return 弾の性質データ
	 */
	public static ShotData shot(int form, int color, float size, float damage, int delay, int end, int special)
	{
		form = form % 32;
		color = color % 16;
		return new ShotData(form, color, size, damage, delay, end, special);
	}
	
	public static ShotData shot(int form, int color, float size, float damage, int delay, int end)
	{
		return shot(form, color, size, damage, delay, end, 0);
	}
	
	public static ShotData shot(int form, int color, float size, float damage)
	{
		return shot(form, color, size, damage, 0, 80);
	}
	
	public static ShotData shot(int form, int color)
	{
		return shot(form, color, 0);
	}
	
	public static ShotData shot(int form, int color, int delay)
	{
		return shot(form, color, delay, 80);
	}
	
	public static ShotData shot(int form, int color, int delay, int end)
	{
		return shot(form, color, delay, end, 0);
	}
	
	public static ShotData shot(int form, int color, int delay, int end, int special)
	{
		float size = THShotLib.SIZE[form % 32];
		float damage = THShotLib.DAMAGE[form % 32];
		return shot(form, color, size, damage, delay, end, special);
	}

}
