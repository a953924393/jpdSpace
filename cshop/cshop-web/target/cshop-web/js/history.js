$().ready(function(){
	history.getAccounts();
	history.init();
});
var history={
		init:function(){
			$("#checkAll").click(function(){
				if($("#checkAll").prop("checked")){
					$("input[type='checkbox'][name='checkbox']").prop("checked",true);
				}else{
					$("input[type='checkbox'][name='checkbox']").prop("checked",false);
				}
			});
			$("#remove_btn").click(function(){
				var historyIds = "";
				$("input[type='checkbox'][name='checkbox']:checked").each(function(){
					historyIds+=$(this).val()+",";
				});
				historyIds = historyIds.substring(0,historyIds.length-1);
				if(historyIds!=null && historyIds!='' ){
					$.colorbox({transition:'none',overlayClose:false,href:'/js/colorbox/images/loadingAnimation.gif',title:'正在处理中。。。',width:'200',height:'100',closeButton:false});
					$.post("/history/history!removeItems.action",{historyIds:historyIds},function(data){
						$.colorbox({html:"<div style=\"text-align:center\">"+data+"</div>",width:'200',height:'100'});
					});
				}else{
					$.colorbox({html:"<div style=\"text-align:center\">请先选择要搬家的商品！</div>",width:'200',height:'100'});
				}
			});
			$("#del_btn").click(function(){
				var historyIds = "";
				$("input[type='checkbox'][name='checkbox']:checked").each(function(){
					historyIds+=$(this).val()+",";
				});
				historyIds = historyIds.substring(0,historyIds.length-1);
				if(historyIds!=null && historyIds!='' ){
					$.post("/history/history!deleteHistory.action",{historyIds:historyIds},function(data){
						location.reload();
					});
				}else{
					$.colorbox({html:"<div style=\"text-align:center\">请先选择要删除的商品！</div>",width:'200',height:'100'});
				}
			});
		},
		/**
	     * 源网店查找
	     */
	    getAccounts: function () {
	        $.post("/move/move!accounts.action", {src: 1}, function (data) {
	            var dataObj = eval("(" + data + ")");
	            //转换为json对象
	            $.each(dataObj, function (idx, item) {
	                $("#accounts").append("<option value=" + item.id + ">" + item.platformLoginName + "</option>");
	            });
	        });
	    },
}