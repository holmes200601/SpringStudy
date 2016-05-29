package restaurant.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import restaurant.frw.bean.ApplicationBean;
import restaurant.frw.common.SpringContext;
import restaurant.utils.CollectionUtils;

@RestController
@RequestMapping(value = "/rest/{boType}")
public class JsonController extends ControllerBase {

    @Resource(name = "boTypeProperties")
    private Properties bo2roProperties;
    
    private final String OBJECT_MAPPER_NAME = "objectMapper";

    private Map<String, CollectionUtils.Pair> boTypeMap = new HashMap<String, CollectionUtils.Pair>();

    /*
     * For now, only provide the getting method
     * 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getEntityById(@PathVariable("boType") String boType, @PathVariable Long id) {
        /*
         * Steps:
         * 1) Translate the bo & ro type
         * 2) Get the bo according to the id
         * 3) Translate the bo to ro and return ro
         */
    	
    	Object objectMapper = SpringContext.getBean(com.fasterxml.jackson.databind.ObjectMapper.class);
    	
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

        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Void> createEntity(@PathVariable("boType") String boType, @RequestBody String objectString) {
    	/* Steps:
    	 * 1) Get bo/ro class pair
    	 * 2) Convert the post string to corresponding ro entity
    	 * 3) Convert the ro entity to bo entity
    	 * 4) Create the bo entity
    	 * */
    	// Get bo/ro class pair
    	CollectionUtils.Pair boroPair = boTypeMap.get(boType);
    	assert (boroPair != null);
    	
    	// Convert posting string to corresponding ro entity
    	ObjectMapper objectMapper = SpringContext.getBean(OBJECT_MAPPER_NAME, ObjectMapper.class);
    	try {
			Object roEntity = objectMapper.readValue(objectString, (Class<?>)boroPair.getValue());
			Object boEntity = this.getDozerMapper().map(roEntity, (Class<?>)boroPair.getKey());
			this.getBeanFacade().saveOrUpdate((ApplicationBean)boEntity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return new ResponseEntity<Void>(HttpStatus.CREATED);
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
