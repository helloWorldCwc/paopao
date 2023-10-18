package com.yupi.springbootinit.controller.paopao;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.model.entity.Tags;
import com.yupi.springbootinit.service.TagsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author cwc
 * @Date 2023/10/17 14:50
 * @Version 1.0
 */
@RestController()
@RequestMapping("/tags")
public class TagsController {
    @Resource
    private TagsService tagsService;
    @GetMapping("all")
    public BaseResponse<Object> allTags(){
        List<Tags> list = tagsService.list();
        list = Optional.ofNullable(list).orElse(new ArrayList<>());

        return ResultUtils.success(1);
    }
}
