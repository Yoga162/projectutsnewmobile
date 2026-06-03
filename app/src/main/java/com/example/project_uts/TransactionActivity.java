package com.example.project_uts;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TransactionActivity extends AppCompatActivity {

    private Button btnAqua, btnSprite, btnCleo, btnResetKasir, btnBayarKasir;
    private TextView tvTotalHargaKasir;
    private EditText etBayar; // Tambahkan deklarasi EditText untuk input uang

    private int totalTagihan = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        btnAqua = findViewById(R.id.btnTambahAqua);
        btnSprite = findViewById(R.id.btnTambahSprite);
        btnCleo = findViewById(R.id.btnTambahCleo);
        btnResetKasir = findViewById(R.id.btnReset);
        btnBayarKasir = findViewById(R.id.btnBayar);
        tvTotalHargaKasir = findViewById(R.id.tvTotalHarga);
        etBayar = findViewById(R.id.etBayar); // Inisialisasi etBayar

        btnAqua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalTagihan += 3000;
                updateTotalText();
            }
        });

        btnSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalTagihan += 5500;
                updateTotalText();
            }
        });

        btnCleo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalTagihan += 5000;
                updateTotalText();
            }
        });

        etBayar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                updateTotalText();
            }
        });

        btnResetKasir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalTagihan = 0;
                etBayar.setText("");
                updateTotalText();
            }
        });

        btnBayarKasir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputBayar = etBayar.getText().toString().trim();

                if (totalTagihan == 0) {
                    Toast.makeText(TransactionActivity.this, "Keranjang masih kosong, Bos Mang!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (inputBayar.isEmpty()) {
                    Toast.makeText(TransactionActivity.this, "Masukkan jumlah uang pembayaran dahulu!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int uangBayar = Integer.parseInt(inputBayar);

                    if (uangBayar >= totalTagihan) {
                        Toast.makeText(TransactionActivity.this, "Transaksi Berhasil!", Toast.LENGTH_LONG).show();

                        totalTagihan = 0;
                        etBayar.setText("");
                        updateTotalText();
                    } else {
                        Toast.makeText(TransactionActivity.this, "Transaksi Gagal! Uang pembayaran masih kurang.", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(TransactionActivity.this, "Input uang tidak valid!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateTotalText() {
        String formatTotal = "Total: Rp " + String.format("%,d", totalTagihan).replace(',', '.');
        String inputBayar = etBayar.getText().toString().trim();

        if (inputBayar.isEmpty()) {
            tvTotalHargaKasir.setText(formatTotal);
            return;
        }

        try {
            int uangBayar = Integer.parseInt(inputBayar);
            int selisih = uangBayar - totalTagihan;

            if (selisih > 0) {
                String formatKembalian = String.format("%,d", selisih).replace(',', '.');
                tvTotalHargaKasir.setText(formatTotal + "\nKembalian: Rp " + formatKembalian);
            } else if (selisih < 0) {
                String formatKurang = String.format("%,d", Math.abs(selisih)).replace(',', '.');
                tvTotalHargaKasir.setText(formatTotal + "\nKurang: Rp " + formatKurang);
            } else {
                tvTotalHargaKasir.setText(formatTotal + "\nUang Pas");
            }
        } catch (NumberFormatException e) {
            tvTotalHargaKasir.setText(formatTotal);
        }
    }
}