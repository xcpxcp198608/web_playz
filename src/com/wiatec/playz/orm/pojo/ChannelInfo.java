package com.wiatec.playz.orm.pojo;

/**
 * Created by xuchengpeng on 23/06/2017.
 */
public class ChannelInfo {
    private int id;
    private int channelId;
    private String tag;
    private String name;
    private String url;
    private String icon;
    private float price;
    private String type;
    private String country;
    private String style;
    private short visible;
    private boolean locked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public short getVisible() {
        return visible;
    }

    public void setVisible(short visible) {
        this.visible = visible;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "ChannelInfo{" +
                "id=" + id +
                ", channelId=" + channelId +
                ", tag='" + tag + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", country='" + country + '\'' +
                ", style='" + style + '\'' +
                ", visible=" + visible +
                ", locked=" + locked +
                '}';
    }
}
