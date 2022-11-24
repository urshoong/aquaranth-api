package com.dq.aquaranth.objectstorage.controller;


import com.dq.aquaranth.objectstorage.dto.request.ObjectGetRequestDTO;
import com.dq.aquaranth.objectstorage.dto.request.ObjectPostRequestDTO;
import com.dq.aquaranth.objectstorage.dto.response.ObjectResponseDTO;
import com.dq.aquaranth.objectstorage.service.ObjectStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(("/api/file"))
public class ObjectStorageController {

    private final ObjectStorageService objectStorageService;

    @PostMapping
    // FIXME
    public ResponseEntity<ObjectResponseDTO> postObject(@RequestParam("file") MultipartFile multipartFile, String filename) throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(objectStorageService.postObject(ObjectPostRequestDTO.builder().build()));
    }

    @GetMapping("/{filename}")
    public ResponseEntity<ObjectResponseDTO> getUrl(@PathVariable("filename") String filename) throws Exception {
        ObjectGetRequestDTO objectGetRequestDTO = ObjectGetRequestDTO.builder()
                .filename(filename)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(objectStorageService.getObject(objectGetRequestDTO));
    }
}
