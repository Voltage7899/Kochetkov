package com.company.nemov

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.nemov.databinding.SubElementBinding

class SubjAdapter (val clickListener: ClickListener) : RecyclerView.Adapter<SubjAdapter.ViewHolder>() {

    private var ListInAdapter = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sub_element, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjAdapter.ViewHolder, position: Int) {
        holder.bind(ListInAdapter[position], clickListener)
    }

    override fun getItemCount(): Int {
        return ListInAdapter.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SubElementBinding.bind(itemView)
        fun bind(subj: String, clickListener: ClickListener) {
            binding.elSubject.text = subj

            itemView.setOnClickListener {

                clickListener.onClick(subj)
            }

        }
    }

    fun loadListToAdapter(subj: ArrayList<String>) {
        ListInAdapter = subj
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(subj: String) {

        }
    }

}