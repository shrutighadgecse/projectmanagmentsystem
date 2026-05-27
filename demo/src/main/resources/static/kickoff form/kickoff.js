// ===== AUTO-SAVE TO LOCALSTORAGE (DRAFT) =====
const DRAFT_STORAGE_KEY = 'kickoff_form_draft';
const DRAFT_TIMESTAMP_KEY = 'kickoff_form_draft_timestamp';

// Save form data to localStorage
function saveFormDraft() {
    const form = document.getElementById('kickoff-form');
    const formData = new FormData(form);
    let data = {};
    formData.forEach((value, key) => {
        data[key] = value;
    });
    localStorage.setItem(DRAFT_STORAGE_KEY, JSON.stringify(data));
    localStorage.setItem(DRAFT_TIMESTAMP_KEY, new Date().toISOString());
    console.log('Form Draft Auto-Saved:', data);
}

// Restore form data from localStorage
function restoreFormDraft() {
    const savedDraft = localStorage.getItem(DRAFT_STORAGE_KEY);
    if (savedDraft) {
        try {
            const data = JSON.parse(savedDraft);
            const form = document.getElementById('kickoff-form');
            
            // Restore all form fields
            for (let [key, value] of Object.entries(data)) {
                const field = form.elements[key];
                if (field) {
                    if (field.type === 'checkbox' || field.type === 'radio') {
                        field.checked = value === 'on' || value === true;
                    } else {
                        field.value = value;
                    }
                }
            }
            
            // Restore members-present selection
            if (data['members_present']) {
                membersPresentOptions = data['members_present'].split(',').filter(m => m.trim());
                updateMembersPresentSelected();
            }
            
            // Restore machine family
            if (data['machine_family']) {
                document.getElementById('machine_family_input').value = data['machine_family'];
            }
            
            showDraftNotification();
            console.log('Form Draft Restored');
        } catch (e) {
            console.error('Error restoring draft:', e);
        }
    }
}

// Show notification that draft was restored
function showDraftNotification() {
    const timestamp = localStorage.getItem(DRAFT_TIMESTAMP_KEY);
    if (timestamp) {
        const draftDate = new Date(timestamp);
        const formattedTime = draftDate.toLocaleString();
        
        const notification = document.createElement('div');
        notification.id = 'draft-notification';
        notification.style.cssText = `
            position: fixed;
            top: 20px;
            right: 20px;
            background: #fff3cd;
            border: 1px solid #ffc107;
            border-radius: 4px;
            padding: 12px 16px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.15);
            z-index: 9999;
            font-size: 14px;
            color: #856404;
            max-width: 300px;
        `;
        notification.innerHTML = `
            <strong>Draft Restored!</strong><br>
            Last saved: ${formattedTime}
            <button onclick="discardFormDraft()" style="margin-top: 8px; margin-right: 8px; padding: 4px 8px; background: #ffc107; border: none; border-radius: 3px; cursor: pointer;">Discard</button>
            <button onclick="closeDraftNotification()" style="margin-top: 8px; padding: 4px 8px; background: transparent; border: 1px solid #ffc107; border-radius: 3px; cursor: pointer;">Keep</button>
        `;
        document.body.appendChild(notification);
        
        // Auto-close after 8 seconds
        setTimeout(() => {
            if (document.getElementById('draft-notification')) {
                closeDraftNotification();
            }
        }, 8000);
    }
}

// Close draft notification
function closeDraftNotification() {
    const notification = document.getElementById('draft-notification');
    if (notification) {
        notification.remove();
    }
}

// Discard draft
function discardFormDraft() {
    localStorage.removeItem(DRAFT_STORAGE_KEY);
    localStorage.removeItem(DRAFT_TIMESTAMP_KEY);
    closeDraftNotification();
    const form = document.getElementById('kickoff-form');
    form.reset();
    console.log('Form Draft Discarded');
}

// Clear draft after successful submission
function clearFormDraft() {
    localStorage.removeItem(DRAFT_STORAGE_KEY);
    localStorage.removeItem(DRAFT_TIMESTAMP_KEY);
    console.log('Form Draft Cleared (Submitted)');
}

// Function to save form data (manual save)
function saveForm() {
    const form = document.getElementById('kickoff-form');
    const formData = new FormData(form);

    // Create an object to store form data
    let data = {};
    formData.forEach((value, key) => {
        data[key] = value;
    });

    // Save to draft
    saveFormDraft();
    
    // Log the data to the console
    console.log('Form Data Saved:', data);

    // Show a message to the user
    alert('Form data has been saved to draft!');
}

// Function to discard form data
function discardForm() {
    const form = document.getElementById('kickoff-form');
    
    // Clear the form
    form.reset();
    
    // Clear draft
    discardFormDraft();

    // Show a message to the user
    alert('Form data has been discarded!');
}
