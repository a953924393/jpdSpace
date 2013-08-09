package com.jingpaidang.cshop.dao.convert;

import com.jingpaidang.cshop.domain.convert.ConvertRuleDetail;
import com.jingpaidang.cshop.domain.convert.PlatformConvertRule;

import java.util.List;

/**
 * Mapper for converting rules.
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 6/25/13
 */
public interface ConvertRuleMapper {

    /**
     * Update convert rule
     *
     * @param convertRule
     */
    public void updatePlatformConvertRule(PlatformConvertRule convertRule);

    /**
     * update convert rule modifyTime
     * @param convertRule
     */
    public void updateConvertRuleModifyTime(PlatformConvertRule convertRule);
    /**
     * Insert convert rule.
     *
     * @param convertRule
     */
    public void insertPlatformConvertRule(PlatformConvertRule convertRule);

    /**
     * Query convert rule by id.
     *
     * @param id
     */
    public PlatformConvertRule findPlatformConvertRuleById(long id);

    /**
     * Query platform convert rules by more conditions.
     *
     * @param platformConvertRule
     * @return
     */
    public List<PlatformConvertRule> findPlatformConvertRules(PlatformConvertRule platformConvertRule);

    /**
     * Query convert rules by accountId.
     * @param accountId
     * @return
     */
    public List<PlatformConvertRule> findPlatformConvertRulesByUserId(long accountId);

    /**
     * Query all the convert rules.
     *
     * @return List
     */
    public List<PlatformConvertRule> findPlatformConvertRules();

    /**
     * Insert convert rule detail.
     *
     * @param convertRuleDetail
     */
    public void insertConvertRuleDetail(ConvertRuleDetail convertRuleDetail);

    /**
     * Update convert rule detail.
     *
     * @param convertRuleDetail
     */
    public void updateConvertRuleDetail(ConvertRuleDetail convertRuleDetail);

    /**
     * Query rule details by ruleId.
     *
     * @param ruleId
     * @return
     */
    public List<ConvertRuleDetail> findConvertRuleDetailByRuleId(long ruleId);
    /**
     * delete rule by ruleId
     * @table name: jshop_platform_convert_rule
     */
    public void delConvertRuleByRuleId(String ruleId);
    /**
     * delete rule details by ruleId
     * @table name: jshop_convert_rule_detail
     */
    public void delConvertRuleDetailByRuleId(String ruleId);
}
