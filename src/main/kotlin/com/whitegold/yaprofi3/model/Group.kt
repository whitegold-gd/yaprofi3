package com.whitegold.yaprofi3.model

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name="grouppp")
data class Group(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column
    val name: String? = null,
    @Column
    val description: String? = null,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    val participant: List<Participant> = mutableListOf()
)