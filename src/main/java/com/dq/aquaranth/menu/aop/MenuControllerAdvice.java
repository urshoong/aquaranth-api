package com.dq.aquaranth.menu.aop;

import com.dq.aquaranth.menu.controller.ControllerMarker;
import com.dq.aquaranth.menu.dto.response.ErrorResponseDTO;
import com.dq.aquaranth.menu.exception.MenuException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice(basePackageClasses = ControllerMarker.class)
public class MenuControllerAdvice {

    @ExceptionHandler({MenuException.class})
    protected ResponseEntity<ErrorResponseDTO> handleMenuException(MenuException menuException) {
        log.error("메뉴 에러 : {}", menuException.getErrorCode());
        return ErrorResponseDTO.toResponseEntity(menuException.getErrorCode());
    }
}


