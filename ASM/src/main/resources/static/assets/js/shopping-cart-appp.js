const app = angular.module("shopping-cart-app", []);
app.controller("shopping-cart-ctrl", function ($scope, $http) {

    // QUẢN LÝ GIỎ HÀNG
    $scope.cart = {
        items: [],
        //thêm sản thẩm vào giỏ hàng
        add(id) {
            var item = this.items.find(item => item.id == id);//tìm kiếm sản phẩm trong giỏ hàng
            if (item) {//nếu có tăng số lượng
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp => { //tải sản phẩm trên server
                    resp.data.qty = 1; //sl=1
                    this.items.push(resp.data);//thêm vào ds mặt hàng đã chọn
                    this.saveToLocalStorage();
                })
            }
        },
        //xóa sản phẩm khỏi giỏ hàng
        remove(id) {
            var index = this.items.findIndex(item => item.id == id);//tìm vị trí của sp trong giỏ hàng
            this.items.splice(index, 1);//splice để xóa
            this.saveToLocalStorage();
        },
        //xóa sạch các mặt hàng có trong giỏ hàng
        clear() {
            this.items = []
            this.saveToLocalStorage();
        },
        //tính thành tiền của 1 sản phẩm
        amt_of(item) { },
        //tính tổng số lượng các mặt hàng trong giỏ
        get count() {
            return this.items //duyệt qua các mặt hàng
                .map(item => item.qty)//lấy quantity
                .reduce((total, qty) => total += qty, 0);
        },
        //tính tổng các mặt hàng trong giỏ
        get amount() {
            return this.items//duyệt qua các mặt hàng
                .map(item => item.qty * item.price)//lấy quantity
                .reduce((total, qty) => total += qty, 0);
        },
        //lưu giỏ hàng vào local strorage
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));//dùng anguar để copy và đổi dang json
            localStorage.setItem("cart", json);//lưu vào local với tên là cart
        },
        //Đọc giỏ hàng từ local storage
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");//đọc từ local 
            this.items = json ? JSON.parse(json) : [];//nếu có thì gán items nếu k thì để mảng rỗng
            console.log("adadadada");
        }
    }
    $scope.cart.loadFromLocalStorage();
    $scope.order = {
        createDate: new Date(),
        address: "",
        account: { username: $("#username").text() },
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: { id: item.id },
                    price: item.price,
                    quantity: item.qty
                }
            });
        },
        purchase() {
            var order = angular.copy(this);
            $http.post("/rest/orders", order).then(resp => {
                alert("Đặt hàng thành công!");
                $scope.cart.clear();
                location.href = "/order/detail/" + resp.data.id;
            }).catch(error => {
                alert("Đặt hàng lỗi!")
                console.log(error)
            })
        }
    }
    $(function () {
        $(window).bind('storage', function (e) {
            if (localStorage.getItem("cart") == "0") {
                $("#btnCheckOut").hide();
            } else if (localStorage.getItem("cart") == "1") {
                $("#btnCheckOut").show();
            }
        });
    });
})