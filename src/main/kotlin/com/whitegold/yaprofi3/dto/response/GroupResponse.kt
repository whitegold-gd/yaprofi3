package com.whitegold.yaprofi3.dto.response

import com.whitegold.yaprofi3.model.Participant
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

data class GroupResponse(
    val id: Long? = null,
    val name: String? = null,
    val description: String? = null,
)