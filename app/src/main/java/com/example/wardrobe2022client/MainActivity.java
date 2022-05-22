package com.example.wardrobe2022client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.wardrobe2022client.adapter.ClothingAdapter;
import com.example.wardrobe2022client.domain.Clothing;
import com.example.wardrobe2022client.fragment.AddClothingFragment;
import com.example.wardrobe2022client.nodb.NoDb;
import com.example.wardrobe2022client.rest.WardrobeApiVolley;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvClothing;
    private ClothingAdapter clothingAdapter;
    private ItemTouchHelper.SimpleCallback simpleCallback;
    private WardrobeApiVolley wardrobeApiVolley;
    private AppCompatButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd  = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddClothingFragment addClothingFragment = new AddClothingFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_main, addClothingFragment)
                        .commit();

            }
        });


        wardrobeApiVolley = new WardrobeApiVolley(this);
        wardrobeApiVolley.fillClothing();
        wardrobeApiVolley.fillSeason();
        wardrobeApiVolley.fillSize();
        wardrobeApiVolley.fillSex();


        rvClothing = findViewById(R.id.rv_clothing);
        clothingAdapter = new ClothingAdapter(this, NoDb.CLOTHING_LIST);
        rvClothing.setAdapter(clothingAdapter);

        simpleCallback = new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT
        ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                Clothing clothing = NoDb.CLOTHING_LIST.get(viewHolder.getAdapterPosition());
                if(direction == ItemTouchHelper.LEFT){
                    wardrobeApiVolley.deleteClothing(clothing.getId());
                }

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvClothing);

    }
    public void updateAdapter(){
        clothingAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        int size = fragmentList.size();
        if (size > 0){
            getSupportFragmentManager().beginTransaction()
                    .remove(fragmentList.get(size - 1))
                    .commit();
        }
        else{
            finish();
        }

    }
}