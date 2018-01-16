package kq.qh.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import kq.qh.entity.KeyValueEntity;


/**
 * 集合工具类
 * @author Administrator
 *
 */
public class CollectionUtil extends CollectionUtils{
	
	/**
	 * 将数组  @param array 转化成字符串，用 @param splitStr 分隔
	 * @param array
	 * @param splitStr
	 * @return
	 */
	public static String joinSplitStr(String[] array,String splitStr){
		
		StringBuffer str = new StringBuffer();
		for(int i=0;i<array.length;i++){
			str.append(array[i]);
			if(i<array.length-1){
				str.append(splitStr);
			}
		}
		return str.toString();
	}
	
	/**
	 * 将列表  @param list 转化成字符串，用 @param splitStr 分隔
	 * @param list
	 * @param splitStr
	 * @return
	 */
	public static String joinSplitStr(List<String> list,String splitStr){
		Collections.sort(list);
		StringBuffer str = new StringBuffer();
		for(int i=0;i<list.size();i++){
			str.append(list.get(i));
			if(i<list.size()-1){
				str.append(splitStr);
			}
		}
		return str.toString();
	}
	
	/**
	 * 将map转换成  @KeyValueEntity 的集合
	 * map中的key 为 @KeyValueEntity 中的属性 key 
	 * map中的value 为 @KeyValueEntity 中的属性 value 
	 * @param map
	 * @return
	 */
	public static List<KeyValueEntity> transforKVEntity(Map<String,?> map){
		List<KeyValueEntity> list = new ArrayList<KeyValueEntity>();
		Set<?> set = map.keySet();
		Iterator<?> it = set.iterator();
		while(it.hasNext()){
			KeyValueEntity kv = new KeyValueEntity();
			String key = (String) it.next();
			kv.setKey(key);
			kv.setValue(map.get(key).toString());
			list.add(kv);
		}
		return list;
	}

}
