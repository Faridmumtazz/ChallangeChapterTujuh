package mumtaz.binar.challangechapterenam.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mumtaz.binar.challangechapterenam.model.GetAllFilm
import mumtaz.binar.challangechapterenam.model.GetAllFilmItem
import mumtaz.binar.challangechapterenam.network.ApiClient
import mumtaz.binar.challangechapterenam.network.ApiService
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelFilm @Inject constructor(api : ApiService): ViewModel(){

    private var liveDataFilm = MutableLiveData<List<GetAllFilmItem>>()

    val film : LiveData<List<GetAllFilmItem>> = liveDataFilm

    init {
        viewModelScope.launch {
            val datafilm = api.getAllFilm()
            delay(2000)
            liveDataFilm.value = datafilm
        }
    }




}