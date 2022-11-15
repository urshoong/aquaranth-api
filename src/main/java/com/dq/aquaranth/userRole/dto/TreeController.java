package com.dq.aquaranth.userRole.dto;

import com.dq.aquaranth.userRole.service.TreeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/dept2")
public class TreeController {

    private final TreeService treeService;

    @GetMapping("/list/{uppperDeptNo}/{depth}/{gno}")
    public List<TreeDTO> listDept2 (@PathVariable("uppperDeptNo") Long uppperDeptNo, @PathVariable("depth") int depth, @PathVariable("gno") int gno) {

        return treeService.listDepth(uppperDeptNo, depth, gno);
    }
}
