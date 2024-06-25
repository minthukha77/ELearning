$(function () {

    console.log(uri);

    $("#deleteConfirmModal").on("show.bs.modal", function (event) {
        const button = $(event.relatedTarget);

        const name = button.data("bs-name"); // Extract info from data-* attributes
        const id = button.data("bs-id"); // Extract info from data-* attributes

        const modal = $(this);
        modal.find(".modal-title").text("Delete " + name);
        modal.find("#deleteCertificate").click((e) => {
            $.ajax({
                type: "delete",
                url: uri,
                data: { name },
            }).done(function () {
                //reload page
                window.location.href = uri;
            }).fail(function () {
                alert("Something went wrong");
            });
        });

        /* modal.find('.modal-body input').val(recipient) */
    });


    $(".delete").click((e) => {

        const name = $(e).data('name');
        console.log(name);
        console.log("delete button clicked");


    })


});
