layui.config({
	base : "js/"
}).use(['form','element','layer','jquery','table'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		element = layui.element,
		table = layui.table,
		$ = layui.jquery;

	$(".panel a").on("click",function(){
		window.parent.addTab($(this));
	});
	
	// ==================一个table实例================================
	  table.render({
	    elem: '#demo',// 渲染对象
	    height: 315,// 表格高度
	    url: 'getTaskList', // 数据接口
	    where: {key: ''},// 给后台传的参数
	    page: true, // 开启分页
	    limit: 10,// 每页显示信息条数
	    id: 'testReload',
	    cols: [[ // 表头
	      {field: 'id', title: 'ID', sort: true, fixed: 'left'}
	      ,{field: 'title', title: '任务标题',lign:'center'}
	      ,{field: 'create_time', title: '开始时间', lign:'center'}
	      ,{field: 'deadline', title: '截止时间', lign:'center'} 
	      ,{field: 'depname', title: '执行人',align:'center' }
	      ,{field: 'status', title: '状态',align:'center',
	    	  templet: function(d){
	    		  if(d.status==1){
	    			  return '<span style="color:green;">已完成</span>';
	    		  }else{
	    			  return '<span style="color:red;">未完成</span>';
	    		  }}  
	      }
	      ,{fixed: 'right',  align:'center', toolbar: '#barDemo'} // 这里的toolbar值是模板元素的选择器
	    ]]
	  });
	

	// 动态获取文章总数和待审核文章数量,最新文章
	$.get("../json/newsList.json",
		function(data){
			var waitNews = [];
			$(".allNews span").text(data.length);  // 文章总数
			for(var i=0;i<data.length;i++){
				var newsStr = data[i];
				if(newsStr["newsStatus"] == "待审核"){
					waitNews.push(newsStr);
				}
			}
			$(".waitNews span").text(waitNews.length);  // 待审核文章
			// 加载最新文章
			var hotNewsHtml = '';
			for(var i=0;i<5;i++){
				hotNewsHtml += '<tr>'
		    	+'<td align="left">'+data[i].newsName+'</td>'
		    	+'<td>'+data[i].newsTime+'</td>'
		    	+'</tr>';
			}
			$(".hot_news").html(hotNewsHtml);
		}
	)

	// 图片总数
	$.get("../json/images.json",
		function(data){
			$(".imgAll span").text(data.length);
		}
	)

	// 用户数
	$.get("../json/usersList.json",
		function(data){
			$(".userAll span").text(data.length);
		}
	)

	// 新消息
	$.get("../json/message.json",
		function(data){
			$(".newMessage span").text(data.length);
		}
	)


	// 数字格式化
	$(".panel span").each(function(){
		$(this).html($(this).text()>9999 ? ($(this).text()/10000).toFixed(2) + "<em>万</em>" : $(this).text());	
	})

	// 系统基本参数
	if(window.sessionStorage.getItem("systemParameter")){
		var systemParameter = JSON.parse(window.sessionStorage.getItem("systemParameter"));
		fillParameter(systemParameter);
	}else{
		$.ajax({
			url : "../json/systemParameter.json",
			type : "get",
			dataType : "json",
			success : function(data){
				fillParameter(data);
			}
		})
	}

	// 填充数据方法
 	function fillParameter(data){
 		// 判断字段数据是否存在
 		function nullData(data){
 			if(data == '' || data == "undefined"){
 				return "未定义";
 			}else{
 				return data;
 			}
 		}
 		$(".version").text(nullData(data.version));      // 当前版本
		$(".author").text(nullData(data.author));        // 开发作者
		$(".homePage").text(nullData(data.homePage));    // 网站首页
		$(".server").text(nullData(data.server));        // 服务器环境
		$(".dataBase").text(nullData(data.dataBase));    // 数据库版本
		$(".maxUpload").text(nullData(data.maxUpload));    // 最大上传限制
		$(".userRights").text(nullData(data.userRights));// 当前用户权限
 	}
 	
 	
 // ///////////////////////////////
 // 基于准备好的dom，初始化echarts实例
         var myChart = echarts.init(document.getElementById('tubiao'));
         var option = {
        		    tooltip : {
        		        trigger: 'axis'
        		    },
        		    calculable : true,
        		    legend: {
        		        data:['未完成','已完成','总任务']
        		    },
        		    xAxis : [
        		        {
        		            type : 'category',
        		            splitLine : {show : false},
        		            data : ['王驰','李金鹏','李颖鹏','杨正旺','李洋','蒋霜','卢英剑','肖老师','梁老师']
        		        }
        		    ],
        		    yAxis : [
        		        {
        		            type : 'value',
        		            position: 'right'
        		        }
        		    ],
        		    series : [
        		        {
        		            name:'未完成',
        		            type:'bar',
        		            stack: '总任务',
        		            label: {
        		            	formatter: '{a}',
        		                normal: {
        		                    show: true
        		                }
        		            },
        		            data:[]
        		        },
        		        {
        		            name:'已完成',
        		            type:'bar',
        		            stack: '总任务',
        		            label: {
        		            	formatter: '{a}',
        		                normal: {
        		                    show: true
        		                }
        		            },
        		            itemStyle: {
          		            	 normal: {
          		            		 label: {
          		            			 show: true,
          		            			 position: 'top',
          		            			 formatter: '{a}\n{c}'
          		            		 }
          		            	 }
           		            },
        		            data:[]
        		        },
        		        {
        		            name:'总任务',
        		            type:'line',
        		            data:[]
        		        },

        		        {
        		            name:'工程任务',
        		            type:'pie',
        		            tooltip : {
        		                trigger: 'item',
        		                formatter: '{a} <br/>{b} : {c} ({d}%)'
        		            },
        		            center:[130,100],
        		            radius:[0, 80],
        		            itemStyle:{
        		            	normal:{
        		                    labelLine : {
        		                        length : 20
        		                    }
        		                }
        		            },
        		            data:[]
        		        }
        		    ]
        		};
         // 使用刚指定的配置项和数据显示图表。
 myChart.setOption(option);
 var names=[];    // 成员名单数组
 var totalTask=[];    // 总完成数组
// 异步加载数据
 $.get('getTaskTubiaoinfo').done(function (data) {
		 var list = data.names;
         for(var i=0;i<list.length;i++){
        	 names.push(list[i].name);    // 遍历成员并填入数组
        	 totalTask.push(list[i].totalTask);
         }
	 
     // 填入数据
     myChart.setOption({
         xAxis: {
             data: names
         },
         series: [{
             // 根据名字对应到相应的系列
             name: '未完成',
             data:data.ncList
         },
         {
             // 根据名字对应到相应的系列
             name: '已完成',
             data: data.cList
         },
         {
             // 根据名字对应到相应的系列
             name: '总任务',
             data: totalTask
         },
         {
             // 根据名字对应到相应的系列
             name: '工程任务',
             data: data.allList
         }
         ]
     });
     });
//处理点击事件并且跳转到相应的百度搜索页面
 myChart.on('click', 'series', function (params) {
	 var index = layui.layer.open({
         title : "查看信息",
         type : 2,
         area: ['1000px', '600px'],
         content : "openStuTask?stuid="+params.name,
         success : function(layero, index){
             setTimeout(function(){
                 layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                     tips: 3
                 });
             },500)
         }
     }) 
 });
 
 })


