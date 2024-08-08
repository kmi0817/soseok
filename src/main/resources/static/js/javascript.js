const header = document.querySelector("header");
let lastScrollTop = 0;

window.addEventListener("scroll", () => {
  const currentScrollTop = window.scrollY || document.documentElement.scrollTop;

  if (currentScrollTop > lastScrollTop) {
    hideHeader();
  } else if (currentScrollTop < lastScrollTop) {
    showHeader();
  }

  lastScrollTop = currentScrollTop <= 0 ? 0 : currentScrollTop;
});

const hideHeader = () => {
  header.classList.add("header-up");
  header.classList.remove("header-down");
};

const showHeader = () => {
  header.classList.add("header-down");
  header.classList.remove("header-up");
};