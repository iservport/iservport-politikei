package org.helianto.politikei.controller

import java.security.Principal

import org.helianto.politikei.domain.Proposition
import org.helianto.politikei.service.VoteService
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api/vote"))
class VoteController(service: VoteService) {

  /**
    * GET /api/vote/:propositionId
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param propositionId id da proposição
    * @return lista de votos de uma proposição
    */
  @GetMapping(Array("{propositionId}"))
  def getAll(principal: Principal, @PathVariable propositionId: String) = service.all(propositionId)

  /**
    * GET /api/vote/?voteId=
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param voteId id do voto
    * @return o voto solicitado
    */
  @GetMapping(params = Array("voteId"))
  def getOne(principal: Principal, @RequestParam voteId: String) = service.one(voteId)

  /**
    * PUT /api/vote/up
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param command a proposição a salvar ou atualizar.
    * @return a proposição atualizada
    */
  @PutMapping
  def voteUp(principal: Principal, @RequestBody command: Proposition) = service.saveOrUpdate(command)

  /**
    * PUT /api/vote/down
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param command a proposição a salvar ou atualizar.
    * @return a proposição atualizada
    */
  @PutMapping
  def voteDown(principal: Principal, @RequestBody command: Proposition) = service.saveOrUpdate(command)

}
