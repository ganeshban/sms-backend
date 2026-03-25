package com.ganeshban.smsserver.model.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchOrder {
    private String key;
    private SortOrder order;
}

