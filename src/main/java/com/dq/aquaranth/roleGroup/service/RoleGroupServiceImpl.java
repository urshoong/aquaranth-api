package com.dq.aquaranth.roleGroup.service;

import com.dq.aquaranth.roleGroup.dto.RoleGroupDTO;
import com.dq.aquaranth.roleGroup.dto.RoleGroupModifyReqDTO;
import com.dq.aquaranth.roleGroup.dto.RoleGroupUpdateDTO;
import com.dq.aquaranth.roleGroup.mapper.RoleGroupMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class RoleGroupServiceImpl implements RoleGroupService {
    private final RoleGroupMapper roleGroupMapper;
    @Override
    public List<RoleGroupDTO> findAll() {
        log.info("권한그룹을 조회합니다");
        return roleGroupMapper.findAll();
    }

    @Override
    public RoleGroupDTO findById(Long roleGroupNo) {
        log.info("{} 번째 권한그룹을 조회합니다", roleGroupNo);
        return roleGroupMapper.findById(roleGroupNo);
    }

    @Override
    public void update(RoleGroupUpdateDTO updateDTO) {
        log.info("{} 번째 권한그룹을 수정합니다.", updateDTO);
        roleGroupMapper.update(updateDTO);
    }
}
