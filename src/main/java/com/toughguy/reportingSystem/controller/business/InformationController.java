package com.toughguy.reportingSystem.controller.business;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.reportingSystem.dto.InformationDTO;
import com.toughguy.reportingSystem.model.authority.User;
import com.toughguy.reportingSystem.model.business.IndustryContent;
import com.toughguy.reportingSystem.model.business.Information;
import com.toughguy.reportingSystem.model.business.Informer;
import com.toughguy.reportingSystem.pagination.PagerModel;
import com.toughguy.reportingSystem.service.authority.prototype.IUserService;
import com.toughguy.reportingSystem.service.business.prototype.IIndustryContentService;
import com.toughguy.reportingSystem.service.business.prototype.IInformationService;
import com.toughguy.reportingSystem.service.business.prototype.IInformerService;
import com.toughguy.reportingSystem.util.BackupUtil;
import com.toughguy.reportingSystem.util.FileUploadUtil;
import com.toughguy.reportingSystem.util.MD5Util;
import com.toughguy.reportingSystem.util.MyEncryptUtil;

@Controller
@RequestMapping(value = "/information")
public class InformationController {
	@Autowired
	private IInformationService informationService;
	@Autowired
	private IIndustryContentService industryContentService;
	@Autowired
	private IInformerService informerService;
	
//	@Autowired
//	private IFeedbackInformationService feedbackInformerService;
	
	@Autowired
	private IUserService userService;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
//	@RequiresPermissions("information:save")
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
	
	@ResponseBody
	@RequestMapping(value = "/edit")
//	@RequiresPermissions("information:edit")
	public String editInformation(Information information) {
		try {
			informationService.update(information);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete")
//	@RequiresPermissions("information:detele")
	public String deleteInformation(int id) {
		try {
			informationService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	/**
	 * 无权限
	 * @param params
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/noAccessData")
	@RequiresPermissions("information:data")
	public String noAccessData(String params,HttpSession session,int userId) {
		try {
			String newParams = "";
			User user = userService.find(userId);
			if(params.length()>2) {
				newParams = params.substring(0, params.length()-1);
				newParams += ",\"threadAreaIdAndAcceptUnits\":\""+user.getRegionId() + "\"}";
				System.out.println(newParams);
			} else {
				newParams = params.substring(0, 1);
				newParams += "\"threadAreaIdAndAcceptUnits\":\""+user.getRegionId() + "\"}";
				System.out.println(newParams);
			}
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Information> pg = informationService.findPaginated(map);
			for(Information i:pg.getData()) {
				i.setPhoneNumber("");
			}
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
	@RequestMapping(value = "/accessData")
	@RequiresPermissions("information:getEncrypt")
	public String accessData(String params,HttpSession session,int userId) {
		try {
			String newParams = "";
			User user = userService.find(userId);
			if(params.length()>2) {
				newParams = params.substring(0, params.length()-1);
				newParams += ",\"threadAreaIdAndAcceptUnits\":\""+user.getRegionId() + "\"}";
				System.out.println(newParams);
			} else {
				newParams = params.substring(0, 1);
				newParams += "\"threadAreaIdAndAcceptUnits\":\""+user.getRegionId() + "\"}";
				System.out.println(newParams);
			}
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(newParams)) {
				// 参数处理
				map = om.readValue(newParams, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Information> pg = informationService.findPaginated(map);
			for(Information i:pg.getData()) {
				if(i.getPhoneNumber() == null || "".equals(i.getPhoneNumber())) {
					i.setPhoneNumber("");
				}
				i.setEncryptPhoneNumber(MyEncryptUtil.decryptPhone(i.getPhoneNumber()));
				i.setPhoneNumber("");
			}
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
	
	//admin显示全部
	@ResponseBody
	@RequestMapping(value = "/accessDataAll")
//	@RequiresPermissions("information:getEncrypt")
	public String accessDataAll(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Information> pg = informationService.findPaginated(map);
			for(Information i:pg.getData()) {
				if(i.getPhoneNumber() == null || "".equals(i.getPhoneNumber())) {
					i.setPhoneNumber("");
				}
				i.setEncryptPhoneNumber(MyEncryptUtil.decryptPhone(i.getPhoneNumber()));
				i.setPhoneNumber("");
			}
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
	@RequestMapping(value = "/find")
//	@RequiresPermissions("information:find")
	public Information find(int id) {
			return informationService.find(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/findNum")
//	@RequiresPermissions("information:findNum")
	public InformationDTO findNum(int userId) {
		try {
			User user = userService.find(userId);
			InformationDTO informationDTO = new InformationDTO();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("state", 0);
			params.put("threadAreaId", user.getRegionId());
			params.put("acceptUnits", user.getRegionId());
			int sum = informationService.findNum(params);   //总数
			params.put("state", 4);
			int invalidNumber = informationService.findNum(params);   //无效案件
			params.put("state", 1);
			int validNumber = informationService.findNum(params);     //已接案件
			params.put("state", 3);
			int endNumber = informationService.findNum(params);       //已结案件
			informationDTO.setSum(sum);
			informationDTO.setInvalidNumber(invalidNumber);
			informationDTO.setValidNumber(validNumber);
			informationDTO.setEndNumber(endNumber);
			return informationDTO;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//admin
	@ResponseBody
	@RequestMapping(value = "/findNumAll")
//	@RequiresPermissions("information:findNum")
	public InformationDTO findNumAll() {
		try {
			InformationDTO informationDTO = new InformationDTO();
			int sum = informationService.findNumAll(0);   //总数
			int invalidNumber = informationService.findNumAll(4);   //无效案件
			int validNumber = informationService.findNumAll(1);     //已接案件
			int endNumber = informationService.findNumAll(3);       //已结案件
			informationDTO.setSum(sum);
			informationDTO.setInvalidNumber(invalidNumber);
			informationDTO.setValidNumber(validNumber);
		informationDTO.setEndNumber(endNumber);
		return informationDTO;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}
	@ResponseBody
	@RequestMapping(value = "/findSumAndValid")
//	@RequiresPermissions("information:findSumAndValid")
	public List<InformationDTO> findSumAndValid(int userId) {
		try {
			User user = userService.find(userId);
			List<Integer> is = informationService.findValidNumber(user.getRegionId()); //首页图表每日已接案
			List<InformationDTO> informationDTOs  = informationService.findSum(user.getRegionId()); //首页图表每日数量汇总
			if(is.size() > informationDTOs.size()) {
				for(int i=0;i<informationDTOs.size();i++) {
					informationDTOs.get(i).setValidNumber(is.get(i));
				}
			} else {
				for(int i=0;i<is.size();i++) {
					informationDTOs.get(i).setValidNumber(is.get(i));
				}
			}
			return informationDTOs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//admin
	@ResponseBody
	@RequestMapping(value = "/findSumAndValidAll")
//	@RequiresPermissions("information:findSumAndValid")
	public List<InformationDTO> findSumAndValidAll() {
		try {
			List<Integer> is = informationService.findValidNumberAll(); //首页图表每日已接案
			List<InformationDTO> informationDTOs  = informationService.findSumAll(); //首页图表每日数量汇总
			if(is.size() > informationDTOs.size()) {
				for(int i=0;i<informationDTOs.size();i++) {
					informationDTOs.get(i).setValidNumber(is.get(i));
			}
		} else {
			for(int i=0;i<is.size();i++) {
				informationDTOs.get(i).setValidNumber(is.get(i));
			}
		}
		return informationDTOs;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}
	
	/**
	 * 无权限
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/noAccessGet")
	@RequiresPermissions("information:get")
	public String noAccessGet(int id) {
		try {
			Information i = informationService.find(id);
			Informer ir = informerService.find(i.getInformerId());
			Informer informer = new Informer();
			if(ir == null) {
			} else {
				informer.setId(ir.getId());
				informer.setEncryptName(ir.getEncryptName());
				informer.setEncryptPhoneNumber(ir.getEncryptPhoneNumber());
				informer.setCreateTime(ir.getCreateTime());
				informer.setUpdateTime(ir.getUpdateTime());
			}
//			String str1 = JsonUtil.objectToJson(i);
//			String str2 = JsonUtil.objectToJson(ir);
			User u1 = new User();
			User u2 = new User();
			User u3 = new User();
			if(i.getValidAssessorId() != 0) {
				u1 = userService.find(i.getValidAssessorId());
			}
			if(i.getInvestigationAssessorId() != 0) {
				u2 = userService.find(i.getInvestigationAssessorId());
			}
			if(i.getEndAssessorId() != 0) {
				u3 = userService.find(i.getEndAssessorId());
			}
			if(u1.equals(null)) {
				System.out.println("u1为空");
			} else {
				i.setValidAssessor(u1.getUserName());
			}
			if(u2.equals(null)) {
				System.out.println("u2为空");
			} else {
				i.setInvestigationAssessor(u2.getUserName());
			}
			if(u3.equals(null)) {
				System.out.println("u3为空");
			} else {
				i.setEndAssessor(u3.getUserName());
			}
			System.out.println(i.getAcceptUnitsWord());
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("information", i);
			result.put("informer", informer);
			return om.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 有权限
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/accessGet")
	@RequiresPermissions("information:getEncrypt")
	public String AccessGet(int id) {
		try {
			Information i = informationService.find(id);
			Informer ir = informerService.find(i.getInformerId());
			Informer informer = new Informer();
			if(ir == null) {
			} else {
				informer.setId(ir.getId());
				informer.setEncryptName(ir.getInformerName());
				informer.setEncryptPhoneNumber(MyEncryptUtil.decryptPhone(ir.getPhoneNumber()));
				informer.setCreateTime(ir.getCreateTime());
				informer.setUpdateTime(ir.getUpdateTime());
			}
//			String str1 = JsonUtil.objectToJson(i);
//			String str2 = JsonUtil.objectToJson(ir);
			User u1 = new User();
			User u2 = new User();
			User u3 = new User();
			if(i.getValidAssessorId() != 0) {
				u1 = userService.find(i.getValidAssessorId());
			}
			if(i.getInvestigationAssessorId() != 0) {
				u2 = userService.find(i.getInvestigationAssessorId());
			}
			if(i.getEndAssessorId() != 0) {
				u3 = userService.find(i.getEndAssessorId());
			}
			if(u1.equals(null)) {
				System.out.println("u1为空");
			} else {
				i.setValidAssessor(u1.getUserName());
			}
			if(u2.equals(null)) {
				System.out.println("u2为空");
			} else {
				i.setInvestigationAssessor(u2.getUserName());
			}
			if(u3.equals(null)) {
				System.out.println("u3为空");
			} else {
				i.setEndAssessor(u3.getUserName());
			}
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("information", i);
			result.put("informer", informer);
			return om.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/getInformation")
//	public List<Information> getInformation(String informerId) {
//		System.out.println(informerId);
//		return informationService.getInformation(informerId);
//	}
	@ResponseBody
	@RequestMapping(value = "/getInformation")
//	@RequiresPermissions("information:getInformation")
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
	
	@ResponseBody
	@RequestMapping(value = "/toExamine")
	@RequiresPermissions("information:toExamine")
	public String toExamine(int id,int state,int assessorId,String feedbackInformation,int acceptUnits) {
		try {
			Information i = informationService.find(id);
			if(state == 1) {
				i.setValidAssessorId(assessorId);
				i.setFeedbackInformation(feedbackInformation);
				i.setState(1);
				i.setId(id);
				i.setAcceptUnits(acceptUnits);
	       	    i.setAcceptTime(new Date());
				informationService.update(i);
			} else if(state == 2) {
				i.setInvestigationAssessorId(assessorId);
				i.setFeedbackInformation(feedbackInformation);
				i.setState(2);
				i.setId(id);
				i.setAcceptUnits(acceptUnits);
				i.setAcceptTime(new Date());
				informationService.update(i);
			} else if(state == 3) {
				i.setEndAssessorId(assessorId);
				i.setFeedbackInformation(feedbackInformation);
				i.setState(3);
				i.setId(id);
				i.setAcceptUnits(acceptUnits);
				i.setAcceptTime(new Date());
				informationService.update(i);
			} else if(state == 4) {
				i.setFeedbackInformation(feedbackInformation);
				i.setState(4);
				i.setId(id);
				i.setAcceptUnits(acceptUnits);
				i.setAcceptTime(new Date());
				informationService.update(i);
			}
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	@ResponseBody
	@RequestMapping(value = "/upload")
	@RequiresPermissions("information:upload")
	public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request,int id, int state,int assessorId) {
		String fileName = file.getOriginalFilename();
		
		String filePath = "C:\\java\\reportingSytem\\upload\\barcode\\";
//		String filePath ="C:\\Users\\Administrator\\git\\reportingSystem\\upload\\barcode\\";
		System.out.println(fileName);
		try {
			FileUploadUtil.uploadFile(file.getBytes(), filePath, fileName);
			Information information = informationService.find(id);
			information.setState(state);
			if(state == 2) {
				information.setValidFile(fileName);
				information.setInvestigationAssessorId(assessorId);
			} else if(state == 3) {
				information.setInvestigationFile(fileName);
				information.setEndAssessorId(assessorId);
			}
			informationService.update(information);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "upload success";
	}
	

	//小程序的图片上传
	@ResponseBody
	@RequestMapping(value = "/uploadImagefile" ,method = { RequestMethod.POST,RequestMethod.GET})
    public String uploadImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("进入get图片方法！");
 
        MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
        MultipartFile multipartFile =  req.getFile("file");
        //服务器路径需要换
//        String realPath = "C:/Users/Administrator/git/reportingSystem/upload/barcode";
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
	@ResponseBody
	@RequestMapping(value = "/uploadViedofile" ,method = { RequestMethod.POST,RequestMethod.GET})
    public String fileUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    System.out.println("进入get视频方法！");
        MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
        MultipartFile multipartFile =  req.getFile("file");
        //服务器路径需要换
//        String realPath = "C:/Users/Administrator/git/reportingSystem/upload/video";
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

	//侦办中附件下载
	@RequestMapping(value="/fileDownload",method=RequestMethod.GET)
	  public void testDownload(HttpServletResponse res,int id) {
		Information information = informationService.find(id);
		String fileName=information.getValidFile();
	    
	    res.setHeader("content-type", "application/octet-stream");
	    res.setContentType("application/octet-stream");
	    res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
	    byte[] buff = new byte[1024];
	    BufferedInputStream bis = null;
	    OutputStream os = null;
//	    C:\\Users\\Administrator\\git\\reportingSystem\\upload\\barcode\\
//	    C:\\java\\reportingSytem\\upload\\barcode\\
	    try {
	      os = res.getOutputStream();
	      bis = new BufferedInputStream(new FileInputStream(new File("C:\\java\\reportingSytem\\upload\\barcode\\"
		          + fileName)));
	      int i = bis.read(buff);
	      while (i != -1) {
	        os.write(buff, 0, buff.length);
	        os.flush();
	        i = bis.read(buff);
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	      if (bis != null) {
	        try {
	          bis.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	      }
	    }
	    System.out.println("下载success");
	  }
	//已结案的附件下载
	@RequestMapping(value="/fileDownloadAgain",method=RequestMethod.GET)
	  public void testDownload2(HttpServletResponse res,int id) {
		Information information = informationService.find(id);
		String fileName=information.getInvestigationFile();
	    
	    res.setHeader("content-type", "application/octet-stream");
	    res.setContentType("application/octet-stream");
	    res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
	    byte[] buff = new byte[1024];
	    BufferedInputStream bis = null;
	    OutputStream os = null;
//	    C:\\Users\\Administrator\\git\\reportingSystem\\upload\\barcode\\
//	    C:\\java\\reportingSytem\\upload\\barcode\\
	    try {
	      os = res.getOutputStream();
	      bis = new BufferedInputStream(new FileInputStream(new File("C:\\java\\reportingSytem\\upload\\barcode\\"
	          + fileName)));
	      int i = bis.read(buff);
	      while (i != -1) {
	        os.write(buff, 0, buff.length);
	        os.flush();
	        i = bis.read(buff);
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	      if (bis != null) {
	        try {
	          bis.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	      }
	    }
	    System.out.println("下载又success");
	  }
	/**

	 * 查询各行业领域类型数量
	 */
	@ResponseBody
	@RequestMapping(value = "/findAllInformerType")
//	@RequiresPermissions("information:findAllInformerType")
	public Map<String, Object> findAllInformerType(int userId) {
		User user = userService.find(userId);
		Information information = informationService.findAllInformerType(user.getRegionId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("GJZZAQNumber", information.getGJZZAQNumber());
			map.put("JCZQNumber", information.getJCZQNumber());
			map.put("ZZSLCBNumber", information.getZZSLCBNumber());
			map.put("ZDCQNumber", information.getZDCQNumber());
			map.put("JSGCNumber", information.getJSGCNumber());
			map.put("QXBSNumber", information.getQXBSNumber());
			map.put("HDDNumber", information.getHDDNumber());
			map.put("FFGLFDNumber", information.getFFGLFDNumber());
			map.put("CSMJJFNumber", information.getCSMJJFNumber());
			map.put("JWHSHNumber", information.getJWHSHNumber());
			
			map.put("DJGMNumber", information.getDJGMNumber());
			map.put("HESLBHSNumber", information.getHESLBHSNumber());
			map.put("LDJYNumber", information.getLDJYNumber());
			map.put("JJFZNumber", information.getJJFZNumber());
			map.put("GSHZSNumber", information.getGSHZSNumber());
			map.put("KHZRNumber", information.getKHZRNumber());
			map.put("WLWXNumber", information.getWLWXNumber());
			return map;
	}
	
	//admin
	@ResponseBody
	@RequestMapping(value = "/findAllInformerTypeAll")
//	@RequiresPermissions("information:findAllInformerType")
	public Map<String, Object> findAllInformerTypeAll() {
		Information information = informationService.findAllInformerTypeAll();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("GJZZAQNumber", information.getGJZZAQNumber());
		map.put("JCZQNumber", information.getJCZQNumber());
		map.put("ZZSLCBNumber", information.getZZSLCBNumber());
		map.put("ZDCQNumber", information.getZDCQNumber());
		map.put("JSGCNumber", information.getJSGCNumber());
		map.put("QXBSNumber", information.getQXBSNumber());
		map.put("HDDNumber", information.getHDDNumber());
		map.put("FFGLFDNumber", information.getFFGLFDNumber());
		map.put("CSMJJFNumber", information.getCSMJJFNumber());
		map.put("JWHSHNumber", information.getJWHSHNumber());
		
		map.put("DJGMNumber", information.getDJGMNumber());
		map.put("HESLBHSNumber", information.getHESLBHSNumber());
		map.put("LDJYNumber", information.getLDJYNumber());
		map.put("JJFZNumber", information.getJJFZNumber());
		map.put("GSHZSNumber", information.getGSHZSNumber());
		map.put("KHZRNumber", information.getKHZRNumber());
		map.put("WLWXNumber", information.getWLWXNumber());
		return map;
}
	/**
	 * 根据用户id查出用户地域显示未审批案件（不可审批只可查看）和受理单位相匹配的案件
	 */
	
	@ResponseBody
	@RequestMapping(value = "/findAllIndustry")
	//@RequiresPermissions("about:findAll")
	public List<IndustryContent> findAll() {
		return industryContentService.findAll();
	}

}
