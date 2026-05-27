// Function to add a new option
function addOption(field) {
    var newValue = document.getElementById('new' + capitalize(field)).value;
    $.ajax({
        url: '/addOption',  // Backend API endpoint for adding an option
        type: 'POST',
        data: { field: field, value: newValue },
        success: function(response) {
            loadDropdown(field);  // Reload dropdown with updated options
        }
    });
}

// Function to delete an option
function deleteOption(field) {
    var selectedOption = document.getElementById(capitalize(field) + 'Dropdown').value;
    $.ajax({
        url: '/deleteOption/' + selectedOption,
        type: 'DELETE',
        data: { field: field },
        success: function(response) {
            loadDropdown(field);  // Reload dropdown with updated options
        }
    });
}

// Function to search within dropdown
function searchDropdown(dropdownId) {
    var searchInput = document.getElementById('search' + capitalize(dropdownId.replace('Dropdown', ''))).value.toLowerCase();
    var dropdown = document.getElementById(dropdownId);
    var options = dropdown.getElementsByTagName('option');
    for (var i = 0; i < options.length; i++) {
        var optionText = options[i].text.toLowerCase();
        options[i].style.display = optionText.includes(searchInput) ? '' : 'none';
    }
}

// Function to load dropdown from backend
function loadDropdown(field) {
    $.ajax({
        url: '/getOptions/' + field,
        type: 'GET',
        success: function(response) {
            var dropdown = document.getElementById(capitalize(field) + 'Dropdown');
            dropdown.innerHTML = '';  // Clear existing options
            response.forEach(function(option) {
                var opt = document.createElement('option');
                opt.value = option.id;
                opt.text = option.name;
                dropdown.appendChild(opt);
            });
        }
    });
}

// Helper function to capitalize the first letter
function capitalize(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

// Load all dropdowns on page load
window.onload = function() {
    loadDropdown('machine_name');
    loadDropdown('assy_location');
    loadDropdown('cupboard');
};
