package molecule.document.mongodb.query

import java.util
import com.mongodb.MongoClientSettings
import com.mongodb.client.model._
import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations_
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.ConnProxy
import molecule.core.query.Model2QueryBase
import molecule.core.util.{JavaConversions, ModelUtils}
import molecule.document.mongodb.query.mongoModel.FlatEmbed
import molecule.document.mongodb.util.BsonUtils
import org.bson._
import org.bson.conversions.Bson
import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class Model2MongoQuery[Tpl](elements0: List[Element])
  extends Model2QueryBase
    with ModelUtils
    with ModelTransformations_
    with ResolveExprOne
    with ResolveExprOneID
    with ResolveExprSet
    with ResolveExprSetRefAttr
    with ResolveRef
    with MongoQueryBase
    with JavaConversions
    with BsonUtils
    with MoleculeLogging {


  final def getBsonQuery(
    altElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int],
  ): (String, util.ArrayList[Bson]) = {
    val elements1 = if (altElements.isEmpty) elements0 else altElements
    hasFilterAttr = validateQueryModel(elements1)
    elements1.foreach(println)

    val elements2 = if (hasFilterAttr) {
      checkFilterAttrs(elements1)
    } else elements1
    elements2.foreach(println)

    val initialNs = getInitialNonGenericNs(elements1)
    topBranch = new FlatEmbed(ns = initialNs)
    b = topBranch

    // Recursively resolve molecule elements
    resolve(elements2)

    // Top Mongo aggregation stages
    val topStages = new util.ArrayList[Bson]()

    if (sampleSize > 0) {
      topStages.add(new BsonDocument().append("$sample",
        new BsonDocument().append("size", new BsonInt32(sampleSize)))
      )
    }

    // Recursively add aggregation pipeline stages for all branches
    topStages.addAll(topBranch.getStages)

    // Return elements with possible filter attribute resolutions
    (getInitialNonGenericNs(elements2), topStages)
  }

  def pagination(optLimit: Option[Int], optOffset: Option[Int], isBackwards: Boolean): String = {
    val limit_ = if (isNestedMan || isNestedOpt) {
      ""
    } else if (hardLimit != 0) {
      s"\nLIMIT $hardLimit"
    } else {
      optLimit.fold("")(limit => s"\nLIMIT " + (if (isBackwards) -limit else limit))
    }

    val offset_ = if (isNestedMan || isNestedOpt) {
      ""
    } else {
      optOffset.fold("")(offset => s"\nOFFSET " + (if (isBackwards) -offset else offset))
    }

    s"$limit_$offset_"
  }


  private def checkFilterAttrs(elements: List[Element]): List[Element] = {
    val nsAttrPaths    = mutable.Map.empty[String, List[List[String]]]
    val qualifiedPaths = mutable.Map.empty[List[String], List[List[String]]]
    val directions     = ListBuffer.empty[List[String]]
    var qualifiedPath  = List.empty[String]
    var path           = List.empty[String]

    @tailrec
    def check(elements: List[Element]): Unit = {
      elements match {
        case element :: tail =>
          element match {
            case a: Attr      => checkAttr(a); check(tail)
            case r: Ref       => handleRef(r); check(tail)
            case _: BackRef   => handleBackRef(); check(tail)
            case n: Nested    => handleRef(n.ref); check(n.elements ++ tail)
            case n: NestedOpt => handleRef(n.ref); check(n.elements ++ tail)
          }
        case Nil             => ()
      }
    }

    def checkAttr(a: Attr): Unit = {
      if (path.isEmpty) {
        path = List(a.ns)
      }

      val nsAttr = a.cleanName
      nsAttrPaths(nsAttr) = nsAttrPaths.get(nsAttr).fold(List(path))(_ :+ path)

      qualifiedPath = path :+ a.cleanAttr

      //      println("  Y  " + qualifiedPath)

      qualifiedPaths(qualifiedPath) = qualifiedPaths.get(qualifiedPath).fold(List(path))(_ :+ path)

      directions += qualifiedPath

      //      availableAttrs += nsAttr
      //      availablePathAttrs += (if (path.isEmpty) a.attr else pathAttr(a.attr))

      if (a.filterAttr.nonEmpty) {
        val (dir, filterPath, fa) = a.filterAttr.get
        if (fa.filterAttr.nonEmpty) {
          throw ModelError(s"Filter attributes inside filter attributes not allowed in ${a.ns}.${a.attr}")
        }
        val filterNsAttr = fa.cleanName
        filterAttrVars.get(filterNsAttr).fold {
          filterAttrVars = filterAttrVars + (filterNsAttr -> nsAttr)
        }(_ => throw ModelError(s"Can't refer to ambiguous filter attribute $filterNsAttr"))

        if (filterPath :+ fa.cleanAttr == path :+ a.cleanAttr) {
          throw ModelError(s"Can't filter by the same attribute `${a.name}`")
        } else if (fa.isInstanceOf[Mandatory]) {
          throw ModelError(s"Filter attribute $filterNsAttr pointing to other namespace should be tacit.")
        } else if (filterPath != path && fa.op != V) {
          throw ModelError("Filtering inside cross-namespace attribute filter not allowed.")
        }
      }
    }

    def handleRef(ref: Ref): Unit = {
      if (path.isEmpty) {
        path = List(ref.ns, ref.refAttr, ref.refNs)
      } else {
        path ++= List(ref.refAttr, ref.refNs)
      }
    }
    def handleBackRef(): Unit = {
      path = path.dropRight(2)
    }

    check(elements)

    //    println("---------- x1")
    //    nsAttrPaths.foreach(println)
    //    println("---------- x2")
    //    qualifiedPaths.foreach(println)
    //    //    println("---------- 0")
    //    //    expectedFilterAttrs.foreach(println)
    //    println("---------- 1")
    //    expectedPathFilterAttrs.foreach(println)
    //    println("---------- 2")
    //    directions.foreach(println)

    path = Nil
    qualifiedPath = Nil
    var prevAttrName        = ""
    var prevWasFilterCallee = false
    var i                   = -1
    @tailrec
    def prepare(elements: List[Element], acc: List[Element]): List[Element] = {
      elements match {
        case element :: tail =>
          element match {
            case a: Attr      => prepare(tail, acc ++ prepareAttr(a))
            case r: Ref       => prepare(tail, acc :+ prepareRef(r))
            case b: BackRef   => prepare(tail, acc :+ prepareBackRef(b))
            case n: Nested    => prepare(tail, acc :+ prepareNested(n))
            case n: NestedOpt => prepare(tail, acc :+ prepareNestedOpt(n))
          }
        case Nil             => acc
      }
    }
    def prepareAttr(a: Attr): List[Attr] = {
      i += 1
      val attrName = a.cleanName



      //      println(s"+++++++++++ $prevAttrName  $attrName  $prevWasFilterCallee  $qualifiedPath  $path")

      if (prevWasFilterCallee && prevAttrName == attrName && qualifiedPath != path) {
        // Omit attribute that turned out not to he adjacent
        //        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
        Nil
      } else {
        prevAttrName = a.name
        a.filterAttr.fold {
          prevWasFilterCallee = false
          qualifiedPath = Nil
          if (a.isInstanceOf[AttrSetMan] && filterAttrVars.contains(a.name) && a.op != V) {
            throw ModelError(s"Cardinality-set filter attributes (${a.name}) not allowed to do additional filtering.")
          }
          List(a)
        } { case (_, filterPath, filterAttr) =>
          if (filterAttr.cleanAttr == "id") {
            throw ModelError(noIdFiltering)
          }

          prevWasFilterCallee = true
          qualifiedPath = filterPath

          val filterPathAttr = filterPath :+ filterAttr.cleanAttr

          //          println(s"  ---  $filterPathAttr")

          val filterAttrName      = filterAttr.cleanName
          val qualifiedFilterPath = qualifiedPaths.get(filterPathAttr).fold {
            val nsPaths = nsAttrPaths.getOrElse(filterAttrName,
              throw ModelError(s"Please add missing filter attribute $filterAttrName")
            )
            nsPaths.length match {
              case 1 => nsPaths.head
              case 0 => throw ModelError(s"Unexpectedly found no nsPaths for filter attribute $filterAttrName")
              case _ =>
                val prefixes = nsPaths.distinct.map { tokens =>
                  var prefix = tokens.head
                  var even   = false
                  tokens.foreach {
                    case token if even =>
                      prefix += "." + token.head.toTitleCase + token.tail
                      even = !even
                    case _             => even = !even
                  }
                  prefix + "." + filterAttr.cleanAttr
                }
                throw ModelError(
                  s"Please qualify filter attribute $filterAttrName to an unambiguous path:\n  " +
                    prefixes.mkString("\n  ")
                )
            }
          } { fullPaths =>
            fullPaths.length match {
              case 1                                => fullPaths.head
              case n if n > 1 && path == filterPath => fullPaths.head
              case 0                                =>
                throw ModelError(s"Unexpectedly found no fullPaths for filter attribute $filterAttrName")
              case _                                =>
                throw ModelError(s"Found multiple fullPaths for filter attribute $filterAttrName:\n  " +
                  fullPaths.mkString("\n  ")
                )
            }
          }

          val fullPath = qualifiedFilterPath :+ a.cleanAttr

          //          println("-----------")
          //          println(a)
          //          println("  >>  " + fullPath)
          //          directions.foreach(println)

          val dir = directions.indexOf(fullPath) match {
            case -1          => throw ModelError(s"Unexpectedly couldn't find direction index for $fullPath")
            case j if j < i  => -1
            case j if j == i => 0
            case _           => 1
          }

          //          println(s"########  $i  $qualifiedFilterPath")
          //          println(s"########  ${directions.indexOf(qualifiedFilterPath :+ a.cleanAttr)}")
          //          println(s"########  $dir")

          val qualifiedFilterAttr = a match {
            case a: AttrOneTac => a match {
              case a: AttrOneTacID             => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacString         => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacInt            => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacLong           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacFloat          => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacDouble         => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacBoolean        => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacBigInt         => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacBigDecimal     => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacDate           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacDuration       => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacInstant        => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacLocalDate      => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacLocalTime      => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacLocalDateTime  => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacOffsetTime     => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacOffsetDateTime => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacZonedDateTime  => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacUUID           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacURI            => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacByte           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacShort          => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneTacChar           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
            }

            case a: AttrOneMan => a match {
              case a: AttrOneManID             => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManString         => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManInt            => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManLong           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManFloat          => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManDouble         => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManBoolean        => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManBigInt         => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManBigDecimal     => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManDate           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManDuration       => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManInstant        => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManLocalDate      => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManLocalTime      => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManLocalDateTime  => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManOffsetTime     => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManOffsetDateTime => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManZonedDateTime  => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManUUID           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManURI            => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManByte           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManShort          => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrOneManChar           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
            }

            case a: AttrSetMan => a match {
              case a: AttrSetManID             => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManString         => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManInt            => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManLong           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManFloat          => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManDouble         => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManBoolean        => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManBigInt         => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManBigDecimal     => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManDate           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManDuration       => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManInstant        => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManLocalDate      => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManLocalTime      => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManLocalDateTime  => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManOffsetTime     => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManOffsetDateTime => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManZonedDateTime  => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManUUID           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManURI            => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManByte           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManShort          => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetManChar           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
            }
            case a: AttrSetTac => a match {
              case a: AttrSetTacID             => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacString         => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacInt            => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacLong           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacFloat          => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacDouble         => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacBoolean        => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacBigInt         => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacBigDecimal     => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacDate           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacDuration       => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacInstant        => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacLocalDate      => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacLocalTime      => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacLocalDateTime  => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacOffsetTime     => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacOffsetDateTime => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacZonedDateTime  => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacUUID           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacURI            => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacByte           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacShort          => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
              case a: AttrSetTacChar           => a.copy(filterAttr = Some((dir, qualifiedFilterPath, filterAttr)))
            }
            case other         => other
          }
          List(qualifiedFilterAttr)
        }
      }
    }

    def prepareRef(ref: Ref): Ref = {
      if (path.isEmpty) {
        path = List(ref.ns, ref.refAttr, ref.refNs)
      } else {
        path ++= List(ref.refAttr, ref.refNs)
      }
      ref
    }
    def prepareBackRef(backRef: BackRef): BackRef = {
      path = path.dropRight(2)
      backRef
    }
    def prepareNested(nested: Nested): Nested = {
      path ++= List(nested.ref.refAttr, nested.ref.refNs)
      Nested(nested.ref, prepare(nested.elements, Nil))
    }
    def prepareNestedOpt(nested: NestedOpt): NestedOpt = {
      path ++= List(nested.ref.refAttr, nested.ref.refNs)
      NestedOpt(nested.ref, prepare(nested.elements, Nil))
    }

    prepare(elements, Nil)
  }

  private lazy val noIdFiltering = "Filter attributes not allowed to involve entity ids."

  @tailrec
  final private def resolve(elements: List[Element]): Unit = elements match {
    case element :: tail => element match {
      case a: AttrOne                     =>
        //        if (a.attr == "id" && a.filterAttr.nonEmpty || a.attr != "id" && a.filterAttr.exists(_._3.attr == "id")) {
        if (a.attr == "id" && a.filterAttr.nonEmpty) {
          throw ModelError(noIdFiltering)
        }
        if (a.attr == "id") {
          a match {
            case a: AttrOneMan => resolveAttrOneManID(a); resolve(tail)
            case a: AttrOneTac => resolveAttrOneTacID(a); resolve(tail)
            case _             => throw new Exception("Unexpected optional id")
          }
        } else {
          if (a.owner)
            throw ModelError("Can't query for non-existing ids of embedded documents in MongoDB.")
          a match {
            case a: AttrOneMan => resolveAttrOneMan(a); resolve(tail)
            case a: AttrOneOpt => resolveAttrOneOpt(a); resolve(tail)
            case a: AttrOneTac => resolveAttrOneTac(a); resolve(tail)
          }
        }
      case a: AttrSet                     =>
        if (a.owner)
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