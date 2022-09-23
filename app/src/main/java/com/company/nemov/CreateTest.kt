package com.company.nemov

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.company.nemov.databinding.ActivityCreateTestBinding
import com.company.nemov.databinding.ActivityMainBinding
import com.google.firebase.database.*
import java.lang.Boolean.FALSE

class CreateTest : AppCompatActivity() {
    lateinit var binding: ActivityCreateTestBinding
    private var currentSub:String=""
    private var currentNumberQues:Int=0
    private var currentName:String=""
    private val listQuest:ArrayList<TestQuestion> = ArrayList<TestQuestion>()
    private var database:  DatabaseReference= FirebaseDatabase.getInstance().getReference()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createAccept.setOnClickListener {

            try {
                if(currentName.isEmpty() && currentNumberQues==0 && currentSub.isEmpty()){
                    Toast.makeText(this,"Пустые значения,Bведите заново",Toast.LENGTH_LONG).show()
                    binding.createSub.focusable= View.FOCUSABLE
                    binding.createNumberQuestion.focusable= View.FOCUSABLE
                    binding.createName.focusable= View.FOCUSABLE
                }
                else{
                    if(listQuest.size!=currentNumberQues){
                        Toast.makeText(this,"Недостаточное количество вопросов",Toast.LENGTH_LONG).show()
                    }
                    else{
                        val test = TestModel(currentSub,currentNumberQues,currentName,listQuest)
                        Log.d(TAG,"ссылка "+database)
                        database.child("Subject").child(currentSub).addListenerForSingleValueEvent(object :ValueEventListener{
                            override fun onDataChange(snapshot: DataSnapshot) {

                                if(snapshot.child(currentName).exists()){
                                    Toast.makeText(this@CreateTest,"Тест с таким именем уже есть",Toast.LENGTH_LONG).show()
                                    binding.createName.focusable= View.FOCUSABLE
                                }
                                else{
                                    database.child("Subject").child(currentSub).child(currentName).setValue(test)
                                    finish()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        })
                    }


                }


            }catch (ex:Exception){

            }
        }
        binding.createChooseSub.setOnClickListener {
            currentSub=binding.createSub.text.toString()
            binding.createSub.focusable= View.NOT_FOCUSABLE

            currentNumberQues=binding.createNumberQuestion.text.toString().toInt()
            binding.createNumberQuestion.focusable= View.NOT_FOCUSABLE

            currentName=binding.createName.text.toString()
            binding.createName.focusable= View.NOT_FOCUSABLE


        }
        binding.addQuestion.setOnClickListener{
            if(listQuest.size!=currentNumberQues){
                listQuest.add(TestQuestion(binding.createQuestion.text.toString(),binding.createAns.text.toString()))
                binding.createQuestion.text.clear()
                binding.createAns.text.clear()
                if (listQuest.size==currentNumberQues){
                    binding.addQuestion.visibility=View.INVISIBLE
                }
            }
        }

    }
}