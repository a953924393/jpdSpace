package com.jingpaidang.cshop.dao.move;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jingpaidang.cshop.domain.move.MoveHistory;

/**
 * 
 * @ClassName:	MoveHistoryMapper 
 * @Description:TODO(搬家历史DAO) 
 * @author:	Alex
 * @date:	2013-7-22 下午2:57:16 
 *
 */
public interface MoveHistoryMapper {
	/**
	 * 
	 * @Title:insert 
	 * @Description:TODO(添加历史记录) 
	 * @param:@param moveHistory
	 * @param:@return 
	 * @return:int 
	 * @throws 
	 * @Create: 2013-7-22 下午2:58:49
	 * @Author : Alex
	 */
	public int insert(MoveHistory moveHistory);
	/**
	 * 
	 * @Title:getMoveHistoryList 
	 * @Description:TODO(查询历史列表) 
	 * @param:@param paras
	 * @param:@return 
	 * @return:List<MoveHistory> 
	 * @throws 
	 * @Create: 2013-7-23 上午11:35:00
	 * @Author : Alex
	 */
	public List<MoveHistory> getMoveHistoryList(Map<String,Object> paras);
	
	/**
	 * 
	 * @Title:getMoveHistoryListByIds 
	 * @Description:TODO(根据ids串查询列表) 
	 * @param:@param paras
	 * @param:@return 
	 * @return:List<MoveHistory> 
	 * @throws 
	 * @Create: 2013-7-24 下午2:39:12
	 * @Author : Alex
	 */
	public List<MoveHistory> getMoveHistoryListByIds(Map<String,Object> paras);
	/**
	 * 
	 * @Title:updateStatusAndReasonById 
	 * @Description:TODO(修改历史状态) 
	 * @return:void 
	 * @throws 
	 * @Create: 2013-7-24 下午3:48:06
	 * @Author : Alex
	 */
	public void updateStatusAndReasonById(Map<String,Object> paras);
	/**
	 * 
	 * @Title:deleteHistory 
	 * @Description:TODO(删除历史记录) 
	 * @param:@param paras 
	 * @return:void 
	 * @throws 
	 * @Create: 2013-7-24 下午5:10:32
	 * @Author : Alex
	 */
	public void deleteHistory(Map<String,Object> paras);
}
