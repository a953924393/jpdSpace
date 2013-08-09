$().ready(function () {
    move.getAccounts();
    move.init();
});
var move = {

    /**
     * 初始化各个动作事件
     */
    init: function () {
        $("#accounts").change(function () {
            //获取Select选择的Text
            var accountId = $(this).val();
            if (accountId != -1) {
                //获取Select选择的Value
                move.getCategories(accountId);
            }
            if(accountId==-1){
                //Synchronize the sub-options with the value of the primary
                $("#categories").html("<option value=\"-1\">请选择</option>");
            }
        });


        $("#downloadCategories").click(function () {
            var accountId = $("#accounts").val();
            //获取Select选择的Value
            move.downloadCategories(accountId);
        })

        $("#submit-query-form").click(function () {
            move.findItemList();
        })

    },

    /**
     * 按照商品id查询商品的sku
     * @param wareId
     */

    getSkus: function (wareId) {
        $.post("/move/move!skus.action", {wareId: wareId}, function (data) {
            var dataObj = eval("(" + data + ")");
            //转换为json对象
            $.each(dataObj, function (idx, item) {
                $("#accounts").append("<option value=" + item.id + ">" + item.platformLoginName + "</option>");
            });
        });
    },
    /**
     * 源网店查找
     */
    getAccounts: function () {
        $.post("/move/move!accounts.action", {src: 1}, function (data) {
            var dataObj = eval("(" + data + ")");
            var tbAppKey = $("#tbAppKey").val();
            var tbRedirectUri = $("#tbRedirectUri").val();
            if(dataObj.length == 0) {

                $.colorbox({html:"<div style=\"text-align:center\"><a href='https://oauth.taobao.com/authorize?response_type=code&client_id=" + tbAppKey + "&redirect_uri=" + tbRedirectUri + "'   target='_blank' >" + " 源网店为空，点击加入源网店</a></div>",width:'200',height:'100'});

            } else {

                //转换为json对象
                $.each(dataObj, function (idx, item) {
                    $("#accounts").append("<option value=" + item.id + ">" + item.platformLoginName + "</option>");
                });

            }

        });
    },
    /**
     * 源类目查找
     * @param at
     * @param flag
     */
    getCategories: function (at) {
        $.post("/move/move!categories.action", {accountId: at}, function (data) {
            $("#categories").empty();
            var dataObj = eval("(" + data + ")");

            //转换为json对象
            $.each(dataObj, function (idx, item) {
                $("#categories").append("<option value=" + item.categoryId + " cid=" + item.id +">" + item.categoryName + "</option>");
            });
        });
    },

    /**
     * 点击下载类目信息
     * @param at
     * @param flag
     */
    downloadCategories: function (at) {
        var accountId = $("#accounts").val();
        if(accountId=="" || accountId==-1){
            $("#div1").html("请选择源商店!");
            return ;
        }else{
            $("#div1").html("");
        }
        $.post("/category/category!downloadCategories.action", {accountId: at}, function (data) {
            $("#categories").empty();
            var dataObj = eval("(" + data + ")");

            //转换为json对象
            $.each(dataObj, function (idx, item) {
                $("#categories").append("<option value=" + item.categoryId + ">" + item.categoryName + "</option>");
            });
        });
    },
    /**
     * 选择条件后得到商品列表结果
     *
     */
    findItemList: function () {
        if(!move.checkFind())
            return;
        var accountId = $("#accounts").val();
        var categoryId = $("#categories").val();
        var queryKey = $("input[name=queryKey]").val();
        var itemId = $("input[name=itemId]").val();
        var moved = $("input[name=moved]:checked").val();
        var sale = $("input[name=sale]:checked").val();
        var cid = $("#categories").children("option:selected").attr("cid");
        $.post("/move/move!findItemList.action", {accountId: accountId, categoryId: categoryId, queryKey: queryKey, itemIds: itemId, moved: moved, sale: sale,cid:cid}, function (data) {
        		$("#ware-item-list").html(data);
                $("#src-categoryId").val(cid);
        });
    },
    
    /**
     * 下载商品到库
     */
    downloadWares:function(){
    	var accountId = $("#accounts").val();

        if(accountId=="" || accountId==-1){
            $("#div1").html("请选择源商店!");
            return ;
        }else{
            $("#div1").html("");
        }
        $.colorbox({transition:'none',overlayClose:false,href:'/js/colorbox/images/loadingAnimation.gif',title:'正在处理中。。。',width:'200',height:'100',closeButton:false});

        $.post("/wares/wares!downloadWares.action",{accountId:accountId},function(data){
            var tbAppKey = $("#tbAppKey").val();
            var tbRedirectUri = $("#tbRedirectUri").val();

    		if(data>0){
    			$.colorbox({html:"<div style=\"text-align:center\">更新了 "+ data +" 条商品！</div>",width:'200',height:'100'});
    		}else if(data==-1){
    			$.colorbox({html:"<div style=\"text-align:center\"><a href='https://oauth.taobao.com/authorize?response_type=code&client_id=" + tbAppKey + "&redirect_uri=" + tbRedirectUri + "'   target='_blank' >" + " 点击重新获取授权！</a></div>",width:'200',height:'100'});
    		}else{
                $.colorbox({html:"<div style=\"text-align:center\">没有更新到最新商品！</div>",width:'200',height:'100'});
    		}

    	});
    },
    /**
     * @author 杨振
     * 在查询商品之前检查源网店和类目
     */
    checkFind: function () {

        if(($("#accounts").val() == "")||($("#accounts").val()==-1) ) {
            document.getElementById("div1").innerHTML = '请选择源商店!';
            document.getElementById("accounts").focus();
            return false;
        }else{
            document.getElementById("div1").innerHTML = '';
        }
        if(($("#categories").val() == "")||($("#categories").val()=="-1") ) {
            document.getElementById("div2").innerHTML = '请选择类目!';
            document.getElementById("categories").focus();
            return false;
        } else{
            document.getElementById("div2").innerHTML = '';
        }
        return true;
    }

};
