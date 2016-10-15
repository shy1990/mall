/**
 * 继承自simpleRender
 */
$.widget('sj.blockDataView', $.sj.simpleDataView, {
	options: {
		value2Prefix: '￥'
	},
	render: function() {
		this.data = this.options.data;
		this.value1Prefix = this.options.value1Prefix;
		this.value2Prefix = this.options.value2Prefix;
		this.oldNumber = this.newNumber = this.data.number;
		this.wrap = $("<div></div>", {
			"class": 'ui-sj-block-data-view'
		}).disableSelection().appendTo(this.element);
		
		this.image = $("<img>", {
			alt: this.data.description,
			src: this.data.imgSrc
		});
		this.a=$("<a>",{href:"accessory/detail/"+this.data.specCode+".html"}).append(this.image);
		$("<div>", {
			"class": 'img'
		}).append(this.a).appendTo(this.wrap);

		this.content = $("<div>", {
			"class": 'content'
			}).append("<p> <span class=\"value1\">" + this.value1Prefix + this.data.value1 + "</span>&nbsp;&nbsp;&nbsp;<span class=\"value2 \">" + this.value2Prefix + this.data.value2 + "</span></p>")
			.append("<p class=\"description\">" + this.data.description +"</p>").append("<p>购买数量</p>")
			.appendTo(this.wrap);
/*+"(" +this.data.colorName+")</p>"*/
		this.subButton = $("<a>", {
			"class": 'button sub',
			href: 'javascript:void(0);'
		}).appendTo(this.content);
		this.input = $("<input>", {
			type: 'text',
			val: this.data.number
		}).appendTo(this.content);
		this.addButton = $("<a>", {
			"class": 'button add',
			href: 'javascript:void(0);'
		}).appendTo(this.content);
	}
});