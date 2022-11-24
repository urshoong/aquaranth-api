package com.dq.aquaranth.objectstorage.service;

import com.dq.aquaranth.objectstorage.dto.request.ObjectGetRequestDTO;
import com.dq.aquaranth.objectstorage.dto.request.ObjectPostRequestDTO;
import com.dq.aquaranth.objectstorage.dto.response.ObjectResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ObjectStorageService {

    ObjectResponseDTO postObject(ObjectPostRequestDTO objectPostRequestDTO) throws Exception;

    ObjectResponseDTO getObject(ObjectGetRequestDTO objectGetRequestDTO) throws Exception;

}
