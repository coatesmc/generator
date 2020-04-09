package com.coates.generator;

import com.coates.generator.core.Param;
import com.coates.generator.core.factroy.GeneratoerFactroy;
import com.coates.generator.util.DatasourceUtils;
import com.coates.generator.util.PropertyUtils;
import com.coates.generator.util.StringUtils;

import java.io.File;

/**
 * 一键无痛苦，自动生成 model、service、controller、service
 * <p>
 * 文件生成位置destFilePath
 *
 * @author coates
 * @create 2018-09-04 9:36
 **/
public class GeneratorCode {

    /**
     * 生成文件的地址
     */
    private static String destFilePath = "D://generatore";
    /**
     * model包地址
     */
    private static String modelPackagePath = "com.coates.generator.model";
    /**
     * controller的包地址
     */
    private static String controllerClassPath = "com.coates.generator.controller";
    /**
     * service的包地址
     */
    private static String serviceClassPath = "com.coates.generator.service";
    /**
     * mapper的包地址
     */
    private static String mapperClassPath = "com.coates.generator.mapper";

    private static String tableName = "oauth_client_details";

    private static Param param = new Param();

    public static void main(String[] args) {
        String tableNames = PropertyUtils.getValue("table.name");
        String[] tbs = tableNames.split(",");
        for (String tname : tbs) {
            tableName = tname;
            loadConfig();
            loadDataSource();
            generatorModel();
            generatorMapper();
            generatorService();
            generatorController();
        }

    }

    /**
     * 加载配置文件
     */
    public static void loadConfig() {
        destFilePath = !StringUtils.isEmpty(PropertyUtils.getValue("dest.file.path")) ? PropertyUtils.getValue("dest.file.path") : destFilePath;
        modelPackagePath = !StringUtils.isEmpty(PropertyUtils.getValue("model.package.path")) ? PropertyUtils.getValue("model.package.path") : modelPackagePath;
        controllerClassPath = !StringUtils.isEmpty(PropertyUtils.getValue("controller.class.path")) ? PropertyUtils.getValue("controller.class.path") : controllerClassPath;
        serviceClassPath = !StringUtils.isEmpty(PropertyUtils.getValue("service.class.path")) ? PropertyUtils.getValue("service.class.path") : serviceClassPath;
        mapperClassPath = !StringUtils.isEmpty(PropertyUtils.getValue("mapper.class.path")) ? PropertyUtils.getValue("mapper.class.path") : mapperClassPath;

        File destFile = new File(destFilePath);
        if (!destFile.exists()) {
            destFile.mkdir();
        }

        //tableName = PropertyUtils.getValue("table.name");
        if (StringUtils.isEmpty(tableName)) {
            System.err.println("请检查配置文件table.name属性的值是否配置？");
            return;
        }
        param.setTableName(tableName);
        param.setAuther(PropertyUtils.getValue("auther"));

        param.setControllerClassPath(controllerClassPath);
        param.setDestFilePath(destFilePath);
        param.setModelPackagePath(modelPackagePath);
        param.setServiceClassPath(serviceClassPath);
        param.setMapperClass(mapperClassPath);

        if (tableName.indexOf("_") > 0) {
            String[] names = tableName.split("_");
            String newName = "";
            for (String name : names) {
                newName = newName + DatasourceUtils.getInstance().initcap(name);
            }
            //newName = newName.substring(1, newName.length());
            param.setFileName(newName);
        } else {
            param.setFileName(tableName);
        }


        System.out.println("=======================配置文件加载成功！============================");
    }

    /**
     * 加载数据源
     */
    public static void loadDataSource() {
        DatasourceUtils.load();
    }

    /**
     * 根据模板生成model
     */
    public static void generatorModel() {
        GeneratoerFactroy.getGenerator(GeneratoerFactroy.GeneratoerType.MODEL).generator(param);
    }

    /**
     * 根据模板生成controller
     */
    public static void generatorController() {
        GeneratoerFactroy.getGenerator(GeneratoerFactroy.GeneratoerType.CONCTROLLER).generator(param);
    }

    /**
     * 根据模板生成service
     */
    public static void generatorService() {
        GeneratoerFactroy.getGenerator(GeneratoerFactroy.GeneratoerType.SERVICE).generator(param);
    }

    /**
     * 根据模板生成mapper
     */
    public static void generatorMapper() {
        GeneratoerFactroy.getGenerator(GeneratoerFactroy.GeneratoerType.MAPPER).generator(param);
    }

}
