package com.ganeshban.smsserver.model.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchCriteria {
    private String key;
    private SearchOperation op;
    private String value;
}
