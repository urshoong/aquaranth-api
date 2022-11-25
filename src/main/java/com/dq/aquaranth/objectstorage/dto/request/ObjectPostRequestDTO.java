package com.dq.aquaranth.objectstorage.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObjectPostRequestDTO {
    private String filename;
    private MultipartFile multipartFile;
}
