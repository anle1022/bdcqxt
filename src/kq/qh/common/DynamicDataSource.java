package kq.qh.common;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.mysql.jdbc.StringUtils;

/**
 * 切换数据源
 * @author Administrator
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static String getCurrentLookupKey() {  
        return (String) contextHolder.get();  
    }  
    
    public static void setCurrentLookupKey(String currentLookupKey) {  
        contextHolder.set(currentLookupKey);  
    }  
    
	@Override
	protected Object determineCurrentLookupKey() {
		String dataSource = getCurrentLookupKey();
		if(StringUtils.isNullOrEmpty(dataSource)){
			setCurrentLookupKey("local0");
		}
		return dataSource;
	}
	
	
}
