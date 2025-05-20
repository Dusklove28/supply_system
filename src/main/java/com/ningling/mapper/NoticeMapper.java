package com.ningling.mapper;


import com.ningling.Entity.Notice;
import com.ningling.VO.NoticeVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<Notice> getNotices();

    @Delete("delete from notice where notice_id = #{noticeId}")
    int deleteNotice(Long noticeId);

    int updateNotice(Notice notice);

    int insertNotice(Notice notice);

    @Select("select * from notices where notice_id = #{noticeId}")
    NoticeVO getNotice(Long noticeId);
}
