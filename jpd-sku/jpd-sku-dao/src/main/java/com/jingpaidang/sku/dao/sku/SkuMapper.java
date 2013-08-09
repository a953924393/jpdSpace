package com.jingpaidang.sku.dao.sku;

import com.jingpaidang.sku.domain.keyword.Keyword;
import com.jingpaidang.sku.domain.sku.Sku;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JackChan
 * Date: 8/9/13
 * Time: 1:23 PM
 */
public interface SkuMapper {
    /**
     * 和删除关键词相关连
     * keywordMapper.deleteKeywordByTime(int i );
     * @param i
     */
    public void deleteSkuRelateKeyByTime(int i);

    /**
     * 删除当前日期的前一天的sku
     */
    public void deleteYesterday();

    /**
     * 单个添加sku
     *
     */
    public void addSku(Sku sku);

    public void addSkuList(List<Sku> skuList);

    /**
     * 根据keyName 和skuName 查询 sku
     * @param keyName
     * @param skuNum
     * @return
     */
    public List<Sku> findSkuBySkuNumAndKey(@Param("keyName")String keyName, @Param("skuNum")String skuNum);
}
