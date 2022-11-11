package com.dq.aquaranth.dept.controller;

import com.dq.aquaranth.dept.dto.TodoCriteria;
import com.dq.aquaranth.dept.dto.TodoDTO;
import com.dq.aquaranth.dept.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;


    //1개 조회
    @GetMapping("/detail/{tno}")
    public TodoDTO getOne(@PathVariable("tno") Long tno) {

        TodoDTO todoDTO = todoService.getOne(tno);
        return todoDTO;
    }

    //추가
    @PostMapping("/")
    public Map<Object, Object> register(@RequestBody TodoDTO todoDTO) {

        Map<Object, Object> result = todoService.register(todoDTO);

        return result;

    }

    //삭제
    @DeleteMapping("/{tno}")
    public Long remove(@PathVariable("tno") Long tno) {

        return todoService.remove(tno);
    }

    //수정
    @PutMapping("/modify/{tno}")
    public TodoDTO modify(@PathVariable("tno") Long tno, @RequestBody TodoDTO todoDTO) {

        return todoService.modify(todoDTO);


    }

    //리스트 뽑기
    @GetMapping("/")
    public List<TodoDTO> list(TodoCriteria todoCriteria) {
        return todoService.list(todoCriteria.getSkip(), todoCriteria.getSize());
    }

}
