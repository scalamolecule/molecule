package molecule.db.core.api.expression

import molecule.base.metaModel.CardOne
import molecule.core.dataModel.*
import molecule.core.dataModel.Keywords.*
import molecule.db.core.api.*
import molecule.db.core.ops.ModelTransformations_.*
import scala.Tuple.{:*, Init}


trait ExprOneManBase_n[T, Tpl <: Tuple, Entity[_ <: Tuple]] extends CardOne { self: Molecule   =>
  val entity: [tpl <: Tuple] => DataModel => Entity[tpl]

  def apply(                ): Entity[Tpl] = entity[Tpl](addOne(dataModel, NoValue, Nil         ))
  def apply(v    : T, vs: T*): Entity[Tpl] = entity[Tpl](addOne(dataModel, Eq     , Seq(v) ++ vs))
  def apply(vs   : Seq[T]   ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Eq     , vs          ))
  def not  (v    : T, vs: T*): Entity[Tpl] = entity[Tpl](addOne(dataModel, Neq    , Seq(v) ++ vs))
  def not  (vs   : Seq[T]   ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Neq    , vs          ))
  def <    (upper: T        ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Lt     , Seq(upper)  ))
  def <=   (upper: T        ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Le     , Seq(upper)  ))
  def >    (lower: T        ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Gt     , Seq(lower)  ))
  def >=   (lower: T        ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Ge     , Seq(lower)  ))

  def apply(v    : qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Eq , Nil, true))
  def not  (v    : qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Neq, Nil, true))
  def <    (upper: qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Lt , Nil, true))
  def <=   (upper: qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Le , Nil, true))
  def >    (lower: qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Gt , Nil, true))
  def >=   (lower: qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Ge , Nil, true))

  def apply(a: Molecule_0 & CardOne)(implicit ev: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Eq , a))
  def not  (a: Molecule_0 & CardOne)(implicit ev: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Neq, a))
  def <    (a: Molecule_0 & CardOne)(implicit ev: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Lt , a))
  def <=   (a: Molecule_0 & CardOne)(implicit ev: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Le , a))
  def >    (a: Molecule_0 & CardOne)(implicit ev: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Gt , a))
  def >=   (a: Molecule_0 & CardOne)(implicit ev: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Ge , a))

  def apply(kw: count)        : Entity[Init[Tpl] :* Int   ] = entity[Init[Tpl] :* Int   ](toInt(dataModel, kw            ))
  def apply(kw: countDistinct): Entity[Init[Tpl] :* Int   ] = entity[Init[Tpl] :* Int   ](toInt(dataModel, kw            ))
  def apply(kw: min)          : Entity[Init[Tpl] :* T     ] = entity[Init[Tpl] :* T     ](asIs (dataModel, kw            ))
  def apply(kw: max)          : Entity[Init[Tpl] :* T     ] = entity[Init[Tpl] :* T     ](asIs (dataModel, kw            ))
  def apply(kw: sample)       : Entity[Init[Tpl] :* T     ] = entity[Init[Tpl] :* T     ](asIs (dataModel, kw            ))
  def apply(kw: mins)         : Entity[Init[Tpl] :* Set[T]] = entity[Init[Tpl] :* Set[T]](asIs (dataModel, kw, Some(kw.n)))
  def apply(kw: maxs)         : Entity[Init[Tpl] :* Set[T]] = entity[Init[Tpl] :* Set[T]](asIs (dataModel, kw, Some(kw.n)))
  def apply(kw: samples)      : Entity[Init[Tpl] :* Set[T]] = entity[Init[Tpl] :* Set[T]](asIs (dataModel, kw, Some(kw.n)))
  def apply(kw: distinct)     : Entity[Init[Tpl] :* Set[T]] = entity[Init[Tpl] :* Set[T]](asIs (dataModel, kw            ))
}

trait ExprOneMan_n[T, Tpl <: Tuple, Entity[_ <: Tuple]] extends ExprOneManBase_n[T, Tpl, Entity] { self: Molecule => }

trait ExprOneMan_n_String[T, Tpl <: Tuple, Entity[_ <: Tuple]] extends ExprOneManBase_n[T, Tpl, Entity] { self: Molecule  =>
  def startsWith(prefix: T)               : Entity[Tpl] = entity[Tpl](addOne(dataModel, StartsWith                  , Seq(prefix)            ))
  def endsWith  (suffix: T)               : Entity[Tpl] = entity[Tpl](addOne(dataModel, EndsWith                    , Seq(suffix)            ))
  def contains  (needle: T)               : Entity[Tpl] = entity[Tpl](addOne(dataModel, Contains                    , Seq(needle)            ))
  def matches   (regex : T)               : Entity[Tpl] = entity[Tpl](addOne(dataModel, Matches                     , Seq(regex)             ))
  def +         (str   : T)               : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Append               , Seq(str)               ))
  def prepend   (str   : T)               : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Prepend              , Seq(str)               ))
  def substring (start: Int, end: Int)    : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.SubString(start, end), Nil                    ))
  def replaceAll(regex: T, replacement: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.ReplaceAll           , Seq(regex, replacement)))
  def toLower                             : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.ToLower              , Nil                    ))
  def toUpper                             : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.ToUpper              , Nil                    ))

  def startsWith(prefix: qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, StartsWith, Nil, true))
  def endsWith  (suffix: qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, EndsWith  , Nil, true))
  def contains  (needle: qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Contains  , Nil, true))
  def matches   (regex : qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Matches   , Nil, true))
}

trait ExprOneMan_n_Enum[T, Tpl <: Tuple, Entity[_ <: Tuple]](entity: [tpl <: Tuple] => DataModel => Entity[tpl]) { self: Molecule  =>
  def apply(             ): Entity[Tpl] = entity[Tpl](addOne(dataModel, NoValue, Nil                                      ))
  def apply(v : T, vs: T*): Entity[Tpl] = entity[Tpl](addOne(dataModel, Eq     , (v +: vs).map(_.toString.asInstanceOf[T])))
  def apply(vs: Seq[T]   ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Eq     , vs       .map(_.toString.asInstanceOf[T])))
  def not  (v : T, vs: T*): Entity[Tpl] = entity[Tpl](addOne(dataModel, Neq    , (v +: vs).map(_.toString.asInstanceOf[T])))
  def not  (vs: Seq[T]   ): Entity[Tpl] = entity[Tpl](addOne(dataModel, Neq    , vs       .map(_.toString.asInstanceOf[T])))

  def apply(v : qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Eq , Nil, true))
  def not  (v : qm): Entity[Tpl] = entity[Tpl](addOne(dataModel, Neq, Nil, true))
}

trait ExprOneMan_n_Integer[T, Tpl <: Tuple, Entity[_ <: Tuple]] extends ExprOneMan_n_Number[T, Tpl, Entity] { self: Molecule  =>
  def even                       : Entity[Tpl] = entity[Tpl](addOne(dataModel, Even         , Nil                    ))
  def odd                        : Entity[Tpl] = entity[Tpl](addOne(dataModel, Odd          , Nil                    ))
  def %(divider: T, remainder: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, Remainder    , Seq(divider, remainder)))
}

trait ExprOneMan_n_Decimal[T, Tpl <: Tuple, Entity[_ <: Tuple]] extends ExprOneMan_n_Number[T, Tpl, Entity] { self: Molecule  =>
  def ceil : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Ceil  , Nil))
  def floor: Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Floor , Nil))
}

trait ExprOneMan_n_Number[T, Tpl <: Tuple, Entity[_ <: Tuple]] extends ExprOneManBase_n[T, Tpl, Entity] { self: Molecule  =>
  def +(v: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Plus  , Seq(v)))
  def -(v: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Minus , Seq(v)))
  def *(v: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Times , Seq(v)))
  def /(v: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Divide, Seq(v)))
  def negate : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Negate, Nil   ))
  def abs    : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Abs   , Nil   ))
  def absNeg : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.AbsNeg, Nil   ))

  def apply(kw: sum)     : Entity[Init[Tpl] :* T     ] = entity[Init[Tpl] :* T     ](asIs     (dataModel, kw))
  def apply(kw: median)  : Entity[Init[Tpl] :* Double] = entity[Init[Tpl] :* Double](toDouble (dataModel, kw))
  def apply(kw: avg)     : Entity[Init[Tpl] :* Double] = entity[Init[Tpl] :* Double](toDouble (dataModel, kw))
  def apply(kw: variance): Entity[Init[Tpl] :* Double] = entity[Init[Tpl] :* Double](toDouble (dataModel, kw))
  def apply(kw: stddev)  : Entity[Init[Tpl] :* Double] = entity[Init[Tpl] :* Double](toDouble (dataModel, kw))
}

trait ExprOneMan_n_Boolean[T, Tpl <: Tuple, Entity[_ <: Tuple]] extends ExprOneManBase_n[T, Tpl, Entity] { self: Molecule  =>
  def &&(bool: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.And, Seq(bool)))
  def ||(bool: T): Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Or , Seq(bool)))
  def !          : Entity[Tpl] = entity[Tpl](addOne(dataModel, AttrOp.Not, Nil      ))
}

