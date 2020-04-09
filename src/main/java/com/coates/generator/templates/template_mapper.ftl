package ${mapperClassPath};

import ${modelClassPath}.${fileName}Entity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * @author ${auther}
 * @create ${time}
 **/
@Repository
@Mapper
public interface ${fileName}Mapper extends BaseMapper<${fileName}Entity> {

}