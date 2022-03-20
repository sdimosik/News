package com.sdimosikvip.domain.model

import com.sdimosikvip.common.model.AvailableCategory

data class NewsDomain(
    var list: List<OneNewsDomain>,
    var category: AvailableCategory
)
