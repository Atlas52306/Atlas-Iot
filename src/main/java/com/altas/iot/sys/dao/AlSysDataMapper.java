package com.altas.iot.sys.dao;

import com.altas.iot.sys.domin.AlSysData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @ClassName: AlSysDataMapper
* @Description: ${description}
* @Author: LiHanzhang
* @Date: 2024-09-26 15:01
* @Email: 9255520@gmail.com
* @Version: 1.0
**/

@Mapper
public interface AlSysDataMapper extends BaseMapper<AlSysData> {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(AlSysData record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(AlSysData record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    AlSysData selectByPrimaryKey(Long id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(AlSysData record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(AlSysData record);

}