package com.company.nemov

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.nemov.databinding.TestElementBinding

class TestAdapter(val clickListener: ClickListener) : RecyclerView.Adapter<TestAdapter.ViewHolder>() {

    private var ListInAdapter = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_element, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TestAdapter.ViewHolder, position: Int) {
        holder.bind(ListInAdapter[position], clickListener)
    }

    override fun getItemCount(): Int {
        return ListInAdapter.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = TestElementBinding.bind(itemView)
        fun bind(testName: String, clickListener: ClickListener) {
            binding.elTest.text = testName


            itemView.setOnClickListener {

                clickListener.onClick(testName)
            }

        }
    }

    fun loadListToAdapter(testList: ArrayList<String>) {
        ListInAdapter = testList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(test: String) {

        }
    }


}