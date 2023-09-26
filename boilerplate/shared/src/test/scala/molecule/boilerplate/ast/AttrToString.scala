package molecule.boilerplate.ast


import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._
import org.scalactic.TripleEquals._
import utest._


object AttrToString extends TestSuite with Validations {

  // Indent dummy to align
  val attr = Model


  override lazy val tests = Tests {

    "One" - {
      "One mandatory" - {
        attr.AttrOneManString("Ns", "string", V, Seq("a", "b"), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneManString("Ns", "string", V, Seq("a", "b"), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneManInt("Ns", "int", V, Seq(1, 2), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneManInt("Ns", "int", V, Seq(1, 2), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneManLong("Ns", "long", V, Seq(1L, 2L), None, None, Nil, Nil, Some("ref"), Some("a1"), Nil).toString ==>
          """AttrOneManLong("Ns", "long", V, Seq(1L, 2L), None, Nil, Nil , Some("ref"), Some("a1"), Nil)"""

        attr.AttrOneManFloat("Ns", "float", V, Seq(1.1f, 2.2f), None, None, Nil, Nil, None, Some("a1")).toString === // tolerate js precision
          """AttrOneManFloat("Ns", "float", V, Seq(1.1f, 2.2f), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneManDouble("Ns", "double", V, Seq(1.1, 2.2), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneManDouble("Ns", "double", V, Seq(1.1, 2.2), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneManBoolean("Ns", "boolean", V, Seq(true, false), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneManBoolean("Ns", "boolean", V, Seq(true, false), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneManBigInt("Ns", "bigInt", V, Seq(BigInt(1), BigInt(2)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneManBigInt("Ns", "bigInt", V, Seq(BigInt(1), BigInt(2)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneManBigDecimal("Ns", "bigDecimal", V, Seq(BigDecimal(1.1), BigDecimal(2.2)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneManBigDecimal("Ns", "bigDecimal", V, Seq(BigDecimal(1.1), BigDecimal(2.2)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneManDate("Ns", "date", V, Seq(new Date(1), new Date(2)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneManDate("Ns", "date", V, Seq(new Date(1), new Date(2)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneManUUID("Ns", "uuid", V, Seq(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneManUUID("Ns", "uuid", V, Seq(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneManURI("Ns", "uri", V, Seq(new URI("a"), new URI("b")), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneManURI("Ns", "uri", V, Seq(new URI("a"), new URI("b")), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneManByte("Ns", "byte", V, Seq(1.toByte, 2.toByte), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneManByte("Ns", "byte", V, Seq(1.toByte, 2.toByte), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneManShort("Ns", "short", V, Seq(1.toShort, 2.toShort), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneManShort("Ns", "short", V, Seq(1.toShort, 2.toShort), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneManChar("Ns", "char", V, Seq('a', 'b'), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneManChar("Ns", "char", V, Seq('a', 'b'), None, None, Nil, Nil, None, Some("a1"), Nil)"""
      }

      "One tacit" - {
        attr.AttrOneTacString("Ns", "string", V, Seq("a", "b"), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneTacString("Ns", "string", V, Seq("a", "b"), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneTacInt("Ns", "int", V, Seq(1, 2), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneTacInt("Ns", "int", V, Seq(1, 2), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneTacLong("Ns", "long", V, Seq(1L, 2L), None, None, Nil, Nil, Some("ref"), Some("a1"), Nil).toString ==>
          """AttrOneTacLong("Ns", "long", V, Seq(1L, 2L), None, Nil, Nil, Some("ref"), Some("a1"), Nil)"""

        attr.AttrOneTacFloat("Ns", "float", V, Seq(1.1f, 2.2f), None, None, Nil, Nil, None, Some("a1")).toString ===
          """AttrOneTacFloat("Ns", "float", V, Seq(1.1f, 2.2f), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneTacDouble("Ns", "double", V, Seq(1.1, 2.2), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneTacDouble("Ns", "double", V, Seq(1.1, 2.2), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneTacBoolean("Ns", "boolean", V, Seq(true, false), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneTacBoolean("Ns", "boolean", V, Seq(true, false), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneTacBigInt("Ns", "bigInt", V, Seq(BigInt(1), BigInt(2)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneTacBigInt("Ns", "bigInt", V, Seq(BigInt(1), BigInt(2)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneTacBigDecimal("Ns", "bigDecimal", V, Seq(BigDecimal(1.1), BigDecimal(2.2)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneTacBigDecimal("Ns", "bigDecimal", V, Seq(BigDecimal(1.1), BigDecimal(2.2)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneTacDate("Ns", "date", V, Seq(new Date(1), new Date(2)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneTacDate("Ns", "date", V, Seq(new Date(1), new Date(2)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneTacUUID("Ns", "uuid", V, Seq(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneTacUUID("Ns", "uuid", V, Seq(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneTacURI("Ns", "uri", V, Seq(new URI("a"), new URI("b")), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneTacURI("Ns", "uri", V, Seq(new URI("a"), new URI("b")), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneTacByte("Ns", "byte", V, Seq(1.toByte, 2.toByte), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneTacByte("Ns", "byte", V, Seq(1.toByte, 2.toByte), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneTacShort("Ns", "short", V, Seq(1.toShort, 2.toShort), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneTacShort("Ns", "short", V, Seq(1.toShort, 2.toShort), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneTacChar("Ns", "char", V, Seq('a', 'b'), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneTacChar("Ns", "char", V, Seq('a', 'b'), None, None, Nil, Nil, None, Some("a1"), Nil)"""
      }

      "One optional" - {
        attr.AttrOneOptString("Ns", "string", V, Some(Seq("a", "b")), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneOptString("Ns", "string", V, Some(Seq("a", "b")), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneOptInt("Ns", "int", V, Some(Seq(1, 2)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneOptInt("Ns", "int", V, Some(Seq(1, 2)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneOptLong("Ns", "long", V, Some(Seq(1L, 2L)), None, None, Nil, Nil, Some("ref"), Some("a1"), Nil).toString ==>
          """AttrOneOptLong("Ns", "long", V, Some(Seq(1L, 2L)), None, Nil, Nil, Some("ref"), Some("a1"), Nil)"""

        attr.AttrOneOptFloat("Ns", "float", V, Some(Seq(1.1f, 2.2f)), None, None, Nil, Nil, None, Some("a1")).toString ===
          """AttrOneOptFloat("Ns", "float", V, Some(Seq(1.1f, 2.2f)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneOptDouble("Ns", "double", V, Some(Seq(1.1, 2.2)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneOptDouble("Ns", "double", V, Some(Seq(1.1, 2.2)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneOptBoolean("Ns", "boolean", V, Some(Seq(true, false)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneOptBoolean("Ns", "boolean", V, Some(Seq(true, false)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneOptBigInt("Ns", "bigInt", V, Some(Seq(BigInt(1), BigInt(2))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneOptBigInt("Ns", "bigInt", V, Some(Seq(BigInt(1), BigInt(2))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneOptBigDecimal("Ns", "bigDecimal", V, Some(Seq(BigDecimal(1.1), BigDecimal(2.2))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneOptBigDecimal("Ns", "bigDecimal", V, Some(Seq(BigDecimal(1.1), BigDecimal(2.2))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneOptDate("Ns", "date", V, Some(Seq(new Date(1), new Date(2))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneOptDate("Ns", "date", V, Some(Seq(new Date(1), new Date(2))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneOptUUID("Ns", "uuid", V, Some(Seq(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457"))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneOptUUID("Ns", "uuid", V, Some(Seq(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457"))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneOptURI("Ns", "uri", V, Some(Seq(new URI("a"), new URI("b"))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneOptURI("Ns", "uri", V, Some(Seq(new URI("a"), new URI("b"))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneOptByte("Ns", "byte", V, Some(Seq(1.toByte, 2.toByte)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneOptByte("Ns", "byte", V, Some(Seq(1.toByte, 2.toByte)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneOptShort("Ns", "short", V, Some(Seq(1.toShort, 2.toShort)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneOptShort("Ns", "short", V, Some(Seq(1.toShort, 2.toShort)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrOneOptChar("Ns", "char", V, Some(Seq('a', 'b')), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrOneOptChar("Ns", "char", V, Some(Seq('a', 'b')), None, None, Nil, Nil, None, Some("a1"), Nil)"""
      }
    }


    "One empty" - {
      "One mandatory empty" - {
        attr.AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneManLong("Ns", "long", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneManLong("Ns", "long", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneManFloat("Ns", "float", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneManFloat("Ns", "float", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneManDouble("Ns", "double", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneManDouble("Ns", "double", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneManBoolean("Ns", "boolean", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneManBoolean("Ns", "boolean", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneManBigInt("Ns", "bigInt", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneManBigInt("Ns", "bigInt", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneManBigDecimal("Ns", "bigDecimal", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneManBigDecimal("Ns", "bigDecimal", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneManDate("Ns", "date", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneManDate("Ns", "date", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneManUUID("Ns", "uuid", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneManUUID("Ns", "uuid", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneManURI("Ns", "uri", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneManURI("Ns", "uri", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneManByte("Ns", "byte", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneManByte("Ns", "byte", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneManShort("Ns", "short", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneManShort("Ns", "short", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneManChar("Ns", "char", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneManChar("Ns", "char", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""
      }

      "One tacit empty" - {
        attr.AttrOneTacString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneTacString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneTacInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneTacInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneTacLong("Ns", "long", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneTacLong("Ns", "long", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneTacFloat("Ns", "float", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneTacFloat("Ns", "float", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneTacDouble("Ns", "double", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneTacDouble("Ns", "double", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneTacBoolean("Ns", "boolean", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneTacBoolean("Ns", "boolean", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneTacBigInt("Ns", "bigInt", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneTacBigInt("Ns", "bigInt", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneTacBigDecimal("Ns", "bigDecimal", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneTacBigDecimal("Ns", "bigDecimal", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneTacDate("Ns", "date", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneTacDate("Ns", "date", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneTacUUID("Ns", "uuid", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneTacUUID("Ns", "uuid", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneTacURI("Ns", "uri", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneTacURI("Ns", "uri", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneTacByte("Ns", "byte", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneTacByte("Ns", "byte", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneTacShort("Ns", "short", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneTacShort("Ns", "short", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneTacChar("Ns", "char", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneTacChar("Ns", "char", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""
      }

      "One optional empty" - {
        attr.AttrOneOptString("Ns", "string", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneOptString("Ns", "string", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneOptInt("Ns", "int", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneOptInt("Ns", "int", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneOptLong("Ns", "long", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneOptLong("Ns", "long", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneOptFloat("Ns", "float", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneOptFloat("Ns", "float", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneOptDouble("Ns", "double", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneOptDouble("Ns", "double", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneOptBoolean("Ns", "boolean", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneOptBoolean("Ns", "boolean", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneOptBigInt("Ns", "bigInt", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneOptBigInt("Ns", "bigInt", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneOptBigDecimal("Ns", "bigDecimal", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneOptBigDecimal("Ns", "bigDecimal", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneOptDate("Ns", "date", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneOptDate("Ns", "date", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneOptUUID("Ns", "uuid", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneOptUUID("Ns", "uuid", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneOptURI("Ns", "uri", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneOptURI("Ns", "uri", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneOptByte("Ns", "byte", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneOptByte("Ns", "byte", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneOptShort("Ns", "short", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneOptShort("Ns", "short", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrOneOptChar("Ns", "char", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrOneOptChar("Ns", "char", V, None, None, None, Nil, Nil, None, None, Nil)"""
      }
    }


    "Set" - {
      "Set mandatory" - {
        attr.AttrSetManString("Ns", "string", V, Seq(Set("a", "b"), Set("c")), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetManString("Ns", "string", V, Seq(Set("a", "b"), Set("c")), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetManInt("Ns", "int", V, Seq(Set(1, 2), Set(3)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetManInt("Ns", "int", V, Seq(Set(1, 2), Set(3)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetManLong("Ns", "long", V, Seq(Set(1L, 2L), Set(3L)), None, None, Nil, Nil, Some("ref"), Some("a1"), Nil).toString ==>
          """AttrSetManLong("Ns", "long", V, Seq(Set(1L, 2L), Set(3L)), None, Nil, Nil, Some("ref"), Some("a1"), Nil)"""

        attr.AttrSetManFloat("Ns", "float", V, Seq(Set(1.1f, 2.2f), Set(3.3f)), None, None, Nil, Nil, None, Some("a1")).toString ===
          """AttrSetManFloat("Ns", "float", V, Seq(Set(1.1f, 2.2f), Set(3.3f)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetManDouble("Ns", "double", V, Seq(Set(1.1, 2.2), Set(3.3)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetManDouble("Ns", "double", V, Seq(Set(1.1, 2.2), Set(3.3)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetManBoolean("Ns", "boolean", V, Seq(Set(true, false), Set(false)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetManBoolean("Ns", "boolean", V, Seq(Set(true, false), Set(false)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetManBigInt("Ns", "bigInt", V, Seq(Set(BigInt(1), BigInt(2)), Set(BigInt(3))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetManBigInt("Ns", "bigInt", V, Seq(Set(BigInt(1), BigInt(2)), Set(BigInt(3))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetManBigDecimal("Ns", "bigDecimal", V, Seq(Set(BigDecimal(1.1), BigDecimal(2.2)), Set(BigDecimal(3.3))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetManBigDecimal("Ns", "bigDecimal", V, Seq(Set(BigDecimal(1.1), BigDecimal(2.2)), Set(BigDecimal(3.3))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetManDate("Ns", "date", V, Seq(Set(new Date(1), new Date(2)), Set(new Date(3))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetManDate("Ns", "date", V, Seq(Set(new Date(1), new Date(2)), Set(new Date(3))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetManUUID("Ns", "uuid", V, Seq(Set(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), Set(UUID.fromString("2ecfa663-8628-4d9e-9f7c-d5c16d7b48fa"))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetManUUID("Ns", "uuid", V, Seq(Set(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), Set(UUID.fromString("2ecfa663-8628-4d9e-9f7c-d5c16d7b48fa"))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetManURI("Ns", "uri", V, Seq(Set(new URI("a"), new URI("b")), Set(new URI("c"))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetManURI("Ns", "uri", V, Seq(Set(new URI("a"), new URI("b")), Set(new URI("c"))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetManByte("Ns", "byte", V, Seq(Set(1.toByte, 2.toByte), Set(3.toByte)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetManByte("Ns", "byte", V, Seq(Set(1.toByte, 2.toByte), Set(3.toByte)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetManShort("Ns", "short", V, Seq(Set(1.toShort, 2.toShort), Set(3.toShort)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetManShort("Ns", "short", V, Seq(Set(1.toShort, 2.toShort), Set(3.toShort)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetManChar("Ns", "char", V, Seq(Set('a', 'b'), Set('c')), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetManChar("Ns", "char", V, Seq(Set('a', 'b'), Set('c')), None, None, Nil, Nil, None, Some("a1"), Nil)"""
      }

      "Set tacit" - {
        attr.AttrSetTacString("Ns", "string", V, Seq(Set("a", "b"), Set("c")), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetTacString("Ns", "string", V, Seq(Set("a", "b"), Set("c")), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetTacInt("Ns", "int", V, Seq(Set(1, 2), Set(3)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetTacInt("Ns", "int", V, Seq(Set(1, 2), Set(3)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetTacLong("Ns", "long", V, Seq(Set(1L, 2L), Set(3L)), None, None, Nil, Nil, Some("ref"), Some("a1"), Nil).toString ==>
          """AttrSetTacLong("Ns", "long", V, Seq(Set(1L, 2L), Set(3L)), None, Nil, Nil, Some("ref"), Some("a1"), Nil)"""

        attr.AttrSetTacFloat("Ns", "float", V, Seq(Set(1.1f, 2.2f), Set(3.3f)), None, None, Nil, Nil, None, Some("a1")).toString ===
          """AttrSetTacFloat("Ns", "float", V, Seq(Set(1.1f, 2.2f), Set(3.3f)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetTacDouble("Ns", "double", V, Seq(Set(1.1, 2.2), Set(3.3)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetTacDouble("Ns", "double", V, Seq(Set(1.1, 2.2), Set(3.3)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetTacBoolean("Ns", "boolean", V, Seq(Set(true, false), Set(false)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetTacBoolean("Ns", "boolean", V, Seq(Set(true, false), Set(false)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetTacBigInt("Ns", "bigInt", V, Seq(Set(BigInt(1), BigInt(2)), Set(BigInt(3))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetTacBigInt("Ns", "bigInt", V, Seq(Set(BigInt(1), BigInt(2)), Set(BigInt(3))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetTacBigDecimal("Ns", "bigDecimal", V, Seq(Set(BigDecimal(1.1), BigDecimal(2.2)), Set(BigDecimal(3.3))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetTacBigDecimal("Ns", "bigDecimal", V, Seq(Set(BigDecimal(1.1), BigDecimal(2.2)), Set(BigDecimal(3.3))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetTacDate("Ns", "date", V, Seq(Set(new Date(1), new Date(2)), Set(new Date(3))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetTacDate("Ns", "date", V, Seq(Set(new Date(1), new Date(2)), Set(new Date(3))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetTacUUID("Ns", "uuid", V, Seq(Set(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), Set(UUID.fromString("2ecfa663-8628-4d9e-9f7c-d5c16d7b48fa"))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetTacUUID("Ns", "uuid", V, Seq(Set(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), Set(UUID.fromString("2ecfa663-8628-4d9e-9f7c-d5c16d7b48fa"))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetTacURI("Ns", "uri", V, Seq(Set(new URI("a"), new URI("b")), Set(new URI("c"))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetTacURI("Ns", "uri", V, Seq(Set(new URI("a"), new URI("b")), Set(new URI("c"))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetTacByte("Ns", "byte", V, Seq(Set(1.toByte, 2.toByte), Set(3.toByte)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetTacByte("Ns", "byte", V, Seq(Set(1.toByte, 2.toByte), Set(3.toByte)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetTacShort("Ns", "short", V, Seq(Set(1.toShort, 2.toShort), Set(3.toShort)), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetTacShort("Ns", "short", V, Seq(Set(1.toShort, 2.toShort), Set(3.toShort)), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetTacChar("Ns", "char", V, Seq(Set('a', 'b'), Set('c')), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetTacChar("Ns", "char", V, Seq(Set('a', 'b'), Set('c')), None, None, Nil, Nil, None, Some("a1"), Nil)"""
      }

      "Set optional" - {
        attr.AttrSetOptString("Ns", "string", V, Some(Seq(Set("a", "b"), Set("c"))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetOptString("Ns", "string", V, Some(Seq(Set("a", "b"), Set("c"))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetOptInt("Ns", "int", V, Some(Seq(Set(1, 2), Set(3))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetOptInt("Ns", "int", V, Some(Seq(Set(1, 2), Set(3))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetOptLong("Ns", "long", V, Some(Seq(Set(1L, 2L), Set(3L))), None, None, Nil, Nil, Some("ref"), Some("a1"), Nil).toString ==>
          """AttrSetOptLong("Ns", "long", V, Some(Seq(Set(1L, 2L), Set(3L))), None, Nil, Nil, Some("ref"), Some("a1"), Nil)"""

        attr.AttrSetOptFloat("Ns", "float", V, Some(Seq(Set(1.1f, 2.2f), Set(3.3f))), None, None, Nil, Nil, None, Some("a1")).toString ===
          """AttrSetOptFloat("Ns", "float", V, Some(Seq(Set(1.1f, 2.2f), Set(3.3f))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetOptDouble("Ns", "double", V, Some(Seq(Set(1.1, 2.2), Set(3.3))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetOptDouble("Ns", "double", V, Some(Seq(Set(1.1, 2.2), Set(3.3))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetOptBoolean("Ns", "boolean", V, Some(Seq(Set(true, false), Set(false))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetOptBoolean("Ns", "boolean", V, Some(Seq(Set(true, false), Set(false))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetOptBigInt("Ns", "bigInt", V, Some(Seq(Set(BigInt(1), BigInt(2)), Set(BigInt(3)))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetOptBigInt("Ns", "bigInt", V, Some(Seq(Set(BigInt(1), BigInt(2)), Set(BigInt(3)))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetOptBigDecimal("Ns", "bigDecimal", V, Some(Seq(Set(BigDecimal(1.1), BigDecimal(2.2)), Set(BigDecimal(3.3)))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetOptBigDecimal("Ns", "bigDecimal", V, Some(Seq(Set(BigDecimal(1.1), BigDecimal(2.2)), Set(BigDecimal(3.3)))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetOptDate("Ns", "date", V, Some(Seq(Set(new Date(1), new Date(2)), Set(new Date(3)))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetOptDate("Ns", "date", V, Some(Seq(Set(new Date(1), new Date(2)), Set(new Date(3)))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetOptUUID("Ns", "uuid", V, Some(Seq(Set(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), Set(UUID.fromString("2ecfa663-8628-4d9e-9f7c-d5c16d7b48fa")))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetOptUUID("Ns", "uuid", V, Some(Seq(Set(UUID.fromString("e3520ab7-80f7-4b8a-ac73-63fe45aa162f"), UUID.fromString("be25241b-b52b-4e24-8b7f-6b3b5d365457")), Set(UUID.fromString("2ecfa663-8628-4d9e-9f7c-d5c16d7b48fa")))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetOptURI("Ns", "uri", V, Some(Seq(Set(new URI("a"), new URI("b")), Set(new URI("c")))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetOptURI("Ns", "uri", V, Some(Seq(Set(new URI("a"), new URI("b")), Set(new URI("c")))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetOptByte("Ns", "byte", V, Some(Seq(Set(1.toByte, 2.toByte), Set(3.toByte))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetOptByte("Ns", "byte", V, Some(Seq(Set(1.toByte, 2.toByte), Set(3.toByte))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetOptShort("Ns", "short", V, Some(Seq(Set(1.toShort, 2.toShort), Set(3.toShort))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetOptShort("Ns", "short", V, Some(Seq(Set(1.toShort, 2.toShort), Set(3.toShort))), None, None, Nil, Nil, None, Some("a1"), Nil)"""

        attr.AttrSetOptChar("Ns", "char", V, Some(Seq(Set('a', 'b'), Set('c'))), None, None, Nil, Nil, None, Some("a1"), Nil).toString ==>
          """AttrSetOptChar("Ns", "char", V, Some(Seq(Set('a', 'b'), Set('c'))), None, None, Nil, Nil, None, Some("a1"), Nil)"""
      }
    }


    "Set empty" - {
      "Set mandatory empty" - {
        attr.AttrSetManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetManLong("Ns", "long", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetManLong("Ns", "long", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetManFloat("Ns", "float", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetManFloat("Ns", "float", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetManDouble("Ns", "double", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetManDouble("Ns", "double", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetManBoolean("Ns", "boolean", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetManBoolean("Ns", "boolean", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetManBigInt("Ns", "bigInt", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetManBigInt("Ns", "bigInt", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetManBigDecimal("Ns", "bigDecimal", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetManBigDecimal("Ns", "bigDecimal", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetManDate("Ns", "date", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetManDate("Ns", "date", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetManUUID("Ns", "uuid", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetManUUID("Ns", "uuid", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetManURI("Ns", "uri", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetManURI("Ns", "uri", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetManByte("Ns", "byte", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetManByte("Ns", "byte", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetManShort("Ns", "short", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetManShort("Ns", "short", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetManChar("Ns", "char", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetManChar("Ns", "char", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""
      }

      "Set tacit empty" - {
        attr.AttrSetTacString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetTacString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetTacInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetTacInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetTacLong("Ns", "long", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetTacLong("Ns", "long", V, Seq(), None, None, Nil, Nil, None, None, false)"""

        attr.AttrSetTacFloat("Ns", "float", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetTacFloat("Ns", "float", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetTacDouble("Ns", "double", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetTacDouble("Ns", "double", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetTacBoolean("Ns", "boolean", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetTacBoolean("Ns", "boolean", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetTacBigInt("Ns", "bigInt", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetTacBigInt("Ns", "bigInt", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetTacBigDecimal("Ns", "bigDecimal", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetTacBigDecimal("Ns", "bigDecimal", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetTacDate("Ns", "date", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetTacDate("Ns", "date", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetTacUUID("Ns", "uuid", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetTacUUID("Ns", "uuid", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetTacURI("Ns", "uri", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetTacURI("Ns", "uri", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetTacByte("Ns", "byte", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetTacByte("Ns", "byte", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetTacShort("Ns", "short", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetTacShort("Ns", "short", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetTacChar("Ns", "char", V, Seq(), None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetTacChar("Ns", "char", V, Seq(), None, None, Nil, Nil, None, None, Nil)"""
      }

      "Set optional empty" - {
        attr.AttrSetOptString("Ns", "string", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetOptString("Ns", "string", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetOptInt("Ns", "int", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetOptInt("Ns", "int", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetOptLong("Ns", "long", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetOptLong("Ns", "long", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetOptFloat("Ns", "float", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetOptFloat("Ns", "float", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetOptDouble("Ns", "double", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetOptDouble("Ns", "double", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetOptBoolean("Ns", "boolean", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetOptBoolean("Ns", "boolean", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetOptBigInt("Ns", "bigInt", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetOptBigInt("Ns", "bigInt", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetOptBigDecimal("Ns", "bigDecimal", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetOptBigDecimal("Ns", "bigDecimal", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetOptDate("Ns", "date", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetOptDate("Ns", "date", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetOptUUID("Ns", "uuid", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetOptUUID("Ns", "uuid", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetOptURI("Ns", "uri", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetOptURI("Ns", "uri", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetOptByte("Ns", "byte", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetOptByte("Ns", "byte", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetOptShort("Ns", "short", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetOptShort("Ns", "short", V, None, None, None, Nil, Nil, None, None, Nil)"""

        attr.AttrSetOptChar("Ns", "char", V, None, None, None, Nil, Nil, None, None, Nil).toString ==>
          """AttrSetOptChar("Ns", "char", V, None, None, None, Nil, Nil, None, None, Nil)"""
      }
    }
  }
}

