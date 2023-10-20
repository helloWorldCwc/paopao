package com.yupi.springbootinit.controller.paopao;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.model.dto.tags.TagsDto;
import com.yupi.springbootinit.model.entity.Tags;
import com.yupi.springbootinit.service.TagsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author cwc
 * @Date 2023/10/17 14:50
 * @Version 1.0
 */
@RestController()
@RequestMapping("/tags")
@Api(tags = "标签管理")
public class TagsController {
    @Resource
    private TagsService tagsService;
    @GetMapping("all")
    @ApiOperation("查询所有标签")
    public BaseResponse<List<TagsDto>> allTags(){
        List<Tags> list = Optional.ofNullable(tagsService.list()).orElse(new ArrayList<Tags>());
        List<TagsDto> allTags = list.stream()
                .filter(tag -> tag.getIsParent() == 1)
                .map(TagsDto::tagsToTagsDto)
                .collect(Collectors.toList());
        allTags.forEach(dto -> list.forEach(tag -> {
            if(Objects.equals(dto.getId(), tag.getParentId()) && tag.getIsParent() == 0){
                List<TagsDto> children = dto.getChildren();
                TagsDto tagsDto = new TagsDto();
                tagsDto.setText(tag.getTagName());
                tagsDto.setId(tag.getId());
                children.add(tagsDto);
            }
        }));
        return ResultUtils.success(allTags);
    }
}
