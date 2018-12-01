package com.toughguy.reportingSystem.controller.business;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.reportingSystem.model.business.Content;
import com.toughguy.reportingSystem.model.business.Information;
import com.toughguy.reportingSystem.model.business.Informer;
import com.toughguy.reportingSystem.service.business.prototype.IContentService;
import com.toughguy.reportingSystem.service.business.prototype.IInformationService;
import com.toughguy.reportingSystem.service.business.prototype.IInformerService;
import com.toughguy.reportingSystem.util.BackupUtil;
import com.toughguy.reportingSystem.util.MD5Util;

import lombok.extern.slf4j.Slf4j;

 
@RestController
@RequestMapping("/wechat")
@Slf4j
public class WeixinController {
 
    @Autowired
    private WxMpService wxMpService;

    @Autowired
	private IContentService contentService;
    
    @Autowired
	private IInformationService informationService;
	
	@Autowired
	private IInformerService informerService;
	
 
//    @GetMapping("/authorize")
//    public String authorize(@RequestParam("returnUrl") String returnUrl){
//        String url = "http://localhost:8082/reportingSystem/wechat/userInfo";
//        String redirectURL = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
//        return "redirect:" + redirectURL;
//    }
 
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code) throws Exception {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
        	throw new Exception(e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        System.out.println(openId);
        return openId;
    }
	
	@ResponseBody
	@RequestMapping(value = "/findAllContent")
	public List<Content> findAll() {
		return contentService.findAll();
	}
	/**
	 * 保存举报信息
	 * @param information
	 * @param resultMap
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody	
	@RequestMapping(value = "/saveInformation")
	public String saveInformation(Information information,String resultMap) throws ParseException {
		Map<String, Object> map = null;
		try {
			ObjectMapper om = new ObjectMapper();
			map = new HashMap<String,Object>();
			if (!StringUtils.isEmpty(resultMap)) {
				// 参数处理
				map = om.readValue(resultMap, new TypeReference<Map<String, Object>>() {});
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String verityCodeMD5 = MD5Util.MD5Encode(map.get("verityCode").toString(), "utf8");
		String customCodeTime = map.get("tamp").toString();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(customCodeTime);
		String newDate = simpleDateFormat.format(new Date());
		if(date.compareTo(simpleDateFormat.parse(newDate)) > 0) {
			if(verityCodeMD5.equalsIgnoreCase(map.get("customCode").toString())) {
				try {
					informationService.save(information);
					return "{ \"success\" : true }";
				} catch (Exception e) {
					e.printStackTrace();
					return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
				}
			} else {
				return "{ \"success\" : 验证码错误}";
			}
		} else {
			return "{ \"success\" : 超时}";
		}
	}
	
	/**
	 * 获取用户的举报信息
	 * @param openId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getInformation")
	public List<Information> getInformationByOpenId(String openId) {
		if("".equals(openId) || openId == null) {
			return null;
		} else {
			Informer inf = informerService.getInformer(openId);
			List<Information> inft= informationService.getInformation(inf.getId());
			if(inft != null){
				return inft;
			}else{
				return null;
			}
		}
		
	}
	/**
	 * 图片上传
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadImagefile" ,method = { RequestMethod.POST,RequestMethod.GET})
    public String uploadImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("进入get图片方法！");
 
        MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
        MultipartFile multipartFile =  req.getFile("file");
        //服务器路径需要换
//	        String realPath = "C:/Users/Administrator/git/reportingSystem/upload/barcode";
        String realPath = "C:/java/reportingSytem/upload/barcode";
        String path = BackupUtil.rename("jpg");
        try {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdir();
            }
            
            File file  =  new File(realPath,path);
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return path;
    }
	
	/**
	 * 上传视频
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadViedofile" ,method = { RequestMethod.POST,RequestMethod.GET})
    public String fileUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    System.out.println("进入get视频方法！");
        MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
        MultipartFile multipartFile =  req.getFile("file");
        //服务器路径需要换
//	        String realPath = "C:/Users/Administrator/git/reportingSystem/upload/video";
        String realPath = "C:/java/reportingSytem/upload/video";
        String path = BackupUtil.rename("mp4");
        try {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdir();
            }
            
            File file  =  new File(realPath,path);
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return path;
    }
	
	/**
	 * 保存举报人信息
	 * @param informer
	 * @return
	 */
	@ResponseBody	
	@RequestMapping(value = "/saveInformer")
	public String saveinformer(Informer informer) {
		try {
			informerService.save(informer);
			System.out.println(informer);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	/**
	 * 查询举报人
	 * @param openId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getInformer")
	public Informer getInformer(String openId) {
		Informer inf=informerService.getInformer(openId);
		if(inf != null){
			return inf;
		}else{
			return null;
		}
	}
	
	/**
	 * 修改举报人
	 * @param informer
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/editInformer")
	public String editinformer(Informer informer) {
		try {
			informerService.update(informer);
			System.out.println(informer);
			System.out.println("改完了");
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
}


