package com.webapi.tactile.entities;

import javax.persistence.*;

@Entity
@Table(name = "product_favourites_to_user", schema = "dbo", catalog = "DB_Tactile_Crafts")
public class ProductFavouritesToUserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "product_id", nullable = false)
    private int productId;
    @Basic
    @Column(name = "app_user_id", nullable = false)
    private int appUserId;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ProductsEntity productsByProductId;
    @ManyToOne
    @JoinColumn(name = "app_user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private AppUsersEntity appUsersByAppUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductFavouritesToUserEntity that = (ProductFavouritesToUserEntity) o;

        if (id != that.id) return false;
        if (productId != that.productId) return false;
        if (appUserId != that.appUserId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + productId;
        result = 31 * result + appUserId;
        return result;
    }

    public ProductsEntity getProductsByProductId() {
        return productsByProductId;
    }

    public void setProductsByProductId(ProductsEntity productsByProductId) {
        this.productsByProductId = productsByProductId;
    }

    public AppUsersEntity getAppUsersByAppUserId() {
        return appUsersByAppUserId;
    }

    public void setAppUsersByAppUserId(AppUsersEntity appUsersByAppUserId) {
        this.appUsersByAppUserId = appUsersByAppUserId;
    }
}
