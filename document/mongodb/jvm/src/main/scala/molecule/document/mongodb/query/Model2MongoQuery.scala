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
import molecule.document.mongodb.util.BsonUtils
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
    with ResolveRef
    with MongoQueryBase
    with JavaConversions
    with BsonUtils
    with MoleculeLogging {

  private val topBranch = branches.head._2

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


    val topStages = new util.ArrayList[Bson]()
    def addStage(name: String, params: Bson): Boolean = {
      // Add codec for MQL expressions
      val doc = params.toBsonDocument(classOf[Bson], MongoClientSettings.getDefaultCodecRegistry)
      topStages.add(new BsonDocument().append(name, doc))
    }

    if (sampleSize > 0) {
      topStages.add(
        new BsonDocument().append("$sample",
          new BsonDocument().append("size", new BsonInt32(sampleSize)))
      )
    }

    //    println("..............")
    //    branches.foreach { case (rp, b) => println(rp) }
    //
    //    val branches2 = List(
    //      branches.head,
    //      branches(1),
    //      (List("c", "C"), branches(2)._2)
    //    )

    branches.toList.sortBy(-_._1.length).foreach {
      case (Nil, b) =>
        println(s"B -------------------- List()  ${b.refPath}")
        topStages.addAll(getStages(Nil, b))

      case (refPath, b) =>
        println(s"A -------------------- $refPath  ${b.refPath}")
        val List(refAttr, refNs) = refPath.takeRight(2)
        val lookup               = new BsonDocument()
          .append("from", new BsonString(refNs))
          .append("localField", new BsonString(refAttr))
          .append("foreignField", new BsonString("_id"))
          .append("as", new BsonString(refAttr))

        val pipeline = new BsonArray()
        getStages(refPath, b).forEach(stage => pipeline.add(stage))
        lookup.append("pipeline", pipeline)
        lookups += (refPath -> new BsonDocument().append("$lookup", lookup))
    }

    // Select which fields to output
    if (!topBranch.idField) {
      //      projections.add(0, Projections.excludeId())
      projections(Nil) = projections(Nil).append("_id", new BsonInt32(0))
    }

    if (projections.nonEmpty) {
      //      println("#####################")
      //      println(projections2(Nil).toJson(pretty))
      //      println("#####################")
      //      addStage("$project", Projections.fields(projections))
      //      addStage("$project", projections2(Nil))
      topStages.add(new BsonDocument().append("$project", projections(Nil)))
    }

    if (!sorts.isEmpty) {
      addStage("$sort", Sorts.orderBy(sorts))
      //      pipeline.add(new BsonDocument().append("$sort", sorts))
    }

    (getInitialNonGenericNs(elements2), topStages)
  }


  //  private def getStages(b: Branch, lookups: List[BsonDocument]): util.ArrayList[BsonDocument] = {
  private def getStages(parentRefPath: List[String], b: Branch): util.ArrayList[BsonDocument] = {
    val stages = new util.ArrayList[BsonDocument]()
    def addStage(name: String, params: Bson): Boolean = {
      // Add codec for MQL expressions
      val doc = params.toBsonDocument(classOf[Bson], MongoClientSettings.getDefaultCodecRegistry)
      stages.add(new BsonDocument().append(name, doc))
    }

    b.matches.size() match {
      case 0 => // do nothing
      case 1 => addStage("$match", b.matches.iterator.next.toBsonDocument)
      case _ => addStage("$match", Filters.and(b.matches))
    }

    println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% " + lookups.length)
    lookups.collect {
      case (refPath, lookup) =>
        val topLevelLookup = refPath.length == 2
        val isChildBranch  = parentRefPath.nonEmpty &&
          (parentRefPath.length + 2) == refPath.length &&
          refPath.startsWith(parentRefPath)

        if (topLevelLookup || isChildBranch) {
          println(s"  REF  $parentRefPath  $refPath")
          println(s"  - $lookup")
          stages.add(lookup)
        } else {
          println(s"  XXX  $parentRefPath  $refPath")
          println(s"  - $lookup")
        }
    }

    //    println(" 0 " + preGroupFields)
    //    println(" 1 " + groupIdFields)
    //    println(" 2 " + groupExprs)
    //    println(" 3 " + addFields.toList.sortBy(-_._1.length))

    // Pre-group
    val prefix = if (b.preGroupFields.nonEmpty) {
      val preGroupFieldsDoc = new BsonDocument()
      b.groupIdFields.foreach { case (pathAlias, pathDot, field) =>
        preGroupFieldsDoc.put(pathAlias + field, new BsonString("$" + pathDot + field))
      }
      b.preGroupFields.foreach { case (fieldAlias, pathField) =>
        preGroupFieldsDoc.put(fieldAlias, new BsonString("$" + pathField))
      }
      stages.add(new BsonDocument().append("$group", new BsonDocument().append("_id", preGroupFieldsDoc)))
      "$_id."
    } else "$"

    // Main group
    if (b.groupExprs.nonEmpty) {
      val groupIdFieldsDoc = new BsonDocument()
      b.groupIdFields.foreach { case (pathAlias, pathDot, field) =>
        val ctx = if (b.preGroupFields.isEmpty) pathDot else pathAlias
        groupIdFieldsDoc.put(pathAlias + field, new BsonString(prefix + ctx + field))
      }
      val groupDoc = new BsonDocument()
      groupDoc.append("_id", groupIdFieldsDoc)

      b.groupExprs.foreach { case (field, bson) =>
        groupDoc.put(field, bson)
      }
      stages.add(new BsonDocument().append("$group", groupDoc))


      // $addFields - "format" fields to expected structure
      val addFieldsDoc = new BsonDocument()
      b.groupIdFields.collect { case ("", "", field) =>
        addFieldsDoc.put(field, new BsonString("$_id." + field))
      }

      if (b.addFields.nonEmpty) {
        val refFields = b.addFields.toList.sortBy(-_._1.length)

        // Add fields of initial namespace
        refFields.last._2.foreach { case (field, value) =>
          addFieldsDoc.put(field, value)
        }

        // Add ref fields from leaves to branches
        if (b.addFields.size != 1) {
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
        stages.add(new BsonDocument().append("$addFields", addFieldsDoc))
    }

    //    println("------------- " + b.refPath)
    //    stages.forEach(p => println(p))

    stages
  }



  //  final def getBsonQueryOLD(
  //    altElements: List[Element],
  //    optLimit: Option[Int],
  //    optOffset: Option[Int],
  //    optProxy: Option[ConnProxy]
  //  ): (String, util.ArrayList[Bson]) = {
  //    val elements1 = if (altElements.isEmpty) elements0 else altElements
  //    validateQueryModel(elements1)
  //    //    elements.foreach(println)
  //
  //    // Set attrMap if available (used to get original type of aggregate attributes)
  //    optProxy.foreach(p => attrMap = p.attrMap)
  //    val elements2 = prepareElements(elements1, optProxy)
  //
  //    // Recursively resolve molecule elements
  //    resolve(elements2)
  //
  //    if (!idField) {
  //      //      projections.add(0, Projections.excludeId())
  //      projections2(Nil) = projections2(Nil).append("_id", new BsonInt32(0))
  //    }
  //
  //    val pipeline = new util.ArrayList[Bson]()
  //    def addStage(name: String, params: Bson): Boolean = {
  //      // Add codec for MQL expressions
  //      val doc = params.toBsonDocument(classOf[Bson], MongoClientSettings.getDefaultCodecRegistry)
  //      pipeline.add(new BsonDocument().append(name, doc))
  //    }
  //
  //    if (sampleSize > 0) {
  //      pipeline.add(
  //        new BsonDocument().append("$sample",
  //          new BsonDocument().append("size", new BsonInt32(sampleSize)))
  //      )
  //    }
  //
  //    if (!matches.isEmpty) {
  //      addStage("$match", Filters.and(matches))
  //    }
  //
  //    lookups.filter(_._1.size == 1).foreach { case (_, lookup) =>
  //      pipeline.add(new BsonDocument().append("$lookup", lookup))
  //    }
  //
  //    //    println(" 0 " + preGroupFields)
  //    //    println(" 1 " + groupIdFields)
  //    //    println(" 2 " + groupExprs)
  //    //    println(" 3 " + addFields.toList.sortBy(-_._1.length))
  //
  //    // Pre-group
  //    val prefix = if (preGroupFields.nonEmpty) {
  //      val preGroupFieldsDoc = new BsonDocument()
  //      groupIdFields.foreach { case (pathAlias, pathDot, field) =>
  //        preGroupFieldsDoc.put(pathAlias + field, new BsonString("$" + pathDot + field))
  //      }
  //      preGroupFields.foreach { case (fieldAlias, pathField) =>
  //        preGroupFieldsDoc.put(fieldAlias, new BsonString("$" + pathField))
  //      }
  //      pipeline.add(new BsonDocument().append("$group", new BsonDocument().append("_id", preGroupFieldsDoc)))
  //      "$_id."
  //    } else "$"
  //
  //    // Main group
  //    if (groupExprs.nonEmpty) {
  //      val groupIdFieldsDoc = new BsonDocument()
  //      groupIdFields.foreach { case (pathAlias, pathDot, field) =>
  //        val ctx = if (preGroupFields.isEmpty) pathDot else pathAlias
  //        groupIdFieldsDoc.put(pathAlias + field, new BsonString(prefix + ctx + field))
  //      }
  //      val groupDoc = new BsonDocument()
  //      groupDoc.append("_id", groupIdFieldsDoc)
  //
  //      groupExprs.foreach { case (field, bson) =>
  //        groupDoc.put(field, bson)
  //      }
  //      pipeline.add(new BsonDocument().append("$group", groupDoc))
  //
  //
  //      // $addFields - "format" fields to expected structure
  //      val addFieldsDoc = new BsonDocument()
  //      groupIdFields.collect { case ("", "", field) =>
  //        addFieldsDoc.put(field, new BsonString("$_id." + field))
  //      }
  //
  //      if (addFields.nonEmpty) {
  //        val refFields = addFields.toList.sortBy(-_._1.length)
  //
  //        // Add fields of initial namespace
  //        refFields.last._2.foreach { case (field, value) =>
  //          addFieldsDoc.put(field, value)
  //        }
  //
  //        // Add ref fields from leaves to branches
  //        if (addFields.size != 1) {
  //          val branches = mutable.Map.empty[List[String], List[(String, BsonDocument)]]
  //          branches(Nil) = Nil
  //          refFields.init.foreach { case (refPath, fields) =>
  //            val refFieldsDoc = new BsonDocument()
  //            fields.foreach { case (field, value) =>
  //              refFieldsDoc.put(field, value)
  //            }
  //            if (branches.keys.toList.contains(refPath)) {
  //              // Add ref branch(es) to current doc
  //              branches(refPath).foreach { case (ref, refDoc) =>
  //                refFieldsDoc.put(ref, refDoc)
  //              }
  //            }
  //            //            println("A " + branches)
  //            //            println("A " + refPath)
  //            //            println("A " + refFieldsDoc)
  //            branches(refPath.init) = branches.getOrElse(refPath.init, Nil) :+ (refPath.last -> refFieldsDoc)
  //          }
  //
  //          // add ref branches of initial namespace
  //          branches(Nil).foreach { case (ref, fieldsDoc) =>
  //            addFieldsDoc.put(ref, fieldsDoc)
  //          }
  //        }
  //      }
  //      if (!addFieldsDoc.isEmpty)
  //        pipeline.add(new BsonDocument().append("$addFields", addFieldsDoc))
  //    }
  //
  //    // Select which fields to output
  //    if (projections2.nonEmpty) {
  //      //      println("#####################")
  //      //      println(projections2(Nil).toJson(pretty))
  //      //      println("#####################")
  //      //      addStage("$project", Projections.fields(projections))
  //      //      addStage("$project", projections2(Nil))
  //      pipeline.add(new BsonDocument().append("$project", projections2(Nil)))
  //    }
  //
  //    if (!sorts.isEmpty) {
  //      addStage("$sort", Sorts.orderBy(sorts))
  //      //      pipeline.add(new BsonDocument().append("$sort", sorts))
  //    }
  //
  //    (getInitialNonGenericNs(elements2), pipeline)
  //  }

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
          b.idField = true
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
      case ref: Ref                       => resolveRef(ref); resolve(tail)
      case backRef: BackRef               => resolveBackRef(backRef, tail.head); resolve(tail)
      case Nested(ref, nestedElements)    => resolveNested(ref, nestedElements, tail)
      case NestedOpt(ref, nestedElements) => resolveNestedOpt(ref, nestedElements, tail)
    }
    case Nil             => ()
  }

  final private def resolveNested(ref: Ref, nestedElements: List[Element], tail: List[Element]): Unit = {
    resolveNestedRef(ref, nestedElements)
    resolve(nestedElements)
    resolve(tail)
  }

  final private def resolveNestedOpt(ref: Ref, nestedElements: List[Element], tail: List[Element]): Unit = {
    resolveNestedOptRef(ref, nestedElements)
    resolve(nestedElements)
    resolve(tail)
  }
}