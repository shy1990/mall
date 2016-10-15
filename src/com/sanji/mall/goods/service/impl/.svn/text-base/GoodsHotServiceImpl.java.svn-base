/**  
* @Title: GoodsHotServiceImpl.java
* @Package com.sanji.mall.goods.service.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author ZhouZhangbao  
* @date 2014-10-23 下午3:10:54
* @version V1.0  
*/
package com.sanji.mall.goods.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.collect.dao.CollectMapper;
import com.sanji.mall.goods.dao.GoodsHotMapper;
import com.sanji.mall.goods.dao.GoodsMapper;
import com.sanji.mall.goods.service.GoodsHotService;
import com.sanji.mall.goodsSku.dao.GoodsSkuMapper;
import com.sanji.mall.model.GoodsHot;
import com.sanji.mall.model.GoodsSku;

/**
 * @ClassName: GoodsHotServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-10-23 下午3:10:54
 */
@Service("goodsHotService")
@Transactional(rollbackFor = Exception.class)
public class GoodsHotServiceImpl implements GoodsHotService{
	
	
	@Resource
	private GoodsSkuMapper goodsSkuMapper;
	@Resource
	private CollectMapper collectMapper;
	@Resource
	private GoodsHotMapper goodsHotMapper;

	/* (非 Javadoc)
	* <p>Title: gainGoodsHotByType</p>
	* <p>Description: </p>
	* @param goodsHot
	* @return
	* @see com.sanji.mall.goods.service.GoodsHotService#gainGoodsHotByType(com.sanji.mall.model.GoodsHot)
	*/
	
	public List<GoodsSku> gainGoodsHotByType(GoodsHot goodsHot) {
		
		if (null != goodsHot && "sku".equals(goodsHot.getGoodsType())) {//查询的sku热销商品
			List<GoodsHot> goodsHots = goodsHotMapper.gainHotByType(goodsHot);
			List<GoodsSku> goodsSkus = new ArrayList<GoodsSku>();
			GoodsSku goodsSku ;
			for (GoodsHot goodsHot2 : goodsHots) {
				goodsSku = new GoodsSku();
				List<GoodsSku> list = goodsSkuMapper.gainHotByType(goodsHot2);
				if (null != list) {
					goodsSku = list.get(0);
					goodsSku.setCollectNum(collectMapper.gainCollectByTargetId(goodsSku.getId()));
					goodsSkus.add(goodsSku);
				}
				
			}
			return goodsSkus;
		}else if (null != goodsHot && "accessories".equals(goodsHot.getGoodsType())) {//查询配件的热销
			//本方法为首页提供，暂时不查询配件
			return null;
		}else {
			return null;
		}
		
	}
	
	
	/* (非 Javadoc)
	* <p>Title: gainGoodsHotByPhone</p>
	* <p>Description: </p>
	* @param goodsHot
	* @return
	* @see com.sanji.mall.goods.service.GoodsHotService#gainGoodsHotByPhone(com.sanji.mall.model.GoodsHot)
	*/
	
	public List<GoodsHot> gainGoodsHotByPhone(GoodsHot goodsHot) {
		List<GoodsHot> phoneList  = goodsHotMapper.gainGoodHotTopByPhone(goodsHot);
		if(phoneList.size() > 0 && phoneList != null){
			return phoneList;
		}
		return null;
	}
	/* (非 Javadoc)
	* <p>Title: gainGoodsHotByBrand</p>
	* <p>Description: </p>
	* @param goodsHot
	* @return
	* @see com.sanji.mall.goods.service.GoodsHotService#gainGoodsHotByBrand(com.sanji.mall.model.GoodsHot)
	*/
	public List<GoodsHot> gainGoodsHotByBrand(GoodsHot goodsHot) {
		List<GoodsHot> brandList  = goodsHotMapper.gainGoodHotTopByBrand(goodsHot);
		if(brandList.size() > 0 && brandList != null){
			return brandList;
		}else{
			return null;
		}
		
	}
	/* (非 Javadoc)
	* <p>Title: gainGoodsHotBy4G</p>
	* <p>Description: </p>
	* @param goodsHot
	* @return
	* @see com.sanji.mall.goods.service.GoodsHotService#gainGoodsHotBy4G(com.sanji.mall.model.GoodsHot)
	*/
	public List<GoodsHot> gainGoodsHotBy4G(GoodsHot goodsHot) {
		List<GoodsHot> fourGList  = goodsHotMapper.gainGoodHotTopBy4G(goodsHot);
		if(fourGList.size() > 0 && fourGList != null){
			return fourGList;
		}else{
			return null;
		}
		
	}
	/* (非 Javadoc)
	* <p>Title: gainGoodsHotByLt3G</p>
	* <p>Description: </p>
	* @param goodsHot
	* @return
	* @see com.sanji.mall.goods.service.GoodsHotService#gainGoodsHotByLt3G(com.sanji.mall.model.GoodsHot)
	*/
	public List<GoodsHot> gainGoodsHotByLt3G(GoodsHot goodsHot) {
		List<GoodsHot> ltThreeGList  = goodsHotMapper.gainGoodHotTopByLt3G(goodsHot);
		if(ltThreeGList.size() > 0 && ltThreeGList != null){
			return ltThreeGList;
		}else{
			return null;
		}
		
	}
	/* (非 Javadoc)
	* <p>Title: gainGoodsHotByDx3G</p>
	* <p>Description: </p>
	* @param goodsHot
	* @return
	* @see com.sanji.mall.goods.service.GoodsHotService#gainGoodsHotByDx3G(com.sanji.mall.model.GoodsHot)
	*/
	public List<GoodsHot> gainGoodsHotByDx3G(GoodsHot goodsHot) {
		List<GoodsHot> dxThreeGList  = goodsHotMapper.gainGoodHotTopByDx3G(goodsHot);
		if(dxThreeGList.size() > 0 && dxThreeGList != null){
			return dxThreeGList;
		}else{
			return null;
		}
		
	}
	/* (非 Javadoc)
	* <p>Title: gainGoodsHotByYd3G</p>
	* <p>Description: </p>
	* @param goodsHot
	* @return
	* @see com.sanji.mall.goods.service.GoodsHotService#gainGoodsHotByYd3G(com.sanji.mall.model.GoodsHot)
	*/
	public List<GoodsHot> gainGoodsHotByYd3G(GoodsHot goodsHot) {
		List<GoodsHot> ydThreeGList  = goodsHotMapper.gainGoodHotTopByYd3G(goodsHot);
		if(ydThreeGList.size() > 0 && ydThreeGList != null){
			return ydThreeGList;
		}else{
			return null;
		}
	}


}
