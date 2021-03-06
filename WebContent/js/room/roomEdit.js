var $;
layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage;
		$ = layui.jquery;
		var id=$("input[name='id']").val();
		//加载页面数据
		$.get("getRoom?id="+id, function(data){
			var d=data.m;
	        	//执行加载数据的方法
	        	$("input[name='name']").val(d.name);
	        	$("input[name='building_id']").val(d.building_id);
	        	$("input[name='capacity']").val(d.capacity);
	        	$("input[name='type']").val(d.type);
	        	$("input[name='status']").val(d.status);
	        	
	        	$.get("getClassroom", function(data){
	    			var cl=data.cl;
	    			var school_id=cl[0].school_id;
	    			for(var i=0;i<cl.length;i++){
	    				if(cl[i].id==d.building_id){
	    					$("#building_id").append("<option selected='true' value='"+cl[i].id+"'>"+cl[i].name+"</option>");
	    				}else{
	    					$("#building_id").append("<option value='"+cl[i].id+"'>"+cl[i].name+"</option>");
	    				}
	    			}
	    			form.render();//必须要再次渲染，要不然option显示不出来
	    	});
		});
		
		
		
	
	

 	form.on("submit(update)",function(data){console.log(data.field);
 		var index;
 		 $.ajax({//异步请求返回给后台
	    	  url:'updateRoom',
	    	  type:'POST',
	    	  data:data.field,
	    	  dataType:'json',
	    	  beforeSend: function(re){
	    		  index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
	          },
	    	  success:function(d){
	    			//弹出loading
			    	top.layer.close(index);
			  		top.layer.msg("操作成功！");
			   		layer.closeAll("iframe");
			  	 		//刷新父页面
			  	 	parent.location.reload();
		    		
	    	  },
	    	  error:function(XMLHttpRequest, textStatus, errorThrown){
	    		  top.layer.msg('操作失败！！！服务器有问题！！！！<br>请检测服务器是否启动？', {
	    		        time: 20000, //20s后自动关闭
	    		        btn: ['知道了']
	    		      });
	           }
	      });
 		return false;
 	})
	
})
