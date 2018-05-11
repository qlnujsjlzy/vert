package com.zhaoyang.vert.module.system.controller;

import com.zhaoyang.vert.core.base.controller.BaseController;
import com.zhaoyang.vert.core.common.annotion.BusinessLog;
import com.zhaoyang.vert.core.common.constant.dictmap.NoticeMap;
import com.zhaoyang.vert.core.common.constant.enums.BizExceptionEnum;
import com.zhaoyang.vert.core.exception.VertException;
import com.zhaoyang.vert.core.log.PrototypeBeanInstance;
import com.zhaoyang.vert.core.shiro.ShiroKit;
import com.zhaoyang.vert.core.util.ToolUtil;
import com.zhaoyang.vert.module.system.factory.DaoFactory;
import com.zhaoyang.vert.module.system.model.Notice;
import com.zhaoyang.vert.module.system.service.NoticeService;
import com.zhaoyang.vert.module.system.wrapper.NoticeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通知控制器
 *
 * @author fengshuonan
 * @Date 2017-05-09 23:02:21
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    private String PREFIX = "/system/notice/";
    @Resource
    private PrototypeBeanInstance prototypeBeanInstance;
    @Autowired
    private NoticeService noticeService;

    /**
     * 跳转到通知列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "notice.html";
    }

    /**
     * 跳转到添加通知
     */
    @RequestMapping("/notice_add")
    public String noticeAdd() {
        return PREFIX + "notice_add.html";
    }

    /**
     * 跳转到修改通知
     */
    @RequestMapping("/notice_update/{noticeId}")
    public String noticeUpdate(@PathVariable Integer noticeId, Model model) {
        Notice notice = this.noticeService.selectById(noticeId);
        model.addAttribute("notice",notice);
        prototypeBeanInstance.getLogSessionHolder().setObject(notice);
        return PREFIX + "notice_edit.html";
    }

    /**
     * 跳转到首页通知
     */
    @RequestMapping("/hello")
    public String hello() {
        List<Map<String, Object>> notices = noticeService.list(null);
        super.setAttr("noticeList",notices);
        return "/blackboard.html";
    }

    /**
     * 获取通知列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Map<String, Object>> list = this.noticeService.list(condition);
        return super.wrapObject(new NoticeWrapper(list));
    }

    /**
     * 新增通知
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @BusinessLog(value = "新增通知",key = "title",dict = NoticeMap.class)
    public Object add(Notice notice) {
        if (ToolUtil.isOneEmpty(notice, notice.getTitle(), notice.getContent())) {
            throw new VertException(BizExceptionEnum.REQUEST_NULL);
        }
        notice.setCreator(ShiroKit.getUser().getId());
        notice.setCreateTime(new Date());
        notice.insert();
        return SUCCESS_TIP;
    }

    /**
     * 删除通知
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @BusinessLog(value = "删除通知",key = "noticeId",dict = NoticeMap.class)
    public Object delete(@RequestParam Integer noticeId) {

        //缓存通知名称
        prototypeBeanInstance.getLogSessionHolder().setObject(DaoFactory.instance().getNoticeTitle(noticeId));

        this.noticeService.deleteById(noticeId);

        return SUCCESS_TIP;
    }

    /**
     * 修改通知
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    @BusinessLog(value = "修改通知",key = "title",dict = NoticeMap.class)
    public Object update(Notice notice) {
        if (ToolUtil.isOneEmpty(notice, notice.getId(), notice.getTitle(), notice.getContent())) {
            throw new VertException(BizExceptionEnum.REQUEST_NULL);
        }
        Notice old = this.noticeService.selectById(notice.getId());
        old.setTitle(notice.getTitle());
        old.setContent(notice.getContent());
        old.updateById();
        return SUCCESS_TIP;
    }

}
