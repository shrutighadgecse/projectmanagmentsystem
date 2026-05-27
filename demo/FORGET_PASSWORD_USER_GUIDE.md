# 🎯 Forget Password Feature - User Guide

## For End Users: How to Reset Your Password

---

## Quick Overview

If you forget your password, you can reset it in 3 easy steps:

1. **Step 1:** Enter your registered email
2. **Step 2:** Enter the OTP code sent to your email
3. **Step 3:** Create a new strong password

> **⏱️ Total Time:** 2-3 minutes

---

## Step-by-Step Instructions

### STEP 1: Request Password Reset

#### To Start:
1. Go to the login page: `http://localhost:8080/login`
2. Look for the **"Forgot Password?"** link below the login form
3. Click it

#### On the Email Page:
1. Enter your **registered email address**
2. System will automatically validate your email in realtime:
   - ✅ Green checkmark = Email found in system
   - ❌ Red X = Email not found
   - ⏳ Loading spinner = System checking

3. Click **"Send OTP"** button

#### What Happens Next:
- You'll see: "OTP sent to your email"
- You'll automatically move to Step 2
- Check your email inbox for the OTP code

> **💡 Tip:** If you don't see the email, check your:
> - Spam folder
> - Junk folder
> - Promotions tab (in Gmail)

---

### STEP 2: Verify OTP Code

#### On the OTP Page:
1. Look at the 6-digit code from your email
2. Enter it in the **"One-Time Password (OTP)"** field
3. Notice the timer showing how much time remains (10 minutes)

#### OTP Input Tips:
- You must enter exactly **6 digits**
- Only numbers allowed (0-9)
- No spaces needed
- The field will automatically format as you type

#### What the Timer Means:
- 🟢 **Green (10:00 - 5:00):** Plenty of time
- 🟡 **Yellow (5:00 - 2:00):** Getting close
- 🔴 **Red (2:00 - 0:00):** Hurry up!
- ⏹️ **Expired:** Request a new OTP

#### If OTP Expires:
- Click **"Resend OTP"** link at the bottom
- A new code will be sent to your email
- You get another 10 minutes
- No need to restart from Step 1

#### If OTP is Wrong:
- Error message tells you "Invalid OTP"
- Try again with the correct code
- Or resend to get a new one

#### When Correct:
- System verifies your OTP
- You automatically go to Step 3

---

### STEP 3: Create New Password

#### On the Password Reset Page:

##### Enter New Password:
1. Click on the **"New Password"** field
2. Type a strong password that meets ALL these requirements:

**Password Requirements Checklist:**

```
☐ At least 8 characters long
   Example: MyPass123! is 11 characters ✓
   Example: Pass123! is 8 characters ✓
   Example: Short1! is 7 characters ✗

☐ Contains at least one UPPERCASE letter (A-Z)
   Example: Password ✓ (has P)
   Example: password ✗ (no uppercase)

☐ Contains at least one lowercase letter (a-z)
   Example: Password123 ✓ (has lowercase)
   Example: PASSWORD123 ✗ (no lowercase)

☐ Contains at least one NUMBER (0-9)
   Example: MyPassword2 ✓ (has 2)
   Example: MyPassword ✗ (no number)

☐ Contains at least one SPECIAL CHARACTER (!@#$%^&*)
   Example: MyPass123! ✓ (has !)
   Example: MyPass123 ✗ (no special char)
```

##### Real-Time Feedback:
As you type, you'll see:
- **Strength Meter:** Shows your password strength
  - 🔴 Red = Weak (doesn't meet requirements)
  - 🟠 Orange = Medium (meets most requirements)
  - 🟢 Green = Strong (meets all requirements)

- **Requirement List:** Shows which rules you meet
  - ✅ Gray = Not checked yet
  - ✅ Green = Requirement met
  - ❌ Red = Requirement not met

##### Confirm Password:
1. Enter the same password again in the **"Confirm Password"** field
2. System shows real-time match status:
   - ✅ "Passwords match" = Can proceed
   - ❌ "Passwords do not match" = Fix it

##### Example Strong Passwords:
✅ **MyNewPassword123!**
✅ **SecurePass2025@**
✅ **UpdatedPwd2k26#**

##### Example Weak Passwords:
❌ **Password123** (no special character)
❌ **Pass!** (too short)
❌ **MYPASSWORD1!** (no lowercase)
❌ **mypassword1!** (no uppercase)

#### Submit Your New Password:
1. When ALL requirements are met:
   - Check button turns blue
   - Text changes to "Reset Password"
   
2. Click **"Reset Password"** button

#### Success!
You'll see:
- ✅ "Password Reset Successful"
- 📧 Confirmation email sent to your email
- Redirect to login page
- Message: "Please login with your new password"

---

## Important Information

### Security Tips:

🔒 **DO:**
- Use unique passwords (not used elsewhere)
- Make passwords hard to guess
- Use a mix of character types
- Keep your password private
- Write it down in a secure place (safe, password manager)

❌ **DON'T:**
- Use your name or birthdate
- Use common words (password, 123456)
- Share password with anyone
- Use same password for multiple accounts
- Write password on sticky notes

### Email Issues:

**If OTP email doesn't arrive:**
1. ✅ Check spam/junk folder first
2. ✅ Check Gmail Promotions tab
3. ✅ Wait a few moments (email can be slow)
4. ✅ Click "Resend OTP" to request new code
5. ✅ Check with IT if issue persists

**Email from:** `your-email@gmail.com` (or configured sender)

### Time Limits:

⏱️ **OTP is valid for:**
- **10 minutes** from when it's sent
- Gets marked as expired after 10 minutes
- After that, you must request a new OTP

### Account Security:

Once password is reset:
- ✅ Old password no longer works
- ✅ New password takes effect immediately
- ✅ You need to login with new password
- ✅ You'll receive confirmation email
- ✅ Any existing sessions may be logged out

---

## Common Scenarios

### Scenario 1: "I lost my OTP email"

**Solution:**
1. On Step 2 (OTP page), look for **"Resend OTP"** link
2. Click it
3. New OTP will be sent to your email
4. Check email within 10 minutes
5. Use new OTP to proceed

### Scenario 2: "My password doesn't meet requirements"

**Solution:**
1. Read the checklist on the right
2. Add missing character type:
   - Need uppercase? Add A-Z
   - Need lowercase? Add a-z
   - Need number? Add 0-9
   - Need special? Add !@#$%^&*
3. Password strength meter will turn green
4. Then submit

### Scenario 3: "Passwords don't match"

**Solution:**
1. Make sure both fields have exactly the same text
2. Check for extra spaces
3. Use password visibility toggle (👁️ icon) to verify
4. Retype if needed
5. Match indicator will show green ✅

### Scenario 4: "I see error: Email not found"

**Solution:**
1. Go back to Step 1
2. Make sure you're using the email you registered with
3. Email must be exactly as registered (case doesn't matter)
4. Try again
5. If still stuck, contact support

### Scenario 5: "OTP says expired"

**Solution:**
1. Click **"Resend OTP"** to get a new one
2. Check email immediately
3. Enter new OTP within 10 minutes
4. Don't worry - you can try again!

---

## Visual Guide

### Login Page
```
┌─────────────────────────────┐
│    IMA System               │
│  ┌─────────────────────┐    │
│  │ Email               │    │
│  │ ___________________ │    │
│  │                     │    │
│  │ Password            │    │
│  │ ___________________ │    │
│  │                     │    │
│  │ [Login Button]      │    │
│  │                     │    │
│  │ Forgot Password? <─ CLICK HERE
│  └─────────────────────┘    │
└─────────────────────────────┘
```

### Step 1: Email Entry
```
Step 1 of 3: Enter Your Email
┌────────────────┐
│ Email:         │
│ ______________ │
│ ✓ Email found  │ (Green checkmark = good)
│                │
│ [Send OTP]     │
│ [Back]         │
└────────────────┘
```

### Step 2: OTP Verification
```
Step 2 of 3: Verify OTP
Time remaining: 578s ▓▓▓▓▓▓▓░░░
┌────────────────┐
│ OTP (6-digit): │
│ ______         │ (Only numbers)
│ ✓ 6 entered    │
│                │
│ [Verify OTP]   │
│ [Back]         │
│ Resend OTP <─── If expired
└────────────────┘
```

### Step 3: Password Reset
```
Step 3 of 3: Set New Password
New Password: _________________
  Strength:  ▓▓▓▓▓░░░░░ [Strong]
  
Confirm Password: _________________
  ✓ Passwords match

Requirements:
  ✓ At least 8 characters
  ✓ Uppercase (A-Z)
  ✓ Lowercase (a-z)
  ✓ Number (0-9)
  ✓ Special (!@#$%^&*
  ✓ Match confirmed

[Reset Password]  [Back to Login]
```

---

## Keyboard Shortcuts

| Key | Action |
|-----|--------|
| Tab | Move to next field |
| Shift+Tab | Move to previous field |
| Enter | Submit form |
| Space | Toggle password visibility |
| Escape | Go back |

---

## Browser Support

Tested and working on:
- ✅ Chrome 90+
- ✅ Firefox 88+
- ✅ Safari 14+
- ✅ Edge 90+
- ✅ Mobile browsers

---

## Accessibility

The feature is designed to be accessible:
- ✅ Screen reader friendly
- ✅ Keyboard navigation enabled
- ✅ Color-blind friendly (not relying on color alone)
- ✅ Large touch targets on mobile
- ✅ High contrast text

---

## Still Need Help?

### Contact Support
- 📧 Email: support@imaproject.com
- 📞 Call: +91-XXX-XXXX-XXXX
- 🌐 Website: www.imaproject.com

### What to Tell Support:
When contacting support, provide:
1. Your registered email address
2. What step you're stuck on (1, 2, or 3)
3. Error message you're seeing
4. What browser you're using
5. Your approximate location/timezone

---

## Password Security Best Practices

### Good Passwords:
- `MyCompany2025!`
- `BlueSkySummer@26`
- `SecureNow123#`
- `PasswordReset$`

### Bad Passwords:
- `password` (too simple)
- `12345678` (only numbers)
- `QWERTYUIOP` (only uppercase)
- `MyName2000` (too predictable)

### Password Manager Recommendation
Consider using a password manager like:
- **1Password**
- **LastPass**
- **KeePass**
- **Dashlane**

These safely store complex passwords so you don't have to remember them.

---

## Forgot Something?

### Forgot Email Address?
- Check your company employee directory
- Check confirmation email from registration
- Contact HR department
- Contact support

### Forgot Both Email AND Password?
- Contact system administrator
- Provide proof of identity
- Request account recovery

### Forgot Username?
- Username = Your registered email address
- Check welcome email from registration

---

**Last Updated:** March 12, 2026  
**Version:** 1.0

For technical details, see: `FORGET_PASSWORD_SETUP_GUIDE.md`
