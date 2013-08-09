package com.jingpaidang.sku.dao.keyword;

import com.jingpaidang.sku.domain.keyword.Keyword;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JackChan
 * Date: 8/9/13
 * Time: 1:23 PM
 */
public interface KeywordMapper {

    /**
     * 添加单个关键词
     * @param keyword
     */
    public void addKeyword(Keyword keyword);

    public void updateKeyword(Keyword keyword);

    /**
     * 批量添加关键词
     * @param keywordList
     */
    public void addKeywordList(List<String> keywordList);

    /**
     * 通过关键词name查找到keyword
     * @param keyName
     * @return
     */
    public Keyword findKeywordByName(String keyName);

    public List<Keyword> findKeywords(List<String> strings);

    /**
     * 判断时间，删除关键词
     * 删除添加了i天以上但从未被查询过得关键词
     * 条件
     */
    public void deleteKeywordByTime(int i);

    public List<Keyword> findAllKeyword();

}
