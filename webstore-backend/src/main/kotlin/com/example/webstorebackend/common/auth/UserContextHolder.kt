package com.example.webstorebackend.common.auth


object UserContextHolder {
    private val currentUserId = ThreadLocal<Long?>()

    fun setUserId(id: Long?) = currentUserId.set(id)
    fun getUserId(): Long? = currentUserId.get()
    fun clear() = currentUserId.remove()
}
