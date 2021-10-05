package com.valhallaonlineservices.eventspass.nativeappexam.di

import androidx.room.Room
import com.valhallaonlineservices.eventspass.nativeappexam.constants.DatabaseRoomConstants
import com.valhallaonlineservices.eventspass.nativeappexam.data.db.database.AppDatabase
import com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.implementations.CommentsInteractor
import com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.implementations.PostsInteractor
import com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.implementations.UsersInteractor
import com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.interfaces.ICommentsInteractor
import com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.interfaces.IPostsInteractor
import com.valhallaonlineservices.eventspass.nativeappexam.data.interactors.interfaces.IUsersInteractor
import com.valhallaonlineservices.eventspass.nativeappexam.data.repositories.implementations.CommentsRepository
import com.valhallaonlineservices.eventspass.nativeappexam.data.repositories.implementations.PostsRepository
import com.valhallaonlineservices.eventspass.nativeappexam.data.repositories.implementations.UsersRepository
import com.valhallaonlineservices.eventspass.nativeappexam.data.repositories.interfaces.ICommentsRepository
import com.valhallaonlineservices.eventspass.nativeappexam.data.repositories.interfaces.IPostsRepository
import com.valhallaonlineservices.eventspass.nativeappexam.data.repositories.interfaces.IUsersRepository
import com.valhallaonlineservices.eventspass.nativeappexam.mvvms.viewmodels.DisplayCommentsViewModel
import com.valhallaonlineservices.eventspass.nativeappexam.mvvms.viewmodels.DisplayPostsViewModel
import com.valhallaonlineservices.eventspass.nativeappexam.mvvms.views.DisplayCommentsContract
import com.valhallaonlineservices.eventspass.nativeappexam.mvvms.views.DisplayPostsContract
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java,
            DatabaseRoomConstants.DATABASE_NAME).build()
    }

    single { get<AppDatabase>().CommentsDao() }

    single { get<AppDatabase>().PostsDao() }

    single { get<AppDatabase>().UsersDao() }

    single<ICommentsRepository> { CommentsRepository(get()) }

    single<IPostsRepository> { PostsRepository(get()) }

    single<IUsersRepository> { UsersRepository(get()) }

    single<ICommentsInteractor> { CommentsInteractor(get()) }

    single<IPostsInteractor> { PostsInteractor(get()) }

    single<IUsersInteractor> { UsersInteractor(get()) }

    factory<DisplayCommentsContract.DisplayCommentsViewModel> {
        (view: DisplayCommentsContract.DisplayCommentsView) ->
        DisplayCommentsViewModel(view, iCommentsInteractor = get())
    }

    factory<DisplayPostsContract.DisplayPostsViewModel> {
        (view: DisplayPostsContract.DisplayPostsView) ->
        DisplayPostsViewModel(view, iPostsInteractor = get(), iUsersInteractor = get())
    }
}
