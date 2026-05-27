// search-box open close js code
let navbar = document.querySelector(".navbar");
let searchBox = document.querySelector(".search-box .bx-search");
// let searchBoxCancel = document.querySelector(".search-box .bx-x");

if (searchBox) {
  searchBox.addEventListener("click", ()=>{
    if (navbar) {
      navbar.classList.toggle("showInput");
      if(navbar.classList.contains("showInput")){
        searchBox.classList.replace("bx-search" ,"bx-x");
      }else {
        searchBox.classList.replace("bx-x" ,"bx-search");
      }
    }
  });
}

// sidebar open close js code
let navLinks = document.querySelector(".nav-links");
let menuOpenBtn = document.querySelector(".navbar .bx-menu");
let menuCloseBtn = document.querySelector(".nav-links .bx-x");

if (menuOpenBtn) {
  menuOpenBtn.onclick = function() {
    if (navLinks) navLinks.style.left = "0";
  }
}

if (menuCloseBtn) {
  menuCloseBtn.onclick = function() {
    if (navLinks) navLinks.style.left = "-100%";
  }
}


// sidebar submenu open close js code
let htmlcssArrow = document.querySelector(".htmlcss-arrow");
if (htmlcssArrow) {
  htmlcssArrow.onclick = function() {
    if (navLinks) navLinks.classList.toggle("show1");
  }
}

let moreArrow = document.querySelector(".more-arrow");
if (moreArrow) {
  moreArrow.onclick = function() {
    if (navLinks) navLinks.classList.toggle("show2");
  }
}

let jsArrow = document.querySelector(".js-arrow");
if (jsArrow) {
  jsArrow.onclick = function() {
    if (navLinks) navLinks.classList.toggle("show3");
  }
}

let storesArrow = document.querySelector(".stores-arrow");
if (storesArrow) {
  storesArrow.onclick = function() {
    if (navLinks) navLinks.classList.toggle("showStores");
  }
}

// Submenu toggles
let bhomikaArrow = document.querySelector(".bhomika-arrow");
if (bhomikaArrow) {
  bhomikaArrow.onclick = function() {
    if (navLinks) navLinks.classList.toggle("showBhomika");
  }
}

let kanbanArrow = document.querySelector(".kanban-arrow");
if (kanbanArrow) {
  kanbanArrow.onclick = function() {
    if (navLinks) navLinks.classList.toggle("showKanban");
  }
}

let ifatArrow = document.querySelector(".ifat-arrow");
if (ifatArrow) {
  ifatArrow.onclick = function() {
    if (navLinks) navLinks.classList.toggle("showIfat");
  }
}

let cfatArrow = document.querySelector(".cfat-arrow");
if (cfatArrow) {
  cfatArrow.onclick = function() {
    if (navLinks) navLinks.classList.toggle("showCfat");
  }
}

let othersArrow = document.querySelector(".others-arrow");
if (othersArrow) {
  othersArrow.onclick = function() {
    if (navLinks) navLinks.classList.toggle("showOthers");
  }
}

document.addEventListener('DOMContentLoaded', function() {
  // Dropdown Minimize functionality
  document.querySelectorAll('.dropdown-close').forEach(function(btn) {
    btn.addEventListener('click', function(e) {
      e.stopPropagation();
      var subMenu = btn.closest('.sub-menu');
      if(subMenu) subMenu.style.display = 'none';
    });
  });

  // Navbar mobile menu toggle
  const menuToggle = document.getElementById('menuToggle');
  const navLinks = document.getElementById('navLinks');

  if (menuToggle && navLinks) {
    menuToggle.addEventListener('click', function() {
      navLinks.classList.toggle('active');
    });

    // Close menu when a link is clicked
    navLinks.querySelectorAll('a').forEach(link => {
      link.addEventListener('click', function() {
        navLinks.classList.remove('active');
      });
    });
  }

  // Avatar dropdown toggle
  const avatarMenu = document.getElementById('avatarMenu');
  const avatarDropdown = document.getElementById('avatarDropdown');

  if (avatarMenu && avatarDropdown) {
    console.log('Avatar dropdown initialized');
    
    // Toggle dropdown on avatar click
    avatarMenu.addEventListener('click', function(e) {
      console.log('Avatar clicked');
      e.stopPropagation();
      
      const currentDisplay = avatarDropdown.style.display;
      const isVisible = currentDisplay === 'block';
      
      console.log('Current display:', currentDisplay, 'Is visible:', isVisible);
      
      // Toggle display
      if (isVisible) {
        avatarDropdown.style.display = 'none';
        console.log('Dropdown closing');
      } else {
        avatarDropdown.style.display = 'block';
        console.log('Dropdown opening');
      }
    });

    // Close dropdown when clicking outside
    document.addEventListener('click', function(e) {
      const isClickedInside = avatarMenu.contains(e.target) || avatarDropdown.contains(e.target);
      if (!isClickedInside && avatarDropdown.style.display === 'block') {
        avatarDropdown.style.display = 'none';
        console.log('Dropdown closed (clicked outside)');
      }
    });

    // Close dropdown when a menu item is clicked
    avatarDropdown.querySelectorAll('a, button').forEach(item => {
      item.addEventListener('click', function(e) {
        // Close dropdown after item click
        if (item.getAttribute('href') !== '/logout') {
          e.preventDefault();
        }
        avatarDropdown.style.display = 'none';
        console.log('Dropdown closed (menu item clicked)');
      });
    });
  } else {
    console.log('Avatar menu or dropdown not found!');
  }

  // Close dropdowns on outside click
  document.addEventListener('click', function(e) {
    if (menuToggle && !menuToggle.contains(e.target) && !navLinks.contains(e.target)) {
      navLinks.classList.remove('active');
    }
  });

  // Make nav dropdowns click-to-toggle and stay open until clicked again.
  // This overrides hover-based closing so users can move into submenus.
  document.querySelectorAll('.nav-item-dropdown').forEach(function(item) {
    const toggle = item.querySelector('a');
    const submenu = item.querySelector('.nav-submenu');
    if (!toggle || !submenu) return;

    // Initialize submenu hidden (inline style to override CSS :hover)
    if (!submenu.style.display) submenu.style.display = 'none';
    toggle.setAttribute('aria-expanded', 'false');

    toggle.addEventListener('click', function(e) {
      // If the link is a real navigation href (not '#'), allow navigation.
      const href = toggle.getAttribute('href');
      const isPlaceholder = !href || href.trim() === '#' || href.trim().toLowerCase().startsWith('javascript:');
      if (isPlaceholder) e.preventDefault();

      const nowOpen = item.classList.toggle('dropdown-open');
      submenu.style.display = nowOpen ? 'block' : 'none';
      toggle.setAttribute('aria-expanded', String(nowOpen));
      e.stopPropagation();
    });

    // Do not auto-close on clicks inside the submenu (so user can interact)
    submenu.addEventListener('click', function(e) {
      e.stopPropagation();
    });
  });
});
