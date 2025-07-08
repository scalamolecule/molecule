package molecule.db.common.marshalling

import java.nio.ByteBuffer
import boopickle.CompositePickler
import boopickle.Default.*
import molecule.base.error.{InsertError, MoleculeError}
import molecule.db.common.marshalling.Boopicklers.*
import sttp.tapir.*
import sttp.tapir.EndpointIO.Info
import sttp.tapir.RawBodyType.ByteBufferBody

trait MoleculeEndpoints {

  type publicEndpoint = PublicEndpoint[ByteBuffer, MoleculeError, ByteBuffer, Any]

  implicit private val pickleInsertError: CompositePickler[InsertError] =
    compositePickler[InsertError]

  implicit private val pickleMoleculeError: Pickler[MoleculeError] =
    generatePickler[MoleculeError]

  private val boopickleCodec: Codec[ByteBuffer, MoleculeError, CodecFormat.OctetStream] =
    Codec.byteBuffer.map(
      Unpickle[MoleculeError].fromBytes(_)
    )(
      Pickle.intoBytes(_)
    )

  private val errors = EndpointIO.Body(ByteBufferBody, boopickleCodec, Info.empty)

  private def mkEndpoint(action: String): publicEndpoint =
    endpoint.post
      .in("molecule" / action)
      .in(byteBufferBody)
      .out(byteBufferBody)
      .errorOut(errors)

  val moleculeEndpoint_query      : publicEndpoint = mkEndpoint("query")
  val moleculeEndpoint_queryOffset: publicEndpoint = mkEndpoint("queryOffset")
  val moleculeEndpoint_queryCursor: publicEndpoint = mkEndpoint("queryCursor")
  val moleculeEndpoint_unsubscribe: publicEndpoint = mkEndpoint("unsubscribe")
  val moleculeEndpoint_save       : publicEndpoint = mkEndpoint("save")
  val moleculeEndpoint_insert     : publicEndpoint = mkEndpoint("insert")
  val moleculeEndpoint_update     : publicEndpoint = mkEndpoint("update")
  val moleculeEndpoint_delete     : publicEndpoint = mkEndpoint("delete")

  // No subscribe endpoint here since each server has its own websocket implementation
}