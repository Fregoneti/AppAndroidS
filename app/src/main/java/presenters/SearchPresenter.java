package presenters;

import android.app.Dialog;
import android.os.Bundle;

import java.util.Calendar;

import interfaces.IList;
import interfaces.ISearch;

public class SearchPresenter implements ISearch.Presenter {

    private ISearch.View view;
    public SearchPresenter(ISearch.View view) {
        this.view = view;
    }


    @Override
    public void onClickDatePicker(){
        view.startDatePicker();
    }

    @Override
    public void onClickAboutButton() { view.goBack(); }

    @Override
    public void onBack() { view.goBack();}

    @Override
    public void onSearch() {view.search();


    }
}
