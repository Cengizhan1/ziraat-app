package com.ziraat.app.user.dto;

import com.ziraat.app.user.model.enums.AddressType;

public record UserContactInformationRequest(
    String address,
    AddressType addressType,
    String phone,
    String email,
    Boolean phonePermission,
    Boolean emailPermission
) {
}
