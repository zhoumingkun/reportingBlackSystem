package com.toughguy.reportingSystem.controller.business;
import me.chanjar.weixin.mp.api.WxMpService;
import net.sf.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
import com.toughguy.reportingSystem.util.MyEncryptUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/wechat")
@Slf4j
public class WeixinController {
 
    @Autowired
	private IContentService contentService;
    
    @Autowired
	private IInformationService informationService;
	
	@Autowired
	private IInformerService informerService;
	
	@ResponseBody
	@RequestMapping(value = "/getOpenId", method = RequestMethod.GET)//此处填自己要用到的项目名。
	 public static String getOpenId(@RequestParam(value="code",required=false)String code) {//接收用户传过来的code，required=false表明如果这个参数没有传过来也可以。
		  //接收从客户端获取的code
		  //向微信后台发起请求获取openid的url
		  String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
	           //这三个参数就是之后要填上自己的值。
	      //替换appid，appsecret，和code
	      String requestUrl = WX_URL.replace("APPID", "wxb24760396cbf5bc4").//填写自己的appid
	        replace("SECRET", "294538f45fcbd980ade5ab589fd993ba").replace("JSCODE", code).//填写自己的appsecret，
	        replace("authorization_code", "authorization_code");
//	      String requestUrl = WX_URL.replace("APPID", "wx05dd96e3e0d5a7fb").//填写自己的appid
//	    		  replace("SECRET", "d6aae1ffc685b60fbc7a8b848514108f").replace("JSCODE", code).//填写自己的appsecret，
//	    		  replace("authorization_code", "authorization_code");
		   
	       //调用get方法发起get请求，并把返回值赋值给returnvalue
	         String  returnvalue=GET(requestUrl);
	         System.out.println(requestUrl);//打印发起请求的url
//	         System.out.println(returnvalue);//打印调用GET方法返回值
	         //定义一个json对象。 
	         JSONObject convertvalue=new JSONObject();
	      
	         //将得到的字符串转换为json
	         convertvalue=(JSONObject) JSONObject.fromObject(returnvalue);
//	         JSONObject convertvalue = JSONObject.parseObject(requestBody);
	 
	       System.out.println("return openid is ："+(String)convertvalue.get("openid")); //打印得到的openid
//	       System.out.println("return sessionkey is ："+(String)convertvalue.get("session_key"));//打印得到的sessionkey，
	       //把openid和sessionkey分别赋值给openid和sessionkey
	       String openid=(String) convertvalue.get("openid");
	       String sessionkey=(String) convertvalue.get("session_key");//定义两个变量存储得到的openid和session_key.
	 
	       return openid;//返回openid
	 }
	        //发起get请求的方法。
		public static String GET(String url) {
			String result = "";
			BufferedReader in = null;
			InputStream is = null;
			InputStreamReader isr = null;
			try {
				URL realUrl = new URL(url);
				URLConnection conn = realUrl.openConnection();
				conn.connect();
				Map<String, List<String>> map = conn.getHeaderFields();
				is = conn.getInputStream();
				isr = new InputStreamReader(is);
				in = new BufferedReader(isr);
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			} catch (Exception e) {
				// 异常记录
			} finally {
				try {
					if (in != null) {
						in.close();
					}
					if (is != null) {
						is.close();
					}
					if (isr != null) {
						isr.close();
					}
				} catch (Exception e2) {
					// 异常记录
				}
			}
			return result;
		}


//	@GetMapping("/userInfo")
//    public String userInfo(@RequestParam("code") String code) throws Exception {
//        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
//        try {
//            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
//        } catch (WxErrorException e) {
//        	throw new Exception(e.getError().getErrorMsg());
//        }
//        String openId = wxMpOAuth2AccessToken.getOpenId();
//        System.out.println(openId);
//        return openId;
//    }
	
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
		if(openId == null || "".equals(openId)) {
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
			String informerName = informer.getInformerName();
			String idCard = informer.getIdCard();
			String phoneNumber = informer.getPhoneNumber();
			String otherContectWay = informer.getOtherContectWay();
			//md5加密举报人信息
//			String informerNameMD5 = MD5Util.MD5Encode(informerName, "utf8");
			String idCardMD5 = MyEncryptUtil.encryptPhone(idCard);
			String phoneNumberMD5 = MyEncryptUtil.encryptPhone(phoneNumber);
			String otherContectWayMD5 = MyEncryptUtil.encryptPhone(otherContectWay);
//			informer.setInformerName(informerNameMD5);
			informer.setIdCard(idCardMD5);
			informer.setPhoneNumber(phoneNumberMD5);
			informer.setOtherContectWay(otherContectWayMD5);
			//添加加密举报人姓名（页面显示）
			char[] informerNames = informerName.toCharArray();
			String encryptName = "";
			if(informerNames.length == 2) {
				encryptName = informerName.substring(0,1) + "*";
			} else if(informerName.length() == 3) {
				encryptName = informerName.substring(0,1) + "**";
			} else if(informerName.length() == 4) {
				encryptName = informerName.substring(0,1) + "***";
			} else {
				encryptName = informerName.substring(0,1) + "***";
			}
			informer.setEncryptName(encryptName);
			//添加加密举报人身份证号（页面显示）
			String encryptIdCard = idCard.substring(0,1)+ "****************" + idCard.substring(idCard.length()-1,1);
			informer.setEncryptIdCard(encryptIdCard);
			//添加加密举报人手机号（页面显示）
			String encryptPhoneNumber = phoneNumber.substring(1) + "*********" + phoneNumber.substring(phoneNumber.length()-1, 1);
			informer.setEncryptPhoneNumber(encryptPhoneNumber);
		    //添加加密其他联系方式（页面显示）
			String encryptOtherContectWay = otherContectWay.substring(1) + "*********" + otherContectWay.substring(otherContectWay.length()-1, 1);
			informer.setEncryptOtherContectWay(encryptOtherContectWay);
			informerService.save(informer);
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
		Informer i = new Informer();
		Informer inf = informerService.getInformer(openId);
		if(inf != null){
			i.setEncryptName(inf.getEncryptName());
			i.setEncryptIdCard(inf.getEncryptIdCard());
			i.setEncryptOtherContectWay(inf.getEncryptOtherContectWay());
			i.setEncryptPhoneNumber(inf.getEncryptPhoneNumber());
			i.setWorkPlace(inf.getWorkPlace());
			i.setAddress(inf.getAddress());
			i.setLivingArea(inf.getLivingArea());
			return i;
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


