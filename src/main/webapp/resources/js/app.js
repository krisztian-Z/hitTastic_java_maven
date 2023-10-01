

let order_total;

/*
const categoryItems = document.querySelectorAll('.list-group');

function setActiveClass() {
    console.log(categoryItems)
  if (document.querySelector('.list-group.active') != null) {
    document.querySelector('.list-group.active').classList.remove('active')  
  } 
  this.classList.add('active');
}

categoryItems.forEach(item => {
  item.addEventListener('click', setActiveClass);
});

*/
$(document).ready(function () {
    update_cart();
    

});

function add_to_cart(pid, image, pname, pprice) {
    let cart = localStorage.getItem("cart");
    let products = [];
    if (cart === null) {
        let product = {pid: pid, image: image, name: pname, quantity: 1, price: pprice};
        products.push(product);
        localStorage.setItem("cart", JSON.stringify(products));
        show_toast(product.name + ' added to cart');
    } else {
        let pcart = JSON.parse(cart);
        let cartProduct = pcart.find((item) => item.pid === pid);
        if (cartProduct) {  // Item already in cart
            pcart.map((item) => {
                if (item.pid === cartProduct.pid) {
                    item.quantity++;
                    show_toast(item.name + ' quantity increased by 1');
                }
            });
            localStorage.setItem("cart", JSON.stringify(pcart));
        } else { // Item not in cart
            let product = {pid: pid, image: image, name: pname, quantity: 1, price: pprice};
            pcart.push(product);
            localStorage.setItem("cart", JSON.stringify(pcart));
            show_toast(product.name + ' added to cart');
        }
    }
    update_cart();
}

function remove_from_cart(id) {
    let cartString = localStorage.getItem("cart");
    let pcart = JSON.parse(cartString);
    let cartProduct = pcart.find((item) => item.pid === id);
    if (cartProduct.quantity > 1) {  // Item already in cart
        pcart.map((item) => { // reduce quantity using map
            if (item.pid === cartProduct.pid) {
                item.quantity--;
                var availableCredit =  $('.credit-amount').text();
    console.log(availableCredit)
                show_toast(item.name + ' quantity reduced by 1' + availableCredit);
                
            }
        });
        localStorage.setItem("cart", JSON.stringify(pcart));
    } else {
        show_toast('This product removed  from cart');
        let filteredCart = pcart.filter(item => item.pid !== id);
        localStorage.setItem("cart", JSON.stringify(filteredCart));
    }
    update_cart();
}

function item_price(price, quantity) {
    return price * quantity;
}


function update_cart() {
    let cartString = localStorage.getItem("cart");
    let cart = JSON.parse(cartString);
    if (cart === null || cart.length === 0) {
        $("#cart_items").empty();
        $("#scart_items").empty();
        $(".cart-body").html('<h1 class="fw-lighter text-center text-danger"> Your shopping cart is empty </h1>');
        $(".checkout").addClass('disabled not-allowed');
        $(".checkout").removeClass('btn-outline-success');
        $(".checkout").addClass('btn-outline-default');
    } else {
        let items = cart.length;
        $("#cart_items").text(items);
        $("#scart_items").text(items);
        $(".checkout").removeClass('disabled not-allowed');
        $(".checkout").removeClass('btn-outline-default');
        $(".checkout").addClass('btn-outline-success');
        $(".cart-body").html(build_cart_table(cart));
    }
    
    
    
}

function build_cart_table(cart) {
    let total = 0.00;
    table = `<table class="table "><thead><tr>`;
    table = table + `<th class="fw-normal">Product</th>`;
    table = table + `<th class="fw-normal">Name</th>`;
    table = table + `<th class="fw-normal">Price</th>`;
    table = table + `<th class="fw-normal">Quantity</th>`;
    table = table + `<th class="fw-normal">Item Total</th>`;
    table = table + `<th class="fw-normal text-center">Action</th></tr></thead>`;
    cart.map((item) => { // building cart table using map
        table = table + `<tbody class="align-middle text-sm-start fw-light">`;
        table = table + `<tr>`;
        table = table + `<td><img src="/${getImagePath()}/resources/images/products/${item.image}" class="cart-image" alt="${item.image}"></td>`;
        table = table + `<td>${item.name}</td>`;
        table = table + `<td>${item.price}</td>`;
        table = table + `<td>${item.quantity}</td>`;
        table = table + `<td>${parseFloat(item_price(item.price, item.quantity)).toFixed(2)}</td>`;
        table = table + `<td>`;
        table = table + `<div class="d-flex justify-content-between align-items-center w-100">`;
        table = table + `<button class="btn btn-sm btn-outline-primary" onclick="add_to_cart( ${item.pid}, '${item.name}', ${item.price} )"> + </button>`;
        table = table + `<button class="btn btn-sm btn-outline-danger" onclick="remove_from_cart(${item.pid})"> - </button>`;
        table = table + `</div></td></tr></tbody>`;
        total += item_price(item.price, item.quantity);
        order_total= total;
        var availableCredit =  $('#credit-amount').text();
    });
    table = table + `</table>`;
    table = table + `<div class="row  d-flex justify-content-end align-items-center ">`;
    table = table + `<div class="text-end col-sm-10 fs-5 "> Total Amount </div>`;
    table = table + `<div class="text-center col-sm-2 fs-5 ">&#163;${total}</div>`;
    table = table + `</div>`;
    return table;
}


function show_toast(message) {
    var x = document.getElementById("toast");
    console.log(x);
    x.className = "show";
    setTimeout(function () {
        x.className = x.className.replace("show", "");
    }, 5000);
    document.getElementById("desc").innerHTML = message;
}

function PlaceOrder() {
    let username = $('#username').val();
    let email = $('#email').val();
    let address = $('textarea').val();
    if (username.length === 0 || email.length === 0 || address.length === 0) {
        show_toast('Please fill all fields');
        return;
    }
    let cartString = localStorage.getItem("cart");
    $.ajax({
        method: "POST",
        url: "Order",
        data: {cart: cartString, username: username, email: email, address: address, order_total: order_total}
    })
            .done(function (msg) {
                if (msg === 'DONE') {
                    show_toast(username + ' Your order placed successfully. Thank you.');
                    setTimeout(() => {
                        localStorage.clear();
                        window.location = 'orders.jsp';
                    }, "3000")
                    update_cart();
                }
            });
}

function getImagePath() {
    let url = window.location.pathname;
    let path = url.split('/');
    return path[1];
}






