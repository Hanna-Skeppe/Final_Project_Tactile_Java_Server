package com.webapi.tactile.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "products", schema = "dbo", catalog = "DB_Tactile_Crafts")
public class ProductsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "artist_id", nullable = false)
    private int artistId;
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "category", nullable = false, length = 50)
    private String category;
    @Basic
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Basic
    @Column(name = "is_sale", nullable = true)
    private Boolean isSale;
    @Basic
    @Column(name = "description", nullable = true, length = 1000)
    private String description;
    @Basic
    @Column(name = "colour", nullable = false, length = 50)
    private String colour;
    @Basic
    @Column(name = "material", nullable = false, length = 50)
    private String material;
    @Basic
    @Column(name = "picture_url", nullable = false, length = 1024)
    private String pictureUrl;
    @Basic
    @Column(name = "secondary_url", nullable = true, length = 1024)
    private String secondaryUrl;
    @Basic
    @Column(name = "thumbnail_url", nullable = true, length = 1024)
    private String thumbnailUrl;
    @Basic
    @Column(name = "inventory", nullable = true)
    private Integer inventory;
    @Basic
    @Column(name = "date_created", nullable = true)
    private Date dateCreated;
    @OneToMany(mappedBy = "productsByProductId")
    private Collection<ProductFavouritesToUserEntity> productFavouritesToUsersById;
    @OneToMany(mappedBy = "productsByProductId")
    private Collection<ProductToOrderEntity> productToOrdersById;
    @ManyToOne
    @JoinColumn(name = "artist_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ArtistsEntity artistsByArtistId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getSale() {
        return isSale;
    }

    public void setSale(Boolean sale) {
        isSale = sale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getSecondaryUrl() {
        return secondaryUrl;
    }

    public void setSecondaryUrl(String secondaryUrl) {
        this.secondaryUrl = secondaryUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsEntity that = (ProductsEntity) o;

        if (id != that.id) return false;
        if (artistId != that.artistId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (isSale != null ? !isSale.equals(that.isSale) : that.isSale != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (colour != null ? !colour.equals(that.colour) : that.colour != null) return false;
        if (material != null ? !material.equals(that.material) : that.material != null) return false;
        if (pictureUrl != null ? !pictureUrl.equals(that.pictureUrl) : that.pictureUrl != null) return false;
        if (secondaryUrl != null ? !secondaryUrl.equals(that.secondaryUrl) : that.secondaryUrl != null) return false;
        if (thumbnailUrl != null ? !thumbnailUrl.equals(that.thumbnailUrl) : that.thumbnailUrl != null) return false;
        if (inventory != null ? !inventory.equals(that.inventory) : that.inventory != null) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + artistId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (isSale != null ? isSale.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (colour != null ? colour.hashCode() : 0);
        result = 31 * result + (material != null ? material.hashCode() : 0);
        result = 31 * result + (pictureUrl != null ? pictureUrl.hashCode() : 0);
        result = 31 * result + (secondaryUrl != null ? secondaryUrl.hashCode() : 0);
        result = 31 * result + (thumbnailUrl != null ? thumbnailUrl.hashCode() : 0);
        result = 31 * result + (inventory != null ? inventory.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
    }

    public Collection<ProductFavouritesToUserEntity> getProductFavouritesToUsersById() {
        return productFavouritesToUsersById;
    }

    public void setProductFavouritesToUsersById(Collection<ProductFavouritesToUserEntity> productFavouritesToUsersById) {
        this.productFavouritesToUsersById = productFavouritesToUsersById;
    }

    public Collection<ProductToOrderEntity> getProductToOrdersById() {
        return productToOrdersById;
    }

    public void setProductToOrdersById(Collection<ProductToOrderEntity> productToOrdersById) {
        this.productToOrdersById = productToOrdersById;
    }

    public ArtistsEntity getArtistsByArtistId() {
        return artistsByArtistId;
    }

    public void setArtistsByArtistId(ArtistsEntity artistsByArtistId) {
        this.artistsByArtistId = artistsByArtistId;
    }
}
