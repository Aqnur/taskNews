package kz.news.app.ui.news.top_headlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.news.app.R
import kz.news.app.data.models.network.Status
import kz.news.app.databinding.FragmentTopHeadlinesBinding
import kz.news.app.ui.news.NewsAdapter
import kz.news.app.ui.news.everything.EverythingFragmentDirections
import kz.news.app.ui_common.base.BaseFragment
import kz.news.app.utils.PaginationCounter
import kz.news.app.utils.navigation.getSlideLeftAnimBuilder

class TopHeadlinesFragment : BaseFragment() {

    private lateinit var binding: FragmentTopHeadlinesBinding
    private lateinit var viewModel: TopHeadlinesViewModel
    private var isLoading = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_top_headlines, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = getViewModel(TopHeadlinesViewModel::class.java)

        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        binding.recyclerView.adapter = NewsAdapter(
            recyclerViewItemClickCallback = viewModel.recyclerViewItemClickCallback
        )

        binding.viewModel = viewModel

        pagination()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.topHeadlinesResource.observe(
            viewLifecycleOwner,
            Observer {
                when (it.status) {
                    Status.ERROR -> {
                        handleException(it.exception)
                    }
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        isLoading = false
                        viewModel.onSuccess(it.data)
                    }
                }
            }
        )

        viewModel.list.observe(
            viewLifecycleOwner,
            Observer {
                it.getContentIfNotHandled()?.let {
                    (binding.recyclerView.adapter as NewsAdapter).submitList(it)
                }
            }
        )

        viewModel.openDetails.observe(
            viewLifecycleOwner,
            Observer {
                it.getContentIfNotHandled()?.let {
                    val action =
                        TopHeadlinesFragmentDirections.actionTopHeadlinesFragmentToNavigationNewsDetails(
                            it
                        )
                    findNavController().navigate(
                        action,
                        getSlideLeftAnimBuilder().build()
                    )
                }
            }
        )
    }

    fun pagination() {

        binding.recyclerView.addOnScrollListener(object :
            PaginationCounter(binding.recyclerView.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                viewModel.setPage()
            }

            override fun isLastPage(): Boolean {
                return false
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

        })
    }
}