package com.evourse.instaclone

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.evourse.instaclone.databinding.FrHomeBinding
import com.evourse.instaclone.paging.LoadStateFeedAdapter
import com.evourse.instaclone.viewModel.HomeFeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FrHome : Fragment(R.layout.fr_home) {
    private lateinit var binding: FrHomeBinding

    private val homeFeedViewModel: HomeFeedViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FrHomeBinding.bind(view)

        val homeFeedRecycler = binding.imgPostsRecycler
        val homeFeedAdapter = FeedAdapter()

        homeFeedRecycler.layoutManager = LinearLayoutManager(requireContext())
//        homeFeedRecycler.setHasFixedSize(true)
        homeFeedRecycler.adapter = homeFeedAdapter
            .withLoadStateHeaderAndFooter(
                header = LoadStateFeedAdapter(),
                footer = LoadStateFeedAdapter()
            )


        homeFeedViewModel.mixContentList.observe(viewLifecycleOwner) { pagingData ->
            viewLifecycleOwner.lifecycleScope.launch {
                homeFeedAdapter.submitData(pagingData)
            }
        }

        //gs
    }//gc

}