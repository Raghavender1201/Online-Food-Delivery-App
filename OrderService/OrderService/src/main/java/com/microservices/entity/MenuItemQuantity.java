package com.microservices.entity;

import jakarta.persistence.*;

@Entity
public class MenuItemQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long menuItemId;
    private Integer quantity;
//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;

    public MenuItemQuantity() {
    }

    public MenuItemQuantity(Long id, Long menuItemId, Integer quantity) {
        this.id = id;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }

    public MenuItemQuantity(Long menuItemId, Integer quantity) {
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
