package com.valhallaonlineservices.eventspass.nativeappexam.mvvms.viewmodels

import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.PostsEntity
import com.valhallaonlineservices.eventspass.nativeappexam.data.db.entities.UsersEntity
import com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.interfaces.IPostsInteractor
import com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.interfaces.IUsersInteractor
import com.valhallaonlineservices.eventspass.nativeappexam.mvvms.models.Posts
import com.valhallaonlineservices.eventspass.nativeappexam.mvvms.views.DisplayPostsContract
import com.valhallaonlineservices.eventspass.nativeappexam.singletons.PostsSingleton
import com.valhallaonlineservices.eventspass.nativeappexam.singletons.UsersSingleton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DisplayPostsViewModel (private var viewDisplayPosts: DisplayPostsContract.DisplayPostsView?,
    private var iPostsInteractor: IPostsInteractor, private var iUsersInteractor: IUsersInteractor) :
    DisplayPostsContract.DisplayPostsViewModel {
    override fun retrieveUsers() {
        iUsersInteractor.retrieveUsersViaApi()
        /*val callUsersList: Call<List<UsersResponse>> = RetrofitClient.getClientForUsers.retrieveUsers()
        callUsersList.enqueue(object: Callback<List<UsersResponse>> {
            override fun onResponse(call: Call<List<UsersResponse>>, response: Response<List<UsersResponse>>) {
                if (response.isSuccessful) {
                    UsersSingleton.users = response.body() as List<UsersResponse>
                    if (viewDisplayPosts != null) {
                        viewDisplayPosts!!.retrieveUsersSuccessful()
                    }
                } else {
                    if (viewDisplayPosts != null) {
                        viewDisplayPosts!!.retrieveUsersFailed()
                    }
                }
            }

            override fun onFailure(call: Call<List<UsersResponse>>, t: Throwable) {
                if (viewDisplayPosts != null) {
                    viewDisplayPosts!!.retrievePostsFailed()
                }
            }
        })*/
    }

    override fun retrievePosts() {
        iPostsInteractor.retrievePostsViaApi()
        /*val callPostsList: Call<List<PostsResponse>> = RetrofitClient.getClientForPosts.retrievePosts()
        callPostsList.enqueue(object: Callback<List<PostsResponse>> {
            override fun onResponse(call: Call<List<PostsResponse>>, response: Response<List<PostsResponse>>) {
                if (response.isSuccessful) {
                    PostsSingleton.posts = response.body() as List<PostsResponse>
                    val filteredPosts: List<Posts> = filterRetrievedPosts()
                    if (viewDisplayPosts != null) {
                        viewDisplayPosts!!.retrievePostsSuccessful(filteredPosts)
                    }
                } else {
                    if (viewDisplayPosts != null) {
                        viewDisplayPosts!!.retrievePostsFailed()
                    }
                }
            }

            override fun onFailure(call: Call<List<PostsResponse>>, t: Throwable) {
                if (viewDisplayPosts != null) {
                    viewDisplayPosts!!.retrievePostsFailed()
                }
            }
        })*/
    }

    override fun showPosts() {
        if (viewDisplayPosts != null) {
            viewDisplayPosts!!.displayPosts()
        }
    }

    override fun continueRetrieval() {
        insertUsersFromResponseToDatabase()
        if (viewDisplayPosts != null) {
            viewDisplayPosts!!.retrieveUsersSuccessful()
        }
    }

    override fun filterRetrievedPosts() {
        val filteredDataSet: ArrayList<Posts> = ArrayList()

        for (post in PostsSingleton.posts) {
            val user = UsersSingleton.users.find{ it.id == post.userId }!!.name
            val postId = post.id
            val username = if (user.isNotEmpty()) user else ""
            val title = post.title
            val body = post.body
            val modelDisplayPosts = Posts(postId, username, title, body)
            filteredDataSet.add(modelDisplayPosts)
        }
        insertPostsFromResponseToDatabase()

        if (viewDisplayPosts != null) {
            viewDisplayPosts!!.retrievePostsSuccessful(filteredDataSet.toList())
        }
    }

    private fun insertUsersFromResponseToDatabase() {
        var users: ArrayList<UsersEntity> = ArrayList()
        for (user in UsersSingleton.users) {
            val retrievedUser = UsersEntity(user.id, user.name, user.username, user.email,
                user.address.street, user.address.suite, user.address.city, user.address.zipcode,
                user.address.geo.lat, user.address.geo.lng, user.phone, user.website,
                user.company.name, user.company.catchPhrase, user.company.bs)
            users.add(retrievedUser)
        }
        if (users.size > 0) {
            GlobalScope.launch {
                iUsersInteractor.insertUsers(users)
            }
        }
    }

    private fun insertPostsFromResponseToDatabase() {
        var posts: ArrayList<PostsEntity> = ArrayList()
        for (post in PostsSingleton.posts) {
            val retrievedPost = PostsEntity(post.userId, post.id, post.title, post.body)
            posts.add(retrievedPost)
        }

        if (posts.size > 0) {
            GlobalScope.launch {
                iPostsInteractor.insertPosts(posts)
            }
        }
    }
}
