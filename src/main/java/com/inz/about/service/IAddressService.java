package com.inz.about.service;

import com.inz.about.model.StaticCity;
import com.inz.about.model.StaticProvince;
import com.inz.about.model.StaticRegion;
import com.inz.about.model.StaticZipCode;

import java.util.List;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/10 16:55
 **/
public interface IAddressService {
    /**
     * 获取全部省
     *
     * @return 省
     */
    List<StaticProvince> findAllProvince();

    /**
     * 获取全部地级市
     *
     * @return 地级市
     */
    List<StaticCity> findAllCity();

    /**
     * 通过省Id 查询地级市
     *
     * @param provinceId 省ID
     * @return 地级市
     */
    List<StaticCity> findCityByProvinceId(String provinceId);

    /**
     * 通过是否热门 查询地级市
     *
     * @return 地级市
     */
    List<StaticCity> findCityByHot();

    /**
     * 获取全部区/县
     *
     * @return 区/县
     */
    List<StaticRegion> findAllRegion();

    /**
     * 通过地级市ID 互获取 区/县
     *
     * @param cityId 低级市ID
     * @return 区/县
     */
    List<StaticRegion> findRegionByCityId(String cityId);

    /**
     * 获取全部邮编
     *
     * @return 邮编
     */
    List<StaticZipCode> findAllZipCode();

    /**
     * 通过 区/县 ID 获取邮编
     *
     * @param regionId 区/县 ID
     * @return 邮编
     */
    List<StaticZipCode> findZipCodeByRegionId(String regionId);

}
