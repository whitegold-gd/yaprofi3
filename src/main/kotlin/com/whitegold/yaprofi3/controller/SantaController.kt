package com.whitegold.yaprofi3.controller

import com.whitegold.yaprofi3.dto.response.GroupResponse
import com.whitegold.yaprofi3.model.Group
import com.whitegold.yaprofi3.model.Participant
import com.whitegold.yaprofi3.model.Resident
import com.whitegold.yaprofi3.service.GroupService
import com.whitegold.yaprofi3.service.ParticipantService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class SantaController @Autowired constructor(
    val groupService: GroupService,
    val participantService: ParticipantService
) {

    @Operation(summary = "postGroup", responses = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "401", description = "Authentication Failure", content = [Content(schema = Schema(hidden = true))]) ])
    @PostMapping("/group")
    fun postGroup(@RequestBody body: Group): ResponseEntity<Long>{
        val id = groupService.postGroup(body)
        return if (id != null){
            ResponseEntity(id, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.CONFLICT)
        }
    }

    @Operation(summary = "getGroup", responses = [
        ApiResponse(responseCode = "200", description = "OK"),
        ApiResponse(responseCode = "401", description = "Authentication Failure", content = [Content(schema = Schema(hidden = true))]) ])
    @GetMapping("/groups")
    fun getGroup(): ResponseEntity<List<GroupResponse>>{
        return ResponseEntity(groupService.getGroup(),
            HttpStatus.OK)
    }

    @Operation(summary = "getGroupById", responses = [
        ApiResponse(responseCode = "200", description = "OK"),
        ApiResponse(responseCode = "401", description = "Authentication Failure", content = [Content(schema = Schema(hidden = true))]) ])
    @GetMapping("/group/{id}")
    fun getGroupById(@PathVariable id: Long): ResponseEntity<Group>{
        return ResponseEntity(groupService.getGroupById(id),
            HttpStatus.OK)
    }

    @Operation(summary = "putGroupById", responses = [
        ApiResponse(responseCode = "200", description = "OK"),
        ApiResponse(responseCode = "401", description = "Authentication Failure", content = [Content(schema = Schema(hidden = true))]) ])
    @PutMapping("/group/{id}")
    fun putGroupById(@PathVariable id: Long, @RequestBody body: Group): ResponseEntity<Unit>{
        groupService.putGroupById(id, body)
        return ResponseEntity(HttpStatus.OK)
    }

    @Operation(summary = "deleteGroupById", responses = [
        ApiResponse(responseCode = "200", description = "OK"),
        ApiResponse(responseCode = "401", description = "Authentication Failure", content = [Content(schema = Schema(hidden = true))]) ])
    @DeleteMapping("/group/{id}")
    fun deleteGroupById(@PathVariable id: Long): ResponseEntity<Unit>{
        groupService.deleteGroupById(id)
        return ResponseEntity(HttpStatus.OK)
    }

    @Operation(summary = "postParticipantIntoGroup", responses = [
        ApiResponse(responseCode = "200", description = "OK"),
        ApiResponse(responseCode = "401", description = "Authentication Failure", content = [Content(schema = Schema(hidden = true))]) ])
    @PostMapping("/group/{id}/participant")
    fun postParticipantIntoGroup(@PathVariable id: Long,
                                 @RequestBody participant: Participant): ResponseEntity<Long>{
        val idPart = participantService.postParticipantIntoGroup(id, participant)
        return if (idPart != null){
            ResponseEntity(idPart, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.CONFLICT)
        }
    }

    @Operation(summary = "deleteParticipantFromGroup", responses = [
        ApiResponse(responseCode = "200", description = "OK"),
        ApiResponse(responseCode = "401", description = "Authentication Failure", content = [Content(schema = Schema(hidden = true))]) ])
    @DeleteMapping("/group/{groupId}/participant/{participantId}")
    fun deleteParticipantFromGroup(@PathVariable groupId: Long,
                                   @PathVariable participantId: Long): ResponseEntity<Unit>{
        return ResponseEntity(participantService.deleteParticipantFromGroup(groupId, participantId), HttpStatus.OK)
    }

    @Operation(summary = "toss", responses = [
        ApiResponse(responseCode = "200", description = "OK"),
        ApiResponse(responseCode = "401", description = "Authentication Failure", content = [Content(schema = Schema(hidden = true))]) ])
    @PostMapping("/group/{id}/toss")
    fun toss(@PathVariable id: Long): ResponseEntity<List<Participant>>{
        val list = participantService.toss(id)
        return if (list != null){
            ResponseEntity(list,
                HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.CONFLICT)
        }
    }

    @Operation(summary = "getRecipient", responses = [
        ApiResponse(responseCode = "200", description = "OK"),
        ApiResponse(responseCode = "401", description = "Authentication Failure", content = [Content(schema = Schema(hidden = true))]) ])
    @GetMapping("/group/{groupId}/participant/{participantId}/recipient")
    fun getRecipient(@PathVariable groupId: Long, @PathVariable participantId: Long): ResponseEntity<Resident>{
        return ResponseEntity(participantService.getRecipient(groupId, participantId), HttpStatus.OK)
    }
}