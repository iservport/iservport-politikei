package org.helianto.politikei.service

import org.helianto.politikei.domain.Proposition
import org.helianto.politikei.repository.VoteRepository
import org.springframework.stereotype.Service

@Service
class VoteService(repository: VoteRepository) {

  def all(entityId: String) = repository.findByEntityId(entityId)

  def one(propositionId: String) = repository.findOne(propositionId)

  def saveOrUpdate(command: Proposition) = ???


}
