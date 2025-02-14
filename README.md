🟦 **MeraBills App Overview**
This app allows users to add, manage, and save payments using different payment types. Payments are stored and retrieved efficiently using JSON and local file storage.

🟦 **Files**
**MainActivity.java** – The main screen where users add and view payments.
**AddPaymentDialog.java** – A pop-up dialog for adding new payments.
**FileHelper.java** – Handles saving and reading payment data from local storage.
**Payment.java** – A model class representing a payment (type, amount, provider, reference).
**JsonUtils.java** – Converts payment data to/from JSON format.

🟦 **Important Functions:**
**1] MainActivity.java -**
loadPayments() -> Reads saved payments and updates the UI.
openAddPaymentDialog() -> Opens the dialog to add a payment.
savePayments() -> Saves all payments to local storage.
updateUI() -> Refreshes the list of payments on the screen.

**2] AddPaymentDialog.java -**
toggleFields() -> Shows/hides provider & reference fields based on payment type.
validateAndSavePayment() -> Checks inputs and sends the new payment back to MainActivity.

**3] FileHelper.java -**
writeToFile() -> Saves payment data as a file.
readFromFile() -> Retrieves saved payment data.

**4] JsonUtils.java -**
toJson() -> Converts a list of payments into a JSON string.
fromJson() -> Converts a JSON string back into a list of payments.

🟦 App Flow
**1] App starts** → Loads saved payments using FileHelper.
**2] User adds a payment** -> AddPaymentDialog collects details and returns them to MainActivity.
**3] Payment appears on screen** -> updateUI() refreshes the view.
**4] User saves payments** -> Data is converted to JSON and stored using FileHelper.
**5] Next app launch** -> Payments are retrieved and displayed again.

<img src="https://github.com/user-attachments/assets/776910a3-0d4e-434d-bc30-7296e8ff40ef" width="300" height="700"/>
<img src="https://github.com/user-attachments/assets/90aba4a2-4939-43b8-98e1-ec20d53604f7" width="300" height="700"/>
<img src="https://github.com/user-attachments/assets/5df0e620-3e32-45b4-a329-860f7dc1f6d7" width="300" height="700"/>
<img src="https://github.com/user-attachments/assets/c381c2a1-e00f-47fa-b23d-a73509c7e809" width="300" height="700"/>
<img src="https://github.com/user-attachments/assets/1ac90d9f-7e82-4b2a-9e52-e81033bbde78" width="300" height="700"/>




