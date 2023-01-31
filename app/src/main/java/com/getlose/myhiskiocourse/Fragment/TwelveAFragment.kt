package com.getlose.myhiskiocourse.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getlose.myhiskiocourse.databinding.FragmentTwelveABinding

class TwelveAFragment : Fragment() {

    private val TAG = TwelveAFragment::class.java.simpleName
    private var mutableList : ArrayList<String> = ArrayList<String>()
    private lateinit var binding : FragmentTwelveABinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        // Inflate the layout for this fragment
        binding = FragmentTwelveABinding.inflate(inflater,container,false)
        return binding.root
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
        fun newInstance(): TwelveAFragment
            {
                Log.d("TwelveAFragment", "newInstance")
                return TwelveAFragment()
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: ")

        //(activity as TempToolbarTitleListener).updateTitle("custom title")



    }



    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(TAG, "onViewStateRestored: ")
        savedInstanceState?.let {
            it.getStringArrayList("data").let {
                if (it != null) {
                    mutableList = it
                    binding.txtA.text = mutableList.first()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.d(TAG, "onSaveInstanceState: ")

        //mutableList.add("6")  // 新增元素
        mutableList.add("7")
        outState.putStringArrayList("data",mutableList)
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }
}