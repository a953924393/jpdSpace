package com.jingpaidang.crm.domain.merchantmember;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Robert
 * Date: 13-8-8
 * Time: 上午9:27
 * To change this template use File | Settings | File Templates.
 */
public class MerchantMember {
    private int id;
    private int merchantId;   //  商家id
    private int memberId;     //会员  id
    private Date created;      //创建时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
