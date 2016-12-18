package org.helianto.politikei.controller

import org.helianto.politikei.service.{AuthorityExtractor, VoteService}
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api/vote", "/vote"))
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
    * POST /api/vote/:propositionId
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param propositionId id da proposição
    * @return o meu voto na proposição
    */
  @PostMapping(Array("{propositionId}"))
  def getOne(implicit principal: OAuth2Authentication, @PathVariable propositionId: String) =
    service.me(propositionId, _identityId)

  /**
    * PUT /api/vote/:propositionId/:vote
    *
    * @param principal usuário autenticado (injetado pelo container)
    * @param propositionId id da proposição
    * @param vote o valor do voto
    * @return o voto atualizada
    */
  @PutMapping(Array("{propositionId}/{vote}"))
  def vote(implicit principal: OAuth2Authentication, @PathVariable propositionId: String, @PathVariable vote: Int) =
    service.saveOrUpdate(propositionId, _identityId, vote)

}
