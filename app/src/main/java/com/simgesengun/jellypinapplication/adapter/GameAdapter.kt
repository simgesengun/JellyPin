package com.simgesengun.jellypinapplication.adapter

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.simgesengun.jellypinapplication.R
import com.simgesengun.jellypinapplication.databinding.FragmentGameBinding
import com.simgesengun.jellypinapplication.entity.Game
import com.simgesengun.jellypinapplication.entity.Item
import com.simgesengun.jellypinapplication.viewmodel.HomepageViewModel

class GameAdapter(var mContext : Context,
                  var gamesList : List<Game>,
                  var itemsList : List<Item>,
                  var viewModel : HomepageViewModel) : RecyclerView.Adapter<GameAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(cardDesignBinding: FragmentGameBinding) : RecyclerView.ViewHolder(cardDesignBinding.root){
        var design : FragmentGameBinding
        init{
            this.design = cardDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val design = FragmentGameBinding.inflate(layoutInflater,parent,false)
        return CardDesignHolder(design)
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val game = gamesList[position]

        val gameItemsList = getItems(game.name)

        holder.design.game = game
        holder.design.onSale = isOnSale(gameItemsList)
        holder.design.adapter = ItemAdapter(mContext, gameItemsList, viewModel)


    }

    override fun getItemCount(): Int = gamesList.size

    private fun getItems(game_name : String) : MutableList<Item>{
        var gameItemsList : MutableList<Item> = mutableListOf()
        for (item in itemsList){
            if (item.description.contains(game_name)){
                gameItemsList.add(item)
            }
        }
        return gameItemsList
    }

    private fun isOnSale(gameItemsList : List<Item>) : Int{
        for (item in gameItemsList){
            if (item.is_on_sale == 1){
                return 1
            }
        }
        return 0
    }

}