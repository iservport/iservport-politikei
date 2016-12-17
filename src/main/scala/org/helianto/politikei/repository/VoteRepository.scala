package org.helianto.politikei.repository

import org.helianto.politikei.Vote
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.data.jpa.repository.{JpaRepository, Query}

trait VoteRepository extends JpaRepository[Vote, String] {

  def findByPropositionId(propositionId: String, page: Pageable): Page[Vote]

  def findByPropositionIdAndIdentityId(propositionId: String, identityId: String): Vote

  @Query("select v_.propositionId, v_.vote, count(v_.id) as VoteCount from Vote v_ where v_.propositionId = ?1 group by v_.vote ")
  def countVote(propositionId: String): java.util.List[VoteCountProjection]

}

trait VoteCountProjection {

  def getPropositionId: String

  def getVote: Int

  def getVoteCount: Int

}

