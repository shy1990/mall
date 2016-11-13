$.widget('sj.dataViewList', {
	options: {
		viewType: 'simple', //block
		datas: [] //{}json
	},
	_create: function() {
		this.wrap = $("<ul>", {
			"class": 'ui-sj-data-view-list'
		}).appendTo(this.element);
		this.items = this.options.datas;
		this.UINodes = [];
		this.refresh();
	},
	appendDatas: function(datas) {
		var self=this;
		$.each(datas, function(i, data) {
			var index = self._getItemIndex(data);
			if (index >= 0) {
				self._updateItem(data);
			} else {
				self.items.push(data);
			}
		});

		this.refresh();
	},
	_appendData: function(data) {
		var self = this;
		var dv;
		if (this.options.viewType === 'block') {
			dv = $("<li>").blockDataView({
				value1Prefix:this.options.value1Prefix,
				value2Prefix:this.options.value2Prefix,
				data: data,
				numberChange: function() {
					var data = $(this).data('sjBlockDataView').data;
					self._updateItem(data);
					self._trigger('itemChange', null, dv);
				},
				beforeNumberChange:self.options.beforeNumberChange
			}).appendTo(this.wrap).blockDataView('instance');
		} else if (this.options.viewType === 'simple') {
			dv = $("<li>").simpleDataView({
				value1Prefix:this.options.value1Prefix,
				value2Prefix:this.options.value2Prefix,
				value3Prefix:this.options.value3Prefix,
				data: data,
				numberChange: function() {
					var data = $(this).data('sjSimpleDataView').data;
					self._updateItem(data);
					self._trigger('itemChange', null, dv);
				},
				beforeNumberChange:self.options.beforeNumberChange
			}).appendTo(this.wrap).simpleDataView('instance');
		}
		this.UINodes.push(dv);
	},
	_getItemIndex: function(item) {
		var index = -1;
		for (var i = 0; i < this.items.length; i++) {
			if (this.items[i].id == item.id) {
				index = i;
				break;
			}
		}
		return index;
	},
	_updateItem: function(item) {
		var index = this._getItemIndex(item);
		this.items.splice(index, 1, item);
	},
	remove: function(item) {
		var index = this._getItemIndex(item);
		this.items.splice(index, 1);
		var node=this.UINodes[index];
		if(node){
			node.element.remove();
			this.UINodes.splice(index, 1);
		}
		
	},
	clean: function() {
		this.items = []; //清空数组
		this._cleanUI();
	},
	_cleanUI: function() {
		$.each(this.UINodes, function(i, node) {
			node.element.remove();
		});
		this.UINodes = [];
	},
	refresh: function() {
		var self = this;
		this._cleanUI();
		$.each(this.items, function(i, item) {
			self._appendData(item);
		});
	},

	_destory: function() {
		this.wrap.remove();
	}
});