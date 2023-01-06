package molecule.core.marshalling

import java.nio.ByteBuffer
import cats.implicits._
import molecule.core.util.Executor._
import sloth._
import scala.concurrent.Future

trait WebClient {

  def moleculeAjax(moleculeRpcRequest: MoleculeRpcRequest): ClientCo[ByteBuffer, Future] =
    Client.apply[ByteBuffer, Future](moleculeRpcRequest)
}