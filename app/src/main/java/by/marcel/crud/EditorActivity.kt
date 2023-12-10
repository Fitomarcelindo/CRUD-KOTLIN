package by.marcel.crud

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import by.marcel.crud.data.AppDatabase
import by.marcel.crud.data.entity.User

class EditorActivity : AppCompatActivity() {

    private lateinit var flname : EditText
    private lateinit var email  : EditText
    private lateinit var phone  : EditText
    private lateinit var btn    : Button
    private lateinit var database: AppDatabase

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        flname = findViewById<EditText>(R.id.fullname)
        email = findViewById<EditText>(R.id.email)
        phone = findViewById<EditText>(R.id.phone)
        btn = findViewById<Button>(R.id.btn_save)

        database = AppDatabase.getInstance(applicationContext)

        val intent = intent.extras
        if (intent!=null){
            val id =  intent.getInt("id", 0)
            val user = database.UserDao().get(id)

            flname.setText(user.fullname)
            email.setText(user.email)
            phone.setText(user.phone)
        }

        btn.setOnClickListener {
            if (flname.text.isNotEmpty() && email.text.isNotEmpty() && phone.text.isNotEmpty()){

                if (intent!=null){
                    database.UserDao().Update(User(
                        intent.getInt("id",0),
                        flname.text.toString(),
                        email.text.toString(),
                        phone.text.toString()
                    ))
                }else {
                    database.UserDao().insertAll(
                        User(
                            null,
                            flname.text.toString(),
                            email.text.toString(),
                            phone.text.toString()
                        )
                    )
                }
                finish()
            }else {
                Toast.makeText(
                    applicationContext,
                    "Please Field Data",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}