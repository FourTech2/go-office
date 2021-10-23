package com.example.loginfirebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loginfirebase.models.Employee


class MainAdapter(private val context: Context): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private var dataList = mutableListOf<Employee>()

    fun setListData(data: MutableList<Employee>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_employee, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val employee = dataList[position]
        holder.bindView(employee)
    }

    override fun getItemCount(): Int {
        return if(dataList.size > 0) {
            dataList.size
        } else {
            0
        }
    }

    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindView(employee: Employee) {
            //Cargar una imagen
            //Glide.with(context).load(employee.imageURL).into(itemView.circleImageView)
            //holder.itemView.titleView.text = notiList[position].title
            itemView.findViewById<TextView>(R.id.idEmployeeTextView).text = employee.id
            itemView.findViewById<TextView>(R.id.nameEmployeeTextView).text = employee.name
            itemView.findViewById<TextView>(R.id.emailEmployeeTextView).text = employee.email
        }
    }
}