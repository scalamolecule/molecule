package molecule.coreTests.marshalling.serialize

import java.nio.ByteBuffer
import boopickle.Default._
import molecule.base.error.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.serialize.PickleTpls
import utest._


object Serialization extends TestSuite {

  override lazy val tests = Tests {

    "No base attrs with tx meta data" - {
      val elements     = List(
        AttrOneManInt("R2", "i", V, Seq(), None, None, Nil, Nil, None, None),
        AttrOneManString("R2", "s", V, Seq(), None, None, Nil, Nil, None, None)
      )
      val data         = Right(List((2, "b")))
      val serialized   = PickleTpls(elements, false).pickleEither(data)
      val deserialized = Unpickle.apply[Either[MoleculeError, List[(Int, String)]]].fromBytes(ByteBuffer.wrap(serialized))

      deserialized ==> data
    }

//    "Seq" - {
//      val elements     = List(
//        AttrOneManInt("Ns", "i", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 1)),
//        AttrMapOptShort("Ns", "shortMap", Has, Some(Map(("a", null))), None, None, Nil, Nil, None, None, Seq(0, 96))
//      )
//      val data         = Right(List((2, "b")))
//      val serialized   = PickleTpls(elements, false).pickle(data)
//      val deserialized = Unpickle.apply[Either[MoleculeError, List[(Int, String)]]].fromBytes(ByteBuffer.wrap(serialized))
//
//      deserialized ==> data
//    }
  }
}

