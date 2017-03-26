package com.heima.web.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest req = (HttpServletRequest) request;
		ClassLoader classLoader = req.getClass().getClassLoader();
		Class<?>[] interfaces = req.getClass().getInterfaces();
		InvocationHandler h = new InvocationHandler() {

			boolean flag = true;

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

				if ("POST".equalsIgnoreCase(req.getMethod())) {
					req.setCharacterEncoding("utf-8");
					return method.invoke(req, args);
				}

				if ("GET".equalsIgnoreCase(req.getMethod())) {
					if ("getParameter".equals(method.getName())) {
						String value = (String) method.invoke(req, args);
						try {
							value = new String(value.getBytes("iso8859-1"), "utf-8");
						} catch (Exception e) {
						}
						return value;
					}

					if ("getParameterValues".equals(method.getName())) {
						String[] values = (String[]) method.invoke(req, args);
						if (values != null) {
							for (int i = 0; i < values.length; i++) {
								try {
									values[i] = new String(values[i].getBytes("iso8859-1"), "utf-8");
								} catch (Exception e) {
								}
							}
						}
						return values;
					}

					if (flag) {
						if ("getParameterMap".equals(method.getName())) {
							Map<String, String[]> map = (Map<String, String[]>) method.invoke(req, args);
							for (Map.Entry<String, String[]> entry : map.entrySet()) {
								String[] values = entry.getValue();
								if (values != null) {
									for (int i = 0; i < values.length; i++) {
										try {
											values[i] = new String(values[i].getBytes("iso8859-1"), "utf-8");
										} catch (Exception e) {
										}
									}
								}
								entry.setValue(values);
							}
							flag = false;
							return map;
						}
					}
				}

				Object object = method.invoke(req, args);
				return object;
			}

		};
		HttpServletRequest EnhanceRequest = (HttpServletRequest)Proxy.newProxyInstance(classLoader, interfaces, h);
		chain.doFilter(EnhanceRequest, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
