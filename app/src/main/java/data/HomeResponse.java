package data;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by abhil on 13-10-2017.
 */

public class HomeResponse extends GenericJson {
    @Key("_id")
    private String id;
    @Key("name")
    private String name;
    @Key("position")
    private Position position;
    @Key("_acl")
    private Acl acl;
    @Key("_kmd")
    private Kmd kmd;

    public HomeResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Acl getAcl() {
        return acl;
    }

    public void setAcl(Acl acl) {
        this.acl = acl;
    }

    public Kmd getKmd() {
        return kmd;
    }

    public void setKmd(Kmd kmd) {
        this.kmd = kmd;
    }
}
