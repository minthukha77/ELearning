$(document).ready(function () {
  console.log("Doc on service");

  //   Action for on Form Submit
  onSubmit();
  //   Action for when Day check boxes are clicked
  onDayCheckBoxClick();
  //   Set Required Fileds
  setRequiredField();
  //   Set Disabled Fileds
  setDisabledField();

  onClassTypeCheckBoxClicked();

  // $("#btnReg").click(function (e) {
  //     e.preventDefault();
  //     e.stopPropagation();
  //     $(form).addClass("was-validated");

  // });
});

function onClassTypeCheckBoxClicked() {
  if ($("#liveCheck").is(":checked")) {
    $("#iflive").show();
    $("#ifvideo").hide();
  } else {
    $("#iflive").hide();
    $("#ifvideo").show();
  }

  setRequiredField();
}

const onDayCheckBoxClick = () => {
  for (let i = 0; i < 7; i++) {
    $("#day" + i).change(function (e) {
      e.preventDefault();
      // Disable if it is not checked
      isDisable = !this.checked;
      $("#startTime" + i).prop("disabled", isDisable);
      $("#endTime" + i).prop("disabled", isDisable);
      isRequired = this.checked;
      $("#startTime" + i).prop("required", isRequired);
      $("#endTime" + i).prop("required", isRequired);
    });
  }
};

const setRequiredField = () => {
  fields = ["#maxStu", "#startDate", "#endDate"];
  const isRequired = $("#liveCheck").is(":checked");
  fields.forEach((e) => {
    $(e).prop("required", isRequired);
  });
};

const setDisabledField = () => {
  for (let i = 0; i < 7; i++) {
    const isChecked = $("#day" + i).is(":checked");
    isDisable = !isChecked;

    $("#startTime" + i).prop("disabled", isDisable);
    $("#endTime" + i).prop("disabled", isDisable);
  }
};

const onSubmit = () => {
  const form = $("form");

  $("#btnReg").click((event) => {
    console.log("")
    $(form).addClass("was-validated");
    let isFormValid = checkDate();
    isFormValid = checkTime() && isFormValid;
    if (!(form[0].checkValidity() && isFormValid)) {
      event.preventDefault();
      event.stopPropagation();
      // Get the selected payment type
      var paymentType = document.querySelector('input[name="paymentType"]:checked').value;

      // Set the payment type value in the hidden input field
      document.getElementById("paymentType").value = paymentType;

      // Create a new FormData object to store the form data
      var formData = new FormData(document.getElementById("myForm"));

      // Send the form data to the server using AJAX
      var xhr = new XMLHttpRequest();
      xhr.open("POST", "/teacher/save-course-register");
      xhr.onload = function () {
        if (xhr.status === 200) {
          console.log(xhr.responseText);
        }
      };
      xhr.send(formData);

    }
  });
};

const checkDate = () => {
  const startDate = new Date($("#startDate").val());
  const endDate = new Date($("#endDate").val());
  if (startDate > endDate) {
    const errorMessage = "The end date should be later";
    $("#startDate")[0].setCustomValidity(errorMessage);
    $("#endDate")[0].setCustomValidity(errorMessage);
    $(".date-feedback").html(errorMessage);
    return false;
  } else {
    $("#startDate")[0].setCustomValidity("");
    $("#endDate")[0].setCustomValidity("");
    $(".date-feedback").html("This field must be filled");
  }
  return true;
};

const checkTime = () => {
  let isValid = true;
  for (let i = 0; i < 7; i++) {
    const isChecked = $("#day" + i).is(":checked");
    if (isChecked) {
      const startTimeInput = $("#startTime" + i);
      const endTimeInput = $("#endTime" + i);
      const startTime = minFromMidnight(startTimeInput.val());
      const endTime = minFromMidnight(endTimeInput.val());
      if (startTime < 0 || endTime < 0) {
        // If startTime or endTime is null;
        isValid = false;
        continue;
      }
      if (startTime > endTime) {
        const errorMessage = "The end time should be later";
        startTimeInput[0].setCustomValidity(errorMessage);
        endTimeInput[0].setCustomValidity(errorMessage);
        $(".day" + i + "-feedback").html(errorMessage);
        isValid = false;
      } else {
        startTimeInput[0].setCustomValidity("");
        endTimeInput[0].setCustomValidity("");
        $(".day" + i + "-feedback").html("This field must be filled");
      }
    }
  }
  return isValid;
};

function minFromMidnight(tm) {
  try {
    var ampm = tm.substr(-2);
    var clk = tm.substr(0, 5);
    var m = parseInt(clk.match(/\d+$/)[0], 10);
    var h = parseInt(clk.match(/^\d+/)[0], 10);
    h += ampm.match(/pm/i) ? 12 : 0;
    return h * 60 + m;
  } catch (e) {

  }
  return -1;

}

// $("input").on("click", function () {
//   $("#log").html($("input:checked").val() + " is checked!");
// });

// $('input[type="checkbox"]').click(function () {
//   if (!this.checked) {
//     $(this).closest("div").nextAll("div").find("input").attr("disabled", true);
//   } else {
//     $(this).closest("div").nextAll("div").find("input").attr("disabled", false);
//   }
// });



// ##### New JS code for Course Registration #####

// upload img files 
// let imgUpload = document.querySelector('.img-upload');
// let upload = document.querySelector('#image_input');
// let img = document.querySelector('.display-image');

// imgUpload.addEventListener('click',()=>{
//     upload.click()
// })

// upload.addEventListener('change',()=>{
//     let photo = upload.files[0];
//     console.log(upload.files)
//     let reader = new FileReader();

//     reader.addEventListener('load',()=>{
//         img.src = reader.result;
//         img.classList = 'display-image'
//     });

//     reader.readAsDataURL(photo);
// })


// add_new_category
let add = document.querySelector('#add_new_category');
let close = document.querySelectorAll('.close');
let catBox = document.querySelector('.add-category');
let inputInSelect = document.querySelector('.add');
let catTitle = document.querySelector('#new_cat');
let courseTitle = document.querySelector('#course_title');

add.addEventListener('click', () => {
  catBox.classList.remove('d-none');
  catBox.classList.add('ani');
});

close.forEach(el => {
  el.addEventListener('click', () => {
    catBox.classList.add('d-none');
    delBox.classList.add('d-none');
  })
});

inputInSelect.addEventListener('click', () => {
  courseTitle.innerHTML += `<option value="${catTitle.value}">${catTitle.value}</option>`;
  delteTitle.innerHTML += `<option value="${catTitle.value}">${catTitle.value}</option>`;
  catBox.classList.add('d-none');
  catTitle.value = ''
})

catTitle.addEventListener('keyup', (e) => {
  if (e.key == 'Enter') {
    courseTitle.innerHTML += `<option value="${catTitle.value}">${catTitle.value}</option>`;
    delteTitle.innerHTML += `<option value="${catTitle.value}">${catTitle.value}</option>`;
    catBox.classList.add('d-none');
    catTitle.value = ''
  }
})

// delete category

let del = document.querySelector('#delete_category');
let delBox = document.querySelector('.delete-category');
let delBtn = document.querySelector('.deleteBtn');
let delteTitle = document.querySelector('#delete_cat');
let courseTitleValue = courseTitle.value;

del.addEventListener('click', () => {
  delBox.classList.remove('d-none');
  delBox.classList.add('ani');
});

delBtn.addEventListener('click', () => {
  if (delteTitle.value != 'Select Category') {
    for (var i = 0; i < courseTitle.length; i++) {
      if (courseTitle.options[i].value == delteTitle.value)
        courseTitle.remove(i);
    }
    for (var i = 0; i < delteTitle.length; i++) {
      if (delteTitle.options[i].value == delteTitle.value)
        delteTitle.remove(i);
    }
    delBox.classList.add('d-none');
  }
})