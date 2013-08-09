var xmlHttpRequest = null;
/**
 *   delete the convertRule
 *   of selected
 */
$("#delselectconvertRule").click(function () {
    var str = "";
    $("input[type='checkbox']:checked").each(function () {
        str += $(this).val() + ",";
    });
    if (str != "") {
        if (window.confirm("您确定要删除吗？")) {
            $.post("/convert/convert!deleteConvertRule.action", {ruleId: str, signBite: 'someselect'}, function (data) {
                alert(data);
                window.location.href = "/convert/convert!list.action";
            });
        }
    } else {
        alert("You must select the convertRule you want to delete.");
    }
});
/**
 *   delete the convertRule
 *   of single
 */
function delsingle(ruleId) {
    if (window.confirm("您确定要删除吗？")) {
        var str='single';
        $.post("/convert/convert!deleteConvertRule.action", {ruleId: ruleId, signBite: str}, function (data) {
            alert(data);
            window.location.href = "/convert/convert!list.action";
        });
    }
}
/**
 * Synchronize the sub-options with the checked value of the primary
 */
$("#checkselectedconvertRule").click(function () {
    var rst = this.checked;
    $("input[name='checkbox']").each(function () {
        this.checked = rst;

    })
});
/**
 * If all the Options were selected, Select All is selected
 */
$('input[type="checkbox"][name="checkbox"]').click(function () {
    var items = true;
    $("input[type='checkbox'][name='checkbox']").each(function () {
        if (!this.checked) {
            items = false;
            return false;
        }
    })
    $("input[name='checkselectedconvertRule']").get(0).checked = items;

})

