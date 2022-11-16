package com.dq.aquaranth.dept.service;

import com.dq.aquaranth.dept.dto.DeptDTO;
import com.dq.aquaranth.dept.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class DeptService {

    private final DeptMapper deptMapper;

    //조회
    public DeptDTO getOne(Long deptNo) {
        DeptDTO deptDTO2 = deptMapper.select(deptNo);

        log.info(deptDTO2);

        return deptDTO2;
    } //No로 조회해서 DTO타입의 변수에 초기화


    //등록
    @Transactional
    public Map<Object, Object> register(DeptDTO deptDTO2) {


        deptMapper.insert(deptDTO2);

        Long newDeptNo = deptDTO2.getDeptNo();

        int gno = deptDTO2.getGno();

        Long parent = deptDTO2.getUpperDeptNo();

        int ord = deptMapper.getNextOrd(gno, parent);


        Long deptNo = newDeptNo;
        Long lastDno = deptNo;        // deptDTO2.getDeptNo();
        Long parentDeptNo = parent;   // deptDTO2.getUpperDeptNo();

        deptMapper.arrangeOrd(gno, ord);

        deptMapper.fixOrd(deptNo, ord);

        deptMapper.updateLastDno(parentDeptNo, lastDno);

        return Map.of(
                "newDeptNo", newDeptNo,
                "newGno", gno,
                "newParent", parent,
                "ord", ord,
                "lastDno", lastDno,
                "parentDeptNo", parentDeptNo
        );
    }

//    public Map<Object, Object> register(DeptDTO2 deptDTO2) {
//        deptMapper2.insert(deptDTO2);
//
//        Long newNo = deptDTO2.getDeptNo();
//
//        log.info(newNo);
//
//        return Map.of("result", newNo);
//    }

    public DeptDTO modify(DeptDTO deptDTO2) {
        int result = deptMapper.update(deptDTO2);
        Long no = deptDTO2.getDeptNo();

        log.info("no : " + no);

        DeptDTO modiftDTO = deptMapper.select(no);

        log.info("modify : " + modiftDTO);

        return modiftDTO;
    }

    public Long remove(Long deptNo) {
        deptMapper.delete(deptNo);
        return deptNo;
    }

    public List<DeptDTO> list(int skip, int size) {
        List<DeptDTO> result = deptMapper.getList(skip,size);
        return result;
    }

    //ord번호로 오름차순 정렬 리스트 조회
    public List<DeptDTO> listDept(int gno) {
        return deptMapper.getGnoList(gno);
    }

//    public List<DeptDTO2> registerDept(DeptDTO2 deptDTO2) {
//
//    }


    public List<DeptDTO> listDepth (Long upperDeptNo, int depth){

        return deptMapper.getFromParent(upperDeptNo, depth);
    }




}
