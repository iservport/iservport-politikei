package org.helianto.politikei.controller

import java.security.Principal

import org.helianto.politikei.domain.Proposition
import org.helianto.politikei.service.PropositionService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api/proposition"))
//@PreAuthorize("isAuthenticated()")
class PropositionController(service: PropositionService) {

  /**
    * GET /api/proposition/:entityId/?page=
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param entityId id da entidade, ou seja, a câmara legislativa onde a proposta está inscrita
    * @param page número da página
    * @return lista de proposições da entidade
    */
  @GetMapping(Array("{entityId}"))
  def getAll(principal: Principal, @PathVariable entityId: String, @RequestParam(defaultValue="0") page: Int=0) =
    service.all(entityId, page)

  /**
    * GET /api/proposition/?propositionId=
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param propositionId id da proposição
    * @return a proposição solicitada
    */
  @GetMapping(params = Array("propositionId"))
  def getOne(principal: Principal, @RequestParam propositionId: String) = service.one(propositionId)

  /**
    * POST /api/proposition/?entityId=
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param entityId id da entidade, ou seja, a câmara legislativa onde a proposta está inscrita
    * @return uma nova proposição
    */
  @PostMapping(params = Array("entityId"))
  def postNew(principal: Principal, @RequestParam entityId: String) = new Proposition(entityId)

  /**
    * PUT /api/proposition
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param command a proposição a salvar ou atualizar.
    * @return a proposição atualizada
    */
  @PutMapping
  def putUpdate(principal: Principal, @RequestBody command: Proposition) = service.saveOrUpdate(command)

}
