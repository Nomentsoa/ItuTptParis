package mg.itu.lazanomentsoa.itutptparis.views.fragments.accueil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccueilViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AccueilViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Page d'accueil");
    }

    public LiveData<String> getText() {
        return mText;
    }
}