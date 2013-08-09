var xmlHttpRequest = null;

/**
 * 判断会员是否已经添加过
 * @Create: 2013-8-9 下午2:48:19
 * @Author :Robert
 */
function addMemberAjax(){

    var val=$('#memberJdId').val();
    $.post('/member/isMemberExist',{memberjdid:val},function(data){
        if(data ==true){
            document.getElementById("div1").innerHTML = '此会员已存在';
            document.getElementById("memberJdId").value='';
            document.getElementById("memberJdId").select();
        }else{
            document.getElementById("div1").innerHTML = '此会员名可用';
        }
    },'json')
}