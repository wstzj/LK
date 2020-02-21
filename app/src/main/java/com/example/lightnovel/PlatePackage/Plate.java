package com.example.lightnovel.PlatePackage;

public class Plate {

    private String name;//板块名字
    private String urlForImage;//图片
    private String lastUpdate;//最新回复
    private String urlForPlate;//链接
    private String urlForLastUpdate;//链接




    public Plate(String name, String urlForImage, String lastUpdate, String urlForPlate, String urlForLastUpdate) {
        this.name = name;
        this.urlForImage = urlForImage;
        this.lastUpdate = lastUpdate;
        this.urlForPlate = urlForPlate;
        this.urlForLastUpdate = urlForLastUpdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlForImage() {
        return urlForImage;
    }

    public void setUrlForImage(String urlForImage) {
        this.urlForImage = urlForImage;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getUrlForPlate() {
        return urlForPlate;
    }

    public void setUrlForPlate(String urlForPlate) {
        this.urlForPlate = urlForPlate;
    }

    public String getUrlForLastUpdate() {
        return urlForLastUpdate;
    }

    public void setUrlForLastUpdate(String urlForLastUpdate) {
        this.urlForLastUpdate = urlForLastUpdate;
    }
}
