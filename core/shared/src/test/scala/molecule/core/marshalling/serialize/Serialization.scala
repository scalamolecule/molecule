package molecule.core.marshalling.serialize

import java.nio.ByteBuffer
import boopickle.Default._
import molecule.base.error.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.Boopicklers._
import utest._


object Serialization extends TestSuite {

  override lazy val tests = Tests {

    "No base attrs with tx data" - {
      val elements     = List(
        AttrOneTacInt("R1", "i", V, Seq(), None, None, Nil, Nil, None, None),
        TxData(List(
          AttrOneManInt("R2", "i", V, Seq(), None, None, Nil, Nil, None, None),
          AttrOneManString("R2", "s", V, Seq(), None, None, Nil, Nil, None, None))
        )
      )
      val data         = Right(List((2, "b")))
      val serialized   = PickleTpls(elements, false).pickle(data)
      val deserialized = Unpickle.apply[Either[MoleculeError, List[(Int, String)]]].fromBytes(ByteBuffer.wrap(serialized))

      deserialized ==> data
    }

    "2" - {
      val elements     = List(
        AttrOneManInt("R1", "i", V, Seq(), None, None, Nil, Nil, None, None),
        TxData(List(
          Composite(List(
            AttrOneTacInt("R2", "i", V, Seq(), None, None, Nil, Nil, None, None))),
          Composite(List(
            AttrOneTacInt("R3", "i", V, Seq(), None, None, Nil, Nil, None, None)))))
      )
      val data         = Right(List(1))
      val serialized   = PickleTpls(elements, false).pickle(data)
      val deserialized = Unpickle.apply[Either[MoleculeError, List[Int]]].fromBytes(ByteBuffer.wrap(serialized))

      deserialized ==> data
    }

    "3" - {
      val elements     = List(
        Composite(List(
          AttrOneTacInt("R1", "i", V, Seq(), None, None, Nil, Nil, None, None))),
        Composite(List(
          AttrOneManInt("R2", "i", V, Seq(), None, None, Nil, Nil, None, None),
          AttrOneManString("R2", "s", V, Seq(), None, None, Nil, Nil, None, None))),
        TxData(List(
          AttrOneManInt("R3", "i", V, Seq(), None, None, Nil, Nil, None, None),
          AttrOneManString("R3", "s", V, Seq(), None, None, Nil, Nil, None, None)))
      )
      val data         = Right(List(((2, "b"), 3, "c")))
      val serialized   = PickleTpls(elements, false).pickle(data)
      val deserialized = Unpickle.apply[Either[MoleculeError, List[((Int, String), Int, String)]]].fromBytes(ByteBuffer.wrap(serialized))

      deserialized ==> data
    }
  }
}

