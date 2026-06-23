package com.example.project_uts;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TransactionActivity extends AppCompatActivity {

    private Button btnTambahAqua, btnTambahSprite, btnTambahCleo;
    private Button btnTambahIndomie, btnTambahChitato, btnTambahOreo;
    private Button btnTambahSusuUltra, btnTambahSariRoti, btnTambahKopi;

    private Button btnKurangAqua, btnKurangSprite, btnKurangCleo;
    private Button btnKurangIndomie, btnKurangChitato, btnKurangOreo;
    private Button btnKurangSusuUltra, btnKurangSariRoti, btnKurangKopi;

    private Button btnResetKasir, btnBayarKasir;
    private TextView tvTotalHargaKasir, tvDetailBelanja;
    private EditText etBayar;

    private int qtyAqua = 0, qtySprite = 0, qtyCleo = 0;
    private int qtyIndomie = 0, qtyChitato = 0, qtyOreo = 0;
    private int qtySusuUltra = 0, qtySariRoti = 0, qtyKopi = 0;

    private int totalTagihan = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        btnTambahAqua = findViewById(R.id.btnTambahAqua);
        btnTambahSprite = findViewById(R.id.btnTambahSprite);
        btnTambahCleo = findViewById(R.id.btnTambahCleo);
        btnTambahIndomie = findViewById(R.id.btnTambahIndomie);
        btnTambahChitato = findViewById(R.id.btnTambahChitato);
        btnTambahOreo = findViewById(R.id.btnTambahOreo);
        btnTambahSusuUltra = findViewById(R.id.btnTambahSusuUltra);
        btnTambahSariRoti = findViewById(R.id.btnTambahSariRoti);
        btnTambahKopi = findViewById(R.id.btnTambahKopi);

        btnKurangAqua = findViewById(R.id.btnKurangAqua);
        btnKurangSprite = findViewById(R.id.btnKurangSprite);
        btnKurangCleo = findViewById(R.id.btnKurangCleo);
        btnKurangIndomie = findViewById(R.id.btnKurangIndomie);
        btnKurangChitato = findViewById(R.id.btnKurangChitato);
        btnKurangOreo = findViewById(R.id.btnKurangOreo);
        btnKurangSusuUltra = findViewById(R.id.btnKurangSusuUltra);
        btnKurangSariRoti = findViewById(R.id.btnKurangSariRoti);
        btnKurangKopi = findViewById(R.id.btnKurangKopi);

        btnResetKasir = findViewById(R.id.btnReset);
        btnBayarKasir = findViewById(R.id.btnBayar);
        tvTotalHargaKasir = findViewById(R.id.tvTotalHarga);
        tvDetailBelanja = findViewById(R.id.tvDetailBelanja);
        etBayar = findViewById(R.id.etBayar);


        btnTambahAqua.setOnClickListener(v -> {
            qtyAqua++;
            hitungUlangTransaksi();
        });
        btnKurangAqua.setOnClickListener(v -> {
            if (qtyAqua > 0) { qtyAqua--; hitungUlangTransaksi(); }
        });

        btnTambahSprite.setOnClickListener(v -> {
            qtySprite++;
            hitungUlangTransaksi();
        });
        btnKurangSprite.setOnClickListener(v -> {
            if (qtySprite > 0) { qtySprite--; hitungUlangTransaksi(); }
        });

        btnTambahCleo.setOnClickListener(v -> {
            qtyCleo++;
            hitungUlangTransaksi();
        });
        btnKurangCleo.setOnClickListener(v -> {
            if (qtyCleo > 0) { qtyCleo--; hitungUlangTransaksi(); }
        });

        btnTambahIndomie.setOnClickListener(v -> {
            qtyIndomie++;
            hitungUlangTransaksi();
        });
        btnKurangIndomie.setOnClickListener(v -> {
            if (qtyIndomie > 0) { qtyIndomie--; hitungUlangTransaksi(); }
        });

        btnTambahChitato.setOnClickListener(v -> {
            qtyChitato++;
            hitungUlangTransaksi();
        });
        btnKurangChitato.setOnClickListener(v -> {
            if (qtyChitato > 0) { qtyChitato--; hitungUlangTransaksi(); }
        });

        btnTambahOreo.setOnClickListener(v -> {
            qtyOreo++;
            hitungUlangTransaksi();
        });
        btnKurangOreo.setOnClickListener(v -> {
            if (qtyOreo > 0) { qtyOreo--; hitungUlangTransaksi(); }
        });

        btnTambahSusuUltra.setOnClickListener(v -> {
            qtySusuUltra++;
            hitungUlangTransaksi();
        });
        btnKurangSusuUltra.setOnClickListener(v -> {
            if (qtySusuUltra > 0) { qtySusuUltra--; hitungUlangTransaksi(); }
        });

        btnTambahSariRoti.setOnClickListener(v -> {
            qtySariRoti++;
            hitungUlangTransaksi();
        });
        btnKurangSariRoti.setOnClickListener(v -> {
            if (qtySariRoti > 0) { qtySariRoti--; hitungUlangTransaksi(); }
        });

        btnTambahKopi.setOnClickListener(v -> {
            qtyKopi++;
            hitungUlangTransaksi();
        });
        btnKurangKopi.setOnClickListener(v -> {
            if (qtyKopi > 0) { qtyKopi--; hitungUlangTransaksi(); }
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

        btnResetKasir.setOnClickListener(v -> resetSemuaData());

        btnBayarKasir.setOnClickListener(v -> {
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
                    resetSemuaData();
                } else {
                    Toast.makeText(TransactionActivity.this, "Transaksi Gagal! Uang pembayaran masih kurang.", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(TransactionActivity.this, "Input uang tidak valid!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hitungUlangTransaksi() {
        totalTagihan = (qtyAqua * 3000) + (qtySprite * 5500) + (qtyCleo * 5000)
                + (qtyIndomie * 3500) + (qtyChitato * 11500) + (qtyOreo * 8000)
                + (qtySusuUltra * 7000) + (qtySariRoti * 15000) + (qtyKopi * 12500);

        StringBuilder detailBuilder = new StringBuilder();

        if (qtyAqua > 0) detailBuilder.append("Aqua 600ml (x").append(qtyAqua).append(")\n");
        if (qtySprite > 0) detailBuilder.append("Sprite 250ml (x").append(qtySprite).append(")\n");
        if (qtyCleo > 0) detailBuilder.append("Le Mineral (x").append(qtyCleo).append(")\n");
        if (qtyIndomie > 0) detailBuilder.append("Indomie Goreng (x").append(qtyIndomie).append(")\n");
        if (qtyChitato > 0) detailBuilder.append("Chitato 68g (x").append(qtyChitato).append(")\n");
        if (qtyOreo > 0) detailBuilder.append("Oreo Vanilla (x").append(qtyOreo).append(")\n");
        if (qtySusuUltra > 0) detailBuilder.append("Ultra Milk 250ml (x").append(qtySusuUltra).append(")\n");
        if (qtySariRoti > 0) detailBuilder.append("Sari Roti Tawar (x").append(qtySariRoti).append(")\n");
        if (qtyKopi > 0) detailBuilder.append("Kopi Kapal Api (x").append(qtyKopi).append(")\n");

        if (detailBuilder.length() == 0) {
            tvDetailBelanja.setText("Belum ada item yang dipilih");
        } else {
            tvDetailBelanja.setText(detailBuilder.toString().trim());
        }

        updateTotalText();
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

    private void resetSemuaData() {
        qtyAqua = 0; qtySprite = 0; qtyCleo = 0;
        qtyIndomie = 0; qtyChitato = 0; qtyOreo = 0;
        qtySusuUltra = 0; qtySariRoti = 0; qtyKopi = 0;
        totalTagihan = 0;
        etBayar.setText("");
        hitungUlangTransaksi();
    }
}