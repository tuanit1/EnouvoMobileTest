package com.example.enouvomobiletest.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

fun Fragment.replaceFragment(
    containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false,
    tag: String
){
    if (childFragmentManager.findFragmentByTag(tag) == null) {
        childFragmentManager.commit {
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
){
    if (childFragmentManager.findFragmentByTag(tag) == null) {
        childFragmentManager.commit {
            add(containerId, fragment, tag)
            if (addToBackStack) {
                addToBackStack(tag)
            }
        }
    }
}