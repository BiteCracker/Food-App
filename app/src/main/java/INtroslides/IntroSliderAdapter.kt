package INtroslides

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodu.R
class IntroSliderAdapter (private val introSlides: List<IntroSlide>):
    RecyclerView.Adapter<IntroSliderAdapter.IntroSliderViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSliderViewHolder {
        return IntroSliderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: IntroSliderViewHolder, position: Int) {
        holder.bind(introSlides[position])
    }
    override fun getItemCount(): Int {
        return introSlides.size
    }
    inner class  IntroSliderViewHolder(view: View) : RecyclerView.ViewHolder(view){
       private val TextTitle = view.findViewById<TextView>(R.id.texttitle)
       private val Textdescription = view.findViewById<TextView>(R.id.textdescription)
       private val imageiconn = view.findViewById<ImageView>(R.id.imageicon)
        fun bind(introSlide: IntroSlide){
            TextTitle.text=introSlide.title
            Textdescription.text=introSlide.description
            imageiconn.setImageResource(introSlide.icon)
        }
    }
}