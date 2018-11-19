layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//加载页面数据
	var newsData = '';
	$.get("queryCmslogin_Log", function(d){
		var data=d.infos.list;
        	//执行加载数据的方法
        	newsList(data);
	})

	//查询
	$(".search_btn").click(function(){
		if($(".search_input").val() != ''){
			var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            setTimeout(function(){
            	$.ajax({
					url : "queryCmslogin_Log",
					type:'POST',
			    	data:{"key":$(".search_input").val()},
			    	dataType:'json',
					success : function(d){
						var data=d.infos.list;
			        	//执行加载数据的方法
			        	newsList(data);
					}
				})
            	
                layer.close(index);
            },2000);
		}else{
			layer.msg("请输入需要查询的内容");
		}
	})

	
	//批量删除
	$(".batchDel").click(function(){
		var $checkbox = $('.news_list tbody input[type="checkbox"][name="checked"]');
		var $checked = $('.news_list tbody input[type="checkbox"][name="checked"]:checked');
		if($checkbox.is(":checked")){
			layer.confirm('确定删除选中的信息？',{icon:3, title:'提示信息'},function(index){
				var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
	            setTimeout(function(){
	            	//删除数据
	            	for(var j=0;j<$checked.length;j++){
	            		for(var i=0;i<newsData.length;i++){
							if(newsData[i].newsId == $checked.eq(j).parents("tr").find(".news_del").attr("data-id")){
								newsData.splice(i,1);
								newsList(newsData);
							}
						}
	            	}
	            	$('.news_list thead input[type="checkbox"]').prop("checked",false);
	            	form.render();
	                layer.close(index);
					layer.msg("删除成功");
	            },2000);
	        })
		}else{
			layer.msg("请选择需要删除的文章");
		}
	})

	//全选
	form.on('checkbox(allChoose)', function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		child.each(function(index, item){
			item.checked = data.elem.checked;
		});
		form.render('checkbox');
	});

	//通过判断文章是否全部选中来确定全选按钮是否选中
	form.on("checkbox(choose)",function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		var childChecked = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"]):checked')
		if(childChecked.length == child.length){
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = true;
		}else{
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = false;
		}
		form.render('checkbox');
	})

		
	
	$("body").on("click",".news_del",function(){  //删除
		var _this = $(this);
		layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
			var msgid;
	 		 $.ajax({//异步请求返回给后台
		    	  url:'delCms_User',
		    	  type:'POST',
		    	  data:{"id":_this.attr("data-id")},
		    	  dataType:'json',
		    	  beforeSend: function(re){
		    		  msgid = top.layer.msg('数据处理中，请稍候',{icon: 16,time:false,shade:0.8});
		          },
		    	  success:function(d){
		    		  top.layer.close(msgid);
		    		  if(d.result){
		    			//弹出loading
					   		layer.closeAll("iframe");
					  	 //刷新父页面
					  	 	parent.location.reload();
		    		  }else{
		    			  top.layer.msg("操作失败！，数据库操作有问题！！");
		    		  }
			    		
		    	  },
		    	  error:function(XMLHttpRequest, textStatus, errorThrown){
		    		  top.layer.msg('操作失败！！！服务器有问题！！！！<br>请检测服务器是否启动？', {
		    		        time: 20000, //20s后自动关闭
		    		        btn: ['知道了']
		    		      });
		           }
		      });
	 		 
	 		layer.close(index);
			
		});
	})

	function newsList(that){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			if(!that){
				currData = newsData.concat().splice(curr*nums-nums, nums);
			}else{
				currData = that.concat().splice(curr*nums-nums, nums);
			}
			if(currData.length != 0){
				for(var i=0;i<currData.length;i++){
					dataHtml += '<tr>'
			    	+'<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
			    	+'<td>'+currData[i].id+'</td>'
			    	+'<td>'+currData[i].username+'</td>'
			    	+'<td>'+currData[i].login_time+'</td>'
			    	+'<td>'+currData[i].ip+'</td>'
			    	+'<td>'+currData[i].addr+'</td>';
					+'<td>'+currData[i].remark+'</td>';
			    	if(currData[i].status == 1){
			    		dataHtml += '<td style="color:#f00">失败</td>';
			    	}else{
			    		dataHtml += '<td>成功</td>';
			    	}			    	
			    	dataHtml +='<td>'
					+  '<a class="layui-btn layui-btn-mini news_edit" data-id="'+currData[i].id+'"><i class="iconfont icon-edit" ></i> 编辑</a>'
					+  '<a class="layui-btn layui-btn-danger layui-btn-mini news_del" data-id="'+currData[i].id+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
			        +'</td>'
			    	+'</tr>';
				}
			}else{
				dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
			}
		    return dataHtml;
		}

		//分页
		var nums =5; //每页出现的数据量
		if(that){
			newsData = that;
		}
		laypage({
			cont : "page",
			pages : Math.ceil(newsData.length/nums),
			jump : function(obj){
				$(".news_content").html(renderDate(newsData,obj.curr));
				$('.news_list thead input[type="checkbox"]').prop("checked",false);
		    	form.render();
			}
		})
	}
})
