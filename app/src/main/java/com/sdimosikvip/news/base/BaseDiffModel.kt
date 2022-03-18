package com.sdimosikvip.news.base

interface BaseDiffModel {
    val id: Long
    fun isIdEqual(other: BaseDiffModel): Boolean = id == other.id
}