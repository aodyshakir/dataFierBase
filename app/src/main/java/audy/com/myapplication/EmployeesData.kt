package audy.com.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.*

class EmployeesData : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var employeelist :MutableList<Employees>
    lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employees_data)

        employeelist = mutableListOf()

        listView=findViewById(R.id.listView1)

        ref =FirebaseDatabase.getInstance().getReference("Employees")

        ref.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {

                if (p0!! .exists()){
                    employeelist.clear()
                    for (e in p0.children){
                        val employee= e.getValue(Employees ::class.java)
                        employeelist.add(employee!!)
                    }
                    val adapter =EmployeeAdapter(this@EmployeesData,R.layout.employees,employeelist)

                     listView.adapter=adapter

                }

            }

        })

    }
}
