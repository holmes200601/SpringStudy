package restaurant.converter;

import org.dozer.DozerConverter;

import restaurant.dto.association.AssociationInfo;
import restaurant.frw.bean.ApplicationBean;

public class AssociationConverter extends DozerConverter<AssociationInfo, ApplicationBean> {

    public AssociationConverter(Class<AssociationInfo> prototypeA, Class<ApplicationBean> prototypeB) {
        super(prototypeA, prototypeB);

    }

    @Override
    public ApplicationBean convertTo(AssociationInfo source, ApplicationBean destination) {
        // TODO Auto-generated method stub
        return destination;
    }

    @Override
    public AssociationInfo convertFrom(ApplicationBean source, AssociationInfo destination) {

        return destination;
    }

}
