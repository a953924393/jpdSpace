$().ready(function () {
   Reg.init() ;
});
var userflag = true ;
var passwordflag = true ;
var telephoneflag = true ;
var Reg = {
		init:function(){
			$("#userEmail").blur(function(){
				Reg.isUserEmailExist();
			});
			$("#confirmPassword").blur(function(){
				Reg.confirmPassword() ;
			});
			$("#userTelephone").blur(function(){
				Reg.validataTelephone();
			});
			$("#register_btn").click(function(){
				Reg.isUserEmailExist();
				Reg.confirmPassword() ;
				Reg.validataTelephone();
				Reg.doReg();
			});
		},
		isUserEmailExist:function(){
			$.ajaxSetup({
				async:false,
				cache:false
			});
			var userEmail = $("#userEmail").val();
			if(!Reg.isEmalFormat(userEmail)){
				$("#emailmsg").html("<p style=\"color:red\">邮箱格式不正确，请重新输入！</p>");
				userflag = false ;
				return;
			}
			$.post("/isUserEmailExist",{userEmail:userEmail},function(data){
				var json = eval("(" + data + ")");
				if(json.msg){
					$("#emailmsg").html("<p style=\"color:red\">该邮箱已被注册，可以使用邮箱登录！</p>");
					userflag = false ;
				}else{
					$("#emailmsg").html("<p style=\"color:green\">该邮箱可以使用！</p>");
					userflag = true ;
				}
			});
		},
		isEmalFormat:function(email){
			var pattern=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/; 
	        if(email!="" && !(pattern.test(email))){
	            return false ;
	        }else if(email==''){
	        	return false ;
	        }else{
	        	return true ;
	        }
		},
		confirmPassword:function(){
			var password = $("#password").val();
			var confirmPassword = $("#confirmPassword").val();
			if(password==''){
				$("#passwordmsg").html("<p style=\"color:red\">密码不能为空！</p>");
				passwordflag = false;
				return;
			}
			if(confirmPassword==''){
				$("#passwordmsg").html("<p style=\"color:red\">确认密码不能为空！</p>");
				passwordflag = false;
				return ;
			}
			if(password!=confirmPassword){
				$("#passwordmsg").html("<p style=\"color:red\">两次输入的密码不一致！</p>");
				passwordflag = false;
			}else{
				$("#passwordmsg").html("");
				passwordflag = true;
			}
		},
		validataTelephone:function(){
			var telephone = $("#userTelephone").val();
			if(telephone==''){
				$("#telephonemsg").html("<p style=\"color:red\">请输入手机号码！</p>");
				telephoneflag = false;
				return;
			}
			var tex = /^1[3458]\d{9}$/;
			var length = telephone.length;
			if(!(length == 11 && (tex.test(telephone)))){
				$("#telephonemsg").html("<p style=\"color:red\">手机号码只能由11位数字组成！</p>");
				telephoneflag = false;
			}else{
				telephoneflag = true ;
				$("#telephonemsg").html("");
			}
		},
		doReg:function(){
			$.ajaxSetup({
				async:false,
				cache:false
			});
			var userEmail = $("#userEmail").val();
			var password = $("#password").val();
			var userTelephone = $("#userTelephone").val();
			var userQq = $("#userQq").val();
			if(userflag && passwordflag && telephoneflag){
				$.post("/register",{userEmail:userEmail,password:password,userTelephone:userTelephone,userQq:userQq},function(data){
					var json = eval("(" + data + ")");
					if(json.msg){
						location.href="/index.jsp" ;
					}else{
						$.colorbox({
		                    html: "<div style=\"text-align:center\">注册失败！</div>",
		                    width: '200',
		                    height: '100'
		                });
					}
				});
			}
		}
}