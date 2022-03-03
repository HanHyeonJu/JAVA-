const farmChecks = document.querySelectorAll('.farmCheck');

farmChecks.forEach((td) => {
  if (td.textContent == 'false') td.textContent = 'No';
  else td.textContent = '완료';
});

(function (path) {
  // 영문 -> 한글, 페이지 설정
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
    lengthMenu: [5, 10, 25], //한페이지 표시할 줄수
    pageLength: 5, //페이지의 갯수
    ordering: false, //열의 정렬기능(삭제)
    stateSave: true,
  });
})(path);
