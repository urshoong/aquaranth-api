package com.dq.aquaranth.emp.service;

import com.dq.aquaranth.emp.dto.*;
import com.dq.aquaranth.emp.mapper.EmpMapper;
import com.dq.aquaranth.login.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
@Log4j2

public class EmpServiceImpl implements EmpService {

    private final EmpMapper mapper;

    private final UserService userService;

    @Override
    public List<EmpDTO> empList() {
        return mapper.empFindAll();
    }

    @Override
    public EmpDTO empRead(Long empNo) {
        return mapper.empFindById(empNo);
    }

    @Override
    public Long empModify(EmpUpdateDTO empUpdateDTO) {
        return mapper.empUpdate(empUpdateDTO);
    }

    @Override
    public Long empRemove(Long empNo) {
        return mapper.empDeleteById(empNo);
    }

    @Override
    @Transactional
    public EmpDTO empRegister(EmpInsertInformationDTO reqDTO, HttpServletResponse response) throws IOException, IllegalAccessException {

        // 1. 조직 테이블 insert
        EmpOrgaDTO insertEmpOrga = EmpOrgaDTO
                .builder()
                .deptNo(reqDTO.getDeptNo())
                .orgaType(reqDTO.getOrgaType())
                .build();

        log.info(mapper.empOrgaInsert(insertEmpOrga));

        log.info(insertEmpOrga);

        //조직 테이블의 last_insert_id 저장
        Long orgaId = insertEmpOrga.getOrgaNo();

        log.info(orgaId);

        // 2. 사원 테이블 insert
        EmpDTO insertEmp = EmpDTO
                .builder()
                .empName(reqDTO.getEmpName())
                .username(reqDTO.getUsername())
                .password(reqDTO.getPassword())
                .gender(reqDTO.getGender())
                .empPhone(reqDTO.getEmpPhone())
                .empAddress(reqDTO.getEmpAddress())
                .empProfile(reqDTO.getEmpProfile())
                .email(reqDTO.getEmail())
                .lastLoginTime(now())
                .lastLoginIp("192.168.500.55")
                .firstHiredate(LocalDate.now())
                .lastRetiredate(null)
                .build();


//        log.info(mapper.empInsert(insertEmp));

        EmpDTO insertEmpDTO = userService.create(insertEmp, response);

        //사원 테이블의 last_insert_id 저장
        Long empId = insertEmp.getEmpNo();

        log.info(empId);

        // 3. 사원매핑 테이블 insert
        EmpMappingDTO insertEmpMapping = EmpMappingDTO
                .builder()
                .empNo(empId)
                .orgaNo(orgaId)
                .empRank("사원")
                .hiredate(LocalDate.now())
                .build();

        mapper.empMappingInsert(insertEmpMapping);


        return insertEmpDTO;
    }

    @Override
    public List<EmpSelectOrga> empOrgaList(Long empNo) {
        return mapper.empFindOrga(empNo);
    }


}
