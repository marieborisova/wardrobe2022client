package com.example.wardrobe2022client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvBook;
    private BookAdapter bookAdapter;
    private ItemTouchHelper.SimpleCallback simpleCallback;
    private LibraryApiImpl libraryApi;
    private AppCompatButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd  = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBookFragment addBookFragment = new AddBookFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_main, addBookFragment)
                        .commit();

            }
        });


        libraryApi = new LibraryApiImpl(this);
        libraryApi.fillBook();
        libraryApi.fillAuthor();
        libraryApi.fillGenre();


        rvBook = findViewById(R.id.rv_books);
        bookAdapter = new BookAdapter(this, NoDb.BOOK_LIST);
        rvBook.setAdapter(bookAdapter);

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

                Book book = NoDb.BOOK_LIST.get(viewHolder.getAdapterPosition());
                if(direction == ItemTouchHelper.LEFT){
                    libraryApi.deleteBook(book.getId());
                }

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvBook);

    }
    public void updateAdapter(){
        bookAdapter.notifyDataSetChanged();
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