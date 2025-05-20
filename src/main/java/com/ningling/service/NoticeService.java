package com.ningling.service;

import com.ningling.DTO.NoticeReleaseDTO;
import com.ningling.Entity.Notice;
import com.ningling.VO.NoticeVO;

import java.util.List;

public interface NoticeService {

    List<NoticeVO> getNoticesList();

    boolean insertNotice(NoticeReleaseDTO noticeReleaseDTO);

    boolean updateNotice(Notice notice);

    boolean deleteNotice(Long noticedId);

    boolean toggleNoticeTop(Long noticeId,int isTop);

    NoticeVO getNotice(Long noticeId);

}
