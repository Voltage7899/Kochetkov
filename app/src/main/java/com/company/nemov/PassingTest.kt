package com.company.nemov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.company.nemov.databinding.ActivityMainBinding
import com.company.nemov.databinding.ActivityPassingTestBinding
import com.google.firebase.database.*

class PassingTest : AppCompatActivity() {
    lateinit var binding: ActivityPassingTestBinding
    var testQuest:ArrayList<TestQuestion> = ArrayList<TestQuestion>()
    private var currentQuestion:Int=1
    private var mark:Int=0
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Subject")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPassingTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val subj = intent.getStringExtra("subject2")
        val testName=intent.getStringExtra("test")

        getQuestions(subj.toString(),testName.toString())



        binding.passAccept.setOnClickListener {
            if (binding.passAnswer.text.toString()==testQuest[currentQuestion-1].answer){
                mark+=1
            }
            if(currentQuestion==testQuest.size){
                binding.passAccept.visibility=View.INVISIBLE
                binding.passMarkark.visibility= View.VISIBLE
                binding.passMarkark.setText(mark.toString() + " из " +currentQuestion)
            }
            else{
                currentQuestion+=1
                binding.passQuestion.setText(testQuest[currentQuestion-1].question)
                binding.passCurrentNumQuest.setText(currentQuestion.toString() + " из " +currentQuestion)
            }


        }


    }
    fun getQuestions(subj:String,testName:String){

        database.child(subj).child(testName).get().addOnSuccessListener {
            val testModel = it.getValue(TestModel::class.java)
            if (testModel != null) {
                testQuest = testModel.listTests
            }
            binding.passQuestion.setText(testQuest[currentQuestion-1].question)
            binding.passCurrentNumQuest.setText(currentQuestion.toString() +" из "+testQuest.size.toString())
        }

    }
}