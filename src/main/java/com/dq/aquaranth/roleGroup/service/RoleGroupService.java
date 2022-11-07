package com.dq.aquaranth.roleGroup.service;

import com.dq.aquaranth.roleGroup.dto.RoleGroupDTO;
import com.dq.aquaranth.roleGroup.dto.RoleGroupUpdateDTO;

import java.util.List;

public interface RoleGroupService {
    List<RoleGroupDTO> findAll();

    RoleGroupDTO findById(Long roleGroupNo);

    void update(RoleGroupUpdateDTO updateDTO);

    RoleGroupDTO insert(RoleGroupDTO insertDTO);

    void delete(Long roleGroupNo);
}
