**MeraBills App Overview**
This app allows users to add, manage, and save payments using different payment types. Payments are stored and retrieved efficiently using JSON and local file storage.

**Files**
**MainActivity.java** – The main screen where users add and view payments.
**AddPaymentDialog.java** – A pop-up dialog for adding new payments.
**FileHelper.java** – Handles saving and reading payment data from local storage.
**Payment.java** – A model class representing a payment (type, amount, provider, reference).
**JsonUtils.java** – Converts payment data to/from JSON format.

**Important Functions:**

**MainActivity.java -**
loadPayments() -> Reads saved payments and updates the UI.
openAddPaymentDialog() -> Opens the dialog to add a payment.
savePayments() -> Saves all payments to local storage.
updateUI() -> Refreshes the list of payments on the screen.

**AddPaymentDialog.java -**
toggleFields() -> Shows/hides provider & reference fields based on payment type.
validateAndSavePayment() -> Checks inputs and sends the new payment back to MainActivity.

**FileHelper.java -**
writeToFile() -> Saves payment data as a file.
readFromFile() -> Retrieves saved payment data.

**JsonUtils.java -**
toJson() -> Converts a list of payments into a JSON string.
fromJson() -> Converts a JSON string back into a list of payments.

🔄 App Flow
**App starts** → Loads saved payments using FileHelper.
**User adds a payment** -> AddPaymentDialog collects details and returns them to MainActivity.
**Payment appears on screen** -> updateUI() refreshes the view.
**User saves payments** -> Data is converted to JSON and stored using FileHelper.
**Next app launch** -> Payments are retrieved and displayed again.
