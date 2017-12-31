package audy.com.myapplication

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import java.sql.RowId


/**
 * Created by Lenovo on 11/15/2017.
 */
   class EmployeeAdapter(val mCts:Context ,val layoutId: Int ,val employeeList: List<Employees>):ArrayAdapter<Employees>(mCts,layoutId,employeeList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val LayoutInflater : LayoutInflater = LayoutInflater.from(mCts)
        val view:View =LayoutInflater.inflate(layoutId,null)
        val firstname = view.findViewById<TextView>(R.id.firstnameText)
        val lastname = view.findViewById<TextView>(R.id.lastnameText)
        val address = view.findViewById<TextView>(R.id.addresstext)
        val depatment = view.findViewById<TextView>(R.id.depttext)

        val updateBtn = view.findViewById<TextView>(R.id.updata)
        val deleteBtn = view.findViewById<TextView>(R.id.delete)

        val employee= employeeList[position]

        firstname.text=employee.firstname
        lastname.text=employee.lastname
        address.text=employee.addrees
        depatment.text=employee.depatment

        updateBtn.setOnClickListener {
            updataInfo(employee)
        }

        deleteBtn.setOnClickListener {
            deleteInfo(employee)
        }


        return view
    }
   private  fun  updataInfo(employee :Employees){

        val bulider =AlertDialog.Builder(mCts)
        bulider.setTitle("Updata Info")
        val infater=LayoutInflater.from(mCts)
        val view =infater.inflate(R.layout.employee_updata,null)

        val firstname = view.findViewById<TextView>(R.id.firstnameUpdata)
        val lastname = view.findViewById<TextView>(R.id.lastnameUpdata)
        val address = view.findViewById<TextView>(R.id.addressUpdata)
        val depatment = view.findViewById<TextView>(R.id.departmentUpdata)

        firstname.setText(employee.firstname)
        lastname.setText(employee.lastname)
        address.setText(employee.addrees)
        depatment.setText(employee.depatment)

        bulider.setView(view)
       bulider.setPositiveButton("Update",object :DialogInterface.OnClickListener{
           override fun onClick(dialog: DialogInterface?, which: Int) {

               val  myDatabase = FirebaseDatabase.getInstance().getReference("Employees")

               val firstname1    = firstname.text.toString().trim()
               val lastname2     = lastname.text.toString().trim()
               val address3     = address.text.toString().trim()
               val department4   = depatment.text.toString().trim()

               if (firstname1.isEmpty()){
                   firstname.error = "Please enter your firstname"
                   return
               }
               if (lastname2.isEmpty()){
                   lastname.error = "Please enter your lastname"
                   return
               }
               if (address3.isEmpty()){
                   address.error = "Please enter your address"
                   return
               }
               if (department4.isEmpty()){
                   depatment.error = "Please enter your department"
                   return
               }

               val employee = Employees(employee.id,firstname1,lastname2,address3,department4)
               myDatabase.child(employee.id).setValue(employee)
               Toast.makeText(mCts,"Updated :) ", Toast.LENGTH_LONG).show()



           }})

       bulider.setNegativeButton("cancel",object :DialogInterface.OnClickListener{
           override fun onClick(dialog: DialogInterface?, which: Int) {

           }

       })

       val alert = bulider.create()
       alert.show()

   }
    private fun deleteInfo(employee:Employees){
        val myDatabase = FirebaseDatabase.getInstance().getReference("Employees")
        myDatabase.child(employee.id).removeValue()
        Toast.makeText(mCts,"Deleted !",Toast.LENGTH_LONG).show()
    }




}







