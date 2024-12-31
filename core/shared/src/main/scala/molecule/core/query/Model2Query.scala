package molecule.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.util.ModelUtils
import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

trait Model2Query extends QueryExpr with ModelUtils {

  private var level         = 1
  private val sortsPerLevel = mutable.Map[Int, List[Int]](1 -> Nil)
  private var hasBinding    = false
  private var hasAggr       = false
  private var hasAggrSet    = false
  private var hasFilterAttr = false

  protected val expectedFilterAttrs = mutable.Set.empty[String]
  protected var optNestedLeafIsSet  = Option.empty[Boolean]

  final var nestedOptRef = false
  final var hasOptRef    = false
  final var isNested     = false
  final var isManNested  = false
  final var isOptNested  = false


  @tailrec
  final protected def resolve(elements: List[Element]): Unit = elements match {
    case element :: tail => element match {
      case a: AttrOne => a.attr match {
        case "id" =>
          if (a.filterAttr.nonEmpty) noIdFiltering()
          a match {
            case a: AttrOneMan => queryIdMan(a); resolve(tail)
            case a: AttrOneTac => queryIdTac(a); resolve(tail)
            case _             => unexpectedElement(a)
          }
        case _    =>
          if (a.filterAttr.exists(_._3.attr == "id")) noIdFiltering()
          a match {
            case a: AttrOneMan => queryAttrOneMan(a); resolve(tail)
            case a: AttrOneTac => queryAttrOneTac(a); resolve(tail)
            case a: AttrOneOpt => queryAttrOneOpt(a); resolve(tail)
          }
      }

      case a: AttrSet if a.refNs.isDefined => a match {
        case a: AttrSetMan => queryRefAttrSetMan(a); resolve(tail)
        case a: AttrSetTac => queryRefAttrSetTac(a); resolve(tail)
        case a: AttrSetOpt => queryRefAttrSetOpt(a); resolve(tail)
      }

      case a: AttrSet => a match {
        case a: AttrSetMan => queryAttrSetMan(a); resolve(tail)
        case a: AttrSetTac => queryAttrSetTac(a); resolve(tail)
        case a: AttrSetOpt => queryAttrSetOpt(a); resolve(tail)
      }

      case a: AttrSeq => a match {
        case a: AttrSeqMan => queryAttrSeqMan(a); resolve(tail)
        case a: AttrSeqTac => queryAttrSeqTac(a); resolve(tail)
        case a: AttrSeqOpt => queryAttrSeqOpt(a); resolve(tail)
      }

      case a: AttrMap => a match {
        case a: AttrMapMan => queryAttrMapMan(a); resolve(tail)
        case a: AttrMapTac => queryAttrMapTac(a); resolve(tail)
        case a: AttrMapOpt => queryAttrMapOpt(a); resolve(tail)
      }

      case ref: Ref                             => queryRef(ref, tail); resolve(tail)
      case backRef: BackRef                     => queryBackRef(backRef, tail); resolve(tail)
      case OptRef(ref, refElements)             => queryOptRef(ref, refElements); resolve(tail)
      case Nested(ref, nestedElements)          => queryNested(ref, nestedElements); resolve(tail)
      case OptNested(nestedRef, nestedElements) => queryOptNested(nestedRef, nestedElements); resolve(tail)
    }
    case Nil             => ()
  }

  def validateQueryModel(
    elements: List[Element],
    addFilterAttr: Option[(List[String], Attr) => Unit] = None,
    optHandleRef: Option[(String, String) => Unit] = None,
    optHandleBackRef: Option[() => Unit] = None,
  ): (List[Element], String, Boolean) = {
    elements match {
      case List(a: Attr) if a.attr == "id" => throw ModelError(
        "Querying for the entity id only is not allowed. " +
          "Please add at least one attribute (can be tacit).")

      case _ if optHandleRef.isDefined && optHandleBackRef.isDefined =>
        val handleRef     = optHandleRef.get
        val handleBackRef = optHandleBackRef.get

        @tailrec
        def validate(elements: List[Element], prevElements: List[Element] = Nil): Unit = {
          elements match {
            case element :: tail =>
              element match {
                case a: Attr =>
                  validateAttr(a)
                  validate(tail, prevElements :+ a)

                case r: Ref =>
                  handleRef(r.refAttr, r.refNs)
                  validate(tail, prevElements)

                case _: BackRef =>
                  handleBackRef.apply()
                  validate(tail, prevElements)

                case OptRef(_, es) =>
                  validate(es ++ tail, prevElements)

                case Nested(r, es) =>
                  handleRef(r.refAttr, r.refNs)
                  validateNested()
                  validate(es, prevElements)

                case OptNested(r, es) =>
                  handleRef(r.refAttr, r.refNs)
                  validateOptNested(prevElements)
                  validate(es, prevElements)
              }
            case Nil             => ()
          }
        }
        validate(elements)

      case _ =>
        @tailrec
        def validate(elements: List[Element], prevElements: List[Element] = Nil): Unit = {
          elements match {
            case element :: tail =>
              element match {
                case a: Attr          => validateAttr(a); validate(tail, prevElements :+ a)
                case OptRef(_, es)    => validate(es ++ tail, prevElements)
                case Nested(_, es)    => validateNested(); validate(es, prevElements)
                case OptNested(_, es) => validateOptNested(prevElements); validate(es, prevElements)
                case _                => validate(tail, prevElements)
              }
            case Nil             => ()
          }
        }
        validate(elements)
    }

    checkSorting()
    checkBinding()

    val initialNs = getInitialNonGenericNs(elements)

    val elements1 = if (hasFilterAttr) {
      checkFilterAttrs(elements, initialNs, addFilterAttr)
    } else elements

    (elements1, initialNs, hasFilterAttr)
  }

  private def validateAttr(a: Attr): Unit = {
    if (a.sort.nonEmpty) {
      sortsPerLevel(level) = sortsPerLevel(level) :+ a.sort.get.substring(1, 2).toInt
    }
    a.filterAttr.foreach(_ => hasFilterAttr = true)
    if (a.isInstanceOf[Mandatory] || a.isInstanceOf[Tacit]) {
      hasBinding = true
    }
  }

  private def validateNested(): Unit = {
    level += 1
    sortsPerLevel += level -> Nil
  }

  private def validateOptNested(prevElements: List[Element]): Unit = {
    level += 1
    sortsPerLevel += level -> Nil
    val prevElementsWithoutId = prevElements.filterNot {
      case a: Attr if a.attr == "id" => true
      case _                         => false
    }
    if (prevElementsWithoutId.length == 1) {
      prevElementsWithoutId.head match {
        case _: AttrOneOpt | _: AttrSetOpt | _: AttrSeqOpt | _: AttrMapOpt =>
          throw ModelError(
            s"Single optional attribute before optional nested data structure " +
              s"is not allowed (id attribute doesn't count)."
          )
        case _                                                             => ()
      }
    }
  }

  private def checkSorting(): Unit = {
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
  }

  private def checkBinding(): Unit = {
    if (!hasBinding) {
      throw ModelError(
        "Query needs at least 1 mandatory/tacit attribute (id not counting)."
      )
    }
  }

  protected def checkAggrOne(): Unit = {
    if (hasAggrSet) {
      noMultiAggrSet()
    }
    hasAggr = true
  }
  protected def checkAggrSet(): Unit = {
    if (hasAggr) {
      noMultiAggrSet()
    }
    hasAggr = true
    hasAggrSet = true
  }

  private def noMultiAggrSet() = throw ModelError(
    "Only a single aggregation is allowed with card-set attributes."
  )

  private def checkFilterAttrs(
    elements: List[Element],
    initialNs: String,
    addFilterAttr: Option[(List[String], Attr) => Unit]
  ): List[Element] = {
    val nsAttrPaths    = mutable.Map.empty[String, List[List[String]]]
    val qualifiedPaths = mutable.Map.empty[List[String], List[List[String]]]
    val directions     = ListBuffer.empty[List[String]]
    var path           = List(initialNs)
    var qualifiedPath  = List(initialNs)
    var filterAttrVars = Map.empty[List[String], String]
    var level          = 0

    @tailrec
    def check(elements: List[Element]): Unit = {
      elements match {
        case element :: tail =>
          element match {
            case a: Attr      => checkAttr(a); check(tail)
            case r: Ref       => handleRef(r); check(tail)
            case _: BackRef   => handleBackRef(); check(tail)
            case n: OptRef    => ???
            case n: Nested    => handleNested(n.ref); check(n.elements ++ tail)
            case n: OptNested => handleNested(n.ref); check(n.elements ++ tail)
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
          throw ModelError(s"Filter attribute $filterNsAttr pointing to other entity should be tacit.")
        } else if (filterPath != path && fa.op != V) {
          throw ModelError("Filtering inside cross-entity attribute filter not allowed.")
        } else {
          // Expect filter attribute in other entity
          expectedFilterAttrs += fa.cleanName
        }

        // Callback (if any) from implementation
        val pathAttr = filterPath :+ fa.cleanAttr
        addFilterAttr.foreach(_(pathAttr, a))

        filterAttrVars.get(pathAttr).fold {
          filterAttrVars = filterAttrVars + (pathAttr.takeRight(2) -> nsAttr)
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
            case r: OptRef    => ???
            case n: Nested    => prepare(tail, acc :+ prepareNested(n))
            case n: OptNested => prepare(tail, acc :+ prepareOptNested(n))
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
          val nsAttr = path.takeRight(1) :+ a.cleanAttr
          if ((a.isInstanceOf[AttrSetMan] || a.isInstanceOf[AttrSeqMan]) && filterAttrVars.contains(nsAttr) && a.op != V) {
            throw ModelError(s"Cardinality-many filter attributes ($attrName) not allowed to do additional filtering.")
          }
          List(a)
        } { case (_, filterPath, filterAttr) =>
          if (filterAttr.cleanAttr == "id") {
            noIdFiltering()
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
              case 0 => throw ModelError(s"Unexpectedly found nsPaths for filter attribute $filterAttrName")
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
            case _ if path == fullFilterAttrPath.init => 0 //  same entity
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

            //            case a: AttrSeq => ???
            //            case a: AttrMap => ???

            case other => other
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
    def prepareOptNested(nested: OptNested): OptNested = {
      path ++= List(nested.ref.refAttr, nested.ref.refNs)
      OptNested(nested.ref, prepare(nested.elements, Nil))
    }

    prepare(elements, Nil)
  }


  def noIdFiltering() = throw ModelError(
    "Filter attributes not allowed to involve entity ids."
  )

  protected def unexpectedElement(element: Element): Nothing =
    throw ModelError("Unexpected element: " + element)

  protected def unexpectedOp(op: Op): Nothing =
    throw ModelError("Unexpected operation: " + op)

  protected def unexpectedKw(kw: String): Nothing =
    throw ModelError("Unexpected keyword: " + kw)


  protected def noMixedNestedModes: Nothing = throw ModelError(
    "Can't mix mandatory/optional nested queries."
  )
  protected def onlyCardOneInsideOptRef(ref: Ref): Nothing = throw ModelError(
    s"Only cardinality-one refs allowed in optional ref queries (${ref.ns}.${ref.refAttr})."
  )
  protected def noCardManyInsideOptRef(): Unit = if (nestedOptRef) {
    throw ModelError(
      "Cardinality-many nesting not allowed inside optional ref."
    )
  }

  protected def noCollectionMatching(attr: Attr): Nothing = {
    noCollectionMatching(attr.cleanName)
  }
  protected def noCollectionMatching(attr: String): Nothing = {
    throw ModelError(s"Matching collections ($attr) not supported in queries.")
  }

  protected def noApplyNothing(attr: Attr): Nothing = {
    val a = attr.cleanName
    throw ModelError(
      s"Applying nothing to mandatory attribute ($a) is reserved for updates to retract."
    )
  }

  protected def noCardManyFilterAttrExpr(attr: Attr): Nothing = {
    throw ModelError(
      s"Cardinality-many filter attributes not allowed to do additional filtering (${attr.name}).")
  }

  protected def checkOnlyOptRef(): Unit = if (hasOptRef) {
    throw ModelError(s"Only further optional refs allowed after optional ref.")
  }

  protected def validateRefNs(ref: Ref, nestedElements: List[Element]): Unit = {
    val nestedNs = nestedElements.head match {
      case a: Attr   => a.ns
      case r: Ref    => r.ns
      case r: OptRef => r.ref.ns
      case other     => unexpectedElement(other)
    }
    if (ref.refNs != nestedNs) {
      val refName = ref.refAttr.capitalize
      throw ModelError(s"`$refName` can only nest to `${ref.refNs}`. Found: `$nestedNs`")
    }
  }
}
