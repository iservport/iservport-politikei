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
    // FIXME: implementar
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @return o voto atualizada
    */
  @PutMapping(Array("/up"))
  def voteUp(principal: Principal) = ???

  /**
    * PUT /api/vote/down
    *
    // FIXME: implementar
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @return o voto atualizada
    */
  @PutMapping(Array("/down"))
  def voteDown(principal: Principal) = ???

}
