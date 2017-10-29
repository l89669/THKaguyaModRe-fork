package thKaguyaMod;

/**
 * レーザーの性質設定
 * レーザーの生成時にこれで主に動作以外のレーザー独自の性質を設定。
 */
public class LaserData 
{	
	/**
	 * レーザーの形状
	 */
	public int form;
	
	/**
	 * レーザーの色。THShotLib.REDなどで取得
	 */
	public int color;
	
	/**
	 * レーザーのダメージ。2.0Fでハート１つ分。
	 */
	public float damage;
	
	/**
	 * レーザーの太さ。見た目ではなく当たり判定で、1.0Fで１メートルくらいになる。
	 */
	public float width;
	
	/**
	 * レーザーの長さ。
	 */
	public float length;
	
	/**
	 * レーザーの遅延時間。1/20秒でこの時間だけ出現が遅れる（動作なし、当たり判定なし）
	 */
	public int delay;
	
	/**
	 * レーザーの消滅時間。1/20秒。遅延時間は含まれない
	 */
	public int end;
	
	/**
	 * 特殊動作。EntityTHShot内に記述はあるが現状複雑で柔軟性はないので説明は省く
	 */
	public int special;
	
	public LaserData(int form, int color, float width, float length, float damage, int delay, int end, int special)
	{
		this.form = form;
		this.color = color;
		this.damage = damage;
		this.width = width;
		this.length = length;
		this.delay = delay;
		this.end = end;
		this.special = special;
	}
	
	public static LaserData laser(int form, int color, float width, float length, float damage, int delay, int end, int special)
	{
		return new LaserData(form, color, width, length, damage, delay, end, special);
	}
	
	public static LaserData laser(int form, int color, float width, float length, float damage, int delay, int end)
	{
		return laser(form, color, width, length, damage, delay, end, 0);
	}
	
	public static LaserData laser(int form, int color, float width, float length, float damage, int delay)
	{
		return laser(form, color, width, length, damage, delay, 80);
	}
	
	public static LaserData laser(int form, int color, float width, float length, float damage)
	{
		return laser(form, color, width, length, damage, 0, 80);
	}
	
	public static LaserData laser(int color, float width, float length, float damage, int delay, int end, int special)
	{
		return new LaserData(0, color, width, length, damage, delay, end, special);
	}
	
	public static LaserData laser(int color, float width, float length, float damage, int delay, int end)
	{
		return laser(color, width, length, damage, delay, end, 0);
	}
	
	public static LaserData laser(int color, float width, float length, float damage, int delay)
	{
		return laser(color, width, length, damage, delay, 80);
	}
	
	public static LaserData laser(int color, float width, float length, float damage)
	{
		return laser(color, width, length, damage, 0, 80);
	}

}
