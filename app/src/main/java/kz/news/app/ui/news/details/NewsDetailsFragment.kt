package kz.news.app.ui.news.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.news.app.R
import kz.news.app.databinding.FragmentNewsDetailsBinding
import kz.news.app.databinding.FragmentTopHeadlinesBinding
import kz.news.app.ui.news.NewsAdapter
import kz.news.app.ui.news.top_headlines.TopHeadlinesViewModel
import kz.news.app.ui_common.base.BaseFragment

class NewsDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentNewsDetailsBinding
    private lateinit var viewModel: NewsDetailsViewModel

    private val args: NewsDetailsFragmentArgs by navArgs()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news_details, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = getViewModel(NewsDetailsViewModel::class.java)
        viewModel.setArgs(args.article)
        binding.viewModel = viewModel

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.toastMessage.observe(
            viewLifecycleOwner,
            Observer {
                it.getContentIfNotHandled()?.let {
                    Toast.makeText(requireContext(), "Добавлен в избранные", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}