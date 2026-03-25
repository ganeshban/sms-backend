package com.ganeshban.smsserver.model.search;

import lombok.Data;

import java.util.List;

@Data
public class SearchRequest {
    List<SearchCriteria> filters;
    List<SearchOrder> sort;
    Integer pageNO;
    Integer pageSize;
}
