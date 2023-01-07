package ma.pack.ep.pharmacieees.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ma.pack.ep.pharmacieees.R;
import ma.pack.ep.pharmacieees.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    public WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_home, container, false);
        mWebView = (WebView) v.findViewById(R.id.webview);
        mWebView.loadUrl("https://www.telecontact.ma/services/pharmacies-de-garde/casablanca-Maroc");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}