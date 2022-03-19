package com.sdimosikvip.common.mapper

interface BaseBidirectionalMapper<R, D> {

    fun transform(o: R): D
    fun reverseTransform(o: D): R
}