package com.example.jsonparser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.jsonparser.model.Employee
import com.example.jsonparser.model.Sources
import com.example.jsonparser.model.Student
import com.example.jsonparser.model.Website
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val student1 = Student("Prakash" , "India")
        //To convert single hierarchical class object into equivalent json string you simply need to call Gson().toJson()
        val jsonString = Gson().toJson(student1) //json string
        Log.i("MyTag object to kotlin", jsonString)

        val json1 = """{
            "key_name" : "Rajesh",
            "key_address" : "New Delhi"
           }"""
        val student2 : Student = Gson().fromJson(json1,Student::class.java)
        Log.i("MyTag json to object", student2.toString())

        //To convert nested class object into equivalent json string
        val json2 = """{
            "id" = 123,
            "info": {
                "key_name" : "Ram",
                "key_address" : "Mumbai"
            }
          }"""
        val employee1 = Gson().fromJson<Employee>(json2,Employee::class.java)
        Log.i("MyTag nested json", employee1.toString())
        Log.i("MyTag nested json", employee1.info?.name.toString())

        //Using example.json from Assets folder for parsing
        val json3: String = applicationContext.assets.open("First.json").bufferedReader().use {it.readText()}
        Log.i("MyTag1", json3)

        val exm = Gson().fromJson<Website>(json3,Website::class.java)
        Log.i("MyTag2", exm.sources[16].toString())

        var list = ArrayList<Sources>()
        list = exm.sources as ArrayList<Sources>

        var list1  = ArrayList<String?>()

        for(i in list)
        {
            list1.add(i.name)

        }


        var adapter = ArrayAdapter<String> (this,android.R.layout.simple_list_item_1, list1)
        list_view.adapter = adapter


        //parsing to and from an array or a list automatically into json object and vice versa
        var exlist = listOf<String>("Prakash", "Rajesh", "Mohit", "Shiva", "Varun")
        val json4 = Gson().toJson(exlist)
        Log.i("MyTag list", json4)

        val sType = object : TypeToken<List<String>>() { }.type
        val otherList = Gson().fromJson<List<String>>(json4,sType)
        Log.i("MyTag3", otherList.toString())


        //parsing to and from an array or a list of objects automatically into json object and vice versa
        var list23  = listOf<Student>(Student("Mohit", "Delhi"), Student("Shiva", "Kolkata"), Student("Varun", "Punjab"))
        val json5 = Gson().toJson(list23)
        Log.i("MyTag4", json5)

        val type = object : TypeToken<List<Student>>() { }.type
        val otherList1 = Gson().fromJson<List<Student>>(json5, type)
        Log.i("MyTag5", otherList1.toString())







    }

}