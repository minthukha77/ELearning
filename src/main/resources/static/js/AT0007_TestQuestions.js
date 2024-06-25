let createExamContainer = document.querySelector('.create-exam-container');
    cancelBtn = document.querySelector('#cancel_btn'),
    createExam = document.querySelector('#create_question');

cancelBtn.addEventListener('click',() => createExamContainer.classList.add('d-none') );
createExam.addEventListener('click',() => createExamContainer.classList.remove('d-none') );

// create exam 
let answerType = document.querySelector('#answer_Type');
    singleAnswer = document.querySelector('#single_answer'),
    multipleAnswer = document.querySelector('#multiple_answer'),
    singleAddOption = document.querySelector('#single_add_option'),
    multipleAddOption = document.querySelector('#multiple_add_option'),
    singleAddOptionBox = document.querySelector('#single_add_optionbox'),
    multipleAddOptionBox = document.querySelector('#multiple_add_optionbox'),
    singleAddNew = document.querySelector('#single_add_new'),
    multipleAddNew = document.querySelector('#multiple_add_new'),
    singleAnswerChoice = document.querySelector('#single_answer_choice'),
    multipleAnswerChoice = document.querySelector('#multiple_answer_choice'),
    singleAddText = document.querySelector('#single_add_text'),
    multipleAddText = document.querySelector('#multiple_add_text'),

answerType.addEventListener('change',() => {
    if(answerType.value == '1'){
        singleAnswer.classList.remove('d-none')
        multipleAnswer.classList.add('d-none')
    }else if(answerType.value == '2'){
        singleAnswer.classList.add('d-none')
        multipleAnswer.classList.remove('d-none')
    }else if(answerType.value == '3'){
        singleAnswer.classList.add('d-none')
        multipleAnswer.classList.add('d-none')
    }
})

singleAddOption.addEventListener('click',() => singleAddOptionBox.classList.toggle('d-none'));

multipleAddOption.addEventListener('click',() => multipleAddOptionBox.classList.toggle('d-none'));

singleAddNew.addEventListener('click', () => {
    
    if (singleAddText.value == ''){
        alert(11)
    }else{
        singleAnswerChoice.innerHTML += `
                <div class="row align-items-center mt-2 shadow-sm">
                  <div class="col-11">
                    <input
                      type="text"
                      class="form-control"
                      id=""
                      required
                      value='${singleAddText.value}'
                    />
                  </div>
                  <div class="col-1 px-1">
                    <div class="form-check">
                      <input
                        class="form-check-input"
                        required
                        type="radio"
                        name="flexRadioDefault"
                        id=""
                      />
                    </div>
                  </div>
                </div>
        `;
    }
    singleAddText.value = '';

})

multipleAddNew.addEventListener('click', () => {
    
    if (multipleAddText.value == ''){
        alert(11)
    }else{
        multipleAnswerChoice.innerHTML += `
        <div class="row align-items-center mt-2 shadow-sm">
        <div class="col-11">
          <input
            type="text"
            class="form-control"
            placeholder=""
            id="option 1"
            required
            value='${multipleAddText.value}'
          />
        </div>
        <div class="col-1 px-1">
          <div class="form-check">
            <input
              class="form-check-input"
              required
              type="checkBox"
              name="flexRadioDefault"
              id=""
            />
          </div>
        </div>
      </div>
        `;
    }
    multipleAddText.value = '';

});