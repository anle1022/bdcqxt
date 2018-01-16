package kq.qh.util;

import java.util.Date;

public class TransformUtils {
	
	/**
	 * 转为取号号码
	 * @param format
	 * @param year
	 * @param number
	 * @return
	 */
	public static String getQHNumber(String format,int year,String number){
		Date date = new Date();
		format = format.replace("year", year+"")
				.replace("day", DateUtil.getDay(date)+"" )
				.replace("month", DateUtil.getMonth(date)+"" )
				.replace("number", number );
		return format;
	}
}
