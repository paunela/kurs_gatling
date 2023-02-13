package perf

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Actions {

  <!-- open -->
  val webtours = http("/webtours/")
    .get("/webtours/")
    .check(status is 200)

  val welcomeOpen = http("/cgi-bin/welcome.pl?signOff=true")
    .get("/cgi-bin/welcome.pl")
    .queryParam("signOff",true)
    .check(status is 200)

  val navOpen = http("/cgi-bin/nav.pl?in=home")
    .get("/cgi-bin/nav.pl")
    .queryParam("in","home")
    .check(status is 200)
    .check(regex(pattern = """name="userSession" value="(.+)"""").saveAs(key = "userSession"))

  <!-- Login -->
  val loginPL = http(requestName = "/cgi-bin/login.pl")
    .post("/cgi-bin/login.pl")
    .formParam("userSession","${userSession}")
    .formParam("username","${login}")
    .formParam("password","${password}")
    .formParam("login.x","0")
    .formParam("login.y","0")
    .formParam("JSFormSubmit","off")
    .check(status is 200)

  val navLogin = http("/cgi-bin/nav.pl?page=menu&in=home")
    .get("/cgi-bin/nav.pl")
    .queryParam("in","home")
    .queryParam("page","menu")
    .check(status is 200)

  val loginIntro = http(requestName = "/cgi-bin/login.pl?intro=true")
    .post("/cgi-bin/login.pl")
    .queryParam("intro","true")
    .check(status is 200)

  <!-- Find Flights-->
  val findFlights = http("/cgi-bin/welcome.pl?page=search")
    .get("/cgi-bin/welcome.pl")
    .queryParam("page","search")
    .check(status is 200)

  val navFlights = http("/cgi-bin/nav.pl?page=menu&in=flights")
    .get("/cgi-bin/nav.pl")
    .queryParam("in","flights")
    .queryParam("page","menu")
    .check(status is 200)

  val reservationsOpen = http("/cgi-bin/reservations.pl?page=welcome")
    .get("/cgi-bin/reservations.pl")
    .queryParam("page","welcome")
    .check(status is 200)
    .check(css("input[name='departDate']","value").find.saveAs(key = "departDate"))
    .check(css("input[name='returnDate']","value").find.saveAs(key = "returnDate"))

  <!-- continueFlights -->
  val reservationsFindFlight = http("/cgi-bin/reservations.pl")
    .post("/cgi-bin/reservations.pl")
    .formParam("advanceDiscount","0")
    .formParam("depart","${cityDepart}")
    .formParam("departDate","${departDate}")
    .formParam("arrive","${cityArrive}")
    .formParam("returnDate","${returnDate}")
    .formParam("numPassengers","1")
    .formParam("seatPref","None")
    .formParam("seatType","Coach")
    .formParam("findFlights.x","46")
    .formParam("findFlights.y","10")
    .formParam(".cgifields","roundtrip")
    .formParam(".cgifields","seatType")
    .formParam(".cgifields","seatPref")
    .check(status is 200)
    .check(css("input[name='outboundFlight']","value").findRandom.saveAs(key = "outboundFlightChoice"))


  <!-- ChoiceFlight -->
  val reservationsChoiceFlight = http("/cgi-bin/reservations.pl")
    .post("/cgi-bin/reservations.pl")
    .formParam("outboundFlight","${outboundFlightChoice}")
    .formParam("numPassengers","1")
    .formParam("advanceDiscount",0)
    .formParam("seatType","Coach")
    .formParam("seatPref","None")
    .formParam("reserveFlights.x",46)
    .formParam("reserveFlights.y",9)
    .check(status is 200)

  <!-- Payment Flight-->
  val reservationsPaymentFlight = http("/cgi-bin/reservations.pl")
    .post("/cgi-bin/reservations.pl")
    .formParam("firstName","Alina")
    .formParam("lastName","Alexeevna")
    .formParam("address1","Moskovski")
    .formParam("address2","Vorohezh")
    .formParam("pass1","Alina Alexeevna")
    .formParam("creditCard",193759304)
    .formParam("expDate","02/50")
    .formParam("oldCCOption","")
    .formParam("numPassengers",1)
    .formParam("seatType","Coach")
    .formParam("seatPref","None")
    .formParam("outboundFlight","${outboundFlightChoice}")
    .formParam("advanceDiscount",0)
    .formParam("returnFlight","")
    .formParam("JSFormSubmit","off")
    .formParam("buyFlights.x",46)
    .formParam("buyFlights.y",13)
    .formParam(".cgifields","saveCC")
    .check(status is 200)

  <!-- Itenerary -->
  val welcomeItinerary = http("/cgi-bin/welcome.pl?page=itinerary")
    .get("/cgi-bin/welcome.pl")
    .queryParam("page","itinerary")
    .check(status is 200)

  val navItinerary = http("/cgi-bin/nav.pl?page=menu&in=itinerary")
    .get("/cgi-bin/nav.pl")
    .queryParam("in","itinerary")
    .queryParam("page","menu")
    .check(status is 200)

  val itinerary = http("/cgi-bin/itinerary.pl")
    .get("/cgi-bin/itinerary.pl")
    .check(status is 200)

  <!-- sign off-->
  val welcomeSignOff = http("/cgi-bin/welcome.pl?signOff=1")
    .get("/cgi-bin/welcome.pl")
    .queryParam("signOff","1")
    .check(status is 200)
}
