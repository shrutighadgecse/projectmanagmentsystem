const apiBase = '/api/machineccnproject';

const fields = {
    ccn: document.getElementById('machineCcnProjectCcn'),
    ccnDisplay: document.getElementById('ccnDisplay'),
    searchCcn: document.getElementById('searchCcn'),
    statusBanner: document.getElementById('statusPill'),
    messageBox: document.getElementById('messageBox'),
    poRecordNo: document.getElementById('poRecordNo'),
    poReferenceNumber: document.getElementById('poReferenceNumber'),
    poSalesPerson: document.getElementById('poSalesPerson'),
    poCustomerName: document.getElementById('poCustomerName'),
    poMachineName: document.getElementById('poMachineName'),
    poModelName: document.getElementById('poModelName'),
    poOrderType: document.getElementById('poOrderType'),
    poDate: document.getElementById('poDate'),
    poDeliveryDate: document.getElementById('poDeliveryDate'),
    poReceiptDate: document.getElementById('poReceiptDate'),
    poValueLacs: document.getElementById('poValueLacs'),
    poAddress: document.getElementById('poAddress'),
    machineName: document.getElementById('machineName'),
    kickoffMachineFamily: document.getElementById('kickoffMachineFamily'),
    kickoffUnit: document.getElementById('kickoffUnit'),
    kickoffMachineCategory: document.getElementById('kickoffMachineCategory'),
    kickoffOrderType: document.getElementById('kickoffOrderType'),
    kickoffCcnStatus: document.getElementById('kickoffCcnStatus'),
    kickoffInterUnitChange: document.getElementById('kickoffInterUnitChange'),
    kickoffMachineCpQty: document.getElementById('kickoffMachineCpQty'),
    kickoffMachineOptionalCpQty: document.getElementById('kickoffMachineOptionalCpQty'),
    kickoffMembersPresent: document.getElementById('kickoffMembersPresent'),
    kickoffMachineBasics: document.getElementById('kickoffMachineBasics'),
    kickoffMachineFeatures: document.getElementById('kickoffMachineFeatures'),
    kickoffAdditionalOptions: document.getElementById('kickoffAdditionalOptions'),
    kickoffProjectKickoffDetails: document.getElementById('kickoffProjectKickoffDetails'),
    cpTrialDataInput: document.getElementById('cpTrialRemarksTable'),
    cpTrialTableBody: document.getElementById('cpTrialTableBody'),
    workmen: document.getElementById('workmen'),
    workmenContainer: document.getElementById('workmen-container'),
    workmenCheckboxes: document.getElementById('workmen-checkboxes'),
    addWorkmenInput: document.getElementById('add-workmen-input'),
    addWorkmenBtn: document.getElementById('add-workmen-btn'),
    actualFields: [
        'actualOaGenerationDate', 'actualCpOaGenerationDate', 'actualAdvPaymentRecdDate',
        'actualBalPaymentRecdDate', 'actualMachineLayoutApprovalDate', 'actualRoomLayoutApprovalDate',
        'actualDispatchClearanceRecdDate', 'actualCommercialClearanceDate',
        'actualCpLayoutApprovalDate', 'actualOptionalCpLayoutApprovalDate', 'actualDqReleaseDate', 'actualPpcBasicMaterialDate',
        'actualPpcOptionalMaterialDate', 'actualIfatProtocolReleaseDate',
        'actualPmrSubmissionDate', 'actualProductReceiptDate', 'actualPackagingReceiptDate',
        'actualComplianceDate', 'actualPackingClearanceRecdDate', 'actualCpDesignReleaseDate',
        'actualMachineCpManufacturingDate', 'actualMachineOptionalCpManufacturingDate',
        'actualMachineBasicCompletion', 'actualMachineCustomizationCompletion',
        'actualAssyStartDate', 'actualBasicMaterialHandedOverAssyDate', 'actualOptionalMaterialHandedOverAssyDate',
        'actualBasicMaterialReceiptDate', 'actualCustomizationMaterialReceiptDate', 'actualIfatStdCpDate', 'actualIfatCustomerCpDate',
        'actualCfatDate', 'actualHandedToDispatchDate', 'actualDispatchDate',
        'actualPoReceiptDate', 'actualKomDate', 'actualPpcBasicDate', 'actualPpcOptionalDate',
        'actualLoaBasic', 'actualLoaOptional',
        // New fields
        'dnNumber', 'salesOrderNumber', 'remarksSales', 'mcnRemarksShortfall',
        'totalBasicAssemblyAttached', 'totalOptionalAssemblyAttached', 'totalAdditionalNewAssemblyAttachedAfterCfat',
        'additionalNewAssemblyAttachedAfterCfatDate', 'assembliesReleasedBy', 'remarksDesign',
        'mcnNumber', 'mcnWithShortfall', 'productRemark', 'productPackagingRemarks',
        'cpTrialRemarksTable', 'machineCpTrialCompleted', 'machineOptionalCpTrialCompleted',
        'mcnShortfallClosureDate', 'actualPpcNewMaterialDateAfterCfat', 'materialProgressStatus',
        'remarksPpc', 'delayInDaysBasicMaterial', 'delayInDaysOptionalBasicMaterial',
        'delayInDaysMachineCp', 'delayInDaysOptionalCp', 'cpProgressStatusTable',
        'remarksCpCell', 'cpImageUpload', 'remarksStores', 'invoiceNumber',
        'invoiceDate', 'lrNo', 'lrDate', 'podDate', 'ccnStatus', 'machineName', 'progressStatus',
        'machineImageStoresUpload', 'lastUpdatedDataAsOn', 'workmen', 'assemblyProgressStatus',
        'pendingFollowUpMech', 'pendingFollowUpElect', 'pendingFollowUpCp', 'delayAnalysis',
        'machineImageAssemblyUpload', 'validationUploaded', 'matpsUploaded', 'videoUploaded', 'twelveDaysProject',
        'ccnInterchangedMc', 'interUnitReceipt', 'interUnitTransfer', 'issueReturnQty', 'liveStatus', 'stdTask', 'taskCompleted',
        'machineCompletionPercentage', 'projectCompletionPercentage'
    ]
};

const excludedFinalDateFields = new Set([
    'additionalNewAssemblyAttachedAfterCfatDate',
    'mcnShortfallClosureDate',
    'actualPpcNewMaterialDateAfterCfat',
    'lastUpdatedDataAsOn',
    'lrDate',
    'podDate'
]);

function isFinalizableDateField(fieldName) {
    return !excludedFinalDateFields.has(fieldName);
}

function normalizeDateInputValue(value) {
    if (!value || typeof value !== 'string') return null;
    const trimmed = value.trim();
    const isoMatch = trimmed.match(/^(\d{4})-(\d{2})-(\d{2})$/);
    if (isoMatch) return trimmed;
    const slashMatch = trimmed.match(/^(\d{2})\/(\d{2})\/(\d{4})$/);
    if (slashMatch) return `${slashMatch[3]}-${slashMatch[2]}-${slashMatch[1]}`;
    const dashMatch = trimmed.match(/^(\d{2})-(\d{2})-(\d{4})$/);
    if (dashMatch) return `${dashMatch[3]}-${dashMatch[2]}-${dashMatch[1]}`;
    return null;
}

function isValidIsoDate(value) {
    const match = value.match(/^(\d{4})-(\d{2})-(\d{2})$/);
    if (!match) return false;
    const year = Number(match[1]);
    const month = Number(match[2]);
    const day = Number(match[3]);
    const date = new Date(value);
    return date instanceof Date && !isNaN(date) && date.getFullYear() === year && date.getMonth() + 1 === month && date.getDate() === day;
}

function validateDateFieldInput(input) {
    if (!input || !input.value) return;
    const normalized = normalizeDateInputValue(input.value);
    if (!normalized || !isValidIsoDate(normalized)) {
        showMessage('Please enter a valid date in DD/MM/YYYY format with a 4-digit year.', 'error');
        input.value = '';
        return;
    }
    input.value = normalized;
}

function attachDateInputValidation() {
    document.querySelectorAll('input[type="date"]').forEach(input => {
        input.title = 'Use DD/MM/YYYY with a 4-digit year';
        input.addEventListener('blur', () => validateDateFieldInput(input));
    });
}

const planMappings = {
    actualCpOaGenerationDate: 'cp_oa_genration_date',
    actualAdvPaymentRecdDate: 'advance_payment_receipt_date',
    actualBalPaymentRecdDate: 'balance_payment_receipt_date',
    actualMachineLayoutApprovalDate: 'machine_layout_approvals',
    actualRoomLayoutApprovalDate: 'room_layout_approvals',
    actualDispatchClearanceRecdDate: 'dispatch_clearance',
    actualCommercialClearanceDate: 'commercial_clearance',
    actualCpLayoutApprovalDate: 'cp_layout_approvals',
    actualOptionalCpLayoutApprovalDate: 'optional_cp_layout_approval_date',
    actualDqReleaseDate: 'dq_release',
    actualIfatProtocolReleaseDate: 'ifat_protocol_release',
    actualPmrSubmissionDate: 'pmr_genration_submission_date',
    actualProductReceiptDate: 'products_receipt_date',
    actualPackagingReceiptDate: 'packaging_receipt_date',
    actualComplianceDate: 'compliance',
    actualPackingClearanceRecdDate: 'packaging_clearance_rec_date',
    actualCpDesignReleaseDate: 'cp_design_release_date',
    actualMachineCpManufacturingDate: 'cp_manufacturing_date',
    actualMachineOptionalCpManufacturingDate: 'optional_cp_manufacturing_date',
    actualMachineBasicCompletion: 'machine_manufacturing_basic',
    actualMachineCustomizationCompletion: 'machine_manufacturing_customisation',
    actualAssyStartDate: 'assy_start_date',
    actualBasicMaterialHandedOverAssyDate: 'basic_matl_handed_over_Assy_date',
    actualOptionalMaterialHandedOverAssyDate: 'optional_matl_handed_over_Assy_date',
    actualBasicMaterialReceiptDate: 'basic_matl_handed_receipt_date',
    actualCustomizationMaterialReceiptDate: 'customization_matl_handed_receipt_date',
    actualIfatStdCpDate: 'ifat_std_cp',
    actualIfatCustomerCpDate: 'ifat_customer_cp',
    actualCfatDate: 'cfat',
    actualHandedToDispatchDate: 'handed_dispatch_date',
    actualDispatchDate: 'planned_dispatch_date',
    actualPoReceiptDate: 'po_receipt_date',
    actualKomDate: 'kom_date',
    actualPpcBasicDate: 'ppc_basic_date',
    actualPpcOptionalDate: 'ppc_optional_date',
    actualLoaBasic: 'loa_basic',
    actualLoaOptional: 'loa_optional'
};

function formatFieldLabel(fieldName) {
    if (!fieldName) return fieldName;
    const label = fieldName
        .replace(/^actual/, '')
        .replace(/Final$/, '')
        .replace(/([a-z])([A-Z])/g, '$1 $2')
        .replace(/Date$/, '')
        .trim();
    return label.charAt(0).toUpperCase() + label.slice(1);
}

function setFinalCheckboxState(fieldName, isFinal) {
    const dateInput = document.getElementById(fieldName);
    const checkbox = document.querySelector(`.final-checkbox[data-field="${fieldName}"]`);
    if (!dateInput || !checkbox) return;

    const finalized = Boolean(isFinal);
    checkbox.checked = finalized;
    checkbox.disabled = finalized;
    dateInput.disabled = finalized;

    const titleText = finalized
        ? 'This date is finalized and cannot be changed'
        : 'Click to finalize this date';
    checkbox.title = titleText;
    if (checkbox.parentElement) {
        checkbox.parentElement.title = titleText;
    }
}

function bindFinalCheckbox(dateFieldId, checkboxElement) {
    const dateInput = document.getElementById(dateFieldId);
    if (!dateInput || !checkboxElement) return;

    checkboxElement.addEventListener('change', async () => {
        if (!checkboxElement.checked) {
            return;
        }

        const confirmed = window.confirm('Are you sure you want to finalize this date?');
        if (!confirmed) {
            checkboxElement.checked = false;
            return;
        }

        const ccn = fields.ccn.value.trim();
        if (!ccn) {
            showMessage('Load a valid CCN before finalizing a date.', 'error');
            checkboxElement.checked = false;
            return;
        }

        dateInput.disabled = true;
        checkboxElement.disabled = true;
        checkboxElement.title = 'Finalizing...';

        try {
            const response = await fetch(`${apiBase}/mark-final/${encodeURIComponent(ccn)}?dateField=${encodeURIComponent(dateFieldId)}`, {
                method: 'POST'
            });

            if (!response.ok) {
                const body = await response.text();
                throw new Error(body || `Server error ${response.status}`);
            }

            const result = await response.json();
            if (!result.success) {
                throw new Error(result.error || 'Unable to finalize this date.');
            }

            setFinalCheckboxState(dateFieldId, true);
            showMessage(`"${formatFieldLabel(dateFieldId)}" has been finalized.`, 'success');

            // Auto-set invoice date when dispatch date is finalized
            if (dateFieldId === 'actualDispatchDate') {
                document.getElementById('invoiceDate').value = dateInput.value;
            }
        } catch (error) {
            setFinalCheckboxState(dateFieldId, false);
            showMessage(error.message || 'Unable to finalize this date.', 'error');
            console.error('Finalize date error:', error);
        }
    });
}

function attachFinalCheckboxes() {
    fields.actualFields.forEach(fieldName => {
        // Skip invoiceDate as it's auto-filled when dispatch date is finalized
        if (fieldName === 'invoiceDate' || !isFinalizableDateField(fieldName)) return;

        const dateInput = document.getElementById(fieldName);
        if (!dateInput || dateInput.type !== 'date') return;
        if (document.querySelector(`.final-checkbox[data-field="${fieldName}"]`)) return;

        const wrapper = document.createElement('div');
        wrapper.className = 'final-checkbox-container';

        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.className = 'final-checkbox';
        checkbox.dataset.field = fieldName;
        checkbox.id = `${fieldName}-final-checkbox`;
        checkbox.title = 'Click to finalize this date';

        const label = document.createElement('label');
        label.htmlFor = checkbox.id;
        label.textContent = 'Final';

        wrapper.appendChild(checkbox);
        wrapper.appendChild(label);

        const rowWrapper = document.createElement('div');
        rowWrapper.className = 'date-input-row';
        const parent = dateInput.parentElement;
        if (parent) {
            parent.insertBefore(rowWrapper, dateInput);
            rowWrapper.appendChild(dateInput);
            rowWrapper.appendChild(wrapper);
        } else {
            dateInput.insertAdjacentElement('afterend', wrapper);
        }

        bindFinalCheckbox(fieldName, checkbox);
    });
}

function setText(element, value) {
    if (element) element.value = value || '';
}

function setTextArea(element, value) {
    if (element) element.value = value || '';
}

function normalizeCcnStatus(value) {
    if (!value) return '';
    const normalized = String(value).trim().toLowerCase();
    if (normalized === 'close' || normalized === 'closed') return 'closed';
    if (normalized === 'live') return 'live';
    return normalized;
}

function setStatus(text, variant = 'success') {
    fields.statusBanner.textContent = text;
    fields.statusBanner.style.background = variant === 'success' ? 'rgba(231, 245, 255, 0.88)' : 'rgba(255, 230, 230, 0.9)';
    fields.statusBanner.style.color = variant === 'success' ? '#1c3b65' : '#7a1c1c';
}

function clearForm() {
    fields.searchCcn.value = '';
    fields.ccn.value = '';
    fields.ccnDisplay.value = '';
    fields.poRecordNo.value = '';
    fields.poReferenceNumber.value = '';
    fields.poSalesPerson.value = '';
    fields.poCustomerName.value = '';
    fields.poMachineName.value = '';
    fields.poModelName.value = '';
    fields.poOrderType.value = '';
    fields.poDate.value = '';
    fields.poDeliveryDate.value = '';
    fields.poReceiptDate.value = '';
    fields.poValueLacs.value = '';
    fields.poAddress.value = '';
    if (fields.machineName) {
        fields.machineName.value = '';
    }
    fields.kickoffMachineFamily.value = '';
    fields.kickoffUnit.value = '';
    fields.kickoffMachineCategory.value = '';
    fields.kickoffOrderType.value = '';
    fields.kickoffCcnStatus.value = '';
    fields.kickoffInterUnitChange.value = '';
    fields.kickoffMachineCpQty.value = '';
    fields.kickoffMachineOptionalCpQty.value = '';
    fields.kickoffMembersPresent.value = '';
    fields.kickoffMachineBasics.value = '';
    fields.kickoffMachineFeatures.value = '';
    fields.kickoffAdditionalOptions.value = '';
    fields.kickoffProjectKickoffDetails.value = '';
    fields.actualFields.forEach(fieldName => {
        const field = document.getElementById(fieldName);
        if (field) {
            if (field.type === 'checkbox') {
                field.checked = false;
            } else if (fieldName === 'workmen') {
                // Clear workmen checkboxes
                document.querySelectorAll('#workmen-checkboxes input[type="checkbox"]').forEach(cb => cb.checked = false);
                field.value = '';
            } else {
                field.value = '';
            }
        }
        const plan = document.getElementById(formatPlanLabel(fieldName));
        if (plan) plan.textContent = 'Kom Plan: -';
    });

    document.querySelectorAll('.final-checkbox').forEach(checkbox => {
        checkbox.checked = false;
        checkbox.disabled = false;
        checkbox.title = 'Click to finalize this date';
        const dateInput = document.getElementById(checkbox.dataset.field);
        if (dateInput) {
            dateInput.disabled = false;
        }
    });

    fields.messageBox.style.display = 'none';
    setStatus('Search a CCN to populate PO + Kickoff fields.');
}

function showMessage(text, type = 'success') {
    fields.messageBox.textContent = text;
    fields.messageBox.className = `message-box ${type}`;
    fields.messageBox.style.display = 'block';
}

function formatPlanLabel(fieldName) {
    if (!fieldName || !fieldName.startsWith('actual')) {
        return 'plan' + fieldName.charAt(0).toUpperCase() + fieldName.slice(1);
    }
    return 'plan' + fieldName.substring(6);
}

function parsePlanDate(value) {
    if (!value || typeof value !== 'string') return null;

    const isoMatch = value.match(/^(\d{4})-(\d{2})(?:-(\d{2}))?/);
    if (isoMatch) {
        return new Date(`${isoMatch[1]}-${isoMatch[2]}-${isoMatch[3] || '01'}`);
    }

    const slashMatch = value.match(/^(\d{2})\/(\d{2})\/(\d{4})/);
    if (slashMatch) {
        return new Date(`${slashMatch[3]}-${slashMatch[2]}-${slashMatch[1]}`);
    }

    const parsed = Date.parse(value);
    if (!isNaN(parsed)) {
        return new Date(parsed);
    }
    return null;
}

function formatMonthYear(value) {
    const date = parsePlanDate(value);
    if (!date) return value || '-';

    const monthNames = [
        'January', 'February', 'March', 'April', 'May', 'June',
        'July', 'August', 'September', 'October', 'November', 'December'
    ];
    const day = date.getDate();
    return `${day} ${monthNames[date.getMonth()]} ${date.getFullYear()}`;
}

function populatePlanValues(kickoff) {
    fields.actualFields.forEach(fieldName => {
        const planFieldName = planMappings[fieldName];
        const planElement = document.getElementById(formatPlanLabel(fieldName));
        if (!planElement) return;
        const planValue = kickoff && kickoff[planFieldName] ? kickoff[planFieldName] : null;
        planElement.textContent = `Kom Plan: ${planValue ? formatMonthYear(planValue) : '-'}`;
    });
}

function fillPurchaseOrder(po) {
    if (!po) {
        setStatus('No Purchase Order found for this CCN.', 'error');
        return;
    }
    fields.ccnDisplay.value = po.ccn || '';
    fields.poRecordNo.value = po.recordNo || '';
    fields.poReferenceNumber.value = po.poReferenceNumber || '';
    fields.poSalesPerson.value = po.salesPerson || '';
    fields.poCustomerName.value = po.customerName || '';
    fields.poMachineName.value = po.machineName || '';
    fields.poModelName.value = po.modelName || '';
    fields.poOrderType.value = po.orderType || '';
    fields.poDate.value = po.poDate || '';
    fields.poDeliveryDate.value = po.poDeliveryDate || '';
    fields.poReceiptDate.value = po.poReceiptDate || '';
    fields.poValueLacs.value = po.poValueLacs || '';
    const addressParts = [po.addressLine1, po.addressLine2, po.city, po.state, po.country]
        .filter(Boolean)
        .join(', ');
    fields.poAddress.value = addressParts;
    if (fields.machineName) {
        fields.machineName.value = po.machineName || '';
    }
}

function fillKickoff(kickoff) {
    if (!kickoff) {
        setStatus('No Kickoff form found for this CCN.', 'error');
        return;
    }
    fields.kickoffMachineFamily.value = kickoff.machineFamily || '';
    fields.kickoffUnit.value = kickoff.unit || '';
    fields.kickoffMachineCategory.value = kickoff.machineCategory || '';
    fields.kickoffOrderType.value = kickoff.orderType || '';
    fields.kickoffCcnStatus.value = normalizeCcnStatus(kickoff.ccnStatus) || '';
    fields.kickoffInterUnitChange.value = kickoff.interUnitChange || '';
    fields.kickoffMachineCpQty.value = kickoff.machineCpQty || '';
    fields.kickoffMachineOptionalCpQty.value = kickoff.machineOptionalCpQty || '';
    fields.kickoffMembersPresent.value = kickoff.membersPresent || '';
    fields.kickoffMachineBasics.value = parseKickoffJsonField(kickoff.machineBasics);
    fields.kickoffMachineFeatures.value = parseKickoffJsonField(kickoff.machineFeatures);
    fields.kickoffAdditionalOptions.value = parseKickoffJsonField(kickoff.additionalOptions);
    fields.kickoffProjectKickoffDetails.value = kickoff.projectKickoffDetails || '';
    populatePlanValues(kickoff);
}

function parseKickoffJsonField(value) {
    if (!value) return '';
    try {
        const parsed = JSON.parse(value);
        if (Array.isArray(parsed)) {
            return parsed.map(item => typeof item === 'string' ? item : JSON.stringify(item)).join('\n');
        }
        if (parsed && typeof parsed === 'object') {
            return Object.entries(parsed)
                .map(([key, val]) => `${key}: ${val}`)
                .join('\n');
        }
        return String(parsed);
    } catch (error) {
        return String(value);
    }
}

function fillMachineCcnProject(project, kickoff) {
    if (!project && !kickoff) return;
    fields.actualFields.forEach(fieldName => {
        const field = document.getElementById(fieldName);
        if (!field) return;

        const actualValue = project ? project[fieldName] : null;
        const planFieldName = planMappings[fieldName];
        let kickoffValue = kickoff && planFieldName ? kickoff[planFieldName] : null;
        if (fieldName === 'ccnStatus' && kickoff && kickoff.ccnStatus) {
            kickoffValue = normalizeCcnStatus(kickoff.ccnStatus);
        }

        if (field.type === 'checkbox') {
            if (actualValue !== undefined && actualValue !== null) {
                field.checked = Boolean(actualValue);
            }
        } else if (fieldName === 'workmen') {
            // Special handling for workmen multi-select
            setSelectedWorkmen(actualValue || kickoffValue);
        } else {
            if (actualValue !== undefined && actualValue !== null && actualValue !== '') {
                field.value = actualValue;
            } else if (kickoffValue !== undefined && kickoffValue !== null && kickoffValue !== '') {
                field.value = kickoffValue;
            }
        }

        if (field.type === 'date') {
            const finalValue = project ? Boolean(project[`${fieldName}Final`]) : false;
            setFinalCheckboxState(fieldName, finalValue);
        }
    });
}

async function loadCcn() {
    const ccn = fields.searchCcn.value.trim();
    if (!ccn) {
        showMessage('Please enter a CCN before loading.', 'error');
        return;
    }
    try {
        const response = await fetch(`${apiBase}/by-ccn?ccn=${encodeURIComponent(ccn)}`);
        if (!response.ok) {
            const error = await response.json();
            showMessage(error.error || 'Unable to load CCN data.', 'error');
            return;
        }
        const data = await response.json();
        const { purchaseOrder, kickoffForm, machineCcnProject } = data;
        fields.ccn.value = ccn;
        fillPurchaseOrder(purchaseOrder);
        fillKickoff(kickoffForm);
        fillMachineCcnProject(machineCcnProject, kickoffForm);
        initializeCpTrialTable();
        
        // Fetch STD task based on machine model
        if (purchaseOrder && purchaseOrder.modelName) {
            const stdTask = await fetchStdTask(purchaseOrder.modelName);
            if (stdTask !== null) {
                document.getElementById('stdTask').value = stdTask;
            }
        }
        
        // Generate CP Trial Remarks Table
        generateCpTrialRemarksTable();
        
        // Calculate percentages after loading data
        calculateMachineCompletionPercentage();
        updateProjectPercentage();
        
        setStatus('Data loaded successfully. Editable Kickoff fields are shown below.', 'success');
        fields.ccnDisplay.value = ccn;
    } catch (error) {
        showMessage('Server error loading CCN data.', 'error');
        console.error(error);
    }
}

async function saveProject() {
    const ccn = fields.ccn.value.trim();
    if (!ccn) {
        showMessage('Load a valid CCN first.', 'error');
        return;
    }
    const payload = { ccn, machineCategory: fields.kickoffMachineCategory.value || null };
    fields.actualFields.forEach(fieldName => {
        const field = document.getElementById(fieldName);
        if (field) {
            // Handle checkbox inputs specially - send boolean value instead of "on"
            if (field.type === 'checkbox') {
                payload[fieldName] = field.checked;
            } else {
                payload[fieldName] = field.value || null;
            }
        }
    });
    try {
        const response = await fetch(`${apiBase}/save`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });

        if (!response.ok) {
            // If response is not ok, try to get error message as text
            const errorText = await response.text();
            showMessage(`Server error (${response.status}): ${errorText}`, 'error');
            return;
        }

        // Only try to parse as JSON if response is ok
        const contentType = response.headers.get('content-type');
        if (!contentType || !contentType.includes('application/json')) {
            showMessage('Server returned unexpected response format.', 'error');
            return;
        }

        const result = await response.json();
        if (!result.success) {
            showMessage(result.error || 'Unable to save Machine CCN Project.', 'error');
            return;
        }
        showMessage(result.message || 'Machine CCN Project saved successfully.', 'success');
        fields.ccn.value = ccn;
    } catch (error) {
        showMessage('Network error when saving Machine CCN Project.', 'error');
        console.error('Save error:', error);
    }
}

// Function to fetch STD Task from MachineModel table
async function fetchStdTask(machineModel) {
    if (!machineModel) return null;
    try {
        const response = await fetch(`/api/machine-model/std-task?model=${encodeURIComponent(machineModel)}`);
        if (response.ok) {
            return await response.json();
        }
    } catch (error) {
        console.error('Error fetching STD task:', error);
    }
    return null;
}

// Function to calculate Machine Completion Percentage
function calculateMachineCompletionPercentage() {
    const stdTask = parseFloat(document.getElementById('stdTask').value) || 0;
    const taskCompleted = parseFloat(document.getElementById('taskCompleted').value) || 0;
    
    if (stdTask > 0) {
        const percentage = (taskCompleted / stdTask) * 100;
        document.getElementById('machineCompletionPercentage').value = percentage.toFixed(2);
    } else {
        document.getElementById('machineCompletionPercentage').value = '0.00';
    }
}

// Function to update Project Percentage based on Live Status
function updateProjectPercentage() {
    const liveStatus = document.getElementById('liveStatus').value;
    let projectPercent = 0;

    // Map live status stages to percentage ranges
    const statusToPercent = {
        'NEW CCN FOR APPROVAL': 0,
        'DESIGN': 4,
        'IN MUMBAI': 8,
        'RECEIVED FROM MUMBAI': 10,
        'PPC': 15,
        'WIP- KANBAN': 20,
        'WIP -BASIC': 25,
        'WIP- OPTIONALS': 27,
        'HOLD - AUTOMATION PENDING': 30,
        'HOLD': 15,
        'STD CP TRIALS': 35,
        'IFAT IN PROGRESS': 40,
        'IFAT DONE': 45,
        'POST IFAT - POINT CLOSURE': 47,
        'READY': 50,
        'AWAITED PRODUCT': 52,
        'AWAITED PACKAGING': 52,
        'AWAITED CP': 55,
        'CUSTOMER CP TRIALS': 60,
        'AWAITED CFAT': 65,
        'CFAT IN PROGRESS': 70,
        'CFAT DONE': 75,
        'RE-CFAT': 60,
        'POST CFAT - POINT CLOSURE': 80,
        'COMPLIANCE - SENT TO CUSTOMER': 85,
        'AWAITING PACKING CLEARLANCE': 90,
        'FOR DISPATCH': 95,
        'AWAITING COMMERCIAL CLEARLANCE': 100,
        'CLOSE TOTAL': 100,
        'STOCK MC - READY': 50
    };

    // Get percentage directly from the selected live status
    projectPercent = statusToPercent[liveStatus] !== undefined ? statusToPercent[liveStatus] : 0;

    // Set to input
    const projectPercentageInput = document.getElementById('projectCompletionPercentage');
    if (projectPercentageInput) {
        projectPercentageInput.value = projectPercent.toFixed(2);
    }
}


function parseCpTrialDataField() {
    const rawValue = fields.cpTrialDataInput ? fields.cpTrialDataInput.value : '';
    if (!rawValue) return null;
    try {
        const parsed = JSON.parse(rawValue);
        return Array.isArray(parsed) ? parsed : null;
    } catch {
        return null;
    }
}

function getCpQuantities() {
    return {
        machineCpQty: parseInt(fields.kickoffMachineCpQty?.value, 10) || 0,
        machineOptionalCpQty: parseInt(fields.kickoffMachineOptionalCpQty?.value, 10) || 0
    };
}

function getCpRowMeta(index, machineCpQty) {
    const isBasic = index < machineCpQty;
    const cpType = isBasic ? 'Basic CP' : 'Optional CP';
    const cpNumber = isBasic ? index + 1 : index - machineCpQty + 1;
    return `${cpType} ${cpNumber}`;
}

function saveCpTrialData() {
    if (fields.cpTrialDataInput) {
        fields.cpTrialDataInput.value = JSON.stringify(cpTrialData);
    }
}

function updateCpTrialRow(index, fieldName, value) {
    if (!cpTrialData[index]) return;
    cpTrialData[index][fieldName] = value || '';
    saveCpTrialData();
}

function ensureCpTrialData() {
    const { machineCpQty, machineOptionalCpQty } = getCpQuantities();
    const totalRows = machineCpQty + machineOptionalCpQty;

    if (totalRows === 0) {
        cpTrialData = [];
        saveCpTrialData();
        return;
    }

    const preserved = Array.isArray(cpTrialData) ? cpTrialData.slice(0, totalRows) : [];
    const newRows = [];

    for (let rowIndex = 0; rowIndex < totalRows; rowIndex++) {
        const existingRow = preserved[rowIndex] || {};

        newRows.push({
            cpDetail: existingRow.cpDetail || '',
            ifatDoneOn: existingRow.ifatDoneOn || '',
            qcClearedOn: existingRow.qcClearedOn || '',
            remarks: existingRow.remarks || '',
            handedToAssemblyOn: existingRow.handedToAssemblyOn || '',
            receivedBy: existingRow.receivedBy || ''
        });
    }

    cpTrialData = newRows;
    saveCpTrialData();
}

function renderCpTrialTable() {
    const tbody = fields.cpTrialTableBody || document.getElementById('cpTrialTableBody');
    if (!tbody) return;

    tbody.innerHTML = '';

    cpTrialData.forEach((row, index) => {
        const tr = document.createElement('tr');

        const createCell = (fieldName, type = 'text') => {
            const td = document.createElement('td');
            const input = document.createElement('input');
            input.type = type;
            input.value = row[fieldName] || '';
            input.dataset.field = fieldName;
            input.dataset.index = index;
            input.addEventListener('change', event => {
                updateCpTrialRow(index, fieldName, event.target.value);
            });
            td.appendChild(input);
            return td;
        };

        tr.appendChild(createCell('cpDetail', 'text'));
        tr.appendChild(createCell('ifatDoneOn', 'date'));
        tr.appendChild(createCell('qcClearedOn', 'date'));
        tr.appendChild(createCell('remarks', 'text'));
        tr.appendChild(createCell('handedToAssemblyOn', 'date'));
        tr.appendChild(createCell('receivedBy', 'text'));
        tbody.appendChild(tr);
    });
}

function initializeCpTrialTable() {
    const storedData = parseCpTrialDataField();
    if (storedData) {
        cpTrialData = storedData;
    }
    ensureCpTrialData();
    renderCpTrialTable();
}

function handleCpQuantityChange() {
    ensureCpTrialData();
    renderCpTrialTable();
}

function init() {
    document.getElementById('loadButton').addEventListener('click', loadCcn);
    document.getElementById('saveButton').addEventListener('click', saveProject);
    document.getElementById('clearButton').addEventListener('click', clearForm);
    
    // Workmen event listeners
    if (fields.addWorkmenBtn) {
        fields.addWorkmenBtn.addEventListener('click', addNewWorkmen);
    }
    if (fields.addWorkmenInput) {
        fields.addWorkmenInput.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                addNewWorkmen();
            }
        });
    }
    
    // Add event listeners for calculations
    document.getElementById('taskCompleted').addEventListener('input', calculateMachineCompletionPercentage);
    document.getElementById('liveStatus').addEventListener('change', updateProjectPercentage);
    
    // Add event listeners for CP trial table generation
    fields.kickoffMachineCpQty.addEventListener('input', handleCpQuantityChange);
    fields.kickoffMachineOptionalCpQty.addEventListener('input', handleCpQuantityChange);
    
    // Attach final-date controls and backend binding
    attachFinalCheckboxes();
    attachDateInputValidation();
    
    // Load dynamic dropdowns on page load
    loadDynamicDropdowns();
    renderCpTrialTable();
}

// Function to calculate Machine Completion Percentage
function calculateMachineCompletionPercentage() {
    const stdTask = parseFloat(document.getElementById('stdTask').value) || 0;
    const taskCompleted = parseFloat(document.getElementById('taskCompleted').value) || 0;
    
    if (stdTask > 0) {
        const percentage = (taskCompleted / stdTask) * 100;
        document.getElementById('machineCompletionPercentage').value = percentage.toFixed(2);
    } else {
        document.getElementById('machineCompletionPercentage').value = '0.00';
    }
}

// Function to update Project Percentage based on Live Status
function updateProjectPercentage() {
    const liveStatus = document.getElementById('liveStatus').value;
    let projectPercent = 0;

    // Map live status stages to percentage ranges
    const statusToPercent = {
        'NEW CCN FOR APPROVAL': 0,
        'DESIGN': 4,
        'IN MUMBAI': 8,
        'RECEIVED FROM MUMBAI': 10,
        'PPC': 15,
        'WIP- KANBAN': 20,
        'WIP -BASIC': 25,
        'WIP- OPTIONALS': 27,
        'HOLD - AUTOMATION PENDING': 30,
        'HOLD': 15,
        'STD CP TRIALS': 35,
        'IFAT IN PROGRESS': 40,
        'IFAT DONE': 45,
        'POST IFAT - POINT CLOSURE': 47,
        'READY': 50,
        'AWAITED PRODUCT': 52,
        'AWAITED PACKAGING': 52,
        'AWAITED CP': 55,
        'CUSTOMER CP TRIALS': 60,
        'AWAITED CFAT': 65,
        'CFAT IN PROGRESS': 70,
        'CFAT DONE': 75,
        'RE-CFAT': 60,
        'POST CFAT - POINT CLOSURE': 80,
        'COMPLIANCE - SENT TO CUSTOMER': 85,
        'AWAITING PACKING CLEARLANCE': 90,
        'FOR DISPATCH': 95,
        'AWAITING COMMERCIAL CLEARLANCE': 100,
        'CLOSE TOTAL': 100,
        'STOCK MC - READY': 50
    };

    // Get percentage directly from the selected live status
    projectPercent = statusToPercent[liveStatus] !== undefined ? statusToPercent[liveStatus] : 0;

    // Set to input
    const projectPercentageInput = document.getElementById('projectCompletionPercentage');
    if (projectPercentageInput) {
        projectPercentageInput.value = projectPercent.toFixed(2);
    }
}

// Function to load dynamic dropdown options
async function loadDynamicDropdowns() {
    try {
        // Load assemblies released by options (you can modify this to fetch from your API)
        const assembliesSelect = document.getElementById('assembliesReleasedBy');
        if (assembliesSelect) {
            // Add some default options - you can replace this with API call
            const defaultOptions = ['John Doe', 'Jane Smith', 'Mike Johnson', 'Sarah Wilson'];
            assembliesSelect.innerHTML = '<option value="">Select</option>';
            defaultOptions.forEach(option => {
                const opt = document.createElement('option');
                opt.value = option;
                opt.textContent = option;
                assembliesSelect.appendChild(opt);
            });
        }

        // Load workmen options (you can modify this to fetch from your API)
        const workmenSelect = document.getElementById('workmen');
        if (workmenSelect) {
            // Add some default options - you can replace this with API call
            const defaultWorkmen = [];
            workmenSelect.innerHTML = '<option value="">Select</option>';
            defaultWorkmen.forEach(option => {
                const opt = document.createElement('option');
                opt.value = option;
                opt.textContent = option;
                workmenSelect.appendChild(opt);
            });
        }
    } catch (error) {
        console.error('Error loading dynamic dropdowns:', error);
    }
}

function clearForm() {
    // Clear all form fields
    fields.actualFields.forEach(fieldName => {
        const field = document.getElementById(fieldName);
        if (field) {
            // Handle checkbox inputs specially - uncheck them
            if (field.type === 'checkbox') {
                field.checked = false;
            } else {
                field.value = '';
            }
        }
    });
    
    // Clear search field
    fields.searchCcn.value = '';
    
    // Clear calculated fields
    document.getElementById('stdTask').value = '';
    document.getElementById('machineCompletionPercentage').value = '';
    document.getElementById('projectCompletionPercentage').value = '';
    
    // Clear CP Trial Remarks Table
    cpTrialData = [];
    if (fields.cpTrialDataInput) {
        fields.cpTrialDataInput.value = '';
    }
    renderCpTrialTable();
    
    // Reset dropdowns
    document.getElementById('assembliesReleasedBy').innerHTML = '<option value="">Select</option>';
    document.getElementById('workmen').innerHTML = '<option value="">Select</option>';
    
    // Reload dynamic dropdowns
    loadDynamicDropdowns();
    
    setStatus('Form cleared.', 'info');
}

window.addEventListener('DOMContentLoaded', init);
