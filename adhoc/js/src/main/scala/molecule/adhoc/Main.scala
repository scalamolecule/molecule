package molecule.adhoc

import boopickle.Default._
import molecule.adhoc.domains.MoleculeEndpoints
import molecule.core.util.Executor._
import sttp.client4._


object Main extends MoleculeClient(uri"http://localhost:8080") with MoleculeEndpoints{

  def main(args: Array[String]): Unit = {

    fetch[String](moleculeEndpoint_Query, Pickle.intoBytes("world"))
      .foreach(r => println(r))
  }
}