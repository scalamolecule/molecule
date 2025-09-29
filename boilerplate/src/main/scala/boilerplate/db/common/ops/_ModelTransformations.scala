package boilerplate.db.common.ops

import boilerplate.db.common.DbCommonBase

object _ModelTransformations extends DbCommonBase("ModelTransformations", "/ops") {

  override val content = {
    s"""// GENERATED CODE ********************************
       |package molecule.db.common.ops
       |
       |import java.net.URI
       |import java.time.*
       |import java.util.{Date, UUID}
       |import scala.annotation.tailrec
       |import molecule.core.dataModel.*
       |import molecule.core.dataModel.Keywords.*
       |import molecule.core.error.ModelError
       |import molecule.db.common.api.Molecule
       |import molecule.db.common.util.ModelUtils
       |
       |object ModelTransformations_ extends ModelTransformations_
       |
       |trait ModelTransformations_ extends ModelUtils {
       |
       |  private def unexpected(element: Element) = throw ModelError("Unexpected element: " + element)
       |
       |  def attrsOnly(dataModel: DataModel): List[Attr] = dataModel.elements.map {
       |    case a: Attr => a
       |    case _       => throw ModelError("Only attributes allowed.")
       |  }
       |
       |  def addOptRef(self: Molecule, optRef: Molecule): DataModel = {
       |    val dataModel       = self.dataModel
       |    val optRefDataModel = optRef.dataModel
       |    DataModel(
       |      dataModel.elements.init :+ OptRef(dataModel.elements.last.asInstanceOf[Ref], optRefDataModel.elements),
       |      dataModel.attrIndexes ++ optRefDataModel.attrIndexes,
       |      binds = dataModel.binds + optRefDataModel.binds
       |    )
       |  }
       |
       |  private def getSortedTacitId(ns: String) = {
       |    // Add sorting by entity id out of range from up to 5 custom sorts
       |    AttrOneTacID(ns, "id", V, Seq(), None, None, Nil, Nil, None, Some("a6"), false, List(0, 0))
       |  }
       |
       |  def addNested(self: Molecule, nestedMolecule: Molecule): DataModel = {
       |    val dataModel       = self.dataModel
       |    val nestedDataModel = nestedMolecule.dataModel
       |    val ns              = getInitialEntity(dataModel.elements.reverse)
       |    val initialElements = dataModel.elements.init :+ getSortedTacitId(ns)
       |    DataModel(
       |      initialElements :+ Nested(dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
       |      dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
       |      binds = dataModel.binds + nestedDataModel.binds
       |    )
       |  }
       |
       |  def addNestedJoin(self: Molecule, nestedMolecule: Molecule): DataModel = {
       |    val dataModel       = self.dataModel
       |    val nestedDataModel = nestedMolecule.dataModel
       |    val ent2join        = dataModel.elements.init.last
       |    val join2ref        = dataModel.elements.last
       |    val ns              = getInitialEntity(dataModel.elements.dropRight(2).reverse)
       |    val initialElements = dataModel.elements.dropRight(2) :+ getSortedTacitId(ns)
       |    DataModel(
       |      initialElements :+ Nested(ent2join.asInstanceOf[Ref], join2ref :: nestedDataModel.elements),
       |      dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
       |      binds = dataModel.binds + nestedDataModel.binds
       |    )
       |  }
       |
       |  def addOptNested(self: Molecule, nestedMolecule: Molecule): DataModel = {
       |    val dataModel       = self.dataModel
       |    val nestedDataModel = nestedMolecule.dataModel
       |    DataModel(
       |      dataModel.elements.init :+ OptNested(dataModel.elements.last.asInstanceOf[Ref], nestedDataModel.elements),
       |      dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
       |      binds = dataModel.binds + nestedDataModel.binds
       |    )
       |  }
       |
       |  def addOptNestedJoin(self: Molecule, nestedMolecule: Molecule): DataModel = {
       |    val dataModel       = self.dataModel
       |    val nestedDataModel = nestedMolecule.dataModel
       |    val ent2join        = dataModel.elements.init.last
       |    val join2ref        = dataModel.elements.last
       |    DataModel(
       |      dataModel.elements.dropRight(2) :+ OptNested(ent2join.asInstanceOf[Ref], join2ref :: nestedDataModel.elements),
       |      dataModel.attrIndexes ++ nestedDataModel.attrIndexes,
       |      binds = dataModel.binds + nestedDataModel.binds
       |    )
       |  }
       |
       |  private def getFn(a: AttrOneMan, kw: Kw, n: Option[Int] = None): Fn = a match {
       |    case a: AttrOneManID             => Fn("Long", kw.toString, n)
       |    case a: AttrOneManString         => Fn("String", kw.toString, n)
       |    case a: AttrOneManInt            => Fn("Int", kw.toString, n)
       |    case a: AttrOneManLong           => Fn("Long", kw.toString, n)
       |    case a: AttrOneManFloat          => Fn("Float", kw.toString, n)
       |    case a: AttrOneManDouble         => Fn("Double", kw.toString, n)
       |    case a: AttrOneManBoolean        => Fn("Boolean", kw.toString, n)
       |    case a: AttrOneManBigInt         => Fn("BigInt", kw.toString, n)
       |    case a: AttrOneManBigDecimal     => Fn("BigDecimal", kw.toString, n)
       |    case a: AttrOneManDate           => Fn("Date", kw.toString, n)
       |    case a: AttrOneManDuration       => Fn("Duration", kw.toString, n)
       |    case a: AttrOneManInstant        => Fn("Instant", kw.toString, n)
       |    case a: AttrOneManLocalDate      => Fn("LocalDate", kw.toString, n)
       |    case a: AttrOneManLocalTime      => Fn("LocalTime", kw.toString, n)
       |    case a: AttrOneManLocalDateTime  => Fn("LocalDateTime", kw.toString, n)
       |    case a: AttrOneManOffsetTime     => Fn("OffsetTime", kw.toString, n)
       |    case a: AttrOneManOffsetDateTime => Fn("OffsetDateTime", kw.toString, n)
       |    case a: AttrOneManZonedDateTime  => Fn("ZonedDateTime", kw.toString, n)
       |    case a: AttrOneManUUID           => Fn("UUID", kw.toString, n)
       |    case a: AttrOneManURI            => Fn("URI", kw.toString, n)
       |    case a: AttrOneManByte           => Fn("Byte", kw.toString, n)
       |    case a: AttrOneManShort          => Fn("Short", kw.toString, n)
       |    case a: AttrOneManChar           => Fn("Char", kw.toString, n)
       |  }
       |
       |  def toInt(dataModel: DataModel, kw: Kw): DataModel = {
       |    val es   = dataModel.elements
       |    val last = es.last match {
       |      case a: AttrOneMan => AttrOneManInt(a.ent, a.attr, getFn(a, kw), ref = a.ref, coord = a.coord)
       |      case a             => unexpected(a)
       |    }
       |    dataModel.copy(elements = es.init :+ last)
       |  }
       |
       |  def toDouble(dataModel: DataModel, kw: Kw): DataModel = {
       |    val es   = dataModel.elements
       |    val last = es.last match {
       |      case a: AttrOneMan => AttrOneManDouble(a.ent, a.attr, getFn(a, kw), ref = a.ref, coord = a.coord)
       |      case a             => unexpected(a)
       |    }
       |    dataModel.copy(elements = es.init :+ last)
       |  }
       |
       |  def asIs(dataModel: DataModel, kw: Kw, n: Option[Int] = None): DataModel = {
       |    val es   = dataModel.elements
       |    val last = es.last match {
       |      case a: AttrOneMan => a match {
       |        case a: AttrOneManID             => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManString         => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManInt            => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManLong           => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManFloat          => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManDouble         => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManBoolean        => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManBigInt         => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManBigDecimal     => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManDate           => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManDuration       => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManInstant        => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManLocalDate      => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManLocalTime      => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManLocalDateTime  => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManOffsetTime     => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManOffsetDateTime => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManZonedDateTime  => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManUUID           => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManURI            => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManByte           => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManShort          => a.copy(op = getFn(a, kw, n))
       |        case a: AttrOneManChar           => a.copy(op = getFn(a, kw, n))
       |      }
       |      case a             => unexpected(a)
       |    }
       |    dataModel.copy(elements = es.init :+ last)
       |  }
       |
       |  def addAggrOp[T](dataModel: DataModel, aggrOp: Op, aggrOpV: Option[T]): DataModel = {
       |    val es    = dataModel.elements
       |    val last  = es.last match {
       |      case a: AttrOneMan =>
       |        val fn = a.op.asInstanceOf[Fn]
       |        a match {
       |          case a: AttrOneManID             => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneLong(v.asInstanceOf[Long])))))
       |          case a: AttrOneManString         => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneString(v.asInstanceOf[String])))))
       |          case a: AttrOneManInt            => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneInt(v.asInstanceOf[Int])))))
       |          case a: AttrOneManLong           => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneLong(v.asInstanceOf[Long])))))
       |          case a: AttrOneManFloat          => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneFloat(v.asInstanceOf[Float])))))
       |          case a: AttrOneManDouble         => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneDouble(v.asInstanceOf[Double])))))
       |          case a: AttrOneManBoolean        => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneBoolean(v.asInstanceOf[Boolean])))))
       |          case a: AttrOneManBigInt         => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneBigInt(v.asInstanceOf[BigInt])))))
       |          case a: AttrOneManBigDecimal     => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneBigDecimal(v.asInstanceOf[BigDecimal])))))
       |          case a: AttrOneManDate           => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneDate(v.asInstanceOf[Date])))))
       |          case a: AttrOneManDuration       => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneDuration(v.asInstanceOf[Duration])))))
       |          case a: AttrOneManInstant        => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneInstant(v.asInstanceOf[Instant])))))
       |          case a: AttrOneManLocalDate      => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneLocalDate(v.asInstanceOf[LocalDate])))))
       |          case a: AttrOneManLocalTime      => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneLocalTime(v.asInstanceOf[LocalTime])))))
       |          case a: AttrOneManLocalDateTime  => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneLocalDateTime(v.asInstanceOf[LocalDateTime])))))
       |          case a: AttrOneManOffsetTime     => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneOffsetTime(v.asInstanceOf[OffsetTime])))))
       |          case a: AttrOneManOffsetDateTime => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneOffsetDateTime(v.asInstanceOf[OffsetDateTime])))))
       |          case a: AttrOneManZonedDateTime  => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneZonedDateTime(v.asInstanceOf[ZonedDateTime])))))
       |          case a: AttrOneManUUID           => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneUUID(v.asInstanceOf[UUID])))))
       |          case a: AttrOneManURI            => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneURI(v.asInstanceOf[URI])))))
       |          case a: AttrOneManByte           => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneByte(v.asInstanceOf[Byte])))))
       |          case a: AttrOneManShort          => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneShort(v.asInstanceOf[Short])))))
       |          case a: AttrOneManChar           => aggrOpV.fold(a.copy(op = fn.copy(op = Some(aggrOp)), binding = true))(v => a.copy(op = fn.copy(op = Some(aggrOp), v = Some(OneChar(v.asInstanceOf[Char])))))
       |        }
       |      case a             => unexpected(a)
       |    }
       |    val binds = dataModel.binds + (if aggrOpV.isEmpty then 1 else 0)
       |    dataModel.copy(elements = es.init :+ last, binds = binds)
       |  }
       |
       |  def addOne[T](dataModel: DataModel, op: Op, vs: Seq[T], binding: Boolean = false): DataModel = {
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
       |  def addOneOpt[T](dataModel: DataModel, op: Op, v: Option[T]): DataModel = {
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
       |  def addSet[T](dataModel: DataModel, op: Op, vs: Set[T]): DataModel = {
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
       |  def addSetOpt[T](dataModel: DataModel, op: Op, vs: Option[Set[T]]): DataModel = {
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
       |  def addSeq[T](dataModel: DataModel, op: Op, vs: Seq[T]): DataModel = {
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
       |  def addSeqOpt[T](dataModel: DataModel, op: Op, vs: Option[Seq[T]]): DataModel = {
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
       |  def addBAr[T](dataModel: DataModel, op: Op, ba: Array[T]): DataModel = {
       |    val es          = dataModel.elements
       |    val newElements = es.init :+ (es.last match {
       |      case a: AttrSeqManByte => a.copy(op = op, vs = ba.asInstanceOf[Array[Byte]])
       |      case a: AttrSeqTacByte => a.copy(op = op, vs = ba.asInstanceOf[Array[Byte]])
       |      case e                 => throw ModelError("Unexpected Element for adding byte array: " + e)
       |    })
       |    dataModel.copy(elements = newElements)
       |  }
       |
       |  def addBArOpt[T](dataModel: DataModel, op: Op, optBA: Option[Array[T]]): DataModel = {
       |    val es          = dataModel.elements
       |    val newElements = es.init :+ (es.last match {
       |      case a: AttrSeqOptByte => a.copy(op = op, vs = optBA.asInstanceOf[Option[Array[Byte]]])
       |      case e                 => throw ModelError("Unexpected Element for adding byte array: " + e)
       |    })
       |    dataModel.copy(elements = newElements)
       |  }
       |
       |  def addMap[T](dataModel: DataModel, op: Op, vs: Map[String, T]): DataModel = {
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
       |  def addMapKs(dataModel: DataModel, op: Op, ks: Seq[String]): DataModel = {
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
       |  def addMapVs[T](dataModel: DataModel, op: Op, vs: Seq[T]): DataModel = {
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
       |  def addMapOpt[T](dataModel: DataModel, op: Op, vs: Option[Map[String, T]]): DataModel = {
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
       |  def addSort(dataModel: DataModel, sort: String): DataModel = {
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
       |  def filterAttr(dataModel: DataModel, op: Op, filterAttrMolecule: Molecule): DataModel = {
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
       |  def reverseTopLevelSorting(es: List[Element]): List[Element] = {
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
       |  def topLevelAttrCount(elements: List[Element], count: Int = 0): Int = {
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

  private def asIs(value: String): String = {
    baseTypesWithSpaces.map {
      case (baseTpe, space) => s"case a: Attr${value}Man$baseTpe $space=> a.copy(op = Fn(kw.toString, n))"
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

  private def addSort(value: String, mode: String): String = {
    baseTypesWithSpaces.map { case (baseTpe, space) =>
      s"case a: Attr$value$mode$baseTpe $space=> a.copy(sort = Some(sort))"
    }.mkString("\n        ")
  }

  private def liftFilterAttr(card: String): String = {
    baseTypesWithSpaces.map {
      case (baseTpe, space) if card == "Map" =>
        s"case a: Attr${card}Man$baseTpe $space=> Attr${card}Tac$baseTpe(a.ent, a.attr, a.op, a.map, a.keys, Nil, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)"

      case (baseTpe, space) if card == "One" =>
        s"case a: Attr${card}Man$baseTpe $space=> Attr${card}Tac$baseTpe(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.binding, a.coord)"

      case (baseTpe, space) =>
        s"case a: Attr${card}Man$baseTpe $space=> Attr${card}Tac$baseTpe(a.ent, a.attr, a.op, a.vs, None, a.validator, a.valueAttrs, a.errors, a.ref, a.sort, a.coord)"
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
