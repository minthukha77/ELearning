
// let searchIcon = document.querySelector('#searchIcon');
// let filter = document.getElementById('filter');
// searchIcon.addEventListener('click',() => filter.focus() )

// add modal 

let add_student = document.querySelector('#add_student');
let add_student_box = document.querySelector('.add-student-box');
let add_student_cancel = document.querySelector('#add_student_cancel');

add_student.addEventListener('click', () => add_student_box.classList.remove('d-none'))
add_student_cancel.addEventListener('click', () => add_student_box.classList.add('d-none'))

let add_student_search_icon = document.querySelector('#add_student_search_icon');
let add_student_search = document.querySelector('#add_student_search');

add_student_search_icon.addEventListener('click', () => add_student_search.focus())

add_student_search.addEventListener('input', function() {
  const filter = add_student_search.value.toUpperCase();
  const studentList = document.querySelector("#student_list_container");
  const students = studentList.querySelectorAll(".d-flex.flex-column");

  students.forEach(student => {
    const name = student.querySelector(".fw-bold.d-block:nth-of-type(1)").textContent.toUpperCase();
    const phoneNumber = student.querySelector(".fw-bold.d-block:nth-of-type(2)").textContent.toUpperCase();
    const email = student.querySelector(".fw-bold.d-block:nth-of-type(3)").textContent.toUpperCase();
    if (name.indexOf(filter) > -1 || phoneNumber.indexOf(filter) > -1 || email.indexOf(filter) > -1) {
      student.classList.remove("d-none");
    } else {
      student.classList.add("d-none");
    }
  });
});

