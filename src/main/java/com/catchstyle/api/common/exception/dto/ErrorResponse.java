//에러 내용을 파싱하여 팝업을 띄우려면, 에러 났을 때도 일관된 JSON 형태가 보장되어야함
//에러 전용 DTO
package com.catchstyle.api.common.exception.dto;

public record ErrorResponse (
        String message
){}
