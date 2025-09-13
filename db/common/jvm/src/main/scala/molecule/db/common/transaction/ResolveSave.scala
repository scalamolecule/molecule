package molecule.db.common.transaction

import java.sql.PreparedStatement as PS
import scala.annotation.tailrec
import molecule.core.dataModel.*
import molecule.core.error.{ExecutionError, ModelError}

trait ResolveSave { self: SqlSave =>

  @tailrec
  final def resolve(
    elements: List[Element],
    paramIndex: Int,
    tableInserts: List[TableInsert],
    tableInsert: TableInsert,
  ): List[TableInsert] = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          if (a.op != Eq) {
            throw ModelError(s"Missing applied value for attribute ${a.ent}.${a.attr}")
          }
          a match {
            case a: AttrOne => a match {
              case a: AttrOneMan =>
                val tableInsert1 = tableInsert.add(a, resolveAttrOneMan(a, paramIndex), cast)
                resolve(tail, paramIndex + 1, tableInserts, tableInsert1)

              case a: AttrOneOpt =>
                val tableInsert1 = tableInsert.add(a, resolveAttrOneOpt(a, paramIndex), cast)
                resolve(tail, paramIndex + 1, tableInserts, tableInsert1)

              case a: AttrOneTac =>
                val tableInsert1 = tableInsert.add(a, resolveAttrOneTac(a, paramIndex), cast)
                resolve(tail, paramIndex + 1, tableInserts, tableInsert1)
            }

            case a: AttrSet => a match {
              case a: AttrSetMan =>
                val tableInsert1 = tableInsert.add(a, resolveAttrSetMan(a, paramIndex))
                resolve(tail, paramIndex + 1, tableInserts, tableInsert1)

              case a: AttrSetOpt =>
                val tableInsert1 = tableInsert.add(a, resolveAttrSetOpt(a, paramIndex))
                resolve(tail, paramIndex + 1, tableInserts, tableInsert1)

              case a: AttrSetTac =>
                val tableInsert1 = tableInsert.add(a, resolveAttrSetTac(a, paramIndex))
                resolve(tail, paramIndex + 1, tableInserts, tableInsert1)
            }

            case a: AttrSeq => a match {
              case a: AttrSeqMan =>
                val tableInsert1 = tableInsert.add(a, resolveAttrSeqMan(a, paramIndex))
                resolve(tail, paramIndex + 1, tableInserts, tableInsert1)

              case a: AttrSeqOpt =>
                val tableInsert1 = tableInsert.add(a, resolveAttrSeqOpt(a, paramIndex))
                resolve(tail, paramIndex + 1, tableInserts, tableInsert1)

              case a: AttrSeqTac =>
                val tableInsert1 = tableInsert.add(a, resolveAttrSeqTac(a, paramIndex))
                resolve(tail, paramIndex + 1, tableInserts, tableInsert1)
            }

            case a: AttrMap => a match {
              case a: AttrMapMan =>
                val tableInsert1 = tableInsert.add(a, resolveAttrMapMan(a, paramIndex), cast)
                resolve(tail, paramIndex + 1, tableInserts, tableInsert1)

              case a: AttrMapOpt =>
                val tableInsert1 = tableInsert.add(a, resolveAttrMapOpt(a, paramIndex), cast)
                resolve(tail, paramIndex + 1, tableInserts, tableInsert1)

              case a: AttrMapTac =>
                val tableInsert1 = tableInsert.add(a, resolveAttrMapTac(a, paramIndex))
                resolve(tail, paramIndex + 1, tableInserts, tableInsert1)
            }
          }

        case Ref(ent, refAttr, ref, OneToMany, _, _, reverseRefAttr) =>
          resolveOneToManyRef(tableInserts, tableInsert, refAttr, ref, reverseRefAttr, tail)

        case Ref(ent, refAttr, ref, _ /* ManyToOne */ , _, _, reverseRefAttr) =>
          resolveManyToOneRef(tableInserts, tableInsert, refAttr, ref, reverseRefAttr, tail)

        case BackRef(backRef, _, _) =>
          resolveBackRef(tableInserts, tableInsert, backRef, paramIndex, tail)

        case _: OptRef => throw ModelError(
          "Optional ref not allowed in save molecule. Please use mandatory ref instead."
        )

        case _: OptEntity => throw ModelError(
          "Optional entity not allowed in save molecule. Please use mandatory entity instead."
        )

        case _: Nested    => throw ModelError(
          "Nested data structure not allowed in save molecule. Please use insert instead."
        )
        case _: OptNested => throw ModelError(
          "Optional nested data structure not allowed in save molecule. Please use insert instead."
        )
      }

      case Nil => tableInserts :+ tableInsert
    }
  }

  def resolveOneToManyRef(
    tableInserts: List[TableInsert],
    tableInsert: TableInsert,
    refAttr: String,
    ref: String,
    reverseRefAttr: Option[String],
    tail: List[Element]
  ): List[TableInsert] = {
    val refInsert = TableInsert(
      tableInsert.refPath ++ List(refAttr, ref),
      Nil,
      List(reverseRefAttr.get -> tableInsert.refPath),
      Nil,
      List("?"),
      Nil
    )
    resolve(tail, 1, tableInserts :+ tableInsert, refInsert)
  }

  def resolveManyToOneRef(
    tableInserts: List[TableInsert],
    tableInsert: TableInsert,
    refAttr: String,
    ref: String,
    reverseRefAttr: Option[String],
    tail: List[Element]
  ): List[TableInsert] = {
    //    println("######################")
    //    println(tableInserts)
    //
    //    println("######################")
    //    println(tableInsert)
    //    println("######################")

    val refPath1  = tableInsert.refPath ++ List(refAttr, ref)
    val refInsert = tableInsert.copy(
      foreignKeys = tableInsert.foreignKeys :+ (refAttr -> refPath1),
      refPlaceHolders = tableInsert.refPlaceHolders :+ "?",
    )
    resolve(tail, 1, tableInserts :+ refInsert, TableInsert(refPath1))
  }

  def resolveBackRef(
    tableInserts: List[TableInsert],
    tableInsert: TableInsert,
    backRef: String,
    paramIndex: Int,
    tail: List[Element],
  ): List[TableInsert] = {
    // Add current tableInsert and continue with previous tableInsert
    val prevRefPath         = tableInsert.refPath.dropRight(2)
    val prevTableInsert     = tableInserts.find(_.refPath == prevRefPath).get
    val curLevelWithoutPrev = tableInserts.filterNot(_.refPath == prevRefPath) :+ tableInsert
    //    println(s"  BackRef: $backRef  $tplIndex  $prevRefPath ")

    // Reset paramIndex to the next column slot for the previous table.
    // This ensures that subsequent attribute setters target the correct parameter indices
    // and do not collide with foreign key placeholders.
    val nextParamIndexForPrev = prevTableInsert.columns.length + 1

    resolve(tail, nextParamIndexForPrev, curLevelWithoutPrev, prevTableInsert)
  }

  private def oneV[T](
    ent: String,
    attr: String,
    vs: Seq[T],
  ): Option[T] = {
    vs match {
      case Seq(v) => Some(v)
      case Nil    => None
      case vs     => throw ExecutionError(
        s"Can only save one value for attribute `$ent.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }
  private def resolveAttrOneMan(a: AttrOneMan, paramIndex: Int): (PS, Product) => Unit = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case a: AttrOneManID             => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterID, extsID)
      case a: AttrOneManString         => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterString, extsString)
      case a: AttrOneManInt            => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterInt, extsInt)
      case a: AttrOneManLong           => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterLong, extsLong)
      case a: AttrOneManFloat          => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterFloat, extsFloat)
      case a: AttrOneManDouble         => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterDouble, extsDouble)
      case a: AttrOneManBoolean        => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterBoolean, extsBoolean)
      case a: AttrOneManBigInt         => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterBigInt, extsBigInt)
      case a: AttrOneManBigDecimal     => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterBigDecimal, extsBigDecimal)
      case a: AttrOneManDate           => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterDate, extsDate)
      case a: AttrOneManDuration       => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterDuration, extsDuration)
      case a: AttrOneManInstant        => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterInstant, extsInstant)
      case a: AttrOneManLocalDate      => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterLocalDate, extsLocalDate)
      case a: AttrOneManLocalTime      => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterLocalTime, extsLocalTime)
      case a: AttrOneManLocalDateTime  => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterLocalDateTime, extsLocalDateTime)
      case a: AttrOneManOffsetTime     => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterOffsetTime, extsOffsetTime)
      case a: AttrOneManOffsetDateTime => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterOffsetDateTime, extsOffsetDateTime)
      case a: AttrOneManZonedDateTime  => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterZonedDateTime, extsZonedDateTime)
      case a: AttrOneManUUID           => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterUUID, extsUUID)
      case a: AttrOneManURI            => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterURI, extsURI)
      case a: AttrOneManByte           => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterByte, extsByte)
      case a: AttrOneManShort          => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterShort, extsShort)
      case a: AttrOneManChar           => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterChar, extsChar)
    }
  }
  private def resolveAttrOneTac(a: AttrOneTac, paramIndex: Int): (PS, Product) => Unit = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case a: AttrOneTacID             => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterID, extsID)
      case a: AttrOneTacString         => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterString, extsString)
      case a: AttrOneTacInt            => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterInt, extsInt)
      case a: AttrOneTacLong           => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterLong, extsLong)
      case a: AttrOneTacFloat          => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterFloat, extsFloat)
      case a: AttrOneTacDouble         => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterDouble, extsDouble)
      case a: AttrOneTacBoolean        => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterBoolean, extsBoolean)
      case a: AttrOneTacBigInt         => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterBigInt, extsBigInt)
      case a: AttrOneTacBigDecimal     => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterBigDecimal, extsBigDecimal)
      case a: AttrOneTacDate           => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterDate, extsDate)
      case a: AttrOneTacDuration       => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterDuration, extsDuration)
      case a: AttrOneTacInstant        => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterInstant, extsInstant)
      case a: AttrOneTacLocalDate      => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterLocalDate, extsLocalDate)
      case a: AttrOneTacLocalTime      => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterLocalTime, extsLocalTime)
      case a: AttrOneTacLocalDateTime  => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterLocalDateTime, extsLocalDateTime)
      case a: AttrOneTacOffsetTime     => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterOffsetTime, extsOffsetTime)
      case a: AttrOneTacOffsetDateTime => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterOffsetDateTime, extsOffsetDateTime)
      case a: AttrOneTacZonedDateTime  => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterZonedDateTime, extsZonedDateTime)
      case a: AttrOneTacUUID           => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterUUID, extsUUID)
      case a: AttrOneTacURI            => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterURI, extsURI)
      case a: AttrOneTacByte           => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterByte, extsByte)
      case a: AttrOneTacShort          => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterShort, extsShort)
      case a: AttrOneTacChar           => addOne(ent, attr, paramIndex, oneV(ent, attr, a.vs), setterChar, extsChar)
    }
  }


  private def oneOptV[T](
    ent: String,
    attr: String,
    optVs: Option[Seq[T]],
  ): Option[T] = {
    optVs.flatMap {
      case Seq(v) => Some(v)
      case Nil    => None
      case vs     => throw ExecutionError(
        s"Can only save one value for optional attribute `$ent.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }
  private def resolveAttrOneOpt(a: AttrOneOpt, paramIndex: Int): (PS, Product) => Unit = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case a: AttrOneOptID             => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterID, extsID)
      case a: AttrOneOptString         => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterString, extsString)
      case a: AttrOneOptInt            => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterInt, extsInt)
      case a: AttrOneOptLong           => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterLong, extsLong)
      case a: AttrOneOptFloat          => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterFloat, extsFloat)
      case a: AttrOneOptDouble         => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterDouble, extsDouble)
      case a: AttrOneOptBoolean        => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterBoolean, extsBoolean)
      case a: AttrOneOptBigInt         => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterBigInt, extsBigInt)
      case a: AttrOneOptBigDecimal     => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterBigDecimal, extsBigDecimal)
      case a: AttrOneOptDate           => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterDate, extsDate)
      case a: AttrOneOptDuration       => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterDuration, extsDuration)
      case a: AttrOneOptInstant        => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterInstant, extsInstant)
      case a: AttrOneOptLocalDate      => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterLocalDate, extsLocalDate)
      case a: AttrOneOptLocalTime      => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterLocalTime, extsLocalTime)
      case a: AttrOneOptLocalDateTime  => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterLocalDateTime, extsLocalDateTime)
      case a: AttrOneOptOffsetTime     => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterOffsetTime, extsOffsetTime)
      case a: AttrOneOptOffsetDateTime => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterOffsetDateTime, extsOffsetDateTime)
      case a: AttrOneOptZonedDateTime  => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterZonedDateTime, extsZonedDateTime)
      case a: AttrOneOptUUID           => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterUUID, extsUUID)
      case a: AttrOneOptURI            => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterURI, extsURI)
      case a: AttrOneOptByte           => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterByte, extsByte)
      case a: AttrOneOptShort          => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterShort, extsShort)
      case a: AttrOneOptChar           => addOne(ent, attr, paramIndex, oneOptV(ent, attr, a.vs), setterChar, extsChar)
    }
  }


  private def optSet[T](set: Set[T]): Option[Set[T]] = if (set.nonEmpty) Some(set) else None

  private def resolveAttrSetMan(a: AttrSetMan, paramIndex: Int): (PS, Product) => Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSetManID             => addSet(ent, attr, paramIndex, optSet(a.vs), setterID, extsID, set2arrayID, value2jsonID)
      case a: AttrSetManString         => addSet(ent, attr, paramIndex, optSet(a.vs), setterString, extsString, set2arrayString, value2jsonString)
      case a: AttrSetManInt            => addSet(ent, attr, paramIndex, optSet(a.vs), setterInt, extsInt, set2arrayInt, value2jsonInt)
      case a: AttrSetManLong           => addSet(ent, attr, paramIndex, optSet(a.vs), setterLong, extsLong, set2arrayLong, value2jsonLong)
      case a: AttrSetManFloat          => addSet(ent, attr, paramIndex, optSet(a.vs), setterFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case a: AttrSetManDouble         => addSet(ent, attr, paramIndex, optSet(a.vs), setterDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case a: AttrSetManBoolean        => addSet(ent, attr, paramIndex, optSet(a.vs), setterBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case a: AttrSetManBigInt         => addSet(ent, attr, paramIndex, optSet(a.vs), setterBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case a: AttrSetManBigDecimal     => addSet(ent, attr, paramIndex, optSet(a.vs), setterBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSetManDate           => addSet(ent, attr, paramIndex, optSet(a.vs), setterDate, extsDate, set2arrayDate, value2jsonDate)
      case a: AttrSetManDuration       => addSet(ent, attr, paramIndex, optSet(a.vs), setterDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case a: AttrSetManInstant        => addSet(ent, attr, paramIndex, optSet(a.vs), setterInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case a: AttrSetManLocalDate      => addSet(ent, attr, paramIndex, optSet(a.vs), setterLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSetManLocalTime      => addSet(ent, attr, paramIndex, optSet(a.vs), setterLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => addSet(ent, attr, paramIndex, optSet(a.vs), setterLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => addSet(ent, attr, paramIndex, optSet(a.vs), setterOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => addSet(ent, attr, paramIndex, optSet(a.vs), setterOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => addSet(ent, attr, paramIndex, optSet(a.vs), setterZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetManUUID           => addSet(ent, attr, paramIndex, optSet(a.vs), setterUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case a: AttrSetManURI            => addSet(ent, attr, paramIndex, optSet(a.vs), setterURI, extsURI, set2arrayURI, value2jsonURI)
      case a: AttrSetManByte           => addSet(ent, attr, paramIndex, optSet(a.vs), setterByte, extsByte, set2arrayByte, value2jsonByte)
      case a: AttrSetManShort          => addSet(ent, attr, paramIndex, optSet(a.vs), setterShort, extsShort, set2arrayShort, value2jsonShort)
      case a: AttrSetManChar           => addSet(ent, attr, paramIndex, optSet(a.vs), setterChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSetTac(a: AttrSetTac, paramIndex: Int): (PS, Product) => Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSetTacID             => addSet(ent, attr, paramIndex, optSet(a.vs), setterID, extsID, set2arrayID, value2jsonID)
      case a: AttrSetTacString         => addSet(ent, attr, paramIndex, optSet(a.vs), setterString, extsString, set2arrayString, value2jsonString)
      case a: AttrSetTacInt            => addSet(ent, attr, paramIndex, optSet(a.vs), setterInt, extsInt, set2arrayInt, value2jsonInt)
      case a: AttrSetTacLong           => addSet(ent, attr, paramIndex, optSet(a.vs), setterLong, extsLong, set2arrayLong, value2jsonLong)
      case a: AttrSetTacFloat          => addSet(ent, attr, paramIndex, optSet(a.vs), setterFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case a: AttrSetTacDouble         => addSet(ent, attr, paramIndex, optSet(a.vs), setterDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case a: AttrSetTacBoolean        => addSet(ent, attr, paramIndex, optSet(a.vs), setterBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case a: AttrSetTacBigInt         => addSet(ent, attr, paramIndex, optSet(a.vs), setterBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case a: AttrSetTacBigDecimal     => addSet(ent, attr, paramIndex, optSet(a.vs), setterBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSetTacDate           => addSet(ent, attr, paramIndex, optSet(a.vs), setterDate, extsDate, set2arrayDate, value2jsonDate)
      case a: AttrSetTacDuration       => addSet(ent, attr, paramIndex, optSet(a.vs), setterDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case a: AttrSetTacInstant        => addSet(ent, attr, paramIndex, optSet(a.vs), setterInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case a: AttrSetTacLocalDate      => addSet(ent, attr, paramIndex, optSet(a.vs), setterLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSetTacLocalTime      => addSet(ent, attr, paramIndex, optSet(a.vs), setterLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSetTacLocalDateTime  => addSet(ent, attr, paramIndex, optSet(a.vs), setterLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetTacOffsetTime     => addSet(ent, attr, paramIndex, optSet(a.vs), setterOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSetTacOffsetDateTime => addSet(ent, attr, paramIndex, optSet(a.vs), setterOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetTacZonedDateTime  => addSet(ent, attr, paramIndex, optSet(a.vs), setterZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetTacUUID           => addSet(ent, attr, paramIndex, optSet(a.vs), setterUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case a: AttrSetTacURI            => addSet(ent, attr, paramIndex, optSet(a.vs), setterURI, extsURI, set2arrayURI, value2jsonURI)
      case a: AttrSetTacByte           => addSet(ent, attr, paramIndex, optSet(a.vs), setterByte, extsByte, set2arrayByte, value2jsonByte)
      case a: AttrSetTacShort          => addSet(ent, attr, paramIndex, optSet(a.vs), setterShort, extsShort, set2arrayShort, value2jsonShort)
      case a: AttrSetTacChar           => addSet(ent, attr, paramIndex, optSet(a.vs), setterChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSetOpt(a: AttrSetOpt, paramIndex: Int): (PS, Product) => Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSetOptID             => addSet(ent, attr, paramIndex, a.vs, setterID, extsID, set2arrayID, value2jsonID)
      case a: AttrSetOptString         => addSet(ent, attr, paramIndex, a.vs, setterString, extsString, set2arrayString, value2jsonString)
      case a: AttrSetOptInt            => addSet(ent, attr, paramIndex, a.vs, setterInt, extsInt, set2arrayInt, value2jsonInt)
      case a: AttrSetOptLong           => addSet(ent, attr, paramIndex, a.vs, setterLong, extsLong, set2arrayLong, value2jsonLong)
      case a: AttrSetOptFloat          => addSet(ent, attr, paramIndex, a.vs, setterFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case a: AttrSetOptDouble         => addSet(ent, attr, paramIndex, a.vs, setterDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case a: AttrSetOptBoolean        => addSet(ent, attr, paramIndex, a.vs, setterBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case a: AttrSetOptBigInt         => addSet(ent, attr, paramIndex, a.vs, setterBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case a: AttrSetOptBigDecimal     => addSet(ent, attr, paramIndex, a.vs, setterBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSetOptDate           => addSet(ent, attr, paramIndex, a.vs, setterDate, extsDate, set2arrayDate, value2jsonDate)
      case a: AttrSetOptDuration       => addSet(ent, attr, paramIndex, a.vs, setterDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case a: AttrSetOptInstant        => addSet(ent, attr, paramIndex, a.vs, setterInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case a: AttrSetOptLocalDate      => addSet(ent, attr, paramIndex, a.vs, setterLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSetOptLocalTime      => addSet(ent, attr, paramIndex, a.vs, setterLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSetOptLocalDateTime  => addSet(ent, attr, paramIndex, a.vs, setterLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetOptOffsetTime     => addSet(ent, attr, paramIndex, a.vs, setterOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSetOptOffsetDateTime => addSet(ent, attr, paramIndex, a.vs, setterOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetOptZonedDateTime  => addSet(ent, attr, paramIndex, a.vs, setterZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetOptUUID           => addSet(ent, attr, paramIndex, a.vs, setterUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case a: AttrSetOptURI            => addSet(ent, attr, paramIndex, a.vs, setterURI, extsURI, set2arrayURI, value2jsonURI)
      case a: AttrSetOptByte           => addSet(ent, attr, paramIndex, a.vs, setterByte, extsByte, set2arrayByte, value2jsonByte)
      case a: AttrSetOptShort          => addSet(ent, attr, paramIndex, a.vs, setterShort, extsShort, set2arrayShort, value2jsonShort)
      case a: AttrSetOptChar           => addSet(ent, attr, paramIndex, a.vs, setterChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def optSeq[T](seq: Seq[T]): Option[Seq[T]] = if (seq.nonEmpty) Some(seq) else None

  private def resolveAttrSeqMan(a: AttrSeqMan, paramIndex: Int): (PS, Product) => Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSeqManID             => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterID, extsID, seq2arrayID, value2jsonID)
      case a: AttrSeqManString         => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterString, extsString, seq2arrayString, value2jsonString)
      case a: AttrSeqManInt            => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterInt, extsInt, seq2arrayInt, value2jsonInt)
      case a: AttrSeqManLong           => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterLong, extsLong, seq2arrayLong, value2jsonLong)
      case a: AttrSeqManFloat          => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case a: AttrSeqManDouble         => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case a: AttrSeqManBoolean        => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case a: AttrSeqManBigInt         => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case a: AttrSeqManBigDecimal     => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqManDate           => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterDate, extsDate, seq2arrayDate, value2jsonDate)
      case a: AttrSeqManDuration       => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case a: AttrSeqManInstant        => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case a: AttrSeqManLocalDate      => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSeqManLocalTime      => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSeqManLocalDateTime  => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqManOffsetTime     => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqManOffsetDateTime => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqManUUID           => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case a: AttrSeqManURI            => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqManByte           => addByteArray(ent, attr, paramIndex, optArray(a.vs))
      case a: AttrSeqManShort          => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterShort, extsShort, seq2arrayShort, value2jsonShort)
      case a: AttrSeqManChar           => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterChar, extsChar, seq2arrayChar, value2jsonChar)
    }
  }
  private def resolveAttrSeqTac(a: AttrSeqTac, paramIndex: Int): (PS, Product) => Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSeqTacID             => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterID, extsID, seq2arrayID, value2jsonID)
      case a: AttrSeqTacString         => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterString, extsString, seq2arrayString, value2jsonString)
      case a: AttrSeqTacInt            => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterInt, extsInt, seq2arrayInt, value2jsonInt)
      case a: AttrSeqTacLong           => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterLong, extsLong, seq2arrayLong, value2jsonLong)
      case a: AttrSeqTacFloat          => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case a: AttrSeqTacDouble         => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case a: AttrSeqTacBoolean        => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case a: AttrSeqTacBigInt         => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case a: AttrSeqTacBigDecimal     => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqTacDate           => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterDate, extsDate, seq2arrayDate, value2jsonDate)
      case a: AttrSeqTacDuration       => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case a: AttrSeqTacInstant        => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case a: AttrSeqTacLocalDate      => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSeqTacLocalTime      => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSeqTacLocalDateTime  => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqTacOffsetTime     => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqTacOffsetDateTime => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqTacZonedDateTime  => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqTacUUID           => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case a: AttrSeqTacURI            => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqTacByte           => addByteArray(ent, attr, paramIndex, optArray(a.vs))
      case a: AttrSeqTacShort          => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterShort, extsShort, seq2arrayShort, value2jsonShort)
      case a: AttrSeqTacChar           => addSeq(ent, attr, paramIndex, optSeq(a.vs), setterChar, extsChar, seq2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSeqOpt(a: AttrSeqOpt, paramIndex: Int): (PS, Product) => Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSeqOptID             => addSeq(ent, attr, paramIndex, a.vs, setterID, extsID, seq2arrayID, value2jsonID)
      case a: AttrSeqOptString         => addSeq(ent, attr, paramIndex, a.vs, setterString, extsString, seq2arrayString, value2jsonString)
      case a: AttrSeqOptInt            => addSeq(ent, attr, paramIndex, a.vs, setterInt, extsInt, seq2arrayInt, value2jsonInt)
      case a: AttrSeqOptLong           => addSeq(ent, attr, paramIndex, a.vs, setterLong, extsLong, seq2arrayLong, value2jsonLong)
      case a: AttrSeqOptFloat          => addSeq(ent, attr, paramIndex, a.vs, setterFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case a: AttrSeqOptDouble         => addSeq(ent, attr, paramIndex, a.vs, setterDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case a: AttrSeqOptBoolean        => addSeq(ent, attr, paramIndex, a.vs, setterBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case a: AttrSeqOptBigInt         => addSeq(ent, attr, paramIndex, a.vs, setterBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case a: AttrSeqOptBigDecimal     => addSeq(ent, attr, paramIndex, a.vs, setterBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqOptDate           => addSeq(ent, attr, paramIndex, a.vs, setterDate, extsDate, seq2arrayDate, value2jsonDate)
      case a: AttrSeqOptDuration       => addSeq(ent, attr, paramIndex, a.vs, setterDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case a: AttrSeqOptInstant        => addSeq(ent, attr, paramIndex, a.vs, setterInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case a: AttrSeqOptLocalDate      => addSeq(ent, attr, paramIndex, a.vs, setterLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSeqOptLocalTime      => addSeq(ent, attr, paramIndex, a.vs, setterLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSeqOptLocalDateTime  => addSeq(ent, attr, paramIndex, a.vs, setterLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqOptOffsetTime     => addSeq(ent, attr, paramIndex, a.vs, setterOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqOptOffsetDateTime => addSeq(ent, attr, paramIndex, a.vs, setterOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqOptZonedDateTime  => addSeq(ent, attr, paramIndex, a.vs, setterZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqOptUUID           => addSeq(ent, attr, paramIndex, a.vs, setterUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case a: AttrSeqOptURI            => addSeq(ent, attr, paramIndex, a.vs, setterURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqOptByte           => addByteArray(ent, attr, paramIndex, a.vs)
      case a: AttrSeqOptShort          => addSeq(ent, attr, paramIndex, a.vs, setterShort, extsShort, seq2arrayShort, value2jsonShort)
      case a: AttrSeqOptChar           => addSeq(ent, attr, paramIndex, a.vs, setterChar, extsChar, seq2arrayChar, value2jsonChar)
    }
  }


  private def oneMap[T](map: Map[String, T]): Option[Map[String, T]] = {
    if (map.nonEmpty) Some(map) else None
  }
  private def resolveAttrMapMan(a: AttrMapMan, paramIndex: Int): (PS, Product) => Unit = a match {
    case a: AttrMapManID             => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterID, value2jsonID)
    case a: AttrMapManString         => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterString, value2jsonString)
    case a: AttrMapManInt            => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterInt, value2jsonInt)
    case a: AttrMapManLong           => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterLong, value2jsonLong)
    case a: AttrMapManFloat          => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterFloat, value2jsonFloat)
    case a: AttrMapManDouble         => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterDouble, value2jsonDouble)
    case a: AttrMapManBoolean        => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterBoolean, value2jsonBoolean)
    case a: AttrMapManBigInt         => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterBigInt, value2jsonBigInt)
    case a: AttrMapManBigDecimal     => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterBigDecimal, value2jsonBigDecimal)
    case a: AttrMapManDate           => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterDate, value2jsonDate)
    case a: AttrMapManDuration       => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterDuration, value2jsonDuration)
    case a: AttrMapManInstant        => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterInstant, value2jsonInstant)
    case a: AttrMapManLocalDate      => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterLocalDate, value2jsonLocalDate)
    case a: AttrMapManLocalTime      => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterLocalTime, value2jsonLocalTime)
    case a: AttrMapManLocalDateTime  => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterLocalDateTime, value2jsonLocalDateTime)
    case a: AttrMapManOffsetTime     => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterOffsetTime, value2jsonOffsetTime)
    case a: AttrMapManOffsetDateTime => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterOffsetDateTime, value2jsonOffsetDateTime)
    case a: AttrMapManZonedDateTime  => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterZonedDateTime, value2jsonZonedDateTime)
    case a: AttrMapManUUID           => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterUUID, value2jsonUUID)
    case a: AttrMapManURI            => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterURI, value2jsonURI)
    case a: AttrMapManByte           => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterByte, value2jsonByte)
    case a: AttrMapManShort          => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterShort, value2jsonShort)
    case a: AttrMapManChar           => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterChar, value2jsonChar)
  }
  private def resolveAttrMapTac(a: AttrMapTac, paramIndex: Int): (PS, Product) => Unit = a match {
    case a: AttrMapTacID             => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterID, value2jsonID)
    case a: AttrMapTacString         => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterString, value2jsonString)
    case a: AttrMapTacInt            => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterInt, value2jsonInt)
    case a: AttrMapTacLong           => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterLong, value2jsonLong)
    case a: AttrMapTacFloat          => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterFloat, value2jsonFloat)
    case a: AttrMapTacDouble         => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterDouble, value2jsonDouble)
    case a: AttrMapTacBoolean        => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterBoolean, value2jsonBoolean)
    case a: AttrMapTacBigInt         => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterBigInt, value2jsonBigInt)
    case a: AttrMapTacBigDecimal     => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterBigDecimal, value2jsonBigDecimal)
    case a: AttrMapTacDate           => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterDate, value2jsonDate)
    case a: AttrMapTacDuration       => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterDuration, value2jsonDuration)
    case a: AttrMapTacInstant        => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterInstant, value2jsonInstant)
    case a: AttrMapTacLocalDate      => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterLocalDate, value2jsonLocalDate)
    case a: AttrMapTacLocalTime      => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterLocalTime, value2jsonLocalTime)
    case a: AttrMapTacLocalDateTime  => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterLocalDateTime, value2jsonLocalDateTime)
    case a: AttrMapTacOffsetTime     => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterOffsetTime, value2jsonOffsetTime)
    case a: AttrMapTacOffsetDateTime => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterOffsetDateTime, value2jsonOffsetDateTime)
    case a: AttrMapTacZonedDateTime  => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterZonedDateTime, value2jsonZonedDateTime)
    case a: AttrMapTacUUID           => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterUUID, value2jsonUUID)
    case a: AttrMapTacURI            => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterURI, value2jsonURI)
    case a: AttrMapTacByte           => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterByte, value2jsonByte)
    case a: AttrMapTacShort          => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterShort, value2jsonShort)
    case a: AttrMapTacChar           => addMap(a.ent, a.attr, paramIndex, oneMap(a.map), setterChar, value2jsonChar)
  }

  private def resolveAttrMapOpt(a: AttrMapOpt, paramIndex: Int): (PS, Product) => Unit = {
    a match {
      case a: AttrMapOptID             => addMap(a.ent, a.attr, paramIndex, a.map, setterID, value2jsonID)
      case a: AttrMapOptString         => addMap(a.ent, a.attr, paramIndex, a.map, setterString, value2jsonString)
      case a: AttrMapOptInt            => addMap(a.ent, a.attr, paramIndex, a.map, setterInt, value2jsonInt)
      case a: AttrMapOptLong           => addMap(a.ent, a.attr, paramIndex, a.map, setterLong, value2jsonLong)
      case a: AttrMapOptFloat          => addMap(a.ent, a.attr, paramIndex, a.map, setterFloat, value2jsonFloat)
      case a: AttrMapOptDouble         => addMap(a.ent, a.attr, paramIndex, a.map, setterDouble, value2jsonDouble)
      case a: AttrMapOptBoolean        => addMap(a.ent, a.attr, paramIndex, a.map, setterBoolean, value2jsonBoolean)
      case a: AttrMapOptBigInt         => addMap(a.ent, a.attr, paramIndex, a.map, setterBigInt, value2jsonBigInt)
      case a: AttrMapOptBigDecimal     => addMap(a.ent, a.attr, paramIndex, a.map, setterBigDecimal, value2jsonBigDecimal)
      case a: AttrMapOptDate           => addMap(a.ent, a.attr, paramIndex, a.map, setterDate, value2jsonDate)
      case a: AttrMapOptDuration       => addMap(a.ent, a.attr, paramIndex, a.map, setterDuration, value2jsonDuration)
      case a: AttrMapOptInstant        => addMap(a.ent, a.attr, paramIndex, a.map, setterInstant, value2jsonInstant)
      case a: AttrMapOptLocalDate      => addMap(a.ent, a.attr, paramIndex, a.map, setterLocalDate, value2jsonLocalDate)
      case a: AttrMapOptLocalTime      => addMap(a.ent, a.attr, paramIndex, a.map, setterLocalTime, value2jsonLocalTime)
      case a: AttrMapOptLocalDateTime  => addMap(a.ent, a.attr, paramIndex, a.map, setterLocalDateTime, value2jsonLocalDateTime)
      case a: AttrMapOptOffsetTime     => addMap(a.ent, a.attr, paramIndex, a.map, setterOffsetTime, value2jsonOffsetTime)
      case a: AttrMapOptOffsetDateTime => addMap(a.ent, a.attr, paramIndex, a.map, setterOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrMapOptZonedDateTime  => addMap(a.ent, a.attr, paramIndex, a.map, setterZonedDateTime, value2jsonZonedDateTime)
      case a: AttrMapOptUUID           => addMap(a.ent, a.attr, paramIndex, a.map, setterUUID, value2jsonUUID)
      case a: AttrMapOptURI            => addMap(a.ent, a.attr, paramIndex, a.map, setterURI, value2jsonURI)
      case a: AttrMapOptByte           => addMap(a.ent, a.attr, paramIndex, a.map, setterByte, value2jsonByte)
      case a: AttrMapOptShort          => addMap(a.ent, a.attr, paramIndex, a.map, setterShort, value2jsonShort)
      case a: AttrMapOptChar           => addMap(a.ent, a.attr, paramIndex, a.map, setterChar, value2jsonChar)
    }
  }
}