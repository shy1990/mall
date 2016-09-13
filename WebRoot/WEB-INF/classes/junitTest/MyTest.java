package junitTest;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jws.soap.SOAPBinding.Style;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sanji.mall.admin.service.AdminService;
import com.sanji.mall.common.util.DateUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.mobileCode.service.MobileCodeService;
import com.sanji.mall.model.Admin;
import com.sanji.mall.model.MobileCode;

public class MyTest {
	private static AdminService adminService;
	private static MobileCodeService mobileCodeService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext act = new  ClassPathXmlApplicationContext("applicationContext.xml");
		adminService = (AdminService)act.getBean("adminService");
		mobileCodeService = (MobileCodeService)act.getBean("mobileCodeService");
		 
	}

	public static void main(String[] args) {
		String aa="荣耀,苹果,小米,三星,魅族,大神,多美达,酷派,诺基亚,联想,中兴,米多,华为,Q2,纽曼,世纪天元,爱我,飞利浦,HTC,TCL,倍斯特,努比亚,美图,索尼,微软,JIMI大可乐,果果,洪洋伟业,摩托罗拉,神舟";
		String[] split = aa.split(",");
		String bb="";
		for (String string : split) {
			bb+=string+":[],";
		}
		System.out.println(bb);
	}

	@Test
	public void test() {
		Admin  admin = adminService.getAdminByMoble("15053197164");
		
		if(admin != null && !"".equals(admin.getMobilephone())){
			System.out.println("===================="+admin.getMobilephone());
		}else{
			System.out.println("=====================");
		}
	}
	@Test
	public void testMobile() {
		Timestamp d = new Timestamp(new Date().getTime());
		MobileCode mc = new MobileCode();
		mc.setId(ToolsUtil.getUUID());
		mc.setMagRecordId("112358");
		mc.setToMobile("15053197164");
		mc.setVerificationCode("112358");
		mc.setCreatetime(d);
		mobileCodeService.addMobileCode(mc);
	}
	//@Test
//	public void testMobileCode(){
//		List<MobileCode> mbList = mobileCodeService.exsitMobileCodeByMobileAndCod("15865261462","052843");
//		for(MobileCode mobileCode : mbList){
//			//boolean a = DateUtil.isCheckExpires(mobileCode.getCreatetime(), 5*60000L);
//			Long crentTime = DateUtil.getLongByDate(new Date());
//			Long sendTime = DateUtil.getLongByDate(mobileCode.getCreatetime());
//			Long timeDifference = crentTime
//					- sendTime;// 获得时间差(秒)
//			
////			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////			System.out.println(">>>>>>>>>>>>>>>>"+mobileCode.getCreatetime());
////			System.out.println("由13位long型转换回去的当前时间:" + df.format(new Date(crentTime*1000)));
////			System.out.println("由13位long型转换回去的发送时间:" + df.format(new Date(sendTime*1000)));
//			
//			if (timeDifference > 5*60L) {
//				System.out.println("====================="+timeDifference);
//				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+false);
//			} else {
//				//System.out.println("========================"+new Date().getTime());
//				System.out.println("========================="+DateUtil.getLongByDate(new Date()));
//				//System.out.println("========================"+mobileCode.getCreatetime().getTime());
//				System.out.println("=========================="+DateUtil.getLongByDate(mobileCode.getCreatetime()));
//				System.out.println("========================"+timeDifference);
//				
//				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+true);
//			}
//			
//		}
//	}

}
