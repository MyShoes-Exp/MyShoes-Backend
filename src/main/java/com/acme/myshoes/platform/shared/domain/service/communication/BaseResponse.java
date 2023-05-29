package com.acme.myshoes.platform.shared.domain.service.communication;

import org.apache.logging.log4j.util.Strings;

// Para las respuestas de la base de datos.
public abstract class BaseResponse<T>{
    private boolean success;
    private String message;
    private T resource;

    public BaseResponse(String message){
        this.message = message;
        this.success = false;
        this.resource = null;
    }

    public BaseResponse(T resource){
        this.success = true;
        this.message = Strings.EMPTY;
        this.resource = resource;
    }
}
