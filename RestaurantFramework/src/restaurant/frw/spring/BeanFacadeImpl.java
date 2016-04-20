package restaurant.frw.spring;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import restaurant.frw.common.BeanFacade;
import restaurant.frw.common.SpringContext;
import restaurant.utils.CollectionUtils;
import restaurant.utils.CollectionUtils.MapKeyValueArray;

@Component
public class BeanFacadeImpl implements BeanFacade {
    @Autowired
    private HibernateTemplate hTemplate;

    @Autowired
    private SpringContext wrapedContext;

    @Override
    public <T> T loadBean(Class<T> beanClazz, Long id) {
        T obj = hTemplate.load(beanClazz, id);

        return obj;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> loadBeans(Class<T> beanClazz, String query, Map<String, Object> paramMap) {
        MapKeyValueArray keyValuePairs = new CollectionUtils.MapKeyValueArray(paramMap);
        List<String> keys = keyValuePairs.getKeys();
        List<Object> values = keyValuePairs.getValues();

        List<T> resultList = (List<T>) hTemplate.findByNamedParam(query, keys.toArray(new String[0]), values.toArray());

        return resultList;
    }

    @SuppressWarnings("static-access")
    @Override
    public <T> T getSpringBean(Class<T> beanClazz) {
        return wrapedContext.getBean(beanClazz);
    }

}
