package ma.pack.ep.pharmacieees.ui.villes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ma.pack.ep.pharmacieees.databinding.FragmentVilleBinding;

public class VillesFragment extends Fragment {

    private FragmentVilleBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        VillesViewModel VillesViewModel =
                new ViewModelProvider(this).get(ma.pack.ep.pharmacieees.ui.villes.VillesViewModel.class);

       binding=FragmentVilleBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        final TextView textView = binding.textVilles;
      VillesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}