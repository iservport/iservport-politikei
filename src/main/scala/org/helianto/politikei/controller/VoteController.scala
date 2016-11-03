package org.helianto.politikei.controller

import org.helianto.politikei.service.{AuthorityExtractor, VoteService}
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api/vote"))
class VoteController(service: VoteService) extends AuthorityExtractor {

  /**
    * GET /api/vote/:propositionId/?page=
    *
    * @param propositionId id da proposição
    * @param page número da página
    * @return lista de votos de uma proposição
    */
  @GetMapping(Array("{propositionId}"))
  def getAll(@PathVariable propositionId: String, @RequestParam(defaultValue="0") page: Int=0) =
    service.all(propositionId, page)

  /**
    * GET /api/vote/?voteId=
    *
    * @param voteId id do voto
    * @return o voto solicitado
    */
  @GetMapping(params = Array("voteId"))
  def getOne(@RequestParam voteId: String) = service.one(voteId)

  /**
    * PUT /api/vote/up
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param propositionId id da proposição
    * @return o voto atualizada
    */
  @PutMapping(Array("/up"))
  def voteUp(implicit principal: OAuth2Authentication, @PathVariable propositionId: String) =
    service.saveOrUpdate(propositionId, _identityId, 1)

  /**
    * PUT /api/vote/down
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param propositionId id da proposição
    * @return o voto atualizada
    */
  @PutMapping(Array("/down"))
  def voteDown(implicit principal: OAuth2Authentication, @PathVariable propositionId: String) =
    service.saveOrUpdate(propositionId, _identityId, -1)

}
