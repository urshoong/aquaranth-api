package com.dq.aquaranth.mygroup.service.mygroup;

import com.dq.aquaranth.mygroup.dto.mygroup.MygroupListDTO;
import com.dq.aquaranth.mygroup.dto.mygroup.MygroupUpdateDTO;

import java.util.List;

public interface MygroupService {

    /**
     * 마이그룹
     */
    List<MygroupListDTO> findAllMygroup(String username);
    Long insert(String username);
    Long update(MygroupUpdateDTO mygroupUpdateDTO);
    Long delete(Long mygroupNo);
}
