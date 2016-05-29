package restaurant.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import restaurant.dozer.custom.mapper.CustomizedDozerBeanMapper;
import restaurant.ro.uom.UomGroupRO;
import restaurant.uom.bean.entity.UomGroup;

@Controller
@RequestMapping(value = "/uom")
public class UomController extends ControllerBase {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getUomManagement() {
    	ModelAndView result = new ModelAndView();
    	result.setViewName("uom_management");
    	
    	List<UomGroupRO> roList = new LinkedList<UomGroupRO>();
    	List<UomGroup> boList = this.getBeanFacade().loadBeans(UomGroup.class, "SELECT ug FROM UomGroup ug", null);
    	if (boList != null) {
    		CustomizedDozerBeanMapper dozerMapper = this.getDozerMapper();
    		for (UomGroup bo : boList) {
    			roList.add(dozerMapper.map(bo, UomGroupRO.class));
    		}
    	}
    	result.addObject("title", "Uom Management");
    	result.addObject("uomGroupList", roList);
    	
    	return result;
    }
}
