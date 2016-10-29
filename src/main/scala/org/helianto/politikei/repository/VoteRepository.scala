package org.helianto.politikei.repository

import org.helianto.politikei.domain.Vote
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.data.jpa.repository.JpaRepository

trait VoteRepository extends JpaRepository[Vote, String] {

  def findByPropositionId(propositionId: String, page: Pageable): Page[Vote]

}