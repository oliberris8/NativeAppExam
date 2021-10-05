package com.valhallaonlineservices.eventspass.nativeappexam.ui.displayposts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.valhallaonlineservices.eventspass.nativeappexam.R
import com.valhallaonlineservices.eventspass.nativeappexam.adapters.DisplayPostsAdapter
import com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.interfaces.IPostsInteractor
import com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.interfaces.IUsersInteractor
import com.valhallaonlineservices.eventspass.nativeappexam.mvvms.models.Posts
import com.valhallaonlineservices.eventspass.nativeappexam.enums.NotificationCenterType
import com.valhallaonlineservices.eventspass.nativeappexam.mvvms.viewmodels.DisplayPostsViewModel
import com.valhallaonlineservices.eventspass.nativeappexam.mvvms.views.DisplayPostsContract
import com.valhallaonlineservices.eventspass.nativeappexam.services.NotificationCenter
import com.valhallaonlineservices.eventspass.nativeappexam.singletons.PostsSingleton
import com.valhallaonlineservices.eventspass.nativeappexam.ui.displaycomments.DisplayCommentsActivity
import com.valhallaonlineservices.eventspass.nativeappexam.utilities.InternetConnectionChecker
import com.valhallaonlineservices.eventspass.nativeappexam.utilities.SnackbarDisplayHelper
import org.koin.android.ext.android.inject

class DisplayPostsActivity : AppCompatActivity(), DisplayPostsContract.DisplayPostsView,
    DisplayPostsAdapter.DisplayPostsAdapterListener {
    private var checkerInternetConnection: InternetConnectionChecker = InternetConnectionChecker(this)
    private var helperSnackbarDisplay: SnackbarDisplayHelper = SnackbarDisplayHelper(this)
    private lateinit var viewModelDisplayPosts: DisplayPostsViewModel
    private lateinit var ctlDisplayPostsScreen: ConstraintLayout
    private lateinit var lloDisplayPostsEmpty: LinearLayout
    private lateinit var rcvDisplayPosts: RecyclerView
    private lateinit var adpDisplayPosts:DisplayPostsAdapter
    private val iPostsInteractor: IPostsInteractor by inject()
    private val iUsersInteractor: IUsersInteractor by inject()

    private val receiverDisplayPosts: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val result = intent.getStringExtra(resources.getString(R.string.notification_center_message_key))

            if (result.equals(resources.getString(R.string.notification_center_retrieve_posts_successful))) {
                viewModelDisplayPosts.filterRetrievedPosts()
            } else if (result.equals(resources.getString(R.string.notification_center_retrieve_posts_failed))) {

            }
        }
    }

    private val receiverDisplayUsers: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val result = intent.getStringExtra(resources.getString(R.string.notification_center_message_key))

            if (result.equals(resources.getString(R.string.notification_center_retrieve_users_successful))) {
                viewModelDisplayPosts.continueRetrieval()
            } else if (result.equals(resources.getString(R.string.notification_center_retrieve_users_failed))) {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_posts)

        prepareViewModel()
        loadNotificationCenter()
        initializeUI()
    }

    private fun prepareViewModel() {
        viewModelDisplayPosts = DisplayPostsViewModel(this, iPostsInteractor, iUsersInteractor)
    }

    private fun loadNotificationCenter() {
        NotificationCenter.addObserver(this, NotificationCenterType.ForDisplayPosts, receiverDisplayPosts)
        NotificationCenter.addObserver(this, NotificationCenterType.ForDisplayPosts, receiverDisplayUsers)
    }

    private fun initializeUI() {
        ctlDisplayPostsScreen = findViewById(R.id.ctlDisplayPostsScreen)
        lloDisplayPostsEmpty = findViewById(R.id.lloDisplayPostsEmpty)
        rcvDisplayPosts = findViewById(R.id.rcvDisplayPosts)
        rcvDisplayPosts.layoutManager = LinearLayoutManager(this)
        rcvDisplayPosts.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    override fun onResume() {
        super.onResume()
        checkerInternetConnection.register()
        loadData()
    }

    private fun loadData() {
        viewModelDisplayPosts.retrieveUsers()
    }

    override fun onPause() {
        super.onPause()
        checkerInternetConnection.unregister()
    }

    override fun onDestroy() {
        super.onDestroy()
        NotificationCenter.removeObserver(this, receiverDisplayPosts)
        NotificationCenter.removeObserver(this, receiverDisplayUsers)
    }

    override fun retrieveUsersSuccessful() {
        viewModelDisplayPosts.retrievePosts()
    }

    override fun retrieveUsersFailed() {
        helperSnackbarDisplay.displaySnackbar(ctlDisplayPostsScreen,
            resources.getString(R.string.snackbar_message_failed_to_retrieve_users))
    }

    override fun retrievePostsSuccessful(dataSet: List<Posts>) {
        if (dataSet.size == 0) {
            lloDisplayPostsEmpty.visibility = View.VISIBLE
            rcvDisplayPosts.visibility = View.INVISIBLE
        } else {
            adpDisplayPosts = DisplayPostsAdapter(this, dataSet)
            rcvDisplayPosts.adapter = adpDisplayPosts
            viewModelDisplayPosts.showPosts()
            adpDisplayPosts.adapterListener = this
        }
    }

    override fun retrievePostsFailed() {
        helperSnackbarDisplay.displaySnackbar(ctlDisplayPostsScreen,
            resources.getString(R.string.snackbar_message_failed_to_retrieve_posts))
    }

    override fun displayPosts() {
        rcvDisplayPosts.invalidate()
    }

    override fun onPostClicked(postId: Int) {
        PostsSingleton.selectedPostId = postId
        val intentLoadDisplayCommentsActivity = Intent(
            this, DisplayCommentsActivity::class.java)
        startActivity(intentLoadDisplayCommentsActivity)
    }
}
