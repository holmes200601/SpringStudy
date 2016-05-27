package restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import restaurant.ro.uom.UomGroupRO;
import restaurant.ro.uom.UomRO;
import restaurant.uom.bean.entity.Uom;
import restaurant.uom.bean.entity.UomGroup;
import restaurant.utils.ReflectionUtilsPlus;

@Controller
@RequestMapping(value = "/uom")
public class UomController extends ControllerBase {

    /*
     * 1) Get all UomGroup list
     * 2) Get all Uom of a UomGroup
     * 3) Create UomGroup
     * 4) Create Uom of a UomGroup
     */

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UomRO getUom(@PathVariable Long id) {
        Uom uom = getBeanFacade().loadBean(Uom.class, id, true);
        UomRO result = getDozerMapper().map(uom, UomRO.class);

        return result;
    }

    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    public UomGroupRO getUomGroup(@PathVariable Long id) {
        UomGroup loadedGroup = getBeanFacade().loadBean(UomGroup.class, id, true);
        UomGroupRO result = getDozerMapper().map(loadedGroup, UomGroupRO.class);

        return result;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Long createUom(@RequestBody UomRO uom) {
        Uom uomEntity = getDozerMapper().map(uom, Uom.class);
        Long result = getBeanFacade().saveBean(uomEntity);

        return result;
    }

    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public Long createUomGroup(@RequestBody UomGroupRO uomGroup) {
        UomGroup groupEntity = getDozerMapper().map(uomGroup, UomGroup.class);
        Long result = getBeanFacade().saveBean(groupEntity);

        return result;
    }

    @RequestMapping(value = "/group/{id}", method = RequestMethod.PATCH)
    public Long udpateUomGroup(@RequestBody UomGroupRO uomGroup, @PathVariable Long id) {
        UomGroup group = getBeanFacade().loadBean(UomGroup.class, id, true);
        getDozerMapper().map(uomGroup, group);
        getBeanFacade().saveOrUpdate(group);

        return group.getId();
    }

    @RequestMapping(value = "/group/example", method = RequestMethod.GET)
    public Object getInstanceOne() {
        return ReflectionUtilsPlus.newInstance(UomGroupRO.class);
    }
}
