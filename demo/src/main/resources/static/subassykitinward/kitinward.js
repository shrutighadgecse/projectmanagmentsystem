 // Function to populate the Assembly Code dropdown on page load
 async function populateAssemblyCodeDropdown() {
    const response = await fetch("/kit-inward/assembly-codes");
    const assemblyCodes = await response.json();
    
    const dropdown = document.getElementById("assemblyCode");
    assemblyCodes.forEach(code => {
        const option = document.createElement("option");
        option.value = code;
        option.text = code;
        dropdown.appendChild(option);
    });
}

// Function to fetch and autofill details based on selected Assembly Code
async function fetchAssemblyDetails() {
    const assemblyCode = document.getElementById("assemblyCode").value;
    if (assemblyCode) { // Only fetch if an assembly code is selected
        const response = await fetch(`/kit-inward/fetch-details?assemblyCode=${assemblyCode}`);
        const data = await response.json()
        document.getElementById("description").value = data.stage || "";
        document.getElementById("stage").value = data.stage || ""; // Autofill Stage
        document.getElementById("machineName").value = data.machineName || ""; // Autofill Machine Name
    }
}

// Call the function to populate the dropdown when the page loads

const blcPoInput = document.getElementById('blcPoInput');
const suggestionsBox = document.getElementById('suggestions');

blcPoInput.addEventListener('input', async (e) => {
    const query = e.target.value;

    if (query.length >= 2) {
        const response = await fetch(`/api/kit-inward/suggestions?blcPo=${query}`);
        const suggestions = await response.json();

        suggestionsBox.innerHTML = '';
        if (suggestions.length > 0) {
            suggestions.forEach(suggestion => {
                const div = document.createElement('div');
                div.textContent = suggestion;
                div.classList.add('suggestion-item');
                div.addEventListener('click', () => {
                    blcPoInput.value = suggestion;
                    suggestionsBox.innerHTML = '';
                });
                suggestionsBox.appendChild(div);
            });
        }
    } else {
        suggestionsBox.innerHTML = '';
    }
});


window.onload = populateAssemblyCodeDropdown;
