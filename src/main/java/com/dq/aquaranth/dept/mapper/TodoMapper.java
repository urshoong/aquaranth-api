package com.dq.aquaranth.dept.mapper;

import com.dq.aquaranth.dept.dto.TodoCriteria;
import com.dq.aquaranth.dept.dto.TodoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TodoMapper {

    //1개 조회
    public TodoDTO getOne(Long tno);

    //등록
    public int insert(TodoDTO todoDTO);

    //삭제
    public int delete(Long tno);

    //수정
    public int update(TodoDTO todoDTO);

    //전체 조회
    public List<TodoDTO> select(@Param("skip") int skip, @Param("size") int size);
    //@Param을 사용 하는 이유 : MyBatis에서 2개 이상의 파라미터를 인식 못해서 사용

}
