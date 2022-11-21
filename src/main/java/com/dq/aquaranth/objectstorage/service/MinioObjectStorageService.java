package com.dq.aquaranth.objectstorage.service;

import com.dq.aquaranth.objectstorage.dto.request.ObjectRequestDTO;
import com.dq.aquaranth.objectstorage.dto.response.ObjectResponseDTO;
import com.dq.aquaranth.objectstorage.repository.MinioAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioObjectStorageService implements ObjectStorageService {
    private final MinioAdaptor minioAdaptor;
    @Override
    public ObjectResponseDTO postObject(MultipartFile multipartFile, String filename) throws Exception {
        minioAdaptor.putObject(filename, multipartFile);
        return ObjectResponseDTO.builder()
                .url(minioAdaptor.getPresignedObjectUrl(filename))
                .build();
    }

    @Override
    public ObjectResponseDTO getObject(ObjectRequestDTO objectRequestDTO) throws Exception {
        return ObjectResponseDTO.builder()
                .url(minioAdaptor.getPresignedObjectUrl(objectRequestDTO.getFilename()))
                .build();
    }
}
