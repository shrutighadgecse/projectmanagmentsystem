// Assembly WIP Corner - Compact Component JavaScript

function loadCompactWipData() {
    const currentYear = new Date().getFullYear();
    const loadingState = document.getElementById('wipLoadingState');
    const tableContainer = document.getElementById('wipTableContainer');
    const emptyState = document.getElementById('wipEmptyState');
    const errorContainer = document.getElementById('wipErrorContainer');

    if (!tableContainer) return; // Component not on page

    // Show loading
    if (loadingState) loadingState.style.display = 'block';
    tableContainer.style.display = 'none';
    if (emptyState) emptyState.style.display = 'none';
    if (errorContainer) errorContainer.style.display = 'none';

    // Fetch data
    fetch(`/api/machineccnproject/wip-dashboard?filterYear=${currentYear}`)
        .then(response => {
            if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
            return response.json();
        })
        .then(data => {
            if (loadingState) loadingState.style.display = 'none';

            if (data.success && data.machines && data.machines.length > 0) {
                populateCompactTable(data.machines);
                tableContainer.style.display = 'block';
            } else {
                if (emptyState) emptyState.style.display = 'block';
            }
        })
        .catch(error => {
            if (loadingState) loadingState.style.display = 'none';
            if (errorContainer) {
                errorContainer.style.display = 'block';
                errorContainer.innerHTML = `Error loading WIP data: ${error.message}`;
            }
            console.error('Error:', error);
        });
}

function populateCompactTable(machines) {
    const tbody = document.getElementById('wipTableBodyCompact');
    if (!tbody) return;

    tbody.innerHTML = '';

    let totalCF = 0, totalBuild = 0, totalDispatch = 0, totalWip = 0;

    // Add rows
    machines.forEach(machine => {
        const row = document.createElement('tr');
        const wip = machine.carryForward + machine.machineInBuild - machine.dispatch;
        const wipClass = wip > 0 ? 'positive' : wip < 0 ? 'negative' : '';

        row.innerHTML = `
            <td>${escapeHtml(machine.machine)}</td>
            <td class="wip-numeric">${machine.carryForward}</td>
            <td class="wip-numeric">${machine.machineInBuild}</td>
            <td class="wip-numeric">${machine.dispatch}</td>
            <td class="wip-numeric ${wipClass}">${wip}</td>
        `;
        tbody.appendChild(row);

        totalCF += machine.carryForward;
        totalBuild += machine.machineInBuild;
        totalDispatch += machine.dispatch;
        totalWip += wip;
    });

    // Add grand total
    const totalRow = document.createElement('tr');
    totalRow.className = 'grand-total';
    totalRow.innerHTML = `
        <td>Grand Total</td>
        <td>${totalCF}</td>
        <td>${totalBuild}</td>
        <td>${totalDispatch}</td>
        <td>${totalWip}</td>
    `;
    tbody.appendChild(totalRow);
}

function escapeHtml(text) {
    const map = {
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        '"': '&quot;',
        "'": '&#039;'
    };
    return text.replace(/[&<>"']/g, m => map[m]);
}

// Load on page ready
document.addEventListener('DOMContentLoaded', loadCompactWipData);
