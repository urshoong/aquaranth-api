package com.dq.aquaranth.userRole.mapper;

import com.dq.aquaranth.userRole.dto.TreeDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TreeMapper {
    List<TreeDTO> getFromParent(@Param("upperDeptNo") Long upperDeptNo, @Param("depth") int depth, @Param("gno") int gno);
}
