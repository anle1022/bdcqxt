package kq.qh.common;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class WebContextSpringBeanFactory extends javax.servlet.http.HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static WebApplicationContext webAppContext;

	public void init(ServletConfig config) throws ServletException {

		WebApplicationContext webAppContext1 = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		webAppContext = webAppContext1;

	}

	/**
	 *  
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		return webAppContext.getBean(beanName);

	}

}