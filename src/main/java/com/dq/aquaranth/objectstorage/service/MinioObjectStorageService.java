package com.dq.aquaranth.objectstorage.service;

import com.dq.aquaranth.objectstorage.dto.request.ObjectGetRequestDTO;
import com.dq.aquaranth.objectstorage.dto.request.ObjectPostRequestDTO;
import com.dq.aquaranth.objectstorage.dto.response.ObjectResponseDTO;
import com.dq.aquaranth.objectstorage.repository.MinioAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MinioObjectStorageService implements ObjectStorageService {
    private final MinioAdaptor minioAdaptor;
    @Override
    public ObjectResponseDTO postObject(ObjectPostRequestDTO objectPostRequestDTO) throws Exception {
        minioAdaptor.putObject(objectPostRequestDTO.getFilename(), objectPostRequestDTO.getMultipartFile());
        return ObjectResponseDTO.builder()
                .url(minioAdaptor.getPresignedObjectUrl(objectPostRequestDTO.getFilename()))
                .build();
    }

    @Override
    public ObjectResponseDTO getObject(ObjectGetRequestDTO objectGetRequestDTO) throws Exception {
        ObjectResponseDTO.builder()
                .url(minioAdaptor.getPresignedObjectUrl(objectGetRequestDTO.getFilename()))
                .build();

        if (Objects.nonNull(objectGetRequestDTO.getTime())){
            return ObjectResponseDTO.builder()
                    .url(minioAdaptor.getPresignedObjectUrl(objectGetRequestDTO.getFilename(), objectGetRequestDTO.getTime()))
                    .build();
        }
        return ObjectResponseDTO.builder()
                .url(minioAdaptor.getPresignedObjectUrl(objectGetRequestDTO.getFilename()))
                .build();
    }
}
