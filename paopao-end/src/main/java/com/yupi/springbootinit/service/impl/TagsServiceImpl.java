package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yupi.springbootinit.mapper.TagsMapper;
import com.yupi.springbootinit.model.entity.Tags;
import com.yupi.springbootinit.service.TagsService;
import org.springframework.stereotype.Service;

/**
* @author cc
* @description 针对表【tags(标签)】的数据库操作Service实现
* @createDate 2023-10-17 16:50:57
*/
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags>
    implements TagsService {

}




