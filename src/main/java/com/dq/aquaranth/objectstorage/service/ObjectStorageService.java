package com.dq.aquaranth.objectstorage.service;

import com.dq.aquaranth.objectstorage.dto.request.ObjectRequestDTO;
import com.dq.aquaranth.objectstorage.dto.response.ObjectResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ObjectStorageService {

    ObjectResponseDTO postObject(MultipartFile multipartFile, String filename) throws Exception;

    ObjectResponseDTO getObject(ObjectRequestDTO objectRequestDTO) throws Exception;

}
