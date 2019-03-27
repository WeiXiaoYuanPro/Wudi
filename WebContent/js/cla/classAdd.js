var $;
layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage;
		$ = layui.jquery;
	//===========================================
		//班主任的下拉框
		$.get("getHeadmasters",
				function(data){
					var list=data.d;
					var arr=new Array();
					for(var j=0;j<list.length;j++){
						arr.push("<option value='"+list[j].id+"'>"+list[j].username+"</option>")
					}
					var select = arr.join('')
					$("#user_no").append(select);
					form.render();//必须要再次渲染，要不然option显示不出来
				}
			);
		//学校的下拉框
		$.get("getDepartments",
				function(data){
					var list=data.d;
					var arr=new Array();
					for(var j=0;j<list.length;j++){
						arr.push("<option value='"+list[j].id+"'>"+list[j].name+"</option>")
					}
					var select = arr.join('')
					$("#dep_no").append(select);
					form.render();//必须要再次渲染，要不然option显示不出来
				}
			);
		
		
		
		
        	
 	form.on("submit(add)",function(data){
 		var index;
 		 $.ajax({//异步请求返回给后台
	    	  url:'saveMajor',
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