package com.acme.myshoes.platform.authentication.domain.service.communication;

import com.acme.myshoes.platform.shared.domain.service.communication.BaseResponse;

public class AuthenticateResponse extends BaseResponse<AuthenticateResponse> {

    public AuthenticateResponse(String message) {
        super(message);
    }

    public AuthenticateResponse(AuthenticateResponse resource) {
        super(resource);
    }
}
