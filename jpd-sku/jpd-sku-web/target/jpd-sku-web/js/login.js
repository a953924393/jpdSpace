$().ready(function () {
   Login.init() ;
});
var Login = {
		init: function () {
			$("#login_btn").click(function(){
				Login.doLogin();
			});
			$("#register_btn").click(function(){
				location.href="/user/registerPage" ;
			});
		},
		doLogin:function(){
			$.ajaxSetup({
				async:false,
				cache:false
			});
			var username = $("#username").val() ;
			var password = $("#password").val() ;
			$.post("/user/login",{username:username,password:password},function(data){
				var json = eval("(" + data + ")");
				if(json.msg=="success"){
					location.href="/sku/searSkuPage";
				}else{
					$.colorbox({
	                    html: "<div style=\"text-align:center\">" + json.msg
	                            + "</div>",
	                    width: '200',
	                    height: '100'
	                });
				}
				
			});
		}
}