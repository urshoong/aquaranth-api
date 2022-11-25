package com.dq.aquaranth.rolegroup.service;

import com.dq.aquaranth.rolegroup.domain.RoleGroup;
import com.dq.aquaranth.rolegroup.dto.RoleGroupResponseDTO;
import com.dq.aquaranth.rolegroup.dto.RoleGroupUpdateDTO;

import java.util.List;

public interface RoleGroupService {
    List<RoleGroupResponseDTO> findAll();

    RoleGroup findById(Long roleGroupNo);

    void update(RoleGroupUpdateDTO updateDTO);

    RoleGroup insert(RoleGroup insertDTO);

    void deleteById(Long roleGroupNo);

    void hideById(Long roleGroupNo);
}
