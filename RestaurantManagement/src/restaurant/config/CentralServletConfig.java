package restaurant.config;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Component
public class CentralServletConfig implements ServletContextAware {
    private ServletContext servletContext;

    @Bean(name = "thymeleafTemplateResolver")
    public ServletContextTemplateResolver getThymeleafTemplateResolver() {
        ServletContextTemplateResolver sctr = new ServletContextTemplateResolver(this.servletContext);

        sctr.setTemplateMode("HTML5");
        sctr.setPrefix("/WEB-INF/html-template/");
        sctr.setSuffix(".html");
        sctr.setCharacterEncoding("UTF-8");

        return sctr;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
