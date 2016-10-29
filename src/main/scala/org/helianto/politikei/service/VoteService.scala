package org.helianto.politikei.service

import org.helianto.politikei.domain.Proposition
import org.helianto.politikei.repository.VoteRepository
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service

@Service
class VoteService(repository: VoteRepository) {

  private def page(page: Int) = new PageRequest(page, 10, Sort.Direction.ASC, "voteDate")

  def all(propositionId: String) = repository.findByPropositionId(propositionId, page(0))

  def one(propositionId: String) = repository.findOne(propositionId)

  def saveOrUpdate(command: Proposition) = ???


}
