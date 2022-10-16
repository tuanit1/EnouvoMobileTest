package com.example.enouvomobiletest.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.enouvomobiletest.R

fun Fragment.replaceFragment(
    containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false,
    tag: String
) {
    if (childFragmentManager.findFragmentByTag(tag) == null) {
        childFragmentManager.commit {

            if(tag == getString(R.string.tweet)){
                setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out)
            }else{
                setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out)
            }


            replace(containerId, fragment, tag)
            if (addToBackStack) {
                addToBackStack(tag)
            }
        }
    }
}

fun Fragment.addFragment(
    containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false,
    tag: String
) {
    if (childFragmentManager.findFragmentByTag(tag) == null) {
        childFragmentManager.commit {
            add(containerId, fragment, tag)
            if (addToBackStack) {
                addToBackStack(tag)
            }
        }
    }
}