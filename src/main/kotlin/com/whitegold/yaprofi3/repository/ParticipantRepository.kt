package com.whitegold.yaprofi3.repository

import com.whitegold.yaprofi3.model.Group
import com.whitegold.yaprofi3.model.Participant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ParticipantRepository: JpaRepository<Participant, Long> {
}