package com.webapi.tactile.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "artists", schema = "dbo", catalog = "DB_Tactile_Crafts")
public class ArtistsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Basic
    @Column(name = "description", nullable = true, length = 1000)
    private String description;
    @Basic
    @Column(name = "category", nullable = false, length = 50)
    private String category;
    @Basic
    @Column(name = "url", nullable = true, length = 1024)
    private String url;
    @Basic
    @Column(name = "picture_url", nullable = true, length = 1024)
    private String pictureUrl;
    @Basic
    @Column(name = "secondary_url", nullable = true, length = 1024)
    private String secondaryUrl;
    @Basic
    @Column(name = "thumbnail", nullable = true, length = 1024)
    private String thumbnail;
    @OneToMany(mappedBy = "artistsByArtistId")
    private Collection<ProductsEntity> productsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtistsEntity that = (ArtistsEntity) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (pictureUrl != null ? !pictureUrl.equals(that.pictureUrl) : that.pictureUrl != null) return false;
        if (secondaryUrl != null ? !secondaryUrl.equals(that.secondaryUrl) : that.secondaryUrl != null) return false;
        if (thumbnail != null ? !thumbnail.equals(that.thumbnail) : that.thumbnail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (pictureUrl != null ? pictureUrl.hashCode() : 0);
        result = 31 * result + (secondaryUrl != null ? secondaryUrl.hashCode() : 0);
        result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
        return result;
    }

    public Collection<ProductsEntity> getProductsById() {
        return productsById;
    }

    public void setProductsById(Collection<ProductsEntity> productsById) {
        this.productsById = productsById;
    }
}
