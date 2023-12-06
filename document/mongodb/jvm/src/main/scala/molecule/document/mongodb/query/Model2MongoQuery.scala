package molecule.document.mongodb.query

import java.util
import com.mongodb.MongoClientSettings
import com.mongodb.client.model._
import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.ConnProxy
import molecule.core.query.Model2QueryBase
import molecule.core.util.{JavaConversions, ModelUtils}
import org.bson._
import org.bson.conversions.Bson
import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class Model2MongoQuery[Tpl](elements0: List[Element])
  extends Model2QueryBase
    with ModelUtils
    with ResolveExprOne
    with ResolveExprOneID
    with ResolveExprSet
    with ResolveExprSetRefAttr
    with MongoQueryBase
    with JavaConversions
    with MoleculeLogging {


  final def getBsonQuery(
    altElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int],
    optProxy: Option[ConnProxy]
  ): (String, util.ArrayList[Bson]) = {
    val elements1 = if (altElements.isEmpty) elements0 else altElements
    validateQueryModel(elements1)
    //    elements.foreach(println)

    // Set attrMap if available (used to get original type of aggregate attributes)
    optProxy.foreach(p => attrMap = p.attrMap)
    val elements2 = prepareElements(elements1, optProxy)

    // Recursively resolve molecule elements
    resolve(elements2)

    if (!idField) {
      projections.add(0, Projections.excludeId())
    }

    val pipeline = new util.ArrayList[Bson]()
    def addStage(name: String, params: Bson): Boolean = {
      // Add codec for MQL expressions
      val doc = params.toBsonDocument(classOf[Bson], MongoClientSettings.getDefaultCodecRegistry)
      pipeline.add(new BsonDocument().append(name, doc))
    }

    if (sampleSize > 0) {
      pipeline.add(
        new BsonDocument().append("$sample",
          new BsonDocument().append("size", new BsonInt32(sampleSize)))
      )
    }

    if (!matches.isEmpty) {
      addStage("$match", Filters.and(matches))
    }

    //    println(" 0 " + preGroupFields)
    //    println(" 1 " + groupIdFields)
    //    println(" 2 " + groupExprs)
    //    println(" 3 " + addFields.toList.sortBy(-_._1.length))

    // Pre-group
    val prefix = if (preGroupFields.nonEmpty) {
      val preGroupFieldsDoc = new BsonDocument()
      groupIdFields.foreach { case (pathAlias, pathDot, field) =>
        preGroupFieldsDoc.put(pathAlias + field, new BsonString("$" + pathDot + field))
      }
      preGroupFields.foreach { case (fieldAlias, pathField) =>
        preGroupFieldsDoc.put(fieldAlias, new BsonString("$" + pathField))
      }
      pipeline.add(new BsonDocument().append("$group", new BsonDocument().append("_id", preGroupFieldsDoc)))
      "$_id."
    } else "$"

    // Main group
    if (groupExprs.nonEmpty) {
      val groupIdFieldsDoc = new BsonDocument()
      groupIdFields.foreach { case (pathAlias, pathDot, field) =>
        val ctx = if (preGroupFields.isEmpty) pathDot else pathAlias
        groupIdFieldsDoc.put(pathAlias + field, new BsonString(prefix + ctx + field))
      }
      val groupDoc = new BsonDocument()
      groupDoc.append("_id", groupIdFieldsDoc)

      groupExprs.foreach { case (field, bson) =>
        groupDoc.put(field, bson)
      }
      pipeline.add(new BsonDocument().append("$group", groupDoc))


      // $addFields - "format" fields to expected structure
      val addFieldsDoc = new BsonDocument()
      groupIdFields.collect { case ("", "", field) =>
        addFieldsDoc.put(field, new BsonString("$_id." + field))
      }

      if (addFields.nonEmpty) {
        val refFields = addFields.toList.sortBy(-_._1.length)

        // Add fields of initial namespace
        refFields.last._2.foreach { case (field, value) =>
          addFieldsDoc.put(field, value)
        }

        // Add ref fields from leaves to branches
        if (addFields.size != 1) {
          val branches = mutable.Map.empty[List[String], List[(String, BsonDocument)]]
          branches(Nil) = Nil
          refFields.init.foreach { case (refPath, fields) =>
            val refFieldsDoc = new BsonDocument()
            fields.foreach { case (field, value) =>
              refFieldsDoc.put(field, value)
            }
            if (branches.keys.toList.contains(refPath)) {
              // Add ref branch(es) to current doc
              branches(refPath).foreach { case (ref, refDoc) =>
                refFieldsDoc.put(ref, refDoc)
              }
            }
            //            println("A " + branches)
            //            println("A " + refPath)
            //            println("A " + refFieldsDoc)
            branches(refPath.init) = branches.getOrElse(refPath.init, Nil) :+ (refPath.last -> refFieldsDoc)
          }

          // add ref branches of initial namespace
          branches(Nil).foreach { case (ref, fieldsDoc) =>
            addFieldsDoc.put(ref, fieldsDoc)
          }
        }
      }
      if (!addFieldsDoc.isEmpty)
        pipeline.add(new BsonDocument().append("$addFields", addFieldsDoc))
    }

    // Select which fields to output
    if (!projections.isEmpty) {
      addStage("$project", Projections.fields(projections))
    }

    if (!sorts.isEmpty) {
      addStage("$sort", Sorts.orderBy(sorts))
    }

    (getInitialNonGenericNs(elements2), pipeline)
  }

  final private def renderSqlQuery(
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): String = {
    //    val distinct_ = if (distinct) " DISTINCT" else ""
    //    val select_   = (nestedIds ++ select).mkString(s",\n  ")
    //
    //    val joins_      = if (joins.isEmpty) "" else {
    //      val max1  = joins.map(_._1.length).max
    //      val max2  = joins.map(_._2.length).max
    //      val max3  = joins.map(_._3.length).max
    //      val hasAs = joins.exists(_._3.nonEmpty)
    //      joins.map { case (join, table, as, predicates) =>
    //        val join_  = join + padS(max1, join)
    //        val table_ = table + padS(max2, table)
    //        val as_    = if (hasAs) {
    //          if (as.isEmpty) padS(max3 + 4, "") else " AS " + as + padS(max3, as)
    //        } else ""
    //        s"$join_ $table_$as_ ON $predicates"
    //      }.mkString("\n  ", "\n  ", "")
    //    }
    //    val tempTables_ = if (tempTables.isEmpty) "" else tempTables.mkString(",\n  ", ",\n  ", "")
    //
    //    val notNulls = notNull.map(col => (col, "IS NOT NULL"))
    //    val allWhere = where ++ notNulls
    //    val where_   = if (allWhere.isEmpty) "" else {
    //      val max = allWhere.map(_._1.length).max
    //      allWhere.map {
    //        case ("", predicate)  => predicate
    //        case (col, predicate) => s"$col " + padS(max, col) + predicate
    //      }.mkString("\nWHERE\n  ", s" AND\n  ", "")
    //    }
    //
    //    val groupBy_ = if (groupBy.isEmpty && !aggregate) "" else {
    //      val allGroupByCols = groupBy ++ (if (aggregate) groupByCols else Nil)
    //      if (allGroupByCols.isEmpty) "" else allGroupByCols.mkString("\nGROUP BY ", ", ", "")
    //    }
    //
    //    val having_ = if (having.isEmpty) "" else having.mkString("\nHAVING ", ", ", "")
    //
    //    val isBackwards = optLimit.isDefined && optLimit.get < 0 || optOffset.isDefined && optOffset.get < 0
    //
    //    val orderBy_ = if (orderBy.isEmpty) {
    //      ""
    //    } else {
    //      val coordinates = orderBy.sortBy(t => (t._1, t._2))
    //      val orderCols   = if (isBackwards) {
    //        coordinates.map {
    //          // Switch sort direction on top level
    //          case (0, _, col, "DESC") => col
    //          case (0, _, col, _)      => col + " DESC"
    //          // Nested sorts stay unchanged
    //          case (_, _, col, "DESC") => col + " DESC"
    //          case (_, _, col, _)      => col
    //        }
    //      } else {
    //        coordinates.map {
    //          case (_, _, col, dir) => col + dir
    //        }
    //      }
    //      orderCols.mkString("\nORDER BY ", ", ", "")
    //    }
    //
    //    val pagination_ = pagination(optLimit, optOffset, isBackwards)
    //
    //    s"""SELECT$distinct_
    //       |  $select_
    //       |FROM $from$joins_$tempTables_$where_$groupBy_$having_$orderBy_$pagination_;""".stripMargin
    ???
  }


  def pagination(optLimit: Option[Int], optOffset: Option[Int], isBackwards: Boolean): String = {
    val limit_ = if (isNested || isNestedOpt) {
      ""
    } else if (hardLimit != 0) {
      s"\nLIMIT $hardLimit"
    } else {
      optLimit.fold("")(limit => s"\nLIMIT " + (if (isBackwards) -limit else limit))
    }

    val offset_ = if (isNested || isNestedOpt) {
      ""
    } else {
      optOffset.fold("")(offset => s"\nOFFSET " + (if (isBackwards) -offset else offset))
    }

    s"$limit_$offset_"
  }

  final def getTotalCountQuery: String = {
    //    val table  = from
    //    val joins_ = if (joins.isEmpty) "" else {
    //      val max1  = joins.map(_._1.length).max
    //      val max2  = joins.map(_._2.length).max
    //      val max3  = joins.map(_._3.length).max
    //      val hasAs = joins.exists(_._3.nonEmpty)
    //      joins.map { case (join, table, as, predicates) =>
    //        val join_  = join + padS(max1, join)
    //        val table_ = table + padS(max2, table)
    //        val as_    = if (hasAs) {
    //          if (as.isEmpty) padS(max3 + 4, "") else " AS " + as + padS(max3, as)
    //        } else ""
    //        s"$join_ $table_$as_ ON $predicates"
    //      }.mkString("\n", "\n", "")
    //    }
    //
    //    val notNulls = notNull.map(col => (col, "IS NOT NULL"))
    //    val allWhere = where ++ notNulls
    //    val where_   = if (allWhere.isEmpty) "" else {
    //      val max = allWhere.map(_._1.length).max
    //      allWhere.map {
    //        case ("", predicate)  => predicate
    //        case (col, predicate) => s"$col " + padS(max, col) + predicate
    //      }.mkString("\nWHERE\n  ", s" AND\n  ", "")
    //    }
    //    val having_  = if (having.isEmpty) "" else having.mkString("\nHAVING ", ", ", "")
    //
    //    s"""SELECT COUNT($table.id)
    //       |FROM $table$joins_$where_$having_;""".stripMargin

    ???
  }


  private[molecule] def getWhereClauses: ListBuffer[(String, String)] = {
    //    from = getInitialNonGenericNs(elements0)
    //    exts += from -> None
    //    resolve(elements0)
    //    where
    ???
  }


  private def prepareElements(elements: List[Element], optProxy: Option[ConnProxy]): List[Element] = {
    @tailrec
    def prepare(elements: List[Element], acc: List[Element]): List[Element] = {
      elements match {
        case element :: tail =>
          element match {
            case a: Attr      => prepare(tail, acc :+ prepareAttr(a))
            case n: Nested    => prepare(tail, acc :+ prepareNested(n))
            case n: NestedOpt => prepare(tail, acc :+ prepareNestedOpt(n))
            case other        => prepare(tail, acc :+ other)
          }
        case Nil             => acc
      }
    }
    def prepareAttr(a: Attr): Attr = {
      availableAttrs += a.cleanName
      if (a.filterAttr.nonEmpty) {
        val fa = a.filterAttr.get
        if (fa.filterAttr.nonEmpty) {
          throw ModelError(s"Nested filter attributes not allowed in ${a.ns}.${a.attr}")
        }
        val filterAttr = fa.cleanName
        filterAttrVars.get(filterAttr).fold {
          // Create datomic variable for this expression attribute
          filterAttrVars = filterAttrVars + (filterAttr -> a.cleanName)
        }(_ => throw ModelError(s"Can't refer to ambiguous filter attribute $filterAttr"))

        if (fa.ns == a.ns) {
          // Add adjacent filter attribute is lifted...
        } else if (fa.isInstanceOf[Mandatory]) {
          throw ModelError(s"Filter attribute $filterAttr pointing to other namespace should be tacit.")
        } else if (fa.op != V) {
          throw ModelError("Filtering inside cross-namespace attribute filter not allowed.")
        } else {
          // Expect expression attribute in other namespace
          expectedFilterAttrs += fa.cleanName
        }
      }
      a
    }

    def prepareNested(nested: Nested): Nested = {
      Nested(nested.ref, prepare(nested.elements, Nil))
    }

    def prepareNestedOpt(nested: NestedOpt): NestedOpt = {
      NestedOpt(nested.ref, prepare(nested.elements, Nil))
    }

    val elements1 = prepare(elements, Nil)

    if (expectedFilterAttrs.nonEmpty && expectedFilterAttrs.intersect(availableAttrs) != expectedFilterAttrs) {
      throw ModelError("Please add missing filter attributes:\n  " + expectedFilterAttrs.mkString("\n  "))
    }

    elements1
  }

  private lazy val noIdFiltering = "Filter attributes not allowed to involve entity ids."

  @tailrec
  final private def resolve(elements: List[Element]): Unit = elements match {
    case element :: tail => element match {
      case a: AttrOne =>
        if (a.attr == "id" && a.filterAttr.nonEmpty || a.attr != "id" && a.filterAttr.exists(_.attr == "id")) {
          throw ModelError(noIdFiltering)
        }
        if (a.attr == "id") {
          idField = true
          a match {
            case a: AttrOneMan => resolveAttrOneManID(a); resolve(tail)
            case a: AttrOneTac => resolveAttrOneTacID(a); resolve(tail)
            case _             => throw new Exception("Unexpected optional id")
          }
        } else {
          if (a.refNs.nonEmpty)
            throw ModelError("Can't query for non-existing ids of embedded documents in MongoDB.")
          a match {
            case a: AttrOneMan => resolveAttrOneMan(a); resolve(tail)
            case a: AttrOneOpt => resolveAttrOneOpt(a); resolve(tail)
            case a: AttrOneTac => resolveAttrOneTac(a); resolve(tail)
          }
        }
      //      case a: AttrSet if a.refNs.isDefined => a match {
      //        case a: AttrSetMan =>
      //
      //          throw ModelError("bam!!!!")
      //          resolveRefAttrSetMan(a);
      //          resolve(tail)
      //        case a: AttrSetOpt => resolveRefAttrSetOpt(a); resolve(tail)
      //        case a: AttrSetTac => resolveRefAttrSetTac(a); resolve(tail)
      //      }
      case a: AttrSet                     =>
        if (a.refNs.nonEmpty)
          throw ModelError("Can't query for non-existing set of ids of embedded documents in MongoDB.")
        a match {
          case a: AttrSetMan => resolveAttrSetMan(a); resolve(tail)
          case a: AttrSetOpt => resolveAttrSetOpt(a); resolve(tail)
          case a: AttrSetTac => resolveAttrSetTac(a); resolve(tail)
        }
      case ref: Ref                       => resolveRef0(ref, tail)
      case backRef: BackRef               => resolveBackRef(backRef, tail)
      case Nested(ref, nestedElements)    => resolveNested(ref, nestedElements, tail)
      case NestedOpt(ref, nestedElements) => resolveNestedOpt(ref, nestedElements, tail)
    }
    case Nil             => ()
  }


  final private def resolveRef0(ref: Ref, tail: List[Element]): Unit = {
    val Ref(_, refAttr, _, card, _, _) = ref
    if (isNestedOpt && card == CardSet) {
      throw ModelError(
        "Only cardinality-one refs allowed in optional nested queries. Found: " + ref
      )
    }
    refPath = refPath :+ refAttr
    pathDot = pathDot + refAttr + "."
    pathUnderscore = pathUnderscore + refAttr + "_"

    //    addFields(refPath) = Nil
    addFields(refPath) = Nil

    // Continue from ref namespace
    val nss     = casts.last
    val curPath = nss.last
    nss += ((ListBuffer.empty[String].addAll(curPath._1.toList :+ refAttr), ListBuffer.empty[(String, BsonDocument => Any)]))
    //    printCasts("REF " + refAttr)


    resolve(tail)
  }

  final private def resolveBackRef(bRef: BackRef, tail: List[Element]): Unit = {
    if (isNested || isNestedOpt) {
      val BackRef(backRef, _, _) = bRef
      tail.head match {
        case a: Attr => throw ModelError(
          s"Expected ref after backref _$backRef. " +
            s"Please add attribute ${a.ns}.${a.attr} to initial namespace ${a.ns} " +
            s"instead of after backref _$backRef."
        )
        case _       => ()
      }
    }

    refPath = refPath.init
    pathDot = {
      val nss = pathDot.init.split('.').init
      if (nss.isEmpty) "" else nss.mkString(".") + "."
    }
    pathUnderscore = {
      val nss = pathUnderscore.init.split('_').init
      if (nss.isEmpty) "" else nss.mkString("_") + "_"
    }
    //    addFields(path) = Nil


    // Step back to previous namespace
    val nss     = casts.last
    val curPath = nss.last
    nss += ((ListBuffer.empty[String].addAll(curPath._1.toList.init), ListBuffer.empty[(String, BsonDocument => Any)]))
    //    printCasts("BACKREF")

    resolve(tail)
  }

  final private def resolveNested(ref: Ref, nestedElements: List[Element], tail: List[Element]): Unit = {
    level += 1
    isNested = true
    if (isNestedOpt) {
      noMixedNestedModes
    }
    validateRefNs(ref, nestedElements)
    val Ref(_, refAttr, _, _, _, _) = ref

    // No empty nested arrays when asking for mandatory nested data
    matches.add(Filters.ne(refAttr, new BsonArray()))

    refPath = refPath :+ refAttr
    pathDot = pathDot + refAttr + "."
    pathUnderscore = pathUnderscore + refAttr + "_"

    //    addFields(refPath) = Nil
    addFields(refPath) = Nil

    // Initiate nested namespace
    casts += ListBuffer(
      (
        ListBuffer.empty[String].addAll(casts.last.last._1.toList :+ refAttr),
        ListBuffer.empty[(String, BsonDocument => Any)]
      )
    )
    //    printCasts("NEST " + refAttr)

    resolve(nestedElements)
    resolve(tail)
  }

  final private def resolveNestedOpt(ref: Ref, nestedElements: List[Element], tail: List[Element]): Unit = {
    level += 1
    isNestedOpt = true
    if (isNested) {
      noMixedNestedModes
    }
    if (expectedFilterAttrs.nonEmpty) {
      throw ModelError("Filter attributes not allowed in optional nested queries.")
    }
    validateRefNs(ref, nestedElements)
    val Ref(_, refAttr, _, _, _, _) = ref

    refPath = refPath :+ refAttr
    pathDot = pathDot + refAttr + "."
    pathUnderscore = pathUnderscore + refAttr + "_"

    //    addFields(refPath) = Nil
    addFields(refPath) = Nil

    // Initiate (optional) nested namespace
    casts += ListBuffer(
      (
        ListBuffer.empty[String].addAll(casts.last.last._1.toList :+ refAttr),
        ListBuffer.empty[(String, BsonDocument => Any)]
      )
    )
    //    printCasts("OPT NEST " + refAttr)

    resolve(nestedElements)
    resolve(tail)
  }

  final private def validateRefNs(ref: Ref, nestedElements: List[Element]): Unit = {
    val refName  = ref.refAttr.capitalize
    val nestedNs = nestedElements.head match {
      case a: Attr => a.ns
      case r: Ref  => r.ns
      case other   => unexpectedElement(other)
    }
    if (ref.refNs != nestedNs) {
      throw ModelError(s"`$refName` can only nest to `${ref.refNs}`. Found: `$nestedNs`")
    }
  }
}