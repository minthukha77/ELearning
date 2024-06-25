const toggle = document.querySelector(".checkboxClass");

const menu = document.querySelector(".menu-items");
const line1 = document.querySelector(".line1");
const line2 = document.querySelector(".line2");
const line3 = document.querySelector(".line3");
let toggleOpen = false;

toggle.addEventListener("click", () => {
	if (!toggleOpen) {
		menu.classList.add("open");
		line1.classList.add("open-line1");
		line2.classList.add("open-line2");
		line3.classList.add("open-line3");
		toggleOpen = true;
	} else {
		menu.classList.remove("open");
		line1.classList.remove("open-line1");
		line2.classList.remove("open-line2");
		line3.classList.remove("open-line3");
		toggleOpen = false;
	}
});

// Scroll to top

const scrollToTop = document.querySelector(".scrollToTop");
window.addEventListener("scroll", () => {
	const scroll = this.scrollY;

	if (scroll < 200) {
		scrollToTop.classList.remove("show-scroll");
	}

	if (scroll > 200) {
		scrollToTop.classList.add("show-scroll");
	}
});

function handleScrollToTop() {
	window.scrollTo(0, 0);
}
