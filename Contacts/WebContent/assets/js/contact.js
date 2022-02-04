//console.log('테스트'); js가 잘 연동되는지 확인
(function (path) {
  // 코드 작성
  $('.table').DataTable({
    language: {
      lengthMenu: '표시할 줄수 선택    _MENU_',
      search: '검색',
      paginate: { previous: '이전', next: '다음' },
      info: '페이지 _PAGE_ / _PAGES_',
      infoEmpty: '데이터가 없습니다.',
      infoFiltered: '(전체 페이지 _MAX_ 에서 검색)',
      thousands: ',',
    },
    lengthMenu: [5, 10, 25], // 한 페이지 표시할 줄 수
    pageLength: 5, // 페이지의 갯수
    ordering: false, // 열의 정렬기능(삭제)
    stateSave: true,
  });
  // 추가 버튼을 클릭하면 => 모달이 표시됨
  $('.btn-add').click(function (e) {
    // 제이쿼리 변수는 $로 시작(제이쿼리로 선택한 객체)
    const $modal = $('#modal-add-update');
    // 모달 안의 title-upd를 찾음
    $modal.find('#title-add-upd').text('새 연락처');
    $modal.find('form').attr('action', path + '/contact?cmd=post');
  });

  // ajax로 요청 응답 받기
  $('#add-update').on('submit', function (e) {
    e.preventDefault(); //submit 동작 중지
    e.stopPropagation(); //이벤트 중지
    $('.btn-action').prop('disabled', true); //버튼 동작(modal창 닫기) 중지
    // 새로운 contacts 저장
    $.ajax({
      type: 'POST',
      url: $('#add-update').attr('action'),
      data: $('#add-update').serialize(), // form에 입력한 데이터를 문자열로 변환
      contentType: 'application/x-www-form-urlencoded', // 보낼 때 문자열 타입으로 보냄(생략가능)
      dataType: 'json', // serialize로 변환된 데이터를 json 타입으로 받음
    }).done(function (data) {
      if (data.status) {
        $('#modal-add-update').modal('hide'); // 모달창 종료
        location.reload(); //새로고침
      }
    });
  });
  // 테이블에서 수정 버튼 클릭 시 수정 모달창 생성(id로 그 연락처 내용을 채움)
  $('table').on('click', '.btn-edit', function (e) {
    // 제이쿼리 변수는 $로 시작(제이쿼리로 선택한 객체)
    const $modal = $('#modal-add-update');
    // 모달 안의 title-upd를 찾음
    $modal.find('#title-add-upd').text('업데이트');
    $modal.find('form').attr('action', path + '/contact?cmd=update');

    $.ajax({
      type: 'post',
      url: path + '/contact?cmd=edit',
      data: 'id=' + $(this).data('id'),
      contentType: 'application/x-www-form-urlencoded',
      dataType: 'json',
    })
      .done(function (data) {
        console.log(data);
        if (data.status) {
          $('#name').val(data.contact.name);
          $('#email').val(data.contact.email);
          $('#phone').val(data.contact.phone);
          // form을 찾아서 input창에 id가 없기 때문에 input hidden타입으로 넣어줌
          $modal.find('form').append('<input type="hidden" name="id" value="' + data.contact.id + '">');

          $modal.modal('show');
        }
      })
      .fail(function (jqxHR, textStatus) {
        console.log(textStatus);
      });
  });
  // 삭제버튼 클릭 = > 삭제 모달창 생성(id에 맞는)
  $('table').on('click', '.btn-delete', function (e) {
    $('#frm-delete').find('input[name=id]').val($(this).data('id'));
  });
  // 삭제할 때 (삭제모달의 form의 submit버튼을 클릭했을 때)
  $('#frm-delete').submit(function (e) {
    e.preventDefault();
    e.stopPropagation();
    $('.btn-action').prop('disabled', true); // ajax로 삭제하기 위해서 기존에 실행되고 있던 것들 중지시켜줌

    $.ajax({
      type: 'post',
      url: path + '/contact?cmd=delete',
      data: $('#frm-delete').serialize(), // 클릭한 객체의 id 속성값
      dataType: 'json',
    })
      .done(function (data) {
        console.log(data);
        if (data.status) {
          // 성공시
          $('#modal-delete').modal('hide'); // 모달창 닫기
          location.reload(); // 세로고침
        }
      })
      .fail(function (jqxHR, textStatus) {
        console.log(textStatus);
      });
  });
})(path);
