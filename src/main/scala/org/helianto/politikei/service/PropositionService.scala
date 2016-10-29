package org.helianto.politikei.service

import org.helianto.politikei.domain.Proposition
import org.helianto.politikei.repository.PropositionRepository
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service

@Service
class PropositionService(repository: PropositionRepository) {

  private def page(page: Int) = new PageRequest(page, 10, Sort.Direction.ASC, "docCode")

  def all(entityId: String, p: Int) = repository.findByEntityId(entityId, page(p))

  def one(propositionId: String) = repository.findOne(propositionId)

  def saveOrUpdate(command: Proposition) = ???

}
