let createExamContainer = document.querySelector('.create-exam-container');
let cancelBtn = document.querySelector('#cancel_btn');
let createExam = document.querySelector('#create_exam');

cancelBtn.addEventListener('click', () => createExamContainer.classList.add('d-none'));
createExam.addEventListener('click', () => {
    createExamContainer.classList.remove('d-none')
    $("#exam_status_dropdown").css("display", "none");
});