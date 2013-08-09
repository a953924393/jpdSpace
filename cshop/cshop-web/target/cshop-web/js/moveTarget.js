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
                move.getShopCategories(accountId);
            }

        });
        $("#categories").change(function () {
            //获取Select选择的Text
            var targetAccountId = $("#accounts").val();
            var targetCategoryId = $(this).val();
            var srcAccountId = $("#src-account-id").val();
            var srcCategoryId = $("#src-category-id").val();
            var params = {
                'convertRule.targetAccountId': targetAccountId,
                'convertRule.targetCategoryId': targetCategoryId,
                'convertRule.srcAccountId': srcAccountId,
                'convertRule.srcCategoryId': srcCategoryId
            };
            if (targetCategoryId != -1) {
                move.getConvertRules(params);
            }

        });
        $("#refresh").click(function () {
            //获取Select选择的Text
            var targetAccountId = $("#accounts").val();
            var targetCategoryId = $(this).val();
            var srcAccountId = $("#src-account-id").val();
            var srcCategoryId = $("#src-category-id").val();
            var params = {
                'convertRule.targetAccountId': targetAccountId,
                'convertRule.targetCategoryId': targetCategoryId,
                'convertRule.srcAccountId': srcAccountId,
                'convertRule.srcCategoryId': srcCategoryId
            };
            if (targetCategoryId != -1) {
                move.getConvertRules(params);
            }

        });


        $("#downloadCategories").click(function () {
            //获取Select选择的Text
            var accountId = $(this).val();
            //获取Select选择的Value
            move.downloadCategories(accountId);
        });
        $("#submit-move").click(function() {

            move.moveItems();

        })
    },

    /**
     * 目标网店查找
     */
    getAccounts: function () {
        $.post("/move/move!accounts.action", {src : 0}, function (data) {
            var dataObj = eval("(" + data + ")");
            //转换为json对象
            $.each(dataObj, function (idx, item) {
                $("#accounts").append("<option value=" + item.id + ">" + item.platformLoginName + "</option>");
            });
        });
    },

    /**
     * 目标类目查找
     * @param at
     * @param flag
     */
    getCategories: function (at) {
        $.post("/move/move!categories.action", {accountId: at}, function (data) {
            var dataObj = eval("(" + data + ")");
            $("#categories").empty();
            $("#categories").append("<option value=-1>请选择...</option>");
            //转换为json对象
            $.each(dataObj, function (idx, item) {
                $("#categories").append("<option value=" + item.categoryId + ">" + item.categoryName + "</option>");
            });
        });
    },
    /**
     * 查找店铺的自定义分类类目信息
     */
    getShopCategories: function (accountId) {
        $.post("/move/move!shopCategories.action", {accountId: accountId}, function (data) {
            $("#shopCategories").empty();
            //转换为json对象
            var dataObj = eval("(" + data + ")");
            if(dataObj.length ==0 ) {
                $("#shopCategories").append("<option value=-1>无网店类目</option>");
            } else {
                $.each(dataObj, function (idx, item) {
                    if(item.isParent) {
                        $("#shopCategories").append("<option value=" + item.cid + "-" + item.cid + ">" + item.name + "</option>");

                    } else {
                        $("#shopCategories").append("<option value=" + item.cid + "-" + item.parent_id +  ">" + item.name + "</option>");

                    }

                });

            }
        });
    },

    getConvertRules: function (params) {
        $.getJSON("/convert/convert!convertRules.action", params, function (data) {
            $("#convert-rule-sel").empty();
            $("#convert-rule-sel").append("<option value=-1>请选择...</option>");
            $.each(data, function (index, item) {
                $("#convert-rule-sel").append("<option value=" + item.id + ">" + item.ruleEncoding + "</option>");
            });
        })
    },

    moveItems: function () {
        if(!move.checkTarget())
            return false ;

        var itemIds = $("#items-2-moving").val();

        var shopCategoryId = $("#shopCategories3").val();

        var ruleId = $("#convert-rule-sel").val();
        $.colorbox({transition:'none',overlayClose:false,href:'/js/colorbox/images/loadingAnimation.gif',title:'正在处理中。。。',width:'200',height:'100',closeButton:false});
        $.post("/move/move!moveItems.action", {itemIds :itemIds, shopCategoryId: shopCategoryId, ruleId: ruleId}, function (data) {
            var tbAppKey = $("#tbAppKey").val();
            var tbRedirectUri = $("#tbRedirectUri").val();

            if(data == -1) {

                $.colorbox({html:"<div style=\"text-align:center\"><a href='https://oauth.taobao.com/authorize?response_type=code&client_id=" + tbAppKey + "&redirect_uri=" + tbRedirectUri + "'   target='_blank' >" + " 上传失败，点击重新获取授权！</a></div>",width:'200',height:'100'});

            } else {

                $.colorbox({html:"<div style=\"text-align:center\">上传完成！</div>",width:'200',height:'100'});
            }

        })

    } ,
    /**
     * 点击下载类目信息
     * @param at
     * @param flag
     */
    downloadCategories: function (at) {
        $.post("/move/move!downloadCategories.action", {accountId: at}, function (data) {
            $("#categories").empty();
            var dataObj = eval("(" + data + ")");

            //转换为json对象
            $.each(dataObj, function (idx, item) {
                $("#categories").append("<option value=" + item.categoryId + ">" + item.categoryName + "</option>");
            });
        });
    },
    /**
     * 在查询商品之前检查源网店和类目
     */
    checkTarget: function () {

        if(($("#accounts").val() == "")||($("#accounts").val()==-1) ) {
            document.getElementById("div1").innerHTML = '请选择源商店!';
            document.getElementById("accounts").focus();
            return false;
        }else{
            document.getElementById("div1").innerHTML = '';
        }
        if(($("#categories").val() == "")||($("#categories").val()=="-1") ) {
            document.getElementById("div1").innerHTML = '请选择类目!';
            document.getElementById("categories").focus();
            return false;
        } else{
            document.getElementById("div1").innerHTML = '';
        }
        if(($("#convert-rule-sel").val() == "")||($("#convert-rule-sel").val()=="-1") ) {
            document.getElementById("div1").innerHTML = '请选择类目!';
            document.getElementById("convert-rule-sel").focus();
            return false;
        } else{
            document.getElementById("div1").innerHTML = '';
        }
        return true;
    }


};
