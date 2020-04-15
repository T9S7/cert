package com.aszz.cert.mapper;

import com.aszz.cert.model.FileUpload;
import com.aszz.cert.model.FileUploadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileUploadMapper {
    long countByExample(FileUploadExample example);

    int deleteByExample(FileUploadExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FileUpload record);

    int insertSelective(FileUpload record);

    List<FileUpload> selectByExample(FileUploadExample example);

    FileUpload selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FileUpload record, @Param("example") FileUploadExample example);

    int updateByExample(@Param("record") FileUpload record, @Param("example") FileUploadExample example);

    int updateByPrimaryKeySelective(FileUpload record);

    int updateByPrimaryKey(FileUpload record);
}