package com.mathieu_mercier.shwitter.controller.bottom_nav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mathieu_mercier.shwitter.R;


public abstract class BottomActivity extends AppCompatActivity {
    // Une classe abstraite qui permets de reutiliser la logique de navigation
    // lorsque qu'un item est selectionnÃ©

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // IMPORTANT de ne pas setContentView(), ce sont les sous-classes qui s'en occupent
        // Voir le layout partial_bottom qui est rÃ©utilisÃ© dans les diffÃ©rentes activities
        // on y configure Ã©galement le menu via la ressource menu/bottom_nav.xml

        navigationView = (BottomNavigationView)findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            // Listerer de sÃ©lection d'un item
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Class destinationActivity = null;

                // Selon l'item de menu sÃ©lectionnÃ©, on choisi la bonne activity
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        destinationActivity =  homeActivity.class;
                        break;

                    case R.id.friend:
                        destinationActivity = FriendActivity.class;
                        break;

                    case R.id.friendrequest:
                        destinationActivity = FriendRequestActivity.class;
                        break;
                }

                Intent intent = new Intent(BottomActivity.this, destinationActivity);
                startActivity(intent);
                overridePendingTransition(0, 0);
                // l'intent est dÃ©marrÃ© en annulant la transition pour simuler qu'on ne change pas d'Ã©cran

                finish();
                // On finish pour que toutes les activities utilisable via la bottom nav ait l'air d'etre une seule
                // l'activity choisi par la bottom nav est toujours la derniere sur la pile de navigation

                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        navigationView.getMenu().findItem(getMenuItemId()).setChecked(true);
        // Lorsque l'activity dÃ©marre on sÃ©lectionne le bon item dans le menu
    }

    abstract int getMenuItemId();
}