package org.helianto.politikei.controller

import java.security.Principal

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HomeController {

  @RequestMapping
  def home(principal: Principal): String = s"Hello ${principal.getName}"

}
