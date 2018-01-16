package kq.qh.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @Package kq.qh.util
 *  <p>Copyright: Copyright (c) 2016 </p>
 *  <p>Company:苍穹广州技术中心</p>
 * 
 * @title 
 * @author ylf
 * @date 2016年8月29日 上午10:50:36
 * @version V1.0
 */
/**
 * @Package kq.qh.util
 *  <p>Copyright: Copyright (c) 2016 </p>
 *  <p>Company:苍穹广州技术中心</p>
 * 
 * @title 
 * @author ylf
 * @date 2016年8月29日 上午10:50:36
 * @version V1.0
 */
public class PatternUtil {

	public static boolean match(String target,String regex){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(target);
		return matcher.find();
	}
	
	public static void main(String[] args) {
		String target = "asda_0101";
		String regex = "^([a-z,A-Z]+)_[1-9]([0-9]{3})$";
		System.out.println(PatternUtil.match(target, regex));
	}
}
