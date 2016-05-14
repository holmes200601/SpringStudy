package restaurant.dozer.custom.mapper;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import javax.persistence.OneToMany;

import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import restaurant.frw.bean.ApplicationBean;
import restaurant.frw.bean.SubApplicationBean;

public class CustomizedDozerBeanMapper {
	private CustomDozerBeanMapper dozerMapper;

	public CustomizedDozerBeanMapper(List<String> configLocationList) {
		dozerMapper = new CustomDozerBeanMapper(configLocationList);
	}
	
	protected CustomDozerBeanMapper getDozerMapper() {
		return dozerMapper;
	}

	protected void setDozerMapper(CustomDozerBeanMapper dozerMapper) {
		this.dozerMapper = dozerMapper;
	}

	public <T> T map(Object source, Class<T> destinationClass) throws MappingException {
		T destination = dozerMapper.map(source, destinationClass);
		processChildrenAssociation(destinationClass, destination);
		
		return destination;
	}
	
	public void map(Object source, Object destination) throws MappingException {
		dozerMapper.map(source, destination);
		processChildrenAssociation(destination.getClass(), destination);
	}
	
	private  void processChildrenAssociation(Class<?> destClass, Object dest) {
		if (dest instanceof ApplicationBean) {
			/*
			 * Find the children property by checking OneToMany annotation
			 * Get children property of dest and set parent of each property
			 * */
			ReflectionUtils.doWithFields(destClass, new FieldCallback() {

				@Override
				@SuppressWarnings("unchecked")
				public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
					OneToMany childAnno = field.getAnnotation(OneToMany.class);
					if (childAnno != null) {
						boolean accessable = field.isAccessible();
						field.setAccessible(true);
						Collection<Object> childrenList =  (Collection<Object>) field.get(dest);
						for (Object entity : childrenList) {
							SubApplicationBean child = (SubApplicationBean) entity;
							child.setParent((ApplicationBean)dest);
						}
						field.setAccessible(accessable);
					}					
				}				
			});
		}
	}
	
}
