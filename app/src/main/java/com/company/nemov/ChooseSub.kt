package com.company.nemov

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.nemov.databinding.ActivityChooseSubBinding
import com.google.firebase.database.*

class ChooseSub : AppCompatActivity(),SubjAdapter.ClickListener {
    lateinit var binding: ActivityChooseSubBinding
    var ListAdapter: SubjAdapter? = null
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Subject")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseSubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.subRec.layoutManager= LinearLayoutManager(this)
        ListAdapter= SubjAdapter(this)
        binding.subRec.adapter=ListAdapter
        ListAdapter?.loadListToAdapter(getList())
    }
    fun getList():ArrayList<String>{



        val commonList=ArrayList<String>()
        database.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(i in snapshot.children){
                    commonList.add(i.key.toString())

                }
                ListAdapter?.loadListToAdapter(commonList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return commonList
    }

    override fun onClick(subj: String) {
        super.onClick(subj)
        val intent=Intent(this,ChooseTest::class.java)
        intent.putExtra("subject",subj)
        startActivity(intent)
    }
}