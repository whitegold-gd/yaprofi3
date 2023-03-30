package com.whitegold.yaprofi3.service

import com.whitegold.yaprofi3.model.Group
import com.whitegold.yaprofi3.model.Participant
import com.whitegold.yaprofi3.model.Resident
import com.whitegold.yaprofi3.repository.GroupRepository
import com.whitegold.yaprofi3.repository.ParticipantRepository
import com.whitegold.yaprofi3.repository.ResidentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ParticipantService (
    val groupRepository: GroupRepository,
    val participantRepository: ParticipantRepository,
    val residentRepository: ResidentRepository
) {
    fun postParticipantIntoGroup(id: Long, participant: Participant): Long? {
        val old = groupRepository.findByIdOrNull(id)
        return if (old != null && participant.name != null){
            groupRepository.save(
                Group(
                id,
                old.name,
                old.description,
                old.participant + participant
            )
            )
            residentRepository.save(mapPartToRez(participant))
            groupRepository.findById(id).get().participant.last().id
        } else {
            null
        }
    }

    fun deleteParticipantFromGroup(groupId: Long, participantId: Long){
        participantRepository.deleteById(participantId)
        residentRepository.deleteById(participantId)
    }

    fun toss(id: Long): List<Participant>? {
        val old = groupRepository.findByIdOrNull(id)
        if (old != null && old.participant.size >=3){
            val list = arrayListOf<Participant>()
            for (i in old.participant.indices){
                if (i != old.participant.size - 1) {
                    val partId = old.participant[i].id
                    val partNextId = old.participant[i + 1].id
                    if (partId != null && partNextId != null) {
                        val oldPart = participantRepository.findById(partId)
                        val nextPart = participantRepository.findById(partNextId)
                        participantRepository.save(
                            Participant(
                                partId,
                                oldPart.get().name,
                                oldPart.get().wish,
                                mapPartToRez(nextPart.get())
                            )
                        )
                        list.add(participantRepository.findById(partId).get())
                    }
                }
            }
            val partId = old.participant[old.participant.size - 1].id
            val partNextId = old.participant[0].id
            if (partId != null && partNextId != null) {
                val oldPart = participantRepository.findById(partId)
                val nextPart = participantRepository.findById(partNextId)
                participantRepository.save(Participant(
                    partId,
                    oldPart.get().name,
                    oldPart.get().wish,
                    mapPartToRez(nextPart.get())
                ))
                list.add(participantRepository.findById(partId).get())
            }
            return list
        } else {
            return null
        }
    }

    fun getRecipient(groupId: Long, participantId: Long): Resident? {
        val list = groupRepository.findById(groupId).get().participant
        for (i in list){
            if (i.id == participantId){
                return i.resident
            }
        }
        return null
    }

    fun mapPartToRez(participant: Participant): Resident{
        return Resident(participant.id, participant.name, participant.wish)
    }
}