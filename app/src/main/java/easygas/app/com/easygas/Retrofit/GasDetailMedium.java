package easygas.app.com.easygas.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by archi1 on 5/19/2017.
 */

public class GasDetailMedium {
    @SerializedName("gas_category_id")
    @Expose
    private String gascategoryId;

    @SerializedName("gas_Provider_id")
    @Expose
    private String gasproviderId;

    public String getGascategoryId() {
        return gascategoryId;
    }

    public void setGascategoryId(String gascategoryId) {
        this.gascategoryId = gascategoryId;
    }

    public String getGasproviderId() {
        return gasproviderId;
    }

    public void setGasproviderId(String gasproviderId) {
        this.gasproviderId = gasproviderId;
    }

    public String getGascategoryName() {
        return gascategoryName;
    }

    public void setGascategoryName(String gascategoryName) {
        this.gascategoryName = gascategoryName;
    }

    public String getGascategorySize() {
        return gascategorySize;
    }

    public void setGascategorySize(String gascategorySize) {
        this.gascategorySize = gascategorySize;
    }

    @SerializedName("gas_category_name")
    @Expose
    private String gascategoryName;

    @SerializedName("gas_category_size")
    @Expose
    private String gascategorySize;
}
