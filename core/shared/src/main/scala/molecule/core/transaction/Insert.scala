package molecule.core.transaction

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import scala.annotation.tailrec


class Insert extends InsertResolvers_ { self: Insert2Data =>

  @tailrec
  final override protected def resolve(
    elements: Seq[Element],
    resolvers: List[Product => Unit],
    n: Int = 0
  ): List[Product => Unit] = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          if (a.op != V) {
            throw MoleculeException("Can't insert attributes with an applied value. Found:\n" + a)
          }
          a match {
            case a: AttrOne =>
              a match {
                case a: AttrOneMan => resolve(tail, resolvers :+ resolveAttrOneMan(a, n), n + 1)
                case a: AttrOneOpt => resolve(tail, resolvers :+ resolveAttrOneOpt(a, n), n + 1)
              }
            case a: AttrSet =>
              a match {
                case a: AttrSetMan => resolve(tail, resolvers :+ resolveAttrSetMan(a, n), n + 1)
                case a: AttrSetOpt => resolve(tail, resolvers :+ resolveAttrSetOpt(a, n), n + 1)
              }
          }

        case Ref(ns, refAttr, _, _) =>
          prevRefs += refAttr
          resolve(tail, resolvers :+ addRef(ns, refAttr), n)

        case BackRef(backRefNs) =>
          tail.head match {
            case Ref(_, refAttr, _, _) if prevRefs.contains(refAttr) => throw MoleculeException(
              s"Can't re-use previous namespace ${refAttr.capitalize} after backref _$backRefNs."
            )
            case _                                                   => // ok
          }
          resolve(tail, resolvers :+ addBackRef(backRefNs), n)

        case Nested(Ref(ns, refAttr, _, _), elements) =>
          prevRefs.clear()
          resolve(tail, resolvers :+ addNested(n, ns, refAttr, elements), n)

        case NestedOpt(Ref(ns, refAttr, _, _), elements) =>
          prevRefs.clear()
          resolve(tail, resolvers :+ addNested(n, ns, refAttr, elements), n)

        case Composite(compositeElements) =>
          resolve(tail, resolvers :+ addComposite(n, compositeElements), n + 1)

        case other => unexpected(other)
      }
      case Nil             => resolvers
    }
  }

  private def resolveAttrOneMan(a: AttrOneMan, n: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrOneManString     => addV(ns, attr, n, valueString)
      case _: AttrOneManInt        => addV(ns, attr, n, valueInt)
      case _: AttrOneManLong       => addV(ns, attr, n, valueLong)
      case _: AttrOneManFloat      => addV(ns, attr, n, valueFloat)
      case _: AttrOneManDouble     => addV(ns, attr, n, valueDouble)
      case _: AttrOneManBoolean    => addV(ns, attr, n, valueBoolean)
      case _: AttrOneManBigInt     => addV(ns, attr, n, valueBigInt)
      case _: AttrOneManBigDecimal => addV(ns, attr, n, valueBigDecimal)
      case _: AttrOneManDate       => addV(ns, attr, n, valueDate)
      case _: AttrOneManUUID       => addV(ns, attr, n, valueUUID)
      case _: AttrOneManURI        => addV(ns, attr, n, valueURI)
      case _: AttrOneManByte       => addV(ns, attr, n, valueByte)
      case _: AttrOneManShort      => addV(ns, attr, n, valueShort)
      case _: AttrOneManChar       => addV(ns, attr, n, valueChar)
    }
  }

  private def resolveAttrOneOpt(a: AttrOneOpt, n: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrOneOptString     => addOptV(ns, attr, n, valueString)
      case _: AttrOneOptInt        => addOptV(ns, attr, n, valueInt)
      case _: AttrOneOptLong       => addOptV(ns, attr, n, valueLong)
      case _: AttrOneOptFloat      => addOptV(ns, attr, n, valueFloat)
      case _: AttrOneOptDouble     => addOptV(ns, attr, n, valueDouble)
      case _: AttrOneOptBoolean    => addOptV(ns, attr, n, valueBoolean)
      case _: AttrOneOptBigInt     => addOptV(ns, attr, n, valueBigInt)
      case _: AttrOneOptBigDecimal => addOptV(ns, attr, n, valueBigDecimal)
      case _: AttrOneOptDate       => addOptV(ns, attr, n, valueDate)
      case _: AttrOneOptUUID       => addOptV(ns, attr, n, valueUUID)
      case _: AttrOneOptURI        => addOptV(ns, attr, n, valueURI)
      case _: AttrOneOptByte       => addOptV(ns, attr, n, valueByte)
      case _: AttrOneOptShort      => addOptV(ns, attr, n, valueShort)
      case _: AttrOneOptChar       => addOptV(ns, attr, n, valueChar)
    }
  }

  private def resolveAttrSetMan(a: AttrSetMan, n: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrSetManString     => addSet(ns, attr, n, valueString)
      case _: AttrSetManInt        => addSet(ns, attr, n, valueInt)
      case _: AttrSetManLong       => addSet(ns, attr, n, valueLong)
      case _: AttrSetManFloat      => addSet(ns, attr, n, valueFloat)
      case _: AttrSetManDouble     => addSet(ns, attr, n, valueDouble)
      case _: AttrSetManBoolean    => addSet(ns, attr, n, valueBoolean)
      case _: AttrSetManBigInt     => addSet(ns, attr, n, valueBigInt)
      case _: AttrSetManBigDecimal => addSet(ns, attr, n, valueBigDecimal)
      case _: AttrSetManDate       => addSet(ns, attr, n, valueDate)
      case _: AttrSetManUUID       => addSet(ns, attr, n, valueUUID)
      case _: AttrSetManURI        => addSet(ns, attr, n, valueURI)
      case _: AttrSetManByte       => addSet(ns, attr, n, valueByte)
      case _: AttrSetManShort      => addSet(ns, attr, n, valueShort)
      case _: AttrSetManChar       => addSet(ns, attr, n, valueChar)
    }
  }

  private def resolveAttrSetOpt(a: AttrSetOpt, n: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrSetOptString     => addOptSet(ns, attr, n, valueString)
      case _: AttrSetOptInt        => addOptSet(ns, attr, n, valueInt)
      case _: AttrSetOptLong       => addOptSet(ns, attr, n, valueLong)
      case _: AttrSetOptFloat      => addOptSet(ns, attr, n, valueFloat)
      case _: AttrSetOptDouble     => addOptSet(ns, attr, n, valueDouble)
      case _: AttrSetOptBoolean    => addOptSet(ns, attr, n, valueBoolean)
      case _: AttrSetOptBigInt     => addOptSet(ns, attr, n, valueBigInt)
      case _: AttrSetOptBigDecimal => addOptSet(ns, attr, n, valueBigDecimal)
      case _: AttrSetOptDate       => addOptSet(ns, attr, n, valueDate)
      case _: AttrSetOptUUID       => addOptSet(ns, attr, n, valueUUID)
      case _: AttrSetOptURI        => addOptSet(ns, attr, n, valueURI)
      case _: AttrSetOptByte       => addOptSet(ns, attr, n, valueByte)
      case _: AttrSetOptShort      => addOptSet(ns, attr, n, valueShort)
      case _: AttrSetOptChar       => addOptSet(ns, attr, n, valueChar)
    }
  }
}