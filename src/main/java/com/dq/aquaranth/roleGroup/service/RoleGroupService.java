package com.dq.aquaranth.roleGroup.service;

import com.dq.aquaranth.roleGroup.dto.RoleGroupDTO;
import com.dq.aquaranth.roleGroup.dto.RoleGroupModifyReqDTO;
import com.dq.aquaranth.roleGroup.dto.RoleGroupUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleGroupService {
    List<RoleGroupDTO> findAll();

    RoleGroupDTO findById(Long roleGroupNo);

    void update(RoleGroupUpdateDTO updateDTO);
}
