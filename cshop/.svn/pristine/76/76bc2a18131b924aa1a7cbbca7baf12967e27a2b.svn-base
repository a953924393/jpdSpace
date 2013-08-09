var moveItems = {

    /**
     * 初始化各个动作事件
     */
    initEvent: function () {

        /**
         * 全选checkbox
         */
        $('input[type="checkbox"][name="checkAllItems"]').unbind("click");
        $('input[type="checkbox"][name="checkAllItems"]').bind("click",function() {

            $('input[type="checkbox"][name="itemId"]').prop("checked", this.checked);
        })
        /**
         * 个数选取
         */
        $('input[type="checkbox"][name="itemId"]').unbind("click");
        $('input[type="checkbox"][name="itemId"]').bind("click",function () {
            var checkAllItems = true;
            $('input[type="checkbox"][name="itemId"]').each(function () {
                if (!this.checked) {
                    checkAllItems = false;
                    return false;
                }
            });
            $('input[type="checkbox"][name="checkAllItems"]').prop("checked", checkAllItems);
        });
    },
    /**
     * 得到选取的Item的id
     */

    getItemIds: function () {
        var itemIds = "";
        $("input[type='checkbox'][name='itemId']").each(function () {

            if (this.checked) {
                itemIds = itemIds + "," + $(this).attr("value");
            }

        });

        if (itemIds.length > 0) {
            itemIds = itemIds.substr(1, itemIds.length - 1);
        }


        return itemIds;
    }
};
$(function () {
    moveItems.initEvent();
    $("#to-move").click(function () {
        var itemIds = moveItems.getItemIds();
        if(itemIds == null || itemIds == "") {
            $("#moveCheckMsg").text("请选择商品");
            return false;
        }
        $("#moving-items").val(itemIds);
        $("#moving-items-form").submit();
    });
});
