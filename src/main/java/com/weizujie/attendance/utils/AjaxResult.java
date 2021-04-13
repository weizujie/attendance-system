package com.weizujie.attendance.utils;

import lombok.Data;

@Data
public class AjaxResult {

    private boolean success;

    private String message;

    private String imgurl;

    private String type;
}
