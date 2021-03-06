package com.inz.about.dao;

import com.inz.about.model.UserFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserFileMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_file
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String maperId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_file
     *
     * @mbggenerated
     */
    int insert(UserFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_file
     *
     * @mbggenerated
     */
    int insertSelective(UserFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_file
     *
     * @mbggenerated
     */
    UserFile selectByPrimaryKey(String maperId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_file
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_file
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserFile record);

    /**
     * 通过用户ID 查询
     *
     * @param userId   用户Id
     * @param start    开始
     * @param pageSize 大小
     * @return 用户文件
     */
    List<UserFile> findByUserId(@Param("userId") String userId, @Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * 通过文件ID 查询
     *
     * @param fileId   文件ID
     * @param start    开始
     * @param pageSize 大小
     * @return 用户文件
     */
    List<UserFile> findByFileId(@Param("fileId") String fileId, @Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * 通过用户Id 查询可用的用户头像
     *
     * @param userId 用户ID
     * @return 用户头像文件
     */
    UserFile findEnableUserPhotoByUserId(String userId);

    /**
     * 通过用户Id 查询多个可用的用户头像
     *
     * @param userId 用户ID
     * @return 用户头像文件
     */
    List<UserFile> findListEnableUserPhotoByUserId(String userId);

    /**
     * 通过用户ID 查询全部用户头像
     *
     * @param userId 用户ID
     * @return 用户头像文件
     */
    List<UserFile> findAllUserPhotoByUserId(String userId);

    /**
     * 查询最后一个排序
     *
     * @param userId 用户ID
     * @return order
     */
    String findLastOrder(String userId);

}