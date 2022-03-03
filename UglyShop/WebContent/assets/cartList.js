// alert('test');

(function () {
  //수량 변경 (up버튼을 누름)
  $('.up').on('click', function (e) {
    let param = $(this).attr('data-id');
    $.ajax({
      type: 'GET',
      url: 'http://localhost:8090/SHOP' + '/cart?id=' + param,
      data: $('.up').serialize(),
    }).done(function (data) {
      console.log(data);
      location.reload('cartList.jsp');
    });
  });

  // 수량 변경 (down 버튼을 누름)
  $('.down').on('click', function (e) {
    let param = $(this).attr('data-id');
    $.ajax({
      type: 'POST',
      url: 'http://localhost:8090/SHOP' + '/cart?id=' + param,
      data: $('.down').serialize(),
    }).done(function (data) {
      console.log(data);
      location.reload('cartList.jsp');
    });
  });

  // 삭제
  $('#delete').on('click', function (e) {
    let param = $(this).attr('data-id');
    $.ajax({
      type: 'POST',
      url: 'http://localhost:8090/SHOP' + '/cart?cmd=delete&id=' + param,
      data: {},
    }).done(function (data) {
      console.log(data);
      location.reload('cartList.jsp');
    });
  });
})();
