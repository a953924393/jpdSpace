<script type="text/javascript" src="/js/moveItems.js"></script>
<table cellpadding="0" cellspacing="0" style="border:0;" width="988">
	<tr>
		<th width="3%"><input id="checkAllItems" name="checkAllItems" type="checkbox"></th>
		<th width="6%">图片</th>
		<th width="10%">商品货号</th>
		<th width="30%">商品名称</th>
		<th width="30%">商品类目</th>
		<th width="5%">销售价</th>
		<th width="5%">库存</th>
		<th width="7%">搬家状态</th>
		<th width="4%">操作</th>
	</tr>
	<#list wareItems as wareItem>
	<tr>
		<td><input name="itemId" type="checkbox" value="${wareItem.itemId}"/></td>
		<td><img src="${wareItem.img}" style="width: 50px; height: 50px" class="img-polaroid"></td>
		<td>${wareItem.itemId}</td>
		<td>${wareItem.itemName}</td>
		<td>${wareItem.categoryName}</td>
		<td>${wareItem.price}</td>
		<td>${wareItem.storage}</td>
		<td>${wareItem.moved}</td>
		<td>修改</td>
	</tr>
	</#list>
</table>
<form id="moving-items-form" action="/move/move!targetShopList.action" method="post">
    <input type="hidden" id="src-accountId" name="accountId" value="${accountId}">
    <input type="hidden" id="src-categoryId" name="categoryId" value="">
    <input type="hidden" id="moving-items" name="itemIds" value="">

    <div class="move_bottom">
        <input id="to-move" type="image" src="/images/move.jpg" /><font id="moveCheckMsg" color="red"></font>
    </div>
</form>