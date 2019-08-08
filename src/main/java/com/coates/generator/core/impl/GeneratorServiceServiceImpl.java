package com.coates.generator.core.impl;

import com.coates.generator.core.GeneratorService;
import com.coates.generator.core.Param;
import com.coates.generator.util.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author coates
 * @create 2018-09-04 10:57
 **/
public class GeneratorServiceServiceImpl implements GeneratorService {

     @Override
     public void generator(Param param) {
          Writer out = null;
          try {
               // step1 创建freeMarker配置实例
               Configuration configuration = new Configuration();
               // step2 获取模版路径
               String rootJavaPath = new StringBuffer(System.getProperty("user.dir")).append("/").append(param.getSrcFolder()).append("/").toString();
               configuration.setDirectoryForTemplateLoading(new File(rootJavaPath + param.getTemplatePath()));
               // step3 创建数据模型
               Map<String, Object> dataMap = new HashMap<String, Object>();
               String fileName = param.getFileName();
               dataMap.put("fileName", fileName);
               dataMap.put("mapper", fileName.substring(0, 1).toLowerCase() + fileName.substring(1, fileName.length()));
               dataMap.put("mapperClassPath", param.getMapperClass());
               dataMap.put("serviceClassPath", param.getServiceClassPath());
               dataMap.put("modelClassPath", param.getModelPackagePath());
               dataMap.put("auther", param.getAuther());
               dataMap.put("time", StringUtils.getDate());
               // step4 加载模版文件
               Template template = configuration.getTemplate("template_service.ftl");
               // step5 生成数据
               File docFile = new File(param.getDestFilePath() + "\\" + param.getFileName() + "Service.java");
               if(!docFile.exists()) {
                    docFile.createNewFile();
               }
               out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
               // step6 输出文件
               template.process(dataMap, out);

               System.out.println("=======================service生成成功！============================");
          } catch (Exception e) {
               e.printStackTrace();
          }
     }

}
