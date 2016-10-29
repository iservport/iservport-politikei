package org.helianto.politikei.domain

import java.util.{Date, UUID}
import javax.persistence._

import scala.beans.BeanProperty

/**
  * Voto a favor (1) ou contra (-1), ou ainda, não entendi (0)
  */
@javax.persistence.Entity
@Table(name = "pltk_vote"
  , uniqueConstraints = Array(new UniqueConstraint(columnNames = Array("propositionId", "identityId"))))
class Vote
( @BeanProperty @Column(length = 32) var propositionId: String
, @BeanProperty @Column(length = 32) var identityId: String) {
  @BeanProperty @Id                  var id: String = UUID.randomUUID().toString.replaceAll("", "")
  @BeanProperty @Temporal(TemporalType.TIMESTAMP) var voteDate: Date = new Date()
  @BeanProperty                      var vote: Int = 0

  def this() = this("", "") // empty constructor

}
