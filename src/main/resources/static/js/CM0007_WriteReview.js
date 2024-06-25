$('#btnSubmit').on("click",function() {
    // var getSelectedValue = document.querySelector( 'input[name="rating-star"]:checked');   
    // if(getSelectedValue != null) {   
    //     alert("Review successfully saved");
    // }
    // else{
    //     return
    // }
    // var option=document.getElementsByName('rating-star');
         
    // if (!(option[0].checked && option[1].checked && option[2].checked && option[3].checked && option[4].checked)) {
    //     alert("Please Select Rating");
    //     return false;
    //     }
            $("input[name=rating-star]").each(function() {
              
                // If radio button not checked
                // display alert message 
                if ($(this).attr("checked") != "checked") {
                    alert("Please Select Rating");
                    return false;
                }
            });
}); 