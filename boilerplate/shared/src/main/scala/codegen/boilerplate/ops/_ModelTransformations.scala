package codegen.boilerplate.ops

import codegen.BoilerplateGenBase

object _ModelTransformations extends BoilerplateGenBase("ModelTransformations", "/ops") {

  override val content = {
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.ops
       |
       |import java.net.URI
       |import java.time._
       |import java.util.{Date, UUID}
       |import molecule.base.error.ModelError
       |import molecule.boilerplate.api.Keywords._
       |import molecule.boilerplate.api._
       |import molecule.boilerplate.ast.Model._
       |import scala.annotation.tailrec
       |
       |trait ModelTransformations_ {
       |
       |  def unexpected(element: Element) = throw ModelError("Unexpected element: " + element)
       |
       |  protected def toInt(es: List[Element], kw: Kw): List[Element] = {
       |    val last = es.last match {
       |      case a: AttrOneMan => AttrOneManInt(a.ns, a.attr, Fn(kw.toString), refNs = a.refNs, coord = a.coord)
       |      case a: AttrSetMan => a match {
       |        case _: AttrSetManBoolean =>
       |          if (kw.isInstanceOf[count] || kw.isInstanceOf[countDistinct]) {
       |            // Catch unsupported aggregation of Sets of boolean values
       |            AttrSetManInt(a.ns, a.attr, Fn(kw.toString, Some(-1)), refNs = a.refNs, coord = a.coord)
       |          } else {
       |            AttrSetManInt(a.ns, a.attr, Fn(kw.toString), refNs = a.refNs, coord = a.coord)
       |          }
       |
       |        case _ => AttrSetManInt(a.ns, a.attr, Fn(kw.toString), refNs = a.refNs, coord = a.coord)
       |      }
       |
       |      case a => unexpected(a)
       |    }
       |    es.init :+ last
       |  }
       |
       |  protected def toDouble(es: List[Element], kw: Kw): List[Element] = {
       |    val last = es.last match {
       |      case a: AttrOneMan => AttrOneManDouble(a.ns, a.attr, Fn(kw.toString), refNs = a.refNs, coord = a.coord)
       |      case a: AttrSetMan => AttrSetManDouble(a.ns, a.attr, Fn(kw.toString), refNs = a.refNs, coord = a.coord)
       |      case a             => unexpected(a)
       |    }
       |    es.init :+ last
       |  }
       |
       |  protected def asIs(es: List[Element], kw: Kw, n: Option[Int] = None): List[Element] = {
       |    val last = es.last match {
       |      case a: AttrOneMan => a match {
       |        ${asIs("One")}
       |      }
       |      case a: AttrSetMan => a match {
       |        ${asIs("Set")}
       |      }
       |      case a             => unexpected(a)
       |    }
       |    es.init :+ last
       |  }
       |
       |  protected def addOne[T](es: List[Element], op: Op, vs: Seq[T]): List[Element] = {
       |    val last = es.last match {
       |      case a: AttrOneMan => a match {
       |        ${addOne("Man")}
       |      }
       |      case a: AttrOneTac => a match {
       |        ${addOne("Tac")}
       |      }
       |      case a             => unexpected(a)
       |    }
       |    es.init :+ last
       |  }
       |
       |  protected def addOptOne[T](es: List[Element], op: Op, vs: Option[Seq[T]]): List[Element] = {
       |    val last = es.last match {
       |      case a: AttrOneOpt => a match {
       |        $addOptOne
       |      }
       |      case a             => unexpected(a)
       |    }
       |    es.init :+ last
       |  }
       |
       |  protected def addSet[T](es: List[Element], op: Op, vs: Seq[Set[T]]): List[Element] = {
       |    val last = es.last match {
       |      case a: AttrSetMan => a match {
       |        ${addSet("Man")}
       |      }
       |      case a: AttrSetTac => a match {
       |        ${addSet("Tac")}
       |      }
       |      case a             => unexpected(a)
       |    }
       |    es.init :+ last
       |  }
       |
       |  protected def addOptSet[T](es: List[Element], op: Op, vs: Option[Seq[Set[T]]]): List[Element] = {
       |    val last = es.last match {
       |      case a: AttrSetOpt => a match {
       |        $addOptSet
       |      }
       |      case a             => unexpected(a)
       |    }
       |    es.init :+ last
       |  }
       |
       |  protected def addSort(es: List[Element], sort: String): List[Element] = {
       |    es.size match {
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
       |  }
       |
       |  private def setSort(e: Element, sort: String): Element = {
       |    e match {
       |      case a: AttrOneMan => a match {
       |        ${addSort("Man")}
       |      }
       |      case a: AttrOneOpt => a match {
       |        ${addSort("Opt")}
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
       |        case r: Ref =>
       |          val p = if (path.isEmpty) List(r.ns, r.refAttr, r.refNs) else List(r.refAttr, r.refNs)
       |          resolvePath(tail, path ++ p)
       |        case a: Attr => resolvePath(tail, if (path.isEmpty) List(a.ns) else path)
       |        case other   => throw ModelError("Invalid element in filter attribute path: " + other)
       |      }
       |      case Nil       => path
       |    }
       |  }
       |
       |  protected def filterAttr(es: List[Element], op: Op, filterAttrMolecule: Molecule): List[Element] = {
       |    val filterAttr0 = filterAttrMolecule.elements.last.asInstanceOf[Attr]
       |    val attrs       = es.last match {
       |      case a: Attr =>
       |        val (tacitFilterAttr, adjacent) = if (a.ns == filterAttr0.ns) {
       |          // Rudimentary checked for same current namespace (it's the only information
       |          // we have now during molecule buildup). At least we can rule out if the
       |          // filter attribute is not adjacent to the caller attribute.
       |          // Could point to other branch - have to be checked later.
       |          // If pointing to other branch, the added filterAttr0 should be removed
       |
       |          // Convert adjacent mandatory filter attribute to tacit attribute
       |          val tacitAttr = filterAttr0 match {
       |            case a: AttrOneMan => a match {
       |              case a: AttrOneManID             => AttrOneTacID(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)
       |              ${liftFilterAttr("One")}
       |            }
       |            case a: AttrSetMan => a match {
       |              case a: AttrSetManID             => AttrSetTacID(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord, a.owner)
       |              ${liftFilterAttr("Set")}
       |            }
       |            case other         => other
       |          }
       |          (tacitAttr, List(filterAttr0))
       |        } else (filterAttr0, Nil)
       |
       |        val filterPath         = resolvePath(filterAttrMolecule.elements, Nil)
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
       |          case a: AttrSet => a match {
       |            case a: AttrSetMan => a match {
       |              ${addFilterAttr("Set", "Man")}
       |            }
       |            case a: AttrSetTac => a match {
       |              ${addFilterAttr("Set", "Tac")}
       |            }
       |            case a             => unexpected(a)
       |          }
       |        }
       |        attrWithFilterAttr +: adjacent
       |      case e       => unexpected(e)
       |    }
       |    es.init ++ attrs
       |  }
       |
       |  protected def reverseTopLevelSorting(es: List[Element]): List[Element] = {
       |    es.map {
       |      case attr: AttrOneMan => attr match {
       |        case a@AttrOneManID(_, _, _, _, _, _, _, _, _, Some(sort), _, _)          => a.copy(sort = Some(reverseSort(sort)))
       |        ${reverseTopLevelSorting("Man")}
       |        case a                                                                    => a
       |      }
       |      case attr: AttrOneOpt => attr match {
       |        case a@AttrOneOptID(_, _, _, _, _, _, _, _, _, Some(sort), _, _)          => a.copy(sort = Some(reverseSort(sort)))
       |        ${reverseTopLevelSorting("Opt")}
       |        case a                                                                    => a
       |      }
       |      case other            => other
       |    }
       |  }
       |
       |  private def reverseSort(sort: String): String = sort.head match {
       |    case 'a' => "d" + sort.last
       |    case 'd' => "a" + sort.last
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
      val tpe = if (baseTpe == "ID") "String" else baseTpe
      s"""case a: AttrOne$mode$baseTpe =>
         |          val vs1     = vs.asInstanceOf[Seq[$tpe]]
         |          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
         |            val validator = a.validator.get
         |            vs1.flatMap(v => validator.validate(v))
         |          }
         |          a.copy(op = op, vs = vs1, errors = errors1)""".stripMargin
    }.mkString("\n\n        ")
  }

  private def addOptOne: String = {
    baseTypes.map { baseTpe =>
      val tpe = if (baseTpe == "ID") "String" else baseTpe
      s"""case a: AttrOneOpt$baseTpe =>
         |          val vs1     = vs.asInstanceOf[Option[Seq[$tpe]]]
         |          val errors1 = if (vs1.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
         |            val validator = a.validator.get
         |            vs1.get.flatMap(v => validator.validate(v))
         |          }
         |          a.copy(op = op, vs = vs1, errors = errors1)""".stripMargin
    }.mkString("\n\n        ")
  }

  private def addSet(mode: String): String = {
    baseTypes.map { baseTpe =>
      val tpe = if (baseTpe == "ID") "String" else baseTpe
      s"""case a: AttrSet$mode$baseTpe =>
         |          val sets    = vs.asInstanceOf[Seq[Set[$tpe]]]
         |          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
         |            val validator = a.validator.get
         |            sets.flatMap(set => set.flatMap(v => validator.validate(v)))
         |          }
         |          a.copy(op = op, vs = sets, errors = errors1)""".stripMargin
    }.mkString("\n\n        ")
  }

  private def addOptSet: String = {
    baseTypes.map { baseTpe =>
      val tpe = if (baseTpe == "ID") "String" else baseTpe
      s"""case a: AttrSetOpt$baseTpe =>
         |          val sets    = vs.asInstanceOf[Option[Seq[Set[$tpe]]]]
         |          val errors1 = if (sets.isEmpty || a.validator.isEmpty || a.valueAttrs.nonEmpty) Nil else {
         |            val validator = a.validator.get
         |            sets.get.flatMap(set => set.flatMap(v => validator.validate(v)))
         |          }
         |          a.copy(op = op, vs = sets, errors = errors1)""".stripMargin
    }.mkString("\n\n        ")
  }

  private def addSort(mode: String): String = {
    baseTypesWithSpaces.map { case (baseTpe, space) =>
      s"case a: AttrOne$mode$baseTpe $space=> a.copy(sort = Some(sort))"
    }.mkString("\n        ")
  }

  private def liftFilterAttr(card: String): String = {
    baseTypesWithSpaces.tail.map { case (baseTpe, space) =>
      s"case a: Attr${card}Man$baseTpe $space=> Attr${card}Tac$baseTpe(a.ns, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.refNs, a.sort, a.coord)"
    }.mkString("\n              ")
  }
  private def addFilterAttr(card: String, mode: String): String = {
    baseTypesWithSpaces.map { case (baseTpe, space) =>
      s"case a: Attr$card$mode$baseTpe $space=> a.copy(op = op, filterAttr = Some((-2, filterPath, tacitFilterAttr)))"
    }.mkString("\n              ")
  }

  private def reverseTopLevelSorting(mode: String): String = {
    baseTypesWithSpaces.tail.map { case (baseTpe, space) =>
      s"case a@AttrOne$mode$baseTpe(_, _, _, _, _, _, _, _, _, Some(sort), _) $space=> a.copy(sort = Some(reverseSort(sort)))"
    }.mkString("\n        ")
  }
}
