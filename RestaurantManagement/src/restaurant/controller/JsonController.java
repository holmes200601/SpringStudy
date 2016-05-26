package restaurant.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restaurant.frw.bean.ApplicationBean;
import restaurant.utils.CollectionUtils;

@RestController
@RequestMapping(value = "/rest/{boType}")
public class JsonController extends RestControllerBase {

    @Resource(name = "boTypeProperties")
    private Properties bo2roProperties;

    private Map<String, CollectionUtils.Pair> boTypeMap = new HashMap<String, CollectionUtils.Pair>();

    /*
     * For now, only provide the getting method
     * 
     */
    @RequestMapping(value = "/{id}")
    public Object getEntityById(@PathVariable("boType") String boType, @PathVariable Long id) {
        /*
         * Steps:
         * 1) Translate the bo & ro type
         * 2) Get the bo according to the id
         * 3) Translate the bo to ro and return ro
         */
        // Get bo/ro class pair
        CollectionUtils.Pair boroPair = boTypeMap.get(boType);
        assert (boroPair != null);

        // Get bo
        ApplicationBean boEntity = (ApplicationBean) this.getBeanFacade().loadBean((Class<?>) boroPair.getKey(), id,
                true);

        Object result = null;
        if (boEntity != null) {
            result = this.getDozerMapper().map(boEntity, (Class<?>) boroPair.getValue());
        }

        return result;
    }

    @PostConstruct
    public void initializeBoTypeMap() {
        if (bo2roProperties != null) {
            for (Map.Entry<Object, Object> entry : bo2roProperties.entrySet()) {
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                String[] boRoPair = value.split(",");
                boTypeMap.put(key, new CollectionUtils.Pair(string2Class(boRoPair[0]), string2Class(boRoPair[1])));
            }
        }
    }

    @Override
    public Object getInstance() {
        // TODO Auto-generated method stub
        return null;
    }

    private Class<?> string2Class(String name) {
        Class<?> result = null;

        try {
            result = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

}
