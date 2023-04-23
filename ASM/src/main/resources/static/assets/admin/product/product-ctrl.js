app.controller("product-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};

	$scope.initialize = function() {
		//load products
		$http.get("/rest/products").then(resp => {//lấy sp về
			$scope.items = resp.data;//bỏ vào items
			$scope.items.forEach(item => {//chuyển đổi ngày tháng
				item.createDate = new Date(item.createDate)
			})
		});
		//load category
		$http.get("/rest/categories").then(resp => {//lấy dl về
			$scope.cates = resp.data;//tạo cates trong $scope

		});
	}
	//khởi đầu
	$scope.initialize();

	//xóa form
	$scope.reset = function() {
		$scope.form = {
			createDate: new Date(),
			image: 'cloud-upload.jpg',
			available: true,
		}
	}
	//hiển thị lên form
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);//copy dl items vào form
		$(".nav-tabs a:eq(0)").tab('show')//gọi đến tab 0
	}
	//thêm sản phẩm mới
	$scope.create = function() {
		var item = angular.copy($scope.form);//copy t.tin trên form
		$http.post(`/rest/products`, item).then(resp => {//post lên địa chỉ `/rest/products`
			resp.data.createDate = new Date(resp.data.createDate)// resp trả về product
			$scope.items.push(resp.data);//bỏ vào items
			// $scope.reset();
			alert("Thêm mới sản phẩm thành công");
		}).catch(error => {
			alert("Lỗi thêm mới sản phẩm");
			console.log("Error", error);
		})
	}
	//cập nhật sản phẩm
	$scope.update = function() {
		var item = angular.copy($scope.form);//lấy dữ liệu trên form
		$http.put(`/rest/products/${item.id}`, item).then(resp => {//put dl lên form
			var index = $scope.items.findIndex(p => p.id == item.id);//sau khi nhận phản hồi -> tìm trong product theo id gửi về
			$scope.items[index] = item;//thay dl đã tìm đc vào items
			console.log($scope.items[index]);
			alert("Cập nhật sản phẩm thành công");
		}).catch(error => {
			alert("Lỗi cập nhật sản phẩm");
			console.log("Error", error);
		});
	}
	//xóa sản phẩm
	$scope.delete = function(item) {

		$http.delete(`/rest/products/${item.id}`).then(resp => {//gọi ham delete
			var index = $scope.items.findIndex(p => p.id == item.id);//nhận phản hồi từ resp và tìm sp trong items theo id 
			$scope.items.splice(index, 1);//xóa 1 sp
			$scope.reset();
			alert("Xóa sản phẩm thành công");
		}).catch(error => {
			alert("Lỗi xóa sản phẩm");
			console.log("Error", error);
		});
	}
	//upload hình
	$scope.imageChanged = function(files) {
		var data = new FormData();//tạo ra đt formdata
		data.append('file', files[0]);//lấy file ng.dung chọn bỏ vào form
		$http.post('/rest/upload/images', data, {//post nó lên server vào địa chỉ '/rest/upload/images'
			transformRequest: angular.Identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			console.log(resp);
			$scope.form.image = resp.data.name;//trả về respon và chỉ lấy name của image và thế vào thuộc tính form.image
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}

	// phân trang
	$scope.pager = {
		beginPage: 0,
		pageSize: 10,
		get items() {
			var start = this.beginPage * this.pageSize;
			return $scope.items.slice(start, start + this.pageSize);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.pageSize);
		},
		first() {
			this.beginPage = 0;
		},
		prev() {
			this.beginPage--;
			if (this.beginPage < 0) {
				this.last();
			}
		},
		next() {
			this.beginPage++;
			if (this.beginPage >= this.count) {
				this.first();
			}
		},
		last() {
			this.beginPage = this.count - 1;
		}
	}
});