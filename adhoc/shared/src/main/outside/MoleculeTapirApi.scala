//package molecule.adhoc.domains
//
//import java.nio.ByteBuffer
//import boopickle.Default._
//import molecule.base.error.MoleculeError
//import molecule.core.ast.DataModel
//import molecule.core.ast.DataModel.Element
//import molecule.core.marshalling.ConnProxy
//import sttp.tapir._
//
//object MoleculeTapirApi {
//
//  def booCodec[T: Pickler]: EndpointIO.Body[Array[Byte], T] = {
//    // Define the codec explicitly, making sure we pass the correct transformations
//    val codec: Codec[Array[Byte], T, CodecFormat.OctetStream] =
//      Codec.byteArray.map[T](
//        // Unpickle the bytes to type T
//        (bytes: Array[Byte]) => Unpickle[T].fromBytes(ByteBuffer.wrap(bytes))
//      )(
//        // Pickle type T to bytes
//        (t: T) => Pickle.intoBytes(t).array()
//      )
//
//    // Return the binaryBody with the codec
//    binaryBody(RawBodyType.ByteArrayBody)(codec)
//  }
//
//
//  // Query Endpoint (Using binaryBodyx for serialization)
//  val queryEndpoint: Endpoint[Unit, (ConnProxy, List[DataModel.Element], Option[Long]), String, Either[MoleculeError, List[String]], Any] = ???
////    endpoint.post
////      .in("molecule" / "query")
////      .in(booCodec[(ConnProxy, List[Element], Option[Long])]) // Expecting binary input
////      .errorOut(stringBody)
////      .out(booCodec[Either[MoleculeError, List[String]]]) // Responding in binary format
//
//  val queryEndpoint2: Endpoint[Unit, ByteBuffer, String, ByteBuffer, Any] = ???
////    endpoint.post
////      .in("molecule" / "query")
//////      .in(booCodec[(ConnProxy, List[Element], Option[Long])]) // Expecting binary input
////      .in(booCodec[ByteBuffer]) // Expecting binary input
////      .errorOut(stringBody)
//////      .out(booCodec[Either[MoleculeError, List[String]]]) // Responding in binary format
//////      .out(booCodec[Either[MoleculeError, Array[Byte]]]) // Responding in binary format
//////      .out(booCodec[Array[Byte]]) // Responding in binary format
////      .out(booCodec[ByteBuffer]) // Responding in binary format
//
//  //  // Query Stream (fs2.Stream)
//  //  val queryFs2Endpoint: PublicEndpoint[Array[Byte], String, Stream[IO, Byte], Fs2Streams[IO]] =
//  //    endpoint.post
//  //      .in("molecule" / "queryStreamFs2")
//  //      .in(binaryBody(RawBodyType.ByteArrayBody)) // Request as binary
//  //      .errorOut(stringBody)
//  //      .out(streamBody(Fs2Streams[IO])(_.bytes))
//  //
//  //  // Query Stream (ZStream)
//  //  val queryZStreamEndpoint: PublicEndpoint[Array[Byte], String, ZStream[Any, Throwable, Byte], ZioStreams] =
//  //    endpoint.post
//  //      .in("molecule" / "queryStreamZio")
//  //      .in(binaryBody(RawBodyType.ByteArrayBody)) // Request as binary
//  //      .errorOut(stringBody)
//  //      .out(streamBody(ZioStreams)(_.bytes))
//}