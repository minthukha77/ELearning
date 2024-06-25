// pure chart
let trueArr = JSON.parse(document.querySelector('[data-trueArr]').getAttribute('data-trueArr'));
let chart = document.querySelector('.chart');
let labelBox = document.querySelector('.label-box');
chart.style.width = `${40*trueArr.length}px`;
labelBox.style.width = `${40*trueArr.length}px`;

trueArr.forEach((val,i)=>{
    chart.innerHTML += `<div class="chart-data" id="${i+1}">
        <div class="false" title="False : ${100-val}%"  style='height:${100-val}%'></div>
        <div class="true"  title="True : ${val}%" style='height:${val}%'></div>
    </div>`;
    labelBox.innerHTML += `<div class="label text-center" id="Q${i+1}" style="width: 27px;">Q${i+1}</div>`
})


// filter 
$( "select" ).change(function() {
  var selectedEventType = this.options[this.selectedIndex].value;
  if (selectedEventType == "All") {
      $('.card').removeClass('d-none');
  } else {
      $('.card').addClass('d-none');
      $('.card[data-custom-type="' + selectedEventType + '"]').removeClass('d-none');
  }
});



// let input = document.querySelector('#result');

// function filterOut () {
//   let inputValue = document.querySelector('#result').value;
//   let cards = document.querySelectorAll('.card');
//   console.log(inputValue);
//   console.log(cards);

//   let resut = cards.filterOut(card => {
//     let status = card.querySelector('.card-body').getAttribute('data-status');
//     if(status != inputValue){
//       card.classList.add('d-none')
//     }
//   })

  // cards.forEach((card,i)=>{
  //   let status = card.querySelector('.card-body').getAttribute('data-status');
  //   console.log(status.indexOf(inputValue))
  // })
// }

// input.addEventListener('change',() => {
//   filterOut()
// })



// // for chart 
// let quesArr =[70,100,20,38,55,97,88,10,67,76,70,100,20,38,55,97,88,10,67,76,70,100,20,38,55,97,88,10,67,76];
// let falArr = [];

// for (var i =0 ; i < quesArr.length ; i++) {
//     let y = quesArr[i];
//     let z = 100 -y;
//     falArr.push(z)
// }

// // setup

// const data = {
//     labels: ['Q1', 'Q2', 'Q3', 'Q4', 'Q5', 'Q6','Q7', 'Q8', 'Q9', 'Q10','Q11', 'Q12', 'Q13', 'Q14', 'Q15', 'Q16','Q17', 'Q18', 'Q19', 'Q20','Q21', 'Q22', 'Q23', 'Q24', 'Q25', 'Q26','Q27', 'Q28', 'Q29', 'Q30'],
//     datasets: [{
//       label: 'correct answer',
//       data: quesArr,
//       backgroundColor: [
//         'rgba(48, 153, 134, 1)'
//       ],
//       borderColor: [
//         'rgba(48, 153, 134, 1)'
//       ],
//       borderWidth: 1
//     }]
//   };

// // config 
// const config = {
//     type: 'bar',
//     data: data,
//     options: {
//       responsive: true,
//       plugins: {
//         legend: {
//           position: 'top',
//         },
//         title: {
//           display: true,
//           text: 'Chart Title'
//         }
//       }
//     },
//   };

// const ctx = document.getElementById('myChart');
// const myChart = new Chart(document.getElementById('myChart'), config);

// window.onload = function () {
//   var chart = new CanvasJS.Chart("chartContainer",
//   {
//     title:{
//     text: "Number of Students in Each Room"
//     },

//     data: [
//     {
//       type: "stackedColumn100",
//       dataPoints: [
//       {  y: 40, label: "Q1"},
//       {  y: 10, label: "Q2"},
//       {  y: 72, label: "Q3"},
//       {  y: 30, label: "Q4"},
//       {  y: 21, label: "Q5"},
//       {  y: 40, label: "Q6"},
//       {  y: 10, label: "Q7"},
//       {  y: 72, label: "Q8"},
//       {  y: 30, label: "Q9"},
//       {  y: 21, label: "Q10"}
//       ]
//     }, {
//       type: "stackedColumn100",
//       dataPoints: [
//       {  y: 20, label: "Q1"},
//       {  y: 14, label: "Q2"},
//       {  y: 40, label: "Q3"},
//       {  y: 43, label: "Q4" },
//       {  y: 17, label: "Q5"},
//       {  y: 40, label: "Q6"},
//       {  y: 10, label: "Q7"},
//       {  y: 72, label: "Q8"},
//       {  y: 30, label: "Q9"},
//       {  y: 21, label: "Q10"}
//       ]
//     }

//     ]
//   });

//   chart.render();
// }
