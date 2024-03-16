package com.example.appconsql.ui.adapters

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appconsql.R
import com.example.appconsql.data.model.UserModel
import com.example.appconsql.ui.activities.Update

class UserAdapter(private val userList: List<UserModel>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private val tag = "UserAdapter"

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: UserModel) {
            itemView.findViewById<TextView>(R.id.namesusercard).text = user.names
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.usercard, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d(tag, "getItemCount:${userList.size}")
        return userList.size

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)

        holder.itemView.findViewById<Button>(R.id.btneditarcard).setOnClickListener { v: View ->
            val intent= Intent(v.context,Update::class.java)
            intent.putExtra("id_user",user.uid.toString())
            v.context.startActivity(intent)



        }

        holder.itemView.findViewById<Button>(R.id.btnllamarcard).setOnClickListener { v: View ->
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${user.celphone}")
            v.context.startActivity(intent)

        }
        holder.itemView.findViewById<Button>(R.id.btnmensajecard).setOnClickListener { v: View ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("sms:${user.celphone}")
            intent.putExtra("sms_body", "mensaje de ${user.names}")
            v.context.startActivity(intent)

        }
        holder.itemView.findViewById<Button>(R.id.btnemailcard).setOnClickListener { v: View ->
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:${user.email}")
            v.context.startActivity(intent)
        }



    }
}