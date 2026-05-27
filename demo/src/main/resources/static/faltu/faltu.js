// masterkanban.js

// Function to fetch machine name options
async function fetchMachineOptions() {
    const input = document.getElementById('machine_name').value;
    const response = await fetch(`/options/machine/search?name=${input}`);
    const options = await response.json();
    const optionsList = document.getElementById('machine_name_options');

    optionsList.innerHTML = ''; // Clear previous options

    if (options.length > 0) {
        options.forEach(option => {
            const li = document.createElement('li');
            li.textContent = option.name;
            li.onclick = () => {
                document.getElementById('machine_name').value = option.name;
                optionsList.style.display = 'none'; // Hide options after selection
            };
            optionsList.appendChild(li);
        });
        optionsList.style.display = 'block'; // Show options if available
    } else {
        // If no matching options, show "Add option" link
        const li = document.createElement('li');
        li.textContent = `Add "${input}"`;
        li.onclick = () => createMachineOption(input);
        optionsList.appendChild(li);
        optionsList.style.display = 'block'; // Show the add option
    }
}

// Function to create a new machine name option
async function createMachineOption(name) {
    const response = await fetch('/options/machine/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name }),
    });

    if (response.ok) {
        const newOption = await response.json();
        document.getElementById('machine_name').value = newOption.name;
        document.getElementById('machine_name_options').style.display = 'none'; // Hide options
        alert('Machine name created: ' + newOption.name);
    } else {
        const errorMessage = await response.text();
        alert('Error creating machine name: ' + errorMessage);
    }
}

// Function to fetch Assy Location options
async function fetchAssyLocationOptions() {
    const input = document.getElementById('assy_location').value;
    const response = await fetch(`/options/assy_location/search?name=${input}`);
    const options = await response.json();
    const optionsList = document.getElementById('assy_location_options');

    optionsList.innerHTML = ''; // Clear previous options

    if (options.length > 0) {
        options.forEach(option => {
            const li = document.createElement('li');
            li.textContent = option.name;
            li.onclick = () => {
                document.getElementById('assy_location').value = option.name;
                optionsList.style.display = 'none'; // Hide options after selection
            };
            optionsList.appendChild(li);
        });
        optionsList.style.display = 'block'; // Show options if available
    } else {
        // If no matching options, show "Add option" link
        const li = document.createElement('li');
        li.textContent = `Add "${input}"`;
        li.onclick = () => createAssyLocationOption(input);
        optionsList.appendChild(li);
        optionsList.style.display = 'block'; // Show the add option
    }
}

// Function to create a new Assy Location option
async function createAssyLocationOption(name) {
    const response = await fetch('/options/assy_location/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name }),
    });

    if (response.ok) {
        const newOption = await response.json();
        document.getElementById('assy_location').value = newOption.name;
        document.getElementById('assy_location_options').style.display = 'none'; // Hide options
        alert('Assy Location created: ' + newOption.name);
    } else {
        const errorMessage = await response.text();
        alert('Error creating Assy Location: ' + errorMessage);
    }
}

// Function to fetch Cupboard options
async function fetchCupboardOptions() {
    const input = document.getElementById('cupboard').value;
    const response = await fetch(`/options/cupboard/search?name=${input}`);
    const options = await response.json();
    const optionsList = document.getElementById('cupboard_options');

    optionsList.innerHTML = ''; // Clear previous options

    if (options.length > 0) {
        options.forEach(option => {
            const li = document.createElement('li');
            li.textContent = option.name;
            li.onclick = () => {
                document.getElementById('cupboard').value = option.name;
                optionsList.style.display = 'none'; // Hide options after selection
            };
            optionsList.appendChild(li);
        });
        optionsList.style.display = 'block'; // Show options if available
    } else {
        // If no matching options, show "Add option" link
        const li = document.createElement('li');
        li.textContent = `Add "${input}"`;
        li.onclick = () => createCupboardOption(input);
        optionsList.appendChild(li);
        optionsList.style .display = 'block'; // Show the add option
    }
}

// Function to create a new Cupboard option
async function createCupboardOption(name) {
    const response = await fetch('/options/cupboard/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name }),
    });

    if (response.ok) {
        const newOption = await response.json();
        document.getElementById('cupboard').value = newOption.name;
        document.getElementById('cupboard_options').style.display = 'none'; // Hide options
        alert('Cupboard created: ' + newOption.name);
    } else {
        const errorMessage = await response.text();
        alert('Error creating Cupboard: ' + errorMessage);
    }
}

// Close the dropdown when clicking outside
document.addEventListener('click', function(event) {
    const input = document.getElementById('machine_name');
    const optionsList = document.getElementById('machine_name_options');
    
    if (!input.contains(event.target) && !optionsList.contains(event.target)) {
        optionsList.style.display = 'none'; // Close the dropdown if clicked outside
    }
});

// Repeat the above event listener for Assy Location and Cupboard
document.addEventListener('click', function(event) {
    const input = document.getElementById('assy_location');
    const optionsList = document.getElementById('assy_location_options');
    
    if (!input.contains(event.target) && !optionsList.contains(event.target)) {
        optionsList.style.display = 'none'; // Close the dropdown if clicked outside
    }
});

document.addEventListener('click', function(event) {
    const input = document.getElementById('cupboard');
    const optionsList = document.getElementById('cupboard_options');
    
    if (!input.contains(event.target) && !optionsList.contains(event.target)) {
        optionsList.style.display = 'none'; // Close the dropdown if clicked outside
    }
});