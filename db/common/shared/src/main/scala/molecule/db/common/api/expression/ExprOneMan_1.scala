package molecule.db.common.api.expression

import molecule.base.metaModel.CardOne
import molecule.core.dataModel.*
import molecule.core.dataModel.Keywords.*
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*


trait ExprOneManBase_1[T, Entity[_]] extends CardOne { self: Molecule =>
  val entity: [t] => DataModel => Entity[t]

  def apply(                ): Entity[T] = entity[T](addOne(dataModel, NoValue, Nil         ))
  def apply(v    : T, vs: T*): Entity[T] = entity[T](addOne(dataModel, Eq     , Seq(v) ++ vs))
  def apply(vs   : Seq[T]   ): Entity[T] = entity[T](addOne(dataModel, Eq     , vs          ))
  def not  (v    : T, vs: T*): Entity[T] = entity[T](addOne(dataModel, Neq    , Seq(v) ++ vs))
  def not  (vs   : Seq[T]   ): Entity[T] = entity[T](addOne(dataModel, Neq    , vs          ))
  def <    (upper: T        ): Entity[T] = entity[T](addOne(dataModel, Lt     , Seq(upper)  ))
  def <=   (upper: T        ): Entity[T] = entity[T](addOne(dataModel, Le     , Seq(upper)  ))
  def >    (lower: T        ): Entity[T] = entity[T](addOne(dataModel, Gt     , Seq(lower)  ))
  def >=   (lower: T        ): Entity[T] = entity[T](addOne(dataModel, Ge     , Seq(lower)  ))

  def apply(v    : qm): Entity[T] = entity[T](addOne(dataModel, Eq , Nil, true))
  def not  (v    : qm): Entity[T] = entity[T](addOne(dataModel, Neq, Nil, true))
  def <    (upper: qm): Entity[T] = entity[T](addOne(dataModel, Lt , Nil, true))
  def <=   (upper: qm): Entity[T] = entity[T](addOne(dataModel, Le , Nil, true))
  def >    (lower: qm): Entity[T] = entity[T](addOne(dataModel, Gt , Nil, true))
  def >=   (lower: qm): Entity[T] = entity[T](addOne(dataModel, Ge , Nil, true))

  def apply(a: Molecule_0 & CardOne): Entity[T] = entity[T](filterAttr(dataModel, Eq , a))
  def not  (a: Molecule_0 & CardOne): Entity[T] = entity[T](filterAttr(dataModel, Neq, a))
  def <    (a: Molecule_0 & CardOne): Entity[T] = entity[T](filterAttr(dataModel, Lt , a))
  def <=   (a: Molecule_0 & CardOne): Entity[T] = entity[T](filterAttr(dataModel, Le , a))
  def >    (a: Molecule_0 & CardOne): Entity[T] = entity[T](filterAttr(dataModel, Gt , a))
  def >=   (a: Molecule_0 & CardOne): Entity[T] = entity[T](filterAttr(dataModel, Ge , a))

  def apply(kw: count)        : Entity[Int   ] = entity[Int   ](toInt(dataModel, kw            ))
  def apply(kw: countDistinct): Entity[Int   ] = entity[Int   ](toInt(dataModel, kw            ))
  def apply(kw: min)          : Entity[T     ] = entity[T     ](asIs (dataModel, kw            ))
  def apply(kw: max)          : Entity[T     ] = entity[T     ](asIs (dataModel, kw            ))
  def apply(kw: sample)       : Entity[T     ] = entity[T     ](asIs (dataModel, kw            ))
  def apply(kw: mins)         : Entity[Set[T]] = entity[Set[T]](asIs (dataModel, kw, Some(kw.n)))
  def apply(kw: maxs)         : Entity[Set[T]] = entity[Set[T]](asIs (dataModel, kw, Some(kw.n)))
  def apply(kw: samples)      : Entity[Set[T]] = entity[Set[T]](asIs (dataModel, kw, Some(kw.n)))
  def apply(kw: distinct)     : Entity[Set[T]] = entity[Set[T]](asIs (dataModel, kw            ))
}

trait ExprOneMan_1[T, Entity[_]] extends ExprOneManBase_1[T, Entity] { self: Molecule => }

trait ExprOneMan_1_String[T, Entity[_]] extends ExprOneManBase_1[T, Entity] { self: Molecule  =>
  def startsWith(prefix: T)               : Entity[T] = entity[T](addOne(dataModel, StartsWith                  , Seq(prefix)            ))
  def endsWith  (suffix: T)               : Entity[T] = entity[T](addOne(dataModel, EndsWith                    , Seq(suffix)            ))
  def contains  (needle: T)               : Entity[T] = entity[T](addOne(dataModel, Contains                    , Seq(needle)            ))
  def matches   (regex : T)               : Entity[T] = entity[T](addOne(dataModel, Matches                     , Seq(regex)             ))
  def +         (str   : T)               : Entity[T] = entity[T](addOne(dataModel, AttrOp.Append               , Seq(str)               ))
  def prepend   (str   : T)               : Entity[T] = entity[T](addOne(dataModel, AttrOp.Prepend              , Seq(str)               ))
  def substring (start: Int, end: Int)    : Entity[T] = entity[T](addOne(dataModel, AttrOp.SubString(start, end), Nil                    ))
  def replaceAll(regex: T, replacement: T): Entity[T] = entity[T](addOne(dataModel, AttrOp.ReplaceAll           , Seq(regex, replacement)))
  def toLower                             : Entity[T] = entity[T](addOne(dataModel, AttrOp.ToLower              , Nil                    ))
  def toUpper                             : Entity[T] = entity[T](addOne(dataModel, AttrOp.ToUpper              , Nil                    ))

  def startsWith(prefix: qm): Entity[T] = entity[T](addOne(dataModel, StartsWith, Nil, true))
  def endsWith  (suffix: qm): Entity[T] = entity[T](addOne(dataModel, EndsWith  , Nil, true))
  def contains  (needle: qm): Entity[T] = entity[T](addOne(dataModel, Contains  , Nil, true))
  def matches   (regex : qm): Entity[T] = entity[T](addOne(dataModel, Matches   , Nil, true))
}

trait ExprOneMan_1_Enum[T, Entity[_]](entity: [t] => DataModel => Entity[t]) extends CardOne { self: Molecule  =>
  def apply(             ): Entity[T] = entity[T](addOne(dataModel, NoValue, Nil                                      ))
  def apply(v : T, vs: T*): Entity[T] = entity[T](addOne(dataModel, Eq     , (v +: vs).map(_.toString.asInstanceOf[T])))
  def apply(vs: Seq[T]   ): Entity[T] = entity[T](addOne(dataModel, Eq     , vs       .map(_.toString.asInstanceOf[T])))
  def not  (v : T, vs: T*): Entity[T] = entity[T](addOne(dataModel, Neq    , (v +: vs).map(_.toString.asInstanceOf[T])))
  def not  (vs: Seq[T]   ): Entity[T] = entity[T](addOne(dataModel, Neq    , vs       .map(_.toString.asInstanceOf[T])))

  def apply(v : qm): Entity[T] = entity[T](addOne(dataModel, Eq , Nil, true))
  def not  (v : qm): Entity[T] = entity[T](addOne(dataModel, Neq, Nil, true))
}

trait ExprOneMan_1_Integer[T, Entity[_]] extends ExprOneMan_1_Number[T, Entity] { self: Molecule  =>
  def even                       : Entity[T] = entity[T](addOne(dataModel, Even         , Nil                    ))
  def odd                        : Entity[T] = entity[T](addOne(dataModel, Odd          , Nil                    ))
  def %(divider: T, remainder: T): Entity[T] = entity[T](addOne(dataModel, Remainder    , Seq(divider, remainder)))
}

trait ExprOneMan_1_Decimal[T, Entity[_]] extends ExprOneMan_1_Number[T, Entity] { self: Molecule  =>
  def ceil : Entity[T] = entity[T](addOne(dataModel, AttrOp.Ceil  , Nil))
  def floor: Entity[T] = entity[T](addOne(dataModel, AttrOp.Floor , Nil))
}

trait ExprOneMan_1_Number[T, Entity[_]] extends ExprOneManBase_1[T, Entity] { self: Molecule  =>
  def +(v: T): Entity[T] = entity[T](addOne(dataModel, AttrOp.Plus  , Seq(v)))
  def -(v: T): Entity[T] = entity[T](addOne(dataModel, AttrOp.Minus , Seq(v)))
  def *(v: T): Entity[T] = entity[T](addOne(dataModel, AttrOp.Times , Seq(v)))
  def /(v: T): Entity[T] = entity[T](addOne(dataModel, AttrOp.Divide, Seq(v)))
  def negate : Entity[T] = entity[T](addOne(dataModel, AttrOp.Negate, Nil   ))
  def abs    : Entity[T] = entity[T](addOne(dataModel, AttrOp.Abs   , Nil   ))
  def absNeg : Entity[T] = entity[T](addOne(dataModel, AttrOp.AbsNeg, Nil   ))

  def apply(kw: sum)     : Entity[T     ] = entity[T     ](asIs     (dataModel, kw))
  def apply(kw: median)  : Entity[Double] = entity[Double](toDouble (dataModel, kw))
  def apply(kw: avg)     : Entity[Double] = entity[Double](toDouble (dataModel, kw))
  def apply(kw: variance): Entity[Double] = entity[Double](toDouble (dataModel, kw))
  def apply(kw: stddev)  : Entity[Double] = entity[Double](toDouble (dataModel, kw))
}

trait ExprOneMan_1_Boolean[T, Entity[_]] extends ExprOneManBase_1[T, Entity] { self: Molecule  =>
  def &&(bool: T): Entity[T] = entity[T](addOne(dataModel, AttrOp.And, Seq(bool)))
  def ||(bool: T): Entity[T] = entity[T](addOne(dataModel, AttrOp.Or , Seq(bool)))
  def !          : Entity[T] = entity[T](addOne(dataModel, AttrOp.Not, Nil      ))
}

