package org.helianto.politikei.controller

import org.helianto.politikei.Proposition
import org.helianto.politikei.service.{AuthorityExtractor, PropositionService}
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api/proposition"))
class PropositionController(service: PropositionService) extends AuthorityExtractor {

  /**
    * GET /api/proposition/?page=
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param page número da página
    * @return lista de proposições da entidade onde o usuário está autenticado
    */
  @GetMapping
  def getAllFromUser(implicit principal: OAuth2Authentication, @RequestParam(defaultValue="0") page: Int=0) =
    service.all(_entityId, page)

  /**
    * GET /api/proposition/:entityId/?page=
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param entityId id da entidade, ou seja, a câmara legislativa onde a proposta está inscrita
    * @param page número da página
    * @return lista de proposições da entidade
    */
  @GetMapping(Array("{entityId}"))
  def getAll(implicit principal: OAuth2Authentication, @PathVariable entityId: String, @RequestParam(defaultValue="0") page: Int=0) =
    service.all(entityId, page)

  /**
    * GET /api/proposition/?propositionId=
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param propositionId id da proposição
    * @return a proposição solicitada
    */
  @GetMapping(params = Array("propositionId"))
  def getOne(implicit principal: OAuth2Authentication, @RequestParam propositionId: String) = service.one(propositionId)

  /**
    * POST /api/proposition/?entityId=
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param entityId id da entidade, ou seja, a câmara legislativa onde a proposta está inscrita
    * @return uma nova proposição
    */
  @PostMapping(params = Array("entityId"))
  def postNew(implicit principal: OAuth2Authentication, @RequestParam entityId: String) = new Proposition(entityId)

  /**
    * PUT /api/proposition
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param command a proposição a salvar ou atualizar.
    * @return a proposição atualizada
    */
  @PutMapping
  def putUpdate(implicit principal: OAuth2Authentication, @RequestBody command: Proposition) = service.saveOrUpdate(_entityId, command)

}
