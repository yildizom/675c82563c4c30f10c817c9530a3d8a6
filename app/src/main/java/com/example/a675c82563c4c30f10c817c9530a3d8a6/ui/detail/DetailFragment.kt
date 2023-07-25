package com.example.a675c82563c4c30f10c817c9530a3d8a6.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.a675c82563c4c30f10c817c9530a3d8a6.R
import com.example.a675c82563c4c30f10c817c9530a3d8a6.databinding.FragmentDetailBinding
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.Position
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.Res
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.SatelliteDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment: Fragment() {

    @Inject
    lateinit var viewModel: DetailViewModel
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun getData() {
        arguments?.getInt(BUNDLE_KEY_SATELLITE_ID)?.let {
            observeData(it)
        } ?: findNavController().navigateUp()

        arguments?.getString(BUNDLE_KEY_SATELLITE_NAME)?.let {
            binding.tvName.text = it
        }
    }

    private fun observeData(it: Int) {
        lifecycleScope.launch {
            viewModel.retrieveDataById(it).collectLatest {
                if (it is Res.Success) {
                    onRetrievedData(it.data)
                }
            }

            viewModel.positions.collectLatest {
                it?.let { position ->
                    updatePositionInformation(position)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n") // No need for I18n
    private fun onRetrievedData(satelliteDetail: SatelliteDetail) {
        binding.tvDate.text = satelliteDetail.firstFlightString()
        binding.tvCostValue.text = satelliteDetail.costPerLaunch.toString()
        binding.tvHeightMassValue.text = "${satelliteDetail.height}/${satelliteDetail.mass}"
    }

    @SuppressLint("SetTextI18n") // No need for I18n
    private fun updatePositionInformation(position: Position?) {
        if (position == null) {
            binding.tvLastPositionValue.text = getString(R.string.not_found)
            return
        }
        binding.tvLastPositionValue.text = "(${position.posX},${position.posY})"
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val BUNDLE_KEY_SATELLITE_ID = "detail-satellite-id"
        const val BUNDLE_KEY_SATELLITE_NAME = "detail-satellite-name"
    }
}