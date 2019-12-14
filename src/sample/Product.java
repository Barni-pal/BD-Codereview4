package sample;

import java.io.Serializable;

public class Product implements Serializable{
    public String prodName;
    public String quantity;
    public String oldPrice;
    public String newPrice;
    public String imgPath;
    public String description;
    public Product(String prodName, String quantity, String description, String oldPrice, String newPrice, String imgPath) {
        this.prodName = prodName;
        this.quantity = quantity;
        this.description = description;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.imgPath = imgPath;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString(){
        return "{ '" + getProdName() + "', old='" + getOldPrice() + "', new='" + getNewPrice() + "'}";
    }
}
