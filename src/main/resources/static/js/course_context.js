$(function () {
  //

  let rowId = 0;
  //
  $("#deleteConfirmModal").on("show.bs.modal", function (event) {
    const button = $(event.relatedTarget);

    const title = button.data("bs-title"); // Extract info from data-* attributes
    const id = button.data("bs-id"); // Extract info from data-* attributes

    const modal = $(this);
    modal.find(".modal-title").text("Delete " + title);
    modal.find("#deleteSyllabus").click((e) => {
      $.ajax({
        type: "delete",
        url: uri,
        data: { id },
      }).done(function () {
        //reload page
        window.location.href = uri;
      }).fail(function () {
        alert("Something went wrong");
      });
    });

    /* modal.find('.modal-body input').val(recipient) */
  });

  $("#syllabusFormModal").on("show.bs.modal", function (event) {
    const button = $(event.relatedTarget);

    $("#contentContainer").empty();
    const modal = $(this);

    previousAction = button.data("action");
    if (button.data("action") == "new") {
      modal.find(".modal-title").text("New");
    }
    if (button.data("action") == "modify") {
      // Extract info from data-* attributes
      const cardId = button.data("id");
      const card = $("#" + cardId);
      console.log(cardId);

      const titleTag = card.find(".card-title");
      const titleText = titleTag.text();
      const titleId = titleTag.data("id");
      console.log(titleText, titleId);


      modal.find(".modal-title").text("Modify " + titleText);
      modal.find("input[name=syllabusId]").attr("value", titleId);
      modal.find("input[name=title]").attr("value", titleText);

      card.find("li").each((i, value) => {
        const contentText = $(value).text();
        const contentId = $(value).data("id");
        console.log(contentId, contentText);
        renderContentInputs(contentId, contentText);
      });
    }

    /* modal.find('.modal-body input').val(recipient) */
  });

  $("#submit").click(function (e) {
    e.preventDefault();
    console.log("submitting");
    $("#addSyllabusForm").submit();
  });

  $("#addRow").click(function (e) {
    e.preventDefault();
    renderContentInputs(0, "");
  });
  const updateContentInputs = () => {
    $(".content-inputs").each((i, value) => {
      $(value)
        .find("input[type='hidden']")
        .attr("name", "content[" + i + "].contentId");
      $(value)
        .find("input[type='text']")
        .attr("name", "content[" + i + "].content");
    });
  };

  const renderContentInputs = (id, text) => {
    const templateHTML = $("#hiddenContentTemplate").html();
    const template = $(templateHTML);
    const deleteBtn = template.find("button");

    const templateId = "row-" + rowId;
    rowId++;
    template.attr("id", templateId);

    template.find("input[type='hidden']").attr("value", id);
    template.find("input[type='text']").attr("value", text);

    deleteBtn.click(function (event) {
      //TODO prevent external link
      event.preventDefault();
      $("#" + templateId).remove();
      updateContentInputs();
      console.log(templateId);
    });

    $("#contentContainer").append(template);
    updateContentInputs();
  };
});
