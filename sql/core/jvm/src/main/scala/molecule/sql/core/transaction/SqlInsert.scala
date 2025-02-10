package molecule.sql.core.transaction

import java.sql.{PreparedStatement => PS}
import molecule.base.ast._
import molecule.core.ast.DataModel.Element
import molecule.core.transaction.ops.InsertOps
import molecule.core.transaction.{InsertResolvers_, ResolveInsert}
import molecule.sql.core.transaction.strategy.SqlOps
import molecule.sql.core.transaction.strategy.insert.{InsertAction, InsertRoot}

trait SqlInsert
  extends InsertOps
    with SqlBaseOps { self: ResolveInsert with InsertResolvers_ with SqlOps =>

  protected var insertAction: InsertAction = null

  def getInsertAction(
    elements: List[Element],
    tpls: Seq[Product]
  ): InsertAction = {
    insertAction = InsertRoot(sqlOps, getInitialEntity(elements), tpls.length).insertEnt
    val stableInsert = insertAction
    val resolveTpl   = getResolver(elements)
    tpls.foreach { tpl =>
      //      println("------------------------------- " + tpl)
      stableInsert.nextRow()
      resolveTpl(tpl)
    }
    val xx = insertAction.rootAction

    xx
  }


  override protected def addOne[T](
    ent: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil
  ): Product => Unit = {
    val paramIndex   = insertAction.setCol(attr, exts(2))
    val stableInsert = insertAction
    (tpl: Product) => {
      val scalaValue  = tpl.productElement(tplIndex).asInstanceOf[T]
      val valueSetter = transformValue(scalaValue).asInstanceOf[(PS, Int) => Unit]
      stableInsert.addColSetter((ps: PS) => {
        valueSetter(ps, paramIndex)
      })
    }
  }

  override protected def addOneOpt[T](
    ent: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil
  ): Product => Unit = {
    val paramIndex   = insertAction.setCol(attr, exts(2))
    val stableInsert = insertAction
    (tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case Some(scalaValue) =>
          val valueSetter = transformValue(scalaValue.asInstanceOf[T])
            .asInstanceOf[(PS, Int) => Unit]
          stableInsert.addColSetter((ps: PS) => valueSetter(ps, paramIndex))

        case None =>
          stableInsert.addColSetter((ps: PS) =>
            ps.setNull(paramIndex, java.sql.Types.NULL))
      }
    }
  }

  override protected def addSet[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addIterable(attr, optRef, exts(1), tplIndex, set2array)
  }

  override protected def addSetOpt[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addOptIterable(attr, optRef, exts(1), tplIndex, set2array)
  }

  override protected def addSeq[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addIterable(attr, optRef, exts(1), tplIndex, seq2array)
  }

  override protected def addSeqOpt[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addOptIterable(attr, optRef, exts(1), tplIndex, seq2array)
  }

  override protected def addByteArray(
    ent: String,
    attr: String,
    tplIndex: Int,
  ): Product => Unit = {
    val paramIndex   = insertAction.setCol(attr)
    val stableInsert = insertAction
    (tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case byteArray: Array[_] if byteArray.nonEmpty =>
          stableInsert.addColSetter((ps: PS) =>
            ps.setBytes(paramIndex, byteArray.asInstanceOf[Array[Byte]]))

        case Some(byteArray: Array[_]) if !byteArray.isEmpty =>
          stableInsert.addColSetter((ps: PS) =>
            ps.setBytes(paramIndex, byteArray.asInstanceOf[Array[Byte]]))

        case _ =>
          stableInsert.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
      }
    }
  }

  override protected def addMap[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val paramIndex   = insertAction.setCol(attr)
    val stableInsert = insertAction
    (tpl: Product) => {
      tpl.productElement(tplIndex).asInstanceOf[Map[String, _]] match {
        case map if map.nonEmpty =>
          stableInsert.addColSetter((ps: PS) =>
            ps.setBytes(
              paramIndex,
              map2jsonByteArray(map.asInstanceOf[Map[String, T]], value2json)
            ))

        case _ =>
          stableInsert.addColSetter((ps: PS) =>
            ps.setNull(paramIndex, java.sql.Types.NULL))
      }
    }
  }

  override protected def addMapOpt[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val paramIndex   = insertAction.setCol(attr)
    val stableInsert = insertAction
    (tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case Some(map: Map[_, _]) if map.nonEmpty =>
          stableInsert.addColSetter((ps: PS) =>
            ps.setBytes(
              paramIndex,
              map2jsonByteArray(map.asInstanceOf[Map[String, T]], value2json)
            ))

        case _ =>
          stableInsert.addColSetter((ps: PS) =>
            ps.setNull(paramIndex, java.sql.Types.NULL))
      }
    }
  }

  override protected def addRef(
    ent: String,
    refAttr: String,
    ref: String,
    card: Card
  ): Product => Unit = {
    insertAction = card match {
      case CardOne => insertAction.refOne(ent, refAttr, ref)
      case _       => insertAction.refMany(ent, refAttr, ref)
    }
    (_: Product) => ()
  }

  override protected def addBackRef(backRef: String): Product => Unit = {
    insertAction = insertAction.backRef
    (_: Product) => ()
  }

  override protected def addOptRef(
    tplIndex: Int,
    ent: String,
    refAttr: String,
    ref: String,
    optRefElements: List[Element]
  ): Product => Unit = {
    val baseAction = insertAction

    // Cache stable insert instance
    val insertOptRef = insertAction.optRef(ent, refAttr, ref)
    insertAction = insertOptRef

    // Recursively resolve optional data
    val resolveOptionalRefData = getResolver(optRefElements)

    insertAction = baseAction

    countValueAttrs(optRefElements) match {
      case 1 =>
        (tpl: Product) => {
          val optionalValue = tpl.productElement(tplIndex).asInstanceOf[Option[Any]]
          insertOptRef.setOptionalDefined(optionalValue.isDefined)
          optionalValue.foreach { value =>
            resolveOptionalRefData(Tuple1(value))
          }
        }
      case _ =>
        (tpl: Product) => {
          val optionalTpl = tpl.productElement(tplIndex).asInstanceOf[Option[Product]]
          insertOptRef.setOptionalDefined(optionalTpl.isDefined)
          optionalTpl.foreach { tpl =>
            resolveOptionalRefData(tpl)
          }
        }
    }
  }

  override protected def addOptEntity(
    ent: String,
    refAttr: String,
    ref: String,
    optEntityElements: List[Element]
  ): Product => Unit = {
    // Resolve optional entity data
    val insertOptEntity      = insertAction.optEntity(ent, refAttr, ref)
    val resolveOptEntityData = getResolver(optEntityElements)

    insertAction = insertOptEntity

    countValueAttrs(optEntityElements) match {
      case 1 =>
        (tpl: Product) => {
          val optionalValue = tpl.productElement(0).asInstanceOf[Option[Any]]
          insertOptEntity.setOptionalDefined(optionalValue.isDefined)
          optionalValue.foreach { value =>
            resolveOptEntityData(Tuple1(value))
          }
        }
      case _ =>
        (tpl: Product) => {
          val optionalTpl = tpl.productElement(0).asInstanceOf[Option[Product]]
          insertOptEntity.setOptionalDefined(optionalTpl.isDefined)
          optionalTpl.foreach { tpl =>
            resolveOptEntityData(tpl)
          }
        }
    }
  }

  override protected def addNested(
    tplIndex: Int,
    ent: String,
    refAttr: String,
    ref: String,
    nestedElements: List[Element]
  ): Product => Unit = {
    val nestedJoins  = insertAction.nest(ent, refAttr, ref)
    val nestedInsert = nestedJoins.nested
    insertAction = nestedInsert

    // Recursively resolve nested data
    val resolveNested = getResolver(nestedElements)

    countValueAttrs(nestedElements) match {
      case 1 =>
        (tpl: Product) => {
          val nestedValues = tpl.productElement(tplIndex).asInstanceOf[Seq[Any]]
          nestedJoins.addNestedCount(nestedValues.length)
          nestedValues.foreach { nestedSingleValue =>
            nestedInsert.nextRow()
            resolveNested(Tuple1(nestedSingleValue))
          }
        }
      case _ =>
        (tpl: Product) => {
          val nestedTpls = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
          nestedJoins.addNestedCount(nestedTpls.length)
          nestedTpls.foreach { nestedTpl =>
            nestedInsert.nextRow()
            resolveNested(nestedTpl)
          }
        }
    }
  }


  // Helpers -------------------------------------------------------------------

  private def addIterable[T, M[_] <: Iterable[_]](
    attr: String,
    optRef: Option[String],
    sqlTpe: String,
    tplIndex: Int,
    iterable2array: M[T] => Array[AnyRef],
  ): Product => Unit = {
    optRef.fold {
      val stableInsert = insertAction
      val paramIndex   = stableInsert.setCol(attr)
      (tpl: Product) => {
        val array = iterable2array(tpl.productElement(tplIndex).asInstanceOf[M[T]])
        if (array.nonEmpty) {
          stableInsert.addColSetter((ps: PS) => {
            val conn = ps.getConnection
            val arr  = conn.createArrayOf(sqlTpe, array)
            ps.setArray(paramIndex, arr)
          })
        } else {
          stableInsert.addColSetter(
            (ps: PS) => {
              ps.setNull(paramIndex, java.sql.Types.NULL)
            }
          )
        }
      }
    } { ref =>
      val insertRefIds = insertAction.refIds(attr, ref)
      (tpl: Product) => {
        val refIds = tpl.productElement(tplIndex).asInstanceOf[Iterable[Long]]
        insertRefIds.addRefIds(refIds)
      }
    }
  }

  private def addOptIterable[T, M[_] <: Iterable[_]](
    attr: String,
    optRef: Option[String],
    sqlTpe: String,
    tplIndex: Int,
    iterable2array: M[T] => Array[AnyRef],
  ): Product => Unit = {
    optRef.fold {
      val stableInsert = insertAction
      val paramIndex   = stableInsert.setCol(attr)
      (tpl: Product) => {
        tpl.productElement(tplIndex) match {
          case Some(iterable: Iterable[_]) =>
            val array = iterable2array(iterable.asInstanceOf[M[T]])
            stableInsert.addColSetter((ps: PS) => {
              val conn = ps.getConnection
              val arr  = conn.createArrayOf(sqlTpe, array)
              ps.setArray(paramIndex, arr)
            })

          case None =>
            stableInsert.addColSetter((ps: PS) =>
              ps.setNull(paramIndex, java.sql.Types.NULL))
        }
      }
    } { ref =>
      val insertRefIds = insertAction.refIds(attr, ref)
      (tpl: Product) => {
        tpl.productElement(tplIndex) match {
          case Some(set: Iterable[_]) =>
            insertRefIds.addRefIds(set.asInstanceOf[Iterable[Long]])
          case _                      =>
            insertRefIds.addRefIds(Iterable.empty[Long])
        }
      }
    }
  }
}