package thKaguyaMod.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import thKaguyaMod.THShotLib;
import thKaguyaMod.init.THKaguyaConfig;

/** 妖精の弾幕パターンの登録クラス */
public class DanmakuPatternRegistry
{	
	public static ArrayList<String> pattern = new ArrayList<String>();
	public static Map<String, Integer> danmaku = new HashMap<String, Integer>();
	public static Map<String, Integer> span = new HashMap<String, Integer>();
	public static Map<String, Float> speed = new HashMap<String, Float>();
	public static Map<String, Integer> form = new HashMap<String, Integer>();
	
	public static void registerDanmakuPattern(String patternName, int r_danmaku, int r_span, float r_speed, int r_form)
	{
		danmaku.put(patternName, r_danmaku);
		span.put(patternName, r_span);
		speed.put(patternName, r_speed);
		form.put(patternName, r_form);
		pattern.add(patternName);
	}
	
	public static void setDanmakuPattern()
	{
		switch(THKaguyaConfig.danmakuLevel)
		{
		case 1:
			registerDanmakuPattern("000",    0, 38, 0.3F, THShotLib.SMALL[THShotLib.BLUE]);
			registerDanmakuPattern("001", 2003, 50, 0.2F, THShotLib.SMALL[THShotLib.RED]);
			registerDanmakuPattern("002", 1902, 50, 0.2F, THShotLib.SMALL[THShotLib.GREEN]);
			registerDanmakuPattern("003", 5015, 40, 0.1F, THShotLib.SMALL[THShotLib.YELLOW]);
			registerDanmakuPattern("004",    5, 40, 0.2F, THShotLib.CIRCLE[THShotLib.PURPLE]);
			registerDanmakuPattern("005", 3225, 50, 0.2F, THShotLib.TINY[THShotLib.AQUA]);
			registerDanmakuPattern("006", 4030, 50, 0.15F, THShotLib.CRYSTAL[THShotLib.ORANGE]);
			registerDanmakuPattern("007",  300, 50, 0.20F, THShotLib.KUNAI[THShotLib.AQUA]);
			registerDanmakuPattern("064",    0, 38, 0.3F, THShotLib.LASER[THShotLib.RED]);
			break;
		case 2:
			registerDanmakuPattern("000",    0, 30, 0.3F, THShotLib.SMALL[THShotLib.BLUE]);
			registerDanmakuPattern("001", 2007, 50, 0.2F, THShotLib.SMALL[THShotLib.RED]);
			registerDanmakuPattern("002", 1804, 50, 0.2F, THShotLib.SMALL[THShotLib.GREEN]);
			registerDanmakuPattern("003", 5021, 40, 0.2F, THShotLib.SMALL[THShotLib.YELLOW]);
			registerDanmakuPattern("004",    7, 35, 0.2F, THShotLib.CIRCLE[THShotLib.PURPLE]);
			registerDanmakuPattern("005", 3420, 45, 0.25F, THShotLib.TINY[THShotLib.AQUA]);
			registerDanmakuPattern("006", 4220, 50, 0.15F, THShotLib.CRYSTAL[THShotLib.ORANGE]);
			registerDanmakuPattern("007",  400, 45, 0.25F, THShotLib.KUNAI[THShotLib.AQUA]);
			registerDanmakuPattern("064",    0, 30, 0.3F, THShotLib.LASER[THShotLib.RED]);
			break;
		case 3:
			registerDanmakuPattern("000", 0, 22, 0.4F, THShotLib.SMALL[THShotLib.BLUE]);
			registerDanmakuPattern("001", 2011, 40, 0.3F, THShotLib.SMALL[THShotLib.RED]);
			registerDanmakuPattern("002", 1608, 40, 0.4F, THShotLib.SMALL[THShotLib.GREEN]);
			registerDanmakuPattern("003", 5027, 40, 0.3F, THShotLib.MEDIUM[THShotLib.YELLOW]);
			registerDanmakuPattern("004",   11, 25, 0.4F, THShotLib.CIRCLE[THShotLib.PURPLE]);
			registerDanmakuPattern("005", 3620, 35, 0.3F, THShotLib.SMALL[THShotLib.AQUA]);
			registerDanmakuPattern("006", 4415, 50, 0.25F, THShotLib.CRYSTAL[THShotLib.ORANGE]);
			registerDanmakuPattern("007",  500, 40, 0.35F, THShotLib.KUNAI[THShotLib.AQUA]);
			registerDanmakuPattern("064",    0, 26, 0.4F, THShotLib.LASER[THShotLib.RED]);
			break;
		case 4:
			registerDanmakuPattern("000", 0,  3, 0.5F, THShotLib.SMALL[THShotLib.BLUE]);
			registerDanmakuPattern("001", 2015, 35, 0.4F, THShotLib.MEDIUM[THShotLib.RED]);
			registerDanmakuPattern("002", 1410, 30, 0.6F, THShotLib.MEDIUM[THShotLib.GREEN]);
			registerDanmakuPattern("003", 5027, 40, 0.4F, THShotLib.BIG[THShotLib.YELLOW]);
			registerDanmakuPattern("004",   19, 10, 0.6F, THShotLib.CIRCLE[THShotLib.PURPLE]);
			registerDanmakuPattern("005", 3915, 24, 0.4F, THShotLib.LIGHT[THShotLib.AQUA]);
			registerDanmakuPattern("006", 4810, 35, 0.35F, THShotLib.CRYSTAL[THShotLib.ORANGE]);
			registerDanmakuPattern("007",  700, 15, 0.45F, THShotLib.KUNAI[THShotLib.AQUA]);
			registerDanmakuPattern("064",    0, 20, 0.5F, THShotLib.LASER[THShotLib.RED]);
			break;
		default:
			registerDanmakuPattern("000", 0,  999999, 0.0F, 0);
			registerDanmakuPattern("001", 1,  999999, 0.0F, 0);
			registerDanmakuPattern("002", 2,  999999, 0.0F, 0);
		}
	}
}
