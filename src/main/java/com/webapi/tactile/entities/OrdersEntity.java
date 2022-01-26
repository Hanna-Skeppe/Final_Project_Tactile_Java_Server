package com.webapi.tactile.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "orders", schema = "dbo", catalog = "DB_Tactile_Crafts")
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "app_user_id", nullable = false)
    private int appUserId;
    @Basic
    @Column(name = "creation_date", nullable = true)
    private Date creationDate;
    @Basic
    @Column(name = "total_price", nullable = true)
    private BigDecimal totalPrice;
    @Basic
    @Column(name = "payment_reference", nullable = true, length = 128)
    private String paymentReference;
    @ManyToOne
    @JoinColumn(name = "app_user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private AppUsersEntity appUsersByAppUserId;
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "ordersByOrderId")
    private Collection<ProductToOrderEntity> productToOrdersById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (id != that.id) return false;
        if (appUserId != that.appUserId) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (totalPrice != null ? !totalPrice.equals(that.totalPrice) : that.totalPrice != null) return false;
        if (paymentReference != null ? !paymentReference.equals(that.paymentReference) : that.paymentReference != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + appUserId;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (paymentReference != null ? paymentReference.hashCode() : 0);
        return result;
    }

    public AppUsersEntity getAppUsersByAppUserId() {
        return appUsersByAppUserId;
    }

    public void setAppUsersByAppUserId(AppUsersEntity appUsersByAppUserId) {
        this.appUsersByAppUserId = appUsersByAppUserId;
    }

    public Collection<ProductToOrderEntity> getProductToOrdersById() {
        return productToOrdersById;
    }

    public void setProductToOrdersById(Collection<ProductToOrderEntity> productToOrdersById) {
        this.productToOrdersById = productToOrdersById;
    }
}
