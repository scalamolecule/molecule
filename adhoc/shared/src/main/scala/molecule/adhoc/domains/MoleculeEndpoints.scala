package molecule.adhoc.domains

import java.nio.ByteBuffer
import boopickle.CompositePickler
import boopickle.Default._
import molecule.base.error._
import sttp.tapir.EndpointIO.Info
import sttp.tapir.RawBodyType.ByteBufferBody
import sttp.tapir._


trait MoleculeEndpoints {

  type publicEndpoint = PublicEndpoint[ByteBuffer, MoleculeError, ByteBuffer, Any]

  implicit private val pickleInsertError: CompositePickler[InsertError] =
    compositePickler[InsertError]

  implicit private val pickleMoleculeError: Pickler[MoleculeError] =
    generatePickler[MoleculeError]

  private val codec = Codec.byteBuffer.map(
    Unpickle[MoleculeError].fromBytes(_)
  )(
    Pickle.intoBytes(_)
  )

  private val errors = EndpointIO.Body(ByteBufferBody, codec, Info.empty)

  private def mkEndpoint(action: String): publicEndpoint =
    endpoint.post
      .in("molecule" / action)
      .in(byteBufferBody)
      .out(byteBufferBody)
      .errorOut(errors)

  val moleculeEndpoint_Query      : publicEndpoint = mkEndpoint("query")
  val moleculeEndpoint_QueryOffset: publicEndpoint = mkEndpoint("queryOffset")
  val moleculeEndpoint_QueryCursor: publicEndpoint = mkEndpoint("queryCursor")
  val moleculeEndpoint_QueryStream: publicEndpoint = mkEndpoint("queryStream")
  val moleculeEndpoint_Save       : publicEndpoint = mkEndpoint("save")
  val moleculeEndpoint_Insert     : publicEndpoint = mkEndpoint("insert")
  val moleculeEndpoint_Update     : publicEndpoint = mkEndpoint("update")
  val moleculeEndpoint_Delete     : publicEndpoint = mkEndpoint("delete")

  val moleculeEndpoints: List[publicEndpoint] = List(
    moleculeEndpoint_Query,
    moleculeEndpoint_QueryOffset,
    moleculeEndpoint_QueryCursor,
    moleculeEndpoint_QueryStream,
    moleculeEndpoint_Save,
    moleculeEndpoint_Insert,
    moleculeEndpoint_Update,
    moleculeEndpoint_Delete,
  )
}