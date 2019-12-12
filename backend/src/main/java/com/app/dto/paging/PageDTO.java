package com.app.dto.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class PageDTO {
    private int totalPageCnt;
    private int pageNow;
}
