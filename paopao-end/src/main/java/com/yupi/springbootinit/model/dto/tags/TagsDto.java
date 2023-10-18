package com.yupi.springbootinit.model.dto.tags;

import com.yupi.springbootinit.model.entity.Tags;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cwc
 * @Date 2023/10/18 15:17
 * @Version 1.0
 */
@Data
public class TagsDto {
    private String text;
    private Long id;
    private List<TagsDto> children;

    public static TagsDto tagsToTagsDto(Tags tags){
        TagsDto tagsDto = new TagsDto();
        tagsDto.setId(tags.getId());
        tagsDto.setText(tags.getTagName());
        tagsDto.setChildren(new ArrayList<>());
        return tagsDto;
    }
}
