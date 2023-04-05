// GENERATED CODE ********************************
package molecule.core.transaction

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.ast.SchemaAST.MetaNs
import molecule.base.error.{InsertError, ModelError}
import molecule.boilerplate.ast.Model._
import scala.annotation.tailrec


class InsertExtraction_ extends InsertResolvers_ { self: InsertOps =>

  @tailrec
  final override def resolve(
    nsMap: Map[String, MetaNs],
    elements: List[Element],
    resolvers: List[Product => Seq[InsertError]],
    outerTpl: Int,
    tplIndex: Int
  ): List[Product => Seq[InsertError]] = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          if (a.op != V) {
            throw ModelError("Can't insert attributes with an applied value. Found:\n" + a)
          }
          a match {
            case a: AttrOne =>
              a match {
                case a: AttrOneMan => resolve(nsMap, tail, resolvers :+
                  resolveAttrOneMan(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)

                case a: AttrOneOpt => resolve(nsMap, tail, resolvers :+
                  resolveAttrOneOpt(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)
              }
            case a: AttrSet =>
              a match {
                case a: AttrSetMan =>
                  val mandatory = nsMap(a.ns).mandatoryAttrs.contains(a.attr)
                  resolve(nsMap, tail, resolvers :+
                    resolveAttrSetMan(a, outerTpl, tplIndex, mandatory), outerTpl, tplIndex + 1)

                case a: AttrSetOpt => resolve(nsMap, tail, resolvers :+
                  resolveAttrSetOpt(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)
              }
            case a          => throw new Exception("Attribute family not implemented for " + a)
          }

        case Ref(ns, refAttr, _, _) =>
          prevRefs += refAttr
          resolve(nsMap, tail, resolvers :+ addRef(ns, refAttr), outerTpl, tplIndex)

        case BackRef(backRefNs) =>
          tail.head match {
            case Ref(_, refAttr, _, _) if prevRefs.contains(refAttr) => throw ModelError(
              s"Can't re-use previous namespace ${refAttr.capitalize} after backref _$backRefNs."
            )
            case _                                                   => // ok
          }
          resolve(nsMap, tail, resolvers :+ addBackRef(backRefNs), outerTpl, tplIndex)

        case Nested(Ref(ns, refAttr, _, _), nestedElements) =>
          prevRefs.clear()
          resolve(nsMap, tail, resolvers :+
            addNested(nsMap, tplIndex, ns, refAttr, nestedElements), 0, tplIndex)

        case NestedOpt(Ref(ns, refAttr, _, _), nestedElements) =>
          prevRefs.clear()
          resolve(nsMap, tail, resolvers :+
            addNested(nsMap, tplIndex, ns, refAttr, nestedElements), 0, tplIndex)

        case Composite(compositeElements) =>
          resolve(nsMap, tail, resolvers :+
            addComposite(nsMap, outerTpl, tplIndex, compositeElements), outerTpl + 1, tplIndex + 1)

        // TxMetaData is handed separately in Insert_stmts with call to save_stmts

        case other => throw ModelError("Unexpected element: " + other)
      }
      case Nil             => resolvers
    }
  }


  private def resolveAttrOneMan(a: AttrOneMan, outerTpl: Int, tplIndex: Int): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case at: AttrOneManString =>
        val validate = at.validator.fold((_: String) => Seq.empty[String])(validator =>
          (v: String) => validator.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueString, validate)

      case at: AttrOneManInt =>
        val validate = at.validator.fold((_: Int) => Seq.empty[String])(validator =>
          (v: Int) => validator.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueInt, validate)

      case at: AttrOneManLong =>
        val validate = at.validator.fold((_: Long) => Seq.empty[String])(validator =>
          (v: Long) => validator.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueLong, validate)

      case at: AttrOneManFloat =>
        val validate = at.validator.fold((_: Float) => Seq.empty[String])(validator =>
          (v: Float) => validator.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueFloat, validate)

      case at: AttrOneManDouble =>
        val validate = at.validator.fold((_: Double) => Seq.empty[String])(validator =>
          (v: Double) => validator.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueDouble, validate)

      case at: AttrOneManBoolean =>
        val validate = at.validator.fold((_: Boolean) => Seq.empty[String])(validator =>
          (v: Boolean) => validator.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueBoolean, validate)

      case at: AttrOneManBigInt =>
        val validate = at.validator.fold((_: BigInt) => Seq.empty[String])(validator =>
          (v: BigInt) => validator.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueBigInt, validate)

      case at: AttrOneManBigDecimal =>
        val validate = at.validator.fold((_: BigDecimal) => Seq.empty[String])(validator =>
          (v: BigDecimal) => validator.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueBigDecimal, validate)

      case at: AttrOneManDate =>
        val validate = at.validator.fold((_: Date) => Seq.empty[String])(validator =>
          (v: Date) => validator.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueDate, validate)

      case at: AttrOneManUUID =>
        val validate = at.validator.fold((_: UUID) => Seq.empty[String])(validator =>
          (v: UUID) => validator.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueUUID, validate)

      case at: AttrOneManURI =>
        val validate = at.validator.fold((_: URI) => Seq.empty[String])(validator =>
          (v: URI) => validator.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueURI, validate)

      case at: AttrOneManByte =>
        val validate = at.validator.fold((_: Byte) => Seq.empty[String])(validator =>
          (v: Byte) => validator.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueByte, validate)

      case at: AttrOneManShort =>
        val validate = at.validator.fold((_: Short) => Seq.empty[String])(validator =>
          (v: Short) => validator.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueShort, validate)

      case at: AttrOneManChar =>
        val validate = at.validator.fold((_: Char) => Seq.empty[String])(validator =>
          (v: Char) => validator.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueChar, validate)
    }
  }


  private def resolveAttrOneOpt(a: AttrOneOpt, outerTpl: Int, tplIndex: Int): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case at: AttrOneOptString =>
        val validate = at.validator.fold((_: String) => Seq.empty[String])(validator =>
          (v: String) => validator.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueString, validate)

      case at: AttrOneOptInt =>
        val validate = at.validator.fold((_: Int) => Seq.empty[String])(validator =>
          (v: Int) => validator.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueInt, validate)

      case at: AttrOneOptLong =>
        val validate = at.validator.fold((_: Long) => Seq.empty[String])(validator =>
          (v: Long) => validator.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueLong, validate)

      case at: AttrOneOptFloat =>
        val validate = at.validator.fold((_: Float) => Seq.empty[String])(validator =>
          (v: Float) => validator.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueFloat, validate)

      case at: AttrOneOptDouble =>
        val validate = at.validator.fold((_: Double) => Seq.empty[String])(validator =>
          (v: Double) => validator.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueDouble, validate)

      case at: AttrOneOptBoolean =>
        val validate = at.validator.fold((_: Boolean) => Seq.empty[String])(validator =>
          (v: Boolean) => validator.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueBoolean, validate)

      case at: AttrOneOptBigInt =>
        val validate = at.validator.fold((_: BigInt) => Seq.empty[String])(validator =>
          (v: BigInt) => validator.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueBigInt, validate)

      case at: AttrOneOptBigDecimal =>
        val validate = at.validator.fold((_: BigDecimal) => Seq.empty[String])(validator =>
          (v: BigDecimal) => validator.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueBigDecimal, validate)

      case at: AttrOneOptDate =>
        val validate = at.validator.fold((_: Date) => Seq.empty[String])(validator =>
          (v: Date) => validator.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueDate, validate)

      case at: AttrOneOptUUID =>
        val validate = at.validator.fold((_: UUID) => Seq.empty[String])(validator =>
          (v: UUID) => validator.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueUUID, validate)

      case at: AttrOneOptURI =>
        val validate = at.validator.fold((_: URI) => Seq.empty[String])(validator =>
          (v: URI) => validator.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueURI, validate)

      case at: AttrOneOptByte =>
        val validate = at.validator.fold((_: Byte) => Seq.empty[String])(validator =>
          (v: Byte) => validator.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueByte, validate)

      case at: AttrOneOptShort =>
        val validate = at.validator.fold((_: Short) => Seq.empty[String])(validator =>
          (v: Short) => validator.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueShort, validate)

      case at: AttrOneOptChar =>
        val validate = at.validator.fold((_: Char) => Seq.empty[String])(validator =>
          (v: Char) => validator.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueChar, validate)
    }
  }


  private def resolveAttrSetMan(
    a: AttrSetMan,
    outerTpl: Int,
    tplIndex: Int,
    mandatory: Boolean
  ): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case at: AttrSetManString =>
        val validate = at.validator.fold((_: String) => Seq.empty[String])(validator =>
          (v: String) => validator.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, mandatory, valueString, validate)

      case at: AttrSetManInt =>
        val validate = at.validator.fold((_: Int) => Seq.empty[String])(validator =>
          (v: Int) => validator.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, mandatory, valueInt, validate)

      case at: AttrSetManLong =>
        val validate = at.validator.fold((_: Long) => Seq.empty[String])(validator =>
          (v: Long) => validator.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, mandatory, valueLong, validate)

      case at: AttrSetManFloat =>
        val validate = at.validator.fold((_: Float) => Seq.empty[String])(validator =>
          (v: Float) => validator.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, mandatory, valueFloat, validate)

      case at: AttrSetManDouble =>
        val validate = at.validator.fold((_: Double) => Seq.empty[String])(validator =>
          (v: Double) => validator.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, mandatory, valueDouble, validate)

      case at: AttrSetManBoolean =>
        val validate = at.validator.fold((_: Boolean) => Seq.empty[String])(validator =>
          (v: Boolean) => validator.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, mandatory, valueBoolean, validate)

      case at: AttrSetManBigInt =>
        val validate = at.validator.fold((_: BigInt) => Seq.empty[String])(validator =>
          (v: BigInt) => validator.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, mandatory, valueBigInt, validate)

      case at: AttrSetManBigDecimal =>
        val validate = at.validator.fold((_: BigDecimal) => Seq.empty[String])(validator =>
          (v: BigDecimal) => validator.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, mandatory, valueBigDecimal, validate)

      case at: AttrSetManDate =>
        val validate = at.validator.fold((_: Date) => Seq.empty[String])(validator =>
          (v: Date) => validator.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, mandatory, valueDate, validate)

      case at: AttrSetManUUID =>
        val validate = at.validator.fold((_: UUID) => Seq.empty[String])(validator =>
          (v: UUID) => validator.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, mandatory, valueUUID, validate)

      case at: AttrSetManURI =>
        val validate = at.validator.fold((_: URI) => Seq.empty[String])(validator =>
          (v: URI) => validator.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, mandatory, valueURI, validate)

      case at: AttrSetManByte =>
        val validate = at.validator.fold((_: Byte) => Seq.empty[String])(validator =>
          (v: Byte) => validator.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, mandatory, valueByte, validate)

      case at: AttrSetManShort =>
        val validate = at.validator.fold((_: Short) => Seq.empty[String])(validator =>
          (v: Short) => validator.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, mandatory, valueShort, validate)

      case at: AttrSetManChar =>
        val validate = at.validator.fold((_: Char) => Seq.empty[String])(validator =>
          (v: Char) => validator.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, mandatory, valueChar, validate)
    }
  }


  private def resolveAttrSetOpt(a: AttrSetOpt, outerTpl: Int, tplIndex: Int): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case at: AttrSetOptString =>
        val validate = at.validator.fold((_: String) => Seq.empty[String])(validator =>
          (v: String) => validator.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueString, validate)

      case at: AttrSetOptInt =>
        val validate = at.validator.fold((_: Int) => Seq.empty[String])(validator =>
          (v: Int) => validator.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueInt, validate)

      case at: AttrSetOptLong =>
        val validate = at.validator.fold((_: Long) => Seq.empty[String])(validator =>
          (v: Long) => validator.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueLong, validate)

      case at: AttrSetOptFloat =>
        val validate = at.validator.fold((_: Float) => Seq.empty[String])(validator =>
          (v: Float) => validator.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueFloat, validate)

      case at: AttrSetOptDouble =>
        val validate = at.validator.fold((_: Double) => Seq.empty[String])(validator =>
          (v: Double) => validator.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueDouble, validate)

      case at: AttrSetOptBoolean =>
        val validate = at.validator.fold((_: Boolean) => Seq.empty[String])(validator =>
          (v: Boolean) => validator.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueBoolean, validate)

      case at: AttrSetOptBigInt =>
        val validate = at.validator.fold((_: BigInt) => Seq.empty[String])(validator =>
          (v: BigInt) => validator.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueBigInt, validate)

      case at: AttrSetOptBigDecimal =>
        val validate = at.validator.fold((_: BigDecimal) => Seq.empty[String])(validator =>
          (v: BigDecimal) => validator.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueBigDecimal, validate)

      case at: AttrSetOptDate =>
        val validate = at.validator.fold((_: Date) => Seq.empty[String])(validator =>
          (v: Date) => validator.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueDate, validate)

      case at: AttrSetOptUUID =>
        val validate = at.validator.fold((_: UUID) => Seq.empty[String])(validator =>
          (v: UUID) => validator.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueUUID, validate)

      case at: AttrSetOptURI =>
        val validate = at.validator.fold((_: URI) => Seq.empty[String])(validator =>
          (v: URI) => validator.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueURI, validate)

      case at: AttrSetOptByte =>
        val validate = at.validator.fold((_: Byte) => Seq.empty[String])(validator =>
          (v: Byte) => validator.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueByte, validate)

      case at: AttrSetOptShort =>
        val validate = at.validator.fold((_: Short) => Seq.empty[String])(validator =>
          (v: Short) => validator.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueShort, validate)

      case at: AttrSetOptChar =>
        val validate = at.validator.fold((_: Char) => Seq.empty[String])(validator =>
          (v: Char) => validator.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueChar, validate)
    }
  }
}