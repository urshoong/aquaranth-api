package com.dq.aquaranth.dept;


import com.dq.aquaranth.dept.dto.DeptDTO;
import com.dq.aquaranth.dept.mapper.DeptMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
    public void testInsert() {
        DeptDTO deptDTO2 = DeptDTO
                .builder()
                .upperDeptNo(2L)
                .deptName("개발 5팀")
                .deptDesc("개발 5팀")
                .mainFlag(false)
                .delFlag(true)
                .build();

        int result = deptMapper.insert(deptDTO2);
        log.info("result : " + result);
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

        DeptDTO deptDTO2 = DeptDTO.builder()
                .deptName("개발1-1팀")
                .deptDesc("개발1-1팀")
                .regUser("admin")
                .upperDeptNo(6L)
                .companyNo(1)
                .depth(3)
                .build();

        Long orga = null;

        if(deptDTO2.getUpperDeptNo() == null){
            deptMapper.insertOrga(Long.valueOf(deptDTO2.getCompanyNo()), deptDTO2.getRegUser());
        }else {
            deptMapper.insertOrga(Long.valueOf(deptDTO2.getUpperDeptNo()), deptDTO2.getRegUser());
        }

        orga = deptMapper.getLast();

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

        deptMapper.insertOrgaMapping(newDeptNo, orga, deptDTO2.getRegUser());


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
    public void testInsertAll() {

        DeptDTO deptDTO2 = DeptDTO.builder()
                .deptName("인사부")
                .deptDesc("인사부")
                .upperDeptNo(null)
                .companyNo(1)
                .depth(1)
                .regUser("admin")
                .mainFlag(true)
                .delFlag(true)
                .build();

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

    }



    public void findByCompanyNoTest(){
        log.info(deptMapper.findByCompanyNo(1L));
    }

}
