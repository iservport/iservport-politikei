package org.helianto.politikei

import java.util.{Date, UUID}
import javax.persistence._

import org.helianto.politikei.repository.VoteCountProjection

import scala.beans.BeanProperty

/**
  * Legal document proposition.
  *
  * @param entityId
  */
@javax.persistence.Entity
@Table(name = "pltk_proposition"
  , uniqueConstraints = Array(new UniqueConstraint(columnNames = Array("entityId", "docCode"))))
class Proposition
( @BeanProperty @Column(length = 32)  var entityId: String) {
  @BeanProperty @Id                   var id: String = UUID.randomUUID().toString.replaceAll("-", "")
  @BeanProperty @Column(length = 32)  var docCode: String = ""
  @BeanProperty @Column(length = 256) var docAbstract: String = ""
  @BeanProperty @Lob                  var docContent: String = ""
  @BeanProperty                       var docType: Int = 0
  @BeanProperty                       var issueDate: Date = new Date()
  @BeanProperty @Column(length = 32)  var authorId: String = ""
  @BeanProperty                       var votedUp: Long = 0
  @BeanProperty                       var votedDown: Long = 0
  @BeanProperty                       var votedOther: Long = 0

  def this() = this("") // empty constructor

  def merge(command: Proposition) = {
    docCode = command.docCode
    docAbstract = command.docAbstract
    docContent = command.docContent
    docType = command.docType
    issueDate = command.issueDate
    authorId = command.authorId
    votedUp = command.votedUp
    votedDown = command.votedDown
    votedOther = command.votedOther
    this
  }

  def merge(votes: VoteCountProjection) = {
    Option(votes) match {
      case Some(v) if v.getVoteCount == 1 =>  votedUp = v.getVoteCount
      case Some(v) if v.getVoteCount == -1 => votedDown = v.getVoteCount
      case Some(v) => votedOther = v.getVoteCount
      case _ =>
    }
    this
  }

}
