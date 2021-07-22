package mg.itu.lazanomentsoa.itutptparis.views.fragments.profil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfilViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProfilViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Page de profil");
    }

    public LiveData<String> getText() {
        return mText;
    }
}