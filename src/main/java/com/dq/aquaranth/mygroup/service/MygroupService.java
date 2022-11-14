package com.dq.aquaranth.mygroup.service;

import com.dq.aquaranth.mygroup.dto.MygroupModifyDTO;

public interface MygroupService {

    Long insert(String username);
    Long update(MygroupModifyDTO mygroupModifyDTO);
    Long delete(String username);
}
