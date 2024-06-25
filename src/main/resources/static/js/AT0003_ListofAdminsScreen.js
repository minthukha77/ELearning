$(function () {
  $("#paymentAlert").hide();

  $("#submitPayment").click((e) => {
    console.log("submit payment button clicked");
    const paymentPost = $("#paymentPost");
    const fields = paymentPost
      .find("select, textarea, :input:not([type=hidden])")
      .serializeArray();
    let hasError = false;
    $.each(fields, function (i, field) {
      if (!field.value) {
        hasError = true;
      }
    });
    if (hasError) {
      $("#paymentAlert").show();
    } else {
      $("#paymentPost").submit();
    }
  });

  const deleteUser = (uid) => {
    console.log("deleting user ", uid);
    $.ajax({
      type: "delete",
      url: "/admin/profile/delete",
      data: { uid },
    }).done(function () {
      //reload page\
      console.log(userRole)
      if (userRole == 'ROLE_ADMIN') {
        location.href = "/admin/admin-list";
      }
      if (userRole == 'ROLE_STUDENT') {
        location.href = "/admin/student-list";
      }
      if (userRole == 'ROLE_TEACHER') {
        location.href = "/admin/teacher-list";
      }
    }).fail(function () {
      alert("Something went wrong");
    });

  }

  const suspendUser = (uid) => {
    console.log("suspending user ", uid);
    $.ajax({
      type: "post",
      url: "/admin/profile/suspend",
      data: { uid },
    }).done(function () {
      //reload page
      location.reload();
    }).fail(function () {
      alert("Something went wrong");
    });

  }

  const reactivateUser = (uid) => {
    console.log("reactivating user ", uid);
    $.ajax({
      type: "post",
      url: "/admin/profile/reactivate",
      data: { uid },
    }).done(function () {
      //reload page
      location.reload();
    }).fail(function () {
      alert("Something went wrong");
    });

  }

  const verifyUser = (uid) => {
    console.log("verifying user ", uid);
    $.ajax({
      type: "post",
      url: "/admin/profile/verify",
      data: { uid },
    }).done(function () {
      //reload page
      location.reload();
    }).fail(function () {
      alert("Something went wrong");
    });

  }

  $("#actionConfirmationModal").on("show.bs.modal", function (event) {
    const button = $(event.relatedTarget);

    const action = button.data("bs-action"); // Extract info from data-* attributes
    // Extract info from data-* attributes

    const modal = $(this);
    modal.find(".modal-title").text(action + " User");
    const actionBtn = modal.find("#actionBtn");
    actionBtn.text(action);
    actionBtn.click((e) => {
      if (action == "Delete") {
        console.log("perform delete operation");
        deleteUser(uid);
      } else if (action == "Suspend") {
        console.log("perform suspend operation");
        suspendUser(uid);
      } else if (action == "Re-activate") {
        console.log("perform re-active operation");
        reactivateUser(uid);
      } else if (action == "Verify") {
        console.log("perform verify operation");
        verifyUser(uid);
      }
    });

    // modal.find("#deleteCertificate").click((e) => {
    //     $.ajax({
    //         type: "delete",
    //         url: uri,
    //         data: { name },
    //     }).done(function () {
    //         //reload page
    //         window.location.href = uri;
    //     }).fail(function () {
    //         alert("Something went wrong");
    //     });
    // });

    /* modal.find('.modal-body input').val(recipient) */
  });




});
