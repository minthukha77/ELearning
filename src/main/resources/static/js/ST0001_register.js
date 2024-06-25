$(document).ready(function () {
  fillPassword();


  // $(".back").click(function (event) {
  //   //TODO prevent external link

  //   event.preventDefault();

  //   if(isInternal() && history.length > 0){
  //     history.back(1);
  //   }

  // });
});

function fillPassword() {
  //Initialize previous password value to the password field
  console.log("filling pervious password");
  $("input[type='password']").each(function () {
    const oldPassword = $(this).attr("data-value");
    if (oldPassword && oldPassword.length > 0) {
      console.log("Password re enter");
      $(this).attr("value", oldPassword);
    }
  });

}

function isInternal() {
  /* Get Previous Page */
  const prevPage = document.referrer;
  /* Get current Page Host */
  const regExp = new RegExp(location.host);
  return regExp.test(prevPage);

}




/* 
 */