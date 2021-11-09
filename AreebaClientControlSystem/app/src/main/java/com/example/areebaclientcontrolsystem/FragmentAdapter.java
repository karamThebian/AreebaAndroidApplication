package com.example.areebaclientcontrolsystem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new AddClient();

            case 2:
                return new EditClient();

            case 3:
                return new DeleteClient();

            case 4:
                return new SearchClient();
        }

        return new Home();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
