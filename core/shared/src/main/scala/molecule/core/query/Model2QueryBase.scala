package molecule.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.util.ModelUtils
import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

trait Model2QueryBase extends ModelUtils {

  private var level         = 1
  private val sortsPerLevel = mutable.Map[Int, List[Int]](1 -> Nil)

  def validateQueryModel(
    elements: List[Element],
    addFilterAttr: Option[(String, Attr) => Unit] = None
  ): (List[Element], String, Boolean) = {
    var hasFilterAttr = false
    // Generic validation of model for queries

    // We don't do this validation in ModelTransformations_ since we want to catch
    // exceptions within the api action call and not already while composing molecules.
    @tailrec
    def validate(elements: List[Element], prevElements: List[Element] = Nil): Unit = {
      elements match {
        case element :: tail =>
          element match {
            case a: Attr          => validateAttr(a); validate(tail, prevElements :+ a)
            case Nested(_, es)    => validateNested(es, prevElements)
            case NestedOpt(_, es) => validateNestedOpt(es, prevElements)
            case _                => validate(tail, prevElements)
          }
        case Nil             => ()
      }
    }

    def validateAttr(a: Attr): Unit = {
      if (a.sort.nonEmpty) {
        sortsPerLevel(level) = sortsPerLevel(level) :+ a.sort.get.substring(1, 2).toInt
      }
      a.filterAttr.foreach(_ => hasFilterAttr = true)
    }

    def validateNested(es: List[Element], prevElements: List[Element]): Unit = {
      level += 1
      sortsPerLevel += level -> Nil
      // Nested is the last element, so we can just validate next level elements
      validate(es, prevElements)
    }

    def validateNestedOpt(es: List[Element], prevElements: List[Element]): Unit = {
      level += 1
      sortsPerLevel += level -> Nil
      if (prevElements.length == 1) {
        prevElements.head match {
          case _: AttrOneOpt => throw ModelError(
            s"Single optional attribute before optional nested data structure is not allowed."
          )
          case _             => ()
        }
      }
      // Nested is the last element, so we can just validate next level elements
      validate(es, prevElements)
    }

    // Traverse model
    elements match {
      case List(a: Attr) if a.attr == "id" => throw ModelError(
        "Querying for the entity id only is not allowed. " +
          "Please add at least one attribute (can be tacit).")

      case _ => validate(elements)
    }

    sortsPerLevel.foreach {
      case (_, Nil)         => ()
      case (level, indexes) => indexes.sorted match {
        case Nil                    =>
        case List(1)                =>
        case List(6)                =>
        case List(1, 2)             =>
        case List(1, 6)             =>
        case List(1, 2, 3)          =>
        case List(1, 2, 6)          =>
        case List(1, 2, 3, 4)       =>
        case List(1, 2, 3, 6)       =>
        case List(1, 2, 3, 4, 5)    =>
        case List(1, 2, 3, 4, 6)    =>
        case List(1, 2, 3, 4, 5, 6) =>
        case other                  =>
          val levelMsg = if (level == 1) "" else " on level " + level
          throw ModelError(
            s"Sort index 1 should be present and additional indexes continuously increase (in any order). " +
              s"Found non-unique sort index(es)$levelMsg: " + other.mkString(", ")
          )
      }
    }

    val initialNs = getInitialNonGenericNs(elements)

    val elements1 = if (hasFilterAttr) {
      checkFilterAttrs(elements, initialNs, addFilterAttr)
    } else elements

    (elements1, initialNs, hasFilterAttr)
  }


  private def checkFilterAttrs(
    elements: List[Element],
    initialNs: String,
    addFilterAttr: Option[(String, Attr) => Unit]
  ): List[Element] = {
    val nsAttrPaths    = mutable.Map.empty[String, List[List[String]]]
    val qualifiedPaths = mutable.Map.empty[List[String], List[List[String]]]
    val directions     = ListBuffer.empty[List[String]]
    var path           = List(initialNs)
    var qualifiedPath  = List(initialNs)
    var filterAttrVars = Map.empty[String, String]
    var level          = 0

    @tailrec
    def check(elements: List[Element]): Unit = {
      elements match {
        case element :: tail =>
          element match {
            case a: Attr      => checkAttr(a); check(tail)
            case r: Ref       => handleRef(r); check(tail)
            case _: BackRef   => handleBackRef(); check(tail)
            case n: Nested    => handleNested(n.ref); check(n.elements ++ tail)
            case n: NestedOpt => handleNested(n.ref); check(n.elements ++ tail)
          }
        case Nil             => ()
      }
    }

    def checkAttr(a: Attr): Unit = {
      val nsAttr = a.cleanName
      nsAttrPaths(nsAttr) = nsAttrPaths.get(nsAttr).fold(List(path)) {
        case paths if !paths.contains(path) => paths :+ path
        case paths                          => paths
      }

      qualifiedPath = path :+ a.cleanAttr
      qualifiedPaths(qualifiedPath) = qualifiedPaths.get(qualifiedPath).fold(List(path)) {
        case paths if !paths.contains(path) => paths :+ path
        case paths                          => paths
      }

      directions += qualifiedPath

      if (a.filterAttr.nonEmpty) {
        val (_, filterPath, fa) = a.filterAttr.get
        val filterNsAttr        = fa.cleanName

        if (fa.filterAttr.nonEmpty) {
          throw ModelError(s"Filter attributes inside filter attributes not allowed in ${a.ns}.${a.attr}")
        } else if (filterPath :+ fa.cleanAttr == path :+ a.cleanAttr) {
          throw ModelError(s"Can't filter by the same attribute `${a.name}`")
        } else if (fa.isInstanceOf[Mandatory]) {
          throw ModelError(s"Filter attribute $filterNsAttr pointing to other namespace should be tacit.")
        } else if (filterPath != path && fa.op != V) {
          throw ModelError("Filtering inside cross-namespace attribute filter not allowed.")
        }

        // Callback (if any) from implementation
        addFilterAttr.foreach(_(filterNsAttr, a))

        filterAttrVars.get(filterNsAttr).fold {
          filterAttrVars = filterAttrVars + (filterNsAttr -> nsAttr)
        }(_ => throw ModelError(s"Can't refer to ambiguous filter attribute $filterNsAttr"))
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
    def handleNested(ref: Ref): Unit = {
      level += 1
      handleRef(ref)
    }

    check(elements)

    path = List(initialNs)
    qualifiedPath = List(initialNs)
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
      if (prevWasFilterCallee && prevAttrName == attrName && qualifiedPath != path) {
        // Omit attribute that turned out not to he adjacent
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

          val filterPathAttr      = filterPath :+ filterAttr.cleanAttr
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

          val fullFilterAttrPath  = qualifiedFilterPath :+ filterAttr.cleanAttr
          val dir                 = directions.indexOf(fullFilterAttrPath) match {
            case -1                                   =>
              throw ModelError(s"Unexpectedly couldn't find direction index for $fullFilterAttrPath")
            case _ if path == fullFilterAttrPath.init => 0 //  same namespace
            case j if j < i                           => -1 // filter attribute points backwards
            case _                                    => 1 //  filter attribute points forward
          }
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

            case a: AttrSet =>
              if (level != 0) {
                throw ModelError("Card-Set filter attributes not allowed in nested molecules.")
              }
              a match {
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
            case other      => other
          }
          List(qualifiedFilterAttr)
        }
      }
    }

    def prepareRef(ref: Ref): Ref = {
      path ++= List(ref.refAttr, ref.refNs)
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

  lazy val noIdFiltering = "Filter attributes not allowed to involve entity ids."
}
