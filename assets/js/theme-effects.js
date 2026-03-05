document.addEventListener("DOMContentLoaded", () => {
  document.documentElement.classList.add("js-enabled");

  const navLinks = document.querySelectorAll(".site-nav-list a");
  const currentPath = window.location.pathname.replace(/\/$/, "");

  navLinks.forEach((link) => {
    const linkPath = new URL(link.href).pathname.replace(/\/$/, "");
    if (linkPath === currentPath) {
      link.classList.add("active-nav-link");
      link.setAttribute("aria-current", "page");
    }
  });

  const revealTargets = document.querySelectorAll(
    ".post-preview-card, .home-card, .home-action-card, .submenu"
  );

  if (!revealTargets.length) {
    return;
  }

  if (!("IntersectionObserver" in window)) {
    revealTargets.forEach((el) => el.classList.add("is-visible"));
    return;
  }

  const observer = new IntersectionObserver(
    (entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          entry.target.classList.add("is-visible");
          observer.unobserve(entry.target);
        }
      });
    },
    {
      threshold: 0.1,
      rootMargin: "0px 0px -30px 0px",
    }
  );

  revealTargets.forEach((el) => {
    el.classList.add("reveal-on-scroll");
    observer.observe(el);
  });
});
