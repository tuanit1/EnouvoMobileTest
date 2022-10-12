package com.example.enouvomobiletest.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

fun Fragment.replaceFragment(
    containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false
){
    if (childFragmentManager.findFragmentByTag(tag ?: fragment.javaClass.name) == null) {
        childFragmentManager.commit {
            replace(containerId, fragment, tag ?: fragment.javaClass.name)
            if (addToBackStack) {
                addToBackStack(fragment.javaClass.name)
            }
        }
    }
}

fun Fragment.addFragment(
    containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false
){
    if (childFragmentManager.findFragmentByTag(tag ?: fragment.javaClass.name) == null) {
        childFragmentManager.commit {
            add(containerId, fragment, tag ?: fragment.javaClass.name)
            if (addToBackStack) {
                addToBackStack(fragment.javaClass.name)
            }
        }
    }
}