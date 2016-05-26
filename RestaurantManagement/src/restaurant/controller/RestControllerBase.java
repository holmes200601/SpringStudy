package restaurant.controller;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import restaurant.dozer.custom.mapper.CustomizedDozerBeanMapper;
import restaurant.frw.common.BeanFacade;

public abstract class RestControllerBase implements ServletContextAware {
    private CustomizedDozerBeanMapper mapper;
    private BeanFacade beanFacade;

    protected CustomizedDozerBeanMapper getDozerMapper() {
        return mapper;
    }

    protected BeanFacade getBeanFacade() {
        return beanFacade;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext appContext = WebApplicationContextUtils.findWebApplicationContext(servletContext);
        mapper = appContext.getBean(CustomizedDozerBeanMapper.class);
        beanFacade = appContext.getBean(BeanFacade.class);
    }

    public abstract Object getInstance();

}
