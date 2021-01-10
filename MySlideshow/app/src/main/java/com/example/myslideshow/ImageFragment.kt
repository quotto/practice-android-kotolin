package com.example.myslideshow


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_image.*

val IMG_RES_ID = "IMG_RES_ID"
/**
 * A simple [Fragment] subclass.
 */
class ImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    companion object {
        fun newInstance(imageResourceId: Int) : ImageFragment {
            val bundle = Bundle()
            bundle.putInt(IMG_RES_ID, imageResourceId)
            val imageFragment = ImageFragment()
            imageFragment.arguments = bundle
            return imageFragment
        }
    }

    private var imageResId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageResId = it.getInt(IMG_RES_ID)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageResId?.let {
            imageView.setImageResource(it)
        }
    }
}
