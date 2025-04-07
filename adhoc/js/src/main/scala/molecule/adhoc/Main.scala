package molecule.adhoc

import boopickle.Default._
import molecule.adhoc.domains.HelloApi._

object Main {

  def main(args: Array[String]): Unit = {
    val client = MoleculeClient("localhost", 8080)
    client.callServer[String](helloEndpoint, Pickle.intoBytes[String]("Scala.js User"))
    client.callServer[String](helloEndpoint, Pickle.intoBytes[String]("bob"))
//    client.callServer2(helloEndpoint, Pickle.intoBytes[String]("Scala.js User"))
//    client.callServer2(helloEndpoint, Pickle.intoBytes[String]("bob"))
  }
}