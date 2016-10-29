package org.helianto.politikei.domain

import java.util.UUID
import javax.persistence._

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
  @BeanProperty @Id                   var id: String = UUID.randomUUID().toString.replaceAll("", "")
  @BeanProperty @Column(length = 32)  var docCode: String = ""
  @BeanProperty @Column(length = 256) var docAbstract: String = ""
  @BeanProperty @Lob                  var docContent: String = ""
  @BeanProperty                       var docType: Int = 0
  @BeanProperty                       var votedUp: Int = 0
  @BeanProperty                       var votedDown: Int = 0

  def this() = this("") // empty constructor

}
