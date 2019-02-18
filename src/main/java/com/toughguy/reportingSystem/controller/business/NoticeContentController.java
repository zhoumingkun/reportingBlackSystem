package com.toughguy.reportingSystem.controller.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.reportingSystem.model.business.NoticeContent;
import com.toughguy.reportingSystem.pagination.PagerModel;
import com.toughguy.reportingSystem.service.business.prototype.INoticeContentService;

@Controller
@RequestMapping(value = "/notice")
public class NoticeContentController {
	@Autowired
	private INoticeContentService noticeContentService;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
	//@RequiresPermissions("notice:save")
	public String saveNoticeContent(NoticeContent noticeContent) {
		try {
			noticeContentService.save(noticeContent);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	//@RequiresPermissions("notice:edit")
	public String editNoticeContent(NoticeContent noticeContent) {
		try {
			noticeContentService.update(noticeContent);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	//@RequiresPermissions("notice:detele")
	public String deleteNoticeContent(int id) {
		try {
			noticeContentService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/data")
	//@RequiresPermissions("notice:data")
	public String data(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<NoticeContent> pg = noticeContentService.findPaginated(map);
			
			// 序列化查询结果为JSON
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("total", pg.getTotal());
			result.put("rows", pg.getData());
			return om.writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"total\" : 0, \"rows\" : [] }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/findByType")
	//@RequiresPermissions("notice:findByType")
	public NoticeContent findByType(int type) {
		
	    return noticeContentService.findByType(type);
	}
	
	@ResponseBody
	@RequestMapping(value = "/findAll")
	//@RequiresPermissions("notice:findAll")
	public List<NoticeContent> findAll() {
		return noticeContentService.findAll();
	}
	
}
