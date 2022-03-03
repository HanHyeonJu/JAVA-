// alert('테스트');

(function () {
  // 장바구니에 추가 버튼을 클릭
  $('#gamja').on('click', function (e) {
    e.preventDefault(); // submit 동작 중지
    console.log('눌렀음');
    $.ajax({
      type: 'GET',
      url: 'http://localhost:8090/SHOP' + '/cart?id=1',
      data: $('#gamja').serialize(),
    }).done(function (data) {
      console.log(data);
      // location.replace('cartList.jsp');
    });
  });

  $('#goguma').on('click', function (e) {
    e.preventDefault(); // submit 동작 중지
    console.log('눌렀음');
    $.ajax({
      type: 'GET',
      url: 'http://localhost:8090/SHOP' + '/cart?id=2',
      data: $('#goguma').serialize(),
    }).done(function (data) {
      console.log(data);
      // location.replace('cartList.jsp');
    });
  });
})();
