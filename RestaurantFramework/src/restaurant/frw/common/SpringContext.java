package restaurant.frw.common;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;

@Component
public class SpringContext implements ApplicationContextAware {
	/*
	 * This WebApplicationContext is the ROOT application context. The beans
	 * injected at centralDispatcher-servlet-bean.xml would be in Servlet
	 * WebApplicationContext which inherited from ROOT application context
	 */
	private static XmlWebApplicationContext rootContext;
	private static final String SERVLET_ATTR_NAME = FrameworkServlet.SERVLET_CONTEXT_PREFIX + "centralDispatcher";

	public static <T> T getBean(Class<T> clazz) {
		/*
		 * Steps: 1) Get ServletContext from applicationContext 2) Get Servlet
		 * WebApplicationContext 3) Find bean from Servlet WebApplicationContext
		 */
		T result = null;
		try {
			result = rootContext.getBean(clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result == null) {
			ServletContext sc = rootContext.getServletContext();
			XmlWebApplicationContext servletContext = (XmlWebApplicationContext) sc.getAttribute(SERVLET_ATTR_NAME);
			if (servletContext != null) {
				result = servletContext.getBean(clazz);
			}
		}

		return result;

	}

	public static <T> T getBean(String beanName, Class<T> clazz) {
		T result = null;

		try {
			result = rootContext.getBean(beanName, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result == null) {
			ServletContext sc = rootContext.getServletContext();
			XmlWebApplicationContext servletContext = (XmlWebApplicationContext) sc.getAttribute(SERVLET_ATTR_NAME);
			if (servletContext != null) {
				result = servletContext.getBean(beanName, clazz);
			}
		}

		return result;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContext.rootContext = (XmlWebApplicationContext) applicationContext;
	}

}
