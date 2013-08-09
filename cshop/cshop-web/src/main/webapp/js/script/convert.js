$(function () {
    $("#new-rule-dialog-form").dialog({
        autoOpen: false,
        height: 430,
        width: 600,
        modal: true,
        buttons: {
            "确定": function () {
                $("#submit-new-rule-form").click();
            },
            "取消": function () {
                $(this).dialog("close");
            }
        },
        close: function () {

        }
    });

    $("#new-rule")
        .click(function () {
            $("#new-rule-dialog-form").dialog("open");
        });

    //加载初始网店数据
    newRuleForm.loadData();
    //调用change事件
    newRuleForm.changeAccount();
});

var newRuleForm = {
    /**
     * 加载源网店和目标网店账户信息
     */
    loadData: function () {
        $.getJSON("/move/move!accounts.action?src=1", function (data) {
            $.each(data, function (index, item) {
                $("#src-account-sel").append("<option value=" + item.id + ">" + item.platformLoginName + "</option>");
            });
        });
        $.getJSON("/move/move!accounts.action?src=0", function (data) {
            $.each(data, function (index, item) {
                $("#target-account-sel").append("<option value=" + item.id + ">" + item.platformLoginName + "</option>");
            });
        });
    },

    /**
     * 店铺账户change事件
     */
    changeAccount: function () {
        //源网店账户联动选择源网店商品类目
        $("#src-account-sel").change(function () {
            var value = $(this).val();
            if (value != -1) {
                newRuleForm.getCategories(value, "src");
            }
        });
        //目标网店账户联动选择目标网店商品类目
        $("#target-account-sel").change(function () {
            var value = $(this).val();
            if (value != -1) {
                newRuleForm.getCategories(value, "target");
            }
        });
    },

    /**
     * 下载商品类目
     *
     * @param accountId
     * @param selName
     */
    getCategories: function (accountId, selName) {
        $.getJSON("/move/move!categories.action", {accountId: accountId}, function (data) {
            $("#" + selName + "-category-sel").html("<option value='-1'>请选择...</option>");
            $.each(data, function (index, item) {
                $("#" + selName + "-category-sel").append("<option value=" + item.id + ">" + item.categoryName + "</option>");
            });
        });
    }
};

