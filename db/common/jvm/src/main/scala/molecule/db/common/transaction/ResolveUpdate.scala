package molecule.db.common.transaction

import java.sql.PreparedStatement as PS
import scala.annotation.tailrec
import molecule.core.dataModel.*
import molecule.core.error.ModelError
import molecule.db.common.util.ModelUtils

class ResolveUpdate(isUpsert: Boolean) extends ModelUtils { self: SqlUpdate =>

  @tailrec
  final def resolve(
    elements: List[Element],
    paramIndex: Int,
    tableUpdates: List[TableUpdate],
    tableUpdate: TableUpdate,
    filterElements: List[Element],
    notNulls: List[(String, String)],
  ): (List[TableUpdate], List[Element], List[(String, String)]) = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          val notNulls1 = if (isUpsert) notNulls else notNulls :+ s"${a.ent}.${a.attr}" -> "IS NOT NULL"
          a match {
            case a: AttrOne => a match {
              case a: AttrOneMan =>
                val tableUpdate1 = tableUpdate.add(resolveAttrOneMan(a, paramIndex))
                resolve(tail, paramIndex + 1, tableUpdates, tableUpdate1, filterElements, notNulls1)

              case a: AttrOneTac =>
                resolve(tail, paramIndex, tableUpdates, tableUpdate, filterElements :+ a, notNulls)

              case _: AttrOneOpt => noOptional(a)
            }

            case a: AttrSet => a match {
              case a: AttrSetMan => a.op match {
                case Eq | NoValue =>
                  val tableUpdate1 = tableUpdate.add(resolveAttrSetMan(a, paramIndex))
                  resolve(tail, paramIndex + 1, tableUpdates, tableUpdate1, filterElements, notNulls1)

                case Add =>
                  val tableUpdate1 = tableUpdate.add(resolveAttrSetAdd(a, paramIndex))
                  resolve(tail, paramIndex + 1, tableUpdates, tableUpdate1, filterElements, notNulls1)

                case Remove =>
                  val tableUpdate1 = tableUpdate.add(resolveAttrSetRemove(a, paramIndex))
                  resolve(tail, paramIndex + 1, tableUpdates, tableUpdate1, filterElements, notNulls1)

                case _ => unsupportedOp(a)
              }
              case a: AttrSetTac =>
                resolve(tail, paramIndex, tableUpdates, tableUpdate, filterElements :+ a, notNulls)
              case _: AttrSetOpt => noOptional(a)
            }

            case a: AttrSeq => a match {
              case a: AttrSeqMan => a.op match {
                case Eq | NoValue =>
                  val tableUpdate1 = tableUpdate.add(resolveAttrSeqMan(a, paramIndex))
                  resolve(tail, paramIndex + 1, tableUpdates, tableUpdate1, filterElements, notNulls1)

                case Add          =>
                  val tableUpdate1 = tableUpdate.add(resolveAttrSeqAdd(a, paramIndex))
                  resolve(tail, paramIndex + 1, tableUpdates, tableUpdate1, filterElements, notNulls1)

                case Remove       =>
                  val tableUpdate1 = tableUpdate.add(resolveAttrSeqRemove(a, paramIndex))
                  resolve(tail, paramIndex + 1, tableUpdates, tableUpdate1, filterElements, notNulls1)

                case _            => unsupportedOp(a)
              }
              case a: AttrSeqTac =>
                resolve(tail, paramIndex, tableUpdates, tableUpdate, filterElements :+ a, notNulls)
              case _: AttrSeqOpt => noOptional(a)
            }

            case a: AttrMap => a match {
              case a: AttrMapMan => a.op match {
                case Eq | NoValue =>
                  val tableUpdate1 = tableUpdate.add(resolveAttrMapMan(a, paramIndex))
                  resolve(tail, paramIndex + 1, tableUpdates, tableUpdate1, filterElements, notNulls1)

                case Add          =>
                  val tableUpdate1 = tableUpdate.add(resolveAttrMapAdd(a, paramIndex))
                  resolve(tail, paramIndex + 1, tableUpdates, tableUpdate1, filterElements, notNulls1)

                case Remove       =>
                  val tableUpdate1 = tableUpdate.add(resolveAttrMapRemove(a, paramIndex))
                  resolve(tail, paramIndex + 1, tableUpdates, tableUpdate1, filterElements, notNulls1)

                case _            => unsupportedOp(a)
              }
              case a: AttrMapTac =>
                resolve(tail, paramIndex, tableUpdates, tableUpdate, filterElements :+ a, notNulls)

              case a: AttrMapOpt => noOptional(a)
            }
          }

        case r@Ref(ent, refAttr, ref, OneToMany, _, _, reverseRefAttr) =>
          noUpsertRef
          val filterElements1 = filterElements :+ r :+ AttrOneManID(r.ref, "id")
          val refPath         = tableUpdate.refPath ++ List(refAttr, ref)
          resolve(tail, 1, tableUpdates :+ tableUpdate, TableUpdate(refPath), filterElements1, notNulls)

        case r@Ref(ent, refAttr, ref, _ /* ManyToOne */ , _, _, reverseRefAttr) =>
          noUpsertRef
          val filterElements1 = filterElements :+ r :+ AttrOneManID(r.ref, "id")
          val refPath         = tableUpdate.refPath ++ List(refAttr, ref)
          resolve(tail, 1, tableUpdates :+ tableUpdate, TableUpdate(refPath), filterElements1, notNulls)


        case _: BackRef   => throw ModelError("Backref in update not supported.")
        case _: OptEntity => throw ModelError("Optional entity in update not supported.")
        case _: OptRef    => throw ModelError("Optional ref in update not supported.")
        case _            => noNested
      }

      case Nil => (tableUpdates :+ tableUpdate, filterElements, notNulls)
    }
  }

  private def notSupported(kind: String) = throw ModelError(
    s"Update of $kind is not supported."
  )

  private def noUpsertRef = if (isUpsert) throw ModelError(
    "Upsert of related data not allowed. Please use update instead."
  )

  private def unsupportedOp(a: Attr) = throw ModelError(
    s"Unsupported update operation for value-many attribute (${a.name})." + a
  )

  private def resolveAttrOneMan(a: AttrOneMan, paramIndex: Int): (String, PS => Unit) = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case a if a.attr == "id" => throw ModelError(
        s"Generic id attribute not allowed in update molecule (${a.name}).")

      case a: AttrOneManID             => updateOne(ent, attr, paramIndex, a.op, a.vs, setterID, extsID)
      case a: AttrOneManString         => updateOne(ent, attr, paramIndex, a.op, a.vs, setterString, extsString)
      case a: AttrOneManInt            => updateOne(ent, attr, paramIndex, a.op, a.vs, setterInt, extsInt)
      case a: AttrOneManLong           => updateOne(ent, attr, paramIndex, a.op, a.vs, setterLong, extsLong)
      case a: AttrOneManFloat          => updateOne(ent, attr, paramIndex, a.op, a.vs, setterFloat, extsFloat)
      case a: AttrOneManDouble         => updateOne(ent, attr, paramIndex, a.op, a.vs, setterDouble, extsDouble)
      case a: AttrOneManBoolean        => updateOne(ent, attr, paramIndex, a.op, a.vs, setterBoolean, extsBoolean)
      case a: AttrOneManBigInt         => updateOne(ent, attr, paramIndex, a.op, a.vs, setterBigInt, extsBigInt)
      case a: AttrOneManBigDecimal     => updateOne(ent, attr, paramIndex, a.op, a.vs, setterBigDecimal, extsBigDecimal)
      case a: AttrOneManDate           => updateOne(ent, attr, paramIndex, a.op, a.vs, setterDate, extsDate)
      case a: AttrOneManDuration       => updateOne(ent, attr, paramIndex, a.op, a.vs, setterDuration, extsDuration)
      case a: AttrOneManInstant        => updateOne(ent, attr, paramIndex, a.op, a.vs, setterInstant, extsInstant)
      case a: AttrOneManLocalDate      => updateOne(ent, attr, paramIndex, a.op, a.vs, setterLocalDate, extsLocalDate)
      case a: AttrOneManLocalTime      => updateOne(ent, attr, paramIndex, a.op, a.vs, setterLocalTime, extsLocalTime)
      case a: AttrOneManLocalDateTime  => updateOne(ent, attr, paramIndex, a.op, a.vs, setterLocalDateTime, extsLocalDateTime)
      case a: AttrOneManOffsetTime     => updateOne(ent, attr, paramIndex, a.op, a.vs, setterOffsetTime, extsOffsetTime)
      case a: AttrOneManOffsetDateTime => updateOne(ent, attr, paramIndex, a.op, a.vs, setterOffsetDateTime, extsOffsetDateTime)
      case a: AttrOneManZonedDateTime  => updateOne(ent, attr, paramIndex, a.op, a.vs, setterZonedDateTime, extsZonedDateTime)
      case a: AttrOneManUUID           => updateOne(ent, attr, paramIndex, a.op, a.vs, setterUUID, extsUUID)
      case a: AttrOneManURI            => updateOne(ent, attr, paramIndex, a.op, a.vs, setterURI, extsURI)
      case a: AttrOneManByte           => updateOne(ent, attr, paramIndex, a.op, a.vs, setterByte, extsByte)
      case a: AttrOneManShort          => updateOne(ent, attr, paramIndex, a.op, a.vs, setterShort, extsShort)
      case a: AttrOneManChar           => updateOne(ent, attr, paramIndex, a.op, a.vs, setterChar, extsChar)
    }
  }

  private def resolveAttrSetMan(a: AttrSetMan, paramIndex: Int): (String, PS => Unit) = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSetManID             => updateSetEq(ent, attr, paramIndex, a.vs, transformID, extsID, set2arrayID, value2jsonID)
      case a: AttrSetManString         => updateSetEq(ent, attr, paramIndex, a.vs, transformString, extsString, set2arrayString, value2jsonString)
      case a: AttrSetManInt            => updateSetEq(ent, attr, paramIndex, a.vs, transformInt, extsInt, set2arrayInt, value2jsonInt)
      case a: AttrSetManLong           => updateSetEq(ent, attr, paramIndex, a.vs, transformLong, extsLong, set2arrayLong, value2jsonLong)
      case a: AttrSetManFloat          => updateSetEq(ent, attr, paramIndex, a.vs, transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case a: AttrSetManDouble         => updateSetEq(ent, attr, paramIndex, a.vs, transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case a: AttrSetManBoolean        => updateSetEq(ent, attr, paramIndex, a.vs, transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case a: AttrSetManBigInt         => updateSetEq(ent, attr, paramIndex, a.vs, transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case a: AttrSetManBigDecimal     => updateSetEq(ent, attr, paramIndex, a.vs, transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSetManDate           => updateSetEq(ent, attr, paramIndex, a.vs, transformDate, extsDate, set2arrayDate, value2jsonDate)
      case a: AttrSetManDuration       => updateSetEq(ent, attr, paramIndex, a.vs, transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case a: AttrSetManInstant        => updateSetEq(ent, attr, paramIndex, a.vs, transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case a: AttrSetManLocalDate      => updateSetEq(ent, attr, paramIndex, a.vs, transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSetManLocalTime      => updateSetEq(ent, attr, paramIndex, a.vs, transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetEq(ent, attr, paramIndex, a.vs, transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetEq(ent, attr, paramIndex, a.vs, transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetEq(ent, attr, paramIndex, a.vs, transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetEq(ent, attr, paramIndex, a.vs, transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetManUUID           => updateSetEq(ent, attr, paramIndex, a.vs, transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case a: AttrSetManURI            => updateSetEq(ent, attr, paramIndex, a.vs, transformURI, extsURI, set2arrayURI, value2jsonURI)
      case a: AttrSetManByte           => updateSetEq(ent, attr, paramIndex, a.vs, transformByte, extsByte, set2arrayByte, value2jsonByte)
      case a: AttrSetManShort          => updateSetEq(ent, attr, paramIndex, a.vs, transformShort, extsShort, set2arrayShort, value2jsonShort)
      case a: AttrSetManChar           => updateSetEq(ent, attr, paramIndex, a.vs, transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSetAdd(a: AttrSetMan, paramIndex: Int): (String, PS => Unit) = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSetManID             => updateSetAdd(ent, attr, paramIndex, a.vs, transformID, extsID, set2arrayID, value2jsonID)
      case a: AttrSetManString         => updateSetAdd(ent, attr, paramIndex, a.vs, transformString, extsString, set2arrayString, value2jsonString)
      case a: AttrSetManInt            => updateSetAdd(ent, attr, paramIndex, a.vs, transformInt, extsInt, set2arrayInt, value2jsonInt)
      case a: AttrSetManLong           => updateSetAdd(ent, attr, paramIndex, a.vs, transformLong, extsLong, set2arrayLong, value2jsonLong)
      case a: AttrSetManFloat          => updateSetAdd(ent, attr, paramIndex, a.vs, transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case a: AttrSetManDouble         => updateSetAdd(ent, attr, paramIndex, a.vs, transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case a: AttrSetManBoolean        => updateSetAdd(ent, attr, paramIndex, a.vs, transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case a: AttrSetManBigInt         => updateSetAdd(ent, attr, paramIndex, a.vs, transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case a: AttrSetManBigDecimal     => updateSetAdd(ent, attr, paramIndex, a.vs, transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSetManDate           => updateSetAdd(ent, attr, paramIndex, a.vs, transformDate, extsDate, set2arrayDate, value2jsonDate)
      case a: AttrSetManDuration       => updateSetAdd(ent, attr, paramIndex, a.vs, transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case a: AttrSetManInstant        => updateSetAdd(ent, attr, paramIndex, a.vs, transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case a: AttrSetManLocalDate      => updateSetAdd(ent, attr, paramIndex, a.vs, transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSetManLocalTime      => updateSetAdd(ent, attr, paramIndex, a.vs, transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetAdd(ent, attr, paramIndex, a.vs, transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetAdd(ent, attr, paramIndex, a.vs, transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetAdd(ent, attr, paramIndex, a.vs, transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetAdd(ent, attr, paramIndex, a.vs, transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetManUUID           => updateSetAdd(ent, attr, paramIndex, a.vs, transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case a: AttrSetManURI            => updateSetAdd(ent, attr, paramIndex, a.vs, transformURI, extsURI, set2arrayURI, value2jsonURI)
      case a: AttrSetManByte           => updateSetAdd(ent, attr, paramIndex, a.vs, transformByte, extsByte, set2arrayByte, value2jsonByte)
      case a: AttrSetManShort          => updateSetAdd(ent, attr, paramIndex, a.vs, transformShort, extsShort, set2arrayShort, value2jsonShort)
      case a: AttrSetManChar           => updateSetAdd(ent, attr, paramIndex, a.vs, transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSetRemove(a: AttrSetMan, paramIndex: Int): (String, PS => Unit) = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSetManID             => updateSetRemove(ent, attr, paramIndex, a.vs, transformID, extsID, one2jsonID, set2arrayID)
      case a: AttrSetManString         => updateSetRemove(ent, attr, paramIndex, a.vs, transformString, extsString, one2jsonString, set2arrayString)
      case a: AttrSetManInt            => updateSetRemove(ent, attr, paramIndex, a.vs, transformInt, extsInt, one2jsonInt, set2arrayInt)
      case a: AttrSetManLong           => updateSetRemove(ent, attr, paramIndex, a.vs, transformLong, extsLong, one2jsonLong, set2arrayLong)
      case a: AttrSetManFloat          => updateSetRemove(ent, attr, paramIndex, a.vs, transformFloat, extsFloat, one2jsonFloat, set2arrayFloat)
      case a: AttrSetManDouble         => updateSetRemove(ent, attr, paramIndex, a.vs, transformDouble, extsDouble, one2jsonDouble, set2arrayDouble)
      case a: AttrSetManBoolean        => updateSetRemove(ent, attr, paramIndex, a.vs, transformBoolean, extsBoolean, one2jsonBoolean, set2arrayBoolean)
      case a: AttrSetManBigInt         => updateSetRemove(ent, attr, paramIndex, a.vs, transformBigInt, extsBigInt, one2jsonBigInt, set2arrayBigInt)
      case a: AttrSetManBigDecimal     => updateSetRemove(ent, attr, paramIndex, a.vs, transformBigDecimal, extsBigDecimal, one2jsonBigDecimal, set2arrayBigDecimal)
      case a: AttrSetManDate           => updateSetRemove(ent, attr, paramIndex, a.vs, transformDate, extsDate, one2jsonDate, set2arrayDate)
      case a: AttrSetManDuration       => updateSetRemove(ent, attr, paramIndex, a.vs, transformDuration, extsDuration, one2jsonDuration, set2arrayDuration)
      case a: AttrSetManInstant        => updateSetRemove(ent, attr, paramIndex, a.vs, transformInstant, extsInstant, one2jsonInstant, set2arrayInstant)
      case a: AttrSetManLocalDate      => updateSetRemove(ent, attr, paramIndex, a.vs, transformLocalDate, extsLocalDate, one2jsonLocalDate, set2arrayLocalDate)
      case a: AttrSetManLocalTime      => updateSetRemove(ent, attr, paramIndex, a.vs, transformLocalTime, extsLocalTime, one2jsonLocalTime, set2arrayLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetRemove(ent, attr, paramIndex, a.vs, transformLocalDateTime, extsLocalDateTime, one2jsonLocalDateTime, set2arrayLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetRemove(ent, attr, paramIndex, a.vs, transformOffsetTime, extsOffsetTime, one2jsonOffsetTime, set2arrayOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetRemove(ent, attr, paramIndex, a.vs, transformOffsetDateTime, extsOffsetDateTime, one2jsonOffsetDateTime, set2arrayOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetRemove(ent, attr, paramIndex, a.vs, transformZonedDateTime, extsZonedDateTime, one2jsonZonedDateTime, set2arrayZonedDateTime)
      case a: AttrSetManUUID           => updateSetRemove(ent, attr, paramIndex, a.vs, transformUUID, extsUUID, one2jsonUUID, set2arrayUUID)
      case a: AttrSetManURI            => updateSetRemove(ent, attr, paramIndex, a.vs, transformURI, extsURI, one2jsonURI, set2arrayURI)
      case a: AttrSetManByte           => updateSetRemove(ent, attr, paramIndex, a.vs, transformByte, extsByte, one2jsonByte, set2arrayByte)
      case a: AttrSetManShort          => updateSetRemove(ent, attr, paramIndex, a.vs, transformShort, extsShort, one2jsonShort, set2arrayShort)
      case a: AttrSetManChar           => updateSetRemove(ent, attr, paramIndex, a.vs, transformChar, extsChar, one2jsonChar, set2arrayChar)
    }
  }

  private def resolveAttrSeqMan(a: AttrSeqMan, paramIndex: Int): (String, PS => Unit) = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSeqManID             => updateSeqEq(ent, attr, paramIndex, a.vs, transformID, extsID, seq2arrayID, value2jsonID)
      case a: AttrSeqManString         => updateSeqEq(ent, attr, paramIndex, a.vs, transformString, extsString, seq2arrayString, value2jsonString)
      case a: AttrSeqManInt            => updateSeqEq(ent, attr, paramIndex, a.vs, transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case a: AttrSeqManLong           => updateSeqEq(ent, attr, paramIndex, a.vs, transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case a: AttrSeqManFloat          => updateSeqEq(ent, attr, paramIndex, a.vs, transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case a: AttrSeqManDouble         => updateSeqEq(ent, attr, paramIndex, a.vs, transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case a: AttrSeqManBoolean        => updateSeqEq(ent, attr, paramIndex, a.vs, transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case a: AttrSeqManBigInt         => updateSeqEq(ent, attr, paramIndex, a.vs, transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case a: AttrSeqManBigDecimal     => updateSeqEq(ent, attr, paramIndex, a.vs, transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqManDate           => updateSeqEq(ent, attr, paramIndex, a.vs, transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case a: AttrSeqManDuration       => updateSeqEq(ent, attr, paramIndex, a.vs, transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case a: AttrSeqManInstant        => updateSeqEq(ent, attr, paramIndex, a.vs, transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case a: AttrSeqManLocalDate      => updateSeqEq(ent, attr, paramIndex, a.vs, transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSeqManLocalTime      => updateSeqEq(ent, attr, paramIndex, a.vs, transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSeqManLocalDateTime  => updateSeqEq(ent, attr, paramIndex, a.vs, transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqManOffsetTime     => updateSeqEq(ent, attr, paramIndex, a.vs, transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqManOffsetDateTime => updateSeqEq(ent, attr, paramIndex, a.vs, transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => updateSeqEq(ent, attr, paramIndex, a.vs, transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqManUUID           => updateSeqEq(ent, attr, paramIndex, a.vs, transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case a: AttrSeqManURI            => updateSeqEq(ent, attr, paramIndex, a.vs, transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqManShort          => updateSeqEq(ent, attr, paramIndex, a.vs, transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case a: AttrSeqManChar           => updateSeqEq(ent, attr, paramIndex, a.vs, transformChar, extsChar, seq2arrayChar, value2jsonChar)
      case a: AttrSeqManByte           => updateByteArray(ent, attr, paramIndex, a.vs)
    }
  }

  private def resolveAttrSeqAdd(a: AttrSeqMan, paramIndex: Int): (String, PS => Unit) = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSeqManID             => updateSeqAdd(ent, attr, paramIndex, a.vs, transformID, extsID, seq2arrayID, value2jsonID)
      case a: AttrSeqManString         => updateSeqAdd(ent, attr, paramIndex, a.vs, transformString, extsString, seq2arrayString, value2jsonString)
      case a: AttrSeqManInt            => updateSeqAdd(ent, attr, paramIndex, a.vs, transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case a: AttrSeqManLong           => updateSeqAdd(ent, attr, paramIndex, a.vs, transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case a: AttrSeqManFloat          => updateSeqAdd(ent, attr, paramIndex, a.vs, transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case a: AttrSeqManDouble         => updateSeqAdd(ent, attr, paramIndex, a.vs, transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case a: AttrSeqManBoolean        => updateSeqAdd(ent, attr, paramIndex, a.vs, transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case a: AttrSeqManBigInt         => updateSeqAdd(ent, attr, paramIndex, a.vs, transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case a: AttrSeqManBigDecimal     => updateSeqAdd(ent, attr, paramIndex, a.vs, transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqManDate           => updateSeqAdd(ent, attr, paramIndex, a.vs, transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case a: AttrSeqManDuration       => updateSeqAdd(ent, attr, paramIndex, a.vs, transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case a: AttrSeqManInstant        => updateSeqAdd(ent, attr, paramIndex, a.vs, transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case a: AttrSeqManLocalDate      => updateSeqAdd(ent, attr, paramIndex, a.vs, transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSeqManLocalTime      => updateSeqAdd(ent, attr, paramIndex, a.vs, transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSeqManLocalDateTime  => updateSeqAdd(ent, attr, paramIndex, a.vs, transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqManOffsetTime     => updateSeqAdd(ent, attr, paramIndex, a.vs, transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqManOffsetDateTime => updateSeqAdd(ent, attr, paramIndex, a.vs, transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => updateSeqAdd(ent, attr, paramIndex, a.vs, transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqManUUID           => updateSeqAdd(ent, attr, paramIndex, a.vs, transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case a: AttrSeqManURI            => updateSeqAdd(ent, attr, paramIndex, a.vs, transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqManShort          => updateSeqAdd(ent, attr, paramIndex, a.vs, transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case a: AttrSeqManChar           => updateSeqAdd(ent, attr, paramIndex, a.vs, transformChar, extsChar, seq2arrayChar, value2jsonChar)
      case a: AttrSeqManByte           => throw ModelError(s"Operations on byte arrays (${a.ent}.${a.attr}) not allowed.")
    }
  }

  private def resolveAttrSeqRemove(a: AttrSeqMan, paramIndex: Int): (String, PS => Unit) = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSeqManID             => updateSeqRemove(ent, attr, paramIndex, a.vs, transformID, extsID, one2jsonID, seq2arrayID)
      case a: AttrSeqManString         => updateSeqRemove(ent, attr, paramIndex, a.vs, transformString, extsString, one2jsonString, seq2arrayString)
      case a: AttrSeqManInt            => updateSeqRemove(ent, attr, paramIndex, a.vs, transformInt, extsInt, one2jsonInt, seq2arrayInt)
      case a: AttrSeqManLong           => updateSeqRemove(ent, attr, paramIndex, a.vs, transformLong, extsLong, one2jsonLong, seq2arrayLong)
      case a: AttrSeqManFloat          => updateSeqRemove(ent, attr, paramIndex, a.vs, transformFloat, extsFloat, one2jsonFloat, seq2arrayFloat)
      case a: AttrSeqManDouble         => updateSeqRemove(ent, attr, paramIndex, a.vs, transformDouble, extsDouble, one2jsonDouble, seq2arrayDouble)
      case a: AttrSeqManBoolean        => updateSeqRemove(ent, attr, paramIndex, a.vs, transformBoolean, extsBoolean, one2jsonBoolean, seq2arrayBoolean)
      case a: AttrSeqManBigInt         => updateSeqRemove(ent, attr, paramIndex, a.vs, transformBigInt, extsBigInt, one2jsonBigInt, seq2arrayBigInt)
      case a: AttrSeqManBigDecimal     => updateSeqRemove(ent, attr, paramIndex, a.vs, transformBigDecimal, extsBigDecimal, one2jsonBigDecimal, seq2arrayBigDecimal)
      case a: AttrSeqManDate           => updateSeqRemove(ent, attr, paramIndex, a.vs, transformDate, extsDate, one2jsonDate, seq2arrayDate)
      case a: AttrSeqManDuration       => updateSeqRemove(ent, attr, paramIndex, a.vs, transformDuration, extsDuration, one2jsonDuration, seq2arrayDuration)
      case a: AttrSeqManInstant        => updateSeqRemove(ent, attr, paramIndex, a.vs, transformInstant, extsInstant, one2jsonInstant, seq2arrayInstant)
      case a: AttrSeqManLocalDate      => updateSeqRemove(ent, attr, paramIndex, a.vs, transformLocalDate, extsLocalDate, one2jsonLocalDate, seq2arrayLocalDate)
      case a: AttrSeqManLocalTime      => updateSeqRemove(ent, attr, paramIndex, a.vs, transformLocalTime, extsLocalTime, one2jsonLocalTime, seq2arrayLocalTime)
      case a: AttrSeqManLocalDateTime  => updateSeqRemove(ent, attr, paramIndex, a.vs, transformLocalDateTime, extsLocalDateTime, one2jsonLocalDateTime, seq2arrayLocalDateTime)
      case a: AttrSeqManOffsetTime     => updateSeqRemove(ent, attr, paramIndex, a.vs, transformOffsetTime, extsOffsetTime, one2jsonOffsetTime, seq2arrayOffsetTime)
      case a: AttrSeqManOffsetDateTime => updateSeqRemove(ent, attr, paramIndex, a.vs, transformOffsetDateTime, extsOffsetDateTime, one2jsonOffsetDateTime, seq2arrayOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => updateSeqRemove(ent, attr, paramIndex, a.vs, transformZonedDateTime, extsZonedDateTime, one2jsonZonedDateTime, seq2arrayZonedDateTime)
      case a: AttrSeqManUUID           => updateSeqRemove(ent, attr, paramIndex, a.vs, transformUUID, extsUUID, one2jsonUUID, seq2arrayUUID)
      case a: AttrSeqManURI            => updateSeqRemove(ent, attr, paramIndex, a.vs, transformURI, extsURI, one2jsonURI, seq2arrayURI)
      case a: AttrSeqManShort          => updateSeqRemove(ent, attr, paramIndex, a.vs, transformShort, extsShort, one2jsonShort, seq2arrayShort)
      case a: AttrSeqManChar           => updateSeqRemove(ent, attr, paramIndex, a.vs, transformChar, extsChar, one2jsonChar, seq2arrayChar)
      case a: AttrSeqManByte           => throw ModelError(s"Operations on byte arrays (${a.ent}.${a.attr}) not allowed.")
    }
  }


  private def resolveAttrMapMan(a: AttrMapMan, paramIndex: Int): (String, PS => Unit) = {
    val (ent, attr, ref, noValue) = (a.ent, a.attr, a.ref, a.op == NoValue)
    a match {
      case a: AttrMapManID             => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformID, value2jsonID)
      case a: AttrMapManString         => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformString, value2jsonString)
      case a: AttrMapManInt            => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformInt, value2jsonInt)
      case a: AttrMapManLong           => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformLong, value2jsonLong)
      case a: AttrMapManFloat          => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformFloat, value2jsonFloat)
      case a: AttrMapManDouble         => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformDouble, value2jsonDouble)
      case a: AttrMapManBoolean        => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformBoolean, value2jsonBoolean)
      case a: AttrMapManBigInt         => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformBigInt, value2jsonBigInt)
      case a: AttrMapManBigDecimal     => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformBigDecimal, value2jsonBigDecimal)
      case a: AttrMapManDate           => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformDate, value2jsonDate)
      case a: AttrMapManDuration       => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformDuration, value2jsonDuration)
      case a: AttrMapManInstant        => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformInstant, value2jsonInstant)
      case a: AttrMapManLocalDate      => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformLocalDate, value2jsonLocalDate)
      case a: AttrMapManLocalTime      => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformLocalTime, value2jsonLocalTime)
      case a: AttrMapManLocalDateTime  => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformLocalDateTime, value2jsonLocalDateTime)
      case a: AttrMapManOffsetTime     => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformOffsetTime, value2jsonOffsetTime)
      case a: AttrMapManOffsetDateTime => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrMapManZonedDateTime  => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformZonedDateTime, value2jsonZonedDateTime)
      case a: AttrMapManUUID           => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformUUID, value2jsonUUID)
      case a: AttrMapManURI            => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformURI, value2jsonURI)
      case a: AttrMapManByte           => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformByte, value2jsonByte)
      case a: AttrMapManShort          => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformShort, value2jsonShort)
      case a: AttrMapManChar           => updateMapEq(ent, attr, paramIndex, noValue, a.map, transformChar, value2jsonChar)
    }
  }

  private def resolveAttrMapAdd(a: AttrMapMan, paramIndex: Int): (String, PS => Unit) = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrMapManID             => updateMapAdd(ent, attr, paramIndex, a.map, transformID, extsID, value2jsonID)
      case a: AttrMapManString         => updateMapAdd(ent, attr, paramIndex, a.map, transformString, extsString, value2jsonString)
      case a: AttrMapManInt            => updateMapAdd(ent, attr, paramIndex, a.map, transformInt, extsInt, value2jsonInt)
      case a: AttrMapManLong           => updateMapAdd(ent, attr, paramIndex, a.map, transformLong, extsLong, value2jsonLong)
      case a: AttrMapManFloat          => updateMapAdd(ent, attr, paramIndex, a.map, transformFloat, extsFloat, value2jsonFloat)
      case a: AttrMapManDouble         => updateMapAdd(ent, attr, paramIndex, a.map, transformDouble, extsDouble, value2jsonDouble)
      case a: AttrMapManBoolean        => updateMapAdd(ent, attr, paramIndex, a.map, transformBoolean, extsBoolean, value2jsonBoolean)
      case a: AttrMapManBigInt         => updateMapAdd(ent, attr, paramIndex, a.map, transformBigInt, extsBigInt, value2jsonBigInt)
      case a: AttrMapManBigDecimal     => updateMapAdd(ent, attr, paramIndex, a.map, transformBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrMapManDate           => updateMapAdd(ent, attr, paramIndex, a.map, transformDate, extsDate, value2jsonDate)
      case a: AttrMapManDuration       => updateMapAdd(ent, attr, paramIndex, a.map, transformDuration, extsDuration, value2jsonDuration)
      case a: AttrMapManInstant        => updateMapAdd(ent, attr, paramIndex, a.map, transformInstant, extsInstant, value2jsonInstant)
      case a: AttrMapManLocalDate      => updateMapAdd(ent, attr, paramIndex, a.map, transformLocalDate, extsLocalDate, value2jsonLocalDate)
      case a: AttrMapManLocalTime      => updateMapAdd(ent, attr, paramIndex, a.map, transformLocalTime, extsLocalTime, value2jsonLocalTime)
      case a: AttrMapManLocalDateTime  => updateMapAdd(ent, attr, paramIndex, a.map, transformLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrMapManOffsetTime     => updateMapAdd(ent, attr, paramIndex, a.map, transformOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrMapManOffsetDateTime => updateMapAdd(ent, attr, paramIndex, a.map, transformOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrMapManZonedDateTime  => updateMapAdd(ent, attr, paramIndex, a.map, transformZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrMapManUUID           => updateMapAdd(ent, attr, paramIndex, a.map, transformUUID, extsUUID, value2jsonUUID)
      case a: AttrMapManURI            => updateMapAdd(ent, attr, paramIndex, a.map, transformURI, extsURI, value2jsonURI)
      case a: AttrMapManByte           => updateMapAdd(ent, attr, paramIndex, a.map, transformByte, extsByte, value2jsonByte)
      case a: AttrMapManShort          => updateMapAdd(ent, attr, paramIndex, a.map, transformShort, extsShort, value2jsonShort)
      case a: AttrMapManChar           => updateMapAdd(ent, attr, paramIndex, a.map, transformChar, extsChar, value2jsonChar)
    }
  }

  private def resolveAttrMapRemove(a: AttrMapMan, paramIndex: Int): (String, PS => Unit) = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrMapManID             => updateMapRemove(ent, attr, paramIndex, a.keys, extsID)
      case a: AttrMapManString         => updateMapRemove(ent, attr, paramIndex, a.keys, extsString)
      case a: AttrMapManInt            => updateMapRemove(ent, attr, paramIndex, a.keys, extsInt)
      case a: AttrMapManLong           => updateMapRemove(ent, attr, paramIndex, a.keys, extsLong)
      case a: AttrMapManFloat          => updateMapRemove(ent, attr, paramIndex, a.keys, extsFloat)
      case a: AttrMapManDouble         => updateMapRemove(ent, attr, paramIndex, a.keys, extsDouble)
      case a: AttrMapManBoolean        => updateMapRemove(ent, attr, paramIndex, a.keys, extsBoolean)
      case a: AttrMapManBigInt         => updateMapRemove(ent, attr, paramIndex, a.keys, extsBigInt)
      case a: AttrMapManBigDecimal     => updateMapRemove(ent, attr, paramIndex, a.keys, extsBigDecimal)
      case a: AttrMapManDate           => updateMapRemove(ent, attr, paramIndex, a.keys, extsDate)
      case a: AttrMapManDuration       => updateMapRemove(ent, attr, paramIndex, a.keys, extsDuration)
      case a: AttrMapManInstant        => updateMapRemove(ent, attr, paramIndex, a.keys, extsInstant)
      case a: AttrMapManLocalDate      => updateMapRemove(ent, attr, paramIndex, a.keys, extsLocalDate)
      case a: AttrMapManLocalTime      => updateMapRemove(ent, attr, paramIndex, a.keys, extsLocalTime)
      case a: AttrMapManLocalDateTime  => updateMapRemove(ent, attr, paramIndex, a.keys, extsLocalDateTime)
      case a: AttrMapManOffsetTime     => updateMapRemove(ent, attr, paramIndex, a.keys, extsOffsetTime)
      case a: AttrMapManOffsetDateTime => updateMapRemove(ent, attr, paramIndex, a.keys, extsOffsetDateTime)
      case a: AttrMapManZonedDateTime  => updateMapRemove(ent, attr, paramIndex, a.keys, extsZonedDateTime)
      case a: AttrMapManUUID           => updateMapRemove(ent, attr, paramIndex, a.keys, extsUUID)
      case a: AttrMapManURI            => updateMapRemove(ent, attr, paramIndex, a.keys, extsURI)
      case a: AttrMapManByte           => updateMapRemove(ent, attr, paramIndex, a.keys, extsByte)
      case a: AttrMapManShort          => updateMapRemove(ent, attr, paramIndex, a.keys, extsShort)
      case a: AttrMapManChar           => updateMapRemove(ent, attr, paramIndex, a.keys, extsChar)
    }
  }
}