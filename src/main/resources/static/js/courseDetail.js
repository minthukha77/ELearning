const detailContainer = document.querySelectorAll(
	".detail__container"
);

const showAllDetails = document.getElementById(
	"showAllDetails"
);

const detailBars =
	document.querySelectorAll(".detail__bar");

showAllDetails.addEventListener("click", () => {
	detailContainer.forEach((detail) => {
		// if (detail.classList.contains("active")) {
		// 	detail.classList.remove("active");
		// } else {
		// 	detail.classList.add("active");
		// }
		detail.classList.toggle("active");
	});
});

for (let i = 0; i < detailBars.length; i++) {
	const detail = detailBars[i];
	const container = detailContainer[i];
	detail.addEventListener("click", () => {
		if (container.classList.contains("active")) {
			container.classList.remove("active");
		} else {
			container.classList.add("active");
		}
	});
}
