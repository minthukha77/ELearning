console.log("To the top");

//create button
const div = document.createElement("div");
div.innerHTML = `
    <button
        type="button"
        class="btn btn-dark btn-floating btn"
        id="btn-back-to-top"
        >
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-up" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M8 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L7.5 2.707V14.5a.5.5 0 0 0 .5.5z"/>
        </svg>
    </button>
`;
document.body.appendChild(div);

//Style the button
let toTopBtn = document.getElementById("btn-back-to-top");
scrollFunction();

const style = document.createElement("style");
style.innerHTML = `
    #btn-back-to-top {
        position: fixed;
        bottom: 20px;
        right: 20px;
        display: none;
    }`;
document.head.appendChild(style);

// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function () {
  scrollFunction();
};

function scrollFunction() {
  if (document.body.scrollTop > 40 || document.documentElement.scrollTop > 40) {
    toTopBtn.style.display = "block";
  } else {
    toTopBtn.style.display = "none";
  }
}
// When the user clicks on the button, scroll to the top of the document
toTopBtn.addEventListener("click", backToTop);

function backToTop() {
  document.body.scrollTop = 0;
  document.documentElement.scrollTop = 0;
}
