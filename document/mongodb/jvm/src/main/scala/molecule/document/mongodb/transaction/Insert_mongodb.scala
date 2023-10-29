package molecule.document.mongodb.transaction

import java.util
import molecule.base.ast._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ops.InsertOps
import molecule.core.transaction.{InsertResolvers_, ResolveInsert}
import molecule.core.util.ModelUtils
import org.bson._

trait Insert_mongodb
  extends Base_JVM_mongodb
    with InsertOps
    with DataType_JVM_mongodb
    with ModelUtils
    with MoleculeLogging { self: ResolveInsert with InsertResolvers_ =>

  doPrint = false

  def getData(nsMap: Map[String, MetaNs], elements: List[Element], tpls: Seq[Product]): Data = {
    val tpl2bson   = getResolver(nsMap, elements)
    val insertDocs = new util.ArrayList[BsonDocument]()
    tpls.foreach { tpl =>
      // Build new Bson saveDoc from each entity tuple
      saveDoc = new BsonDocument()
      tpl2bson(tpl)
      insertDocs.add(saveDoc)
    }
    (getInitialNs(elements), insertDocs)
  }


  override protected def addOne[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    handleValue: T => Any,
    exts: List[String] = Nil
  ): Product => Unit = {
    (tpl: Product) => {
      saveDoc.append(
        attr,
        handleValue(tpl.productElement(tplIndex).asInstanceOf[T]).asInstanceOf[BsonValue]
      )
    }
  }

  override protected def addOneOpt[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    handleValue: T => Any,
    exts: List[String] = Nil
  ): Product => Unit = {
    (tpl: Product) => {
      saveDoc.append(attr, tpl.productElement(tplIndex) match {
        case Some(scalaValue) => handleValue(scalaValue.asInstanceOf[T]).asInstanceOf[BsonValue]
        case None             => new BsonNull()
      })
    }
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    refNs.fold {
      (tpl: Product) => {
        tpl.productElement(tplIndex).asInstanceOf[Set[T]] match {
          case set if set.nonEmpty =>
            val array: util.ArrayList[BsonValue] = new util.ArrayList[BsonValue]()
            set.map(bsonValue => array.add(bsonValue.asInstanceOf[BsonValue]))
            saveDoc.append(attr, new BsonArray(array))

          case _ => saveDoc.append(attr, new BsonNull())
        }
        () // saveDoc mutated
      }
    } { refNs =>
      //      val refAttr   = attr
      //      val joinTable = ss(ns, refAttr, refNs)
      //      val curPath   = if (paramIndexes.nonEmpty) curRefPath else List("0", ns)
      //      val joinPath  = curPath :+ joinTable
      //
      //      // join table with single row (treated as normal insert since there's only 1 join per row)
      //      val (id1, id2) = if (ns == refNs)
      //        (ss(ns, "1_id"), ss(refNs, "2_id"))
      //      else
      //        (ss(ns, "id"), ss(refNs, "id"))
      //      // When insertion order is reversed, this join table will be set after left and right has been inserted
      //      inserts = (joinPath, List((id1, ""), (id2, ""))) +: inserts
      //
      //      if (paramIndexes.isEmpty) {
      //        // If current namespace has no attributes, then add an empty row with
      //        // default null values (only to be referenced as the left side of the join table)
      //        val emptyRowSetter: Setter = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
      //        addColSetter(curPath, emptyRowSetter)
      //        inserts = inserts :+ (curRefPath, List())
      //      }
      //
      (tpl: Product) => {
        //        // Empty row if no attributes in namespace in order to have an id for join table left side
        //        if (!paramIndexes.exists { case ((path, _), _) => path == curPath }) {
        //          // If current namespace has no attributes, then add an empty row with
        //          // default null values (only to be referenced as the left side of the join table)
        //          val emptyRowSetter: Setter = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
        //          addColSetter(curPath, emptyRowSetter)
        //        }
        //
        //        // Join table setter
        //        val refIds             = tpl.productElement(tplIndex).asInstanceOf[Set[Long]]
        //        val joinSetter: Setter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
        //          val id = idsMap(curPath)(rowIndex)
        //          refIds.foreach { refId =>
        //            ps.setLong(1, id)
        //            ps.setLong(2, refId)
        //            ps.addBatch()
        //          }
        //        }
        //        addColSetter(joinPath, joinSetter)
        ???
      }
    }
  }

  override protected def addSetOpt[T](
    ns: String,
    attr: String,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    refNs.fold {
      (tpl: Product) => {
        tpl.productElement(tplIndex) match {
          case Some(set: Set[_]) if set.nonEmpty =>
            val array: util.ArrayList[BsonValue] = new util.ArrayList[BsonValue]()
            set.map(bsonValue => array.add(bsonValue.asInstanceOf[BsonValue]))
            saveDoc.append(attr, new BsonArray(array))

          case _ => saveDoc.append(attr, new BsonNull())
        }
        ()
      }
    } { refNs =>
      //      val refAttr   = attr
      //      val joinTable = ss(ns, refAttr, refNs)
      //      val curPath   = curRefPath
      //      val joinPath  = curPath :+ joinTable
      //
      //      // join table with single row (treated as normal insert since there's only 1 join per row)
      //      val (id1, id2) = if (ns == refNs)
      //        (ss(ns, "1_id"), ss(refNs, "2_id"))
      //      else
      //        (ss(ns, "id"), ss(refNs, "id"))
      //      // When insertion order is reversed, this join table will be set after left and right has been inserted
      //      inserts = (joinPath, List((id1, ""), (id2, ""))) +: inserts
      //
      //      (tpl: Product) => {
      //        val colSetter = tpl.productElement(tplIndex) match {
      //          case Some(set: Set[_]) =>
      //            if (set.nonEmpty) {
      //              // Empty row if no attributes in namespace in order to have an id for join table
      //              if (!paramIndexes.exists { case ((path, _), _) => path == curPath }) {
      //                // If current namespace has no attributes, then add an empty row with
      //                // default null values (only to be referenced as the left side of the join table)
      //                val emptyRowSetter: Setter = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
      //                addColSetter(curPath, emptyRowSetter)
      //              }
      //
      //              // Join table setter
      //              val refIds = set.asInstanceOf[Set[Long]]
      //              (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
      //                val id = idsMap(curPath)(rowIndex)
      //                refIds.foreach { refId =>
      //                  ps.setLong(1, id)
      //                  ps.setLong(2, refId)
      //                  ps.addBatch()
      //                }
      //              }
      //            } else {
      //              (_: PS, _: IdsMap, _: RowIndex) => ()
      //            }
      //
      //          case None => (_: PS, _: IdsMap, _: RowIndex) => ()
      //        }
      //        addColSetter(joinPath, colSetter)
      //      }
      ???
    }
  }

  override protected def addRef(ns: String, refAttr: String, refNs: String, card: Card): Product => Unit = {
    //    getRefResolver[Product](ns, refAttr, refNs, card)
    ???
  }

  override protected def addBackRef(backRefNs: String): Product => Unit = {
    //    curRefPath = curRefPath.dropRight(2) // drop refAttr, refNs


    (_: Product) => ()
  }

  override protected def addNested(
    nsMap: Map[String, MetaNs],
    tplIndex: Int,
    ns: String,
    refAttr: String,
    refNs: String,
    nestedElements: List[Element]
  ): Product => Unit = {
    //    val joinTable  = ss(ns, refAttr, refNs)
    //    val (id1, id2) = if (ns == refNs)
    //      (ss(ns, "1_id"), ss(refNs, "2_id"))
    //    else
    //      (ss(ns, "id"), ss(refNs, "id"))
    //    val nextLevel  = level + 1
    //    val joinPath   = curRefPath :+ joinTable
    //    val leftPath   = curRefPath
    //    val rightPath  = List(s"$nextLevel", refNs)
    //    joins = joins :+ (joinPath, id1, id2, leftPath, rightPath)
    //    rightCountsMap(joinPath) = List.empty[Int]
    //
    //    // Initiate new level
    //    level = nextLevel
    //    curRefPath = List(s"$level", refNs)
    //    colSettersMap += curRefPath -> Nil
    //
    //    // Recursively resolve nested data
    //    val nestedResolver = getResolver(nsMap, nestedElements)
    //
    //    countValueAttrs(nestedElements) match {
    //      case 1 =>
    //        (tpl: Product) => {
    //          val nestedSingleValues = tpl.productElement(tplIndex).asInstanceOf[Seq[Any]]
    //          val length             = nestedSingleValues.length
    //          rightCountsMap(joinPath) = rightCountsMap(joinPath) :+ length
    //          nestedSingleValues.foreach { nestedSingleValue =>
    //            nestedResolver(Tuple1(nestedSingleValue))
    //          }
    //        }
    //      case _ =>
    //        (tpl: Product) => {
    //          val nestedTpls = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
    //          val length     = nestedTpls.length
    //          rightCountsMap(joinPath) = rightCountsMap(joinPath) :+ length
    //          nestedTpls.foreach { nestedTpl =>
    //            nestedResolver(nestedTpl)
    //          }
    //        }
    //    }
    ???
  }

  //  override protected lazy val handleString         = (v: Any) => new BsonString(v.asInstanceOf[String])
  //  override protected lazy val handleInt            = (v: Any) => new BsonInt32(v.asInstanceOf[Int])
  //  override protected lazy val handleLong           = (v: Any) => new BsonInt64(v.asInstanceOf[Long])
  //  override protected lazy val handleFloat          = (v: Any) => new BsonDouble(v.asInstanceOf[Float])
  //  override protected lazy val handleDouble         = (v: Any) => new BsonDouble(v.asInstanceOf[Double])
  //  override protected lazy val handleBoolean        = (v: Any) => new BsonBoolean(v.asInstanceOf[Boolean])
  //  override protected lazy val handleBigInt         = (v: Any) => new BsonDecimal128(new Decimal128(BigDecimal(v.asInstanceOf[BigInt]).bigDecimal))
  //  override protected lazy val handleBigDecimal     = (v: Any) => new BsonDecimal128(new Decimal128(v.asInstanceOf[BigDecimal].bigDecimal))
  //  override protected lazy val handleDate           = (v: Any) => new BsonDateTime(v.asInstanceOf[Date].getTime)
  //  override protected lazy val handleDuration       = (v: Any) => new BsonString(v.asInstanceOf[Duration].toString)
  //  override protected lazy val handleInstant        = (v: Any) => new BsonString(v.asInstanceOf[Instant].toString)
  //  override protected lazy val handleLocalDate      = (v: Any) => new BsonString(v.asInstanceOf[LocalDate].toString)
  //  override protected lazy val handleLocalTime      = (v: Any) => new BsonString(v.asInstanceOf[LocalTime].toString)
  //  override protected lazy val handleLocalDateTime  = (v: Any) => new BsonString(v.asInstanceOf[LocalDateTime].toString)
  //  override protected lazy val handleOffsetTime     = (v: Any) => new BsonString(v.asInstanceOf[OffsetTime].toString)
  //  override protected lazy val handleOffsetDateTime = (v: Any) => new BsonString(v.asInstanceOf[OffsetDateTime].toString)
  //  override protected lazy val handleZonedDateTime  = (v: Any) => new BsonString(v.asInstanceOf[ZonedDateTime].toString)
  //  override protected lazy val handleUUID           = (v: Any) => new BsonString(v.asInstanceOf[UUID].toString)
  //  override protected lazy val handleURI            = (v: Any) => new BsonString(v.asInstanceOf[URI].toString)
  //  override protected lazy val handleByte           = (v: Any) => new BsonInt32(v.asInstanceOf[Byte])
  //  override protected lazy val handleShort          = (v: Any) => new BsonInt32(v.asInstanceOf[Short])
  //  override protected lazy val handleChar           = (v: Any) => new BsonString(v.asInstanceOf[Char].toString)
}