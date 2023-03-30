package com.whitegold.yaprofi3.service

import com.whitegold.yaprofi3.dto.response.GroupResponse
import com.whitegold.yaprofi3.model.Group
import com.whitegold.yaprofi3.model.Participant
import com.whitegold.yaprofi3.repository.GroupRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class GroupService(
    val groupRepository: GroupRepository
) {
    fun postGroup(body: Group): Long? {
        return if (body.name != null) {
            groupRepository.save(body).id
        } else {
            null
        }
    }

    fun getGroup(): List<GroupResponse> {
        return groupRepository.findAll().map{ mapToGroupResponse(it) }
    }


    fun mapToGroupResponse(group: Group): GroupResponse{
        return GroupResponse(group.id, group.name, group.description)
    }

    fun getGroupById(id: Long): Group? {
        return groupRepository.findByIdOrNull(id)
    }

    fun putGroupById(id: Long, body: Group) {
        val old = groupRepository.findByIdOrNull(id)
        if (old != null && body.name != null){
            groupRepository.save( Group(
                id,
                body.name,
                body.description,
                old.participant
            ))
        }
    }

    fun deleteGroupById(id: Long) {
        return groupRepository.deleteById(id)
    }
}