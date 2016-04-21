package restaurant.frw.common;

import java.util.List;
import java.util.Map;

import restaurant.frw.bean.ApplicationBean;

public interface BeanFacade {
    /*
     * This function loads bean from database.
     * IF forceLoad == true,
     * THEN the returned object is initialized with REAL data. Resource consuming.
     * ELSE
     * THEN the returned object is a proxy object
     *
     */
    public <T> T loadBean(Class<T> beanClazz, Long id, Boolean forceLoad);

    public <T> List<T> loadBeans(Class<T> beanClazz, String query, Map<String, Object> paramMap);

    public Long saveBean(ApplicationBean bean);

    public void saveOrUpdate(ApplicationBean bean);
}
