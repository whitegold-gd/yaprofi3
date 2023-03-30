package com.whitegold.yaprofi3.repository

import com.whitegold.yaprofi3.model.Group
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GroupRepository: JpaRepository<Group, Long> {
}