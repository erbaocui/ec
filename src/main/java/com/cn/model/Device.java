package com.cn.model;

public class Device {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_device.Id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_device.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_device.charge_ count
     *
     * @mbggenerated
     */
    private String chargeCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_device.use_count
     *
     * @mbggenerated
     */
    private String useCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_device.lat
     *
     * @mbggenerated
     */
    private String lat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_device.lon
     *
     * @mbggenerated
     */
    private String lon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_device.bell
     *
     * @mbggenerated
     */
    private String bell;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_device.pattern
     *
     * @mbggenerated
     */
    private String pattern;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_device
     *
     * @mbggenerated
     */
    public Device(String id, String name, String chargeCount, String useCount, String lat, String lon, String bell, String pattern) {
        this.id = id;
        this.name = name;
        this.chargeCount = chargeCount;
        this.useCount = useCount;
        this.lat = lat;
        this.lon = lon;
        this.bell = bell;
        this.pattern = pattern;
    }

    public Device() {
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_device.Id
     *
     * @return the value of t_device.Id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_device.name
     *
     * @return the value of t_device.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_device.charge_ count
     *
     * @return the value of t_device.charge_ count
     *
     * @mbggenerated
     */
    public String getChargeCount() {
        return chargeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_device.use_count
     *
     * @return the value of t_device.use_count
     *
     * @mbggenerated
     */
    public String getUseCount() {
        return useCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_device.lat
     *
     * @return the value of t_device.lat
     *
     * @mbggenerated
     */
    public String getLat() {
        return lat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_device.lon
     *
     * @return the value of t_device.lon
     *
     * @mbggenerated
     */
    public String getLon() {
        return lon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_device.bell
     *
     * @return the value of t_device.bell
     *
     * @mbggenerated
     */
    public String getBell() {
        return bell;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_device.pattern
     *
     * @return the value of t_device.pattern
     *
     * @mbggenerated
     */
    public String getPattern() {
        return pattern;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChargeCount(String chargeCount) {
        this.chargeCount = chargeCount;
    }

    public void setUseCount(String useCount) {
        this.useCount = useCount;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setBell(String bell) {
        this.bell = bell;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}