package com.whitegold.yaprofi3.repository

import com.whitegold.yaprofi3.model.Participant
import com.whitegold.yaprofi3.model.Resident
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ResidentRepository: JpaRepository<Resident, Long> {
}