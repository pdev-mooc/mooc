package com.mooc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mooc.domain.Person;

@WebFilter("/views/*")
public class LoginFilter implements Filter {

	public LoginFilter() {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println(" url = " + ((HttpServletRequest) servletRequest).getRequestURL());

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		Person currentUser = (Person) request.getSession().getAttribute("user");
		if (currentUser != null) {
			filterChain.doFilter(request, response);
		} else if (request.getRequestURL().toString().contains("login.jsf")) {
			filterChain.doFilter(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/views/login.jsf");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
