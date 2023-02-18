package com.example.masirgithubusers.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masirgithubusers.network.GitApi
import com.example.masirgithubusers.model.GitUser
import com.example.masirgithubusers.model.UserModel
import kotlinx.coroutines.launch

private const val TAG = "GitUserViewModel"

enum class GitApiStatus { LOADING, ERROR, DONE }

class MainViewModel: ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<GitApiStatus>()
    // The external immutable LiveData for the request status
    val status: LiveData<GitApiStatus> = _status

    private val _gitUsers = MutableLiveData<List<GitUser>>()
    val gitUsers: LiveData<List<GitUser>> = _gitUsers
    private val _gitUser = MutableLiveData<UserModel>()
    var gitUser: LiveData<UserModel> = _gitUser
    private var _gitUsername = MutableLiveData<String> () //selected user for show detail
    val gitUsername: LiveData<String> = _gitUsername
    private val _followers = MutableLiveData<List<GitUser>>()
    val followers: LiveData<List<GitUser>> = _followers
    private val _following = MutableLiveData<List<GitUser>>()
    val following: LiveData<List<GitUser>> = _following

    init {
        getGitUserList() // setup list at entrance
    }

    fun getGitUserList() {  // all users, first screen
        viewModelScope.launch {
            _status.value = GitApiStatus.LOADING
            try {
                _gitUsers.value = GitApi.retrofitService.getUserList()
                Log.e(TAG, "getGitUserList: test ${gitUsers.value!!.get(0).username}", )
                _status.value = GitApiStatus.DONE
            } catch (e: Exception) {
                _status.value = GitApiStatus.ERROR
                _gitUsers.value = listOf()
            }
        }
    }

    fun getSearchUsers(q: String?) { // when searching
        if (q != null) {
            viewModelScope.launch {
                _status.value = GitApiStatus.LOADING
                try {
                    _gitUsers.value = GitApi.retrofitService.getSearchUsers(q).items
                    _status.value = GitApiStatus.DONE
                } catch (e: Exception) {
                    _status.value = GitApiStatus.ERROR
                    _gitUsers.value = listOf()
                }
            }
        }
    }

    fun getUserDetail(username: String?) { // when selecting a user
        if (username != null) {
            viewModelScope.launch {
                _status.value = GitApiStatus.LOADING
                try {
                    _gitUser.value = GitApi.retrofitService.getUser(username)
                    _status.value = GitApiStatus.DONE
                } catch (e: Exception) {
                    _status.value = GitApiStatus.ERROR
                }
            }
        }
    }

    fun getFollowers(username: String?) { // get a user followers
        if (username != null) {
            viewModelScope.launch {
                _status.value = GitApiStatus.LOADING
                try {
                    _followers.value = GitApi.retrofitService.getFollower(username)
                    _status.value = GitApiStatus.DONE
                } catch (e: Exception) {
                    _status.value = GitApiStatus.ERROR
                }
            }
        }
    }

    fun getFollowing(username: String?) { // get a user following
        if (username != null) {
            viewModelScope.launch {
                _status.value = GitApiStatus.LOADING
                try {
                    Log.e(TAG, "getFollowing: test 1", )
                    _following.value = GitApi.retrofitService.getFollowing(username)
                    Log.e(TAG, "getFollowing: test ${following.value!![0].avatarUrl}", )
                    _status.value = GitApiStatus.DONE
                } catch (e: Exception) {
                    Log.e(TAG, "getFollowing: test 3", )
                    _status.value = GitApiStatus.ERROR
                }
            }
        }
    }

    // setting user username when it does selected on the list
    fun setGitUserValue(gitUser: String){
        _gitUsername.value = gitUser
    }

}