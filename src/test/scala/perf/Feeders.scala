package perf

import io.gatling.core.Predef.{configuration, csv}


object Feeders {
val users = csv( fileName = "user.csv").circular
  val cityDepart=csv( fileName = "cityDepart.csv").random
  val cityArrive=csv( fileName = "cityArrive.csv").random
}
