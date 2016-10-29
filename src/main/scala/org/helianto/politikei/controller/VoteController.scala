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
    * GET /api/vote/?propositionId=
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param propositionId id da proposição
    * @return a proposição solicitada
    */
  @GetMapping(params = Array("propositionId"))
  def getOne(principal: Principal, @RequestParam propositionId: String) = service.one(propositionId)

  /**
    * POST /api/vote/?entityId=
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param entityId id da entidade, ou seja, a câmara legislativa onde a proposta está inscrita
    * @return uma nova proposição
    */
  @PostMapping(params = Array("entityId"))
  def postNew(principal: Principal, @RequestParam entityId: String) = new Proposition(entityId)

  /**
    * PUT /api/vote
    * 
    * @param principal usuário autenticado (injetado pelo container)
    * @param command a proposição a salvar ou atualizar.
    * @return a proposição atualizada
    */
  @PutMapping
  def putUpdate(principal: Principal, @RequestBody command: Proposition) = service.saveOrUpdate(command)

}
