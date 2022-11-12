package com.dq.aquaranth.menu.mapper;

import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MenuMapper {
    List<MenuResponseDTO> findAll();

    Optional<MenuResponseDTO> findByMenuNo(Long menuNo);

    Optional<MenuResponseDTO> findByMenuCode(String menuCode);

    Integer update(MenuUpdateDTO menuUpdateDTO);


    /**
     * 모든 메뉴에 대한 정보를 가져옵니다.
     * @return
     */
    List<MenuResponseDTO> findAllMenuInformation();

    /**
     * 상위메뉴번호가 없는 메뉴(GNB)를 조회합니다.
     * @return
     */
    List<MenuResponseDTO> findByUpperMenuNoIsNull();

    /**
     * 하위부서 메뉴코드를 이용하여
     * 재귀탐색을 통해
     * 상위메뉴번호가 null일때 까지 조회합니다.
     * @return
     */
    List<MenuResponseDTO> findByMenuCodeUpperRecursive();

    /**
     * 상위부서 메뉴코드를 이용하여
     * 재귀탐색을 통해
     * 모든 하위메뉴를 조회합니다.
     * @return
     */
    List<MenuResponseDTO> findByMenuCodeUnderRecursive();

    /**
     * Username을 이용하여
     * 해당하는 사용자가 접근할 수 있는
     * 모든 메뉴 목록을 가져옵니다.
     * @return
     */
    List<MenuResponseDTO> findMenusByLoginUsername(String username);


}
