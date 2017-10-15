package data;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by abhil on 13-10-2017.
 */

public class Position extends GenericJson {
    @Key("x")
    private Double x;
    @Key("y")
    private Double y;
    @Key("width")
    private Double width;
    @Key("height")
    private Double height;

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
