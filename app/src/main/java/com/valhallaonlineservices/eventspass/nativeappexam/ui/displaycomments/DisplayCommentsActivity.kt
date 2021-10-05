package com.valhallaonlineservices.eventspass.nativeappexam.ui.displaycomments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.valhallaonlineservices.eventspass.nativeappexam.R
import com.valhallaonlineservices.eventspass.nativeappexam.adapters.DisplayCommentsAdapter
import com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.interfaces.ICommentsInteractor
import com.valhallaonlineservices.eventspass.nativeappexam.mvvms.models.Comments
import com.valhallaonlineservices.eventspass.nativeappexam.enums.NotificationCenterType
import com.valhallaonlineservices.eventspass.nativeappexam.mvvms.viewmodels.DisplayCommentsViewModel
import com.valhallaonlineservices.eventspass.nativeappexam.mvvms.views.DisplayCommentsContract
import com.valhallaonlineservices.eventspass.nativeappexam.services.NotificationCenter
import com.valhallaonlineservices.eventspass.nativeappexam.utilities.InternetConnectionChecker
import com.valhallaonlineservices.eventspass.nativeappexam.utilities.SnackbarDisplayHelper
import org.koin.android.ext.android.inject

class DisplayCommentsActivity : AppCompatActivity(), DisplayCommentsContract.DisplayCommentsView {
    private var internetConnectionChecker: InternetConnectionChecker = InternetConnectionChecker(this)
    private var helperSnackbarDisplay: SnackbarDisplayHelper = SnackbarDisplayHelper(this)
    private lateinit var viewModelDisplayComments: DisplayCommentsViewModel
    private lateinit var ctlDisplayCommentsScreen: ConstraintLayout
    private lateinit var lloDisplayCommentsEmpty: LinearLayout
    private lateinit var rcvDisplayComments: RecyclerView
    private lateinit var adpDisplayComments: DisplayCommentsAdapter
    private val iCommentsInteractor: ICommentsInteractor by inject()

    private val receiverDisplayComments: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val result = intent.getStringExtra(resources.getString(R.string.notification_center_message_key))

            if (result.equals(resources.getString(R.string.notification_center_retrieve_comments_successful))) {
                viewModelDisplayComments.filterRetrievedComments()
            } else if (result.equals(resources.getString(R.string.notification_center_retrieve_comments_failed))) {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_comments)

        prepareViewModel()
        loadNotificationCenter()
        initializeUI()
    }

    private fun prepareViewModel() {
        viewModelDisplayComments = DisplayCommentsViewModel(this, iCommentsInteractor)
    }

    private fun loadNotificationCenter() {
        NotificationCenter.addObserver(this, NotificationCenterType.ForDisplayComments, receiverDisplayComments)
    }

    private fun initializeUI() {
        ctlDisplayCommentsScreen = findViewById(R.id.ctlDisplayCommentsScreen)
        lloDisplayCommentsEmpty = findViewById(R.id.lloDisplayCommentsEmpty)
        rcvDisplayComments = findViewById(R.id.rcvDisplayComments)
        rcvDisplayComments.layoutManager = LinearLayoutManager(this)
        rcvDisplayComments.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    override fun onResume() {
        super.onResume()
        internetConnectionChecker.register()
        loadData()
    }

    private fun loadData() {
        viewModelDisplayComments.retrieveComments()
    }

    override fun onPause() {
        super.onPause()
        internetConnectionChecker.unregister()
    }

    override fun onDestroy() {
        super.onDestroy()
        NotificationCenter.removeObserver(this, receiverDisplayComments)
    }

    override fun retrieveCommentsSuccessful(dataSet: List<Comments>) {
        if (dataSet.size == 0) {
            lloDisplayCommentsEmpty.visibility = View.VISIBLE
            rcvDisplayComments.visibility = View.INVISIBLE
        } else {
            adpDisplayComments = DisplayCommentsAdapter(this, dataSet)
            rcvDisplayComments.adapter = adpDisplayComments
            viewModelDisplayComments.showComments()
        }
    }

    override fun retrieveCommentsFailed() {
        helperSnackbarDisplay.displaySnackbar(ctlDisplayCommentsScreen,
            resources.getString(R.string.snackbar_message_failed_to_retrieve_comments))
    }

    override fun displayComments() {
        rcvDisplayComments.invalidate()
    }

}
