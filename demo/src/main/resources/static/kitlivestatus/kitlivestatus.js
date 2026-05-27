const liveStatusDropdown = document.getElementById('liveStatusDropdown');
  const holdDetailsModal = document.getElementById('holdDetailsModal');
  const holdSaveButton = document.getElementById('holdSaveButton');

  // Show Hold Details Page when 'Hold' is selected
  liveStatusDropdown.addEventListener('change', function() {
    if (liveStatusDropdown.value === 'hold') {
      holdDetailsModal.style.display = 'block';
    } else {
      holdDetailsModal.style.display = 'none';
    }
  });

  // Save button logic for Hold Details Page
  holdSaveButton.addEventListener('click', function() {
    holdDetailsModal.style.display = 'none';
    // Add logic here to save the data if needed
  });