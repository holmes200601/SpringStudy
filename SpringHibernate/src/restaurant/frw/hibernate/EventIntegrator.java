package restaurant.frw.hibernate;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;


/* This class would be auto-detected by hibernate to register EventLister*/
public class EventIntegrator implements org.hibernate.integrator.spi.Integrator {

    @SuppressWarnings("unchecked")
    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory,
            SessionFactoryServiceRegistry serviceRegistry) {
        EventListenerRegistry listenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);
        listenerRegistry.setListeners(EventType.POST_LOAD, ApplicationBeanInitializer.class);

    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        // TODO Auto-generated method stub

    }
}
