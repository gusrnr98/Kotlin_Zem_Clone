package com.example.kotlin_zem.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_zem.Adapter.HabitTypeAdapter
import com.example.kotlin_zem.R
import com.example.kotlin_zem.databinding.FragmentAddHabitStep2Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddHabitStep2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddHabitStep2Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentAddHabitStep2Binding? = null
    private val binding get() = _binding!!
    private var typeList = listOf<String>()
    lateinit var mAdapter: HabitTypeAdapter
    private val fragmentStep3 = AddHabitStep3Fragment()
    var list = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddHabitStep2Binding.inflate(inflater,container,false)

        show()

        setFragmentResultListener("fragment2"){ habitInfo, bundle ->
            list = bundle.getStringArrayList("bundleKey")!!

            val imageId = context?.resources?.getIdentifier(list[2],"drawable",context?.packageName)
            binding.habitimage.setImageResource(imageId!!)
            binding.habittitle.text = list[0] +" "+ list[1]

            habittypelist(list)
        }

        binding.back.setOnClickListener {
            var step1Fragment = AddHabitStep1Fragment()

            parentFragmentManager.beginTransaction().remove(this).commit()
            parentFragmentManager.beginTransaction().replace(R.id.addhabitstepview, step1Fragment).commit()
        }

        return binding.root
    }

    fun show(){
        setFragmentResultListener("habitInfo"){ habitInfo, bundle ->
            list = bundle.getStringArrayList("bundleKey")!!

            val imageId = context?.resources?.getIdentifier(list[2],"drawable",context?.packageName)
            binding.habitimage.setImageResource(imageId!!)
            binding.habittitle.text = list[0] +" "+ list[1]

            habittypelist(list)
        }
    }

    fun habittypelist(list: ArrayList<String>){
        when(list[0]){
            "?????????" -> {
                typeList = listOf("????????? 100?????????","?????? ?????????", "??????????????? ??????","?????? ????????????", "?????? 3??? ?????????", "?????? ?????????", "?????? ????????????", "?????? ??????", "??????")
            }
            "?????????" ->{
                typeList = listOf("????????? ??????", "????????? ??????","?????? ?????? ?????????", "?????? ?????? ??????","?????? ?????? ??????","?????? ?????? ?????????","??????")
            }
            "????????????" ->{
                typeList = listOf("?????? ???????????? ?????? ?????????","????????? ????????? ??????","??????????????? ??????","?????? ????????????","?????? ????????????","??????")
            }
            "?????????" ->{
                typeList = listOf("?????? ?????? ?????? ????????????","????????? ?????? ????????????", "?????? 5??? ?????? ????????????","?????? 5????????? ????????????","?????? ?????? ?????????","??????")
            }
            "?????????" ->{
                typeList = listOf("?????? ?????? ?????????","????????? ???????????? ?????????","????????? ????????? SNS ??????","?????? ?????? ?????????","???????????? ?????? ????????? ????????? ?????????","??????")
            }
            "????????????" ->{
                typeList = listOf("????????? ????????? ?????????", "???????????? ??????","????????? ?????? ????????????","?????? ????????????","?????? ????????????","????????? ????????? ??????","????????? ????????????","???????????? ???????????????", "??????")
            }
            "?????????" ->{
                typeList = listOf("????????? ????????? ?????????","?????? ?????????","?????? ??????", "????????? ????????????","??????")
            }
        }
        mAdapter = HabitTypeAdapter(requireContext(), typeList) { String ->
            var habitTypeList = arrayListOf<String>(list[0], list[1], list[2], String)
            setFragmentResult("habittype", bundleOf("bundleKey" to habitTypeList))
            parentFragmentManager.beginTransaction()
                .replace(R.id.addhabitstepview, fragmentStep3!!)
                .commit()
            }
        binding.habittypeReView.adapter = mAdapter
        binding.habittypeReView.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddHabitStep2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}