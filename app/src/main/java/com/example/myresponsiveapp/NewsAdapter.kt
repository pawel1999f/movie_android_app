package com.example.myresponsiveapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myresponsiveapp.databinding.NewsItemBinding
import com.example.myresponsiveapp.model.News

class NewsAdapter(private val dataSet: Array<News>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.title.text = news.title
            binding.secondaryText.text = news.author
            binding.supportingText.text = news.text
        }

//        init {
//            // Define click listener for the ViewHolder's View.
//            textView = view.findViewById(R.id.textView)
//        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
//        val view = LayoutInflater.from(viewGroup.context)
//            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(NewsItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        viewHolder.textView.text = dataSet[position]
        viewHolder.bind(dataSet[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}