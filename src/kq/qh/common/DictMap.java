package kq.qh.common;

import java.util.HashMap;
import java.util.Map;

public class DictMap {

	public static Map<String,Map<String,String>> dictMap = new HashMap<String,Map<String,String>>();
	
	static{
		//不动产状态
		Map<String,String> map = new HashMap<String,String>();
		map.put("0", "无效");
		map.put("1", "有效");
		dictMap.put("zt", map);
		Map<String,String> map1 = new HashMap<String,String>();
		map1.put("1", "个人");
		map1.put("2", "企业");
		map1.put("3", "事业单位");
		map1.put("4", "国家机关");
		map1.put("99", "其它");
		dictMap.put("qlrlb", map1);
		Map<String,String> map2 = new HashMap<String,String>();
		map2.put("1", "身份证");
		map2.put("2", "港澳台身份证");
		map2.put("3", "护照");
		map2.put("4", "户口簿");
		map2.put("5", "军官证（士兵证）");
		map2.put("6", "组织机构代码");
		map2.put("7", "营业执照");
		map2.put("99", "其它");
		dictMap.put("qlrzjlx", map2);
		
	}
}
