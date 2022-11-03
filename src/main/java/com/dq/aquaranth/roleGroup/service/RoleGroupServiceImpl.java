package com.dq.aquaranth.roleGroup.service;

import com.dq.aquaranth.roleGroup.dto.RoleGroupDTO;
import com.dq.aquaranth.roleGroup.dto.RoleGroupUpdateDTO;
import com.dq.aquaranth.roleGroup.mapper.RoleGroupAnnotationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class RoleGroupServiceImpl implements RoleGroupService {
    private final RoleGroupAnnotationMapper roleGroupMapper;
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

    @Override
    public RoleGroupDTO insert(RoleGroupDTO insertDTO) {
        Long result = roleGroupMapper.insert(insertDTO);
        log.info("권한그룹을 등록합니다. 등록된 권한그룹번호 => {}", insertDTO.getRoleGroupNo());

        if (result < 1) {
            log.error("insert가 정상적으로 이루어지지 않았습니다.");
        }

        return insertDTO;
    }

    @Override
    public void delete(Long roleGroupNo) {
        log.info("{} 번째 권한그룹을 삭제합니다.", roleGroupNo);

        RoleGroupDTO findDTO = roleGroupMapper.findById(roleGroupNo);

        if (Objects.isNull(findDTO)) {
            log.error("삭제하려는 권한그룹은 존재하지 않습니다");
            throw new RuntimeException("삭제하려는 권한그룹은 존재하지 않습니다");
        }

        roleGroupMapper.deleteById(roleGroupNo);
        log.info("권한그룹을 삭제가 완료되었습니다.");
    }
}
