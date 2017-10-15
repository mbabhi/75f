package data;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by abhil on 13-10-2017.
 */

public class Kmd extends GenericJson {
    @Key("lmt")
    private String lmt;
    @Key("ect")
    private String ect;

    public String getLmt() {
        return lmt;
    }

    public void setLmt(String lmt) {
        this.lmt = lmt;
    }

    public String getEct() {
        return ect;
    }

    public void setEct(String ect) {
        this.ect = ect;
    }
}
