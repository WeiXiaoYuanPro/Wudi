var $;
layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage;
		$ = layui.jquery;
	//加载页面选择框数据
	$.get("getModeByFId", function(data){
			var ml=data.ml;
			for(var i=0;i<ml.length;i++){
        		$("#selectId").append("<option value='"+ml[i].id+"'>"+ml[i].title+"</option>");
			}
			form.render();//必须要再次渲染，要不然option显示不出来
	});
	//===========================================
		
 	form.on("submit(add)",function(data){
 		var index;
 		 $.ajax({//异步请求返回给后台
	    	  url:'saveNavs',
	    	  type:'POST',
	    	  data:data.field,
	    	  dataType:'json',
	    	  beforeSend: function(re){
	    		  index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
	          },
	    	  success:function(d){
	    			//弹出loading
			    	top.layer.close(index);
			  		top.layer.msg("添加成功！");
			   		layer.closeAll("iframe");
			  	 		//刷新父页面
			  	 	parent.location.reload();
		    		
	    	  },
	    	  error:function(XMLHttpRequest, textStatus, errorThrown){
	    		  top.layer.msg('保存失败！！！服务器有问题！！！！<br>请检测服务器是否启动？', {
	    		        time: 20000, //20s后自动关闭
	    		        btn: ['知道了']
	    		      });
	           }
	      });
 		return false;
 	})
	
})