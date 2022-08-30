package com.getlose.myhiskiocourse.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.getlose.myhiskiocourse.Application.MyApplication
import com.getlose.myhiskiocourse.R
import com.getlose.myhiskiocourse.databinding.FragmentTwelveBBinding
import com.getlose.myhiskiocourse.Interfaces.IBottomNavigationViewListener

class TwelveBFragment : Fragment() {

    private lateinit var binding : FragmentTwelveBBinding
    private lateinit var listener:IBottomNavigationViewListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwelveBBinding.inflate(inflater,container,false)

        binding.button.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container,TwelveCFragment.newInstance("from b","to c",listener))
                .commit()

        }

        listener.onSelected()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnClickToast.setOnClickListener {
            Toast.makeText(MyApplication.getInstance(),"test",Toast.LENGTH_LONG).show()
        }
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
        fun newInstance(listener: IBottomNavigationViewListener) =
            TwelveBFragment().apply {
                this.listener = listener
            }
    }
}