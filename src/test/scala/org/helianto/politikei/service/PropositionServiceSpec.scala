package org.helianto.politikei.service

import org.helianto.politikei.UnitSpec
import org.helianto.politikei.domain.Proposition
import org.helianto.politikei.repository.PropositionRepository
import org.springframework.data.domain.PageImpl


class PropositionServiceSpec extends UnitSpec {

  import org.mockito.Mockito._

  "An ENTITY_ID" should "page all propositions from that entity" in {
    val service = new  PropositionService(mock[PropositionRepository])
    val page = new PageImpl[Proposition](new java.util.ArrayList[Proposition]())
    when(service.repository.findByEntityId("ENTITY_ID", service.page(0)))
      .thenReturn(page)
    page shouldBe service.all("ENTITY_ID", 0)
  }

  "A PROPOSITION_ID" should "get the proposition" in {
    val service = new  PropositionService(mock[PropositionRepository])
    val proposition = new Proposition("ENTITY_ID")
    when(service.repository.findOne("PROPOSITION_ID"))
      .thenReturn(proposition)
    proposition shouldBe service.one("PROPOSITION_ID")
  }

  val command = new Proposition("ENTITY_ID")
  command.id = "PROPOSITION_ID"
  command.docAbstract = "ABSTRACT"
  command.docCode = "CODE"
  command.docContent = "CONTENT"
  command.docType = 1
  command.votedUp = 10
  command.votedDown = 100

  "A command having id of a previously saved proposition" should "update the proposition" in {
    val service = new  PropositionService(mock[PropositionRepository])
    val proposition = new Proposition("ENTITY_ID")
    proposition.id = "PROPOSITION_ID"
    when(service.repository.findOne("PROPOSITION_ID"))
      .thenReturn(proposition)
    when(service.repository.save(proposition))
      .thenReturn(proposition)
    service.saveOrUpdate("ENTITY_ID", command) should have (
      'id ("PROPOSITION_ID")
      , 'docAbstract ("ABSTRACT")
      , 'docCode ("CODE")
      , 'docContent ("CONTENT")
      , 'docType (1)
      , 'votedUp (10)
      , 'votedDown (100)
    )
  }

  "A command having id of a NOT previously saved proposition" should "save the proposition" in {
    val service = new  PropositionService(mock[PropositionRepository])
    val proposition = new Proposition("ENTITY_ID")
    when(service.repository.findOne("PROPOSITION_ID"))
      .thenReturn(null)
    import org.mockito.Matchers._
    when(service.repository.save(any(classOf[Proposition])))
      .thenReturn(proposition)
    service.saveOrUpdate("ENTITY_ID", command) shouldBe proposition
  }

  "An entityId " should "not be null" in {
    val service = new  PropositionService(mock[PropositionRepository])
    intercept[IllegalArgumentException] { service.saveOrUpdate(null, null) }
  }

  "A command " should "not be null" in {
    val service = new  PropositionService(mock[PropositionRepository])
    intercept[IllegalArgumentException] { service.saveOrUpdate("ENTITY_ID", null) }
  }

}
