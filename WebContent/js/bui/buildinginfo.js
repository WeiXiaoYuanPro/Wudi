layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage','table'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		table = layui.table,
		$ = layui.jquery;

	 //一个table实例
	  table.render({
	    elem: '#demo',//渲染对象
	    height: 312,//表格高度
	    url: 'queryBuilding', //数据接口
	    where: {key: ''},//给后台传的参数
	    page: true, //开启分页
	    limit: 10,//每页显示信息条数
	    id: 'testReload',
	    cols: [[ //表头
	      {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
	      ,{field: 'name', title: '名称', width:150}
	      ,{field: 'addr', title: '经度纬度', width:150}
	      ,{field: 'remark', title: '备注', width:150} 
	      ,{field: 'school_id', title: '学校id', width: 177}
	      ,{fixed: 'right', width:200, align:'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
	    ]]
	  });
	  
	    var active = {
	            reload: function(){
	                var demoReload = $('#demoReload');

	                table.reload('testReload', {
	                    where: {
	                        key: demoReload.val()
	                    }
	                });
	            }
	        };
	//监听工具条
	table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	  var data = obj.data; //获得当前行数据
	  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	  var tr = obj.tr; //获得当前行 tr 的DOM对象
	 
	  if(layEvent === 'detail'){ //查看
	    //do somehing
		  
		  
	  } else if(layEvent === 'del'){ //删除
	    layer.confirm('真的删除行么', function(index){
//	      obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
//	      layer.close(index);
	      //向服务端发送删除指令
	    });
	  } else if(layEvent === 'edit'){ //编辑
	    //do something
		  var index = layui.layer.open({
              title : "修改信息",
              type : 2,
              content : "openBuildingEdit?id="+data.id,
              success : function(layero, index){
                  setTimeout(function(){
                      layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                          tips: 3
                      });
                  },500)
              }
          })          
          layui.layer.full(index);
	  }
	});
	
	

	$('#search').on('click', function() {
	    //这里调用初始化的方法
	    paging.init();
	});
	
})
