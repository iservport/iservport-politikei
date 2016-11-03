package org.helianto.politikei.controller

import java.security.Principal

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(Array("/"))
class HomeController {

  @RequestMapping
  def home(principal: Principal): String = "welcome.html"

}
