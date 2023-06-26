package com.acme.myshoes.platform.designing.api.rest;

import com.acme.myshoes.platform.designing.domain.service.DesignService;
import com.acme.myshoes.platform.designing.mapping.DesignMapper;
import com.acme.myshoes.platform.designing.resource.CreateDesignResource;
import com.acme.myshoes.platform.designing.resource.DesignResource;
import com.acme.myshoes.platform.designing.resource.UpdateDesignResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/designs", produces = "application/json")
@Tag(name = "Designs", description = "Create, read, update and delete designs")
public class DesignController {
    private final DesignService designService;
    private final DesignMapper mapper;

    public DesignController(DesignService designService, DesignMapper mapper) {
        this.designService = designService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get all designs")
    @GetMapping
    public Page<DesignResource> getAllDesigns(Pageable pageable) {
        return mapper.modelListPage(designService.getAll(), pageable);
    }

    @Operation(summary = "Get design by ID")
    @GetMapping("{designId}")
    public DesignResource getDesignById(@PathVariable Long designId) {
        return mapper.toResource(designService.getById(designId));
    }

    @Operation(summary = "Create design")
    @ApiResponse(description = "Design successfully created", responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DesignResource.class)))
    @PostMapping
    public DesignResource createDesign(@RequestBody CreateDesignResource resource) {
        return mapper.toResource(designService.create(mapper.toModel(resource)));
    }

    @Operation(summary = "Update design")
    @PutMapping("{designId}")
    public DesignResource updateDesign(@PathVariable Long designId, @RequestBody UpdateDesignResource resource) {
        return mapper.toResource(designService.update(designId, mapper.toModel(resource)));
    }

    @Operation(summary = "Delete design")
    @DeleteMapping("{designId}")
    public ResponseEntity<?> deleteDesign(@PathVariable Long designId) {
        return designService.delete(designId);
    }
}
