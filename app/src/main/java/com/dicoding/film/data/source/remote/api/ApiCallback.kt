package com.dicoding.film.data.source.remote.api

interface ApiCallback<T> {
    fun onCallSuccess(value:T)
    fun onCallError(throwable: Throwable)
}