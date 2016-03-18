package restaurant.frw.hibernate;

import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;

import restaurant.frw.bean.ApplicationBean;
import restaurant.frw.bean.BeanFacade;
import restaurant.frw.bean.SpringContext;

public class ApplicationBeanInitializer implements PostLoadEventListener {
    private static final long serialVersionUID = -6793362186119048769L;

    @Override
    public void onPostLoad(PostLoadEvent event) {
        if (event.getEntity() != null) {
            ApplicationBean ab = (ApplicationBean)event.getEntity();
            ab.setFacade(SpringContext.getBean(BeanFacade.class));
        }        
    }

}
