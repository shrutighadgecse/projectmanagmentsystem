const apiUrl = 'http://localhost:8080/api';

async function fetchOptions(type) {
    const response = await fetch(`${apiUrl}/${type}`);
    const data = await response.json();
    return data;
}

async function populateDropdown(type, dropdownId) {
    const options = await fetchOptions(type);
    const dropdown = document.getElementById(dropdownId);
    dropdown.innerHTML = '';
    options.forEach(option => {
        const optionElement = document.createElement('option');
        optionElement.value = option.id;
        optionElement.textContent = option.name;
        dropdown.appendChild(optionElement);
    });
}

async function addOption(type, inputId, dropdownId) {
    const input = document.getElementById(inputId);
    const value = input.value;
    if (value) {
        const response = await fetch(`${apiUrl}/${type}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ name: value })
        });
        if (response.ok) {
            input.value = '';
            await populateDropdown(type, dropdownId);
        }
    }
}

document.getElementById('salesPersonInput').addEventListener('blur', () => addOption('salespersons', 'salesPersonInput', 'salesPersonDropdown'));
document.getElementById('customerNameInput').addEventListener('blur', () => addOption('customers', 'customerNameInput', 'customerNameDropdown'));
document.getElementById('machineNameInput').addEventListener('blur', () => addOption('machines', 'machineNameInput', 'machineNameDropdown'));
document.getElementById('modelNameInput').addEventListener('blur', () => addOption('models', 'modelNameInput', 'modelNameDropdown'));

// Initial population of dropdowns
populateDropdown('salespersons', 'salesPersonDropdown');
populateDropdown('customers', 'customerNameDropdown');
populateDropdown('machines', 'machineNameDropdown');
populateDropdown('models', 'modelNameDropdown');
