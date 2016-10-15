$.widget('sj.simpleDataView', {
	options: {
		value1Prefix: '￥',
		value3Prefix:"￥",
		minNumber: 0,
		maxNumber: Number.MAX_VALUE,
		data: {
			id: 0,
			specCode:'',
			type: '',
			goodsName: '',
			name: '',
			specification: '',
			imgSrc: '',
			value1: 0.00,
			value2: 0.00,
			value3: 0.00,
			tmallUrl: '',
			description: '',
			number: 0
		},
		numberChange: null,
		beforeNumberChange:function(data){return true;}
	},
	_create: function() {
		this.data = this.options.data;
		this.value1Prefix = this.options.value1Prefix;
		this.oldNumber = this.newNumber = this.data.number;
		this.render();
		this._boundUI();
	},
	render:function(){
		this.wrap = $("<table>", {
			"class": 'ui-sj-simple-data-view'
		}).disableSelection().appendTo(this.element);
		this.row = $("<tr>").appendTo(this.wrap);
		this.row.append($("<td>", {
			width: '92px',
			text: this.data.type
		}));
		this.row.append($("<td>", {
			width: '93px',
			text: this.data.goodsName
		}));
		this.row.append($("<td>", {
			width: '149px',
			text: this.data.name
		}));
		this.row.append($("<td>", {
			width: '199px',
			text: this.data.specification
		}));
		this.row.append($("<td>", {
			width: '109px',
			"class": 'value1',
			text: this.options.value1Prefix + this.data.value1
		}));
		
		var tempRow = $("<td>", {
			width: '192px'
		}).appendTo(this.row);
		
		this.subButton = $("<a>", {
			"class": 'button sub',
			href: 'javascript:void(0);'
		}).appendTo(tempRow);
		
		this.input = $("<input>", {
			type: 'text',
			val: this.data.number
		}).appendTo(tempRow);
		this.addButton = $("<a>", {
			"class": 'button add',
			href: 'javascript:void(0);'
		}).appendTo(tempRow);
		
		this.row.append($("<td>", {
			width: '87px',
			"class": 'value3',
			html: "<a href=\"" + this.data.tmallUrl + "\" target=\"_blank\">" + this.options.value3Prefix + this.data.value3 + "</a>"
		}));
	},
	_boundUI: function() {
		this._on(this.input, {
			keydown: function(event) {
				this.oldNumber = $(event.target).val();
			},
			keyup: function(event) {
				this.newNumber = $(event.target).val().replace(/\D|^0/g, '');
				$(event.target).val(this.newNumber || 0);
				if(!this.options.beforeNumberChange(this.data)){
					return;
				}
				if (this.newNumber != this.oldNumber)
					this._update();
			}
		});
		this._on(this.addButton, {
			click: function(event) {
				var nextNumber = parseInt(this.data.number) + 1;
				if (nextNumber < this.options.maxNumber) {
					if(!this.options.beforeNumberChange(this.data,"add")){
						return;
					}
					this.input.val(nextNumber);
					this._update();
				}

			}
		});
		this._on(this.subButton, {
			click: function(event) {
				var nextNumber = parseInt(this.data.number) - 1;
				if (nextNumber >= this.options.minNumber) {
					if(!this.options.beforeNumberChange(this.data,"sub")){
						return;
					}
					this.input.val(nextNumber);
					this._update();
				}
			}
		});
	},
	setNumber:function(number){
		this.oldNumber=this.newNumber;
		this.newNumber=number.replace(/\D|^0/g, '');
		if(!this.options.beforeNumberChange(this.data)){
			return;
		}
		this.input.val(this.newNumber);
		this._updata();
	},
	_update: function() {
		var number = this.input.val();
		this._setOption("data", $.extend(this.data, {
			number: number
		}));
		this._trigger("numberChange", null, this.options.data);
		this.input.trigger('change');
	},
	_destroy: function() {
		this.wrap.remove();
	}
});