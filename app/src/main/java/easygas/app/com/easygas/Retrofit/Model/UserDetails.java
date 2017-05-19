package easygas.app.com.easygas.Retrofit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetails {

    @SerializedName("id")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private String userName;

    @SerializedName("balance")
    @Expose
    private String userbalance;
    @SerializedName("phone")
    @Expose
    private String userPhone;

    public String getUserbalance() {
        return userbalance;
    }

    public void setUserbalance(String userbalance) {
        this.userbalance = userbalance;
    }

    @SerializedName("email")
    @Expose
    private String userEmail;
    @SerializedName("address")
    @Expose
    private String userAddress;
    @SerializedName("area_name")
    @Expose
    private String userAreaName;
    @SerializedName("latitude")
    @Expose
    private String userLatitude;
    @SerializedName("longitude")
    @Expose
    private String userLongitude;
    @SerializedName("date_time")
    @Expose
    private String userDateTime;
    @SerializedName("img")
    @Expose
    private String userImg;

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    @SerializedName("stock")
    @Expose
    private String stock;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserAreaName() {
        return userAreaName;
    }

    public void setUserAreaName(String userAreaName) {
        this.userAreaName = userAreaName;
    }

    public String getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(String userLatitude) {
        this.userLatitude = userLatitude;
    }

    public String getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(String userLongitude) {
        this.userLongitude = userLongitude;
    }

    public String getUserDateTime() {
        return userDateTime;
    }

    public void setUserDateTime(String userDateTime) {
        this.userDateTime = userDateTime;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

}