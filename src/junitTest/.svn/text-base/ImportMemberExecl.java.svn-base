package junitTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sanji.mall.common.util.MD5;
import com.sanji.mall.common.util.MsgUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.common.util.WdwUtil;
import com.sanji.mall.members.service.MemberService;
import com.sanji.mall.model.Members;

public class ImportMemberExecl {
	private static MemberService memberService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
		memberService = (MemberService)act.getBean("memberService");
	}
	private String errorInfo;
	private boolean flag ;
	@Test
	 public  void mainTest() throws IOException {
		
		 ImportMemberExecl imeMain = new ImportMemberExecl();
	        Members xlsMber = null;
	        List<Members> list = imeMain.readXls();
	        //从数据库导出表
	       /* try {
	            XlsDto2Excel.xlsDto2Excel(list);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }*/
	         for (int i = 0; i < list.size(); i++) {
	            xlsMber = (Members) list.get(i);
	           // MsgUtil.MsgSenderMembers(xlsMber.getMobile());
	           // memberService.deleteMemberByUsername(xlsMber.getUsername());
	               flag =  memberService.exsixtMebByMobile(xlsMber);
	               if(flag){
	            	memberService.addMeber(xlsMber);
	            	  System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+xlsMber.getMobile()+"++++++++"+flag);
	             }else{
	            	 System.out.println("========================"+xlsMber.getMobile()+"++++++++"+flag);
	             }
	            
	           /*System.out.println(xlsMber.getId() + "    " + xlsMber.getPassword()+ "    " + xlsMber.getUsername() + "    "  + xlsMber.getTruename() + "    "
	                    + xlsMber.getDitchEncode() + "    " + xlsMber.getMobile() + "    "
	                    + xlsMber.getAddress());*/
	        }
	        
	    }
	  
	  /** 
	     *  
	     * @描述：验证excel文件 
	     *  
	     * @作者：建宁 
	     *  
	     * @时间：2012-08-29 下午16:27:15 
	     *  
	     * @参数：@param filePath　文件完整路径 
	     *  
	     * @参数：@return 
	     *  
	     * @返回值：boolean 
	     */  
	  
	    private boolean validateExcel(String filePath)  
	    {  
	  
	        /** 检查文件名是否为空或者是否是Excel格式的文件 */  
	  
	        if (filePath == null || !(WdwUtil.isExcel2003(filePath) || WdwUtil.isExcel2007(filePath)))  
	        {  
	  
	            errorInfo = "文件名不是excel格式";  
	  
	            return false;  
	  
	        }  
	  
	        /** 检查文件是否存在 */  
	  
	        File file = new File(filePath);  
	  
	        if (file == null || !file.exists())  
	        {  
	  
	            errorInfo = "文件不存在";  
	  
	            return false;  
	  
	        }  
	  
	        return true;  
	  
	    }  
	    
	    private boolean isExcel2003(String filePath){
	    	 //boolean isExcel2003; 
	    	  if (WdwUtil.isExcel2007(filePath))  
	             {  
	               return false;  
	   
	             } 
	             return true;
	    	/*try  
	         {  
	   
	            //** 验证文件是否合法 *//*  
	   
	             if (validateExcel(filePath)){  
	            	 *//** 判断文件的类型，是2003还是2007 *//*  
	          	   
		             if (WdwUtil.isExcel2007(filePath))  
		             {  
		               return false;  
		   
		             } 
		             return true;
	             }  
	   
	         }catch (Exception ex){  
	  
	            ex.printStackTrace();  
	        } */
	    }
	 
	    /**
	     * 读取xls文件内容
	     * 
	     * @return List<XlsDto>对象
	     * @throws IOException
	     *             输入/输出(i/o)异常
	     */
	    private List<Members> readXls() throws IOException {
	    	String filePath = "E:\\excel\\高新：三际平台和51蜂云平台新增渠道.xlsx";
	    	InputStream inputStream = new FileInputStream(filePath);
	    	Workbook wb = null;
	    	
	    	
	        if (isExcel2003(filePath))  
         {  
	        wb = new HSSFWorkbook(inputStream);  
         }  
         else  
         {  
        	wb = new XSSFWorkbook(inputStream);  
         }  
	        Members member = null;
	        List<Members> list = new ArrayList<Members>();
	        // 循环工作表Sheet
	        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
	        	Sheet sheet = wb.getSheetAt(numSheet);
	            if (sheet == null) {
	                continue;
	            }
	            // 循环行Row
	            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
	            	Row hssfRow = sheet.getRow(rowNum);
	                if (hssfRow == null) {
	                    continue;
	                }
	                member = new Members();
	                // 循环列Cell
	                // 0学号 1姓名 2学院 3课程名 4 成绩
	                // for (int cellNum = 0; cellNum <=4; cellNum++) {
	               /* Cell xy = hssfRow.getCell(0);
	                if (xy == null) {
	                    continue;
	                }
	                System.out.println("==================="+getValue(xy));*/
	                member.setId(String.valueOf(ToolsUtil.getUUID()));
	                member.setPassword(MD5.encrypt("123456"));
	                member.setProvince("2182");
	                member.setCity("2230");
	                member.setArea("2231");
	                member.setPoint(new BigDecimal(5000));
	                member.setRegTime(new Date());
	                Cell username = hssfRow.getCell(1);
	                if (username == null) {
	                    continue;
	                }
	                member.setUsername(getFormatName(getValue(username)));
	               Cell trureName = hssfRow.getCell(2);
	                if (trureName == null) {
	                    continue;
	                }
	                member.setTruename(getValue(trureName));
	                Cell mobile = hssfRow.getCell(3);
	                if (mobile == null) {
	                    continue;
	                }
	                member.setMobile(getValue(mobile));
	               
	                Cell ditchEncode = hssfRow.getCell(4);
	                if (ditchEncode == null) {
	                    continue;
	                }
	                member.setDitchEncode(getValue(ditchEncode));
	                Cell address = hssfRow.getCell(5);
	                if (address == null) {
	                    continue;
	                }
	                member.setAddress(getFormatName(getValue(address)));
	                list.add(member);
	            }
	        }
	        return list;
	    }
	 
	    /**
	     * 得到Excel表中的值
	     * 
	     * @param hssfCell
	     *            Excel中的每一个格子
	     * @return Excel中每一个格子中的值
	     */
	    @SuppressWarnings("static-access")
	    private String getValue(Cell hssfCell) {
	    	String cellValue = "";  
	        /*if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
	            // 返回布尔类型的值
	            return String.valueOf(hssfCell.getBooleanCellValue());
	        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
	            // 返回数值类型的值
	            return String.valueOf(hssfCell.getNumericCellValue());
	        } else {
	            // 返回字符串类型的值
	            return String.valueOf(hssfCell.getStringCellValue());
	        }*/
	        if (null != hssfCell)  
            {  
                // 以下是判断数据的类型  
                switch (hssfCell.getCellType())  
                {  
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字  
                	DecimalFormat dft = new DecimalFormat("0");
                    cellValue = dft.format(hssfCell.getNumericCellValue());  
                    break;  

                case HSSFCell.CELL_TYPE_STRING: // 字符串  
                    cellValue = hssfCell.getStringCellValue();  
                    break;  

                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean  
                    cellValue = String.valueOf(hssfCell.getBooleanCellValue());  
                    break;  

                case HSSFCell.CELL_TYPE_FORMULA: // 公式  
                    cellValue = hssfCell.getCellFormula();  
                    break;  

                case HSSFCell.CELL_TYPE_BLANK: // 空值  
                    cellValue = "";  
                    break;  

                case HSSFCell.CELL_TYPE_ERROR: // 故障  
                    cellValue = "非法字符";  
                    break;  

                default:  
                    cellValue = "未知类型";  
                    break;  
                }  
            } 
	        return cellValue;
	    }

	    /**
	     * 將用戶名稱去掉橫線字符
	     * 
	     * @param String
	     *            
	     * @return String 
	     */
	    private String getFormatName(String a){
	    	// String a = "鱼台-清河镇-刘建军移动指定专营店",
	    	 String b=new String();
			 int i,j,t;
			 for(i=0;i<a.length();i++)
			 if(a.charAt(i)!='-' && a.charAt(i)!=':' && a.charAt(i)!=' ')
			 b=b+a.charAt(i);
			// System.out.println(b);
			 return b;
	    }
}
