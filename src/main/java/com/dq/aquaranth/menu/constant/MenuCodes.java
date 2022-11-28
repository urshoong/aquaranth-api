package com.dq.aquaranth.menu.constant;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuCodes {
    SYS("SYS"),
    ORGA("ORGA"),
    ORGA0010("ORGA0010"),
    ORGA0020("ORGA0020"),
    ORGA0030("ORGA0030"),
    ROLE("ROLE"),
    ROLE0010("ROLE0010"),
    ROLE0020("ROLE0020"),
    ROLE0030("ROLE0030"),
    MAIL("MAIL"),
    BOARD("BOARD"),
    DRIVE("DRIVE"),
    CALENDER("CALENDER");

    private final String code;
}
