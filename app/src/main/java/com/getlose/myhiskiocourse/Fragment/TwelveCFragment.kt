package com.getlose.myhiskiocourse.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getlose.myhiskiocourse.R
import com.getlose.myhiskiocourse.Interfaces.IBottomNavigationViewListener

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TwelveCFragment : Fragment() {

    private var param1:String? = null
    private var param2:String? = null
    private val TAG = TwelveCFragment::class.java.simpleName
    private lateinit var listener:IBottomNavigationViewListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        Log.d(TAG, "parameter => param1=>$param1 , param2=>$param2")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listener.onSelected()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_twelve_c, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TwelveAFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1:String, param2:String,listener: IBottomNavigationViewListener) =
            TwelveCFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
                this.listener = listener
            }

    }
}