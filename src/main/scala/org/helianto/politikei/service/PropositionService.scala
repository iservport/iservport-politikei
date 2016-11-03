package org.helianto.politikei.service

import org.helianto.politikei.domain.Proposition
import org.helianto.politikei.repository.PropositionRepository
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service

@Service
class PropositionService(val repository: PropositionRepository) {

  private[service] def page(page: Int) = new PageRequest(page, 10, Sort.Direction.ASC, "docCode")

  def all(entityId: String, p: Int = 0) = repository.findByEntityId(Option(entityId).getOrElse(""), page(p))

  def one(propositionId: String) = repository.findOne(Option(propositionId).getOrElse(""))

  def saveOrUpdate(entityId: String, command: Proposition) = {
    require(Option(entityId).nonEmpty)
    require(Option(command).nonEmpty)
    repository.save(Option(repository.findOne(command.getId)).getOrElse(new Proposition(entityId)).merge(command))
  }

}
