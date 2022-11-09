package com.dq.aquaranth.dept;


import com.dq.aquaranth.dept.dto.TodoDTO;
import com.dq.aquaranth.dept.mapper.TodoMapper;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Log4j2
public class TodoMapperTests {

    @Autowired(required = false)
    TodoMapper todoMapper;

    @Test
    public void testSelect() {
        TodoDTO result = todoMapper.getOne(5L);
        log.info(result);
    }

    @Test
    public void testInsert() {
        TodoDTO todoDTO = TodoDTO
                .builder()
                .title("농구하기")
                .author("황명수")
                .memo("취미생활")
                .build();

        int result = todoMapper.insert(todoDTO);
        log.info("insert : " + result);
    }

    @Test
    public void testDelete() {

        int result = todoMapper.delete(22L);
        log.info("삭제 : " + result);

    }

    @Test
    public void testUpdate() {

        TodoDTO todoDTO = TodoDTO
                .builder()
                .tno(21L)
                .title("복싱")
                .author("페더러")
                .memo("스포츠")
                .build();

        int result = todoMapper.update(todoDTO);
        log.info("수정 : " + result);
    }

    @Test
    public void testSelectAll() {
        int skip = 0;
        int size = 10;

        List<TodoDTO> list = todoMapper.select(skip, size);

        log.info(list);

    }

}
