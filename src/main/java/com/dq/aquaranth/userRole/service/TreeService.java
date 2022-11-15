package com.dq.aquaranth.userRole.service;

import com.dq.aquaranth.userRole.dto.TreeDTO;
import com.dq.aquaranth.userRole.mapper.TreeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class TreeService {

    private final TreeMapper treeMapper;

    public List<TreeDTO> listDepth (Long upperDeptNo, int depth, int gno){

        return treeMapper.getFromParent(upperDeptNo, depth, gno);
    }
}
