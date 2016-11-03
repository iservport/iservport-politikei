package org.helianto.politikei.service

import org.springframework.security.oauth2.provider.OAuth2Authentication

/**
  * Mix-in to implicitly extract entity or identity from the principal.
  */
trait AuthorityExtractor {

  def _entityId(implicit principal: OAuth2Authentication) = id(principal, "ENTITY_ID_")

  def _identityId(implicit principal: OAuth2Authentication) = id(principal, "SELF_ID_")

  private def id(principal: OAuth2Authentication, prefix: String) = {
    import collection.JavaConversions._
    principal
      .getAuthorities
      .filter(_.toString.startsWith(prefix))
      .map(_.toString.substring(prefix.length))
      .headOption.getOrElse("")
  }

}
