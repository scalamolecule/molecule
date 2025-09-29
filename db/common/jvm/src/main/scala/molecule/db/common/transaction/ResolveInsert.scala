package molecule.db.common.transaction

import java.sql.PreparedStatement as PS
import scala.annotation.tailrec
import molecule.core.dataModel.*
import molecule.core.error.ModelError
import molecule.db.common.validation.insert.InsertValidators_


trait ResolveInsert extends InsertValidators_ { self: SqlInsert =>

  @tailrec
  final def resolve(
    elements: List[Element],
    paramIndex: Int,
    tplIndex: Int,
    partitions: List[Partition],
    tableInsert: TableInsert,
  ): List[Partition] = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          if (a.op != V) {
            throw ModelError(s"Can't insert attributes with an applied value (${a.name}).")
          }
          a match {
            case a: AttrOneMan =>
              val tableInsert1 = tableInsert.add(a, attrOneManSetter(tableInsert, a, paramIndex, tplIndex), cast)
              resolve(tail, paramIndex + 1, tplIndex + 1, partitions, tableInsert1)

            case a: AttrOneOpt =>
              val tableInsert1 = tableInsert.add(a, attrOneOptSetter(tableInsert, a, paramIndex, tplIndex), cast)
              resolve(tail, paramIndex + 1, tplIndex + 1, partitions, tableInsert1)

            case a: AttrSetMan =>
              val tableInsert1 = tableInsert.add(a, attrSetManSetter(a, paramIndex, tplIndex))
              resolve(tail, paramIndex + 1, tplIndex + 1, partitions, tableInsert1)

            case a: AttrSetOpt =>
              val tableInsert1 = tableInsert.add(a, attrSetOptSetter(a, paramIndex, tplIndex))
              resolve(tail, paramIndex + 1, tplIndex + 1, partitions, tableInsert1)

            case a: AttrSeqMan =>
              val tableInsert1 = tableInsert.add(a, attrSeqManSetter(a, paramIndex, tplIndex))
              resolve(tail, paramIndex + 1, tplIndex + 1, partitions, tableInsert1)

            case a: AttrSeqOpt =>
              val tableInsert1 = tableInsert.add(a, attrSeqOptSetter(a, paramIndex, tplIndex))
              resolve(tail, paramIndex + 1, tplIndex + 1, partitions, tableInsert1)

            case a: AttrMapMan =>
              val tableInsert1 = tableInsert.add(a, attrMapManSetter(a, paramIndex, tplIndex), cast)
              resolve(tail, paramIndex + 1, tplIndex + 1, partitions, tableInsert1)

            case a: AttrMapOpt =>
              val tableInsert1 = tableInsert.add(a, attrMapOptSetter(a, paramIndex, tplIndex), cast)
              resolve(tail, paramIndex + 1, tplIndex + 1, partitions, tableInsert1)

            case a: AttrOneTacID =>
              resolve(tail, paramIndex, tplIndex, partitions, tableInsert)

            case a => noTacit(a)
          }

        case Ref(ent, refAttr, ref, OneToMany, _, reverseRefAttr, _) =>
          resolveOneToManyRef(partitions, tableInsert, refAttr, ref, reverseRefAttr, tplIndex, tail)

        case Ref(ent, refAttr, ref, _ /* ManyToOne */ , _, reverseRefAttr, _) =>
          resolveManyToOneRef(partitions, tableInsert, refAttr, ref, reverseRefAttr, tplIndex, tail)


        case BackRef(backRef, _, _) =>
          resolveBackRef(partitions, tableInsert, backRef, paramIndex, tplIndex, tail)

        case _: OptRef    => noOptional("ref")
        case _: OptEntity => noOptional("entity")

        case Nested(Ref(_, refAttr, ref, _, _, reverseRefAttr, _), nestedElements) =>
          resolveNested(partitions, nestedElements, refAttr, ref, reverseRefAttr, tableInsert, tplIndex)

        case OptNested(Ref(_, refAttr, ref, _, _, reverseRefAttr, _), nestedElements) =>
          resolveNested(partitions, nestedElements, refAttr, ref, reverseRefAttr, tableInsert, tplIndex)
      }

      case Nil =>
        val lastPartition = partitions.last.copy(
          tableInserts = partitions.last.tableInserts :+ tableInsert,
        )
        partitions.init :+ lastPartition
    }
  }

  def resolveOneToManyRef(
    partitions: List[Partition],
    tableInsert: TableInsert,
    refAttr: String,
    ref: String,
    reverseRefAttr: Option[String],
    tplIndex: Int,
    tail: List[Element]
  ): List[Partition] = {
    val lastPartition = partitions.last.copy(tableInserts = partitions.last.tableInserts :+ tableInsert)
    val refInsert     = TableInsert(
      tableInsert.refPath ++ List(refAttr, ref),
      Nil,
      List(reverseRefAttr.get -> tableInsert.refPath),
      Nil,
      List("?"),
      Nil
    )
    resolve(tail, 1, tplIndex, partitions.init :+ lastPartition, refInsert)
  }

  def resolveManyToOneRef(
    partitions: List[Partition],
    tableInsert: TableInsert,
    refAttr: String,
    ref: String,
    reverseRefAttr: Option[String],
    tplIndex: Int,
    tail: List[Element]
  ): List[Partition] = {
    val refPath1      = tableInsert.refPath ++ List(refAttr, ref)
    val refInsert     = tableInsert.copy(
      foreignKeys = tableInsert.foreignKeys :+ (refAttr -> refPath1),
      refPlaceHolders = tableInsert.refPlaceHolders :+ "?",
    )
    val lastPartition = partitions.last.copy(tableInserts = partitions.last.tableInserts :+ refInsert)
    resolve(tail, 1, tplIndex, partitions.init :+ lastPartition, TableInsert(refPath1))
  }

  def resolveBackRef(
    partitions: List[Partition],
    tableInsert: TableInsert,
    backRef: String,
    paramIndex: Int,
    tplIndex: Int,
    tail: List[Element],
  ): List[Partition] = {
    // Add current tableInsert and continue with previous tableInsert
    val prevRefPath         = tableInsert.refPath.dropRight(2)
    val tableInserts        = partitions.last.tableInserts
    val prevTableInsert     = tableInserts.find(_.refPath == prevRefPath).get
    val curLevelWithoutPrev = tableInserts.filterNot(_.refPath == prevRefPath) :+ tableInsert
    val lastPartition       = partitions.last.copy(tableInserts = curLevelWithoutPrev)

    // Reset paramIndex to the next column slot for the previous table.
    // This ensures that subsequent attribute setters target the correct parameter indices
    // and do not collide with foreign key placeholders.
    val nextParamIndexForPrev = prevTableInsert.columns.length + 1
    resolve(tail, nextParamIndexForPrev, tplIndex, partitions.init :+ lastPartition, prevTableInsert)
  }

  def resolveNested(
    partitions: List[Partition],
    nestedElements: List[Element],
    refAttr: String,
    ref: String,
    reverseRefAttr: Option[String],
    tableInsert: TableInsert,
    tplIndex: Int
  ) = {
    // Close current (parent) table insert into the last partition
    // and record where the nested container lives in the parent tuple
    val lastPartition = partitions.last.copy(
      tableInserts = partitions.last.tableInserts :+ tableInsert,
      tupleIndex = tplIndex,
      dataKind = if (countValueAttrs(nestedElements) == 1) HasNestedValues else HasNestedTuples
    )

    // Start a new nested partition, with tupleIndex pointing at the nested container in the parent tuple
    val nextDataKind  = nestedElements.last match {
      case Nested(_, nestedElements) if nestedElements.length == 1 => HasNestedValues
      case Nested(_, nestedElements)                               => HasNestedTuples
      case _                                                       => Tpl
    }
    val curPartitions = partitions.init :+ lastPartition :+ Partition(Nil, 0, nextDataKind)
    val nestedInsert  = TableInsert(
      tableInsert.refPath ++ List(refAttr, ref),
      Nil,
      List(reverseRefAttr.get -> tableInsert.refPath),
      Nil,
      List("?"),
      Nil
    )
    resolve(nestedElements, 1, 0, curPartitions, nestedInsert)
  }

  private def noTacit(a: Attr) = throw ModelError(
    s"Can't use tacit attributes in insert molecule (${a.name})."
  )
  private def noOptional(kind: String) = throw ModelError(
    s"Insertion of optional $kind is not supported."
  )
  private def noNestedRef(tableInsert: TableInsert, ent: String, attr: String) = {
    if (tableInsert.foreignKeys.nonEmpty && tableInsert.foreignKeys.last._1 == attr) {
      throw ModelError(
        s"Foreign key attribute $ent.$attr not allowed to be set in nested tuple."
      )
    }
  }

  private def attrOneManSetter(
    tableInsert: TableInsert, a: AttrOneMan, paramIndex: Int, tplIndex: Int
  ): (PS, Product) => Unit = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case _: AttrOneManID             =>
        noNestedRef(tableInsert, a.ent, a.attr)
        addOne(ent, attr, paramIndex, tplIndex, setterID, extsID)
      case _: AttrOneManString         => addOne(ent, attr, paramIndex, tplIndex, setterString, extsString)
      case _: AttrOneManInt            => addOne(ent, attr, paramIndex, tplIndex, setterInt, extsInt)
      case _: AttrOneManLong           => addOne(ent, attr, paramIndex, tplIndex, setterLong, extsLong)
      case _: AttrOneManFloat          => addOne(ent, attr, paramIndex, tplIndex, setterFloat, extsFloat)
      case _: AttrOneManDouble         => addOne(ent, attr, paramIndex, tplIndex, setterDouble, extsDouble)
      case _: AttrOneManBoolean        => addOne(ent, attr, paramIndex, tplIndex, setterBoolean, extsBoolean)
      case _: AttrOneManBigInt         => addOne(ent, attr, paramIndex, tplIndex, setterBigInt, extsBigInt)
      case _: AttrOneManBigDecimal     => addOne(ent, attr, paramIndex, tplIndex, setterBigDecimal, extsBigDecimal)
      case _: AttrOneManDate           => addOne(ent, attr, paramIndex, tplIndex, setterDate, extsDate)
      case _: AttrOneManDuration       => addOne(ent, attr, paramIndex, tplIndex, setterDuration, extsDuration)
      case _: AttrOneManInstant        => addOne(ent, attr, paramIndex, tplIndex, setterInstant, extsInstant)
      case _: AttrOneManLocalDate      => addOne(ent, attr, paramIndex, tplIndex, setterLocalDate, extsLocalDate)
      case _: AttrOneManLocalTime      => addOne(ent, attr, paramIndex, tplIndex, setterLocalTime, extsLocalTime)
      case _: AttrOneManLocalDateTime  => addOne(ent, attr, paramIndex, tplIndex, setterLocalDateTime, extsLocalDateTime)
      case _: AttrOneManOffsetTime     => addOne(ent, attr, paramIndex, tplIndex, setterOffsetTime, extsOffsetTime)
      case _: AttrOneManOffsetDateTime => addOne(ent, attr, paramIndex, tplIndex, setterOffsetDateTime, extsOffsetDateTime)
      case _: AttrOneManZonedDateTime  => addOne(ent, attr, paramIndex, tplIndex, setterZonedDateTime, extsZonedDateTime)
      case _: AttrOneManUUID           => addOne(ent, attr, paramIndex, tplIndex, setterUUID, extsUUID)
      case _: AttrOneManURI            => addOne(ent, attr, paramIndex, tplIndex, setterURI, extsURI)
      case _: AttrOneManByte           => addOne(ent, attr, paramIndex, tplIndex, setterByte, extsByte)
      case _: AttrOneManShort          => addOne(ent, attr, paramIndex, tplIndex, setterShort, extsShort)
      case _: AttrOneManChar           => addOne(ent, attr, paramIndex, tplIndex, setterChar, extsChar)
    }
  }

  private def attrOneOptSetter(
    tableInsert: TableInsert, a: AttrOneOpt, paramIndex: Int, tplIndex: Int
  ): (PS, Product) => Unit = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case _: AttrOneOptID             =>
        noNestedRef(tableInsert, a.ent, a.attr)
        addOneOpt(ent, attr, paramIndex, tplIndex, setterID, extsID)
      case _: AttrOneOptString         => addOneOpt(ent, attr, paramIndex, tplIndex, setterString, extsString)
      case _: AttrOneOptInt            => addOneOpt(ent, attr, paramIndex, tplIndex, setterInt, extsInt)
      case _: AttrOneOptLong           => addOneOpt(ent, attr, paramIndex, tplIndex, setterLong, extsLong)
      case _: AttrOneOptFloat          => addOneOpt(ent, attr, paramIndex, tplIndex, setterFloat, extsFloat)
      case _: AttrOneOptDouble         => addOneOpt(ent, attr, paramIndex, tplIndex, setterDouble, extsDouble)
      case _: AttrOneOptBoolean        => addOneOpt(ent, attr, paramIndex, tplIndex, setterBoolean, extsBoolean)
      case _: AttrOneOptBigInt         => addOneOpt(ent, attr, paramIndex, tplIndex, setterBigInt, extsBigInt)
      case _: AttrOneOptBigDecimal     => addOneOpt(ent, attr, paramIndex, tplIndex, setterBigDecimal, extsBigDecimal)
      case _: AttrOneOptDate           => addOneOpt(ent, attr, paramIndex, tplIndex, setterDate, extsDate)
      case _: AttrOneOptDuration       => addOneOpt(ent, attr, paramIndex, tplIndex, setterDuration, extsDuration)
      case _: AttrOneOptInstant        => addOneOpt(ent, attr, paramIndex, tplIndex, setterInstant, extsInstant)
      case _: AttrOneOptLocalDate      => addOneOpt(ent, attr, paramIndex, tplIndex, setterLocalDate, extsLocalDate)
      case _: AttrOneOptLocalTime      => addOneOpt(ent, attr, paramIndex, tplIndex, setterLocalTime, extsLocalTime)
      case _: AttrOneOptLocalDateTime  => addOneOpt(ent, attr, paramIndex, tplIndex, setterLocalDateTime, extsLocalDateTime)
      case _: AttrOneOptOffsetTime     => addOneOpt(ent, attr, paramIndex, tplIndex, setterOffsetTime, extsOffsetTime)
      case _: AttrOneOptOffsetDateTime => addOneOpt(ent, attr, paramIndex, tplIndex, setterOffsetDateTime, extsOffsetDateTime)
      case _: AttrOneOptZonedDateTime  => addOneOpt(ent, attr, paramIndex, tplIndex, setterZonedDateTime, extsZonedDateTime)
      case _: AttrOneOptUUID           => addOneOpt(ent, attr, paramIndex, tplIndex, setterUUID, extsUUID)
      case _: AttrOneOptURI            => addOneOpt(ent, attr, paramIndex, tplIndex, setterURI, extsURI)
      case _: AttrOneOptByte           => addOneOpt(ent, attr, paramIndex, tplIndex, setterByte, extsByte)
      case _: AttrOneOptShort          => addOneOpt(ent, attr, paramIndex, tplIndex, setterShort, extsShort)
      case _: AttrOneOptChar           => addOneOpt(ent, attr, paramIndex, tplIndex, setterChar, extsChar)
    }
  }

  private def attrSetManSetter(a: AttrSetMan, paramIndex: Int, tplIndex: Int): (PS, Product) => Unit = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case _: AttrSetManID             => addSet(ent, attr, paramIndex, tplIndex, transformID, extsID, set2arrayID, value2jsonID)
      case _: AttrSetManString         => addSet(ent, attr, paramIndex, tplIndex, transformString, extsString, set2arrayString, value2jsonString)
      case _: AttrSetManInt            => addSet(ent, attr, paramIndex, tplIndex, transformInt, extsInt, set2arrayInt, value2jsonInt)
      case _: AttrSetManLong           => addSet(ent, attr, paramIndex, tplIndex, transformLong, extsLong, set2arrayLong, value2jsonLong)
      case _: AttrSetManFloat          => addSet(ent, attr, paramIndex, tplIndex, transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case _: AttrSetManDouble         => addSet(ent, attr, paramIndex, tplIndex, transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case _: AttrSetManBoolean        => addSet(ent, attr, paramIndex, tplIndex, transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case _: AttrSetManBigInt         => addSet(ent, attr, paramIndex, tplIndex, transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case _: AttrSetManBigDecimal     => addSet(ent, attr, paramIndex, tplIndex, transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case _: AttrSetManDate           => addSet(ent, attr, paramIndex, tplIndex, transformDate, extsDate, set2arrayDate, value2jsonDate)
      case _: AttrSetManDuration       => addSet(ent, attr, paramIndex, tplIndex, transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case _: AttrSetManInstant        => addSet(ent, attr, paramIndex, tplIndex, transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case _: AttrSetManLocalDate      => addSet(ent, attr, paramIndex, tplIndex, transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case _: AttrSetManLocalTime      => addSet(ent, attr, paramIndex, tplIndex, transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case _: AttrSetManLocalDateTime  => addSet(ent, attr, paramIndex, tplIndex, transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case _: AttrSetManOffsetTime     => addSet(ent, attr, paramIndex, tplIndex, transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case _: AttrSetManOffsetDateTime => addSet(ent, attr, paramIndex, tplIndex, transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrSetManZonedDateTime  => addSet(ent, attr, paramIndex, tplIndex, transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case _: AttrSetManUUID           => addSet(ent, attr, paramIndex, tplIndex, transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case _: AttrSetManURI            => addSet(ent, attr, paramIndex, tplIndex, transformURI, extsURI, set2arrayURI, value2jsonURI)
      case _: AttrSetManByte           => addSet(ent, attr, paramIndex, tplIndex, transformByte, extsByte, set2arrayByte, value2jsonByte)
      case _: AttrSetManShort          => addSet(ent, attr, paramIndex, tplIndex, transformShort, extsShort, set2arrayShort, value2jsonShort)
      case _: AttrSetManChar           => addSet(ent, attr, paramIndex, tplIndex, transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def attrSetOptSetter(a: AttrSetOpt, paramIndex: Int, tplIndex: Int): (PS, Product) => Unit = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case _: AttrSetOptID             => addSetOpt(ent, attr, paramIndex, tplIndex, transformID, extsID, set2arrayID, value2jsonID)
      case _: AttrSetOptString         => addSetOpt(ent, attr, paramIndex, tplIndex, transformString, extsString, set2arrayString, value2jsonString)
      case _: AttrSetOptInt            => addSetOpt(ent, attr, paramIndex, tplIndex, transformInt, extsInt, set2arrayInt, value2jsonInt)
      case _: AttrSetOptLong           => addSetOpt(ent, attr, paramIndex, tplIndex, transformLong, extsLong, set2arrayLong, value2jsonLong)
      case _: AttrSetOptFloat          => addSetOpt(ent, attr, paramIndex, tplIndex, transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case _: AttrSetOptDouble         => addSetOpt(ent, attr, paramIndex, tplIndex, transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case _: AttrSetOptBoolean        => addSetOpt(ent, attr, paramIndex, tplIndex, transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case _: AttrSetOptBigInt         => addSetOpt(ent, attr, paramIndex, tplIndex, transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case _: AttrSetOptBigDecimal     => addSetOpt(ent, attr, paramIndex, tplIndex, transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case _: AttrSetOptDate           => addSetOpt(ent, attr, paramIndex, tplIndex, transformDate, extsDate, set2arrayDate, value2jsonDate)
      case _: AttrSetOptDuration       => addSetOpt(ent, attr, paramIndex, tplIndex, transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case _: AttrSetOptInstant        => addSetOpt(ent, attr, paramIndex, tplIndex, transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case _: AttrSetOptLocalDate      => addSetOpt(ent, attr, paramIndex, tplIndex, transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case _: AttrSetOptLocalTime      => addSetOpt(ent, attr, paramIndex, tplIndex, transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case _: AttrSetOptLocalDateTime  => addSetOpt(ent, attr, paramIndex, tplIndex, transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case _: AttrSetOptOffsetTime     => addSetOpt(ent, attr, paramIndex, tplIndex, transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case _: AttrSetOptOffsetDateTime => addSetOpt(ent, attr, paramIndex, tplIndex, transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrSetOptZonedDateTime  => addSetOpt(ent, attr, paramIndex, tplIndex, transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case _: AttrSetOptUUID           => addSetOpt(ent, attr, paramIndex, tplIndex, transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case _: AttrSetOptURI            => addSetOpt(ent, attr, paramIndex, tplIndex, transformURI, extsURI, set2arrayURI, value2jsonURI)
      case _: AttrSetOptByte           => addSetOpt(ent, attr, paramIndex, tplIndex, transformByte, extsByte, set2arrayByte, value2jsonByte)
      case _: AttrSetOptShort          => addSetOpt(ent, attr, paramIndex, tplIndex, transformShort, extsShort, set2arrayShort, value2jsonShort)
      case _: AttrSetOptChar           => addSetOpt(ent, attr, paramIndex, tplIndex, transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def attrSeqManSetter(a: AttrSeqMan, paramIndex: Int, tplIndex: Int): (PS, Product) => Unit = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case _: AttrSeqManID             => addSeq(ent, attr, paramIndex, tplIndex, transformID, extsID, seq2arrayID, value2jsonID)
      case _: AttrSeqManString         => addSeq(ent, attr, paramIndex, tplIndex, transformString, extsString, seq2arrayString, value2jsonString)
      case _: AttrSeqManInt            => addSeq(ent, attr, paramIndex, tplIndex, transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case _: AttrSeqManLong           => addSeq(ent, attr, paramIndex, tplIndex, transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case _: AttrSeqManFloat          => addSeq(ent, attr, paramIndex, tplIndex, transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case _: AttrSeqManDouble         => addSeq(ent, attr, paramIndex, tplIndex, transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case _: AttrSeqManBoolean        => addSeq(ent, attr, paramIndex, tplIndex, transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case _: AttrSeqManBigInt         => addSeq(ent, attr, paramIndex, tplIndex, transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case _: AttrSeqManBigDecimal     => addSeq(ent, attr, paramIndex, tplIndex, transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case _: AttrSeqManDate           => addSeq(ent, attr, paramIndex, tplIndex, transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case _: AttrSeqManDuration       => addSeq(ent, attr, paramIndex, tplIndex, transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case _: AttrSeqManInstant        => addSeq(ent, attr, paramIndex, tplIndex, transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case _: AttrSeqManLocalDate      => addSeq(ent, attr, paramIndex, tplIndex, transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case _: AttrSeqManLocalTime      => addSeq(ent, attr, paramIndex, tplIndex, transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case _: AttrSeqManLocalDateTime  => addSeq(ent, attr, paramIndex, tplIndex, transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case _: AttrSeqManOffsetTime     => addSeq(ent, attr, paramIndex, tplIndex, transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case _: AttrSeqManOffsetDateTime => addSeq(ent, attr, paramIndex, tplIndex, transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrSeqManZonedDateTime  => addSeq(ent, attr, paramIndex, tplIndex, transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case _: AttrSeqManUUID           => addSeq(ent, attr, paramIndex, tplIndex, transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case _: AttrSeqManURI            => addSeq(ent, attr, paramIndex, tplIndex, transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case _: AttrSeqManByte           => addByteArray(ent, attr, paramIndex, tplIndex)
      case _: AttrSeqManShort          => addSeq(ent, attr, paramIndex, tplIndex, transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case _: AttrSeqManChar           => addSeq(ent, attr, paramIndex, tplIndex, transformChar, extsChar, seq2arrayChar, value2jsonChar)
    }
  }

  private def attrSeqOptSetter(a: AttrSeqOpt, paramIndex: Int, tplIndex: Int): (PS, Product) => Unit = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case _: AttrSeqOptID             => addSeqOpt(ent, attr, paramIndex, tplIndex, transformID, extsID, seq2arrayID, value2jsonID)
      case _: AttrSeqOptString         => addSeqOpt(ent, attr, paramIndex, tplIndex, transformString, extsString, seq2arrayString, value2jsonString)
      case _: AttrSeqOptInt            => addSeqOpt(ent, attr, paramIndex, tplIndex, transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case _: AttrSeqOptLong           => addSeqOpt(ent, attr, paramIndex, tplIndex, transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case _: AttrSeqOptFloat          => addSeqOpt(ent, attr, paramIndex, tplIndex, transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case _: AttrSeqOptDouble         => addSeqOpt(ent, attr, paramIndex, tplIndex, transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case _: AttrSeqOptBoolean        => addSeqOpt(ent, attr, paramIndex, tplIndex, transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case _: AttrSeqOptBigInt         => addSeqOpt(ent, attr, paramIndex, tplIndex, transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case _: AttrSeqOptBigDecimal     => addSeqOpt(ent, attr, paramIndex, tplIndex, transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case _: AttrSeqOptDate           => addSeqOpt(ent, attr, paramIndex, tplIndex, transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case _: AttrSeqOptDuration       => addSeqOpt(ent, attr, paramIndex, tplIndex, transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case _: AttrSeqOptInstant        => addSeqOpt(ent, attr, paramIndex, tplIndex, transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case _: AttrSeqOptLocalDate      => addSeqOpt(ent, attr, paramIndex, tplIndex, transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case _: AttrSeqOptLocalTime      => addSeqOpt(ent, attr, paramIndex, tplIndex, transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case _: AttrSeqOptLocalDateTime  => addSeqOpt(ent, attr, paramIndex, tplIndex, transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case _: AttrSeqOptOffsetTime     => addSeqOpt(ent, attr, paramIndex, tplIndex, transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case _: AttrSeqOptOffsetDateTime => addSeqOpt(ent, attr, paramIndex, tplIndex, transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrSeqOptZonedDateTime  => addSeqOpt(ent, attr, paramIndex, tplIndex, transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case _: AttrSeqOptUUID           => addSeqOpt(ent, attr, paramIndex, tplIndex, transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case _: AttrSeqOptURI            => addSeqOpt(ent, attr, paramIndex, tplIndex, transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case _: AttrSeqOptByte           => addByteArray(ent, attr, paramIndex, tplIndex)
      case _: AttrSeqOptShort          => addSeqOpt(ent, attr, paramIndex, tplIndex, transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case _: AttrSeqOptChar           => addSeqOpt(ent, attr, paramIndex, tplIndex, transformChar, extsChar, seq2arrayChar, value2jsonChar)
    }
  }

  private def attrMapManSetter(a: AttrMapMan, paramIndex: Int, tplIndex: Int): (PS, Product) => Unit = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case _: AttrMapManID             => addMap(ent, attr, paramIndex, tplIndex, transformID, value2jsonID)
      case _: AttrMapManString         => addMap(ent, attr, paramIndex, tplIndex, transformString, value2jsonString)
      case _: AttrMapManInt            => addMap(ent, attr, paramIndex, tplIndex, transformInt, value2jsonInt)
      case _: AttrMapManLong           => addMap(ent, attr, paramIndex, tplIndex, transformLong, value2jsonLong)
      case _: AttrMapManFloat          => addMap(ent, attr, paramIndex, tplIndex, transformFloat, value2jsonFloat)
      case _: AttrMapManDouble         => addMap(ent, attr, paramIndex, tplIndex, transformDouble, value2jsonDouble)
      case _: AttrMapManBoolean        => addMap(ent, attr, paramIndex, tplIndex, transformBoolean, value2jsonBoolean)
      case _: AttrMapManBigInt         => addMap(ent, attr, paramIndex, tplIndex, transformBigInt, value2jsonBigInt)
      case _: AttrMapManBigDecimal     => addMap(ent, attr, paramIndex, tplIndex, transformBigDecimal, value2jsonBigDecimal)
      case _: AttrMapManDate           => addMap(ent, attr, paramIndex, tplIndex, transformDate, value2jsonDate)
      case _: AttrMapManDuration       => addMap(ent, attr, paramIndex, tplIndex, transformDuration, value2jsonDuration)
      case _: AttrMapManInstant        => addMap(ent, attr, paramIndex, tplIndex, transformInstant, value2jsonInstant)
      case _: AttrMapManLocalDate      => addMap(ent, attr, paramIndex, tplIndex, transformLocalDate, value2jsonLocalDate)
      case _: AttrMapManLocalTime      => addMap(ent, attr, paramIndex, tplIndex, transformLocalTime, value2jsonLocalTime)
      case _: AttrMapManLocalDateTime  => addMap(ent, attr, paramIndex, tplIndex, transformLocalDateTime, value2jsonLocalDateTime)
      case _: AttrMapManOffsetTime     => addMap(ent, attr, paramIndex, tplIndex, transformOffsetTime, value2jsonOffsetTime)
      case _: AttrMapManOffsetDateTime => addMap(ent, attr, paramIndex, tplIndex, transformOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrMapManZonedDateTime  => addMap(ent, attr, paramIndex, tplIndex, transformZonedDateTime, value2jsonZonedDateTime)
      case _: AttrMapManUUID           => addMap(ent, attr, paramIndex, tplIndex, transformUUID, value2jsonUUID)
      case _: AttrMapManURI            => addMap(ent, attr, paramIndex, tplIndex, transformURI, value2jsonURI)
      case _: AttrMapManByte           => addMap(ent, attr, paramIndex, tplIndex, transformByte, value2jsonByte)
      case _: AttrMapManShort          => addMap(ent, attr, paramIndex, tplIndex, transformShort, value2jsonShort)
      case _: AttrMapManChar           => addMap(ent, attr, paramIndex, tplIndex, transformChar, value2jsonChar)
    }
  }

  private def attrMapOptSetter(a: AttrMapOpt, paramIndex: Int, tplIndex: Int): (PS, Product) => Unit = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case _: AttrMapOptID             => addMapOpt(ent, attr, paramIndex, tplIndex, transformID, value2jsonID)
      case _: AttrMapOptString         => addMapOpt(ent, attr, paramIndex, tplIndex, transformString, value2jsonString)
      case _: AttrMapOptInt            => addMapOpt(ent, attr, paramIndex, tplIndex, transformInt, value2jsonInt)
      case _: AttrMapOptLong           => addMapOpt(ent, attr, paramIndex, tplIndex, transformLong, value2jsonLong)
      case _: AttrMapOptFloat          => addMapOpt(ent, attr, paramIndex, tplIndex, transformFloat, value2jsonFloat)
      case _: AttrMapOptDouble         => addMapOpt(ent, attr, paramIndex, tplIndex, transformDouble, value2jsonDouble)
      case _: AttrMapOptBoolean        => addMapOpt(ent, attr, paramIndex, tplIndex, transformBoolean, value2jsonBoolean)
      case _: AttrMapOptBigInt         => addMapOpt(ent, attr, paramIndex, tplIndex, transformBigInt, value2jsonBigInt)
      case _: AttrMapOptBigDecimal     => addMapOpt(ent, attr, paramIndex, tplIndex, transformBigDecimal, value2jsonBigDecimal)
      case _: AttrMapOptDate           => addMapOpt(ent, attr, paramIndex, tplIndex, transformDate, value2jsonDate)
      case _: AttrMapOptDuration       => addMapOpt(ent, attr, paramIndex, tplIndex, transformDuration, value2jsonDuration)
      case _: AttrMapOptInstant        => addMapOpt(ent, attr, paramIndex, tplIndex, transformInstant, value2jsonInstant)
      case _: AttrMapOptLocalDate      => addMapOpt(ent, attr, paramIndex, tplIndex, transformLocalDate, value2jsonLocalDate)
      case _: AttrMapOptLocalTime      => addMapOpt(ent, attr, paramIndex, tplIndex, transformLocalTime, value2jsonLocalTime)
      case _: AttrMapOptLocalDateTime  => addMapOpt(ent, attr, paramIndex, tplIndex, transformLocalDateTime, value2jsonLocalDateTime)
      case _: AttrMapOptOffsetTime     => addMapOpt(ent, attr, paramIndex, tplIndex, transformOffsetTime, value2jsonOffsetTime)
      case _: AttrMapOptOffsetDateTime => addMapOpt(ent, attr, paramIndex, tplIndex, transformOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrMapOptZonedDateTime  => addMapOpt(ent, attr, paramIndex, tplIndex, transformZonedDateTime, value2jsonZonedDateTime)
      case _: AttrMapOptUUID           => addMapOpt(ent, attr, paramIndex, tplIndex, transformUUID, value2jsonUUID)
      case _: AttrMapOptURI            => addMapOpt(ent, attr, paramIndex, tplIndex, transformURI, value2jsonURI)
      case _: AttrMapOptByte           => addMapOpt(ent, attr, paramIndex, tplIndex, transformByte, value2jsonByte)
      case _: AttrMapOptShort          => addMapOpt(ent, attr, paramIndex, tplIndex, transformShort, value2jsonShort)
      case _: AttrMapOptChar           => addMapOpt(ent, attr, paramIndex, tplIndex, transformChar, value2jsonChar)
    }
  }
}