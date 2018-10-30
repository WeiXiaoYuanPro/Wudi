$(document).ready(function(){
	$.ajax({
		data:{"data1":"11","data2":"22"},    
		url:"/hello/indexData",    
		type:"post",    
		success:function(data){    
			var list=data.infos;
			var tr="";
			$.each(list, function(i, item){
				tr +="<tr><td>"+item.no+"</td>"+"<td>"+item.name+"</td>"+"<td>"+item.cls+"</td>";
			});
			$("table").append(tr);
		}
		
	});

});