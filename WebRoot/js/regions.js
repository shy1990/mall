

/**
 * 根据省份id获取市列表<br>
 * 参数说明 : <br>proId为省份代码<br>cityId城市下拉框的ID<br>areaId地区下拉框的ID
 * @author 周张豹
 */
function _getCitys(proId,cityId,areaId) {
	$.ajax( {
		url : 'regions/getCitys.html',
		type : 'POST',
		data : 'province=' + proId,
		dataType : "JSON",
		error : function() {
			alert('请求失败');
			return false;
		},
		success : function(citylist) {
			document.getElementById(cityId).innerHTML = "";
			document.getElementById(areaId).innerHTML = "";
			$("#"+areaId).append("<option value='0'>-选择区-</option>");
			if (citylist.length > 0) {
				$("#"+cityId).append("<option value='0'>-选择市-</option>");				
				for ( var i = 0; i < citylist.length; i++) {
					$("#"+cityId).append(
							"<option value='" + citylist[i].id + "'>"
									+ citylist[i].name + "</option>");
				}
				$("#"+cityId).show();
			} else {
				$("#"+cityId).hide();
			}
		}
	});

}
/**
 * 根据市id获取区列表<br>
 * 参数说明 : <br>cityId为所选城市的ID<br>areaId地区下拉框的ID
 * @author 周张豹
 */
function _getAreas(cityId,areaId) {
	$.ajax( {
		url : 'regions/getAreas.html',
		type : 'POST',
		data : 'city=' + cityId,
		dataType : 'JSON',
		error : function() {
			alert('请求失败');
			return false;
		},
		success : function(areaList) {
			document.getElementById(areaId).innerHTML = "";
			if (areaList.length > 0) {
				$("#"+areaId).append("<option value='0'>-选择区-</option>");
				for ( var i = 0; i < areaList.length; i++) {
					$("#"+areaId).append(
							"<option value='" + areaList[i].id + "'>"
									+ areaList[i].name + "</option>");
				}
				$("#"+areaId).show();
			} else {
				$("#"+areaId).hide();
			} 
		}
	});
}