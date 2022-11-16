package com.dq.aquaranth.mygroup.controller;

import com.dq.aquaranth.mygroup.dto.MygroupModifyDTO;
import com.dq.aquaranth.mygroup.dto.MygroupRemoveDTO;
import com.dq.aquaranth.mygroup.service.MygroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/mygroup")
public class MygroupController {

    private final MygroupService mygroupService;

    /**
     * 로그인한 사원의 마이그룹 생성
     */
    @PostMapping("/register")
    public Long registerMygroup(String username) {
        return mygroupService.insertMygroup(username);
    }

    /**
     * 로그인한 사원의 마이그룹 수정
     */
    @PutMapping("/modify/{mygroupNo}")
    public Long modifyMygroup(@RequestBody MygroupModifyDTO mygroupModifyDTO) {
        return mygroupService.updateMygroup(mygroupModifyDTO);
    }

    /**
     * 로그인한 사원의 마이그룹 삭제
     */
    @DeleteMapping("/remove")
    public Long removeMygroup(@RequestBody MygroupRemoveDTO mygroupRemoveDTO) {
        return mygroupService.deleteMygroup(mygroupRemoveDTO);
    }
}
