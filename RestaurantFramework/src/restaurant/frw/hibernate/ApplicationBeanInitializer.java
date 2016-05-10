package restaurant.frw.hibernate;

import org.hibernate.event.PostLoadEvent;
import org.hibernate.event.PostLoadEventListener;

import restaurant.frw.bean.ApplicationBean;
import restaurant.frw.common.BeanFacade;
import restaurant.frw.common.SpringContext;

public class ApplicationBeanInitializer implements PostLoadEventListener {
    private static final long serialVersionUID = -6793362186119048769L;

    @Override
    public void onPostLoad(PostLoadEvent event) {
        if (event.getEntity() != null && event.getEntity() instanceof ApplicationBean) {
            ApplicationBean ab = (ApplicationBean) event.getEntity();
            ab.setFacade(SpringContext.getBean(BeanFacade.class));
        }
    }

}
