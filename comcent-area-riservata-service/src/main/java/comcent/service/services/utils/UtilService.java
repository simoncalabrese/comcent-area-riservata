package comcent.service.services.utils;

import comcent.common.builders.QueryParamsBuilder;
import comcent.service.dto.base.ConcreteDTO;
import comcent.service.exceptions.BaseException;
import comcent.service.exceptions.Suppliers;
import comcent.service.services.AbstractService;
import comcent.service.services.ApiEnum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by simon.calabrese on 14/12/2017.
 */
@Service
public class UtilService extends AbstractService {

    public List<Map> getDocumentLinks() throws BaseException {
        return doGetCallList(Map.class, ApiEnum.GET_DOCS, QueryParamsBuilder.getBuilder());
    }

    public ConcreteDTO addDoc(final Map<String,String> couple) throws BaseException {
        if(couple.get("url").startsWith("http://")){
            couple.replace("url",couple.get("url").replace("http://",""));
        }
        if(doPostCall(String.class,ApiEnum.ADD_DOCS,couple,e -> e.equals("0"))) {
            return new ConcreteDTO();
        } else {
          throw new BaseException(Suppliers.INSERT_DOC);
        }
    }

    public ConcreteDTO delDoc(final String name) throws BaseException {
        if(doGetCall(String.class,ApiEnum.DEL_DOC,QueryParamsBuilder.getBuilder().appendParams("name",name),e -> e.equals("1"))) {
            return new ConcreteDTO();
        } else {
            throw new BaseException(Suppliers.DEL_DOC);
        }
    }
}
