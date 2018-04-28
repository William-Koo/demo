package com.example.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @描述: 上下文工具类
 * @版权: Copyright(C) 2017
 * @公司:
 * @作者: William-Koo
 * @版本: 1.0
 * @创建日期: 2015-12-15
 * @创建时间: 23:27
 */
public class ContextHolderUtils {

	/**
	 * SpringMvc下获取request
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {

		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * SpringMvc下获取session
	 * 
	 * @return
	 */
	public static HttpSession getSession() {

		return getRequest().getSession();

	}

	/**
	 * 获取 context
	 * @return
	 */
	public static ServletContext getContext(){

		return ContextLoader.getCurrentWebApplicationContext().getServletContext();
	}

	/**
	 * 获取项目根路径
	 * @Description: 获取项目根目录   http://localhost:8080/sc-MDC/
	 * @author Faker
	 * @date 2017-1-2
	 * @version V1.0
	 */
	public static String basepath(){

		HttpServletRequest req = getRequest();
		String path = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath() + "/";
		return path;
	}

	
	/**
	 * 
	* @Description: 获取项目realPath D:\\scxx\\workspace\\sc-MDC\\src\\main\\webapp\\
	* @author Faker  
	* @date 2017-1-2  
	* @version V1.0
	 */
	public static String webAppPath(){

		return System.getProperty("user.dir") +"/";
	}
	
	
}
