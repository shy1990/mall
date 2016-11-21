package com.sanji.mall.goods.service.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.goods.dao.GoodsMapper;
import com.sanji.mall.goods.service.GoodsService;
import com.sanji.mall.members.dao.MembersMapper;
import com.sanji.mall.model.Goods;
import com.sanji.mall.model.Members;

/**
 * 商品信息
 * 
 * @author 田超强
 * 
 */
@Service("goodsService")
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl implements GoodsService {

	@Resource
	private GoodsMapper goodsMapper;
	@Resource
	private MembersMapper membersMaper;

	public List<Goods> select() {
		// TODO Auto-generated method stub
		return goodsMapper.select();
	}

	public Goods selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return goodsMapper.selectByPrimaryKey(id);
	}

	public List<Goods> gainAllGoodsInfoByGoodsNum(String goodsNum) {
		// TODO Auto-generated method stub
		return goodsMapper.gainAllGoodsInfoByGoodsNum(goodsNum);
	}

	public List<Goods> gainAllGoodsInfoByMap(Map param) {
		// TODO Auto-generated method stub
		return goodsMapper.gainAllGoodsInfoByMap(param);
	}

	public int upClickRate(String goodsNum) {
		// TODO Auto-generated method stub
		return goodsMapper.upClickRate(goodsNum);
	}

	public List<Goods> gainByPage(Map param) {
		// TODO Auto-generated method stub
		return goodsMapper.gainByPage(param);
	}

	public String gainCountNum(Map param) {
		// TODO Auto-generated method stub
		return goodsMapper.gainCountNum(param);
	}

	public List<Goods> gainAccessoriesPage(Map param) {
		// TODO Auto-generated method stub
		return goodsMapper.gainAccessoriesPage(param);
	}

	public String gainAccessoriesCountNum(Map param) {
		// TODO Auto-generated method stub
		return goodsMapper.gainAccessoriesCountNum(param);
	}

	public List<Goods> getHotSale(int page, int size, String brandName) {
		int start = (page - 1) * size;
		int end = page * size;
		List<Goods> goodss = goodsMapper.gainHotByBrandName(start, end, brandName);
		return goodss;
	}

	@SuppressWarnings("rawtypes")
	public List<Map<String, Object>> getBjd(String brandName, String userId,String machineType) {
		Members user = membersMaper.gainMembersDetailById(userId);
		String regionId = user.getArea();
		 Integer City = Integer.valueOf(user.getCity());
		 String username=user.getUsername();
		List<Map<String, Object>> selectBjd = goodsMapper.selectBjd(brandName, userId,regionId,machineType, user.getUsername());
		
		for (int i = 0; i < selectBjd.size(); i++) {
		      if (((Map)selectBjd.get(i)).get("GOODS_NAME").equals("酷派 酷派cool 1合约机B2B")){
		        if ((City.intValue() != 2311) && (City.intValue() != 2293) && (City.intValue() != 2331) && (City.intValue() != 2214)) {
		          selectBjd.remove(i);
		        }
		      }
		      if (selectBjd.get(i).get("GOODS_NAME").equals("奇酷  奇酷360 F4合约机") && selectBjd.get(i).get("GOODS_NAME").equals("奇酷  奇酷360 F4合约机（可调拨串号）")){  
	    		       if (City == 2183 || City == 2243 || City == 2324) {
	    		    	   
	    		    	    selectBjd.remove(i);
	    		       }
	    		  
	    		    
	      }
	
		      if(selectBjd.get(i).get("GOODS_NAME").equals("华为 华为畅享6（合约机）B2B")&&selectBjd!=null){
		    	  if(username.equals("中国移动寨里盛达店")||
		    			  username.equals("双杨添翼全网通营业厅")||
		    			  username.equals("淄川区龙泉村顺达手机经营部")||
		    			  username.equals("昆仑志鹏通讯")||
		    			  username.equals("中国移动科胜手机卖场")||
		    			  username.equals("中国移动铭俊手机专卖店")||
		    			  username.equals("淄博市周村区王村镇星宇手机卖场")||
		    			  username.equals("淄博市周村区王村镇威科特瑞手机特卖场")||
		    			  username.equals("淄博市周村区王村镇鑫兴手机卖场")||
		    			  username.equals("淄博市周村区王村镇彭阳联通营业厅")||
		    			  username.equals("淄博市周村区名机谷手机卖场")||
		    			  username.equals("淄博市周村区萌水镇亿达通讯")||
		    			  username.equals("淄博市周村区东门路移动营业厅")||
		    			  username.equals("淄博市周村区丝市街延鑫通讯")||
		    			  username.equals("淄博市周村区丝市街基雅通讯")||
		    			  username.equals("山东省淄博市张店区南定镇移动手机大卖场")||
		    			  username.equals("山东省淄博市张店区南定镇乐讯通讯器材经营部")||
		    			  username.equals("山东省淄博市张店区马尚镇蓝光电子")||
		    			  username.equals("山东省淄博市张店区傅家镇杜飞通讯")||
		    			  username.equals("山东省淄博市张店区沣水镇嘉诚通讯")||
		    			  username.equals("淄博市临淄区朱台镇E世界手机卖场")||
		    			  username.equals("临淄区敬仲镇晴天合作营业厅")||
		    			  username.equals("淄博市高青县田镇华讯通讯")||
		    			  username.equals("淄博市高青县唐坊镇飞天手机")||
		    			  username.equals("山东省淄博市张店区傅家镇杜飞通讯")||
		    			  username.equals("淄博市高青县青城镇海洋通讯")||
		    			  username.equals("淄博市高青县木李镇中国移动镇中路店")||
		    			  username.equals("淄博市高青县黑里寨镇成亮通讯")||
		    			  username.equals("淄博市高青县高城镇全球通通讯")||
		    			  username.equals("淄博市高青县常家镇三合移动店")||
		    			  username.equals("淄博市高青县赵店俊杰通讯") 
		    			  ){
		    		  
		    	  }else{
		    		
		    		 selectBjd.remove(i);	
		    		 
		    	  }
		      }
	}
		    	  
		
		
		setHot(selectBjd);
		return selectBjd;
	}

	// 设置热销
	private void setHot(List<Map<String, Object>> hw) {
		int hotCount = 1;
		for (Map<String, Object> map : hw) {
			int saleIndex = ((BigDecimal) map.get("SALE_INDEX")).intValue();
			boolean isHot = new Boolean((String) map.get("IS_QUOTATION_HOT"));
			if (saleIndex <= 3 && hotCount <= 5) {
				map.put("IS_QUOTATION_HOT", true);
				map.put("IS_NEW", false);
				hotCount++;
			} else if (isHot && hotCount <= 5) {
				map.put("IS_QUOTATION_HOT", true);
				map.put("IS_NEW", false);
			} else {
				map.put("IS_QUOTATION_HOT", false);
			}
		}
	}

	public List<Map<String, String>> gainNameBylike(String s) {
		return goodsMapper.gainNameBylike(s);
	}

	public List<Goods> gainAllGoodsInfo2(String memberId, String goodsNum, String area, String userName) {
		return goodsMapper.gainAllGoodsInfo2(memberId, goodsNum, area,userName);
	}

    @Override
    public List<Goods> gainAllShoppingRush(String memberId, String goodsNum, String area, String userName) {
        return gainAllShoppingRush(memberId, userName);
    }

	@Override
	public List<Goods> gainAllShoppingRush(String memberId, String userName) {
		return goodsMapper.gainAllShoppingRush(memberId,userName);
	}
	
	@Override
	public String gainDealNum(String goodsId) {
		// TODO Auto-generated method stub
		return goodsMapper.gainDealNum(goodsId);
	}
}
