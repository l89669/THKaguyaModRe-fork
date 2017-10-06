package thKaguyaMod.registry;

import java.util.HashMap;
import java.util.Map;

import thKaguyaMod.entity.shot.ISpecialShot;

/**
 * 特殊弾の登録クラス
 */
public class SpecialShotRegistry 
{
	//public static Map<Integer, String> spellcardName = new HashMap<Integer, String>();
	public static Map<Integer, Class<? extends ISpecialShot>> specialShotClass = new HashMap<Integer, Class<? extends ISpecialShot>>();
	//public static Map<String, Integer> spellcardID = new HashMap<String, Integer>();
	
	/**
	 * 特殊弾を登録する。登録するクラスはISpecialShotをimplementsしたもの
	 * @param specialShot_class : ISpecialShotをimplementsしたクラス
	 * @param id              : 他の特殊弾と被らない数字
	 * @return 登録に成功したらtrue
	 */
	public static boolean registerSpecialShot(Class<? extends ISpecialShot> specialShot_class, int id)
	{
		specialShotClass.put(id, specialShot_class);
	
		return true;
	}
	
	/**
	 * 特殊弾IDから特殊弾処理の書かれたクラスを返す
	 * @param id : 特殊弾ID
	 * @return 特殊弾定義の書かれたクラス
	 */
	public static Class<? extends ISpecialShot> getSpecialShotClass(int id)
	{
		return specialShotClass.get(id);
	}
	
	/**
	 * 登録されている特殊弾の合計数を返す
	 * @return
	 */
	public static int getNumberOfSpecialShot()
	{
		return specialShotClass.size();
	}
}
