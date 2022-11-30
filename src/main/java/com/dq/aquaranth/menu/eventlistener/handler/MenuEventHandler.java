package com.dq.aquaranth.menu.eventlistener.handler;

import com.dq.aquaranth.login.service.RedisService;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.eventlistener.event.MenuEventDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;

import static com.dq.aquaranth.menu.util.RedisUtil.getMenuKey;

@Log4j2
@Component
@RequiredArgsConstructor
public class MenuEventHandler {

    private final RedisService redisService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void updateRedis (MenuEventDTO menuEventDTO) throws JsonProcessingException {
        MenuResponseDTO menuResponseDTO
                = menuEventDTO.getMenuResponseDTO();
        redisService.setCacheObject(getMenuKey(menuResponseDTO),menuResponseDTO);
        log.info("Update Completed. {}", menuResponseDTO.getMenuCode());
    }
}
