package com.toughguy.reportingSystem.util;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class POIUtils {

	/**
	 * @return HSSFCellStyle Style
	 * @ 表头样式     总样式
	 */
	public CellStyle Style(SXSSFWorkbook wb) {
		CellStyle style = wb.createCellStyle();
		// 设置边框	
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
//		style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
//		style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
//		style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		
//		style.setRightBorderColor(HSSFColor.BLACK.index);
//		style.setLeftBorderColor(HSSFColor.BLACK.index);
//		style.setTopBorderColor(HSSFColor.BLACK.index);
//		style.setBottomBorderColor(HSSFColor.BLACK.index);
//		// 设置边框颜色
//		style.setTopBorderColor(HSSFColor.BLACK.index);
//		style.setBottomBorderColor(HSSFColor.BLACK.index);
//		style.setLeftBorderColor(HSSFColor.BLACK.index);
//		style.setRightBorderColor(HSSFColor.BLACK.index);
		
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		
		
		Font fon = wb.createFont();
		fon.setFontName("黑体");
		fon.setFontHeightInPoints((short) 16);// 设置字体大小
		fon.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		
		style.setFont(fon);// 选择需要用到的字体格式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		return style;
	}
//-----------------样式一------------------------------------------	
	
	/**
	 * @return HSSFCellStyle StyleOne
	 * @ 样式一  样式一  黄色背景  字体14
	 */
	public CellStyle StyleOne(SXSSFWorkbook wb) {
		CellStyle style = Style(wb);
		 //设置背景颜色
		style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				
		Font fon = wb.createFont();
		fon.setFontName("黑体");
		fon.setFontHeightInPoints((short) 14);// 设置字体大小
		
		style.setFont(fon);// 选择需要用到的字体格式
		return style;
	}
	
	/**
	 * @return HSSFCellStyle StyleOne1
	 * @ 样式一  黄色背景  字体12
	 */
	public CellStyle StyleOne1(SXSSFWorkbook wb) {
		CellStyle style = StyleOne(wb);
		Font fon = wb.createFont();
		fon.setFontName("黑体");
		fon.setFontHeightInPoints((short) 12);// 设置字体大小
		style.setWrapText(true); 
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
		style.setFont(fon);// 选择需要用到的字体格式
		return style;
	}
	
	//-----------------样式二------------------------------------------	
	
		/**
		 * @return HSSFCellStyle StyleTwo
		 * @ 样式二  橘色背景  字体14
		 */
		public CellStyle StyleTwo(SXSSFWorkbook wb) {
			CellStyle style = Style(wb);
			 //设置背景颜色
			style.setFillForegroundColor(IndexedColors.GOLD.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
					
			Font fon = wb.createFont();
			fon.setFontName("黑体");
			fon.setFontHeightInPoints((short) 14);// 设置字体大小
			
			style.setFont(fon);// 选择需要用到的字体格式
			return style;
		}
		
		
		/**
		 * @return HSSFCellStyle StyleTwo1
		 * @ 样式二  橘色背景  字体12
		 */
		public CellStyle StyleTwo1(SXSSFWorkbook wb) {
			CellStyle style = StyleTwo(wb);
			Font fon = wb.createFont();
			fon.setFontName("黑体");
			fon.setFontHeightInPoints((short) 12);// 设置字体大小
			style.setWrapText(true); 
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
			style.setFont(fon);// 选择需要用到的字体格式
			return style;
		}
		
	//-----------------样式三------------------------------------------	
		
		/**
		 * @return HSSFCellStyle StyleThree
		 * @ 样式三  绿色背景  字体14
		 */
		public CellStyle StyleThree(SXSSFWorkbook wb) {
			CellStyle style = Style(wb);
			 //设置背景颜色
			style.setFillForegroundColor(IndexedColors.LIME.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
					
			Font fon = wb.createFont();
			fon.setFontName("黑体");
			fon.setFontHeightInPoints((short) 14);// 设置字体大小
			
			style.setFont(fon);// 选择需要用到的字体格式
			return style;
		}
		
		/**
		 * @return HSSFCellStyle StyleThree1
		 * @ 样式三  绿色背景  字体12
		 */
		public CellStyle StyleThree1(SXSSFWorkbook wb) {
			CellStyle style = StyleThree(wb);
			Font fon = wb.createFont();
			fon.setFontName("黑体");
			fon.setFontHeightInPoints((short) 12);// 设置字体大小
			style.setWrapText(true); 
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
			style.setFont(fon);// 选择需要用到的字体格式
			return style;
		}
		
	//-----------------样式四------------------------------------------	
		/**
		 * @return HSSFCellStyle StyleFour
		 * @ 样式四  橘色背景  字体14
		 */
			public CellStyle StyleFour(SXSSFWorkbook wb) {
				CellStyle style = Style(wb);
				 //设置背景颜色
				style.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
						
				Font fon = wb.createFont();
				fon.setFontName("黑体");
				fon.setFontHeightInPoints((short) 14);// 设置字体大小
				
				style.setFont(fon);// 选择需要用到的字体格式
				return style;
			}
			
			/**
			 * @return HSSFCellStyle
			 * @ 样式四  橘色背景  字体12
			 */
			public CellStyle StyleFour1(SXSSFWorkbook wb) {
				CellStyle style = StyleFour(wb);
				Font fon = wb.createFont();
				fon.setFontName("黑体");
				fon.setFontHeightInPoints((short) 12);// 设置字体大小
				style.setWrapText(true); 
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
				style.setFont(fon);// 选择需要用到的字体格式
		        return style;
			}

			/**
			 * @return HSSFCellStyle
			 * @ 样式五  橘色背景  字体12
			 */
			public CellStyle Style1(SXSSFWorkbook wb) {
				CellStyle style = Style(wb);
				Font fon = wb.createFont();
				fon.setFontName("黑体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setWrapText(true);
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
				style.setFont(fon);// 选择需要用到的字体格式
				return style;
			}
			
			
			public CellStyle Style2(SXSSFWorkbook wb) {
				CellStyle style = Style(wb);
				Font fon = wb.createFont();
				fon.setFontName("黑体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setWrapText(true); 
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
				style.setFont(fon);// 选择需要用到的字体格式
				return style;
			}
			//工作底稿导出样式
			public CellStyle Style3(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 11);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
	            DataFormat format2 = wb.createDataFormat();
	            style.setDataFormat(format2.getFormat("0.0"));//设置单元类型保留一位小数
				return style;
			}
			public CellStyle Style4(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 11);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
	            DataFormat format2 = wb.createDataFormat();
	            style.setDataFormat(format2.getFormat("0.00"));//设置单元类型保留两位小数
				return style;
			}
			public CellStyle Style5(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 11);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
	            DataFormat format2 = wb.createDataFormat();
	            style.setDataFormat(format2.getFormat("0.00"));//设置单元类型保留两位小数
				return style;
			}
			/**
			* 导出样品交接单样式
			*/
			public CellStyle Style6(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 11);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				return style;
			}
			public CellStyle Style7(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 11);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);//居右
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				return style;
			}
			/**
			* 设置单元格边框（解决合并单元格显示部分边框问题）
			* @param sheet 
			* @param region
			* @param cs
			*/
			@SuppressWarnings("deprecation")
			public void setRegionStyle(Sheet sheet, CellRangeAddress region, CellStyle cs) {
			 for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
			  Row row = CellUtil.getRow(i, sheet);
			  for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
			   Cell cell = CellUtil.getCell(row, (short) j);
			   cell.setCellStyle(cs);
			  }
			 }
			}
			
			/** 
			 * 样品登记薄样式
			 * @param wb
			 * @return
			 */
			public CellStyle StyleSamplePlace(SXSSFWorkbook wb) {
				CellStyle style = Style(wb);
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 11);// 设置字体大小
				style.setWrapText(true); 
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
				style.setFont(fon);// 选择需要用到的字体格式
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				return style;
			}
			/**
			* 导出样品确认单样式
			*/
			public CellStyle Style8(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 11);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_LEFT);//居左
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				return style;
			}
			/**
			* 导出监督检查档案样式
			*/
			//字体加粗+上边框加粗+下边框加粗+左边框加粗
			public CellStyle Style9(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体
				fon.setFontHeightInPoints((short) 11);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
				style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);//上边框加粗
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM); //下边框加粗
				style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);//左边框加粗
				return style;
			}
			//上边框加粗
			public CellStyle Style10(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
				style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);//上边框加粗
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				return style;
			}
			//换行+下边框加粗
			public CellStyle Style11(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setWrapText(true);  //自动换行
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM); //下边框加粗
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				return style;
			}
			//右边框加粗+下边框加粗
			public CellStyle Style12(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);//右边框加粗
				style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM); //下边框加粗
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				return style;
			}
			//上边框加粗+右边框加粗
			public CellStyle Style13(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
				style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);//上边框加粗
				style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);//右边框加粗
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				return style;
			}
			//字体加粗+换行+上边框加粗+左边框加粗
			public CellStyle Style17(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setWrapText(true);  //自动换行
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
				style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);//上边框加粗
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);//左边框加粗
				return style;
			}
			//字体加粗+下边框加粗+左边框加粗
			public CellStyle Style18(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM); //下边框加粗
				style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);//左边框加粗
				return style;
			}
			//字体加粗+上边框加粗+下边框加粗
			public CellStyle Style19(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setWrapText(true);  //自动换行
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
				style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);//上边框加粗
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM); //下边框加粗
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				return style;
			}
			//换行+上下左右边框
			public CellStyle Style20(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setWrapText(true);  //自动换行
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				return style;
			}
			//换行+上边框加粗+下边框加粗+右边框加粗
			public CellStyle Style21(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setWrapText(true);  //自动换行
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
				style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);//上边框加粗
				style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);//右边框加粗
				style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM); //下边框加粗
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				return style;
			}
			//无边框基础样式
			public CellStyle Style22(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
				return style;
			}
			//边框加粗+等级的数字转换
			public CellStyle Style23(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
		        DataFormat format=wb.createDataFormat();
		        style.setDataFormat(format.getFormat("[DbNum1][$-804]0等"));
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
				style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);//上边框加粗
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				return style;
			}
			//长宽高保留两位小数 
			public CellStyle Style24(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				DataFormat format2 = wb.createDataFormat();
	            style.setDataFormat(format2.getFormat("0.00"));//设置单元类型保留两位小数
				return style;
			}
			//保留一位小数 
			public CellStyle Style25(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				DataFormat format2 = wb.createDataFormat();
	            style.setDataFormat(format2.getFormat("0.0"));//设置单元类型保留一位小数
				return style;
			}
			//下边框加粗+保留一位
			public CellStyle Style26(SXSSFWorkbook wb) {
				CellStyle style = wb.createCellStyle();
				Font fon = wb.createFont();
				fon.setFontName("宋体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setFont(fon);// 选择需要用到的字体格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM); //下边框加粗
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				DataFormat format2 = wb.createDataFormat();
				style.setDataFormat(format2.getFormat("0.0"));//设置单元类型保留一位小数
				return style;
			}
			/**
			 * @return HSSFCellStyle
			 * @ 导出总表保留小数样式
			 */
			public CellStyle Style14(SXSSFWorkbook wb) {
				CellStyle style = Style(wb);
				Font fon = wb.createFont();
				fon.setFontName("黑体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setWrapText(true); 
				 style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setFont(fon);// 选择需要用到的字体格式
				DataFormat format2 = wb.createDataFormat();
	            style.setDataFormat(format2.getFormat("0.00"));//设置单元类型保留两位小数
				return style;
			}
			public CellStyle Style15(SXSSFWorkbook wb) {
				CellStyle style = Style(wb);
				Font fon = wb.createFont();
				fon.setFontName("黑体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setWrapText(true); 
				 style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setFont(fon);// 选择需要用到的字体格式
				DataFormat format2 = wb.createDataFormat();
	            style.setDataFormat(format2.getFormat("0.0"));//设置单元类型保留一位小数
				return style;
			}
			public CellStyle Style16(SXSSFWorkbook wb) {
				CellStyle style = Style(wb);
				Font fon = wb.createFont();
				fon.setFontName("黑体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				style.setWrapText(true); 
				 style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setFont(fon);// 选择需要用到的字体格式
				DataFormat format2 = wb.createDataFormat();
	            style.setDataFormat(format2.getFormat("0"));//设置单元类型保留整数
				return style;
			}
			//总表的等级转换
			public CellStyle Style27(SXSSFWorkbook wb) {
				CellStyle style = Style(wb);
				Font fon = wb.createFont();
				fon.setFontName("黑体");
				fon.setFontHeightInPoints((short) 10);// 设置字体大小
				DataFormat format=wb.createDataFormat();
		        style.setDataFormat(format.getFormat("[DbNum1][$-804]0等"));
				style.setWrapText(true); 
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
				style.setFont(fon);// 选择需要用到的字体格式
				return style;
			}

			
}
