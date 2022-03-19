package com.sdimosikvip.common.mapper

interface BaseUnidirectionalMapper<R, D> {
    fun transform(o: R): D
}