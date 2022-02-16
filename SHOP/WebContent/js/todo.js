const 상태들 = document.querySelectorAll('.status');
let 진행 = 0;
let 완료 = 0;

상태들.forEach((td) => {
  if (td.textContent == 'false') {
    진행++;
    td.textContent = '진행중';
  } else {
    완료++;
    td.textContent = '완료됨';
  }
});

const data = {
  labels: ['진행중', '완료됨'],
  datasets: [
    {
      label: 'My First Dataset',
      data: [진행, 완료],
      backgroundColor: ['rgb(255, 99, 132)', 'rgb(54, 162, 235)'],
      hoverOffset: 4,
    },
  ],
};

const config = {
  type: 'pie',
  data: data,
};

const myChart = new Chart(document.getElementById('myChart'), config);
