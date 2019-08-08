package com.coates.generator.core.impl;

import com.coates.generator.core.GeneratorService;
import com.coates.generator.core.Param;
import com.coates.generator.util.DatasourceUtils;

/**
 * @author coates
 * @create 2018-09-04 10:57
 **/
public class GeneratorModelServiceImpl implements GeneratorService {

     @Override
     public void generator(Param param) {
          DatasourceUtils.getInstance().generatorModel(param);
          System.out.println("=======================model生成成功！============================");
     }

}
