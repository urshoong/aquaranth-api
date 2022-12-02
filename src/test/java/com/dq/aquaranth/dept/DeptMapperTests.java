
package com.dq.aquaranth.dept;


import com.dq.aquaranth.dept.dto.DeptDTO;
import com.dq.aquaranth.dept.dto.DeptRegisterDTO;
import com.dq.aquaranth.dept.dto.DeptSearchDTO;
import com.dq.aquaranth.dept.mapper.DeptMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
@Log4j2
public class DeptMapperTests {

    @Autowired(required = false)
    private DeptMapper deptMapper;

    @Test
    public void testSelect() {
        DeptDTO selectOne = deptMapper.select(5L);
        log.info(selectOne);
    }

    @Test
    public void testDelete() {
        Long no = 30L;
        int result = deptMapper.delete(no);
        log.info("delete : " + result);
    }

    @Test
    public void testUpdate() {
        DeptDTO deptDTO2 = DeptDTO
                .builder()
                .deptNo(23L)
                .upperDeptNo(2L)
                .deptName("철강 1팀 업데이트")
                .delFlag(false)
                .mainFlag(false)
                 .deptDesc("철강 1팀 업데이트")
                .build();

        int result = deptMapper.update(deptDTO2);
        log.info("update : " + result);
    }

    @Test
    public void testList() {
        int skip = 0;
        int size = 10;

        List<DeptDTO> list = deptMapper.getList(skip, size);

        list.forEach(deptDTO2 -> log.info(deptDTO2));
    }

    @Test
    //@Transactional(rollbackFor = RuntimeException.class)
    public void testInsert2() {

        DeptRegisterDTO deptDTO2 = DeptRegisterDTO.builder()
                .deptName("개발1-1팀")
                .deptDesc("개발1-1팀")
                .upperDeptNo(6L)
                .build();

        Long orga = null;

        log.info("----------------------------------");
        log.info("----------------------------------");
        log.info("orga: " + orga);
        log.info("----------------------------------");
        log.info("----------------------------------");

        deptMapper.insert(deptDTO2);

        log.info("================================================");
        log.info(deptDTO2);

        Long newDeptNo = deptDTO2.getDeptNo();

        log.info("newDeptNo " + newDeptNo);
    }

    @Test
    public void testSub1() {

        int ord = deptMapper.getNextOrd(1, 6L);

        log.info("ORD " + ord); //2

    }

    @Test
    public void testSub2() {

        int ord = 3;
        int companyNo = 1;

        deptMapper.arrangeOrd(companyNo, ord);


    }

    @Test
    public void testSub3() {

        int ord = 3;
        Long deptNo = 22L;

        deptMapper.fixOrd(deptNo,ord);

    }

    @Test
    public void testSub4() {

        //#{lastDno} where deptNo = #{parentDeptNo}

        Long lastDno = 22L;
        Long parentDeptNo = 6L;

        deptMapper.updateLastDno(parentDeptNo, lastDno);

    }

    @Test
    @DisplayName("부서 번호, 부서 이름로 검색 test")
    void search() {
        String deptSearch = "테스";

        List<DeptSearchDTO> deptSearchDTO = deptMapper.deptSearch(deptSearch);

        deptSearchDTO.forEach(log::info);
    }

    public void findByCompanyNoTest(){
        log.info(deptMapper.findByCompanyNo(1L));
    }

}
