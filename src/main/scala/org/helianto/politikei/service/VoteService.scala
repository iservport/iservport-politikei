package org.helianto.politikei.service

import org.helianto.politikei.domain.{Proposition, Vote}
import org.helianto.politikei.repository.{PropositionRepository, VoteRepository}
import org.springframework.data.domain.{PageRequest, Sort}
import org.springframework.stereotype.Service

@Service
class VoteService(val repository: VoteRepository, val propositionRepository: PropositionRepository) {

  private[service] def page(page: Int) = new PageRequest(page, 10, Sort.Direction.ASC, "voteDate")

  def all(propositionId: String, p: Int = 0) = repository.findByPropositionId(Option(propositionId).getOrElse(""), page(0))

  def one(voteId: String) = repository.findOne(Option(voteId).getOrElse(""))

  def saveOrUpdate(propositionId: String, identityId: String, value: Int) = {
    require(Option(propositionId).nonEmpty)
    require(Option(identityId).nonEmpty)
    Option(propositionRepository.findOne(propositionId)) match {
      case Some(proposition) =>
        val vote = repository.save {
          Option(repository.findByPropositionIdAndIdentityId(propositionId, identityId))
            .getOrElse(new Vote(propositionId, identityId))
            .merge(value)
        }
        applyTo(proposition)
        vote
      case None => throw new IllegalArgumentException("Proposition must not be null")
    }
  }

  private[service] def applyTo(proposition: Proposition) = {
    import collection.JavaConversions._
    repository.countVote(proposition.id).toList.foreach{i => proposition.merge(i)}
    propositionRepository.save(proposition)
  }

}
