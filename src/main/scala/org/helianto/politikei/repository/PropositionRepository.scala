package org.helianto.politikei.repository

import org.helianto.politikei.Proposition
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.data.jpa.repository.JpaRepository

trait PropositionRepository extends JpaRepository[Proposition, String] {

  def findByEntityId(entityId: String, page: Pageable): Page[Proposition]

}