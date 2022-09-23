package com.company.nemov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.nemov.databinding.ActivityChooseSubBinding
import com.company.nemov.databinding.ActivityChooseTestBinding
import com.google.firebase.database.*

class ChooseTest : AppCompatActivity(),TestAdapter.ClickListener {
    lateinit var binding: ActivityChooseTestBinding
    var ListAdapter: TestAdapter? = null
    private var subject:String=""
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Subject")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subject= intent.getStringExtra("subject").toString()

        binding.testRec.layoutManager= LinearLayoutManager(this)
        ListAdapter= TestAdapter(this)
        binding.testRec.adapter=ListAdapter
        ListAdapter?.loadListToAdapter(getList(subject.toString()))
    }
    fun getList(subject:String):ArrayList<String>{



        val commonList=ArrayList<String>()
        database.child(subject).addListenerForSingleValueEvent(object : ValueEventListener {
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

    override fun onClick(test: String) {
        super.onClick(test)
        val intent=Intent(this,PassingTest::class.java)
        intent.putExtra("subject2",subject)
        intent.putExtra("test",test)
        startActivity(intent)
    }
}