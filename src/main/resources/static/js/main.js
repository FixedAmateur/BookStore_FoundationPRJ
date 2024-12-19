const paragraph = document.querySelector(".title");
const button_add = document.querySelector(".read_more");
const button_hidden = document.querySelector(".hidden");
button_add.addEventListener("click", function () {
  paragraph.classList.add("title_config");
  button_hidden.style.display = "block";
  button_hidden.style.cursor = "pointer";
  button_add.style.display = "none";
});
button_hidden.addEventListener("click", function () {
  paragraph.classList.remove("title_config");
  button_add.style.display = "block";
  button_hidden.style.display = "none";
});
