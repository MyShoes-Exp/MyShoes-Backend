package com.acme.myshoes.platform.shopping.api.rest;


import com.acme.myshoes.platform.shopping.domain.service.ShoppingCartService;
import com.acme.myshoes.platform.shopping.mapping.ShoppingCartMapper;
import com.acme.myshoes.platform.shopping.resource.CreateShoppingCartResource;
import com.acme.myshoes.platform.shopping.resource.ShoppingCartResource;
import com.acme.myshoes.platform.shopping.resource.UpdateShoppingCartResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/shoppingcart", produces = "application/json")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartMapper mapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ShoppingCartMapper mapper) {
        this.shoppingCartService = shoppingCartService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<ShoppingCartResource>getAllShoppingCart(Pageable pageable){
        return mapper.modelListPage(shoppingCartService.getAll(), pageable);
    }

    @GetMapping("{shoppingcartId}")
    public ShoppingCartResource getShoppingCartById(@PathVariable Long shoppingcartId){
        return mapper.toResource(shoppingCartService.getById(shoppingcartId));
    }

    @PostMapping
    public ShoppingCartResource createShoppingCart(@RequestBody CreateShoppingCartResource resource){
        return mapper.toResource(shoppingCartService.create(mapper.toModel(resource)));
    }

    @PutMapping("{shoppingcartId}")
    public ShoppingCartResource updateShoppingCart(@PathVariable Long shoppingcartId, @RequestBody UpdateShoppingCartResource resource){
        return mapper.toResource(shoppingCartService.update(shoppingcartId, mapper.toModel(resource)));
    }

    @DeleteMapping("{shoppingcartId}")
    public ResponseEntity<?>deleteShoppingCart(@PathVariable Long shoppingcartId){
        return shoppingCartService.delete(shoppingcartId);
    }
}

