package com.brenoedl.bancomr;

import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.brenoedl.bancomr.databinding.ActivityFaturasBinding;
import com.vinaygaba.creditcardview.CreditCardView;

public class Faturas extends AppCompatActivity {
    private ActivityFaturasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityFaturasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        binding.barFaturas.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        CreditCardView cartaoCredito = binding.cartaoCredito;
        cartaoCredito.setCardNumber("5498 7225 9719 1375");
        cartaoCredito.setCardName("Breno.E.D.Lira");
        cartaoCredito.setExpiryDate("02/29");
    }
}