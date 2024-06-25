$(function(){
    $("#deleteCourseConfirmModal").on("show.bs.modal", function (event) {
        const button = $(event.relatedTarget);
    
        const title = button.data("bs-title"); // Extract info from data-* attributes
        const courseId = button.data("bs-id"); // Extract info from data-* attributes
    
        const modal = $(this);
        modal.find(".modal-title").text("Delete " + title);
        modal.find("#deleteCourse").click((e) => {
          $.ajax({
            type: "delete",
            url: "/admin/course-details/delete/",
            data: { courseId },
          }).done(function () {
            //reload page
            window.location.href = "/admin/top/";
          }).fail(function () {
            alert("Something went wrong");
          });
        });
    
        /* modal.find('.modal-body input').val(recipient) */
      });

      //verify course
      $("#verifyCourseConfirmModal").on("show.bs.modal", function (event) {
        const button = $(event.relatedTarget);
    
        const title = button.data("bs-title"); // Extract info from data-* attributes
        const courseId = button.data("bs-id"); // Extract info from data-* attributes
    
        const modal = $(this);
        modal.find(".modal-title").text("Verify " + title);
        modal.find("#verifyCourse").click((e) => {
          $.ajax({
            type: "post",
            url: "/admin/course-details/verify/",
            data: { courseId },
          }).done(function () {
            //reload page
            window.location.href = "/guest/course-detail/" + courseId;
          }).fail(function () {
            alert("Something went wrong");
          });
        });
    
        /* modal.find('.modal-body input').val(recipient) */
      });
});

document.getElementById("downloadstulist").addEventListener("click", function(){
  var table2excel = new Table2Excel();
  table2excel.export(document.querySelectorAll("#studentListTable"));
})