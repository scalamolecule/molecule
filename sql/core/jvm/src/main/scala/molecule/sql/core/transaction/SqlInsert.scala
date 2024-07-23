package molecule.sql.core.transaction

import java.sql.{PreparedStatement => PS}
import molecule.base.ast._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ops.InsertOps
import molecule.core.transaction.{InsertResolvers_, ResolveInsert}
import molecule.core.util.ModelUtils
import molecule.sql.core.transaction.strategy.insert.{InsertAction, InsertRoot}

trait SqlInsert
  extends SqlBase_JVM
    with InsertOps
    with SqlBaseOps
    with SqlDataType_JVM
    with ModelUtils
    with MoleculeLogging { self: ResolveInsert with InsertResolvers_ =>

  protected var insert: InsertAction = null

  def getInsertAction(elements: List[Element], tpls: Seq[Product]): InsertAction = {
    insert = InsertRoot(sqlConn, getInitialNs(elements)).insertNs
    val stableInsert = insert
    val resolveTpl   = getResolver(elements)
    tpls.foreach { tpl =>
      //      println("------------------------------- " + tpl)
      stableInsert.nextRow()
      resolveTpl(tpl)
    }
    insert.rootAction
  }


  override protected def addOne[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil
  ): Product => Unit = {
    val paramIndex   = insert.setCol(attr, exts(2))
    val stableInsert = insert
    (tpl: Product) => {
      val scalaValue  = tpl.productElement(tplIndex).asInstanceOf[T]
      val valueSetter = transformValue(scalaValue).asInstanceOf[(PS, Int) => Unit]
      stableInsert.addColSetter((ps: PS) => valueSetter(ps, paramIndex))
    }
  }

  override protected def addOneOpt[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil
  ): Product => Unit = {
    val paramIndex   = insert.setCol(attr, exts(2))
    val stableInsert = insert
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
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addIterable(ns, attr, optRefNs, exts(1), tplIndex, set2array)
  }

  override protected def addSetOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addOptIterable(ns, attr, optRefNs, exts(1), tplIndex, set2array)
  }

  override protected def addSeq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addIterable(ns, attr, optRefNs, exts(1), tplIndex, seq2array)
  }

  override protected def addSeqOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addOptIterable(ns, attr, optRefNs, exts(1), tplIndex, seq2array)
  }

  override protected def addByteArray(
    ns: String,
    attr: String,
    tplIndex: Int,
  ): Product => Unit = {
    val paramIndex   = insert.setCol(attr)
    val stableInsert = insert
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
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val paramIndex   = insert.setCol(attr)
    val stableInsert = insert
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
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val paramIndex   = insert.setCol(attr)
    val stableInsert = insert
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
    ns: String,
    refAttr: String,
    refNs: String,
    card: Card,
  ): Product => Unit = {
    insert = card match {
      case CardOne => insert.refOne(ns, refAttr, refNs)
      case _       => insert.refMany(ns, refAttr, refNs)
    }
    (_: Product) => ()
  }

  override protected def addBackRef(backRefNs: String): Product => Unit = {
    insert = insert.backRef
    (_: Product) => ()
  }

  override protected def addOptRef(
    tplIndex: Int,
    ns: String,
    refAttr: String,
    refNs: String,
    nestedElements: List[Element]
  ): Product => Unit = {
    ???
  }

  override protected def addNested(
    tplIndex: Int,
    ns: String,
    refAttr: String,
    refNs: String,
    nestedElements: List[Element]
  ): Product => Unit = {
    val nestedJoins  = insert.nest(ns, refAttr, refNs)
    val nestedInsert = nestedJoins.nested
    insert = nestedInsert

    // Recursively resolve nested data
    val resolveNested = getResolver(nestedElements)

    countValueAttrs(nestedElements) match {
      case 1 =>
        (tpl: Product) => {
          val nestedSingleValues = tpl.productElement(tplIndex).asInstanceOf[Seq[Any]]
          nestedJoins.addNestedCount(nestedSingleValues.length)
          nestedSingleValues.foreach { nestedSingleValue =>
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
    ns: String,
    attr: String,
    optRefNs: Option[String],
    sqlTpe: String,
    tplIndex: Int,
    iterable2array: M[T] => Array[AnyRef],
  ): Product => Unit = {
    optRefNs.fold {
      val stableInsert = insert
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
    } { refNs =>
      val insertRefIds = insert.refIds(attr, refNs)
      (tpl: Product) => {
        val refIds = tpl.productElement(tplIndex).asInstanceOf[Iterable[Long]]
        insertRefIds.addRefIds(refIds)
      }
    }
  }

  private def addOptIterable[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    sqlTpe: String,
    tplIndex: Int,
    iterable2array: M[T] => Array[AnyRef],
  ): Product => Unit = {
    optRefNs.fold {
      val stableInsert = insert
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
    } { refNs =>
      val insertRefIds = insert.refIds(attr, refNs)
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