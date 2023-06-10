package com.acme.myshoes.platform.designing.service;

import com.acme.myshoes.platform.designing.api.internal.DesignContextFacade;
import com.acme.myshoes.platform.designing.domain.model.Design;
import com.acme.myshoes.platform.designing.domain.service.DesignService;

import java.util.List;

public class DesignContextFacadeImpl implements DesignContextFacade {
    private final DesignService designService;
    public DesignContextFacadeImpl(DesignService designService) {
        this.designService = designService;
    }

    @Override
    public List<Design> getAllDesign() {
        return designService.getAll();
    }
}
