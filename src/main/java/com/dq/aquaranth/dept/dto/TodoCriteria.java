package com.dq.aquaranth.dept.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoCriteria {

    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int size = 10;

    public int getSkip() {
        return ( page - 1) * size;
    }

    public int getSize() {
        return size;
    }


}
