package com.example.composecoursety.loadmorelist

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}