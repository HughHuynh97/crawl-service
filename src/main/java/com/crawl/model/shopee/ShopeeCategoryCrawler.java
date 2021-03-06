package com.crawl.model.shopee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopeeCategoryCrawler {
    @JsonProperty("data")
    private CategoryList data;
    @JsonProperty("error")
    private int error;
    @JsonProperty("error_msg")
    private String errorMsg;

    @Data
    public static class CategoryList {
        @JsonProperty("category_list")
        private ArrayList<CategoryItem> categoryLists;
    }

    @Data
    public static class CategoryItem {
        @JsonProperty("catid")
        private Long catId;
        @JsonProperty("parent_catid")
        private Long parentCatId;
        @JsonProperty("name")
        private String name;
        @JsonProperty("display_name")
        private String displayName;
        @JsonProperty("image")
        private String image;
        @JsonProperty("unselected_image")
        private String unselectedImage;
        @JsonProperty("selected_image")
        private String selectedImage;
        @JsonProperty("level")
        private int level;
        @JsonProperty("children")
        private List<CategoryItem> children;
        @JsonProperty("block_buyer_platform")
        private ArrayList<Integer> blockBuyerPlatform;
    }
}
