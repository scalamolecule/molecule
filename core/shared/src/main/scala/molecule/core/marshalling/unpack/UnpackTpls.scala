package molecule.core.marshalling.unpack

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import scala.annotation.tailrec

trait UnpackTpls[Tpl] { self: DTO2tpls[Tpl] =>


  @tailrec
  final protected def resolveUnpackers(
    elements: Seq[Element],
    acc: List[() => Any]
  ): List[() => Any] = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          a match {
            case a: AttrOne =>
              a match {
                case a: AttrOneMan => resolveUnpackers(tail, acc :+ unpackAttrOneMan(a))
                case a: AttrOneOpt => resolveUnpackers(tail, acc :+ unpackAttrOneOpt(a))
              }
            case a: AttrSet =>
              a match {
                case a: AttrSetMan => resolveUnpackers(tail, acc :+ unpackAttrSetMan(a))
                case a: AttrSetOpt => resolveUnpackers(tail, acc :+ unpackAttrSetOpt(a))
              }
          }

        //        case Ref(ns, refAttr, _, _) =>
        //          prevRefs += refAttr
        //          resolve(tail, resolvers :+ addRef(ns, refAttr), n)
        //
        //        case BackRef(backRefNs) =>
        //          tail.head match {
        //            case Ref(_, refAttr, _, _) if prevRefs.contains(refAttr) => throw MoleculeException(
        //              s"Can't re-use previous namespace ${refAttr.capitalize} after backref _$backRefNs."
        //            )
        //            case _                                                   => // ok
        //          }
        //          resolve(tail, resolvers :+ addBackRef(backRefNs), n)
        //
        //        case Nested(Ref(ns, refAttr, _, _), elements) =>
        //          prevRefs.clear()
        //          resolve(tail, resolvers :+ addNested(n, ns, refAttr, elements), n)
        //
        //        case NestedOpt(Ref(ns, refAttr, _, _), elements) =>
        //          prevRefs.clear()
        //          resolve(tail, resolvers :+ addNested(n, ns, refAttr, elements), n)
        //
        //        case Composite(compositeElements) =>
        //          resolve(tail, resolvers :+ addComposite(n, compositeElements), n + 1)

        case other => unexpected(other)
      }
      case Nil             => acc
    }
  }


  protected def unexpected(element: Element) =
    throw MoleculeException("Unexpected element: " + element)

  private def unpackAttrOneMan(a: AttrOneMan): () => Any = {
    a match {
      case _: AttrOneManString     => () => oneString.next()
      case _: AttrOneManInt        => () => oneInt.next()
      case _: AttrOneManLong       => () => oneLong.next()
      case _: AttrOneManDouble     => () => oneDouble.next()
      case _: AttrOneManBoolean    => () => oneBoolean.next()
      case _: AttrOneManBigInt     => () => oneBigInt.next()
      case _: AttrOneManBigDecimal => () => oneBigDecimal.next()
      case _: AttrOneManDate       => () => oneDate.next()
      case _: AttrOneManUUID       => () => oneUUID.next()
      case _: AttrOneManURI        => () => oneURI.next()
      case _: AttrOneManByte       => () => oneByte.next()
      case _: AttrOneManShort      => () => oneShort.next()
      case _: AttrOneManFloat      => () => oneFloat.next()
      case _: AttrOneManChar       => () => oneChar.next()
    }
  }

  private def unpackAttrOneOpt(a: AttrOneOpt): () => Any = {
    a match {
      case _: AttrOneOptString     => () => oneOptString.next()
      case _: AttrOneOptInt        => () => oneOptInt.next()
      case _: AttrOneOptLong       => () => oneOptLong.next()
      case _: AttrOneOptFloat      => () => oneOptDouble.next()
      case _: AttrOneOptDouble     => () => oneOptBoolean.next()
      case _: AttrOneOptBoolean    => () => oneOptBigInt.next()
      case _: AttrOneOptBigInt     => () => oneOptBigDecimal.next()
      case _: AttrOneOptBigDecimal => () => oneOptDate.next()
      case _: AttrOneOptDate       => () => oneOptUUID.next()
      case _: AttrOneOptUUID       => () => oneOptURI.next()
      case _: AttrOneOptURI        => () => oneOptByte.next()
      case _: AttrOneOptByte       => () => oneOptShort.next()
      case _: AttrOneOptShort      => () => oneOptFloat.next()
      case _: AttrOneOptChar       => () => oneOptChar.next()
    }
  }

  private def unpackAttrSetMan(a: AttrSetMan): () => Any = {
    a match {
      case _: AttrSetManString     => () => setString.next()
      case _: AttrSetManInt        => () => setInt.next()
      case _: AttrSetManLong       => () => setLong.next()
      case _: AttrSetManFloat      => () => setDouble.next()
      case _: AttrSetManDouble     => () => setBoolean.next()
      case _: AttrSetManBoolean    => () => setBigInt.next()
      case _: AttrSetManBigInt     => () => setBigDecimal.next()
      case _: AttrSetManBigDecimal => () => setDate.next()
      case _: AttrSetManDate       => () => setUUID.next()
      case _: AttrSetManUUID       => () => setURI.next()
      case _: AttrSetManURI        => () => setByte.next()
      case _: AttrSetManByte       => () => setShort.next()
      case _: AttrSetManShort      => () => setFloat.next()
      case _: AttrSetManChar       => () => setChar.next()
    }
  }

  private def unpackAttrSetOpt(a: AttrSetOpt): () => Any = {
    a match {
      case _: AttrSetOptString     => () => setOptString.next()
      case _: AttrSetOptInt        => () => setOptInt.next()
      case _: AttrSetOptLong       => () => setOptLong.next()
      case _: AttrSetOptFloat      => () => setOptDouble.next()
      case _: AttrSetOptDouble     => () => setOptBoolean.next()
      case _: AttrSetOptBoolean    => () => setOptBigInt.next()
      case _: AttrSetOptBigInt     => () => setOptBigDecimal.next()
      case _: AttrSetOptBigDecimal => () => setOptDate.next()
      case _: AttrSetOptDate       => () => setOptUUID.next()
      case _: AttrSetOptUUID       => () => setOptURI.next()
      case _: AttrSetOptURI        => () => setOptByte.next()
      case _: AttrSetOptByte       => () => setOptShort.next()
      case _: AttrSetOptShort      => () => setOptFloat.next()
      case _: AttrSetOptChar       => () => setOptChar.next()
    }
  }

  private def err(element: Element): Nothing = {
    throw MoleculeException(
      s"""Unexpected element/value when unpacking tuple DTO:
         |  element: $element""".stripMargin
    )
  }
}
