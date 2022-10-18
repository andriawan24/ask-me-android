package com.andriawan.askme.features.home.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andriawan.askme.R
import com.andriawan.askme.databinding.ViewCardTopicBinding
import com.andriawan.askme.domain.models.TopicModel
import com.andriawan.askme.utils.recyclerview.RecyclerDiffUtil

class TopicsAdapter : RecyclerView.Adapter<TopicsAdapter.ViewHolder>() {

    private var topics = emptyList<TopicModel>()

    class ViewHolder(
        private val binding: ViewCardTopicBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(topic: TopicModel) {
            binding.topicImageImageView.contentDescription = topic.description
            binding.topicImageImageView.setImageResource(R.drawable.ic_launcher_foreground)
            binding.topicTitleTextView.text = topic.name
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = ViewCardTopicBinding.inflate(LayoutInflater.from(parent.context))
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topic = topics[position]
        holder.bind(topic)
    }

    override fun getItemCount(): Int = topics.size

    fun setData(newData: List<TopicModel>) {
        val diffUtil = RecyclerDiffUtil(topics, newData)
        topics = newData
        val result = DiffUtil.calculateDiff(diffUtil)
        result.dispatchUpdatesTo(this)
    }
}