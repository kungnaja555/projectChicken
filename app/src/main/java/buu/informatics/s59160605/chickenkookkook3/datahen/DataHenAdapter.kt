package buu.informatics.s59160605.chickenkookkook3.datahen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import buu.informatics.s59160605.chickenkookkook3.R
import buu.informatics.s59160605.chickenkookkook3.TextItemViewHolder
import buu.informatics.s59160605.chickenkookkook3.database.hen.Hen


class DataHenDiffCallback : DiffUtil.ItemCallback<Hen>() {
    override fun areItemsTheSame(oldItem: Hen, newItem: Hen): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun areContentsTheSame(oldItem: Hen, newItem: Hen): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}



class DataHenAdapter: RecyclerView.Adapter<DataHenAdapter.ViewHolder>(){
    var data = listOf<Hen>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val dateTxt: TextView = itemView.findViewById(R.id.date_txt)
        val qualityTxt: TextView = itemView.findViewById(R.id.quality_txt)

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.list_item_hen, parent, false)
                return ViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    fun ViewHolder.bind(item: Hen) {
        dateTxt.text = item.date.toString()
        qualityTxt.text = item.die.toString()
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

}