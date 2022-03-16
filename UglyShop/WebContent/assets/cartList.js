// alert('test');

(function () {
  //수량 변경 (up버튼을 누름)
  $('.up').on('click', function (e) {
    let id = $(this).attr('data-id');
    $.ajax({
      type: 'GET',
      url: 'http://localhost:8091/UglyShop' + '/cart?id=' + id,
      data: $('.up').serialize(),
    }).done(function (data) {
      console.log(data);
      location.reload('cartList.jsp');
    });
  });

  // 수량 변경 (down 버튼을 누름)
  $('.down').on('click', function (e) {
    let id = $(this).attr('data-id');
    $.ajax({
      type: 'POST',
      url: 'http://localhost:8091/UglyShop' + '/cart?id=' + id,
      data: $('.down').serialize(),
    }).done(function (data) {
      console.log(data);
      location.reload('cartList.jsp');
    });
  });

  // 삭제
  $('#delete').on('click', function (e) {
    let id = $(this).attr('data-id');
    $.ajax({
      type: 'POST',
      url: 'http://localhost:8091/UglyShop' + '/cart?cmd=delete&id=' + id,
      data: {},
    }).done(function (data) {
      console.log(data);
      location.reload('cartList.jsp');
    });
  });
})();
