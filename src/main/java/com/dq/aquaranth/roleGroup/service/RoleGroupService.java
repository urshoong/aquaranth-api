package com.dq.aquaranth.roleGroup.service;

import com.dq.aquaranth.roleGroup.domain.RoleGroup;
import com.dq.aquaranth.roleGroup.dto.RoleGroupUpdateDTO;

import java.util.List;

public interface RoleGroupService {
    List<RoleGroup> findAll();

    RoleGroup findById(Long roleGroupNo);

    void update(RoleGroupUpdateDTO updateDTO);

    RoleGroup insert(RoleGroup insertDTO);

    void delete(Long roleGroupNo);
}
