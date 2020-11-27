package com.example.gridsehirler

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.sehirler_entry.view.*

class MainActivity : AppCompatActivity() {

    var adapter: SehirlerAdapter?=null
    var sehirlist = ArrayList<Sehirler>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sehirlist.add(Sehirler("İstanbul", R.drawable.istanbul, " Nüfus Bilgisi: 15.52 M \n Coğrafi Konum: Marmara \n İklim: Akdeniz, Karadeniz, Balkan ve Anadolu"))
        sehirlist.add(Sehirler("Bursa", R.drawable.bursa, " Nüfus Bilgisi: 2.99 M \n" +
                " Coğrafi Konum: Anadolu'da Marmara Denizi kıyıları \n" +
                " İklim: Ilıman"))
        sehirlist.add(Sehirler("Antalya", R.drawable.antalya, " Nüfus Bilgisi: 10.46 M \n" +
                " Coğrafi Konum: Akdeniz Bölgesi \n" +
                " İklim: Akdeniz"))
        sehirlist.add(Sehirler("Kars",R.drawable.kars, " Nüfus Bilgisi: 285 Bin \n" +
                " Coğrafi Konum: Doğu Anadolu Bölgesi \n" +
                " İklim: Kışları uzun ve sert, Yazları ılık"))
        sehirlist.add(Sehirler("Rize", R.drawable.rize, " Nüfus Bilgisi: 348 Bin \n" +
                " Coğrafi Konum: Kuzey Doğu \n" +
                " İklim: Karadeniz"))
        sehirlist.add(Sehirler("Ankara", R.drawable.ankara, " Nüfus Bilgisi: 5.5 M \n" +
                " Coğrafi Konum: İç Anadolu Bölgesi \n" +
                " İklim: Step-Bozkır, Yağışlı"))
        adapter = SehirlerAdapter(this, sehirlist)

        gvSehir.adapter=adapter
    }
    class SehirlerAdapter : BaseAdapter{
        var sehirlist=ArrayList<Sehirler>()
        var context: Context?=null

        constructor(context: Context, sehirlist: ArrayList<Sehirler>) : super(){
            this.context=context
            this.sehirlist=sehirlist
        }
        override fun getCount(): Int{
            return sehirlist.size
        }
        override fun getItem(position: Int): Any {
            return sehirlist[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val sehirs = this.sehirlist[position]

            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var sehirview = inflator.inflate(R.layout.sehirler_entry, null)
            sehirview.imgsehir.setImageResource(sehirs.image!!)
            sehirview.tvName.text = sehirs.name!!

            sehirview.setOnClickListener {

                val intent = Intent(context,MainActivity2::class.java)
                intent.putExtra("name", sehirs.name)
                intent.putExtra("description", sehirs.description)
                intent.putExtra("image", sehirs.image)
                context?.startActivity(intent)

            }

            return sehirview
        }
    }
}