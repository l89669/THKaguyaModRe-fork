package thKaguyaMod.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import thKaguyaMod.entity.spellcard.THSpellCard;

/**
 * スペルカードの登録に関するクラス
 */
public class SpellCardRegistry 
{
	public static Map<Integer, String> spellcardName = new HashMap<Integer, String>();
	public static Map<String, String> spellcardModDomain = new HashMap<String, String>();
	public static Map<String, Class<? extends THSpellCard>> spellcardClass = new HashMap<String, Class<? extends THSpellCard>>();
	public static Map<String, Integer> spellcardID = new HashMap<String, Integer>();
	
	//@SideOnly(Side.CLIENT)
	//public static IIcon[] icon;// = new IIcon[1024];
	
	/**
	 * スペルカードを登録する。登録するクラスはTHSpellCardを継承したもの
	 * @param spellcard_class : THSpellCardを継承したクラス。個別のスペルカード処理のみ書かれたもの
	 * @param domain		   : 宣言したMODのドメイン名
	 * @param name            : スペルカードの登録名。符名抜きの日本語名をローマ字表記が望ましい　for example: "MusouFuuin", "Kasho_no_Eimin"
	 * @param id              : 他のスペルカードと被らない数字。128以上が望ましい。
	 * @return 登録に成功したらtrue
	 */
	public static boolean registerSpellCard(Class<? extends THSpellCard> spellcard_class, String domain, String name, int id)
	{
		if(idCheck(id))
		{
			spellcardName.put(id, name);
			spellcardModDomain.put(name, domain);
			spellcardClass.put(name, spellcard_class);
			spellcardID.put(name, id);
			return true;
		}
		return false;
	}
	
	private static boolean idCheck(int id)
	{
		for(String name : spellcardID.keySet())
		{
			if(spellcardID.get(name) == id)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * スペルカードIDからそのスペルカードの登録名を返す
	 * @param id
	 * @return
	 */
	public static String getSpellCardName(int id)
	{
		return spellcardName.get(id);
	}
	
	/**
	 * スペルカードIDからそのスペルカードの属するModのドメイン名を返す
	 * @param name
	 * @return
	 */
	public static String getSpellCardModDomain(String name)
	{
		return spellcardModDomain.get(name);
	}
	
	/**
	 * スペルカードIDからそのスペルカードの属するModのドメイン名を返す
	 * @param name
	 * @return
	 */
	public static String getSpellCardModDomain(int id)
	{
		return spellcardModDomain.get(getSpellCardName(id));
	}
	
	/**
	 * 登録名からスペルカードIDを返す
	 * @param name
	 * @return
	 */
	public static int getSpellCardNumber(String name)
	{
		return spellcardID.get(name);
	}
	
	/**
	 * 登録名からスペルカードの処理の書かれたクラスを返す
	 * @param name
	 * @return
	 */
	public static Class<? extends THSpellCard> getSpellCardClass(String name)
	{
		return spellcardClass.get(name);
	}
	
	/**
	 * スペルカードIDからスペルカードの処理の書かれたクラスを返す
	 * @param id
	 * @return
	 */
	public static Class<? extends THSpellCard> getSpellCardClass(int id)
	{
		return spellcardClass.get(getSpellCardName(id));
	}
	
	/*@SideOnly(Side.CLIENT)
	public static IIcon getSpellCardIcon(int id)
	{
		IIconRegister iconRegister = ;
		String name = getSpellCardName(id);
		
		return registerIcon(SpellCardRegistry.getSpellCardModDomain(name) + ":spellCard/" + name);// + spell.getIconName())
	}*/
	
	/**
	 * 登録されているスペルカードの合計数を返す
	 * @return
	 */
	public static int getNumberOfSpellCard()
	{
		return spellcardID.size();
	}
	
	/**
	 * スペルカードのListを取得する
	 * @return
	 */
	public static List<String> getSpellCardList()
	{
		int length = getNumberOfSpellCard();//登録されているスペルカードの枚数
		String[] spellcardNames = new String[length + 1];//スペルカードの登録名の配列
		int[] spellcardUsers = new int[length + 1];//スペルカードの原作使用者IDの配列
		spellcardUsers[0] = 255;//スペルカードの原作使用者IDの配列の先頭の初期値を最大値にする
		spellcardNames[0] = "";
		THSpellCard spellcard;
		
		int userID;
		int keyNumber = 0;
		int keyNumber2 = 0;
		boolean success = true;
		
		//Map内の全てのキーを参照
		for(String name : spellcardID.keySet())
		{
			//キーからクラスを取得し、インスタンスを生成
			try {
				spellcard = spellcardClass.get(name).newInstance();
				//原作使用者IDを取得
				userID = spellcard.getOriginalUserName();
				//spellcardUsers[keyNumber] = userID;
				//spellcardNames[keyNumber] = name;
				int i = 0;
				keyNumber++;
				keyNumber2 = keyNumber;
				//スペルカードの原作仕様者のIDの配列を参照
				do
				{
					//原作使用者IDが参照中のIDより小さいなら
					if(userID < spellcardUsers[i])
					{
						//現在のiより大きい配列の中身を全て一つ後ろに動かす
						for(int j = keyNumber2; j > i; j--)
						{
							spellcardUsers[j] = spellcardUsers[j - 1];
							spellcardNames[j] = spellcardNames[j - 1];
							keyNumber2--;
						}
						spellcardUsers[i] = userID;
						spellcardNames[i] = name;
					}
					i++;
				}
				while(i < keyNumber2);
			} catch (InstantiationException e) {
				success = false;
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		if(success)
		{
			List<String> list = new ArrayList<String>();
			for(int i = 0; i < length; i++)
			{
				list.add(spellcardNames[i]);
			}
			return list;
		}
		return null;
	}
	
	/**
	 * スペルカードの原作の使用者IDを返す
	 * @param name スペルカード名
	 * @return
	 */
	public static int getSpellCardOriginalUser(String name)
	{
		Class<?> spellcard;
		THSpellCard useSpellCard;
		
		if((spellcard = SpellCardRegistry.getSpellCardClass(name)) != null)
		{
			try {
				useSpellCard = (THSpellCard)spellcard.newInstance();
				return useSpellCard.getOriginalUserName();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
			
		}
		return -100;
	}
	
	
	public static List<String> sortSpellCard()
	{
		List<String> list = getSpellCardList();
		List<String> newList = new ArrayList<String>();
		String array[] = new String[256];
		array[0] = list.get(0);
		
		for(int i = 1; i < list.size(); i++)
		{
			if(getSpellCardOriginalUser(array[i - 1]) > getSpellCardOriginalUser(list.get(i)))
			{
				int j = i;
				while(j > 0 && getSpellCardOriginalUser(array[j - 1]) > getSpellCardOriginalUser(list.get(j)))
				{
					array[j] = array[j-1];
					j--;
				}
			}
			else
			{
				array[i] = list.get(i);
			}
		}
		
		for(int i = 0; i < list.size(); i++)
		{
			newList.add(array[i]);
		}
		return newList;
	}

}
