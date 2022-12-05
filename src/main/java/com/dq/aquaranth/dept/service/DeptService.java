package com.dq.aquaranth.dept.service;

import com.dq.aquaranth.company.mapper.CompanyMapper;
import com.dq.aquaranth.dept.dto.*;
import com.dq.aquaranth.dept.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class DeptService {

    private final DeptMapper deptMapper;

    private final CompanyMapper companyMapper;

    //상세 조회
    public DeptDTO getOne(Long deptNo) {
        DeptDTO deptDTO2 = deptMapper.select(deptNo);

        log.info(deptDTO2);

        return deptDTO2;
    } //No로 조회해서 DTO타입의 변수에 초기화


    //등록
    @Transactional
    public int insert(DeptRegisterDTO deptRegisterDTO) {
        int result = 0, insertDept = 0, insertOrga = 0, insertDeptMapping = 0;

        /** 1. 부서 추가 */
        //      upperDeptNo가 0인 경우 최상위 부서다
        log.info("deptRegisterDTO", deptRegisterDTO);

        log.info("before: ", deptRegisterDTO.getDeptNo());

        // 부서테이블 추가
        insertDept = deptMapper.insert(deptRegisterDTO);
        Long lastDeptNo = deptRegisterDTO.getDeptNo();

        log.info("after: ", deptRegisterDTO.getDeptNo());

        /** 2. 조직 추가 */
        // 회사면 회사 orgaNo 넣어야되고, 부서면 부서 orgaNo넣어야됨.
        DeptDTO deptDTO = deptMapper.select(lastDeptNo);

        DeptOrgaRegisterDTO deptOrgaRegisterDTO = DeptOrgaRegisterDTO
                .builder()
                .regUser(deptRegisterDTO.getUsername())
                .build();

        Long tempUpperOrgaNo = 0L;

        if (deptDTO.getUpperDeptNo() == null || deptDTO.getUpperDeptNo() == 0L) {
            // 상위 부서 번호가 0인 경우 > 최상위 부서
            // 해당 회사의 조직번호 찾기
            tempUpperOrgaNo = companyMapper.findByCompanyNo(deptDTO.getCompanyNo()).getOrgaNo();
        } else {
            // 상위 부서 번호가 있을 경우 > 하위 부서
            // 해당 상위부서의 조직번호 찾기
            tempUpperOrgaNo = deptMapper.selectDeptOrgaNo(deptRegisterDTO.getUpperDeptNo());
        }

        deptOrgaRegisterDTO.setUpperOrgaNo(tempUpperOrgaNo);

        insertOrga = deptMapper.insertOrga(deptOrgaRegisterDTO);

        Long lastOrgaNo = deptOrgaRegisterDTO.getOrgaNo();
        log.info("lastOrgaNo: ", lastOrgaNo);

        /** 3. 부서 매핑 추가 */
        DeptMappingRegisterDTO deptMappingRegisterDTO = DeptMappingRegisterDTO
                .builder()
                .deptNo(lastDeptNo)
                .orgaNo(lastOrgaNo)
                .regUser(deptRegisterDTO.getUsername())
                .build();
        insertDeptMapping = deptMapper.insertOrgaMapping(deptMappingRegisterDTO);

        result = insertDept + insertOrga + insertDeptMapping;

        log.info("insertDept : " + insertDept);
        log.info("insertOrga : " + insertOrga);
        log.info("insertDeptMapping : " + insertDeptMapping);
        log.info("result : " + result);

        return result;
    }


    // 수정
    public DeptDTO modify(DeptDTO deptDTO2) {
        int result = deptMapper.update(deptDTO2);
        Long no = deptDTO2.getDeptNo();

        log.info("no : " + no);

        DeptDTO modifyDTO = deptMapper.select(no);

        log.info("modify : " + modifyDTO);

        return modifyDTO;
    }

    // 삭제
    public Long remove(Long deptNo) {
        deptMapper.delete(deptNo);
        return deptNo;
    }

    // ord번호로 오름차순 정렬 리스트 조회
    public List<DeptDTO> listDept(Long companyNo) {
        return deptMapper.getGnoList(companyNo);
    }

    public List<DeptDTO> listDepth (Long upperDeptNo, int depth){

        return deptMapper.getFromParent(upperDeptNo, depth);

    }


    //트리 구조 조회
    public List<DeptTreeDTO> getTree(Long company){

        return deptMapper.getTree(company);
    }

    //맨처음 회사 나오고 버튼 누르면 바로 밑 하위 부서 조회
    public List<DeptDTO> getSubDepth(GetSubDeptDTO getSubDeptDTO) {
        return deptMapper.getSubDepth(getSubDeptDTO);
    }

    // 검색
    public List<DeptSearchDTO> searchList(String deptName, Long deptNo) {
        log.info("부서명, 부서이름으로 해당 부서 검색합니다.");
        return deptMapper.deptSearch(deptName, deptNo);
    }

    // 회사 번호로 부서 목록 조회
    public List<DepartmentDTO> findByCompanyNo(Long companyNo)
    {
        return deptMapper.findByCompanyNo(companyNo);

    }
}
