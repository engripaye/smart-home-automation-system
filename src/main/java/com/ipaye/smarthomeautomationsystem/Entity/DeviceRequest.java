package com.ipaye.smarthomeautomationsystem.Entity;

public class DeviceRequest {

    private String name;

    private String type;

    private String status;

    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type=type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status=status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location=location;
    }
}
