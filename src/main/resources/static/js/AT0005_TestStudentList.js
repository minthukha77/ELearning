
// let searchIcon = document.querySelector('#searchIcon');
// let filter = document.getElementById('filter');
// searchIcon.addEventListener('click',() => filter.focus() )

// add modal 
let add_student = document.querySelector('#add_student');
let add_student_box = document.querySelector('.add-student-box');
let add_student_cancel = document.querySelector('#add_student_cancel');

add_student.addEventListener('click',() => add_student_box.classList.remove('d-none'))
add_student_cancel.addEventListener('click',() => add_student_box.classList.add('d-none'))

let add_student_search_icon = document.querySelector('#add_student_search_icon');
let add_student_search = document.querySelector('#add_student_search');

add_student_search_icon.addEventListener('click',() => add_student_search.focus())

let student_search_icon = document.querySelector('#student_search_icon');
let student_search = document.querySelector('#student_search');

student_search_icon.addEventListener('click',() => student_search.focus())