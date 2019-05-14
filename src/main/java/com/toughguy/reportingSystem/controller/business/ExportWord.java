package com.toughguy.reportingSystem.controller.business;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toughguy.reportingSystem.model.business.Information;
import com.toughguy.reportingSystem.model.business.Informer;
import com.toughguy.reportingSystem.model.business.Region;
import com.toughguy.reportingSystem.service.business.prototype.IInformationService;
import com.toughguy.reportingSystem.service.business.prototype.IInformerService;
import com.toughguy.reportingSystem.service.business.prototype.IRegionService;
import com.toughguy.reportingSystem.util.MyEncryptUtil;
import com.toughguy.reportingSystem.util.UploadUtil;
import com.toughguy.reportingSystem.util.WordUtils;
import com.toughguy.reportingSystem.util.XwpfTUtil;


/**
 * 导出word 举报线索登记表
 * @author 
 *
 */
@Controller
@RequestMapping("/export")
public class ExportWord {
	@Autowired
	private IInformationService informationService;
	@Autowired
	private IInformerService informerService;
	@Autowired
	private IRegionService regionService;
    /**
     * POI举报线索登记表导出word
     * @throws Exception
     */
     @RequestMapping(value="exportWord")
//     @RequiresPermissions("export:exportWord")
     public void exportWord(HttpServletResponse response,int id) throws Exception {  
            Map<String, Object> params = new HashMap<String, Object>(); 
            Information i = informationService.find(id);
			Informer ir = informerService.getInformer(i.getOpenId());
			
			Date d = new Date();
       	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            params.put("${riqi}", sdf.format(d));
            params.put("${alarmNumber}",i.getAlarmNumber());
            params.put("${acceptTime}",sdf.format(i.getAcceptTime()));
            Region re = regionService.find(i.getAcceptUnits());
            params.put("${acceptUnits}",re.getRegionName());
            params.put("${industryField}",i.getIndustryField());
            params.put("${informType}",i.getInformType());
            if(i.getInformerId() != 0){
              	 params.put("${informerName}", ir.getInformerName());
               }else if(i.getInformerId()== 0){
        			 params.put("${informerName}", "匿名"); 
        		}
//            params.put("${informerName}",ir.getInformerName());
            if(i.getInformerId() != 0){
             	 params.put("${phoneNumber}", MyEncryptUtil.decryptPhone(ir.getPhoneNumber()));
              }else if(i.getInformerId()== 0){
       			 params.put("${phoneNumber}", "匿名"); 
       		}
//            params.put("${phoneNumber}",MyEncryptUtil.decryptPhone(ir.getPhoneNumber()));
            params.put("${informContent}",i.getInformContent());
            try {
				Map<String, Object> picture = new HashMap<String, Object>();
				String[] pictures = i.getPicture().split(",");
//				for (int j = 0; j < pictures.length; j++) {
//					picture.put("width", 189);
//					picture.put("height", 119);
//					picture.put("type", "jpg");
//					picture.put("content", WordUtils.inputStream2ByteArray(new FileInputStream(UploadUtil.getAbsolutePath("barcode") + "/" + pictures[0]), true));
//				}
//				params.put("${picture}",pictures);
				WordUtils wordutil = new WordUtils();
				List<String[]> testList = new ArrayList<String[]>();
				String path = "upload/base/举报线索登记表.docx";
				String fileName= new String("举报线索登记表.docx".getBytes("UTF-8"),"iso-8859-1");    //生成word文件的文件名
				wordutil.getWord(path, params, testList, fileName, response);
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
     
}
