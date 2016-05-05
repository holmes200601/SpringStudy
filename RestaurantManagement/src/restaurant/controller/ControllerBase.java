package restaurant.controller;

import javax.servlet.ServletContext;

import org.dozer.DozerBeanMapper;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import restaurant.frw.common.BeanFacade;

public abstract class ControllerBase implements ServletContextAware {
	private DozerBeanMapper mapper;
	private BeanFacade beanFacade;

	protected DozerBeanMapper getDozerMapper() {
		return mapper;
	}

	protected BeanFacade getBeanFacade() {
		return beanFacade;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		WebApplicationContext appContext = WebApplicationContextUtils.findWebApplicationContext(servletContext);
		mapper = appContext.getBean(DozerBeanMapper.class);
		beanFacade = appContext.getBean(BeanFacade.class);
	}

	public abstract Object getInstance();

}
