package by.marcel.crud.adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.marcel.crud.R
import by.marcel.crud.data.entity.User

@SuppressLint("ParcelCreator")
class UserAdapter(var list: List<User>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog) {
        this.dialog = dialog
    }

    interface Dialog {
        fun onClik(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var flname: TextView
        var email: TextView
        var phone: TextView


        init {
            flname = view.findViewById(R.id.fullname)
            email = view.findViewById(R.id.email)
            phone = view.findViewById(R.id.phone)
            view.setOnClickListener {
                dialog.onClik(layoutPosition)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_user, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.flname.text = list[position].fullname
        holder.email.text = list[position].email
        holder.phone.text = list[position].phone
    }

    override fun getItemCount(): Int {
        return list.size
    }


}
