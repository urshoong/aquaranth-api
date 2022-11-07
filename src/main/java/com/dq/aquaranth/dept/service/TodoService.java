package com.dq.aquaranth.dept.service;


import com.dq.aquaranth.dept.dto.DeptDTO2;
import com.dq.aquaranth.dept.dto.TodoCriteria;
import com.dq.aquaranth.dept.dto.TodoDTO;
import com.dq.aquaranth.dept.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoMapper todoMapper;

    //1개 조회
    public TodoDTO getOne(Long tno) {

        TodoDTO todoDTO = todoMapper.getOne(tno);
        return todoDTO;
    }


    //추가 사용중
    public Map<Object, Object> register(TodoDTO todoDTO) {

        todoMapper.insert(todoDTO);

        Long newTno = todoDTO.getTno();

        log.info(newTno);

        return Map.of("add result", newTno);

    }


    //삭제
    public Long remove(Long tno) {

        todoMapper.delete(tno);

        return tno;

    }


    //수정
    public TodoDTO modify(TodoDTO todoDTO) {

        todoMapper.update(todoDTO);

        Long newTno = todoDTO.getTno();

        log.info(newTno);

        TodoDTO result = todoMapper.getOne(newTno);

        return result;

    }


    //todo 리스트 뽑기
    public List<TodoDTO> list(int skip, int size) {

        List<TodoDTO> result = todoMapper.select(skip, size);

        return result;

    }

}
