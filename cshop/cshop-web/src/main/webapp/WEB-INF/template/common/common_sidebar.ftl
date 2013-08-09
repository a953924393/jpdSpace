<div class="left">
		<div class="sidebar-t">店铺管理</div>
		<p class="sidebar-p-hover">一键搬家</p>
			<ul>
				<li><a href="${rootUrl}/move/move!srcShopItemList.action">一键搬家</a></li>
				<li><a href="${rootUrl}/convert/convert!list.action">规则设置</a></li>
				<li><a href="${rootUrl}/history/history!moveHistoryList.action">搬家历史</a></li>
			</ul>
		<p class="sidebar-p">一键同步</p>
</div>
<script>
    $(function () {
        $(".left p").click(function () {
        	if($(this).attr("class")=="sidebar-p"){
        		 $(this).attr("class","sidebar-p-hover");
        	}else{
        		$(this).attr("class","sidebar-p");
        	}
        	if($(this).next("ul").css("display")=="block"){
        		$(this).next("ul").css("display","none");
        	}else{
        		$(this).next("ul").css("display","block");
        	}
        });
    });
</script>