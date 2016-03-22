package restaurant.frw.srv;

import org.springframework.beans.factory.annotation.Autowired;

import restaurant.frw.common.BeanFacade;

public class BasicService {
    @Autowired
    private BeanFacade beanFacade;

    public BeanFacade getBeanFacade() {
        return beanFacade;
    }

    public void setBeanFacade(BeanFacade beanFacade) {
        this.beanFacade = beanFacade;
    } 
}
