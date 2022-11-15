package com.dq.aquaranth.emp;

import com.dq.aquaranth.emp.dto.*;
import com.dq.aquaranth.emp.service.EmpService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Locale;

import static java.time.LocalDateTime.now;

@SpringBootTest
@Log4j2
public class EmpServiceTests {

    @Autowired(required = false)
    EmpService service;

    @Test
    void empListTest(){
        log.info(service.findAll());
    }

    @Test
    void empReadTest(){
        log.info(service.findById(1L));
    }

    @Test
    void empOrgaListTest() {
        log.info(service.findAllOrga(1L));
    }

//    @Test
//    void empInsertTest(){
//        EmpDTO empDTO = EmpDTO.builder()
//                .empName("Annie")
//                .username("user09")
//                .password("userpwd09")
//                .gender("여성")
//                .empPhone("01088776655")
//                .empAddress("에버랜드")
//                .empProfile("profile")
//                .email("user09@naver.com")
//                .lastLoginTime(now())
//                .lastLoginIp("192.168.500.9")
//                .lastRetiredate(null)
//                .build();
//        log.info(service.empRegister(empDTO));
//
//    }

    @Test
    void empModifyTest(){
        EmpUpdateDTO empUpdateDTO = EmpUpdateDTO.builder()
                .empName("양당근")
                .password("modTest")
                .gender("여성")
                .empPhone("01011112222")
                .empAddress("수정시 수정구")
                .empProfile("profileUpdate")
                .email("userUpdate02@naver.com")
                .lastRetiredDate(null)
                .empNo(16L)
                .build();

        log.info(service.update(empUpdateDTO));
    }

    @Test
    void empDeleteTest() {
        log.info(service.delete(16L));
    }

//    @Test
//    void registerTest() throws IOException, IllegalAccessException {
//        EmpInsertInformationDTO empInsertInformationDTO
//                = EmpInsertInformationDTO
//                .builder()
//                .deptNo(6L)
//                .empName("이부서")
//                .username("user17")
//                .password("test11")
//                .gender("남성")
//                .empProfile("profile")
//                .empPhone("01099231293")
//                .empAddress("서울시")
//                .empProfile("file")
//                .email("email@naver.com")
//                .empRank("인턴")
//                .build();
//
//        EmpDTO insertDTO = service.empRegister(empInsertInformationDTO, new HttpServletResponse() {
//            @Override
//            public void addCookie(Cookie cookie) {
//
//            }
//
//            @Override
//            public boolean containsHeader(String name) {
//                return false;
//            }
//
//            @Override
//            public String encodeURL(String url) {
//                return null;
//            }
//
//            @Override
//            public String encodeRedirectURL(String url) {
//                return null;
//            }
//
//            @Override
//            public String encodeUrl(String url) {
//                return null;
//            }
//
//            @Override
//            public String encodeRedirectUrl(String url) {
//                return null;
//            }
//
//            @Override
//            public void sendError(int sc, String msg) throws IOException {
//
//            }
//
//            @Override
//            public void sendError(int sc) throws IOException {
//
//            }
//
//            @Override
//            public void sendRedirect(String location) throws IOException {
//
//            }
//
//            @Override
//            public void setDateHeader(String name, long date) {
//
//            }
//
//            @Override
//            public void addDateHeader(String name, long date) {
//
//            }
//
//            @Override
//            public void setHeader(String name, String value) {
//
//            }
//
//            @Override
//            public void addHeader(String name, String value) {
//
//            }
//
//            @Override
//            public void setIntHeader(String name, int value) {
//
//            }
//
//            @Override
//            public void addIntHeader(String name, int value) {
//
//            }
//
//            @Override
//            public void setStatus(int sc) {
//
//            }
//
//            @Override
//            public void setStatus(int sc, String sm) {
//
//            }
//
//            @Override
//            public int getStatus() {
//                return 0;
//            }
//
//            @Override
//            public String getHeader(String name) {
//                return null;
//            }
//
//            @Override
//            public Collection<String> getHeaders(String name) {
//                return null;
//            }
//
//            @Override
//            public Collection<String> getHeaderNames() {
//                return null;
//            }
//
//            @Override
//            public String getCharacterEncoding() {
//                return null;
//            }
//
//            @Override
//            public String getContentType() {
//                return null;
//            }
//
//            @Override
//            public ServletOutputStream getOutputStream() throws IOException {
//                return null;
//            }
//
//            @Override
//            public PrintWriter getWriter() throws IOException {
//                return null;
//            }
//
//            @Override
//            public void setCharacterEncoding(String charset) {
//
//            }
//
//            @Override
//            public void setContentLength(int len) {
//
//            }
//
//            @Override
//            public void setContentLengthLong(long length) {
//
//            }
//
//            @Override
//            public void setContentType(String type) {
//
//            }
//
//            @Override
//            public void setBufferSize(int size) {
//
//            }
//
//            @Override
//            public int getBufferSize() {
//                return 0;
//            }
//
//            @Override
//            public void flushBuffer() throws IOException {
//
//            }
//
//            @Override
//            public void resetBuffer() {
//
//            }
//
//            @Override
//            public boolean isCommitted() {
//                return false;
//            }
//
//            @Override
//            public void reset() {
//
//            }
//
//            @Override
//            public void setLocale(Locale loc) {
//
//            }
//
//            @Override
//            public Locale getLocale() {
//                return null;
//            }
//        });
//
//        log.info("insert된 사원정보 : " + insertDTO);
//    }

    @Test
    void empInsertTest() throws IllegalAccessException{
        String registrant = "종현";

        // 조직
        EmpOrgaDTO orgaDTO = EmpOrgaDTO.builder()
                .deptNo(10L)
                .regUser(registrant) //등록자
                .build();

        Long orgaId = orgaDTO.getOrgaNo();

        // 사원
        EmpDTO empDTO = EmpDTO.builder()
                .empName("진용진") //이름
                .username("emp01") //id
                .password("emp") //비번
                .gender("남성")   //성별
                .empPhone("01011111111") //휴대폰 번호
                .empAddress("부산시") //주소
                .empProfile("profile") //프로필 사진
                .email("email@naver.com") //이메일
                .firstHiredDate(LocalDate.now())//첫입사일
                .regUser(registrant) //등록자
                .build();

        Long empId = empDTO.getEmpNo();

        // 매핑테이블
        EmpMappingDTO empMappingDTO = EmpMappingDTO.builder()
                .orgaNo(orgaId)
                .empNo(empId)
                .empRole("ROLE_USER") //사용자권한
                .empRank("사원") //직급
                .regUser(registrant)
                .build();

            service.insert(orgaDTO, empDTO, empMappingDTO);
    }

    @Test
    void registerEmpOrga() {
        String registrant = "종현";
        Long empNo = 14L;
        // 조직
        EmpOrgaDTO orgaDTO = EmpOrgaDTO.builder()
                .deptNo(6L)
                .regUser(registrant) //등록자
                .build();

        Long orgaId = orgaDTO.getOrgaNo();

        EmpMappingDTO empMappingDTO = EmpMappingDTO.builder()
                .orgaNo(orgaId)
                .empNo(empNo)
                .empRole("ROLE_USER") //사용자권한
                .empRank("사원") //직급
                .regUser(registrant)
                .build();

        service.empOrgaInsert(orgaDTO, empMappingDTO, empNo);
    }

}
