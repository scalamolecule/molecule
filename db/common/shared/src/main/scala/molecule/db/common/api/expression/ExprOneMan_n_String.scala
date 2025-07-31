package molecule.db.common.api.expression

import molecule.base.metaModel.CardOne
import molecule.core.dataModel.*
import molecule.core.dataModel.Keywords.*
import molecule.db.common.api.*
import molecule.db.common.ops.ModelTransformations_.*
import scala.Tuple.{:*, Init}


trait ExprOneMan_n_String[T, Tpl <: Tuple, Entity[_ <: Tuple]](
  entity: [tpl <: Tuple] => DataModel => Entity[tpl]
) extends CardOne { self: Molecule =>
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

  def apply(a: Molecule_0 & CardOne)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Eq , a))
  def not  (a: Molecule_0 & CardOne)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Neq, a))
  def <    (a: Molecule_0 & CardOne)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Lt , a))
  def <=   (a: Molecule_0 & CardOne)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Le , a))
  def >    (a: Molecule_0 & CardOne)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Gt , a))
  def >=   (a: Molecule_0 & CardOne)(using ec: DummyImplicit): Entity[Tpl] = entity[Tpl](filterAttr(dataModel, Ge , a))

  def apply(kw: mins)    : Entity[Init[Tpl] :* Set[T]] = entity[Init[Tpl] :* Set[T]](asIs (dataModel, kw, Some(kw.n)))
  def apply(kw: maxs)    : Entity[Init[Tpl] :* Set[T]] = entity[Init[Tpl] :* Set[T]](asIs (dataModel, kw, Some(kw.n)))
  def apply(kw: samples) : Entity[Init[Tpl] :* Set[T]] = entity[Init[Tpl] :* Set[T]](asIs (dataModel, kw, Some(kw.n)))
  def apply(kw: distinct): Entity[Init[Tpl] :* Set[T]] = entity[Init[Tpl] :* Set[T]](asIs (dataModel, kw            ))

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


trait ExprOneMan_n_String_Aggr[T, Tpl <: Tuple, Entity[_, _ <: Tuple]](
  entity: [t, tpl <: Tuple] => DataModel => Entity[t, tpl]
) extends CardOne { self: Molecule =>
  def apply(kw: count)        : Entity[Int, Init[Tpl] :* Int] = entity[Int, Init[Tpl] :* Int](toInt(dataModel, kw))
  def apply(kw: countDistinct): Entity[Int, Init[Tpl] :* Int] = entity[Int, Init[Tpl] :* Int](toInt(dataModel, kw))
  def apply(kw: min)          : Entity[T  , Init[Tpl] :* T  ] = entity[T  , Init[Tpl] :* T  ](asIs (dataModel, kw))
  def apply(kw: max)          : Entity[T  , Init[Tpl] :* T  ] = entity[T  , Init[Tpl] :* T  ](asIs (dataModel, kw))
  def apply(kw: sample)       : Entity[T  , Init[Tpl] :* T  ] = entity[T  , Init[Tpl] :* T  ](asIs (dataModel, kw))
}


trait ExprOneMan_n_String_AggrOps[T, Entity <: Molecule](
  entity: DataModel => Entity
) extends CardOne { self: Molecule =>
  def apply(v    : T): Entity = entity(addOne(dataModel, Eq , Seq(v)    ))
  def not  (v    : T): Entity = entity(addOne(dataModel, Neq, Seq(v)    ))
  def <    (upper: T): Entity = entity(addOne(dataModel, Lt , Seq(upper)))
  def <=   (upper: T): Entity = entity(addOne(dataModel, Le , Seq(upper)))
  def >    (lower: T): Entity = entity(addOne(dataModel, Gt , Seq(lower)))
  def >=   (lower: T): Entity = entity(addOne(dataModel, Ge , Seq(lower)))

  def apply(v    : qm): Entity = entity(addOne(dataModel, Eq , Nil, true))
  def not  (v    : qm): Entity = entity(addOne(dataModel, Neq, Nil, true))
  def <    (upper: qm): Entity = entity(addOne(dataModel, Lt , Nil, true))
  def <=   (upper: qm): Entity = entity(addOne(dataModel, Le , Nil, true))
  def >    (lower: qm): Entity = entity(addOne(dataModel, Gt , Nil, true))
  def >=   (lower: qm): Entity = entity(addOne(dataModel, Ge , Nil, true))
}