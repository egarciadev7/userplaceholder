package com.test.placeholderusers.users.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.entities.User
import com.test.placeholderusers.databinding.FragmentUserListBinding
import com.test.placeholderusers.users.adapters.UserListAdapter
import com.test.placeholderusers.users.viewmodels.UserListViewModel
import com.test.placeholderusers.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {
    private lateinit var binding: FragmentUserListBinding
    private val viewModel: UserListViewModel by viewModels()
    private val adapter by lazy {
        UserListAdapter(object : UserListAdapter.UserListAdapterListener {
            override fun onUserDetail(user: User) {

            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        initView()
        initObservers()
        getUserList()
        return binding.root
    }

    private fun initView() {
        binding.userList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.userList.adapter = adapter
    }

    private fun initObservers() {

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.loader.show(it)
        }

        viewModel.users.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun getUserList() {
        viewModel.getUserList()
    }
}
