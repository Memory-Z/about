package com.inz.about.service

import com.inz.about.model.FileInfo

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/10 10:34
 */
public interface IFileInfoService {
    fun insert(fileInfo: FileInfo): Boolean
    fun update(fileInfo: FileInfo): Boolean
    fun deleteById(fileId: String): Boolean
    fun findById(fileId: String): FileInfo
    fun findByFileType(fileType: String, start: Int, pageSize: Int): List<FileInfo>
}
