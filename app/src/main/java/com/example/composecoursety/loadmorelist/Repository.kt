package com.example.composecoursety.loadmorelist

import kotlinx.coroutines.delay


class Repository {
    private val remoteDataSource = (0..100).map {
       ListItem(
           title = "Item $it",
           description = "Description $it"
       )
    }

    suspend fun getItems(page: Int, pageSize: Int): Result<List<ListItem>> {
        delay(2000)
        val startingIndex = page * pageSize
        return if ((startingIndex + pageSize) < remoteDataSource.size) {
            Result.success(
                remoteDataSource.slice(startingIndex until startingIndex + pageSize)
            )
        } else Result.success(emptyList())
    }
}