package mumtaz.binar.challangechapterenam.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import mumtaz.binar.challangechapterenam.adapter.AdapterFilm
import mumtaz.binar.challangechapterenam.viewmodel.ViewModelFilm
import mumtaz.binar.challangechaptertujuh.R



@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var adapterfilm: AdapterFilm
    lateinit var userManager: UserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        view.list.layoutManager = LinearLayoutManager(requireContext())
        adapterfilm = AdapterFilm(){
            val bund = Bundle()
            bund.putParcelable("detailfilm", it)
            view.findNavController().navigate(R.id.action_homeFragment_to_detailFragment,bund)

        }
        view.list.adapter = adapterfilm

        userManager = UserManager(requireContext())

        userManager.userUsername.asLiveData().observe(requireActivity()){
            welcome.text = "Hi $it"
        }

        getFilm()

        view.profile.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
        view.homelove.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }
        return view
    }

    fun getFilm(){

        val viewModel = ViewModelProvider(requireActivity()).get(ViewModelFilm::class.java)
        viewModel.film.observe(requireActivity()) {
            if(it != null){
                adapterfilm.setDataFilm(it)
                adapterfilm.notifyDataSetChanged()
            }

        }
    }

}