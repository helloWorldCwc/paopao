package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Tags;
import generator.service.TagsService;
import generator.mapper.TagsMapper;
import org.springframework.stereotype.Service;

/**
* @author cc
* @description 针对表【tags(标签)】的数据库操作Service实现
* @createDate 2023-10-17 14:48:10
*/
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags>
    implements TagsService{

}




