function delOrder(orderId) {
    if (confirm("是否确认删除?")) {
        window.location.href = '/delete?id=' + orderId;
    }
}

function linkAddOrder() {
    window.location.href = '/add';
}

function page(pageNo) {
    window.location.href = "/searchByName?pageNum=" + pageNo;
}

function returnIndex() {
    window.location.href = "/";
}

function linkGitHub() {
    window.location.href = "https://github.com/July-66/order_manage_system";
}

function isNumber() {
    var id = document.forms["search_by_id"]["id"].value;
    var patt = /^[0-9]*[1-9][0-9]*$/;
    var res = patt.test(id);
    if (res === false) {
        document.forms["search_by_id"]["id"].value = null;
        alert("请输入正整数！");
    }
    return res;
}