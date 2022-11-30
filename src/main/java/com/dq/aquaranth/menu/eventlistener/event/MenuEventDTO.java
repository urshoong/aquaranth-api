package com.dq.aquaranth.menu.eventlistener.event;


import com.dq.aquaranth.menu.dto.request.MenuRequestDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuEventDTO {
    private MenuResponseDTO menuResponseDTO;
}
