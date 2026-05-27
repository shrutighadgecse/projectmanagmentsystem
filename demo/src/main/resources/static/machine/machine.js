function showPage(pageId) {
  const pages = document.querySelectorAll('.page');
  if (pages && pages.length) {
    pages.forEach(p => p.style.display = 'none');
  }
  const el = document.getElementById(pageId);
  if (el) el.style.display = 'block';
}

function savePage(pageId) {
  alert('Data saved for ' + pageId);
}

function discardChanges() {
  alert('Changes discarded');
}

document.addEventListener('DOMContentLoaded', function() {
  // Show first page by default (if pages exist)
  const pages = document.querySelectorAll('.page');
  if (pages && pages.length) {
    pages.forEach((p, idx) => p.style.display = idx === 0 ? 'block' : 'none');
  }

  // Safe helper to get element by id
  const $ = id => document.getElementById(id);

  // cpTrialDropdown toggling (guard element existence)
  const cpTrialDropdown = $('cpTrialDropdown');
  const cpModal = $('cpModal');
  if (cpTrialDropdown && cpModal) {
    cpTrialDropdown.addEventListener('change', function() {
      cpModal.style.display = (this.value === 'yes') ? 'block' : 'none';
    });
  }

  // Add-row button safe binding
  const addRowButton = $('addRowButton');
  if (addRowButton) {
    addRowButton.addEventListener('click', function() {
      const tableBody = $('cpTableBody');
      if (!tableBody) return;
      const newRow = document.createElement('tr');
      newRow.innerHTML = `
        <td><input type="text" name="cpLayout[]" /></td>
        <td><input type="text" name="coCustomerName[]" /></td>
      `;
      tableBody.appendChild(newRow);

      // Apply uppercase to new inputs immediately
      newRow.querySelectorAll('input[type="text"]').forEach(inp => {
        inp.addEventListener('input', function() { this.value = this.value.toUpperCase(); });
      });
    });
  }

  // Enhance existing save/discard functions to be non-blocking and safe
  window.saveForm = function() {
    const form = document.querySelector('#kickoff-form, form');
    if (!form) { alert('No form found to save.'); return; }
    const formData = new FormData(form);
    const data = {};
    formData.forEach((v,k) => {
      // handle multiple same-name fields
      if (data[k]) {
        if (!Array.isArray(data[k])) data[k] = [data[k]];
        data[k].push(v);
      } else data[k] = v;
    });
    console.log('Form Data Saved:', data);
    alert('Form data has been saved!');
  };

  window.discardForm = function() {
    const form = document.querySelector('#kickoff-form, form');
    if (!form) { alert('No form found to discard.'); return; }
    form.reset();
    // re-hide modals if any
    if (cpModal) cpModal.style.display = 'none';
    alert('Form data has been discarded!');
  };

  // Attach uppercase behavior to visible text inputs (makes UX consistent with kickoff)
  document.querySelectorAll('input[type="text"], input[type="search"]').forEach(inp => {
    inp.addEventListener('input', function() { this.value = this.value.toUpperCase(); });
  });

  // Simple showPage wrapper to reuse existing function name safely
  window.showPage = function(pageId) {
    const pages = document.querySelectorAll('.page');
    if (pages && pages.length) {
      pages.forEach(p => p.style.display = 'none');
    }
    const el = document.getElementById(pageId);
    if (el) el.style.display = 'block';
  };

  // Optional: wire savePage if used by UI (fallback)
  window.savePage = window.savePage || function(pageId) {
    alert('Data saved for ' + pageId);
  };

  // If there are buttons with classes .save/.discard attach nicer behaviors
  document.querySelectorAll('.btn.save').forEach(b => b.addEventListener('click', saveForm));
  document.querySelectorAll('.btn.discard').forEach(b => b.addEventListener('click', discardForm));
});
