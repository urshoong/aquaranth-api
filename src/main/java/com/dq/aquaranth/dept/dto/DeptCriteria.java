package com.dq.aquaranth.dept.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeptCriteria {


    private int page;

    private int size;

    public int getSkip() {
        return ( page-1 ) * size;
    }

    public int getSize() {
        return size;
    }


}
