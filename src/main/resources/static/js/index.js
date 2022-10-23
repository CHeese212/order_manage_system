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