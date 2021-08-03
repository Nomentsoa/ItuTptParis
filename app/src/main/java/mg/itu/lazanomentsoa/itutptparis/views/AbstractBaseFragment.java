package mg.itu.lazanomentsoa.itutptparis.views;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Pari;
import mg.itu.lazanomentsoa.itutptparis.utils.LoadingDialogFragment;
import mg.itu.lazanomentsoa.itutptparis.utils.StringConstant;
import mg.itu.lazanomentsoa.itutptparis.views.fragments.PariDialogFragment;

public class AbstractBaseFragment extends Fragment {
    protected LoadingDialogFragment loadingDialogFragment;
    protected FragmentManager fragmentManager;
    public static Context myContext;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
    }


    public void showLoading(boolean cancelable) {
        loadingDialogFragment = new LoadingDialogFragment();
        loadingDialogFragment.show(fragmentManager, StringConstant.createUniqueTag());
        loadingDialogFragment.setCancelable(cancelable);
    }

    public void dismissLoading() {
        loadingDialogFragment.dismiss();
    }

    public static void showPariDialog(Pari pari, int cote){
        PariDialogFragment pariDialogFragment = new PariDialogFragment(pari, cote);
        pariDialogFragment.show(((FragmentActivity)myContext).getSupportFragmentManager(), "transaction");

    }
}
