package com.dq.aquaranth.menu.util;

import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.objectstorage.dto.request.ObjectGetRequestDTO;
import com.dq.aquaranth.objectstorage.dto.request.ObjectPostRequestDTO;
import org.springframework.web.multipart.MultipartFile;

public class ObjectStorageUtil {

    private ObjectStorageUtil() {}
    public static ObjectGetRequestDTO getObjectGetRequestDTO(MenuResponseDTO menuResponseDTO) {
        return ObjectGetRequestDTO.builder().filename(menuResponseDTO.getUuid() + menuResponseDTO.getFilename()).build();
    }

    public static ObjectGetRequestDTO getObjectGetRequestDTO(MenuResponseDTO menuResponseDTO, Integer time) {
        return ObjectGetRequestDTO.builder().filename(menuResponseDTO.getUuid() + menuResponseDTO.getFilename()).time(time).build();
    }

    public static ObjectPostRequestDTO getObjectPostRequestDTO(MultipartFile multipartFile, String uuid, String filename) {
        return ObjectPostRequestDTO.builder().filename(uuid + filename).multipartFile(multipartFile).build();
    }
}
