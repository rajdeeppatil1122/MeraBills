package com.merabills.android.ui;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.merabills.android.R;
import com.merabills.android.model.Payment;
import com.merabills.android.storage.FileHelper;
import com.merabills.android.utils.JsonUtils;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Payment> payments = new ArrayList<>();
    private ChipGroup chipGroup;
    private TextView totalAmount;
    private final ArrayList<String> paymentTypesUsed = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chipGroup = findViewById(R.id.chip_group);
        totalAmount = findViewById(R.id.total_amount);
        TextView textViewAddPayment = findViewById(R.id.btn_add_payment);
        Button saveBtn = findViewById(R.id.btn_save);

        textViewAddPayment.setPaintFlags(textViewAddPayment.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        textViewAddPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paymentTypesUsed.size()==3){
                    Toast.makeText(MainActivity.this, "All types of payments are already added", Toast.LENGTH_SHORT).show();
                } else {
                    openAddPaymentDialog(v);
                }
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePayments();
            }
        });

        loadPayments();
    }

    private void loadPayments() {
        String data = FileHelper.readFromFile(this);
        Log.d("loadPayments data", data);
        if (!data.isEmpty()) {
            payments = JsonUtils.fromJson(data);
            for (int i = 0; i < payments.size(); i++) {
                String string = " Amount " + String.valueOf(payments.get(i).getAmount());
                String string2 = " Type " + String.valueOf(payments.get(i).getType());
                String string3 = " Provider " + String.valueOf(payments.get(i).getProvider());
                String string4 = " Reference " + String.valueOf(payments.get(i).getReference());
                String string5 = " Class  " + String.valueOf(payments.get(i).getClass());

                paymentTypesUsed.add(payments.get(i).getType());

                Log.d("Payment " + i, string + string2 + string3 + string4 + string5);
            }
            updateUI();
        }
    }

    public void openAddPaymentDialog(View view) {
        new AddPaymentDialog(this, payments, paymentTypesUsed, new AddPaymentDialog.PaymentAddedListener() {
            @Override
            public void onPaymentAdded(Payment payment) {
                payments.add(payment);
                paymentTypesUsed.add(payment.getType());

                updateUI();
            }
        }).show();
    }

    private void savePayments() {
        if(!payments.isEmpty()) {
            FileHelper.writeToFile(this, JsonUtils.toJson(payments));
            Toast.makeText(MainActivity.this, "Payment Details Saved!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "No Payment Added!", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI() {
        chipGroup.removeAllViews();
        double total = 0;
        for (Payment payment : payments) {
            Chip chip = new Chip(this);
            chip.setText(String.format("%s: ₹%s", payment.getType(), payment.getAmount()));
            chip.setCloseIconVisible(true);
            chip.setOnCloseIconClickListener(v -> {
                payments.remove(payment);
                paymentTypesUsed.remove(payment.getType());
//                savePayments();
                updateUI();
            });
            chipGroup.addView(chip);
            total += payment.getAmount();
        }
        totalAmount.setText(String.format("Total: ₹%s", total));
    }
}