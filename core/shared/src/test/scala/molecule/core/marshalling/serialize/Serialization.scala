package molecule.core.marshalling.serialize

import java.nio.ByteBuffer
import molecule.base.error.MoleculeError
import molecule.boilerplate.ast.Model._
import boopickle.Default._
import molecule.core.marshalling.Boopicklers._
import utest._


object Serialization extends TestSuite {

  override lazy val tests = Tests {

    "No base attrs with tx meta data" - {
      val elements = List(
        AttrOneManInt("R2", "i", V, Seq(), None, None, Nil, Nil, None, None),
        AttrOneManString("R2", "s", V, Seq(), None, None, Nil, Nil, None, None)
      )
      val data         = Right(List((2, "b")))
      val serialized   = PickleTpls(elements, false).pickle(data)
      val deserialized = Unpickle.apply[Either[MoleculeError, List[(Int, String)]]].fromBytes(ByteBuffer.wrap(serialized))

      deserialized ==> data
    }
  }
}

