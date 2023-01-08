package molecule.boilerplate.ast


import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._
import utest._
import scala.collection.immutable.{Seq, Set}

object AttrToString extends TestSuite with Validations {

  // Indent dummy to align
  val attr = Model


  lazy val tests = Tests {

    "One" - {
      "One mandatory" - {
        attr.AttrOneManString("Ns", "string", V, Seq("a", "b"), Some("a"), None, Some("a1")).toString ==>
          """AttrOneManString("Ns", "string", V, Seq("a", "b"), Some("a"), None, Some("a1"))"""

        attr.AttrOneManInt("Ns", "int", V, Seq(1, 2), Some(1), None, Some("a1")).toString ==>
          """AttrOneManInt("Ns", "int", V, Seq(1, 2), Some(1), None, Some("a1"))"""

        attr.AttrOneManLong("Ns", "long", V, Seq(1L, 2L), Some(1L), None, Some("a1"), true).toString ==>
          """AttrOneManLong("Ns", "long", V, Seq(1L, 2L), Some(1L), None, Some("a1"), true)"""

        attr.AttrOneManFloat("Ns", "float", V, Seq(1.1f, 2.2f), Some(1.1f), None, Some("a1")).toString ==>
          """AttrOneManFloat("Ns", "float", V, Seq(1.1f, 2.2f), Some(1.1f), None, Some("a1"))"""

        attr.AttrOneManDouble("Ns", "double", V, Seq(1.1, 2.2), Some(1.1), None, Some("a1")).toString ==>
          """AttrOneManDouble("Ns", "double", V, Seq(1.1, 2.2), Some(1.1), None, Some("a1"))"""

        attr.AttrOneManBoolean("Ns", "boolean", V, Seq(true, false), Some(true), None, Some("a1")).toString ==>
          """AttrOneManBoolean("Ns", "boolean", V, Seq(true, false), Some(true), None, Some("a1"))"""

        attr.AttrOneManBigInt("Ns", "bigInt", V, Seq(BigInt(1), BigInt(2)), Some(BigInt(1)), None, Some("a1")).toString ==>
          """AttrOneManBigInt("Ns", "bigInt", V, Seq(BigInt(1), BigInt(2)), Some(BigInt(1)), None, Some("a1"))"""

        attr.AttrOneManBigDecimal("Ns", "bigDecimal", V, Seq(BigDecimal(1.1), BigDecimal(2.2)), Some(BigDecimal(1.1)), None, Some("a1")).toString ==>
          """AttrOneManBigDecimal("Ns", "bigDecimal", V, Seq(BigDecimal(1.1), BigDecimal(2.2)), Some(BigDecimal(1.1)), None, Some("a1"))"""

        attr.AttrOneManDate("Ns", "date", V, Seq(new Date(1), new Date(2)), Some(new Date(1)), None, Some("a1")).toString ==>
          """AttrOneManDate("Ns", "date", V, Seq(new Date(1), new Date(2)), Some(new Date(1)), None, Some("a1"))"""

        attr.AttrOneManUUID("Ns", "uuid", V, Seq(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), Some(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f")), None, Some("a1")).toString ==>
          """AttrOneManUUID("Ns", "uuid", V, Seq(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), Some(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f")), None, Some("a1"))"""

        attr.AttrOneManURI("Ns", "uri", V, Seq(new URI("a"), new URI("b")), Some(new URI("a")), None, Some("a1")).toString ==>
          """AttrOneManURI("Ns", "uri", V, Seq(new URI("a"), new URI("b")), Some(new URI("a")), None, Some("a1"))"""

        attr.AttrOneManByte("Ns", "byte", V, Seq(1.toByte, 2.toByte), Some(1.toByte), None, Some("a1")).toString ==>
          """AttrOneManByte("Ns", "byte", V, Seq(1.toByte, 2.toByte), Some(1.toByte), None, Some("a1"))"""

        attr.AttrOneManShort("Ns", "short", V, Seq(1.toShort, 2.toShort), Some(1.toShort), None, Some("a1")).toString ==>
          """AttrOneManShort("Ns", "short", V, Seq(1.toShort, 2.toShort), Some(1.toShort), None, Some("a1"))"""

        attr.AttrOneManChar("Ns", "char", V, Seq('a', 'b'), Some('a'), None, Some("a1")).toString ==>
          """AttrOneManChar("Ns", "char", V, Seq('a', 'b'), Some('a'), None, Some("a1"))"""
      }

      "One tacit" - {
        attr.AttrOneTacString("Ns", "string", V, Seq("a", "b"), Some("a"), None, Some("a1")).toString ==>
          """AttrOneTacString("Ns", "string", V, Seq("a", "b"), Some("a"), None, Some("a1"))"""

        attr.AttrOneTacInt("Ns", "int", V, Seq(1, 2), Some(1), None, Some("a1")).toString ==>
          """AttrOneTacInt("Ns", "int", V, Seq(1, 2), Some(1), None, Some("a1"))"""

        attr.AttrOneTacLong("Ns", "long", V, Seq(1L, 2L), Some(1L), None, Some("a1"), true).toString ==>
          """AttrOneTacLong("Ns", "long", V, Seq(1L, 2L), Some(1L), None, Some("a1"), true)"""

        attr.AttrOneTacFloat("Ns", "float", V, Seq(1.1f, 2.2f), Some(1.1f), None, Some("a1")).toString ==>
          """AttrOneTacFloat("Ns", "float", V, Seq(1.1f, 2.2f), Some(1.1f), None, Some("a1"))"""

        attr.AttrOneTacDouble("Ns", "double", V, Seq(1.1, 2.2), Some(1.1), None, Some("a1")).toString ==>
          """AttrOneTacDouble("Ns", "double", V, Seq(1.1, 2.2), Some(1.1), None, Some("a1"))"""

        attr.AttrOneTacBoolean("Ns", "boolean", V, Seq(true, false), Some(true), None, Some("a1")).toString ==>
          """AttrOneTacBoolean("Ns", "boolean", V, Seq(true, false), Some(true), None, Some("a1"))"""

        attr.AttrOneTacBigInt("Ns", "bigInt", V, Seq(BigInt(1), BigInt(2)), Some(BigInt(1)), None, Some("a1")).toString ==>
          """AttrOneTacBigInt("Ns", "bigInt", V, Seq(BigInt(1), BigInt(2)), Some(BigInt(1)), None, Some("a1"))"""

        attr.AttrOneTacBigDecimal("Ns", "bigDecimal", V, Seq(BigDecimal(1.1), BigDecimal(2.2)), Some(BigDecimal(1.1)), None, Some("a1")).toString ==>
          """AttrOneTacBigDecimal("Ns", "bigDecimal", V, Seq(BigDecimal(1.1), BigDecimal(2.2)), Some(BigDecimal(1.1)), None, Some("a1"))"""

        attr.AttrOneTacDate("Ns", "date", V, Seq(new Date(1), new Date(2)), Some(new Date(1)), None, Some("a1")).toString ==>
          """AttrOneTacDate("Ns", "date", V, Seq(new Date(1), new Date(2)), Some(new Date(1)), None, Some("a1"))"""

        attr.AttrOneTacUUID("Ns", "uuid", V, Seq(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), Some(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f")), None, Some("a1")).toString ==>
          """AttrOneTacUUID("Ns", "uuid", V, Seq(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), Some(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f")), None, Some("a1"))"""

        attr.AttrOneTacURI("Ns", "uri", V, Seq(new URI("a"), new URI("b")), Some(new URI("a")), None, Some("a1")).toString ==>
          """AttrOneTacURI("Ns", "uri", V, Seq(new URI("a"), new URI("b")), Some(new URI("a")), None, Some("a1"))"""

        attr.AttrOneTacByte("Ns", "byte", V, Seq(1.toByte, 2.toByte), Some(1.toByte), None, Some("a1")).toString ==>
          """AttrOneTacByte("Ns", "byte", V, Seq(1.toByte, 2.toByte), Some(1.toByte), None, Some("a1"))"""

        attr.AttrOneTacShort("Ns", "short", V, Seq(1.toShort, 2.toShort), Some(1.toShort), None, Some("a1")).toString ==>
          """AttrOneTacShort("Ns", "short", V, Seq(1.toShort, 2.toShort), Some(1.toShort), None, Some("a1"))"""

        attr.AttrOneTacChar("Ns", "char", V, Seq('a', 'b'), Some('a'), None, Some("a1")).toString ==>
          """AttrOneTacChar("Ns", "char", V, Seq('a', 'b'), Some('a'), None, Some("a1"))"""
      }

      "One optional" - {
        attr.AttrOneOptString("Ns", "string", V, Some(Seq("a", "b")), Some("a"), None, Some("a1")).toString ==>
          """AttrOneOptString("Ns", "string", V, Some(Seq("a", "b")), Some("a"), None, Some("a1"))"""

        attr.AttrOneOptInt("Ns", "int", V, Some(Seq(1, 2)), Some(1), None, Some("a1")).toString ==>
          """AttrOneOptInt("Ns", "int", V, Some(Seq(1, 2)), Some(1), None, Some("a1"))"""

        attr.AttrOneOptLong("Ns", "long", V, Some(Seq(1L, 2L)), Some(1L), None, Some("a1"), true).toString ==>
          """AttrOneOptLong("Ns", "long", V, Some(Seq(1L, 2L)), Some(1L), None, Some("a1"), true)"""

        attr.AttrOneOptFloat("Ns", "float", V, Some(Seq(1.1f, 2.2f)), Some(1.1f), None, Some("a1")).toString ==>
          """AttrOneOptFloat("Ns", "float", V, Some(Seq(1.1f, 2.2f)), Some(1.1f), None, Some("a1"))"""

        attr.AttrOneOptDouble("Ns", "double", V, Some(Seq(1.1, 2.2)), Some(1.1), None, Some("a1")).toString ==>
          """AttrOneOptDouble("Ns", "double", V, Some(Seq(1.1, 2.2)), Some(1.1), None, Some("a1"))"""

        attr.AttrOneOptBoolean("Ns", "boolean", V, Some(Seq(true, false)), Some(true), None, Some("a1")).toString ==>
          """AttrOneOptBoolean("Ns", "boolean", V, Some(Seq(true, false)), Some(true), None, Some("a1"))"""

        attr.AttrOneOptBigInt("Ns", "bigInt", V, Some(Seq(BigInt(1), BigInt(2))), Some(BigInt(1)), None, Some("a1")).toString ==>
          """AttrOneOptBigInt("Ns", "bigInt", V, Some(Seq(BigInt(1), BigInt(2))), Some(BigInt(1)), None, Some("a1"))"""

        attr.AttrOneOptBigDecimal("Ns", "bigDecimal", V, Some(Seq(BigDecimal(1.1), BigDecimal(2.2))), Some(BigDecimal(1.1)), None, Some("a1")).toString ==>
          """AttrOneOptBigDecimal("Ns", "bigDecimal", V, Some(Seq(BigDecimal(1.1), BigDecimal(2.2))), Some(BigDecimal(1.1)), None, Some("a1"))"""

        attr.AttrOneOptDate("Ns", "date", V, Some(Seq(new Date(1), new Date(2))), Some(new Date(1)), None, Some("a1")).toString ==>
          """AttrOneOptDate("Ns", "date", V, Some(Seq(new Date(1), new Date(2))), Some(new Date(1)), None, Some("a1"))"""

        attr.AttrOneOptUUID("Ns", "uuid", V, Some(Seq(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457"))), Some(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f")), None, Some("a1")).toString ==>
          """AttrOneOptUUID("Ns", "uuid", V, Some(Seq(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457"))), Some(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f")), None, Some("a1"))"""

        attr.AttrOneOptURI("Ns", "uri", V, Some(Seq(new URI("a"), new URI("b"))), Some(new URI("a")), None, Some("a1")).toString ==>
          """AttrOneOptURI("Ns", "uri", V, Some(Seq(new URI("a"), new URI("b"))), Some(new URI("a")), None, Some("a1"))"""

        attr.AttrOneOptByte("Ns", "byte", V, Some(Seq(1.toByte, 2.toByte)), Some(1.toByte), None, Some("a1")).toString ==>
          """AttrOneOptByte("Ns", "byte", V, Some(Seq(1.toByte, 2.toByte)), Some(1.toByte), None, Some("a1"))"""

        attr.AttrOneOptShort("Ns", "short", V, Some(Seq(1.toShort, 2.toShort)), Some(1.toShort), None, Some("a1")).toString ==>
          """AttrOneOptShort("Ns", "short", V, Some(Seq(1.toShort, 2.toShort)), Some(1.toShort), None, Some("a1"))"""

        attr.AttrOneOptChar("Ns", "char", V, Some(Seq('a', 'b')), Some('a'), None, Some("a1")).toString ==>
          """AttrOneOptChar("Ns", "char", V, Some(Seq('a', 'b')), Some('a'), None, Some("a1"))"""
      }
    }


    "One empty" - {
      "One mandatory empty" - {
        attr.AttrOneManString("Ns", "string", V, Seq(), None, None, None).toString ==>
          """AttrOneManString("Ns", "string", V, Seq(), None, None, None)"""

        attr.AttrOneManInt("Ns", "int", V, Seq(), None, None, None).toString ==>
          """AttrOneManInt("Ns", "int", V, Seq(), None, None, None)"""

        attr.AttrOneManLong("Ns", "long", V, Seq(), None, None, None).toString ==>
          """AttrOneManLong("Ns", "long", V, Seq(), None, None, None, false)""" // default boolean value shown

        attr.AttrOneManFloat("Ns", "float", V, Seq(), None, None, None).toString ==>
          """AttrOneManFloat("Ns", "float", V, Seq(), None, None, None)"""

        attr.AttrOneManDouble("Ns", "double", V, Seq(), None, None, None).toString ==>
          """AttrOneManDouble("Ns", "double", V, Seq(), None, None, None)"""

        attr.AttrOneManBoolean("Ns", "boolean", V, Seq(), None, None, None).toString ==>
          """AttrOneManBoolean("Ns", "boolean", V, Seq(), None, None, None)"""

        attr.AttrOneManBigInt("Ns", "bigInt", V, Seq(), None, None, None).toString ==>
          """AttrOneManBigInt("Ns", "bigInt", V, Seq(), None, None, None)"""

        attr.AttrOneManBigDecimal("Ns", "bigDecimal", V, Seq(), None, None, None).toString ==>
          """AttrOneManBigDecimal("Ns", "bigDecimal", V, Seq(), None, None, None)"""

        attr.AttrOneManDate("Ns", "date", V, Seq(), None, None, None).toString ==>
          """AttrOneManDate("Ns", "date", V, Seq(), None, None, None)"""

        attr.AttrOneManUUID("Ns", "uuid", V, Seq(), None, None, None).toString ==>
          """AttrOneManUUID("Ns", "uuid", V, Seq(), None, None, None)"""

        attr.AttrOneManURI("Ns", "uri", V, Seq(), None, None, None).toString ==>
          """AttrOneManURI("Ns", "uri", V, Seq(), None, None, None)"""

        attr.AttrOneManByte("Ns", "byte", V, Seq(), None, None, None).toString ==>
          """AttrOneManByte("Ns", "byte", V, Seq(), None, None, None)"""

        attr.AttrOneManShort("Ns", "short", V, Seq(), None, None, None).toString ==>
          """AttrOneManShort("Ns", "short", V, Seq(), None, None, None)"""

        attr.AttrOneManChar("Ns", "char", V, Seq(), None, None, None).toString ==>
          """AttrOneManChar("Ns", "char", V, Seq(), None, None, None)"""
      }

      "One tacit empty" - {
        attr.AttrOneTacString("Ns", "string", V, Seq(), None, None, None).toString ==>
          """AttrOneTacString("Ns", "string", V, Seq(), None, None, None)"""

        attr.AttrOneTacInt("Ns", "int", V, Seq(), None, None, None).toString ==>
          """AttrOneTacInt("Ns", "int", V, Seq(), None, None, None)"""

        attr.AttrOneTacLong("Ns", "long", V, Seq(), None, None, None).toString ==>
          """AttrOneTacLong("Ns", "long", V, Seq(), None, None, None, false)""" // default boolean value shown

        attr.AttrOneTacFloat("Ns", "float", V, Seq(), None, None, None).toString ==>
          """AttrOneTacFloat("Ns", "float", V, Seq(), None, None, None)"""

        attr.AttrOneTacDouble("Ns", "double", V, Seq(), None, None, None).toString ==>
          """AttrOneTacDouble("Ns", "double", V, Seq(), None, None, None)"""

        attr.AttrOneTacBoolean("Ns", "boolean", V, Seq(), None, None, None).toString ==>
          """AttrOneTacBoolean("Ns", "boolean", V, Seq(), None, None, None)"""

        attr.AttrOneTacBigInt("Ns", "bigInt", V, Seq(), None, None, None).toString ==>
          """AttrOneTacBigInt("Ns", "bigInt", V, Seq(), None, None, None)"""

        attr.AttrOneTacBigDecimal("Ns", "bigDecimal", V, Seq(), None, None, None).toString ==>
          """AttrOneTacBigDecimal("Ns", "bigDecimal", V, Seq(), None, None, None)"""

        attr.AttrOneTacDate("Ns", "date", V, Seq(), None, None, None).toString ==>
          """AttrOneTacDate("Ns", "date", V, Seq(), None, None, None)"""

        attr.AttrOneTacUUID("Ns", "uuid", V, Seq(), None, None, None).toString ==>
          """AttrOneTacUUID("Ns", "uuid", V, Seq(), None, None, None)"""

        attr.AttrOneTacURI("Ns", "uri", V, Seq(), None, None, None).toString ==>
          """AttrOneTacURI("Ns", "uri", V, Seq(), None, None, None)"""

        attr.AttrOneTacByte("Ns", "byte", V, Seq(), None, None, None).toString ==>
          """AttrOneTacByte("Ns", "byte", V, Seq(), None, None, None)"""

        attr.AttrOneTacShort("Ns", "short", V, Seq(), None, None, None).toString ==>
          """AttrOneTacShort("Ns", "short", V, Seq(), None, None, None)"""

        attr.AttrOneTacChar("Ns", "char", V, Seq(), None, None, None).toString ==>
          """AttrOneTacChar("Ns", "char", V, Seq(), None, None, None)"""
      }

      "One optional empty" - {
        attr.AttrOneOptString("Ns", "string", V, None, None, None, None).toString ==>
          """AttrOneOptString("Ns", "string", V, None, None, None, None)"""

        attr.AttrOneOptInt("Ns", "int", V, None, None, None, None).toString ==>
          """AttrOneOptInt("Ns", "int", V, None, None, None, None)"""

        attr.AttrOneOptLong("Ns", "long", V, None, None, None, None).toString ==>
          """AttrOneOptLong("Ns", "long", V, None, None, None, None, false)""" // default boolean value shown

        attr.AttrOneOptFloat("Ns", "float", V, None, None, None, None).toString ==>
          """AttrOneOptFloat("Ns", "float", V, None, None, None, None)"""

        attr.AttrOneOptDouble("Ns", "double", V, None, None, None, None).toString ==>
          """AttrOneOptDouble("Ns", "double", V, None, None, None, None)"""

        attr.AttrOneOptBoolean("Ns", "boolean", V, None, None, None, None).toString ==>
          """AttrOneOptBoolean("Ns", "boolean", V, None, None, None, None)"""

        attr.AttrOneOptBigInt("Ns", "bigInt", V, None, None, None, None).toString ==>
          """AttrOneOptBigInt("Ns", "bigInt", V, None, None, None, None)"""

        attr.AttrOneOptBigDecimal("Ns", "bigDecimal", V, None, None, None, None).toString ==>
          """AttrOneOptBigDecimal("Ns", "bigDecimal", V, None, None, None, None)"""

        attr.AttrOneOptDate("Ns", "date", V, None, None, None, None).toString ==>
          """AttrOneOptDate("Ns", "date", V, None, None, None, None)"""

        attr.AttrOneOptUUID("Ns", "uuid", V, None, None, None, None).toString ==>
          """AttrOneOptUUID("Ns", "uuid", V, None, None, None, None)"""

        attr.AttrOneOptURI("Ns", "uri", V, None, None, None, None).toString ==>
          """AttrOneOptURI("Ns", "uri", V, None, None, None, None)"""

        attr.AttrOneOptByte("Ns", "byte", V, None, None, None, None).toString ==>
          """AttrOneOptByte("Ns", "byte", V, None, None, None, None)"""

        attr.AttrOneOptShort("Ns", "short", V, None, None, None, None).toString ==>
          """AttrOneOptShort("Ns", "short", V, None, None, None, None)"""

        attr.AttrOneOptChar("Ns", "char", V, None, None, None, None).toString ==>
          """AttrOneOptChar("Ns", "char", V, None, None, None, None)"""
      }
    }



    "Set" - {
      "Set mandatory" - {
        attr.AttrSetManString("Ns", "string", V, Seq(Set("a", "b"), Set("c")), Some(Set("a", "b")), None, Some("a1")).toString ==>
          """AttrSetManString("Ns", "string", V, Seq(Set("a", "b"), Set("c")), Some(Set("a", "b")), None, Some("a1"))"""

        attr.AttrSetManInt("Ns", "int", V, Seq(Set(1, 2), Set(3)), Some(Set(1, 2)), None, Some("a1")).toString ==>
          """AttrSetManInt("Ns", "int", V, Seq(Set(1, 2), Set(3)), Some(Set(1, 2)), None, Some("a1"))"""

        attr.AttrSetManLong("Ns", "long", V, Seq(Set(1L, 2L), Set(3L)), Some(Set(1L, 2L)), None, Some("a1"), true).toString ==>
          """AttrSetManLong("Ns", "long", V, Seq(Set(1L, 2L), Set(3L)), Some(Set(1L, 2L)), None, Some("a1"), true)"""

        attr.AttrSetManFloat("Ns", "float", V, Seq(Set(1.1f, 2.2f), Set(3.3f)), Some(Set(1.1f, 2.2f)), None, Some("a1")).toString ==>
          """AttrSetManFloat("Ns", "float", V, Seq(Set(1.1f, 2.2f), Set(3.3f)), Some(Set(1.1f, 2.2f)), None, Some("a1"))"""

        attr.AttrSetManDouble("Ns", "double", V, Seq(Set(1.1, 2.2), Set(3.3)), Some(Set(1.1, 2.2)), None, Some("a1")).toString ==>
          """AttrSetManDouble("Ns", "double", V, Seq(Set(1.1, 2.2), Set(3.3)), Some(Set(1.1, 2.2)), None, Some("a1"))"""

        attr.AttrSetManBoolean("Ns", "boolean", V, Seq(Set(true, false), Set(false)), Some(Set(true, false)), None, Some("a1")).toString ==>
          """AttrSetManBoolean("Ns", "boolean", V, Seq(Set(true, false), Set(false)), Some(Set(true, false)), None, Some("a1"))"""

        attr.AttrSetManBigInt("Ns", "bigInt", V, Seq(Set(BigInt(1), BigInt(2)), Set(BigInt(3))), Some(Set(BigInt(1), BigInt(2))), None, Some("a1")).toString ==>
          """AttrSetManBigInt("Ns", "bigInt", V, Seq(Set(BigInt(1), BigInt(2)), Set(BigInt(3))), Some(Set(BigInt(1), BigInt(2))), None, Some("a1"))"""

        attr.AttrSetManBigDecimal("Ns", "bigDecimal", V, Seq(Set(BigDecimal(1.1), BigDecimal(2.2)), Set(BigDecimal(3.3))), Some(Set(BigDecimal(1.1), BigDecimal(2.2))), None, Some("a1")).toString ==>
          """AttrSetManBigDecimal("Ns", "bigDecimal", V, Seq(Set(BigDecimal(1.1), BigDecimal(2.2)), Set(BigDecimal(3.3))), Some(Set(BigDecimal(1.1), BigDecimal(2.2))), None, Some("a1"))"""

        attr.AttrSetManDate("Ns", "date", V, Seq(Set(new Date(1), new Date(2)), Set(new Date(3))), Some(Set(new Date(1), new Date(2))), None, Some("a1")).toString ==>
          """AttrSetManDate("Ns", "date", V, Seq(Set(new Date(1), new Date(2)), Set(new Date(3))), Some(Set(new Date(1), new Date(2))), None, Some("a1"))"""

        attr.AttrSetManUUID("Ns", "uuid", V, Seq(Set(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), Set(UUID.fromString("2ecfa663-8628-4d9e-9f7c-d5c16d7b48fa"))), Some(Set(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457"))), None, Some("a1")).toString ==>
          """AttrSetManUUID("Ns", "uuid", V, Seq(Set(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), Set(UUID.fromString("2ecfa663-8628-4d9e-9f7c-d5c16d7b48fa"))), Some(Set(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457"))), None, Some("a1"))"""

        attr.AttrSetManURI("Ns", "uri", V, Seq(Set(new URI("a"), new URI("b")), Set(new URI("c"))), Some(Set(new URI("a"), new URI("b"))), None, Some("a1")).toString ==>
          """AttrSetManURI("Ns", "uri", V, Seq(Set(new URI("a"), new URI("b")), Set(new URI("c"))), Some(Set(new URI("a"), new URI("b"))), None, Some("a1"))"""

        attr.AttrSetManByte("Ns", "byte", V, Seq(Set(1.toByte, 2.toByte), Set(3.toByte)), Some(Set(1.toByte, 2.toByte)), None, Some("a1")).toString ==>
          """AttrSetManByte("Ns", "byte", V, Seq(Set(1.toByte, 2.toByte), Set(3.toByte)), Some(Set(1.toByte, 2.toByte)), None, Some("a1"))"""

        attr.AttrSetManShort("Ns", "short", V, Seq(Set(1.toShort, 2.toShort), Set(3.toShort)), Some(Set(1.toShort, 2.toShort)), None, Some("a1")).toString ==>
          """AttrSetManShort("Ns", "short", V, Seq(Set(1.toShort, 2.toShort), Set(3.toShort)), Some(Set(1.toShort, 2.toShort)), None, Some("a1"))"""

        attr.AttrSetManChar("Ns", "char", V, Seq(Set('a', 'b'), Set('c')), Some(Set('a')), None, Some("a1")).toString ==>
          """AttrSetManChar("Ns", "char", V, Seq(Set('a', 'b'), Set('c')), Some(Set('a')), None, Some("a1"))"""
      }

      "Set tacit" - {
        attr.AttrSetTacString("Ns", "string", V, Seq(Set("a", "b"), Set("c")), Some(Set("a", "b")), None, Some("a1")).toString ==>
          """AttrSetTacString("Ns", "string", V, Seq(Set("a", "b"), Set("c")), Some(Set("a", "b")), None, Some("a1"))"""

        attr.AttrSetTacInt("Ns", "int", V, Seq(Set(1, 2), Set(3)), Some(Set(1, 2)), None, Some("a1")).toString ==>
          """AttrSetTacInt("Ns", "int", V, Seq(Set(1, 2), Set(3)), Some(Set(1, 2)), None, Some("a1"))"""

        attr.AttrSetTacLong("Ns", "long", V, Seq(Set(1L, 2L), Set(3L)), Some(Set(1L, 2L)), None, Some("a1"), true).toString ==>
          """AttrSetTacLong("Ns", "long", V, Seq(Set(1L, 2L), Set(3L)), Some(Set(1L, 2L)), None, Some("a1"), true)"""

        attr.AttrSetTacFloat("Ns", "float", V, Seq(Set(1.1f, 2.2f), Set(3.3f)), Some(Set(1.1f, 2.2f)), None, Some("a1")).toString ==>
          """AttrSetTacFloat("Ns", "float", V, Seq(Set(1.1f, 2.2f), Set(3.3f)), Some(Set(1.1f, 2.2f)), None, Some("a1"))"""

        attr.AttrSetTacDouble("Ns", "double", V, Seq(Set(1.1, 2.2), Set(3.3)), Some(Set(1.1, 2.2)), None, Some("a1")).toString ==>
          """AttrSetTacDouble("Ns", "double", V, Seq(Set(1.1, 2.2), Set(3.3)), Some(Set(1.1, 2.2)), None, Some("a1"))"""

        attr.AttrSetTacBoolean("Ns", "boolean", V, Seq(Set(true, false), Set(false)), Some(Set(true, false)), None, Some("a1")).toString ==>
          """AttrSetTacBoolean("Ns", "boolean", V, Seq(Set(true, false), Set(false)), Some(Set(true, false)), None, Some("a1"))"""

        attr.AttrSetTacBigInt("Ns", "bigInt", V, Seq(Set(BigInt(1), BigInt(2)), Set(BigInt(3))), Some(Set(BigInt(1), BigInt(2))), None, Some("a1")).toString ==>
          """AttrSetTacBigInt("Ns", "bigInt", V, Seq(Set(BigInt(1), BigInt(2)), Set(BigInt(3))), Some(Set(BigInt(1), BigInt(2))), None, Some("a1"))"""

        attr.AttrSetTacBigDecimal("Ns", "bigDecimal", V, Seq(Set(BigDecimal(1.1), BigDecimal(2.2)), Set(BigDecimal(3.3))), Some(Set(BigDecimal(1.1), BigDecimal(2.2))), None, Some("a1")).toString ==>
          """AttrSetTacBigDecimal("Ns", "bigDecimal", V, Seq(Set(BigDecimal(1.1), BigDecimal(2.2)), Set(BigDecimal(3.3))), Some(Set(BigDecimal(1.1), BigDecimal(2.2))), None, Some("a1"))"""

        attr.AttrSetTacDate("Ns", "date", V, Seq(Set(new Date(1), new Date(2)), Set(new Date(3))), Some(Set(new Date(1), new Date(2))), None, Some("a1")).toString ==>
          """AttrSetTacDate("Ns", "date", V, Seq(Set(new Date(1), new Date(2)), Set(new Date(3))), Some(Set(new Date(1), new Date(2))), None, Some("a1"))"""

        attr.AttrSetTacUUID("Ns", "uuid", V, Seq(Set(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), Set(UUID.fromString("2ecfa663-8628-4d9e-9f7c-d5c16d7b48fa"))), Some(Set(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457"))), None, Some("a1")).toString ==>
          """AttrSetTacUUID("Ns", "uuid", V, Seq(Set(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), Set(UUID.fromString("2ecfa663-8628-4d9e-9f7c-d5c16d7b48fa"))), Some(Set(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457"))), None, Some("a1"))"""

        attr.AttrSetTacURI("Ns", "uri", V, Seq(Set(new URI("a"), new URI("b")), Set(new URI("c"))), Some(Set(new URI("a"), new URI("b"))), None, Some("a1")).toString ==>
          """AttrSetTacURI("Ns", "uri", V, Seq(Set(new URI("a"), new URI("b")), Set(new URI("c"))), Some(Set(new URI("a"), new URI("b"))), None, Some("a1"))"""

        attr.AttrSetTacByte("Ns", "byte", V, Seq(Set(1.toByte, 2.toByte), Set(3.toByte)), Some(Set(1.toByte, 2.toByte)), None, Some("a1")).toString ==>
          """AttrSetTacByte("Ns", "byte", V, Seq(Set(1.toByte, 2.toByte), Set(3.toByte)), Some(Set(1.toByte, 2.toByte)), None, Some("a1"))"""

        attr.AttrSetTacShort("Ns", "short", V, Seq(Set(1.toShort, 2.toShort), Set(3.toShort)), Some(Set(1.toShort, 2.toShort)), None, Some("a1")).toString ==>
          """AttrSetTacShort("Ns", "short", V, Seq(Set(1.toShort, 2.toShort), Set(3.toShort)), Some(Set(1.toShort, 2.toShort)), None, Some("a1"))"""

        attr.AttrSetTacChar("Ns", "char", V, Seq(Set('a', 'b'), Set('c')), Some(Set('a')), None, Some("a1")).toString ==>
          """AttrSetTacChar("Ns", "char", V, Seq(Set('a', 'b'), Set('c')), Some(Set('a')), None, Some("a1"))"""
      }

      "Set optional" - {
        attr.AttrSetOptString("Ns", "string", V, Some(Seq(Set("a", "b"), Set("c"))), Some(Set("a", "b")), None, Some("a1")).toString ==>
          """AttrSetOptString("Ns", "string", V, Some(Seq(Set("a", "b"), Set("c"))), Some(Set("a", "b")), None, Some("a1"))"""

        attr.AttrSetOptInt("Ns", "int", V, Some(Seq(Set(1, 2), Set(3))), Some(Set(1, 2)), None, Some("a1")).toString ==>
          """AttrSetOptInt("Ns", "int", V, Some(Seq(Set(1, 2), Set(3))), Some(Set(1, 2)), None, Some("a1"))"""

        attr.AttrSetOptLong("Ns", "long", V, Some(Seq(Set(1L, 2L), Set(3L))), Some(Set(1L, 2L)), None, Some("a1"), true).toString ==>
          """AttrSetOptLong("Ns", "long", V, Some(Seq(Set(1L, 2L), Set(3L))), Some(Set(1L, 2L)), None, Some("a1"), true)"""

        attr.AttrSetOptFloat("Ns", "float", V, Some(Seq(Set(1.1f, 2.2f), Set(3.3f))), Some(Set(1.1f, 2.2f)), None, Some("a1")).toString ==>
          """AttrSetOptFloat("Ns", "float", V, Some(Seq(Set(1.1f, 2.2f), Set(3.3f))), Some(Set(1.1f, 2.2f)), None, Some("a1"))"""

        attr.AttrSetOptDouble("Ns", "double", V, Some(Seq(Set(1.1, 2.2), Set(3.3))), Some(Set(1.1, 2.2)), None, Some("a1")).toString ==>
          """AttrSetOptDouble("Ns", "double", V, Some(Seq(Set(1.1, 2.2), Set(3.3))), Some(Set(1.1, 2.2)), None, Some("a1"))"""

        attr.AttrSetOptBoolean("Ns", "boolean", V, Some(Seq(Set(true, false), Set(false))), Some(Set(true, false)), None, Some("a1")).toString ==>
          """AttrSetOptBoolean("Ns", "boolean", V, Some(Seq(Set(true, false), Set(false))), Some(Set(true, false)), None, Some("a1"))"""

        attr.AttrSetOptBigInt("Ns", "bigInt", V, Some(Seq(Set(BigInt(1), BigInt(2)), Set(BigInt(3)))), Some(Set(BigInt(1), BigInt(2))), None, Some("a1")).toString ==>
          """AttrSetOptBigInt("Ns", "bigInt", V, Some(Seq(Set(BigInt(1), BigInt(2)), Set(BigInt(3)))), Some(Set(BigInt(1), BigInt(2))), None, Some("a1"))"""

        attr.AttrSetOptBigDecimal("Ns", "bigDecimal", V, Some(Seq(Set(BigDecimal(1.1), BigDecimal(2.2)), Set(BigDecimal(3.3)))), Some(Set(BigDecimal(1.1), BigDecimal(2.2))), None, Some("a1")).toString ==>
          """AttrSetOptBigDecimal("Ns", "bigDecimal", V, Some(Seq(Set(BigDecimal(1.1), BigDecimal(2.2)), Set(BigDecimal(3.3)))), Some(Set(BigDecimal(1.1), BigDecimal(2.2))), None, Some("a1"))"""

        attr.AttrSetOptDate("Ns", "date", V, Some(Seq(Set(new Date(1), new Date(2)), Set(new Date(3)))), Some(Set(new Date(1), new Date(2))), None, Some("a1")).toString ==>
          """AttrSetOptDate("Ns", "date", V, Some(Seq(Set(new Date(1), new Date(2)), Set(new Date(3)))), Some(Set(new Date(1), new Date(2))), None, Some("a1"))"""

        attr.AttrSetOptUUID("Ns", "uuid", V, Some(Seq(Set(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), Set(UUID.fromString("2ecfa663-8628-4d9e-9f7c-d5c16d7b48fa")))), Some(Set(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457"))), None, Some("a1")).toString ==>
          """AttrSetOptUUID("Ns", "uuid", V, Some(Seq(Set(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), Set(UUID.fromString("2ecfa663-8628-4d9e-9f7c-d5c16d7b48fa")))), Some(Set(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457"))), None, Some("a1"))"""

        attr.AttrSetOptURI("Ns", "uri", V, Some(Seq(Set(new URI("a"), new URI("b")), Set(new URI("c")))), Some(Set(new URI("a"), new URI("b"))), None, Some("a1")).toString ==>
          """AttrSetOptURI("Ns", "uri", V, Some(Seq(Set(new URI("a"), new URI("b")), Set(new URI("c")))), Some(Set(new URI("a"), new URI("b"))), None, Some("a1"))"""

        attr.AttrSetOptByte("Ns", "byte", V, Some(Seq(Set(1.toByte, 2.toByte), Set(3.toByte))), Some(Set(1.toByte, 2.toByte)), None, Some("a1")).toString ==>
          """AttrSetOptByte("Ns", "byte", V, Some(Seq(Set(1.toByte, 2.toByte), Set(3.toByte))), Some(Set(1.toByte, 2.toByte)), None, Some("a1"))"""

        attr.AttrSetOptShort("Ns", "short", V, Some(Seq(Set(1.toShort, 2.toShort), Set(3.toShort))), Some(Set(1.toShort, 2.toShort)), None, Some("a1")).toString ==>
          """AttrSetOptShort("Ns", "short", V, Some(Seq(Set(1.toShort, 2.toShort), Set(3.toShort))), Some(Set(1.toShort, 2.toShort)), None, Some("a1"))"""

        attr.AttrSetOptChar("Ns", "char", V, Some(Seq(Set('a', 'b'), Set('c'))), Some(Set('a')), None, Some("a1")).toString ==>
          """AttrSetOptChar("Ns", "char", V, Some(Seq(Set('a', 'b'), Set('c'))), Some(Set('a')), None, Some("a1"))"""
      }
    }


    "Set empty" - {
      "Set mandatory empty" - {
        attr.AttrSetManString("Ns", "string", V, Seq(), None, None, None).toString ==>
          """AttrSetManString("Ns", "string", V, Seq(), None, None, None)"""

        attr.AttrSetManInt("Ns", "int", V, Seq(), None, None, None).toString ==>
          """AttrSetManInt("Ns", "int", V, Seq(), None, None, None)"""

        attr.AttrSetManLong("Ns", "long", V, Seq(), None, None, None).toString ==>
          """AttrSetManLong("Ns", "long", V, Seq(), None, None, None, false)""" // default boolean value shown

        attr.AttrSetManFloat("Ns", "float", V, Seq(), None, None, None).toString ==>
          """AttrSetManFloat("Ns", "float", V, Seq(), None, None, None)"""

        attr.AttrSetManDouble("Ns", "double", V, Seq(), None, None, None).toString ==>
          """AttrSetManDouble("Ns", "double", V, Seq(), None, None, None)"""

        attr.AttrSetManBoolean("Ns", "boolean", V, Seq(), None, None, None).toString ==>
          """AttrSetManBoolean("Ns", "boolean", V, Seq(), None, None, None)"""

        attr.AttrSetManBigInt("Ns", "bigInt", V, Seq(), None, None, None).toString ==>
          """AttrSetManBigInt("Ns", "bigInt", V, Seq(), None, None, None)"""

        attr.AttrSetManBigDecimal("Ns", "bigDecimal", V, Seq(), None, None, None).toString ==>
          """AttrSetManBigDecimal("Ns", "bigDecimal", V, Seq(), None, None, None)"""

        attr.AttrSetManDate("Ns", "date", V, Seq(), None, None, None).toString ==>
          """AttrSetManDate("Ns", "date", V, Seq(), None, None, None)"""

        attr.AttrSetManUUID("Ns", "uuid", V, Seq(), None, None, None).toString ==>
          """AttrSetManUUID("Ns", "uuid", V, Seq(), None, None, None)"""

        attr.AttrSetManURI("Ns", "uri", V, Seq(), None, None, None).toString ==>
          """AttrSetManURI("Ns", "uri", V, Seq(), None, None, None)"""

        attr.AttrSetManByte("Ns", "byte", V, Seq(), None, None, None).toString ==>
          """AttrSetManByte("Ns", "byte", V, Seq(), None, None, None)"""

        attr.AttrSetManShort("Ns", "short", V, Seq(), None, None, None).toString ==>
          """AttrSetManShort("Ns", "short", V, Seq(), None, None, None)"""

        attr.AttrSetManChar("Ns", "char", V, Seq(), None, None, None).toString ==>
          """AttrSetManChar("Ns", "char", V, Seq(), None, None, None)"""
      }

      "Set tacit empty" - {
        attr.AttrSetTacString("Ns", "string", V, Seq(), None, None, None).toString ==>
          """AttrSetTacString("Ns", "string", V, Seq(), None, None, None)"""

        attr.AttrSetTacInt("Ns", "int", V, Seq(), None, None, None).toString ==>
          """AttrSetTacInt("Ns", "int", V, Seq(), None, None, None)"""

        attr.AttrSetTacLong("Ns", "long", V, Seq(), None, None, None).toString ==>
          """AttrSetTacLong("Ns", "long", V, Seq(), None, None, None, false)""" // default boolean value shown

        attr.AttrSetTacFloat("Ns", "float", V, Seq(), None, None, None).toString ==>
          """AttrSetTacFloat("Ns", "float", V, Seq(), None, None, None)"""

        attr.AttrSetTacDouble("Ns", "double", V, Seq(), None, None, None).toString ==>
          """AttrSetTacDouble("Ns", "double", V, Seq(), None, None, None)"""

        attr.AttrSetTacBoolean("Ns", "boolean", V, Seq(), None, None, None).toString ==>
          """AttrSetTacBoolean("Ns", "boolean", V, Seq(), None, None, None)"""

        attr.AttrSetTacBigInt("Ns", "bigInt", V, Seq(), None, None, None).toString ==>
          """AttrSetTacBigInt("Ns", "bigInt", V, Seq(), None, None, None)"""

        attr.AttrSetTacBigDecimal("Ns", "bigDecimal", V, Seq(), None, None, None).toString ==>
          """AttrSetTacBigDecimal("Ns", "bigDecimal", V, Seq(), None, None, None)"""

        attr.AttrSetTacDate("Ns", "date", V, Seq(), None, None, None).toString ==>
          """AttrSetTacDate("Ns", "date", V, Seq(), None, None, None)"""

        attr.AttrSetTacUUID("Ns", "uuid", V, Seq(), None, None, None).toString ==>
          """AttrSetTacUUID("Ns", "uuid", V, Seq(), None, None, None)"""

        attr.AttrSetTacURI("Ns", "uri", V, Seq(), None, None, None).toString ==>
          """AttrSetTacURI("Ns", "uri", V, Seq(), None, None, None)"""

        attr.AttrSetTacByte("Ns", "byte", V, Seq(), None, None, None).toString ==>
          """AttrSetTacByte("Ns", "byte", V, Seq(), None, None, None)"""

        attr.AttrSetTacShort("Ns", "short", V, Seq(), None, None, None).toString ==>
          """AttrSetTacShort("Ns", "short", V, Seq(), None, None, None)"""

        attr.AttrSetTacChar("Ns", "char", V, Seq(), None, None, None).toString ==>
          """AttrSetTacChar("Ns", "char", V, Seq(), None, None, None)"""
      }

      "Set optional empty" - {
        attr.AttrSetOptString("Ns", "string", V, None, None, None, None).toString ==>
          """AttrSetOptString("Ns", "string", V, None, None, None, None)"""

        attr.AttrSetOptInt("Ns", "int", V, None, None, None, None).toString ==>
          """AttrSetOptInt("Ns", "int", V, None, None, None, None)"""

        attr.AttrSetOptLong("Ns", "long", V, None, None, None, None).toString ==>
          """AttrSetOptLong("Ns", "long", V, None, None, None, None, false)""" // default boolean value shown

        attr.AttrSetOptFloat("Ns", "float", V, None, None, None, None).toString ==>
          """AttrSetOptFloat("Ns", "float", V, None, None, None, None)"""

        attr.AttrSetOptDouble("Ns", "double", V, None, None, None, None).toString ==>
          """AttrSetOptDouble("Ns", "double", V, None, None, None, None)"""

        attr.AttrSetOptBoolean("Ns", "boolean", V, None, None, None, None).toString ==>
          """AttrSetOptBoolean("Ns", "boolean", V, None, None, None, None)"""

        attr.AttrSetOptBigInt("Ns", "bigInt", V, None, None, None, None).toString ==>
          """AttrSetOptBigInt("Ns", "bigInt", V, None, None, None, None)"""

        attr.AttrSetOptBigDecimal("Ns", "bigDecimal", V, None, None, None, None).toString ==>
          """AttrSetOptBigDecimal("Ns", "bigDecimal", V, None, None, None, None)"""

        attr.AttrSetOptDate("Ns", "date", V, None, None, None, None).toString ==>
          """AttrSetOptDate("Ns", "date", V, None, None, None, None)"""

        attr.AttrSetOptUUID("Ns", "uuid", V, None, None, None, None).toString ==>
          """AttrSetOptUUID("Ns", "uuid", V, None, None, None, None)"""

        attr.AttrSetOptURI("Ns", "uri", V, None, None, None, None).toString ==>
          """AttrSetOptURI("Ns", "uri", V, None, None, None, None)"""

        attr.AttrSetOptByte("Ns", "byte", V, None, None, None, None).toString ==>
          """AttrSetOptByte("Ns", "byte", V, None, None, None, None)"""

        attr.AttrSetOptShort("Ns", "short", V, None, None, None, None).toString ==>
          """AttrSetOptShort("Ns", "short", V, None, None, None, None)"""

        attr.AttrSetOptChar("Ns", "char", V, None, None, None, None).toString ==>
          """AttrSetOptChar("Ns", "char", V, None, None, None, None)"""
      }
    }
  }
}

