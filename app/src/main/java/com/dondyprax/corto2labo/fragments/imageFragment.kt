package com.dondyprax.corto2labo.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dondyprax.corto2labo.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_image.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val  image = "param1"


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [imageFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [imageFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class imageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var imageId: Int = 0
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view =inflater.inflate(R.layout.fragment_image, container, false)

        bind(view)

        return view
    }



    fun bind(view: View){

        image_fi.setImageResource(imageId)

    }

    fun idReturn(): Int{
        return when(imageId){
            R.drawable.il_794xN -> 0
            R.drawable.product_image_743236128_1200x1200->1
            R.drawable.reality->2
            else -> 0
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance(id: Int) {
            val newFragment = imageFragment()

            newFragment.imageId = when(id){
                    0-> R.drawable.il_794xN
                    1-> R.drawable.product_image_743236128_1200x1200
                    2->R.drawable.reality
                    else ->R.drawable.il_794xN
                }

        }


    }
}
