package com.whitegold.yaprofi3.model

import jakarta.persistence.*

@Entity
data class Participant(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column
    val name: String? = null,
    @Column
    val wish: String? = null,
    @OneToOne
    val resident: Resident? = null
)
