package com.sdimosikvip.news.base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtilItemCallback : DiffUtil.ItemCallback<BaseDiffModel>() {
    override fun areItemsTheSame(oldItem: BaseDiffModel, newItem: BaseDiffModel): Boolean {
        return oldItem.isIdEqual(newItem)
    }

    override fun areContentsTheSame(oldItem: BaseDiffModel, newItem: BaseDiffModel): Boolean {
        return oldItem.equals(newItem)
    }
}