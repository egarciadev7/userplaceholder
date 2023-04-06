package com.test.placeholderusers.users.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.entities.User
import com.test.placeholderusers.databinding.FragmentUserDetailBinding
import com.test.placeholderusers.users.adapters.PostListAdapter
import com.test.placeholderusers.users.viewmodels.UserDetailViewModel
import com.test.placeholderusers.utils.getSerializableCompat
import com.test.placeholderusers.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment() {
    private lateinit var binding: FragmentUserDetailBinding
    private val viewModel: UserDetailViewModel by viewModels()
    private var user: User? = null
    private val adapter by lazy {
        PostListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getSerializableCompat(USER_DETAIL, User::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        initView()
        initObservers()
        return binding.root
    }

    private fun initView() {
        user?.let {
            binding.name.text = it.name
            binding.phone.text = it.phone
            binding.email.text = it.email
            binding.postsList.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.postsList.adapter = adapter
            viewModel.getUserPostsList(it.id)
        }
    }

    private fun initObservers() {
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.progressLoader.show(it)
        }

        viewModel.posts.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {
        const val USER_DETAIL = "USER_DETAIL"
    }
}
