package com.sanji.mall.cart.model;

import com.sanji.mall.model.GoodsSku;
import com.sanji.mall.model.PriceAble;

/**
 * 
 * @ClassName: PriceCalculator
 * @Description: 价格计算器,单例
 * @author WuJiming wzslw_163_com
 * @date 2014年11月22日 上午11:03:17
 */
public class PriceCalculator {
    private static final PriceCalculator INSTANCE = new PriceCalculator();

    private PriceCalculator() {
    }//

    public static PriceCalculator getInstance() {
        return INSTANCE;
    }

    /**
     * 
     * @Title: calcTotalPrice @Description: 计算总价格 @param priceAble @param
     * quantity @return    设定文件 double    返回类型 @throws
     */
    public double calcTotalPrice(PriceAble priceAble, int quantity) {
        return calcSinglePrice(priceAble, quantity) * quantity;
    }

    /**
     * 
     * @Title: calcSinglePrice @Description: 计算单价 @param priceAble @param
     * quantity @return    设定文件 double    返回类型 @throws
     */
    public double calcSinglePrice(PriceAble priceAble, int quantity) {
        double singlePrice = priceAble.getPrice().doubleValue();

       /* if (priceAble instanceof GoodsSku && Integer.parseInt(((GoodsSku) priceAble).getChangeNum()) < quantity) {
            singlePrice += Double.parseDouble(((GoodsSku) priceAble).getChangePrice());
        }*/

        return singlePrice;
    }

    public double calcPoint(PriceAble priceAble, int quantity) {
        double totalPrice = calcTotalPrice(priceAble, quantity);
        return Math.floor(totalPrice / 10);
    }
}
