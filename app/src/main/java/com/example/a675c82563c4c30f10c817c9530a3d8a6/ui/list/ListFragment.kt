package com.example.a675c82563c4c30f10c817c9530a3d8a6.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a675c82563c4c30f10c817c9530a3d8a6.R
import com.example.a675c82563c4c30f10c817c9530a3d8a6.databinding.FragmentListBinding
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.Res
import com.example.a675c82563c4c30f10c817c9530a3d8a6.ui.detail.DetailFragment
import com.example.a675c82563c4c30f10c817c9530a3d8a6.ui.list.adapter.SatelliteDividerItemDecoration
import com.example.a675c82563c4c30f10c817c9530a3d8a6.ui.list.adapter.SatelliteListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment: Fragment() {

    @Inject
    lateinit var viewModel: ListViewModel
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SatelliteListAdapter { id, name ->
            navigateToDetailFragment(id, name)
        }
        initializeRecyclerView(adapter)
        initializeSearchView()
        observeData(adapter)
    }

    private fun observeData(adapter: SatelliteListAdapter) {
        lifecycleScope.launch {
            viewModel.items.collectLatest {
                if (it is Res.Success) {
                    adapter.setData(it.data)
                }
            }
        }
    }

    private fun initializeRecyclerView(listAdapter: SatelliteListAdapter) {
        binding.rv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter
            addItemDecoration(getDividerItemDecoration())
        }
    }

    private fun initializeSearchView() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                viewModel.getItems(p0 ?: "")
                return false
            }
        })
    }

    private fun navigateToDetailFragment(id: Int, name: String) {
        Bundle().apply {
            putInt(DetailFragment.BUNDLE_KEY_SATELLITE_ID, id)
            putString(DetailFragment.BUNDLE_KEY_SATELLITE_NAME, name)
            findNavController().navigate(R.id.action_listFragment_to_detailFragment, this)
        }
    }

    private fun getDividerItemDecoration(): SatelliteDividerItemDecoration {
        val c = requireContext()
        return SatelliteDividerItemDecoration(c, LinearLayoutManager.VERTICAL).apply {
            ContextCompat.getDrawable(c, R.drawable.item_decoration)?.let {
                setDrawable(it)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}