package com.heima.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.heima.domain.User;
import com.heima.service.UserService;
import com.heima.utils.StrUtils;

/**
 * Servlet Filter implementation class AutoLoginFilter
 */
public class AutoLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AutoLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		// pass the request along the filter chain
		HttpServletRequest req = (HttpServletRequest)request;
		if (req.getSession().getAttribute("user") == null) {
			//如果Session里没有存储Session对象，从Cookie里找username和password
			Cookie[] cookies = req.getCookies();
			String username = null;
			String password = null;
			if (cookies!=null) {
				for (Cookie cookie:cookies) {
					if ("username".equals(cookie.getName())) {
						username = cookie.getValue();
					}
					if ("password".equals(cookie.getName())) {
						password = cookie.getValue();
					}
				}
			}
			User user = UserService.getUserByLogin(username,password);
			if (user!=null) {
				//success
				// save user to session
				req.getSession().setAttribute("user", user);
			}
			
		}
		//System.out.println(req);
		//System.out.println(request);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
