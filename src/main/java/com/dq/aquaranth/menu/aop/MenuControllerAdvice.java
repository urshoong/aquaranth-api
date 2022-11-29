package com.dq.aquaranth.menu.aop;

import com.dq.aquaranth.menu.controller.MenuControllerMarker;
import com.dq.aquaranth.menu.dto.response.ErrorResponseDTO;
import com.dq.aquaranth.menu.exception.MenuException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice(basePackageClasses = MenuControllerMarker.class)
public class MenuControllerAdvice {

    @ExceptionHandler({MenuException.class})
    protected ResponseEntity<ErrorResponseDTO> handleMenuException(MenuException menuException) {
        log.error("메뉴 에러 : {}", menuException.getErrorCodes());
        return ErrorResponseDTO.toResponseEntity(menuException.getErrorCodes());
    }
}


