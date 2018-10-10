package com.inz.about.service.impl;

import com.inz.about.dao.StaticCityMapper;
import com.inz.about.dao.StaticProvinceMapper;
import com.inz.about.dao.StaticRegionMapper;
import com.inz.about.dao.StaticZipCodeMapper;
import com.inz.about.model.StaticCity;
import com.inz.about.model.StaticProvince;
import com.inz.about.model.StaticRegion;
import com.inz.about.model.StaticZipCode;
import com.inz.about.service.IAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/10 17:25
 **/
@Service(value = "addressService")
public class AddressServiceImpl implements IAddressService {
    @Resource
    private StaticCityMapper cityMapper;
    @Resource
    private StaticProvinceMapper provinceMapper;
    @Resource
    private StaticRegionMapper regionMapper;
    @Resource
    private StaticZipCodeMapper zipCodeMapper;

    @Override
    public List<StaticProvince> findAllProvince() {
        return provinceMapper.findAll();
    }

    @Override
    public List<StaticCity> findAllCity() {
        return cityMapper.findAll();
    }

    @Override
    public List<StaticCity> findCityByProvinceId(String provinceId) {
        return cityMapper.findByProvinceId(provinceId);
    }

    @Override
    public List<StaticCity> findCityByHot() {
        return cityMapper.findByHot();
    }

    @Override
    public List<StaticRegion> findAllRegion() {
        return regionMapper.findAll();
    }

    @Override
    public List<StaticRegion> findRegionByCityId(String cityId) {
        return regionMapper.findByCityId(cityId);
    }

    @Override
    public List<StaticZipCode> findAllZipCode() {
        return zipCodeMapper.findAll();
    }

    @Override
    public List<StaticZipCode> findZipCodeByRegionId(String regionId) {
        return zipCodeMapper.findByRegionId(regionId);
    }
}
