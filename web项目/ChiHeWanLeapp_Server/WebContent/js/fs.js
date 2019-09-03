var $cate_menu_item = $(".fs .fs_col1 .cate_menu li");

$cate_menu_item.each(function(){
	$(this).hover(function() {
		$(this).addClass("cate_menu_item_on");
		var index = $(this).index();
		//alert(index);
		$(".cate_pop").show();
		$(".cate_part").hide();
		$(".cate_part").eq(index).show();
	}, function() {
		$(this).removeClass("cate_menu_item_on");
		$(".cate_pop").hover(function(){
			
		},function(){
			$(".cate_pop").hide();
		});
	});
});
