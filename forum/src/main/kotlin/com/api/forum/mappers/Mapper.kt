package com.api.forum.mappers

interface Mapper<T, U> {
    fun map(t: T): U
}
