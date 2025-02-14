package com.merabills.android.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.*;

import com.merabills.android.R;
import com.merabills.android.model.Payment;

import java.util.ArrayList;
import java.util.List;

public class AddPaymentDialog extends Dialog {
    private final PaymentAddedListener listener;
    private final Spinner paymentTypeSpinner;
    private final EditText amountInput, providerInput, referenceInput;

    public interface PaymentAddedListener {
        void onPaymentAdded(Payment payment);
    }

    public AddPaymentDialog(Context context, List<String> paymentTypesUsed, PaymentAddedListener listener) {
        super(context);
        this.listener = listener;
        setContentView(R.layout.dialog_add_payment);

        paymentTypeSpinner = findViewById(R.id.payment_type_spinner);
        amountInput = findViewById(R.id.amount_input);
        providerInput = findViewById(R.id.provider_input);
        referenceInput = findViewById(R.id.reference_input);

        ArrayList<String> availablePaymentTypes = new ArrayList<>(List.of("Cash", "Bank Transfer", "Credit Card"));
        availablePaymentTypes.removeAll(paymentTypesUsed);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, availablePaymentTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentTypeSpinner.setAdapter(adapter);

        paymentTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toggleFields(paymentTypeSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        findViewById(R.id.btn_add_payment).setOnClickListener(v -> validateAndSavePayment());
    }

    private void toggleFields(String paymentType) {
        if (paymentType.equals("Cash")) {
            providerInput.setVisibility(View.GONE);
            referenceInput.setVisibility(View.GONE);
        } else {
            providerInput.setVisibility(View.VISIBLE);
            referenceInput.setVisibility(View.VISIBLE);
        }
    }

    private void validateAndSavePayment() {
        if (amountInput.getText().toString().trim().isEmpty()) {
            amountInput.setError("Enter amount");
            return;
        }

        String selectedPaymentType = paymentTypeSpinner.getSelectedItem().toString();
        if (!selectedPaymentType.equals("Cash")) {
            if (providerInput.getText().toString().trim().isEmpty()) {
                providerInput.setError("Enter provider");
                return;
            }
            if (referenceInput.getText().toString().trim().isEmpty()) {
                referenceInput.setError("Enter transaction reference");
                return;
            }
        }

        double amount = Double.parseDouble(amountInput.getText().toString().trim());
        String provider = providerInput.getText().toString().trim();
        String reference = referenceInput.getText().toString().trim();

        listener.onPaymentAdded(new Payment(selectedPaymentType, amount, provider, reference));
        dismiss();
    }

}