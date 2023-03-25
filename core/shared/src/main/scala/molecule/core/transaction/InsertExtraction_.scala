package molecule.core.transaction

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.error.{ExecutionError, InsertError}
import molecule.boilerplate.ast.Model._
import scala.annotation.tailrec


class InsertExtraction_ extends InsertResolvers_ { self: InsertOps =>

  @tailrec
  final override def resolve(
    elements: List[Element],
    resolvers: List[Product => Seq[InsertError]],
    outerTpl: Int,
    tplIndex: Int
  ): List[Product => Seq[InsertError]] = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          if (a.op != V) {
            throw ExecutionError("Can't insert attributes with an applied value. Found:\n" + a)
          }
          a match {
            case a: AttrOne =>
              a match {
                case a: AttrOneMan => resolve(tail, resolvers :+
                  resolveAttrOneMan(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)

                case a: AttrOneOpt => resolve(tail, resolvers :+
                  resolveAttrOneOpt(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)
              }
            case a: AttrSet =>
              a match {
                case a: AttrSetMan => resolve(tail, resolvers :+
                  resolveAttrSetMan(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)

                case a: AttrSetOpt => resolve(tail, resolvers :+
                  resolveAttrSetOpt(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)
              }
          }

        case Ref(ns, refAttr, _, _) =>
          prevRefs += refAttr
          resolve(tail, resolvers :+ addRef(ns, refAttr), outerTpl, tplIndex)

        case BackRef(backRefNs) =>
          tail.head match {
            case Ref(_, refAttr, _, _) if prevRefs.contains(refAttr) => throw ExecutionError(
              s"Can't re-use previous namespace ${refAttr.capitalize} after backref _$backRefNs."
            )
            case _                                                   => // ok
          }
          resolve(tail, resolvers :+ addBackRef(backRefNs), outerTpl, tplIndex)

        case Nested(Ref(ns, refAttr, _, _), nestedElements) =>
          prevRefs.clear()
          resolve(tail, resolvers :+
            addNested(tplIndex, ns, refAttr, nestedElements), 0, tplIndex)

        case NestedOpt(Ref(ns, refAttr, _, _), nestedElements) =>
          prevRefs.clear()
          resolve(tail, resolvers :+
            addNested(tplIndex, ns, refAttr, nestedElements), 0, tplIndex)

        case Composite(compositeElements) =>
          resolve(tail, resolvers :+
            addComposite(outerTpl, tplIndex, compositeElements), outerTpl + 1, tplIndex + 1)

        // TxMetaData is handed separately in Insert_stmts with call to save_stmts

        case other => throw ExecutionError("Unexpected element: " + other)
      }
      case Nil             => resolvers
    }
  }


  private def resolveAttrOneMan(a: AttrOneMan, outerTpl: Int, tplIndex: Int): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case at: AttrOneManString =>
        val validate = at.validation.fold((_: String) => Seq.empty[String])(validation =>
          (v: String) => validation.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueString, validate)

      case at: AttrOneManInt =>
        val validate = at.validation.fold((_: Int) => Seq.empty[String])(validation =>
          (v: Int) => validation.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueInt, validate)

      case at: AttrOneManLong =>
        val validate = at.validation.fold((_: Long) => Seq.empty[String])(validation =>
          (v: Long) => validation.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueLong, validate)

      case at: AttrOneManFloat =>
        val validate = at.validation.fold((_: Float) => Seq.empty[String])(validation =>
          (v: Float) => validation.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueFloat, validate)

      case at: AttrOneManDouble =>
        val validate = at.validation.fold((_: Double) => Seq.empty[String])(validation =>
          (v: Double) => validation.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueDouble, validate)

      case at: AttrOneManBoolean =>
        val validate = at.validation.fold((_: Boolean) => Seq.empty[String])(validation =>
          (v: Boolean) => validation.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueBoolean, validate)

      case at: AttrOneManBigInt =>
        val validate = at.validation.fold((_: BigInt) => Seq.empty[String])(validation =>
          (v: BigInt) => validation.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueBigInt, validate)

      case at: AttrOneManBigDecimal =>
        val validate = at.validation.fold((_: BigDecimal) => Seq.empty[String])(validation =>
          (v: BigDecimal) => validation.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueBigDecimal, validate)

      case at: AttrOneManDate =>
        val validate = at.validation.fold((_: Date) => Seq.empty[String])(validation =>
          (v: Date) => validation.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueDate, validate)

      case at: AttrOneManUUID =>
        val validate = at.validation.fold((_: UUID) => Seq.empty[String])(validation =>
          (v: UUID) => validation.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueUUID, validate)

      case at: AttrOneManURI =>
        val validate = at.validation.fold((_: URI) => Seq.empty[String])(validation =>
          (v: URI) => validation.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueURI, validate)

      case at: AttrOneManByte =>
        val validate = at.validation.fold((_: Byte) => Seq.empty[String])(validation =>
          (v: Byte) => validation.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueByte, validate)

      case at: AttrOneManShort =>
        val validate = at.validation.fold((_: Short) => Seq.empty[String])(validation =>
          (v: Short) => validation.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueShort, validate)

      case at: AttrOneManChar =>
        val validate = at.validation.fold((_: Char) => Seq.empty[String])(validation =>
          (v: Char) => validation.validate(v)
        )
        addV(ns, attr, outerTpl, tplIndex, valueChar, validate)
    }
  }


  private def resolveAttrOneOpt(a: AttrOneOpt, outerTpl: Int, tplIndex: Int): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case at: AttrOneOptString =>
        val validate = at.validation.fold((_: String) => Seq.empty[String])(validation =>
          (v: String) => validation.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueString, validate)

      case at: AttrOneOptInt =>
        val validate = at.validation.fold((_: Int) => Seq.empty[String])(validation =>
          (v: Int) => validation.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueInt, validate)

      case at: AttrOneOptLong =>
        val validate = at.validation.fold((_: Long) => Seq.empty[String])(validation =>
          (v: Long) => validation.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueLong, validate)

      case at: AttrOneOptFloat =>
        val validate = at.validation.fold((_: Float) => Seq.empty[String])(validation =>
          (v: Float) => validation.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueFloat, validate)

      case at: AttrOneOptDouble =>
        val validate = at.validation.fold((_: Double) => Seq.empty[String])(validation =>
          (v: Double) => validation.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueDouble, validate)

      case at: AttrOneOptBoolean =>
        val validate = at.validation.fold((_: Boolean) => Seq.empty[String])(validation =>
          (v: Boolean) => validation.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueBoolean, validate)

      case at: AttrOneOptBigInt =>
        val validate = at.validation.fold((_: BigInt) => Seq.empty[String])(validation =>
          (v: BigInt) => validation.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueBigInt, validate)

      case at: AttrOneOptBigDecimal =>
        val validate = at.validation.fold((_: BigDecimal) => Seq.empty[String])(validation =>
          (v: BigDecimal) => validation.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueBigDecimal, validate)

      case at: AttrOneOptDate =>
        val validate = at.validation.fold((_: Date) => Seq.empty[String])(validation =>
          (v: Date) => validation.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueDate, validate)

      case at: AttrOneOptUUID =>
        val validate = at.validation.fold((_: UUID) => Seq.empty[String])(validation =>
          (v: UUID) => validation.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueUUID, validate)

      case at: AttrOneOptURI =>
        val validate = at.validation.fold((_: URI) => Seq.empty[String])(validation =>
          (v: URI) => validation.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueURI, validate)

      case at: AttrOneOptByte =>
        val validate = at.validation.fold((_: Byte) => Seq.empty[String])(validation =>
          (v: Byte) => validation.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueByte, validate)

      case at: AttrOneOptShort =>
        val validate = at.validation.fold((_: Short) => Seq.empty[String])(validation =>
          (v: Short) => validation.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueShort, validate)

      case at: AttrOneOptChar =>
        val validate = at.validation.fold((_: Char) => Seq.empty[String])(validation =>
          (v: Char) => validation.validate(v)
        )
        addOptV(ns, attr, outerTpl, tplIndex, valueChar, validate)
    }
  }


  private def resolveAttrSetMan(a: AttrSetMan, outerTpl: Int, tplIndex: Int): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case at: AttrSetManString =>
        val validate = at.validation.fold((_: String) => Seq.empty[String])(validation =>
          (v: String) => validation.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, valueString, validate)

      case at: AttrSetManInt =>
        val validate = at.validation.fold((_: Int) => Seq.empty[String])(validation =>
          (v: Int) => validation.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, valueInt, validate)

      case at: AttrSetManLong =>
        val validate = at.validation.fold((_: Long) => Seq.empty[String])(validation =>
          (v: Long) => validation.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, valueLong, validate)

      case at: AttrSetManFloat =>
        val validate = at.validation.fold((_: Float) => Seq.empty[String])(validation =>
          (v: Float) => validation.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, valueFloat, validate)

      case at: AttrSetManDouble =>
        val validate = at.validation.fold((_: Double) => Seq.empty[String])(validation =>
          (v: Double) => validation.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, valueDouble, validate)

      case at: AttrSetManBoolean =>
        val validate = at.validation.fold((_: Boolean) => Seq.empty[String])(validation =>
          (v: Boolean) => validation.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, valueBoolean, validate)

      case at: AttrSetManBigInt =>
        val validate = at.validation.fold((_: BigInt) => Seq.empty[String])(validation =>
          (v: BigInt) => validation.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, valueBigInt, validate)

      case at: AttrSetManBigDecimal =>
        val validate = at.validation.fold((_: BigDecimal) => Seq.empty[String])(validation =>
          (v: BigDecimal) => validation.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, valueBigDecimal, validate)

      case at: AttrSetManDate =>
        val validate = at.validation.fold((_: Date) => Seq.empty[String])(validation =>
          (v: Date) => validation.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, valueDate, validate)

      case at: AttrSetManUUID =>
        val validate = at.validation.fold((_: UUID) => Seq.empty[String])(validation =>
          (v: UUID) => validation.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, valueUUID, validate)

      case at: AttrSetManURI =>
        val validate = at.validation.fold((_: URI) => Seq.empty[String])(validation =>
          (v: URI) => validation.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, valueURI, validate)

      case at: AttrSetManByte =>
        val validate = at.validation.fold((_: Byte) => Seq.empty[String])(validation =>
          (v: Byte) => validation.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, valueByte, validate)

      case at: AttrSetManShort =>
        val validate = at.validation.fold((_: Short) => Seq.empty[String])(validation =>
          (v: Short) => validation.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, valueShort, validate)

      case at: AttrSetManChar =>
        val validate = at.validation.fold((_: Char) => Seq.empty[String])(validation =>
          (v: Char) => validation.validate(v)
        )
        addSet(ns, attr, outerTpl, tplIndex, valueChar, validate)
    }
  }


  private def resolveAttrSetOpt(a: AttrSetOpt, outerTpl: Int, tplIndex: Int): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case at: AttrSetOptString =>
        val validate = at.validation.fold((_: String) => Seq.empty[String])(validation =>
          (v: String) => validation.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueString, validate)

      case at: AttrSetOptInt =>
        val validate = at.validation.fold((_: Int) => Seq.empty[String])(validation =>
          (v: Int) => validation.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueInt, validate)

      case at: AttrSetOptLong =>
        val validate = at.validation.fold((_: Long) => Seq.empty[String])(validation =>
          (v: Long) => validation.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueLong, validate)

      case at: AttrSetOptFloat =>
        val validate = at.validation.fold((_: Float) => Seq.empty[String])(validation =>
          (v: Float) => validation.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueFloat, validate)

      case at: AttrSetOptDouble =>
        val validate = at.validation.fold((_: Double) => Seq.empty[String])(validation =>
          (v: Double) => validation.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueDouble, validate)

      case at: AttrSetOptBoolean =>
        val validate = at.validation.fold((_: Boolean) => Seq.empty[String])(validation =>
          (v: Boolean) => validation.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueBoolean, validate)

      case at: AttrSetOptBigInt =>
        val validate = at.validation.fold((_: BigInt) => Seq.empty[String])(validation =>
          (v: BigInt) => validation.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueBigInt, validate)

      case at: AttrSetOptBigDecimal =>
        val validate = at.validation.fold((_: BigDecimal) => Seq.empty[String])(validation =>
          (v: BigDecimal) => validation.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueBigDecimal, validate)

      case at: AttrSetOptDate =>
        val validate = at.validation.fold((_: Date) => Seq.empty[String])(validation =>
          (v: Date) => validation.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueDate, validate)

      case at: AttrSetOptUUID =>
        val validate = at.validation.fold((_: UUID) => Seq.empty[String])(validation =>
          (v: UUID) => validation.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueUUID, validate)

      case at: AttrSetOptURI =>
        val validate = at.validation.fold((_: URI) => Seq.empty[String])(validation =>
          (v: URI) => validation.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueURI, validate)

      case at: AttrSetOptByte =>
        val validate = at.validation.fold((_: Byte) => Seq.empty[String])(validation =>
          (v: Byte) => validation.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueByte, validate)

      case at: AttrSetOptShort =>
        val validate = at.validation.fold((_: Short) => Seq.empty[String])(validation =>
          (v: Short) => validation.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueShort, validate)

      case at: AttrSetOptChar =>
        val validate = at.validation.fold((_: Char) => Seq.empty[String])(validation =>
          (v: Char) => validation.validate(v)
        )
        addOptSet(ns, attr, outerTpl, tplIndex, valueChar, validate)
    }
  }
}