package com.dq.aquaranth.dept.service;

import com.dq.aquaranth.dept.dto.DepartmentDTO;
import com.dq.aquaranth.dept.dto.DeptDTO;
import com.dq.aquaranth.dept.dto.DeptTreeDTO;
import com.dq.aquaranth.dept.dto.GetSubDeptDTO;
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

        Long orga = null;

        if(deptDTO2.getUpperDeptNo() == null){
            deptMapper.insertOrga(Long.valueOf(deptDTO2.getCompanyNo()), deptDTO2.getRegUser());
        }else {
            deptMapper.insertOrga(Long.valueOf(deptDTO2.getUpperDeptNo()), deptDTO2.getRegUser());
        }

        orga = deptMapper.getLast();

        log.info("----------------------------------");
        log.info("orga: " + orga);
        log.info("----------------------------------");

        deptMapper.insert(deptDTO2);

        log.info("================================================");
        log.info(deptDTO2);

        Long newDeptNo = deptDTO2.getDeptNo();
        //1. 직접 부서 추가하고 추가한 데이터의 deptNo를 초기화

        log.info("newDeptNo " + newDeptNo);

        log.info("--------------------------------------------------------1");

        log.info("orga_dept_mapping");

        deptMapper.insertOrgaMapping(newDeptNo, orga, deptDTO2.getRegUser());

        int company = deptDTO2.getCompanyNo(); // 그룹번호를 가져와서  gno에 초기화
        Long parent = deptDTO2.getUpperDeptNo(); // 상위부서번호를 가져와서 parent에 초기화

        int ord = deptMapper.getNextOrd(company, parent);
        //2. gno와 parent 이용해서 나온 ord값 -> ord에 초기화

        log.info("ORD " + ord);

        log.info("--------------------------------------------------------2");

        deptMapper.arrangeOrd(company, ord);
        //3. gno가 같은 부서 중에 위에 나온 ord값보다 크거나 같은 ord 모두 +1씩 추가

        log.info("--------------------------------------------------------3");

        Long deptNo = newDeptNo; // 새로운 deptNo -> deptNo에 초기화

        deptMapper.fixOrd(deptNo,ord);
        //4. 직접 추가한 부서에 2번에서 추출한 ord값 업데이트

        log.info("--------------------------------------------------------4");

        Long lastDno = deptNo;  // 추가한 부서의 deptNo를 lastDno에 초기화
        Long parentDeptNo = parent; // 추가한 부서의 상위부서번호를 parentDeptNo에 초기화

        deptMapper.updateLastDno(parentDeptNo, lastDno);
        //5. deptNo가 상위부서번호와 일치하는 곳의 lastDno에 추가한 deptNo번호 업데이트

        log.info("--------------------------------------------------------5");


        return Map.of(
                "newDeptNo", newDeptNo,
                "newGno", company,
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

        DeptDTO modifyDTO = deptMapper.select(no);

        log.info("modify : " + modifyDTO);

        return modifyDTO;
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


    //트리 구조 조회
    public List<DeptTreeDTO> getTree(Long company){

        return deptMapper.getTree(company);
    }

    //맨처음 회사 나오고 버튼 누르면 바로 밑 하위 부서 조회(준성이형)
    public List<DeptDTO> getSubDepth(GetSubDeptDTO getSubDeptDTO) {
        return deptMapper.getSubDepth(getSubDeptDTO);
    }


    // 회사 번호로 부서 목록 조회
    public List<DepartmentDTO> findByCompanyNo(Long companyNo)
    {
        return deptMapper.findByCompanyNo(companyNo);

    }
}
