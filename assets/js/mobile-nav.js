// Mobile Navigation Toggle
document.addEventListener('DOMContentLoaded', function() {
  const menuToggle = document.querySelector('.mobile-menu-toggle');
  const siteNav = document.querySelector('.site-nav');
  const overlay = document.querySelector('.mobile-nav-overlay');
  const body = document.body;

  if (!menuToggle || !siteNav || !overlay) return;

  function openMenu() {
    siteNav.classList.add('active');
    overlay.classList.add('active');
    menuToggle.setAttribute('aria-expanded', 'true');
    body.style.overflow = 'hidden';
  }

  function closeMenu() {
    siteNav.classList.remove('active');
    overlay.classList.remove('active');
    menuToggle.setAttribute('aria-expanded', 'false');
    body.style.overflow = '';
  }

  function toggleMenu() {
    if (siteNav.classList.contains('active')) {
      closeMenu();
    } else {
      openMenu();
    }
  }

  // Toggle menu on button click
  menuToggle.addEventListener('click', toggleMenu);

  // Close menu when overlay is clicked
  overlay.addEventListener('click', closeMenu);

  // Close menu when a nav link is clicked
  const navLinks = siteNav.querySelectorAll('a');
  navLinks.forEach(link => {
    link.addEventListener('click', closeMenu);
  });

  // Close menu on escape key
  document.addEventListener('keydown', function(e) {
    if (e.key === 'Escape' && siteNav.classList.contains('active')) {
      closeMenu();
    }
  });

  // Close menu when window is resized beyond mobile breakpoint
  let resizeTimer;
  window.addEventListener('resize', function() {
    clearTimeout(resizeTimer);
    resizeTimer = setTimeout(function() {
      if (window.innerWidth > 880 && siteNav.classList.contains('active')) {
        closeMenu();
      }
    }, 250);
  });
});
