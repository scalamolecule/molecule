package molecule.adhoc


import boopickle.Default._
import molecule.adhoc.domains.HelloApi._
import molecule.base.error.{ExecutionError, ModelError, MoleculeError}
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.server.netty._
import scala.concurrent.{ExecutionContext, Future}
import scala.io.StdIn

object HelloServer {
  implicit val ec: ExecutionContext = ExecutionContext.global

  // Define the server logic
  private val helloServerEndpoint: ServerEndpoint[Any, Future] =
    helloEndpoint.serverLogic[Future] { args =>
      Future {
        try {
          val name = Unpickle[String].fromBytes(args)

          println("--- " + name)

          if (name == "bob")
            throw ModelError("BAM!!!")
          val response = HelloLogic.hello(name)
          Right(Pickle.intoBytes(response))
        } catch {
          case e: MoleculeError => Left(e) // User errors
          case e: Throwable     => Left(ExecutionError(e.getMessage)) // Db/pickling errors
        }
      }
    }

  // Start the server
  def main(args: Array[String]): Unit = {
    val server = NettyFutureServer()
      .port(8080)
      .addEndpoint(helloServerEndpoint)
      //      .addEndpoint(greetServerEndpoint)
      .start()

    server.foreach { _ =>
      println("Server running on http://localhost:8080")
    }

    println("Press ENTER to stop the server.")
    StdIn.readLine() // Blocks until user presses ENTER

    println("Shutting down server...")
    server.flatMap(_.stop())
  }
}
