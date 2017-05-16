/*!
 * @project : yo_mall
 * @version : 2.0.0
 * @author  : zhl(zhanhailin@yy.com)
 * @update	: 2016年3月1日
 * @decription: 一些插件
 */
(function($){


/*!
 * @project : yo_mall
 * @version : 2.0.0
 * @author  : zhl(zhanhailin@yy.com)
 * @update	: 2016年3月1日
 * @decription: product插件
 */
$(function(){
	var productTip = function($el,options){
		this.init($el,options);
		console.info(JSON.stringify(options));
		return $el;
	};
	$.extend(productTip.prototype,{
		init : function($el,options){
			this.$el = $el.addClass("yo-plugin-product");
			this._options = options;
			this.$el.popover({
				content : (function(){
					
					/*return '<div class="ui-bigItem yo-plugin-product-info">'
								+'<div class="">'
									+'<span class="editableform-loading"></span>'
								+'</div>'
						   '</div>';*/
					
					return '<div class="ui-bigItem yo-plugin-product-info">                                                                                                                               '+
						'     <div class="ui-bigItem__link" href="#" target="_blank">                                                                     '+
						'         <img src="http://assets.dwstatic.com/project/yo-mall/2.0/img/placeholder-220-220.jpg" data-original="${spu.picUrl}" alt="${spu.productName}" class="ui-bigItem__pic"> '+
						'         <div class="ui-bigItem__info">                                                                                                                  '+
						'             <p class="ui-price">&yen; -</p>   '+
						'             <a href="sdsd" class="u-txtGray">加载中<span class="editableform-loading"></span></a>                                                                                                 '+
						'         </div>                                                                                                                                          '+
						'     </div>                                                                                                                                                '+
						' </div>                                                                                                                                                  ';
					
				})(),
				html : true,
				trigger : "hover",
				placement : "auto",
				delay: { "show": 0, "hide": 100 }
			}).on("hide.bs.popover",function(){
				var $this = $(this);
				if($this.is(".tips-active")){
					return false;
				}
				var describedby = $this.attr("aria-describedby");
				if(describedby){//移动到了弹出框本身
					var $describedby = $("#"+describedby);
					if($describedby.is(":hover")){
						$describedby.one("mouseleave",function(){
							console.info("out");
							$this.popover("hide");
						});
						return false;
					}
				}
				
			}).click(function(){
				var $span = $(this);
				if(!$span.is(".tips-active")){
					$span.addClass("tips-active");
					$("body").bind("click.product",function(e){
						if($(e.target).closest(".popover").length){//点击的是弹出框本身
							return;
						}
						$(".yo-plugin-product.tips-active").removeClass("tips-active").popover("hide");
						$("body").unbind("click.product");
					});
				}else{
					$span.removeClass("tips-active");
				}
				//$span.is(".tips-active")?$span.removeClass("tips-active"):$span.addClass("tips-active");
				//$span.toggleClass("tips-active");
				return false;
			});
		}
	});
	
	$.fn.extend({
		"yo.productTip" : function(options){
			$(this).each(function(){
				var $el = $(this);
				var tip = new productTip($el,options);
				$el.data("productTip",tip);
			});
		}
	});
});

	

})($);