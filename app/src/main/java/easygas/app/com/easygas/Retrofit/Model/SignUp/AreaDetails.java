package easygas.app.com.easygas.Retrofit.Model.SignUp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AreaDetails {

    @SerializedName("city_id")
    @Expose
    private String cityId;

    @SerializedName("city_name")
    @Expose
    private String cityName;

    @SerializedName("area_list")
    @Expose
    private List<Area_List> area_list;


    public List<Area_List> getArea_list() {
        return area_list;
    }

    public void setArea_list(List<Area_List> area_list) {
        this.area_list = area_list;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }



}