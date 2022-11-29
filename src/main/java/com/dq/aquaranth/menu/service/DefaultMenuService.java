//package com.dq.aquaranth.menu.service;
//
//import com.dq.aquaranth.menu.constant.ErrorCodes;
//import com.dq.aquaranth.menu.dto.request.MenuIconUpdateDTO;
//import com.dq.aquaranth.menu.dto.request.MenuInsertDTO;
//import com.dq.aquaranth.menu.dto.request.MenuRequestDTO;
//import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
//import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
//import com.dq.aquaranth.menu.exception.MenuException;
//import com.dq.aquaranth.menu.mapper.MenuMapper;
//import com.dq.aquaranth.objectstorage.dto.request.MultipartFileDTO;
//import com.dq.aquaranth.objectstorage.dto.request.ObjectGetRequestDTO;
//import com.dq.aquaranth.objectstorage.dto.request.ObjectPostRequestDTO;
//import com.dq.aquaranth.objectstorage.service.ObjectStorageService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Log4j2
//@RequiredArgsConstructor
//@Service
//public class DefaultMenuService implements MenuService {
//
//    // TODO : 프록시 패턴으로 구현해보기
//
//    private final MenuMapper menuMapper;
//    private final ObjectStorageService objectStorageService;
//
//
//    @Override
//    public MenuResponseDTO findBy(MenuRequestDTO menuRequestDTO) {
//        return null;
//    }
//
//    @Override
//    public List<MenuResponseDTO> findAllBy(MenuRequestDTO menuRequestDTO) {
//        return null;
//    }
//}
