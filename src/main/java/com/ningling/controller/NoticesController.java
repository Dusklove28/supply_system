package com.ningling.controller;

import com.ningling.DTO.NoticeReleaseDTO;
import com.ningling.Entity.Notice;
import com.ningling.VO.NoticeVO;
import com.ningling.service.NoticeService;
import com.ningling.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api("公告管理")
@RequestMapping("/notice")
public class NoticesController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/getNoticesList")
    public Result<List<NoticeVO>> getNoticesList(){
        List<NoticeVO> noticesList = noticeService.getNoticesList();
        return Result.success(noticesList);
    }

    @GetMapping("/getNotice/{noticeId}")
    public Result<NoticeVO> getNotice(@PathVariable Long noticeId){
        NoticeVO noticesList = noticeService.getNotice(noticeId);
        return Result.success(noticesList);
    }

    @PostMapping("/releaseNotice")
    @ApiOperation("发布公告")
    public Result releaseNotice(@RequestBody NoticeReleaseDTO noticeReleaseDTO){
        noticeService.insertNotice(noticeReleaseDTO);
        return Result.success();
    }

    @PutMapping("/updateNotice/{noticedId}")
    @ApiOperation("修改公告")
    public Result updateNotice(@RequestBody Notice notice,@PathVariable Long noticedId){
        notice.setNoticeId(noticedId);
        noticeService.updateNotice(notice);
        return Result.success("修改成功");
    }

    @DeleteMapping("/deleteNotice/{noticedId}")
    @ApiOperation("删除公告")
    public Result deleteNotice(@PathVariable Long noticedId){
        noticeService.deleteNotice(noticedId);
        return Result.success("删除成功");
    }

    @PutMapping("/toggleNoticeTop/{noticeId}")
    @ApiOperation("修改置顶状态")
    public Result updateNotice(@PathVariable Long noticeId,
                               @RequestBody Map<String, Integer> requestBody){
        int isTop = requestBody.get("isTop");

        if(!noticeService.toggleNoticeTop(noticeId,isTop)){
            return Result.error("置顶失败");
        }
        return Result.success("置顶成功");
    }
}
