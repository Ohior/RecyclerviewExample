package com.example.recyclerexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    // replace all the DataClass reference in this file with your own data class name
    private var array_list: ArrayList<DataClass> = ArrayList()
    private var context: Context
    private var layout: Int = 0
    private var recycler_view: RecyclerView
    private lateinit var click_listener:OnItemClickListener

    constructor(context:Context, recyclerview: RecyclerView, layout: Int) {
        this.context = context
        this.recycler_view = recyclerview
        this.layout = layout
        this.recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        this.recycler_view.adapter = this
    }

    constructor(context:Context,
                recyclerview: RecyclerView,
                layout:Int,
                layout_manager: RecyclerView.LayoutManager
    ){
        this.recycler_view = recyclerview
        this.context = context
        this.layout = layout
        this.recycler_view.layoutManager = layout_manager
        this.recycler_view.adapter = this
    }


    interface OnItemClickListener{
        // inter face for auto loading itemClick and longItemClick
        fun onItemClick(position: Int, view:View)
        fun onLongItemClick(position: Int, view:View)
    }

    fun onClickListener(listener:OnItemClickListener){
        // This function handle's click and long click.
        // Do not use this function if you are not sure what to do.
        // Use this function like this in your fragment or activity file
        //*********************************************************
        // name_of_your_recycler_view_adapter.onClickListener(
        //     object : RecyclerAdapter.OnItemClickListener {
        //         override fun onItemClick(position: Int, view: View) {
        //             TODO("Your click execution should be here")
        //         }
        //         override fun onLongItemClick(position: Int, view: View) {
        //             TODO("Your long click execution should be here")
        //         }
        //     }
        // )
        //*********************************************************************

        click_listener = listener
    }

    fun onClickListener(function1: (pos:Int, v:View) -> Unit, function2:(pos:Int, v:View)->Unit){
        //this function handle click and long click
        //use this function if you are familiar with the way lambda works
        //use this function like this in your fragment or activity file
        //*********************************************************
        // name_of_your_recycler_view_adapter.onClickListener(
        //     { pos, v ->
        //         todo this is where your item click execution will be click
        //     },
        //     { pos, v ->
        //         todo this is where your long item click execution will be click
        //     }
        // )
        //*********************************************************************
        click_listener = object:OnItemClickListener{
            override fun onItemClick(position: Int, view: View) {
                function1(position,  view)
            }

            override fun onLongItemClick(position: Int, view: View) {
                function2(position,  view)
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(layout, parent, false)
        return ViewHolder(view, click_listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // bind your view holder class with your recycler adapter
        // i.e bind your view holder with your recyclerview
        val arraylist = array_list[position]
        holder.title.text = arraylist.title
        holder.detail.text = arraylist.detail
    }

    override fun getItemCount(): Int {
        //get the number of item in your recyclerview
        return array_list.size
    }

    fun emptyAdapter(){
        //remove all item from your recyclerview
        array_list.clear()
    }

    fun addToAdapter(element: DataClass){
        // add item to your recyclerview
        array_list.add(element)
    }

    fun addToAdapter(index:Int, element:DataClass){
        // add item to an index spot of your recyclerview
        array_list.add(index, element)
    }


    class ViewHolder(item_view: View, listener:OnItemClickListener): RecyclerView.ViewHolder(item_view) {
        // initialize the item your view holder will hold
        val title: TextView = item_view.findViewById(R.id.id_tv_title)
        val detail: TextView = item_view.findViewById(R.id.id_tv_detail)
        init {
            item_view.setOnLongClickListener{
                listener.onLongItemClick(adapterPosition, item_view)
                true
            }
            item_view.setOnClickListener {
                listener.onItemClick(adapterPosition, item_view)
            }
        }
    }
}
