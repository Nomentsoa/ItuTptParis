package mg.itu.lazanomentsoa.itutptparis.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import mg.itu.lazanomentsoa.itutptparis.utils.LoadingDialogFragment;
import mg.itu.lazanomentsoa.itutptparis.utils.StringConstant;

public class AbstractBaseActivity extends AppCompatActivity {
    protected LoadingDialogFragment loadingDialogFragment;
    protected FragmentManager fragmentManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = this.getSupportFragmentManager();
    }

    public void removeFragment(Fragment abstractBaseFragment) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(abstractBaseFragment).commit();
    }

    public void showLoading(boolean cancelable) {
        loadingDialogFragment = new LoadingDialogFragment();
        loadingDialogFragment.show(fragmentManager, StringConstant.createUniqueTag());
        loadingDialogFragment.setCancelable(cancelable);
    }

    public void dismissLoading() {
        loadingDialogFragment.dismiss();
    }
}
