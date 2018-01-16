package kq.qh.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * @Package kq.qh.util
 * Copyright: Copyright (c) 2016 
 * Company:苍穹广州技术中心
 *
 * @author ylf
 * @date 2016年8月16日 下午3:20:50
 * @version V1.0
 */
public class DateUtil {
	

	public static Date date = null;

	public static DateFormat dateFormat = null;

	public static Calendar calendar = null;

	/**
	 * 功能描述：格式化日期
	 * 
	 * @param dateStr
	 *            String 字符型日期
	 * @param format
	 *            String 格式
	 * @return Date 日期
	 */
	public static Date parseDate(String dateStr, String format) {
		try {
			dateFormat = new SimpleDateFormat(format);
			String dt = dateStr.replaceAll("-", "/");
			if ((!dt.equals("")) && (dt.length() < format.length())) {
				dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]",
						"0");
			}
			date = (Date) dateFormat.parse(dt);
		} catch (Exception e) {
		}
		return date;
	}

	/**
	 * 功能描述：格式化日期
	 * 
	 * @param dateStr
	 *            String 字符型日期：YYYY-MM-DD 格式
	 * @return Date
	 */
	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, "yyyy/MM/dd");
	}

	/**
	 * 功能描述：格式化输出日期
	 * 
	 * @param date
	 *            Date 日期
	 * @param format
	 *            String 格式
	 * @return 返回字符型日期
	 */
	public static String format(Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				dateFormat = new SimpleDateFormat(format);
				result = dateFormat.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 功能描述：
	 * 
	 * @param date
	 *            Date 日期
	 * @return
	 */
	public static String format(Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * 功能描述：返回年份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回年份
	 */
	public static int getYear(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 功能描述：返回月份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能描述：返回日份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回日份
	 */
	public static int getDay(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能描述：返回小时
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能描述：返回分钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 功能描述：返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	/**
	 * 功能描述：返回字符型日期
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型日期 yyyy/MM/dd 格式
	 */
	public static String getDate(Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * 功能描述：返回字符型时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型时间 HH:mm:ss 格式
	 */
	public static String getTime(Date date) {
		return format(date, "HH:mm:ss");
	}

	/**
	 * 功能描述：返回字符型日期时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型日期时间 yyyy/MM/dd HH:mm:ss 格式
	 */
	public static String getDateTime(Date date) {
		return format(date, "yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * 功能描述：日期相加
	 * 
	 * @param date
	 *            Date 日期
	 * @param day
	 *            int 天数
	 * @return 返回相加后的日期
	 */
	public static Date addDate(Date date, int day) {
		calendar = Calendar.getInstance();
		long millis = getMillis(date) + ((long) day) * 24 * 3600 * 1000;
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	/**
	 * 功能描述：日期相减
	 * 
	 * @param date
	 *            Date 日期
	 * @param date1
	 *            Date 日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	/**
	 * 功能描述：取得指定月份的第一天
	 * 
	 * @param strdate
	 *            String 字符型日期
	 * @return String yyyy-MM-dd 格式
	 */
	public static String getMonthBegin(String strdate) {
		date = parseDate(strdate);
		return format(date, "yyyy-MM") + "-01";
	}

	/**
	 * 功能描述：取得本月的第一天
	 * @return String yyyy-MM-01 格式
	 */
	public static String getNowMonthBegin() {
		Date date = new Date();
		return format(date, "yyyy-MM") + "-01";
	}

	/**
	 * 功能描述：取得指定月份的最后一天
	 * 
	 * @param strdate
	 *            String 字符型日期
	 * @return String 日期字符串 yyyy-MM-dd格式
	 */
	public static String getMonthEnd(String strdate) {
		date = parseDate(getMonthBegin(strdate));
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDate(calendar.getTime());
	}

	/**
	 * 功能描述：常用的格式化日期
	 * 
	 * @param date
	 *            Date 日期
	 * @return String 日期字符串 yyyy-MM-dd格式
	 */
	public static String formatDate(Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd");
	}

	/**
	 * 功能描述：以指定的格式来格式化日期
	 * 
	 * @param date
	 *            Date 日期
	 * @param format
	 *            String 格式
	 * @return String 日期字符串
	 */
	public static String formatDateByFormat(Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}


	/**
	 * 功能描述:字符转换为时间 
	 * @param sdate  需要转换的字符 字符格式:yyyyddmm HH:MM
	 * @return 时间 
	 */
	public static Date convetDate(String sdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyddmm HH:MM");
		Date date = null;
		try {
			date = sdf.parse(sdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 功能描述:将时间转换(至1970-01-01时间后)的秒数
	 * @param sdate 格式:(yyyy-MM-dd HH:mm:ss")
	 * @return
	 */
	public static long getSeconds(String sdate) {
		
		SimpleDateFormat sdf = null;

		if(sdate.length()>12)
		{
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}else
		{
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}
		
		Date date1 = null;
		try {
			date1 = sdf.parse(sdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar1 = Calendar.getInstance(Locale.CHINA);
		calendar1.setTime(date1);

		Date date2 =  stringToDate("1970-01-01", "yyyy-MM-dd");
		Calendar calendar2 = Calendar.getInstance( Locale.CHINA );
		calendar2.setTime(date2);
		
		return   calendar1.getTimeInMillis()/1000L - calendar2.getTimeInMillis()/1000L  ;
	}
	
	
	/**
	 * 功能描述:将(至1970-01-01时间后)的秒数转换为时间
	 * @param seconds  描述
	 * @return 格式:(yyyy-MM-dd HH:mm:ss)
	 */
	public static String getDates(long seconds) {
		
		Date date1 = stringToDate("1970-01-01", "yyyy-MM-dd");
		Calendar calendar1 = Calendar.getInstance( Locale.CHINA );
		calendar1.setTime(date1);
		
		Calendar calendar3 = Calendar.getInstance( Locale.CHINA);
		calendar3.setTimeInMillis( seconds*1000L +   calendar1.getTimeInMillis()  );
	
		return   dateToString(calendar3.getTime(), "yyyy-MM-dd HH:mm:ss")  ;
	}

	/**
	 * 功能描述:时间转换为字符 
	 * @param date  时间
	 * @return 时间字符串 字符格式:yyyy-MM-dd 
	 */
	public static String DateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if(date == null)
		{
			date = new Date();
		}
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 功能描述:时间转换为字符
	 * @param date
	 * @return 时间字符串  字符格式:yyyy-MM-dd HH:mm:ss
	 */
	public static String DateToString2(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = "";
		try
		{
			 dateString = formatter.format(date);	
		}catch(Exception e)
		{
			return dateString;
		}
		
		return dateString;
	}

	/**
	 * 功能描述:系统当前的时间
	 * @return 时间
	 */
	public static Date getServerTime() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * 功能描述:将时间转换为指定的格式类
	 * 
	 * @param date  转换的时间
	 * @param str   格式 注意:(24小时制格式:yyyy-MM-dd HH:mm:ss)
	 * @return 时间字符串 
	 */
	public static String dateToString(Date date, String str)  {
		String dates ="1900-00-00";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(str);
			dates = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dates;
	}

	/**
	 * 功能描述:将指定格式的字符转换为date的类
	 * @param str   字符串
	 * @param df  格式 :(24小时制格式:yyyy-MM-dd HH:mm:ss)
	 * @return 时间
	 */
	public static Date stringToDate(String str, String df) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(df);
		try {
			calendar.setTime(sdf.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calendar.getTime();
	}

	/**
	 * 两个日期型日期之间的时间天数。
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间1
	 * @return 天数
	 */
	public static int getDateNum(Date date1, Date date2) {
		return Math
				.abs((int) ((date1.getTime() - date2.getTime()) / 1000 / 60 / 60 / 24));
	}

	/**
	 * 两个字符格式日期之间的天数
	 * 
	 * @param sd1
	 *            时间1
	 * @param sd2
	 *            时间1
	 * @param df
	 *            格式
	 * @return 天数
	 */
	public static int getDateNum(String sd1, String sd2, String df) {
		Date date1 = stringToDate(sd1, df);
		Date date2 = stringToDate(sd2, df);
		return getDateNum(date1, date2);
	}

	/**
	 * 两个字符格式日期之间的每天的字符值
	 * 
	 * @param sd1
	 *            时间1
	 * @param sd2
	 *            时间2
	 * @param df
	 *            格式
	 * @param df2
	 *            格式
	 * @return 字符串数组
	 */
	public static String[] getDateNumStr(String sd1, String sd2, String df,
			String df2) throws Exception {
		Date date1 = stringToDate(sd1, df);
		Date date2 = stringToDate(sd2, df);
		int num = getDateNum(date1, date2) + 1;
		String[] dateStr = new String[num];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		for (int i = 0; i < num; i++) {
			dateStr[i] = dateToString(calendar.getTime(), df2);
			calendar.add(Calendar.DAY_OF_YEAR, +1);
		}
		return dateStr;
	}
	
	
	/**
	 * 两个字符格式日期之间的每天的日期对象
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return 时间间隔日期
	 */
	public static List getBetweenDate(Date date1 ,Date date2) {
		List list = new ArrayList();
		
		int num = getDateNum(date1, date2) + 1;
		String[] dateStr = new String[num];
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		for (int i = 0; i < num; i++) {
			list.add(calendar.getTime());
			calendar.add(Calendar.DAY_OF_YEAR, +1);
		}
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<String> getBetweenDate(String startTime,String endTime){
		Date d1 = parseDate(startTime);
		Date d2 = parseDate(endTime);
		List list = getBetweenDate(d1,d2);
		List<String> timeList = new ArrayList();
		for(int i=0;i<list.size();i++){
			Date d = (Date) list.get(i);
			String time = formatDate(d);
			System.out.println( time );
			timeList.add(  time );
			
		}
		return timeList;
	}
	
	/**
	 * 本年剩余天数。
	 * 
	 * @param date
	 *            时间
	 * @return 天数
	 */
	public static int getRsYearNum(Date date) throws Exception {
		String year = dateToString(date, "yyyy");
		Date dateLast = stringToDate(year + "-12-31", "yyyy-MM-dd");
		return getDateNum(date, dateLast);
	}

	/**
	 * 本月天数
	 * 
	 * @param date
	 *            时间
	 * @return 天数
	 */
	public static int getMonthNum(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, +1);
		Date date2 = calendar.getTime();
		return getDateNum(date, date2);
	}

	/**
	 * 本月剩余天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getRsMonthNum(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, +1);
		Date date2 = calendar.getTime();
		return getDateNum(date, date2) - calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 本周剩余天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getRsWeekNum(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return 7 - calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 得到指定日期n天后的日期
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getAfterDate(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, n);
		return calendar.getTime();
	}
	

	/**
	 * 得到指定日期n月后的日期
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getAfterMonth(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, n);
		return calendar.getTime();
	}

	/**
	 * 数据类型转换 0.0 -> #0.00
	 * 
	 * @param f
	 * @return
	 */
	public static String getTwoDecimal(float f) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
		String new_DCJD = df.format(f);
		return new_DCJD;
	}
	
	
	/**
	 * 数据类型转换 0.0 -> #0.000
	 * 
	 * @param f
	 * @return
	 */
	public static String getThreeDecimal(float f) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#0.000");
		String new_DCJD = df.format(f);
		return new_DCJD;
	}
	

	/**
	 * 获取今天年份
	 * 
	 * @return 字符串年份
	 */
	public static String getYear() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String year;
		year = dateString.substring(0, 4);
		return year;
	}

	/**
	 * 获取今天月份
	 * 
	 * @return 字符串月份
	 */
	public static String getNowMonth() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String month;
		month = dateString.substring(5, 7);
		return month;
	}

	/**
	 * 获取今天日数
	 * @return 字符串日数
	 */
	public static String getNowDay() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String day;
		day = dateString.substring(8, 10);
		return day;
	}
	
	
	/**
	 * 获取今天日数
	 * 
	 * @return 字符串日数
	 */
	public static String getNowYearMonth() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	/**
	 * 获取今天日期
	 * 格式:(yyyy-MM-dd HH:mm:ss)
	 * @return 字符串日日期
	 */
	public static String getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	
	/**
	 * 功能描述：检查时间是否在 开始时间是否大于结束时间 范围内
	 * @param kssj 开始时间
	 * @param jssj 结束时间
	 * @param time
	 * @return
	 */
	public static boolean getTimeInOrOutTime(String kssj,String jssj,String time)
	{
		boolean check = true;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Calendar calBegin = java.util.Calendar.getInstance();
		java.util.Calendar calEnd = java.util.Calendar.getInstance();
		java.util.Calendar calNow = java.util.Calendar.getInstance();
		Date beginDate = null;
		Date endDate = null;
		Date nowDate = null;
		try {
			beginDate = sdf.parse(kssj);
			endDate = sdf.parse(jssj);
			nowDate = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calBegin.setTime(beginDate);
		calEnd.setTime(endDate);
		calNow.setTime(nowDate);
		if(calBegin.after(calNow)){
			//不在时间内
            check = false;
		}
		if(calEnd.before(calNow)){
			//不在时间内
			check = false;
		}
		
		return check;
	}
	
	
	/**
	 * 功能描述：开始时间是否大于结束时间
	 * @param kssj 开始时间
	 * @param jssj 结束时间
	 * @param time
	 * @return
	 */
	public static boolean getTime(String kssj,String jssj,String date_sdf)
	{
		boolean check = false;
		
		SimpleDateFormat sdf = new SimpleDateFormat(date_sdf);
		java.util.Calendar calBegin = java.util.Calendar.getInstance();
		java.util.Calendar calEnd = java.util.Calendar.getInstance();
		Date beginDate = null;
		Date endDate = null;
		try {
			beginDate = sdf.parse(kssj);
			endDate = sdf.parse(jssj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calBegin.setTime(beginDate);
		calEnd.setTime(endDate);
		
		if(calEnd.getTimeInMillis()>calBegin.getTimeInMillis())
		{
			check  = true;
		}
		
		return check;
	}
	public static void main(String[] args) {
		//Date d = new Date();
		// System.out.println(d.toString());
//		System.out.println(formatDate(d).toString());
		// System.out.println(getMonthBegin(formatDate(d).toString()));
		// System.out.println(getMonthBegin("2008/07/19"));
		// System.out.println(getMonthEnd("2008/07/19"));
		//System.out.println(addDate(d,15).toString());
		System.out.println(getAfterDate(new Date(), -1));
		//getBetweenDate("2016-11-22","2016-12-02");
	}

}
