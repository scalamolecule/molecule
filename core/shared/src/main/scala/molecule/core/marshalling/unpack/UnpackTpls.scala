package molecule.core.marshalling.unpack

import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.util.ModelUtils
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

trait UnpackTpls[Tpl] extends ModelUtils { self: DTO2tpls[Tpl] =>

  private val prevRefs: ListBuffer[String] = ListBuffer.empty[String]

  @tailrec
  final protected def resolveUnpackers(
    elements: List[Element],
    unpackers: List[() => Any],
    level: Int
  ): List[() => Any] = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          a match {
            case a: AttrOne =>
              a match {
                case a: AttrOneMan => resolveUnpackers(tail, unpackers :+ unpackAttrOneMan(a), level)
                case a: AttrOneOpt => resolveUnpackers(tail, unpackers :+ unpackAttrOneOpt(a), level)
                case _: AttrOneTac => resolveUnpackers(tail, unpackers, level)
              }
            case a: AttrSet =>
              a match {
                case a: AttrSetMan => resolveUnpackers(tail, unpackers :+ unpackAttrSetMan(a), level)
                case a: AttrSetOpt => resolveUnpackers(tail, unpackers :+ unpackAttrSetOpt(a), level)
                case _: AttrSetTac => resolveUnpackers(tail, unpackers, level)
              }
          }

        case Ref(_, refAttr, _, _) =>
          prevRefs += refAttr
          resolveUnpackers(tail, unpackers, level)

        case BackRef(backRefNs) =>
          tail.head match {
            case Ref(_, refAttr, _, _) if prevRefs.contains(refAttr) => throw MoleculeError(
              s"Can't re-use previous namespace ${refAttr.capitalize} after backref _$backRefNs."
            )
            case _                                                   => // ok
          }
          resolveUnpackers(tail, unpackers, level)

        case Nested(_, nestedElements) =>
          prevRefs.clear()
          resolveUnpackers(tail, unpackers :+ unpackNested(level + 1, nestedElements), level)

        case NestedOpt(_, nestedElements) =>
          prevRefs.clear()
          resolveUnpackers(tail, unpackers :+ unpackNested(level + 1, nestedElements), level)

        case Composite(compositeElements) =>
          resolveUnpackers(tail, unpackers ++ unpackComposite(level, compositeElements), level)

        case TxMetaData(txMetaDataElements) =>
          // Tx meta data is last attribute values in top level tuple
          unpackers ++ unpackTxMetaData(txMetaDataElements, level)
      }
      case Nil             => unpackers
    }
  }

  private def unpackTxMetaData(
    txMetaDataElements: List[Element],
    level: Int
  ): List[() => Any] = {
    resolveUnpackers(txMetaDataElements, Nil, level)
  }

  private def unpackComposite(
    level: Int,
    compositeElements: List[Element]
  ): Seq[() => Any] = {
    countValueAttrs(compositeElements) match {
      case 0 => Nil
      case n =>
        val unpackCompositeData = getUnpacker(compositeElements, level)
        Seq(() => unpackCompositeData())
    }
  }

  private def unpackNested(
    level: Int,
    nestedElements: List[Element]
  ): () => Any = {
    // Recursively unpack nested levels
    val unpackNestedData = getUnpacker(nestedElements, level)
    () => {
      (0 until levelCounts(level).next()).toList.map { _ =>
        unpackNestedData()
      }
    }
  }


  protected def unexpected(element: Element) =
    throw MoleculeError("Unexpected element: " + element)

  private def unpackAttrOneMan(a: AttrOneMan): () => Any = {
    a.op match {
      case Fn(kw, _) => kw match {
        case "count" | "countDistinct"                          => () => oneInt.next()
        case "distinct" | "mins" | "maxs" | "rands" | "samples" => unpackAttrOneManSet(a)
        case "avg" | "variance" | "stddev"                      => () => oneDouble.next()
        case _                                                  => unpackAttrOneManV(a)
      }
      case _         => unpackAttrOneManV(a)
    }
  }
  private def unpackAttrOneManV(a: AttrOneMan): () => Any = {
    a match {
      case _: AttrOneManString     => () => oneString.next()
      case _: AttrOneManInt        => () => oneInt.next()
      case _: AttrOneManLong       => () => oneLong.next()
      case _: AttrOneManFloat      => () => oneFloat.next()
      case _: AttrOneManDouble     => () => oneDouble.next()
      case _: AttrOneManBoolean    => () => oneBoolean.next()
      case _: AttrOneManBigInt     => () => oneBigInt.next()
      case _: AttrOneManBigDecimal => () => oneBigDecimal.next()
      case _: AttrOneManDate       => () => oneDate.next()
      case _: AttrOneManUUID       => () => oneUUID.next()
      case _: AttrOneManURI        => () => oneURI.next()
      case _: AttrOneManByte       => () => oneByte.next()
      case _: AttrOneManShort      => () => oneShort.next()
      case _: AttrOneManChar       => () => oneChar.next()
    }
  }
  private def unpackAttrOneManSet(a: AttrOneMan): () => Any = {
    a match {
      case _: AttrOneManString     => () => setString.next()
      case _: AttrOneManInt        => () => setInt.next()
      case _: AttrOneManLong       => () => setLong.next()
      case _: AttrOneManFloat      => () => setFloat.next()
      case _: AttrOneManDouble     => () => setDouble.next()
      case _: AttrOneManBoolean    => () => setBoolean.next()
      case _: AttrOneManBigInt     => () => setBigInt.next()
      case _: AttrOneManBigDecimal => () => setBigDecimal.next()
      case _: AttrOneManDate       => () => setDate.next()
      case _: AttrOneManUUID       => () => setUUID.next()
      case _: AttrOneManURI        => () => setURI.next()
      case _: AttrOneManByte       => () => setByte.next()
      case _: AttrOneManShort      => () => setShort.next()
      case _: AttrOneManChar       => () => setChar.next()
    }
  }

  private def unpackAttrOneOpt(a: AttrOneOpt): () => Any = {
    a match {
      case _: AttrOneOptString     => () => oneOptString.next()
      case _: AttrOneOptInt        => () => oneOptInt.next()
      case _: AttrOneOptLong       => () => oneOptLong.next()
      case _: AttrOneOptFloat      => () => oneOptFloat.next()
      case _: AttrOneOptDouble     => () => oneOptDouble.next()
      case _: AttrOneOptBoolean    => () => oneOptBoolean.next()
      case _: AttrOneOptBigInt     => () => oneOptBigInt.next()
      case _: AttrOneOptBigDecimal => () => oneOptBigDecimal.next()
      case _: AttrOneOptDate       => () => oneOptDate.next()
      case _: AttrOneOptUUID       => () => oneOptUUID.next()
      case _: AttrOneOptURI        => () => oneOptURI.next()
      case _: AttrOneOptByte       => () => oneOptByte.next()
      case _: AttrOneOptShort      => () => oneOptShort.next()
      case _: AttrOneOptChar       => () => oneOptChar.next()
    }
  }

  private def unpackAttrSetMan(a: AttrSetMan): () => Any = {
    a.op match {
      case Fn(kw, _) => kw match {
        case "count" | "countDistinct"             => () => oneInt.next()
        case "distinct"                            => unpackAttrSetManSet(a)
        case "mins" | "maxs" | "rands" | "samples" => unpackAttrSetManV(a)
        case "avg" | "variance" | "stddev"         => () => setDouble.next()
        case _                                     => unpackAttrSetManV(a)
      }
      case _         => unpackAttrSetManV(a)
    }
  }
  private def unpackAttrSetManV(a: AttrSetMan): () => Any = {
    a match {
      case _: AttrSetManString     => () => setString.next()
      case _: AttrSetManInt        => () => setInt.next()
      case _: AttrSetManLong       => () => setLong.next()
      case _: AttrSetManFloat      => () => setFloat.next()
      case _: AttrSetManDouble     => () => setDouble.next()
      case _: AttrSetManBoolean    => () => setBoolean.next()
      case _: AttrSetManBigInt     => () => setBigInt.next()
      case _: AttrSetManBigDecimal => () => setBigDecimal.next()
      case _: AttrSetManDate       => () => setDate.next()
      case _: AttrSetManUUID       => () => setUUID.next()
      case _: AttrSetManURI        => () => setURI.next()
      case _: AttrSetManByte       => () => setByte.next()
      case _: AttrSetManShort      => () => setShort.next()
      case _: AttrSetManChar       => () => setChar.next()
    }
  }
  private def unpackAttrSetManSet(a: AttrSetMan): () => Any = {
    a match {
      case _: AttrSetManString     => unpackSets(setString)
      case _: AttrSetManInt        => unpackSets(setInt)
      case _: AttrSetManLong       => unpackSets(setLong)
      case _: AttrSetManFloat      => unpackSets(setFloat)
      case _: AttrSetManDouble     => unpackSets(setDouble)
      case _: AttrSetManBoolean    => unpackSets(setBoolean)
      case _: AttrSetManBigInt     => unpackSets(setBigInt)
      case _: AttrSetManBigDecimal => unpackSets(setBigDecimal)
      case _: AttrSetManDate       => unpackSets(setDate)
      case _: AttrSetManUUID       => unpackSets(setUUID)
      case _: AttrSetManURI        => unpackSets(setURI)
      case _: AttrSetManByte       => unpackSets(setByte)
      case _: AttrSetManShort      => unpackSets(setShort)
      case _: AttrSetManChar       => unpackSets(setChar)
    }
  }
  private def unpackSets[T](it: Iterator[Set[T]]): () => Set[Set[T]] = {
    val counts = levelCounts.head
    () => {
      var sets = Set.empty[Set[T]]
      (0 until counts.next()).foreach(_ =>
        sets = sets + it.next()
      )
      sets
    }
  }

  private def unpackAttrSetOpt(a: AttrSetOpt): () => Any = {
    a match {
      case _: AttrSetOptString     => () => setOptString.next()
      case _: AttrSetOptInt        => () => setOptInt.next()
      case _: AttrSetOptLong       => () => setOptLong.next()
      case _: AttrSetOptFloat      => () => setOptFloat.next()
      case _: AttrSetOptDouble     => () => setOptDouble.next()
      case _: AttrSetOptBoolean    => () => setOptBoolean.next()
      case _: AttrSetOptBigInt     => () => setOptBigInt.next()
      case _: AttrSetOptBigDecimal => () => setOptBigDecimal.next()
      case _: AttrSetOptDate       => () => setOptDate.next()
      case _: AttrSetOptUUID       => () => setOptUUID.next()
      case _: AttrSetOptURI        => () => setOptURI.next()
      case _: AttrSetOptByte       => () => setOptByte.next()
      case _: AttrSetOptShort      => () => setOptShort.next()
      case _: AttrSetOptChar       => () => setOptChar.next()
    }
  }
}
