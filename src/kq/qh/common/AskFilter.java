package kq.qh.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kq.qh.entity.Menu;
import kq.qh.entity.User;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mysql.jdbc.StringUtils;

public class AskFilter extends OncePerRequestFilter{
	private String url = "";
	public static WebApplicationContext webAppContext;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		url = request.getRequestURI();
		String contextPath = request.getContextPath();
		
		url = url.replace(contextPath, "");
		if(url.contains(".do")){
			url = url.replace( url.substring(url.indexOf(".do")+3),"" );
		}
		System.out.println("url: "+url);
		
		User user = (User) session.getAttribute("user");
		String askUrl = "'"+request.getRequestURL().toString().replace(request.getRequestURI(), "")+contextPath+"";		
		if(user == null){
			if(url.contains("login.do")){
				chain.doFilter(request, response);
				return;
			}else{
				//处理ajax session 失效
				if(null != request.getHeader("x-requested-with") && request.getHeader("x-requested-with").equals("XMLHttpRequest")){
					response.setHeader("sessionstatus", "timeout"); 
				}else{
					response.getWriter().println("<script>window.location="+askUrl+"/login.do'</script>");
				}
				 		
				return;
			}
		}else{
			
			String id = request.getParameter("menuId");
			List<Menu> allMenuList = user.getMenuList();
			Menu m = null;
			for(Menu menu : allMenuList ){
				if( menu.getId().equals(id) ){
					m = menu;
					request.setAttribute("curMenu",menu);
					continue;
				}
			}
			
			if(StringUtils.isNullOrEmpty(id) || null != m){
				chain.doFilter(request, response);
			}else{
				String url = askUrl.substring(0, askUrl.length()-1)+"/forbid.html'";
				response.getWriter().println("<script>window.location="+url+"</script>");
				
			}
		}
		
	}


	/**
	 * ��ʼ��  �����Ƶ�url
	 */
	@Override
	protected void initFilterBean() throws ServletException {
	}


}
