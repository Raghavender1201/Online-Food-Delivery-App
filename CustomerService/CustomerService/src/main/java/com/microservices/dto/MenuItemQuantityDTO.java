package com.microservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class MenuItemQuantityDTO {

    private Long id;
    @Schema(
            name = "menuItemId", example = "1"
    )
    private Long menuItemId;
    @Schema(
            name = "quantity", example = "2"
    )
    private Integer quantity;

    public MenuItemQuantityDTO() {
    }
    public MenuItemQuantityDTO(Long id, Long menuItemId, Integer quantity) {
        this.id = id;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }
    public MenuItemQuantityDTO(Long menuItemId, Integer quantity) {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getMenuItemId() {
        return menuItemId;
    }
    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
