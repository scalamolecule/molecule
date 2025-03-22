//package molecule
//
//import boopickle.Default._
//import cats.effect.IO
//import sttp.tapir._
//import sttp.tapir.server._
//import sttp.capabilities.fs2.Fs2Streams
//import sttp.capabilities.zio.ZioStreams
//import fs2.Stream
//import zio._
//import zio.stream.ZStream
//import java.nio.ByteBuffer
//
//
//object MoleculeTapirApi {
//
//  // Helper for Boopickle serialization
////  def binaryBodyx[T: Pickler]: EndpointIO.Body[Array[Byte], T] =
////    binaryBody[Array[Byte], T](Codec.byteArray.mapPickler(identity))
//
////  def binaryBodyx[T: Pickler]: EndpointIO.Body[Array[Byte], T] =
////    binaryBody(RawBodyType.ByteArrayBody).map(boopickle.Unpickle[T].fromBytes)(boopickle.Pickle.apply)
//
////  def binaryBodyx[T: Pickler]: EndpointIO.Body[Array[Byte], T] =
////    binaryBody(RawBodyType.ByteArrayBody). map(boopickle.Unpickle[T].fromBytes)(boopickle.Pickle.apply)
//
////  def binaryBodyx[T: Pickler]: EndpointIO.Body[Array[Byte], T] =
////    binaryBody(RawBodyType.ByteArrayBody)
////      .map[T](bytes => Unpickle[T].fromBytes(ByteBuffer.wrap(bytes)))(t => Pickle.intoBytes(t).array())
//
////  import sttp.tapir._
////  import boopickle.Default._
////  import java.nio.ByteBuffer
////
////  def binaryBodyx[T: Pickler]: EndpointIO.Body[Array[Byte], T] =
////    binaryBody(RawBodyType.ByteArrayBody)
////      .map[T](
////        bytes => Unpickle[T].fromBytes(ByteBuffer.wrap(bytes))
////      )(
////        t => Pickle.intoBytes(t).array()
////      )(CodecFormat.OctetStream())
//
////  import sttp.tapir._
////  import sttp.tapir.Codec
////  import sttp.tapir.CodecFormat
////  import boopickle.Default._
////  import java.nio.ByteBuffer
////
////  def binaryBodyx[T: Pickler]: EndpointIO.Body[Array[Byte], T] = {
////    val codec: Codec[Array[Byte], T, CodecFormat.OctetStream] =
////      Codec.binary(CodecFormat.OctetStream())(
////        bytes => Right(Unpickle[T].fromBytes(ByteBuffer.wrap(bytes))),
////        t => Pickle.intoBytes(t).array()
////      )
////
////    binaryBody(RawBodyType.ByteArrayBody, codec)
////  }
//
//  import sttp.tapir._
//  import sttp.tapir.Codec
//  import sttp.tapir.CodecFormat
//  import boopickle.Default._
//  import java.nio.ByteBuffer
//
//  def binaryBodyx[T: Pickler]: EndpointIO.Body[Array[Byte], T] = {
//    val codec: Codec[Array[Byte], T, CodecFormat.OctetStream] =
//      Codec.byteArray.map[T](
//        bytes => Unpickle[T].fromBytes(ByteBuffer.wrap(bytes))
//      )(
//        t => Pickle.intoBytes(t).array()
//      )
//
//    binaryBody(RawBodyType.ByteArrayBody)(codec)
//  }
//
//  def queryEndpoint[T: Pickler]: PublicEndpoint[T, String, T, Any] =
//    endpoint.post
//      .in("molecule" / "query")
//      .in(binaryBodyx[T]) // Use the helper function for input
//      .errorOut(stringBody)
//      .out(binaryBodyx[T]) // Use the helper function for output
//
//
//  //  val queryEndpoint: PublicEndpoint[T, String, T, Any] =
////    endpoint.post
////      .in("molecule" / "query")
////      .in(binaryBodyx[T])  // Uses the helper
////      .errorOut(stringBody)
////      .out(binaryBodyx[T]) // Ensures consistent serialization
//
////  import sttp.tapir._
////  import sttp.tapir.Codec
////  import sttp.tapir.CodecFormat
////  import boopickle.Default._
////  import java.nio.ByteBuffer
////
////  implicit val byteArrayCodec: Codec[Array[Byte], Array[Byte], CodecFormat.OctetStream] =
////    Codec.byteArray.map(identity)(identity)
////
////  val queryEndpoint: PublicEndpoint[Array[Byte], String, Array[Byte], Any] =
////    endpoint.post
////      .in("molecule" / "query")
////      .in(binaryBodyx(RawBodyType.ByteArrayBody)) // Now uses implicit codec
////      .errorOut(stringBody)
////      .out(binaryBodyx(RawBodyType.ByteArrayBody)) // Now uses implicit codec
//
//
//
//
////  // Query (Future API)
////  val queryEndpoint: PublicEndpoint[Array[Byte], String, Array[Byte], Any] =
////    endpoint.post
////      .in("molecule" / "query")
////      .in(binaryBody(RawBodyType.ByteArrayBody)) // Corrected
////      .errorOut(stringBody)
////      .out(binaryBody(RawBodyType.ByteArrayBody)) // Corrected
//
//  // Query Stream (fs2.Stream)
//  val fs2StreamEndpoint: PublicEndpoint[Array[Byte], String, Stream[IO, Byte], Fs2Streams[IO]] =
//    endpoint.post
//      .in("molecule" / "queryStream")
//      .in(binaryBody(RawBodyType.ByteArray))
//      .errorOut(stringBody)
//      .out(streamBody(Fs2Streams[IO])(_.bytes))
//
//  // Query Stream (ZStream)
//  val zStreamEndpoint: PublicEndpoint[Array[Byte], String, ZStream[Any, Throwable, Byte], ZioStreams] =
//    endpoint.post
//      .in("molecule" / "queryStream")
//      .in(binaryBody(RawBodyType.ByteArray))
//      .errorOut(stringBody)
//      .out(streamBody(ZioStreams)(_.bytes))
//}
