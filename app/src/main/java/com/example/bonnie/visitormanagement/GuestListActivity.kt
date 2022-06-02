package com.example.bonnie.visitormanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class GuestListActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var recyclerview:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_list)

        database= FirebaseDatabase.getInstance()
        reference=database.getReference("Scanned")

        getData()

    }

    private fun getData(){
        reference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var list=ArrayList<Visitors>()
                for (data in snapshot.children){
                    var model=data.getValue(Visitors::class.java)
                    list.add(model as Visitors)
                }
                if (list.size>0){
                    var adapter = DataAdapter(list)
                    recyclerview.adapter = adapter
                }


            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("cancel",error.toString())
            }

        })
    }
}