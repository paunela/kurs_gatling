package perf

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import perf.Actions._

object CommonScenario{
  def apply(): ScenarioBuilder = new CommonScenario().mainScenario
}

class CommonScenario {
val open = group(name="open") {
  exec(webtours)
    .exec (welcomeOpen)
    .exec (navOpen)
}
val login = group(name="login"){
  exec(loginPL)
    .exec(navLogin)
    .exec(loginIntro)
}

  val flights =group(name="flights"){
    exec(findFlights)
      .exec(navFlights)
      .exec(reservationsOpen)
      .feed(Feeders.cityDepart)
      .feed(Feeders.cityArrive)
      .exec(reservationsFindFlight)
      .exec(reservationsChoiceFlight)
      .exec(reservationsPaymentFlight)
  }

  val itenerary = group (name = "itenerary"){
    exec(welcomeItinerary)
      .exec(navItinerary)
      .exec(itinerary)
  }

  val signOff = group (name = "signOff"){
    exec(welcomeSignOff)
      .exec(navOpen)
  }

  val mainScenario = scenario("mainScenario")
    .feed(Feeders.users)
    .exec(open)
    .exec(login)
    .exec(flights)
    .exec(itenerary)
    .exec(signOff)
}
