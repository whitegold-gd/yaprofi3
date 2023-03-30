package com.whitegold.yaprofi3.model

import jakarta.persistence.*

@Entity
data class Resident(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column
    val name: String? = null,
    @Column
    val wish: String? = null
)
