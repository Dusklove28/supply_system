package com.ningling.service.impl;

import com.ningling.DTO.NoticeReleaseDTO;
import com.ningling.Entity.Notice;
import com.ningling.VO.NoticeVO;
import com.ningling.mapper.NoticeMapper;
import com.ningling.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public boolean insertNotice(NoticeReleaseDTO noticeReleaseDTO) {
        Notice notice = new Notice();
        notice.setAuthorId(noticeReleaseDTO.getAuthorId());
        notice.setTitle(noticeReleaseDTO.getTitle());
        notice.setContent(noticeReleaseDTO.getContent());
        notice.setStatus(noticeReleaseDTO.getStatus());
        notice.setCreatedTime(LocalDateTime.now());
        notice.setUpdatedTime(LocalDateTime.now());

        noticeMapper.insertNotice(notice);
        return false;
    }

    @Override
    public NoticeVO getNotice(Long noticeId) {
        return noticeMapper.getNotice(noticeId);
    }

    @Override
    public List<NoticeVO> getNoticesList() {
        List<Notice> notices = noticeMapper.getNotices();
        List<NoticeVO> noticeVOS = new ArrayList<>();
        for (Notice n: notices) {

            NoticeVO noticeVO = NoticeVO.builder()
                    .noticeId(n.getNoticeId())
                    .title(n.getTitle())
                    .content(n.getContent())
                    .authorId(n.getAuthorId())
                    .status(n.getStatus())
                    .isTop(n.getIsTop())
                    .createdTime(n.getCreatedTime())
                    .updateTime(LocalDateTime.now())
                    .build();
            noticeVOS.add(noticeVO);
        }
        return noticeVOS;
    }

    @Override
    public boolean updateNotice(Notice notice) {
        if (noticeMapper.updateNotice(notice) <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteNotice(Long noticedId) {
        if (noticeMapper.deleteNotice(noticedId) <= 0) {
            return false;
        }
        return true;
    }


    @Override
    public boolean toggleNoticeTop(Long noticeId, int isTop) {
        Notice notice = new Notice();
        notice.setNoticeId(noticeId);
        notice.setIsTop(isTop);
        noticeMapper.updateNotice(notice);
        return false;
    }
}
