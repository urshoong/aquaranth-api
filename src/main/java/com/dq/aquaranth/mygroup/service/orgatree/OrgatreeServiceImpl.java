package com.dq.aquaranth.mygroup.service.orgatree;

import com.dq.aquaranth.mygroup.dto.orgatree.OrgaTreeDTO;
import com.dq.aquaranth.mygroup.dto.orgatree.OrgaTreeEmpListDTO;
import com.dq.aquaranth.mygroup.dto.orgatree.OrgatreeEmpInformationDTO;
import com.dq.aquaranth.mygroup.mapper.OrgatreeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrgatreeServiceImpl implements OrgatreeService {
    private final OrgatreeMapper orgatreeMapper;

    /**
     * 조직도
     */
    @Override
    public List<OrgaTreeDTO> findAllOrga (Long upperDeptNo, Long depth, Long companyNo) {
        log.info("조직도 트리 출력");
        return orgatreeMapper.findAllOrga(upperDeptNo, depth, companyNo);
    }

    @Override
    public List<OrgaTreeEmpListDTO> findAllEmpInformation(Long orgaNo) {
        log.info("해당 부서에 속한 사원 정보 모두 조회");
        return orgatreeMapper.findAllEmpInformation(orgaNo);
    }

    @Override
    public OrgatreeEmpInformationDTO findByEmpNo(Long orgaNo) {
        log.info("해당 부서에 소속된 사원 중 한 사원의 정보 조회");
        return orgatreeMapper.findByEmpNo(orgaNo);
    }

}
