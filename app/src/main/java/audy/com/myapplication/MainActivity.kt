package audy.com.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

      lateinit var editText :EditText
      lateinit var editText1 :EditText
      lateinit var editText2 :EditText
      lateinit var editText3 :EditText

    lateinit var but :Button
    lateinit var but2 :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText =findViewById(R.id.firstname)
        editText1=findViewById(R.id.lastname)
        editText2=findViewById(R.id.address)
        editText3=findViewById(R.id.dept)

        but=findViewById(R.id.saveBtn)
        but2=findViewById(R.id.databaseBtn)

        but.setOnClickListener {
            saveData()
        }
        but2.setOnClickListener {
            val intent =Intent(this@MainActivity,EmployeesData ::class.java)
            startActivity(intent)
        }
    }
       private fun saveData(){
           val firstname = editText.text.toString().trim()
           val lastname = editText1.text.toString().trim()
           val address= editText2.text.toString().trim()
           val department= editText3.text.toString().trim()

           if (firstname.isEmpty()){
               editText.error ="please inter your firstname "
               return
           }
           if (lastname.isEmpty()){
               editText.error ="please inter your lastname "
               return

           }
            if (address.isEmpty()){
               editText.error ="please inter your address "
               return
           }
           if (department.isEmpty()){
               editText.error ="please inter your department "
               return
           }
           val MyDatabase= FirebaseDatabase.getInstance().getReference("Employees")
           val employeeId=MyDatabase.push().key
           val employee =Employees(employeeId,firstname,lastname,address,department)

           MyDatabase.child(employeeId).setValue(employee).addOnCompleteListener {
               Toast.makeText(this,"Save :)", Toast.LENGTH_LONG).show()
           }
        
      }
}
