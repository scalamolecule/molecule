package molecule.adhoc.domains

import java.nio.ByteBuffer
import boopickle.Default._
import molecule.base.error._
import sttp.model.StatusCode
import sttp.tapir.EndpointIO.Info
import sttp.tapir.RawBodyType.ByteBufferBody
import sttp.tapir._

object HelloApi {

  private def boopickleCodec[T: Pickler]: Codec[ByteBuffer, T, CodecFormat.OctetStream] =
    Codec.byteBuffer.map[T](Unpickle[T].fromBytes(_))(Pickle.intoBytes(_))

  private val errors: EndpointOutput.OneOf[MoleculeError, MoleculeError] = oneOf(
    oneOfVariant(StatusCode.BadRequest,
      EndpointIO.Body(ByteBufferBody, boopickleCodec[ModelError], Info.empty)
    ),
    oneOfVariant(StatusCode.InternalServerError,
      EndpointIO.Body(ByteBufferBody, boopickleCodec[ExecutionError], Info.empty)
    ),
    oneOfVariant(StatusCode.UnprocessableEntity,
      EndpointIO.Body(ByteBufferBody, boopickleCodec[ValidationErrors], Info.empty)
    )
  )

  val helloEndpoint = endpoint.post.in("molecule/hello").in(byteBufferBody).out(byteBufferBody).errorOut(errors)
  val greetEndpoint = endpoint.post.in("molecule/greet").in(byteBufferBody).out(byteBufferBody).errorOut(errors)
}