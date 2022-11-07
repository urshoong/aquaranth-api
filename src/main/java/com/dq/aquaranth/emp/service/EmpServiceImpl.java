package com.dq.aquaranth.emp.service;

import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.emp.dto.EmpUpdateDTO;
import com.dq.aquaranth.emp.mapper.EmpMapper;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@ToString
public class EmpServiceImpl implements EmpService {

    private final EmpMapper mapper;

    @Override
    public List<EmpDTO> empList() {
        return mapper.empFindAll();
    }

    @Override
    public EmpDTO empRead(Long empNo) {
        return mapper.empFindById(empNo);
    }

    @Override
    public Long empRegister(EmpDTO empInsertDTO){
        return mapper.empInsert(empInsertDTO);
    }

    @Override
    public Long empModify(EmpUpdateDTO empUpdateDTO){
        return mapper.empUpdate(empUpdateDTO);
    }

    @Override
    public Long empRemove(Long empNo) {
        return mapper.empDeleteById(empNo);
    }
}
