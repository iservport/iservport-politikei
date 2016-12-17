package org.helianto.politikei.service

import org.helianto.politikei.{Proposition, UnitSpec, Vote}
import org.helianto.politikei.repository.{PropositionRepository, VoteRepository}
import org.springframework.data.domain.PageImpl


class VoteServiceSpec extends UnitSpec {

  import org.mockito.Mockito._

  "A PROPOSITION_ID" should "page all votes from that proposition" in {
    val service = new VoteService(mock[VoteRepository], mock[PropositionRepository])
    val page = new PageImpl[Vote](new java.util.ArrayList[Vote]())
    when(service.repository.findByPropositionId("PROPOSITION_ID", service.page(0)))
      .thenReturn(page)
    page shouldBe service.all("PROPOSITION_ID", 0)
  }

  "A VOTE_ID" should "get the vote" in {
    val service = new VoteService(mock[VoteRepository], mock[PropositionRepository])
    val vote = new Vote("PROPOSITION_ID", "IDENTITY_ID")
    when(service.repository.findOne("VOTE_ID"))
      .thenReturn(vote)
    vote shouldBe service.one("VOTE_ID")
  }

  "A condition returning a previously saved vote" should "update the vote" in {
    val service = new VoteService(mock[VoteRepository], mock[PropositionRepository])
    val vote = new Vote("PROPOSITION_ID", "IDENTITY_ID")
    val proposition = new Proposition("ENTITY_ID")
    when(service.propositionRepository.findOne("PROPOSITION_ID"))
        .thenReturn(proposition)
    when(service.repository.findByPropositionIdAndIdentityId("PROPOSITION_ID", "IDENTITY_ID"))
      .thenReturn(vote)
    when(service.repository.save(vote))
      .thenReturn(vote)
    service.saveOrUpdate("PROPOSITION_ID", "IDENTITY_ID", 1000) should have (
      'propositionId ("PROPOSITION_ID")
      , 'identityId ("IDENTITY_ID")
      , 'vote (1000)
    )
  }

  "A condition returning a previously saved vote" should "save the vote" in {
    val service = new VoteService(mock[VoteRepository], mock[PropositionRepository])
    val vote = new Vote("PROPOSITION_ID", "IDENTITY_ID")
    val proposition = new Proposition("ENTITY_ID")
    when(service.propositionRepository.findOne("PROPOSITION_ID"))
      .thenReturn(proposition)
    when(service.repository.findByPropositionIdAndIdentityId("PROPOSITION_ID", "IDENTITY_ID"))
      .thenReturn(null)
    import org.mockito.Matchers._
    when(service.repository.save(any(classOf[Vote])))
      .thenReturn(vote)
    service.saveOrUpdate("PROPOSITION_ID", "IDENTITY_ID", 1000) should have (
      'propositionId ("PROPOSITION_ID")
      , 'identityId ("IDENTITY_ID")
    )
  }

  "A propositionId " should "not be null" in {
    val service = new VoteService(mock[VoteRepository], mock[PropositionRepository])
    intercept[IllegalArgumentException] { service.saveOrUpdate(null, null, 0) }
  }

  "An entityId " should "not be null" in {
    val service = new VoteService(mock[VoteRepository], mock[PropositionRepository])
    intercept[IllegalArgumentException] { service.saveOrUpdate("PROPOSITION_ID", null, 0) }
  }

}
