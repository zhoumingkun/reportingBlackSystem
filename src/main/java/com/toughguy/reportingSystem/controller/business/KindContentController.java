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
import com.toughguy.reportingSystem.model.business.KindContent;
import com.toughguy.reportingSystem.pagination.PagerModel;
import com.toughguy.reportingSystem.service.business.prototype.IKindContentService;

@Controller
@RequestMapping(value = "/kind")
public class KindContentController {
	@Autowired
	private IKindContentService kindContentService;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
	//@RequiresPermissions("kind:save")
	public String saveKindContent(KindContent kindContent) {
		try {
			kindContentService.save(kindContent);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	//@RequiresPermissions("kind:edit")
	public String editKindContent(KindContent kindContent) {
		try {
			kindContentService.update(kindContent);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	//@RequiresPermissions("kind:detele")
	public String deleteKindContent(int id) {
		try {
			kindContentService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/data")
	//@RequiresPermissions("kind:data")
	public String data(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<KindContent> pg = kindContentService.findPaginated(map);
			
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
	@RequestMapping(value = "/findAll")
	//@RequiresPermissions("kind:findAll")
	public List<KindContent> findAll() {
		return kindContentService.findAll();
	}
	
}
