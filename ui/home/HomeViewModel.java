package com.laioffer.tinnews.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.laioffer.tinnews.model.Article;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.repository.NewsRepository;

// extends google 的 viewModel
public class HomeViewModel extends ViewModel {
    private final NewsRepository repository;// viewModel 告诉 repository（Model）
    private final MutableLiveData<String> countryInput = new MutableLiveData<>();

    public HomeViewModel(NewsRepository newsRepository) {
        this.repository = newsRepository;
    }

    // 由 view 来 call
    // 把 input（country）放在一个 place holder
    public void setCountryInput(String country) {
        countryInput.setValue(country);
    }

    public LiveData<NewsResponse> getTopHeadlines() {
        // 拿出 country，return
        // countryInput里有东西，就把它给getTopHeadlines
        // getTopHeadlines 会先给一个empty liveData
        // View 会盯着 viewModel里的来自于 repository的结果看
        // 有数据以后 viewModel 会更新 LiveData，View 监听到后会更新页面
        return Transformations.switchMap(countryInput, repository::getTopHeadlines);
    }


    // to favorite an article
    public void setFavoriteArticleInput(Article article) {
        repository.favoriteArticle(article);
    }

}
