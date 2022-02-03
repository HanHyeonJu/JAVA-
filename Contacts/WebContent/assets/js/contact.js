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
})(path);
