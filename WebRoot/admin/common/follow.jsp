<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(document).ready(function() {
		$('#apDiv1').scrollFollow({
			offset : 156,
			speed : 1
		});
		//当点击跳转链接后，回到页面顶部位置 
		$("#back-to-top").click(function(){
			$('body,html').animate({scrollTop:0},1000);
			return false;
		}); 
	});
</script>


	<div id="apDiv1">
		<div class="top_01">
			<a href="collect/myCollects.html"><img src="images/shoucaxi.jpg" width="50" height="50" />
			</a> 
		</div>
		<div class="top_03">
			<div class="top_02"></div>
			<a href="cart/main.html"><img src="images/shoucaxa.jpg" width="50" height="50" />
			</a>
		</div>
		<div class="top_01">
			<a target="_blank" href="http://crm2.qq.com/page/portalpage/wpa.php?uin=4009371688&aty=0&a=0&curl=&ty=1"><img src="images/shoucaxb.jpg" width="50" height="50" />
			</a>
		</div>
		<div>
			<a id="back-to-top" href="#"><img src="images/shoucaxc.jpg" width="50" height="50" />
			</a>
		</div>
	</div>