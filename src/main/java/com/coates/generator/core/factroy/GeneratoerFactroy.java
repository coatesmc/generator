package com.coates.generator.core.factroy;

import com.coates.generator.core.GeneratorService;
import com.coates.generator.core.impl.GeneratorControllerServiceImpl;
import com.coates.generator.core.impl.GeneratorMapperServiceImpl;
import com.coates.generator.core.impl.GeneratorModelServiceImpl;
import com.coates.generator.core.impl.GeneratorServiceServiceImpl;

/**
 * @author coates
 * @create 2018-09-04 10:55
 **/
public class GeneratoerFactroy {

     public enum GeneratoerType {
          MODEL, MAPPER, CONCTROLLER, SERVICE;
     }

     /**
      * 获取生成代码的对象
      * @param type
      * @return
      */
     public static GeneratorService getGenerator(GeneratoerType type){
          GeneratorService service = null;
          switch (type) {
               case MODEL:
                    service = new GeneratorModelServiceImpl();
                    break;
               case MAPPER:
                    service = new GeneratorMapperServiceImpl();
                    break;
               case CONCTROLLER:
                    service = new GeneratorControllerServiceImpl();
                    break;
               case SERVICE:
                    service = new GeneratorServiceServiceImpl();
                    break;
               default:
                    break;
          }
          return service;
     }
}
