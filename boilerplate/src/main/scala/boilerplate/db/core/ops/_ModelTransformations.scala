package boilerplate.db.core.ops

import boilerplate.db.core.CoreGenBase

object _ModelTransformations extends CoreGenBase("ModelTransformations", "/ops") {

  override val content = {
    s"""// GENERATED CODE ********************************
       |package molecule.db.core.ops
       |
       |import java.net.URI
       |import java.time.*
       |import java.util.{Date, UUID}
       |import molecule.db.base.error.ModelError
       |import molecule.db.core.api.*
       |import molecule.db.core.api.Keywords.*
       |import molecule.db.core.ast.*
       |import scala.annotation.tailrec
       |
       |trait ModelTransformations_ {
       |
       |  private def unexpected(element: Element) = throw ModelError("Unexpected element: " + element)
       |
       |  protected def toInt(dataModel: DataModel, kw: Kw): DataModel = {
       |    val es   = dataModel.elements
       |    val last = es.last match {
       |      case a: AttrOneMan => AttrOneManInt(a.ent, a.attr, Fn(kw.toString), ref = a.ref, coord = a.coord)
       |      case a: AttrSetMan => a match {
       |        case _: AttrSetManBoolean =>
       |          if (kw.isInstanceOf[count] || kw.isInstanceOf[countDistinct]) {
       |            // Catch unsupported aggregation of Sets of boolean values
       |            AttrSetManInt(a.ent, a.attr, Fn(kw.toString, Some(-1)), ref = a.ref, coord = a.coord)
       |          } else {
       |            AttrSetManInt(a.ent, a.attr, Fn(kw.toString), ref = a.ref, coord = a.coord)
       |          }
       |
       |        case _ => AttrSetManInt(a.ent, a.attr, Fn(kw.toString), ref = a.ref, coord = a.coord)
       |      }
       |      case a: AttrSeqMan => AttrSeqManInt(a.ent, a.attr, Fn(kw.toString), ref = a.ref, sort = a.sort, coord = a.coord)
       |      case a: AttrMapMan => AttrMapManInt(a.ent, a.attr, Fn(kw.toString), ref = a.ref, sort = a.sort, coord = a.coord)
       |      case a             => unexpected(a)
       |    }
       |    dataModel.copy(elements = es.init :+ last)
       |  }
       |
       |  protected def toDouble(dataModel: DataModel, kw: Kw): DataModel = {
       |    val es   = dataModel.elements
       |    val last = es.last match {
       |      case a: AttrOneMan => AttrOneManDouble(a.ent, a.attr, Fn(kw.toString), ref = a.ref, coord = a.coord)
       |      case a: AttrSetMan => AttrSetManDouble(a.ent, a.attr, Fn(kw.toString), ref = a.ref, coord = a.coord)
       |      case a: AttrSeqMan => AttrSeqManDouble(a.ent, a.attr, Fn(kw.toString), ref = a.ref, coord = a.coord)
       |      case a: AttrMapMan => AttrMapManDouble(a.ent, a.attr, Fn(kw.toString), ref = a.ref, coord = a.coord)
       |      case a             => unexpected(a)
       |    }
       |    dataModel.copy(elements = es.init :+ last)
       |  }
       |
       |  protected def asIs(dataModel: DataModel, kw: Kw, n: Option[Int] = None): DataModel = {
       |    val es   = dataModel.elements
       |    val last = es.last match {
       |      case a: AttrOneMan => a match {
       |        ${asIs("One")}
       |      }
       |      case a: AttrSetMan => a match {
       |        ${asIs("Set")}
       |      }
       |      case a: AttrSeqMan => a match {
       |        ${asIs("Seq")}
       |      }
       |      case a: AttrMapMan => a match {
       |        ${asIs("Map")}
       |      }
       |      case a             => unexpected(a)
       |    }
       |    dataModel.copy(elements = es.init :+ last)
       |  }
       |
       |  protected def addOne[T](dataModel: DataModel, op: Op, vs: Seq[T], binding: Boolean): DataModel = {
       |    val es    = dataModel.elements
       |    val last  = es.last match {
       |      case a: AttrOneMan => a match {
       |        ${addOne("Man")}
       |      }
       |      case a: AttrOneTac => a match {
       |        ${addOne("Tac")}
       |      }
       |      case a             => unexpected(a)
       |    }
       |    val binds = dataModel.binds + (if binding then 1 else 0)
       |    dataModel.copy(elements = es.init :+ last, binds = binds)
       |  }
       |
       |  protected def addOneOpt[T](dataModel: DataModel, op: Op, v: Option[T]): DataModel = {
       |    val es   = dataModel.elements
       |    val last = es.last match {
       |      case a: AttrOneOpt => a match {
       |        $addOptOne
       |      }
       |      case a             => unexpected(a)
       |    }
       |    dataModel.copy(elements = es.init :+ last)
       |  }
       |
       |  protected def addSet[T](dataModel: DataModel, op: Op, vs: Set[T]): DataModel = {
       |    val es   = dataModel.elements
       |    val last = es.last match {
       |      case a: AttrSetMan => a match {
       |        ${addSet("Man")}
       |      }
       |      case a: AttrSetTac => a match {
       |        ${addSet("Tac")}
       |      }
       |      case a             => unexpected(a)
       |    }
       |    dataModel.copy(elements = es.init :+ last)
       |  }
       |
       |  protected def addSetOpt[T](dataModel: DataModel, op: Op, vs: Option[Set[T]]): DataModel = {
       |    val es   = dataModel.elements
       |    val last = es.last match {
       |      case a: AttrSetOpt => a match {
       |        $addOptSet
       |      }
       |      case a             => unexpected(a)
       |    }
       |    dataModel.copy(elements = es.init :+ last)
       |  }
       |
       |  protected def addSeq[T](dataModel: DataModel, op: Op, vs: Seq[T]): DataModel = {
       |    val es   = dataModel.elements
       |    val last = es.last match {
       |      case a: AttrSeqMan => a match {
       |        ${addSeq("Man")}
       |
       |        case a: AttrSeqManByte => ???
       |      }
       |      case a: AttrSeqTac => a match {
       |        ${addSeq("Tac")}
       |
       |        case a: AttrSeqTacByte => ???
       |      }
       |      case a             => unexpected(a)
       |    }
       |    dataModel.copy(elements = es.init :+ last)
       |  }
       |
       |  protected def addSeqOpt[T](dataModel: DataModel, op: Op, vs: Option[Seq[T]]): DataModel = {
       |    val es   = dataModel.elements
       |    val last = es.last match {
       |      case a: AttrSeqOpt => a match {
       |        $addOptSeq
       |
       |        case a: AttrSeqOptByte => ???
       |      }
       |      case a             => unexpected(a)
       |    }
       |    dataModel.copy(elements = es.init :+ last)
       |  }
       |
       |  protected def addBAr[T](dataModel: DataModel, op: Op, ba: Array[T]): DataModel = {
       |    val es          = dataModel.elements
       |    val newElements = es.init :+ (es.last match {
       |      case a: AttrSeqManByte => a.copy(op = op, vs = ba.asInstanceOf[Array[Byte]])
       |      case a: AttrSeqTacByte => a.copy(op = op, vs = ba.asInstanceOf[Array[Byte]])
       |      case e                 => throw ModelError("Unexpected Element for adding byte array: " + e)
       |    })
       |    dataModel.copy(elements = newElements)
       |  }
       |
       |  protected def addBArOpt[T](dataModel: DataModel, op: Op, optBA: Option[Array[T]]): DataModel = {
       |    val es          = dataModel.elements
       |    val newElements = es.init :+ (es.last match {
       |      case a: AttrSeqOptByte => a.copy(op = op, vs = optBA.asInstanceOf[Option[Array[Byte]]])
       |      case e                 => throw ModelError("Unexpected Element for adding byte array: " + e)
       |    })
       |    dataModel.copy(elements = newElements)
       |  }
       |
       |  protected def addMap[T](dataModel: DataModel, op: Op, vs: Map[String, T]): DataModel = {
       |    val es   = dataModel.elements
       |    val last = es.last match {
       |      case a: AttrMapMan => a match {
       |        ${addMap("Man")}
       |      }
       |      case a: AttrMapTac => a match {
       |        ${addMap("Tac")}
       |      }
       |      case a             => unexpected(a)
       |    }
       |    dataModel.copy(elements = es.init :+ last)
       |  }
       |
       |  protected def addMapKs(dataModel: DataModel, op: Op, ks: Seq[String]): DataModel = {
       |    val es   = dataModel.elements
       |    val last = es.last match {
       |      case a: AttrMapMan => a match {
       |        ${addMapKs("Man")}
       |      }
       |      case a: AttrMapOpt => a match {
       |        ${addMapKs("Opt")}
       |      }
       |      case a: AttrMapTac => a match {
       |        ${addMapKs("Tac")}
       |      }
       |      case a             => unexpected(a)
       |    }
       |    dataModel.copy(elements = es.init :+ last)
       |  }
       |
       |  protected def addMapVs[T](dataModel: DataModel, op: Op, vs: Seq[T]): DataModel = {
       |    val es   = dataModel.elements
       |    val last = es.last match {
       |      case a: AttrMapMan => a match {
       |        ${addMapVs("Man")}
       |      }
       |      case a: AttrMapTac => a match {
       |        ${addMapVs("Tac")}
       |      }
       |      case a             => unexpected(a)
       |    }
       |    dataModel.copy(elements = es.init :+ last)
       |  }
       |
       |  protected def addMapOpt[T](dataModel: DataModel, op: Op, vs: Option[Map[String, T]]): DataModel = {
       |    val es   = dataModel.elements
       |    val last = es.last match {
       |      case a: AttrMapOpt => a match {
       |        $addMapOpt
       |      }
       |      case a             => unexpected(a)
       |    }
       |    dataModel.copy(elements = es.init :+ last)
       |  }
       |
       |  protected def addSort(dataModel: DataModel, sort: String): DataModel = {
       |    val es             = dataModel.elements
       |    val sortedElements = es.size match {
       |      case 1 =>
       |        List(setSort(es.last, sort))
       |      case 2 =>
       |        val (first, last) = (es.head, es.last)
       |        first match {
       |          case attr: Attr if attr.filterAttr.nonEmpty => List(setSort(first, sort), last)
       |          case _                                      => List(first, setSort(last, sort))
       |        }
       |
       |      case _ =>
       |        val (prev, last) = (es.init.last, es.last)
       |        val sorted       = prev match {
       |          case attr: Attr if attr.filterAttr.nonEmpty => List(setSort(prev, sort), last)
       |          case _                                      => List(prev, setSort(last, sort))
       |        }
       |        es.dropRight(2) ++ sorted
       |    }
       |    dataModel.copy(elements = sortedElements)
       |  }
       |
       |  private def setSort(e: Element, sort: String): Element = {
       |    e match {
       |      case a: AttrOneMan => a match {
       |        ${addSort("One", "Man")}
       |      }
       |      case a: AttrOneOpt => a match {
       |        ${addSort("One", "Opt")}
       |      }
       |
       |      case a: AttrSetMan => a match {
       |        ${addSort("Set", "Man")}
       |      }
       |      case a: AttrSetOpt => a match {
       |        ${addSort("Set", "Opt")}
       |      }
       |
       |      case a: AttrSeqMan => a match {
       |        ${addSort("Seq", "Man")}
       |      }
       |      case a: AttrSeqOpt => a match {
       |        ${addSort("Seq", "Opt")}
       |      }
       |
       |      case e => e
       |    }
       |  }
       |
       |  @tailrec
       |  private def resolvePath(es: List[Element], path: List[String]): List[String] = {
       |    es match {
       |      case e :: tail => e match {
       |        case r: Ref    =>
       |          val p = if (path.isEmpty) List(r.ent, r.refAttr, r.ref) else List(r.refAttr, r.ref)
       |          resolvePath(tail, path ++ p)
       |        case r: OptRef =>
       |          ???
       |        case a: Attr   => resolvePath(tail, if (path.isEmpty) List(a.ent) else path)
       |        case other     => throw ModelError("Invalid element in filter attribute path: " + other)
       |      }
       |      case Nil       => path
       |    }
       |  }
       |
       |  protected def filterAttr(dataModel: DataModel, op: Op, filterAttrMolecule: Molecule): DataModel = {
       |    val es          = dataModel.elements
       |    val filterAttr0 = filterAttrMolecule.dataModel.elements.last.asInstanceOf[Attr]
       |    val attrs       = es.last match {
       |      case a: Attr =>
       |        val (tacitFilterAttr, adjacent) = if (a.ent == filterAttr0.ent) {
       |          // Rudimentary checked for same current entity (it's the only information
       |          // we have now during molecule buildup). At least we can rule out if the
       |          // filter attribute is not adjacent to the caller attribute.
       |          // Could point to other branch - have to be checked later.
       |          // If pointing to other branch, the added filterAttr0 should be removed
       |
       |          // Convert adjacent mandatory filter attribute to tacit attribute
       |          val tacitAttr = filterAttr0 match {
       |            case a: AttrOneMan => a match {
       |              ${liftFilterAttr("One")}
       |            }
       |            case a: AttrSetMan => a match {
       |              ${liftFilterAttr("Set")}
       |            }
       |            case a: AttrSeqMan => a match {
       |              ${liftFilterAttr("Seq")}
       |            }
       |            case a: AttrMapMan => a match {
       |              ${liftFilterAttr("Map")}
       |            }
       |            case other         => other
       |          }
       |          (tacitAttr, List(filterAttr0))
       |        } else (filterAttr0, Nil)
       |
       |        val filterPath         = resolvePath(filterAttrMolecule.dataModel.elements, Nil)
       |        val attrWithFilterAttr = a match {
       |          case a: AttrOne => a match {
       |            case a: AttrOneMan => a match {
       |              // -2 is just a dummy value until we can resolve the direction to either -1, 0 or 1
       |              ${addFilterAttr("One", "Man")}
       |            }
       |            case a: AttrOneTac => a match {
       |              ${addFilterAttr("One", "Tac")}
       |            }
       |            case a             => unexpected(a)
       |          }
       |
       |          case a: AttrSet => a match {
       |            case a: AttrSetMan => a match {
       |              ${addFilterAttr("Set", "Man")}
       |            }
       |            case a: AttrSetTac => a match {
       |              ${addFilterAttr("Set", "Tac")}
       |            }
       |            case a             => unexpected(a)
       |          }
       |
       |          case a: AttrSeq => a match {
       |            case a: AttrSeqMan => a match {
       |              ${addFilterAttr("Seq", "Man")}
       |            }
       |            case a: AttrSeqTac => a match {
       |              ${addFilterAttr("Seq", "Tac")}
       |            }
       |            case a             => unexpected(a)
       |          }
       |
       |          case a: AttrMap => a match {
       |            case a: AttrMapMan => a match {
       |              ${addFilterAttr("Map", "Man")}
       |            }
       |            case a: AttrMapTac => a match {
       |              ${addFilterAttr("Map", "Tac")}
       |            }
       |            case a             => unexpected(a)
       |          }
       |        }
       |        attrWithFilterAttr +: adjacent
       |      case e       => unexpected(e)
       |    }
       |    dataModel.copy(elements = es.init ++ attrs)
       |  }
       |
       |  protected def reverseTopLevelSorting(es: List[Element]): List[Element] = {
       |    es.map {
       |      case attr: AttrOneMan => attr match {
       |        ${reverseTopLevelSorting("Man")}
       |        case a                                                                       => a
       |      }
       |      case attr: AttrOneOpt => attr match {
       |        ${reverseTopLevelSorting("Opt")}
       |        case a                                                                       => a
       |      }
       |      case other            => other
       |    }
       |  }
       |
       |  private def reverseSort(sort: String): String = sort.head match {
       |    case 'a' => "d" + sort.last
       |    case 'd' => "a" + sort.last
       |  }
       |
       |  protected def topLevelAttrCount(elements: List[Element], count: Int = 0): Int = {
       |    elements match {
       |      case Nil       => count
       |      case e :: tail => e match {
       |        case a: AttrOne       => a match {
       |          case _: AttrOneMan => topLevelAttrCount(tail, count + 1)
       |          case _: AttrOneOpt => topLevelAttrCount(tail, count + 1)
       |          case _             => topLevelAttrCount(tail, count)
       |        }
       |        case a: AttrSet       => a match {
       |          case _: AttrSetMan => topLevelAttrCount(tail, count + 1)
       |          case _: AttrSetOpt => topLevelAttrCount(tail, count + 1)
       |          case _             => topLevelAttrCount(tail, count)
       |        }
       |        case a: AttrSeq       => a match {
       |          case _: AttrSeqMan => topLevelAttrCount(tail, count + 1)
       |          case _: AttrSeqOpt => topLevelAttrCount(tail, count + 1)
       |          case _             => topLevelAttrCount(tail, count)
       |        }
       |        case a: AttrMap       => a match {
       |          case _: AttrMapMan => topLevelAttrCount(tail, count + 1)
       |          case _: AttrMapOpt => topLevelAttrCount(tail, count + 1)
       |          case _             => topLevelAttrCount(tail, count)
       |        }
       |        case _: Ref           => topLevelAttrCount(tail, count)
       |        case _: OptRef        => topLevelAttrCount(tail, count)
       |        case _: OptEntity     => topLevelAttrCount(tail, count)
       |        case _: BackRef       => topLevelAttrCount(tail, count)
       |        case Nested(_, es)    => topLevelAttrCount(tail, count + countNested(es))
       |        case OptNested(_, es) => topLevelAttrCount(tail, count + countNested(es))
       |      }
       |    }
       |  }
       |
       |  private def countNested(elements: List[Element]): Int = {
       |    topLevelAttrCount(elements, 0)
       |  }
       |}""".stripMargin
  }

  private def asIs(card: String): String = {
    baseTypesWithSpaces.map {
      case (baseTpe, space) => s"case a: Attr${card}Man$baseTpe $space=> a.copy(op = Fn(kw.toString, n))"
    }.mkString("\n        ")
  }

  private def addOne(mode: String): String = {
    baseTypes.map { baseTpe =>
      val tpe = if (baseTpe == "ID") "Long" else baseTpe
      s"""case a: AttrOne$mode$baseTpe =>
         |          if binding then a.copy(op = op, binding = true) else {
         |            val vs1     = vs.asInstanceOf[Seq[$tpe]]
         |            val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
         |              val validator = a.validator.get
         |              vs1.flatMap(v => validator.validate(v))
         |            }
         |            a.copy(op = op, vs = vs1, errors = errors1)
         |          }""".stripMargin
    }.mkString("\n\n        ")
  }

  private def addOptOne: String = {
    baseTypes.map { baseTpe =>
      val tpe = if (baseTpe == "ID") "Long" else baseTpe
      s"""case a: AttrOneOpt$baseTpe =>
         |          val v1      = v.asInstanceOf[Option[$tpe]]
         |          val errors1 = if (v1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
         |            a.validator.get.validate(v1.get)
         |          }
         |          a.copy(op = op, vs = v1.map(Seq(_)), errors = errors1)""".stripMargin
    }.mkString("\n\n        ")
  }

  private def addSet(mode: String): String = {
    baseTypes.map { baseTpe =>
      val tpe = if (baseTpe == "ID") "Long" else baseTpe
      s"""case a: AttrSet$mode$baseTpe =>
         |          val set     = vs.asInstanceOf[Set[$tpe]]
         |          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
         |            val validator = a.validator.get
         |            set.toSeq.flatMap(v => validator.validate(v))
         |          }
         |          a.copy(op = op, vs = set, errors = errors1)""".stripMargin
    }.mkString("\n\n        ")
  }

  private def addOptSet: String = {
    baseTypes.map { baseTpe =>
      val tpe = if (baseTpe == "ID") "Long" else baseTpe
      s"""case a: AttrSetOpt$baseTpe =>
         |          val set     = vs.asInstanceOf[Option[Set[$tpe]]]
         |          val errors1 = if (set.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
         |            val validator = a.validator.get
         |            set.get.toSeq.flatMap(v => validator.validate(v))
         |          }
         |          a.copy(op = op, vs = set, errors = errors1)""".stripMargin
    }.mkString("\n\n        ")
  }

  private def addSeq(mode: String): String = {
    baseTypes.filterNot(_ == "Byte").map { baseTpe =>
      val tpe = if (baseTpe == "ID") "Long" else baseTpe
      s"""case a: AttrSeq$mode$baseTpe =>
         |          val seq     = vs.asInstanceOf[Seq[$tpe]]
         |          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
         |            val validator = a.validator.get
         |            seq.flatMap(v => validator.validate(v))
         |          }
         |          a.copy(op = op, vs = seq, errors = errors1)""".stripMargin
    }.mkString("\n\n        ")
  }

  private def addOptSeq: String = {
    baseTypes.filterNot(_ == "Byte").map { baseTpe =>
      val tpe = if (baseTpe == "ID") "Long" else baseTpe
      s"""case a: AttrSeqOpt$baseTpe =>
         |          val seq     = vs.asInstanceOf[Option[Seq[$tpe]]]
         |          val errors1 = if (seq.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
         |            val validator = a.validator.get
         |            seq.get.flatMap(v => validator.validate(v))
         |          }
         |          a.copy(op = op, vs = seq, errors = errors1)""".stripMargin
    }.mkString("\n\n        ")
  }

  private def addMap(mode: String): String = {
    baseTypes.map { baseTpe =>
      val tpe = if (baseTpe == "ID") "Long" else baseTpe
      s"""case a: AttrMap$mode$baseTpe =>
         |          val newMap  = vs.asInstanceOf[Map[String, $tpe]]
         |          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
         |            val validator = a.validator.get
         |            newMap.values.toSeq.flatMap(validator.validate)
         |          }
         |          a.copy(op = op, map = newMap, errors = errors1)""".stripMargin
    }.mkString("\n\n        ")
  }

  private def addMapKs(mode: String): String = {
    baseTypesWithSpaces.map { case (baseTpe, space) =>
      s"""case a: AttrMap$mode$baseTpe $space=> a.copy(op = op, keys = ks)""".stripMargin
    }.mkString("\n        ")
  }

  private def addMapVs(mode: String): String = {
    baseTypesWithSpaces.map { case (baseTpe, space) =>
      val tpe = if (baseTpe == "ID") "Long" else baseTpe
      s"""case a: AttrMap$mode$baseTpe $space=> a.copy(op = op, values = vs.asInstanceOf[Seq[$tpe]])""".stripMargin
    }.mkString("\n        ")
  }

  private def addMapOpt: String = {
    baseTypes.map { baseTpe =>
      val tpe = if (baseTpe == "ID") "Long" else baseTpe
      s"""case a: AttrMapOpt$baseTpe =>
         |          val newMap  = vs.asInstanceOf[Option[Map[String, $tpe]]]
         |          val errors1 = if (newMap.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
         |            val validator = a.validator.get
         |            newMap.get.values.toSeq.flatMap(validator.validate)
         |          }
         |          a.copy(op = op, map = newMap, errors = errors1)""".stripMargin
    }.mkString("\n\n        ")
  }

  private def addSort(card: String, mode: String): String = {
    baseTypesWithSpaces.map { case (baseTpe, space) =>
      s"case a: Attr$card$mode$baseTpe $space=> a.copy(sort = Some(sort))"
    }.mkString("\n        ")
  }

  private def liftFilterAttr(card: String): String = {
    baseTypesWithSpaces.map {
      case (baseTpe, space) if card == "Map" =>
        s"case a: Attr${card}Man$baseTpe $space=> Attr${card}Tac$baseTpe(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)"

      case (baseTpe, space) =>
        s"case a: Attr${card}Man$baseTpe $space=> Attr${card}Tac$baseTpe(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)"
    }.mkString("\n              ")
  }
  private def addFilterAttr(card: String, mode: String): String = {
    baseTypesWithSpaces.map { case (baseTpe, space) =>
      s"case a: Attr$card$mode$baseTpe $space=> a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))"
    }.mkString("\n              ")
  }

  private def reverseTopLevelSorting(mode: String): String = {
    baseTypesWithSpaces.map { case (baseTpe, space) =>
      s"case a@AttrOne$mode$baseTpe(_, _, _, _, _, _, _, _, _, Some(sort), _, _) $space=> a.copy(sort = Some(reverseSort(sort)))"
    }.mkString("\n        ")
  }

  private def clean(card: String, mode: String): String = {
    baseTypesWithSpaces.map { case (baseTpe, space) =>
      s"case a: Attr$card$mode$baseTpe $space=> a.copy(op = V)"
    }.mkString("\n            ")
  }
}
