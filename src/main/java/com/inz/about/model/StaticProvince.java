package com.inz.about.model;

public class StaticProvince {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column static_province.static_province_id
     *
     * @mbggenerated
     */
    private String staticProvinceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column static_province.static_province_name
     *
     * @mbggenerated
     */
    private String staticProvinceName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column static_province.static_province_id
     *
     * @return the value of static_province.static_province_id
     *
     * @mbggenerated
     */
    public String getStaticProvinceId() {
        return staticProvinceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column static_province.static_province_id
     *
     * @param staticProvinceId the value for static_province.static_province_id
     *
     * @mbggenerated
     */
    public void setStaticProvinceId(String staticProvinceId) {
        this.staticProvinceId = staticProvinceId == null ? null : staticProvinceId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column static_province.static_province_name
     *
     * @return the value of static_province.static_province_name
     *
     * @mbggenerated
     */
    public String getStaticProvinceName() {
        return staticProvinceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column static_province.static_province_name
     *
     * @param staticProvinceName the value for static_province.static_province_name
     *
     * @mbggenerated
     */
    public void setStaticProvinceName(String staticProvinceName) {
        this.staticProvinceName = staticProvinceName == null ? null : staticProvinceName.trim();
    }
}