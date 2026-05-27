# Machine Project Form - Navigation Integration

## Changes Made

Updated all card click handlers across the application to navigate to the Machine Project form instead of the old machine form.

### Files Updated

1. **kickoff-list.html** (Root static directory)
   - Changed: `/machine/machine.html?ccn=...`
   - To: `/machine-project.html?ccn=...`
   - When user clicks a kickoff card, now opens Machine Project form

2. **machine/machine-report.html**
   - Changed: Back button navigation from `/machine/machine.html?ccn=...`
   - To: `/machine-project.html?ccn=...`
   - Allows returning to Machine Project form from reports

3. **machine/machines-by-status.html**
   - Changed: Card click navigation from `/machine/machine.html?ccn=...`
   - To: `/machine-project.html?ccn=...`
   - Status filters now redirect to Machine Project form

### How It Works Now

**Navigation Flow:**

```
1. User clicks a CCN card (from any list page)
   ↓
2. Extracts CCN from card data
   ↓
3. Navigates to: /machine-project.html?ccn=ABC123
   ↓
4. Machine Project form page loads
   ↓
5. JavaScript reads CCN from URL parameter
   ↓
6. Calls API: GET /api/machine-project/by-ccn?ccn=ABC123
   ↓
7. Data auto-populates on page load:
   - Input fields: Machine Project table data
   - Tentative dates: Kickoff form data (blue, read-only)
   ↓
8. User can edit and submit changes
   ↓
9. Changes tracked in machine_project_history table
```

### Entry Points to Machine Project Form

These pages now link to Machine Project form:

| Page | Action | Navigates To |
|------|--------|-------------|
| `kickoff-list.html` | Click CCN card | `/machine-project.html?ccn=...` |
| `machine/machine-report.html` | Click Back button | `/machine-project.html?ccn=...` |
| `machine/machines-by-status.html` | Click machine card | `/machine-project.html?ccn=...` |

### Files Structure

```
Static Directory (src/main/resources/static/):
├── machine-project.html      → Form page
├── machine-project.css       → Styling
├── machine-project.js        → Client-side logic
├── kickoff-list.html         → UPDATED (now links to machine-project)
└── machine/
    ├── machine-report.html   → UPDATED (back button links to machine-project)
    ├── machines-by-status.html → UPDATED (card clicks link to machine-project)
    └── ...
```

### Parameters Passed via URL

When navigating to machine-project form, the CCN is passed as URL parameter:

```javascript
/machine-project.html?ccn=PO-01-2026
```

The machine-project.js automatically:
1. Extracts CCN from URL: `new URLSearchParams(window.location.search).get('ccn')`
2. Calls API to fetch data
3. Populates all form fields
4. Displays tentative dates from Kickoff form

### Testing

**Test URL:**
```
http://localhost:8080/machine-project.html?ccn=YOUR_CCN_HERE
```

**Test Steps:**
1. Navigate to kickoff-list.html
2. Click any CCN card
3. Should open machine-project.html with form auto-populated
4. Try filtering and clicking different cards - each should load different data
5. Click Back button from machine-report.html - should return to form

### API Endpoints Used

```
GET /api/machine-project/by-ccn?ccn=YOUR_CCN
├── Returns: machineProject object + tentativeDates
├── Used for: Auto-populating form on page load

GET /api/machine-project/tentative-dates?ccn=YOUR_CCN
├── Returns: Kickoff form data
├── Used for: Displaying reference dates

POST /api/machine-project/save
├── Body: Updated machine project data
├── Used for: Saving changes

GET /api/machine-project/history/by-ccn?ccn=YOUR_CCN
├── Returns: All changes for this CCN
├── Used for: View Change History modal
```

### No Breaking Changes

✅ PO form workflow unchanged (po.html → po-list.html still works)
✅ Kickoff form workflow updated (now forms chain from PO → Kickoff → Machine Project)
✅ All existing list pages updated to use new form
✅ Back buttons properly configured
✅ All filters and sorting preserved

---

## Summary

Users can now click any CCN card from any list/report page and the Machine Project form will:
1. Open automatically ✓
2. Auto-populate with data from PO and Kickoff forms ✓
3. Display tentative dates from Kickoff (read-only) ✓
4. Allow editing with full change tracking ✓
5. Store all data and history properly ✓

Ready to use! 🎉
