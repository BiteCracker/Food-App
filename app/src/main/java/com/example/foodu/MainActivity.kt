package com.example.foodu
import INtroslides.IntroSlide
import INtroslides.IntroSliderAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Adapter
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.foodu.Login.login
class MainActivity : AppCompatActivity() {
    private lateinit var viewpager: ViewPager2
    private lateinit var btn: Button
    private lateinit var skipintroo:TextView
    private lateinit var linearLayout: LinearLayout
    private lateinit var introSliderAdapter: IntroSliderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.appCompatButton)
        viewpager = findViewById(R.id.introsliderviewpager)
        linearLayout = findViewById(R.id.linearLayout)
        skipintroo=findViewById(R.id.skipintro)


        val IntroSlide = listOf(
            IntroSlide(
                "Order for Food",
                "Savor the timeless flavors of our Classic Margherita Pizza, a true celebration of simplicity and quality. Our hand-tossed dough is crafted daily to ensure the perfect balance of chewiness and crispiness",
                R.drawable.fastfood
            ),
            IntroSlide(
                "Fast Payment",
                "Experience seamless and secure payments with our Fast Payment feature. Enjoy the convenience of quick checkouts, saving you time and hassle",
                R.drawable.payment
            ),
            IntroSlide(
                "Fast Delivery",
                "Get your favorite meals delivered fast with our lightning-speed delivery service. We prioritize getting your food to you hot and fresh, straight from our kitchen to your door",
                R.drawable.dilivery
            )
        )
        introSliderAdapter = IntroSliderAdapter(IntroSlide)
        viewpager.adapter = introSliderAdapter
        btn.setOnClickListener {
            if (viewpager.currentItem + 1 < IntroSlide.size) {
                viewpager.currentItem += 1
            } else {
                startActivity(Intent(this, login::class.java))
                finish()
            }
        }
        skipintroo.setOnClickListener {
            startActivity(Intent(this,login::class.java))
            finish()
        }
        viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        setupindicators()
        setCurrentIndicator(0)
    }
    fun setupindicators() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            linearLayout.addView(indicators[i])
        }
    }
    fun setCurrentIndicator(index: Int) {
        val childCount = linearLayout.childCount
        for (i in 0 until childCount) {
            val imageView = linearLayout.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }

    }
}