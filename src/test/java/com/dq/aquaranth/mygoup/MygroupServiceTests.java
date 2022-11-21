//package com.dq.aquaranth.mygoup;
//
//import com.dq.aquaranth.mygroup.dto.FavoriteEmpListDTO;
//import com.dq.aquaranth.mygroup.dto.FavoriteFindEmpInfoDTO;
//import com.dq.aquaranth.mygroup.dto.MygroupModifyDTO;
//import com.dq.aquaranth.mygroup.dto.MygroupRemoveDTO;
//import com.dq.aquaranth.mygroup.service.MygroupService;
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//@Log4j2
//public class MygroupServiceTests {
//
//    @Autowired(required = false)
//    MygroupService mygroupService;
//
//    @Test
//    @DisplayName("로그인한 사원의 마이그룹 생성")
//    void registerMygroupTest() {
//        String username = "admin";
//        log.info(mygroupService.insertMygroup(username));
//    }
//
//    @Test
//    @DisplayName("로그인한 사원의 마이그룹 수정")
//    void modifyMygroupTest() {
//        String mygroupName = "수정그룹";
//        String username = "admin";
//        MygroupModifyDTO mygroupModifyDTO = MygroupModifyDTO
//                .builder()
//                .mygroupName(mygroupName)
//                .username(username)
//                .modUser(username)
//                .build();
//        log.info(mygroupService.updateMygroup(mygroupModifyDTO));
//    }
//
//    @Test
//    @DisplayName("로그인한 사원의 마이그룹 삭제")
//    void removeMygroupTest() {
//        String username = "user";
//        Long mygroupNo = 6L;
//        MygroupRemoveDTO mygroupRemoveDTO = MygroupRemoveDTO
//                .builder()
//                .mygroupNo(mygroupNo)
//                .username(username)
//                .build();
//        log.info(mygroupService.deleteMygroup(mygroupRemoveDTO));
//    }
//
//    @Test
//    @DisplayName("즐겨찾기에 등록된 모든 사원 정보 출력")
//    void getFavoriteEmplistTest() {
//        Long mygroupNo = 6L;
//        List<FavoriteEmpListDTO> favoriteEmpListDTO = mygroupService.findAllEmp(mygroupNo);
//        favoriteEmpListDTO.forEach(log::info);
//    }
//
//    @Test
//    @DisplayName("즐겨찾기에 등록된 사원 중 해당 사원 정보 출력")
//    void getFavoriteEmpInformationTest() {
//        Long empNo = 14L;
//        Long mygroupNo = 6L;
//        FavoriteFindEmpInfoDTO favoriteFindEmpInfoDTO = FavoriteFindEmpInfoDTO
//                .builder()
//                .empNo(empNo)
//                .mygroupNo(mygroupNo)
//                .build();
//        log.info(mygroupService.findByEmpNo(favoriteFindEmpInfoDTO));
//    }
//}
