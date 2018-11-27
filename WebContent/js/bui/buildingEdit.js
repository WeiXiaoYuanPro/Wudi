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
		$.get("getbuilding?id="+id, function(data){
			var d=data.m;
			var sl=data.sl;
			var szl=data.szl;
	        	//执行加载数据的方法
	        	$("input[name='name']").val(d.name);
	        	$("input[name='addr']").val(d.addr);
	        	$("textarea[name='remark']").val(d.remark);
	        	
	        	var school_id=szl[0].school_id;
	        	//联动下拉学校分校
	        	for(var i=0;i<szl.length;i++){
	        		if(szl[i].id==d.schoolzone_id){
	        			$("#schoolzone_id").append("<option selected='true' value='"+szl[i].id+"'>"+szl[i].name+"</option>");
	        		}else{
	        			$("#schoolzone_id").append("<option value='"+szl[i].id+"'>"+szl[i].name+"</option>");
	        		}
	        		
				}
	        	//联动下拉学校
	        	for(var i=0;i<sl.length;i++){
	        		if(sl[i].id==school_id){
	        			$("#selectId").append("<option selected='true' value='"+sl[i].id+"'>"+sl[i].schoolname+"</option>");
	        		}else{
	        			$("#selectId").append("<option value='"+sl[i].id+"'>"+sl[i].schoolname+"</option>");
	        		}
	        		
				}
	        	form.render();//必须要再次渲染，要不然option显示不出来
		})

 	form.on("submit(update)",function(data){
 		var index;
 		 $.ajax({//异步请求返回给后台
	    	  url:'updateBuilding',
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
