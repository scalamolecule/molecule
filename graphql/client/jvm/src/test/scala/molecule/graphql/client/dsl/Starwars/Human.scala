/*
* AUTO-GENERATED Molecule DSL boilerplate code for namespace `Human`
*
* To change:
* 1. Edit data model in molecule.graphql.client.dsl.dataModel.Starwars
* 2. `sbt compile -Dmolecule=true`
*/
package molecule.graphql.client.dsl.Starwars

import java.time._
import molecule.base.ast._
import molecule.boilerplate.api.Keywords._
import molecule.boilerplate.api._
import molecule.boilerplate.api.expression._
import molecule.boilerplate.ast.Model
import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag


trait Human_base {
  protected lazy val id_man        : AttrOneManID     = AttrOneManID    ("Human", "id"        , coord = Seq(2, 9    ))
  protected lazy val name_man      : AttrOneManString = AttrOneManString("Human", "name"      , coord = Seq(2, 10   ))
  protected lazy val friends_man   : AttrSetManID     = AttrSetManID    ("Human", "friends"   , coord = Seq(2, 11, 0), refNs = Some("Character"))
  protected lazy val appearsIn_man : AttrSetManString = AttrSetManString("Human", "appearsIn" , coord = Seq(2, 12   ), validator = Some(validation_appearsIn))
  protected lazy val homePlanet_man: AttrOneManString = AttrOneManString("Human", "homePlanet", coord = Seq(2, 13   ))
  
  protected lazy val name_opt      : AttrOneOptString = AttrOneOptString("Human", "name"      , coord = Seq(2, 10   ))
  protected lazy val friends_opt   : AttrSetOptID     = AttrSetOptID    ("Human", "friends"   , coord = Seq(2, 11, 0), refNs = Some("Character"))
  protected lazy val appearsIn_opt : AttrSetOptString = AttrSetOptString("Human", "appearsIn" , coord = Seq(2, 12   ), validator = Some(validation_appearsIn))
  protected lazy val homePlanet_opt: AttrOneOptString = AttrOneOptString("Human", "homePlanet", coord = Seq(2, 13   ))
  
  protected lazy val id_tac        : AttrOneTacID     = AttrOneTacID    ("Human", "id"        , coord = Seq(2, 9    ))
  protected lazy val name_tac      : AttrOneTacString = AttrOneTacString("Human", "name"      , coord = Seq(2, 10   ))
  protected lazy val friends_tac   : AttrSetTacID     = AttrSetTacID    ("Human", "friends"   , coord = Seq(2, 11, 0), refNs = Some("Character"))
  protected lazy val appearsIn_tac : AttrSetTacString = AttrSetTacString("Human", "appearsIn" , coord = Seq(2, 12   ), validator = Some(validation_appearsIn))
  protected lazy val homePlanet_tac: AttrOneTacString = AttrOneTacString("Human", "homePlanet", coord = Seq(2, 13   ))
  
  private lazy val validation_appearsIn = new ValidateString {
    override def validate(v: String): Seq[String] = {
      val ok: String => Boolean = v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)
      if (ok(v)) Nil else Seq(s"""Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")""")
    }
  }
}

private object Human extends Human_0[Nothing](Nil) {
//object Human extends Human_0[Nothing](Nil) {
  final def apply(id: String, ids: String*)                       = new Human_0[String](List(AttrOneTacID("Human", "id", Eq, id +: ids                  , coord = Seq(2, 9))))
  final def apply(ids: Iterable[String])                          = new Human_0[String](List(AttrOneTacID("Human", "id", Eq, ids.toSeq                  , coord = Seq(2, 9))))
  final def apply(id: Long, ids: Long*)                           = new Human_0[String](List(AttrOneTacID("Human", "id", Eq, (id +: ids).map(_.toString), coord = Seq(2, 9))))
  final def apply(ids: Iterable[Long])(implicit x: DummyImplicit) = new Human_0[String](List(AttrOneTacID("Human", "id", Eq, ids.toSeq.map(_.toString)  , coord = Seq(2, 9))))
}


class Human_0[t: ClassTag](override val elements: List[Element]) extends Human_base with ModelOps_0[t, Human_0, Human_1] {
  lazy val id           = new Human_1[String             , String](elements :+ id_man        ) with ExprOneMan_1        [String             , String, Human_1, Human_2] with CardOne
  lazy val name         = new Human_1[String             , String](elements :+ name_man      ) with ExprOneMan_1_String [String             , String, Human_1, Human_2] with CardOne
  lazy val friends      = new Human_1[Set[String]        , String](elements :+ friends_man   ) with ExprSetMan_1        [Set[String]        , String, Human_1, Human_2] with CardSet
  lazy val appearsIn    = new Human_1[Set[String]        , String](elements :+ appearsIn_man ) with ExprSetMan_1        [Set[String]        , String, Human_1, Human_2] with CardSet
  lazy val homePlanet   = new Human_1[String             , String](elements :+ homePlanet_man) with ExprOneMan_1_String [String             , String, Human_1, Human_2] with CardOne

  lazy val name_?       = new Human_1[Option[String]     , String](elements :+ name_opt      ) with ExprOneOpt_1        [Option[String]     , String, Human_1, Human_2] with CardOne
  lazy val friends_?    = new Human_1[Option[Set[String]], String](elements :+ friends_opt   ) with ExprSetOpt_1        [Option[Set[String]], String, Human_1, Human_2] with CardSet
  lazy val appearsIn_?  = new Human_1[Option[Set[String]], String](elements :+ appearsIn_opt ) with ExprSetOpt_1        [Option[Set[String]], String, Human_1, Human_2] with CardSet
  lazy val homePlanet_? = new Human_1[Option[String]     , String](elements :+ homePlanet_opt) with ExprOneOpt_1        [Option[String]     , String, Human_1, Human_2] with CardOne

  lazy val id_          = new Human_0[                     String](elements :+ id_tac        ) with ExprOneTac_0        [                     String, Human_0, Human_1] with CardOne
  lazy val name_        = new Human_0[                     String](elements :+ name_tac      ) with ExprOneTac_0_String [                     String, Human_0, Human_1] with CardOne
  lazy val friends_     = new Human_0[                     String](elements :+ friends_tac   ) with ExprSetTac_0        [                     String, Human_0, Human_1] with CardSet
  lazy val appearsIn_   = new Human_0[                     String](elements :+ appearsIn_tac ) with ExprSetTac_0        [                     String, Human_0, Human_1] with CardSet
  lazy val homePlanet_  = new Human_0[                     String](elements :+ homePlanet_tac) with ExprOneTac_0_String [                     String, Human_0, Human_1] with CardOne

  override protected def _exprOneTac(op: Op, vs  : Seq[t]             ) = new Human_0[t](addOne(elements, op, vs)) with CardOne
  override protected def _exprSetTac(op: Op, vs  : Seq[Set[t]]        ) = new Human_0[t](addSet(elements, op, vs)) with CardSet
//  override protected def _exprArrTac[t1 <: t: ClassTag](op: Op, vs  : Seq[Array[t]]      ) = new Human_0[t](addArr(elements, op, vs)) with CardSet
//  override protected def _exprMapTac(op: Op, maps: Seq[Map[String, t]]) = new Human_0[t](addMap(elements, op, maps)) with CardMap
//  override protected def _exprMapTaK(op: Op, keys: Seq[String        ]) = new Human_0[t](addMap(elements, op, Seq(keys.map(k => k -> null.asInstanceOf[t]).toMap))) with CardMap
//  override protected def _exprMapTaV(op: Op, vs  : Seq[t             ]) = new Human_0[t](addMap(elements, op, Seq(vs.zipWithIndex.map { case (v, i) => s"_k$i" -> v }.toMap))) with CardMap


  override protected def _attrTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]) = new Human_0[   t](filterAttr(elements, op, a))
  override protected def _attrMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]) = new Human_1[X, t](filterAttr(elements, op, a))

  trait NestedInit extends NestedOp_0 with Nested_0 { self: Molecule =>
    override protected def _nestedMan[NestedTpl](nestedElements: List[Element]): NestedInit_00[NestedTpl] = new NestedInit_00(self.elements.init :+ Nested   (self.elements.last.asInstanceOf[Ref], nestedElements))
    override protected def _nestedOpt[NestedTpl](nestedElements: List[Element]): NestedInit_00[NestedTpl] = new NestedInit_00(self.elements.init :+ NestedOpt(self.elements.last.asInstanceOf[Ref], nestedElements))
  }

  object Friends extends Character_0[t](elements :+ Model.Ref("Human", "friends", "Character", CardSet, false, Seq(2, 11, 0))) with NestedInit
}


class Human_1[A, t](override val elements: List[Element]) extends Human_base with ModelOps_1[A, t, Human_1, Human_2] {
  lazy val id           = new Human_2[A, String             , String](elements :+ id_man        ) with ExprOneMan_2        [A, String             , String, Human_2, Human_3] with CardOne
  lazy val name         = new Human_2[A, String             , String](elements :+ name_man      ) with ExprOneMan_2_String [A, String             , String, Human_2, Human_3] with CardOne
  lazy val friends      = new Human_2[A, Set[String]        , String](elements :+ friends_man   ) with ExprSetMan_2        [A, Set[String]        , String, Human_2, Human_3] with CardSet
  lazy val appearsIn    = new Human_2[A, Set[String]        , String](elements :+ appearsIn_man ) with ExprSetMan_2        [A, Set[String]        , String, Human_2, Human_3] with CardSet
  lazy val homePlanet   = new Human_2[A, String             , String](elements :+ homePlanet_man) with ExprOneMan_2_String [A, String             , String, Human_2, Human_3] with CardOne

  lazy val name_?       = new Human_2[A, Option[String]     , String](elements :+ name_opt      ) with ExprOneOpt_2        [A, Option[String]     , String, Human_2, Human_3] with CardOne
  lazy val friends_?    = new Human_2[A, Option[Set[String]], String](elements :+ friends_opt   ) with ExprSetOpt_2        [A, Option[Set[String]], String, Human_2, Human_3] with CardSet
  lazy val appearsIn_?  = new Human_2[A, Option[Set[String]], String](elements :+ appearsIn_opt ) with ExprSetOpt_2        [A, Option[Set[String]], String, Human_2, Human_3] with CardSet
  lazy val homePlanet_? = new Human_2[A, Option[String]     , String](elements :+ homePlanet_opt) with ExprOneOpt_2        [A, Option[String]     , String, Human_2, Human_3] with CardOne

  lazy val id_          = new Human_1[A                     , String](elements :+ id_tac        ) with ExprOneTac_1        [A                     , String, Human_1, Human_2] with CardOne
  lazy val name_        = new Human_1[A                     , String](elements :+ name_tac      ) with ExprOneTac_1_String [A                     , String, Human_1, Human_2] with CardOne
  lazy val friends_     = new Human_1[A                     , String](elements :+ friends_tac   ) with ExprSetTac_1        [A                     , String, Human_1, Human_2] with CardSet
  lazy val appearsIn_   = new Human_1[A                     , String](elements :+ appearsIn_tac ) with ExprSetTac_1        [A                     , String, Human_1, Human_2] with CardSet
  lazy val homePlanet_  = new Human_1[A                     , String](elements :+ homePlanet_tac) with ExprOneTac_1_String [A                     , String, Human_1, Human_2] with CardOne

  override protected def _aggrInt   (kw: Kw                           ) = new Human_1[Int   , Int   ](toInt    (elements, kw    )) with SortAttrs_1[Int   , Int   , Human_1]
  override protected def _aggrDouble(kw: Kw                           ) = new Human_1[Double, Double](toDouble (elements, kw    )) with SortAttrs_1[Double, Double, Human_1]
  override protected def _aggrDist  (kw: Kw                           ) = new Human_1[Set[A], t     ](asIs     (elements, kw    ))
  override protected def _aggrSet   (kw: Kw, n: Option[Int]           ) = new Human_1[Set[t], t     ](asIs     (elements, kw, n ))
  override protected def _aggrTsort (kw: Kw                           ) = new Human_1[A     , t     ](asIs     (elements, kw    )) with SortAttrs_1[A     , t     , Human_1]
  override protected def _aggrT     (kw: Kw                           ) = new Human_1[A     , t     ](asIs     (elements, kw    ))

  override protected def _exprOneMan(op: Op, vs: Seq[t]               ) = new Human_1[A     , t     ](addOne   (elements, op, vs)) with SortAttrs_1[A     , t     , Human_1] with CardOne
  override protected def _exprOneTac(op: Op, vs: Seq[t]               ) = new Human_1[A     , t     ](addOne   (elements, op, vs)) with CardOne
  override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]       ) = new Human_1[A     , t     ](addOptOne(elements, op, vs)) with SortAttrs_1[A     , t     , Human_1]

  override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]          ) = new Human_1[A     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]          ) = new Human_1[A     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]  ) = new Human_1[A     , t     ](addOptSet(elements, op, vs))

//  override protected def _exprArrMan(op: Op, vs: Seq[Array[t]]        ) = new Human_1[A     , t    ](addArr   (elements, op, vs)) with CardArr
//  override protected def _exprArrTac(op: Op, vs: Seq[Array[t]]        ) = new Human_1[A     , t    ](addArr   (elements, op, vs)) with CardArr
//  override protected def _exprArrOpt(op: Op, vs: Option[Seq[Array[t]]]) = new Human_1[A     , t    ](addOptArr(elements, op, vs))

//  override protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]) = new Human_1[A     , t     ](addMap   (elements, op, maps)) with CardMap
//  override protected def _exprMapMaK(op: Op, keys: Seq[String        ]) = new Human_1[A     , t     ](addMap   (elements, op, Seq(keys.map(k => k -> null.asInstanceOf[t]).toMap))) with CardMap
//  override protected def _exprMapMaV(op: Op, vs  : Seq[t             ]) = new Human_1[A     , t     ](addMap   (elements, op, Seq(vs.zipWithIndex.map { case (v, i) => s"_k$i" -> v }.toMap))) with CardMap
//  override protected def _exprMapTac(op: Op, maps: Seq[Map[String, t]]) = new Human_1[A     , t     ](addMap   (elements, op, maps)) with CardMap
//  override protected def _exprMapTaK(op: Op, keys: Seq[String        ]) = new Human_1[A     , t     ](addMap   (elements, op, Seq(keys.map(k => k -> null.asInstanceOf[t]).toMap))) with CardMap
//  override protected def _exprMapTaV(op: Op, vs  : Seq[t             ]) = new Human_1[A     , t     ](addMap   (elements, op, Seq(vs.zipWithIndex.map { case (v, i) => s"_k$i" -> v }.toMap))) with CardMap
//  override protected def _exprMapOpK(op: Op, keys: Option[Seq[String]]) = new Human_1[A     , t     ](addOptMap(elements, op, keys.map(keys => Seq(keys.map(k => k -> null.asInstanceOf[t]).toMap))))

  override protected def _sort(sort: String) = new Human_1[A, t](addSort(elements, sort))

  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne) = new Human_1[A,    t](filterAttr(elements, op, a)) with SortAttrs_1[A,    t, Human_1]
  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[A, t, ns1, ns2]             ) = new Human_2[A, A, t](filterAttr(elements, op, a)) with SortAttrs_2[A, A, t, Human_2]
  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ) = new Human_1[A,    t](filterAttr(elements, op, a))
  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ) = new Human_2[A, X, t](filterAttr(elements, op, a))

  trait NestedInit extends NestedOp_1[A] with Nested_1[A] { self: Molecule =>
    override protected def _nestedMan[NestedTpl](nestedElements: List[Element]): NestedInit_01[A, NestedTpl] = new NestedInit_01(self.elements.init :+ Nested   (self.elements.last.asInstanceOf[Ref], nestedElements))
    override protected def _nestedOpt[NestedTpl](nestedElements: List[Element]): NestedInit_01[A, NestedTpl] = new NestedInit_01(self.elements.init :+ NestedOpt(self.elements.last.asInstanceOf[Ref], nestedElements))
  }

  object Friends extends Character_1[A, t](elements :+ Model.Ref("Human", "friends", "Character", CardSet, false, Seq(2, 11, 0))) with NestedInit
}


class Human_2[A, B, t](override val elements: List[Element]) extends Human_base with ModelOps_2[A, B, t, Human_2, Human_3] {
  lazy val id           = new Human_3[A, B, String             , String](elements :+ id_man        ) with ExprOneMan_3        [A, B, String             , String, Human_3, Human_4] with CardOne
  lazy val name         = new Human_3[A, B, String             , String](elements :+ name_man      ) with ExprOneMan_3_String [A, B, String             , String, Human_3, Human_4] with CardOne
  lazy val friends      = new Human_3[A, B, Set[String]        , String](elements :+ friends_man   ) with ExprSetMan_3        [A, B, Set[String]        , String, Human_3, Human_4] with CardSet
  lazy val appearsIn    = new Human_3[A, B, Set[String]        , String](elements :+ appearsIn_man ) with ExprSetMan_3        [A, B, Set[String]        , String, Human_3, Human_4] with CardSet
  lazy val homePlanet   = new Human_3[A, B, String             , String](elements :+ homePlanet_man) with ExprOneMan_3_String [A, B, String             , String, Human_3, Human_4] with CardOne

  lazy val name_?       = new Human_3[A, B, Option[String]     , String](elements :+ name_opt      ) with ExprOneOpt_3        [A, B, Option[String]     , String, Human_3, Human_4] with CardOne
  lazy val friends_?    = new Human_3[A, B, Option[Set[String]], String](elements :+ friends_opt   ) with ExprSetOpt_3        [A, B, Option[Set[String]], String, Human_3, Human_4] with CardSet
  lazy val appearsIn_?  = new Human_3[A, B, Option[Set[String]], String](elements :+ appearsIn_opt ) with ExprSetOpt_3        [A, B, Option[Set[String]], String, Human_3, Human_4] with CardSet
  lazy val homePlanet_? = new Human_3[A, B, Option[String]     , String](elements :+ homePlanet_opt) with ExprOneOpt_3        [A, B, Option[String]     , String, Human_3, Human_4] with CardOne

  lazy val id_          = new Human_2[A, B                     , String](elements :+ id_tac        ) with ExprOneTac_2        [A, B                     , String, Human_2, Human_3] with CardOne
  lazy val name_        = new Human_2[A, B                     , String](elements :+ name_tac      ) with ExprOneTac_2_String [A, B                     , String, Human_2, Human_3] with CardOne
  lazy val friends_     = new Human_2[A, B                     , String](elements :+ friends_tac   ) with ExprSetTac_2        [A, B                     , String, Human_2, Human_3] with CardSet
  lazy val appearsIn_   = new Human_2[A, B                     , String](elements :+ appearsIn_tac ) with ExprSetTac_2        [A, B                     , String, Human_2, Human_3] with CardSet
  lazy val homePlanet_  = new Human_2[A, B                     , String](elements :+ homePlanet_tac) with ExprOneTac_2_String [A, B                     , String, Human_2, Human_3] with CardOne

  override protected def _aggrInt   (kw: Kw                         ) = new Human_2[A, Int   , Int   ](toInt    (elements, kw    )) with SortAttrs_2[A, Int   , Int   , Human_2]
  override protected def _aggrDouble(kw: Kw                         ) = new Human_2[A, Double, Double](toDouble (elements, kw    )) with SortAttrs_2[A, Double, Double, Human_2]
  override protected def _aggrDist  (kw: Kw                         ) = new Human_2[A, Set[B], t     ](asIs     (elements, kw    ))
  override protected def _aggrSet   (kw: Kw, n: Option[Int]         ) = new Human_2[A, Set[t], t     ](asIs     (elements, kw, n ))
  override protected def _aggrTsort (kw: Kw                         ) = new Human_2[A, B     , t     ](asIs     (elements, kw    )) with SortAttrs_2[A, B     , t     , Human_2]
  override protected def _aggrT     (kw: Kw                         ) = new Human_2[A, B     , t     ](asIs     (elements, kw    ))
  override protected def _exprOneMan(op: Op, vs: Seq[t]             ) = new Human_2[A, B     , t     ](addOne   (elements, op, vs)) with SortAttrs_2[A, B     , t     , Human_2] with CardOne
  override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]     ) = new Human_2[A, B     , t     ](addOptOne(elements, op, vs)) with SortAttrs_2[A, B     , t     , Human_2]
  override protected def _exprOneTac(op: Op, vs: Seq[t]             ) = new Human_2[A, B     , t     ](addOne   (elements, op, vs)) with CardOne
  override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        ) = new Human_2[A, B     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]) = new Human_2[A, B     , t     ](addOptSet(elements, op, vs))
  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        ) = new Human_2[A, B     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _sort      (sort: String                   ) = new Human_2[A, B     , t     ](addSort  (elements, sort  ))

  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne) = new Human_2[A, B,    t](filterAttr(elements, op, a)) with SortAttrs_2[A, B,    t, Human_2]
  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[B, t, ns1, ns2]             ) = new Human_3[A, B, B, t](filterAttr(elements, op, a)) with SortAttrs_3[A, B, B, t, Human_3]
  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ) = new Human_2[A, B,    t](filterAttr(elements, op, a))
  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ) = new Human_3[A, B, X, t](filterAttr(elements, op, a))

  trait NestedInit extends NestedOp_2[A, B] with Nested_2[A, B] { self: Molecule =>
    override protected def _nestedMan[NestedTpl](nestedElements: List[Element]): NestedInit_02[A, B, NestedTpl] = new NestedInit_02(self.elements.init :+ Nested   (self.elements.last.asInstanceOf[Ref], nestedElements))
    override protected def _nestedOpt[NestedTpl](nestedElements: List[Element]): NestedInit_02[A, B, NestedTpl] = new NestedInit_02(self.elements.init :+ NestedOpt(self.elements.last.asInstanceOf[Ref], nestedElements))
  }

  object Friends extends Character_2[A, B, t](elements :+ Model.Ref("Human", "friends", "Character", CardSet, false, Seq(2, 11, 0))) with NestedInit
}


class Human_3[A, B, C, t](override val elements: List[Element]) extends Human_base with ModelOps_3[A, B, C, t, Human_3, Human_4] {
  lazy val id           = new Human_4[A, B, C, String             , String](elements :+ id_man        ) with ExprOneMan_4        [A, B, C, String             , String, Human_4, Human_5] with CardOne
  lazy val name         = new Human_4[A, B, C, String             , String](elements :+ name_man      ) with ExprOneMan_4_String [A, B, C, String             , String, Human_4, Human_5] with CardOne
  lazy val friends      = new Human_4[A, B, C, Set[String]        , String](elements :+ friends_man   ) with ExprSetMan_4        [A, B, C, Set[String]        , String, Human_4, Human_5] with CardSet
  lazy val appearsIn    = new Human_4[A, B, C, Set[String]        , String](elements :+ appearsIn_man ) with ExprSetMan_4        [A, B, C, Set[String]        , String, Human_4, Human_5] with CardSet
  lazy val homePlanet   = new Human_4[A, B, C, String             , String](elements :+ homePlanet_man) with ExprOneMan_4_String [A, B, C, String             , String, Human_4, Human_5] with CardOne

  lazy val name_?       = new Human_4[A, B, C, Option[String]     , String](elements :+ name_opt      ) with ExprOneOpt_4        [A, B, C, Option[String]     , String, Human_4, Human_5] with CardOne
  lazy val friends_?    = new Human_4[A, B, C, Option[Set[String]], String](elements :+ friends_opt   ) with ExprSetOpt_4        [A, B, C, Option[Set[String]], String, Human_4, Human_5] with CardSet
  lazy val appearsIn_?  = new Human_4[A, B, C, Option[Set[String]], String](elements :+ appearsIn_opt ) with ExprSetOpt_4        [A, B, C, Option[Set[String]], String, Human_4, Human_5] with CardSet
  lazy val homePlanet_? = new Human_4[A, B, C, Option[String]     , String](elements :+ homePlanet_opt) with ExprOneOpt_4        [A, B, C, Option[String]     , String, Human_4, Human_5] with CardOne

  lazy val id_          = new Human_3[A, B, C                     , String](elements :+ id_tac        ) with ExprOneTac_3        [A, B, C                     , String, Human_3, Human_4] with CardOne
  lazy val name_        = new Human_3[A, B, C                     , String](elements :+ name_tac      ) with ExprOneTac_3_String [A, B, C                     , String, Human_3, Human_4] with CardOne
  lazy val friends_     = new Human_3[A, B, C                     , String](elements :+ friends_tac   ) with ExprSetTac_3        [A, B, C                     , String, Human_3, Human_4] with CardSet
  lazy val appearsIn_   = new Human_3[A, B, C                     , String](elements :+ appearsIn_tac ) with ExprSetTac_3        [A, B, C                     , String, Human_3, Human_4] with CardSet
  lazy val homePlanet_  = new Human_3[A, B, C                     , String](elements :+ homePlanet_tac) with ExprOneTac_3_String [A, B, C                     , String, Human_3, Human_4] with CardOne

  override protected def _aggrInt   (kw: Kw                         ) = new Human_3[A, B, Int   , Int   ](toInt    (elements, kw    )) with SortAttrs_3[A, B, Int   , Int   , Human_3]
  override protected def _aggrDouble(kw: Kw                         ) = new Human_3[A, B, Double, Double](toDouble (elements, kw    )) with SortAttrs_3[A, B, Double, Double, Human_3]
  override protected def _aggrDist  (kw: Kw                         ) = new Human_3[A, B, Set[C], t     ](asIs     (elements, kw    ))
  override protected def _aggrSet   (kw: Kw, n: Option[Int]         ) = new Human_3[A, B, Set[t], t     ](asIs     (elements, kw, n ))
  override protected def _aggrTsort (kw: Kw                         ) = new Human_3[A, B, C     , t     ](asIs     (elements, kw    )) with SortAttrs_3[A, B, C     , t     , Human_3]
  override protected def _aggrT     (kw: Kw                         ) = new Human_3[A, B, C     , t     ](asIs     (elements, kw    ))
  override protected def _exprOneMan(op: Op, vs: Seq[t]             ) = new Human_3[A, B, C     , t     ](addOne   (elements, op, vs)) with SortAttrs_3[A, B, C     , t     , Human_3] with CardOne
  override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]     ) = new Human_3[A, B, C     , t     ](addOptOne(elements, op, vs)) with SortAttrs_3[A, B, C     , t     , Human_3]
  override protected def _exprOneTac(op: Op, vs: Seq[t]             ) = new Human_3[A, B, C     , t     ](addOne   (elements, op, vs)) with CardOne
  override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        ) = new Human_3[A, B, C     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]) = new Human_3[A, B, C     , t     ](addOptSet(elements, op, vs))
  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        ) = new Human_3[A, B, C     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _sort      (sort: String                   ) = new Human_3[A, B, C     , t     ](addSort  (elements, sort  ))

  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne) = new Human_3[A, B, C,    t](filterAttr(elements, op, a)) with SortAttrs_3[A, B, C,    t, Human_3]
  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[C, t, ns1, ns2]             ) = new Human_4[A, B, C, C, t](filterAttr(elements, op, a)) with SortAttrs_4[A, B, C, C, t, Human_4]
  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ) = new Human_3[A, B, C,    t](filterAttr(elements, op, a))
  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ) = new Human_4[A, B, C, X, t](filterAttr(elements, op, a))

  trait NestedInit extends NestedOp_3[A, B, C] with Nested_3[A, B, C] { self: Molecule =>
    override protected def _nestedMan[NestedTpl](nestedElements: List[Element]): NestedInit_03[A, B, C, NestedTpl] = new NestedInit_03(self.elements.init :+ Nested   (self.elements.last.asInstanceOf[Ref], nestedElements))
    override protected def _nestedOpt[NestedTpl](nestedElements: List[Element]): NestedInit_03[A, B, C, NestedTpl] = new NestedInit_03(self.elements.init :+ NestedOpt(self.elements.last.asInstanceOf[Ref], nestedElements))
  }

  object Friends extends Character_3[A, B, C, t](elements :+ Model.Ref("Human", "friends", "Character", CardSet, false, Seq(2, 11, 0))) with NestedInit
}


class Human_4[A, B, C, D, t](override val elements: List[Element]) extends Human_base with ModelOps_4[A, B, C, D, t, Human_4, Human_5] {
  lazy val id           = new Human_5[A, B, C, D, String             , String](elements :+ id_man        ) with ExprOneMan_5        [A, B, C, D, String             , String, Human_5, Human_6] with CardOne
  lazy val name         = new Human_5[A, B, C, D, String             , String](elements :+ name_man      ) with ExprOneMan_5_String [A, B, C, D, String             , String, Human_5, Human_6] with CardOne
  lazy val friends      = new Human_5[A, B, C, D, Set[String]        , String](elements :+ friends_man   ) with ExprSetMan_5        [A, B, C, D, Set[String]        , String, Human_5, Human_6] with CardSet
  lazy val appearsIn    = new Human_5[A, B, C, D, Set[String]        , String](elements :+ appearsIn_man ) with ExprSetMan_5        [A, B, C, D, Set[String]        , String, Human_5, Human_6] with CardSet
  lazy val homePlanet   = new Human_5[A, B, C, D, String             , String](elements :+ homePlanet_man) with ExprOneMan_5_String [A, B, C, D, String             , String, Human_5, Human_6] with CardOne

  lazy val name_?       = new Human_5[A, B, C, D, Option[String]     , String](elements :+ name_opt      ) with ExprOneOpt_5        [A, B, C, D, Option[String]     , String, Human_5, Human_6] with CardOne
  lazy val friends_?    = new Human_5[A, B, C, D, Option[Set[String]], String](elements :+ friends_opt   ) with ExprSetOpt_5        [A, B, C, D, Option[Set[String]], String, Human_5, Human_6] with CardSet
  lazy val appearsIn_?  = new Human_5[A, B, C, D, Option[Set[String]], String](elements :+ appearsIn_opt ) with ExprSetOpt_5        [A, B, C, D, Option[Set[String]], String, Human_5, Human_6] with CardSet
  lazy val homePlanet_? = new Human_5[A, B, C, D, Option[String]     , String](elements :+ homePlanet_opt) with ExprOneOpt_5        [A, B, C, D, Option[String]     , String, Human_5, Human_6] with CardOne

  lazy val id_          = new Human_4[A, B, C, D                     , String](elements :+ id_tac        ) with ExprOneTac_4        [A, B, C, D                     , String, Human_4, Human_5] with CardOne
  lazy val name_        = new Human_4[A, B, C, D                     , String](elements :+ name_tac      ) with ExprOneTac_4_String [A, B, C, D                     , String, Human_4, Human_5] with CardOne
  lazy val friends_     = new Human_4[A, B, C, D                     , String](elements :+ friends_tac   ) with ExprSetTac_4        [A, B, C, D                     , String, Human_4, Human_5] with CardSet
  lazy val appearsIn_   = new Human_4[A, B, C, D                     , String](elements :+ appearsIn_tac ) with ExprSetTac_4        [A, B, C, D                     , String, Human_4, Human_5] with CardSet
  lazy val homePlanet_  = new Human_4[A, B, C, D                     , String](elements :+ homePlanet_tac) with ExprOneTac_4_String [A, B, C, D                     , String, Human_4, Human_5] with CardOne

  override protected def _aggrInt   (kw: Kw                         ) = new Human_4[A, B, C, Int   , Int   ](toInt    (elements, kw    )) with SortAttrs_4[A, B, C, Int   , Int   , Human_4]
  override protected def _aggrDouble(kw: Kw                         ) = new Human_4[A, B, C, Double, Double](toDouble (elements, kw    )) with SortAttrs_4[A, B, C, Double, Double, Human_4]
  override protected def _aggrDist  (kw: Kw                         ) = new Human_4[A, B, C, Set[D], t     ](asIs     (elements, kw    ))
  override protected def _aggrSet   (kw: Kw, n: Option[Int]         ) = new Human_4[A, B, C, Set[t], t     ](asIs     (elements, kw, n ))
  override protected def _aggrTsort (kw: Kw                         ) = new Human_4[A, B, C, D     , t     ](asIs     (elements, kw    )) with SortAttrs_4[A, B, C, D     , t     , Human_4]
  override protected def _aggrT     (kw: Kw                         ) = new Human_4[A, B, C, D     , t     ](asIs     (elements, kw    ))
  override protected def _exprOneMan(op: Op, vs: Seq[t]             ) = new Human_4[A, B, C, D     , t     ](addOne   (elements, op, vs)) with SortAttrs_4[A, B, C, D     , t     , Human_4] with CardOne
  override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]     ) = new Human_4[A, B, C, D     , t     ](addOptOne(elements, op, vs)) with SortAttrs_4[A, B, C, D     , t     , Human_4]
  override protected def _exprOneTac(op: Op, vs: Seq[t]             ) = new Human_4[A, B, C, D     , t     ](addOne   (elements, op, vs)) with CardOne
  override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        ) = new Human_4[A, B, C, D     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]) = new Human_4[A, B, C, D     , t     ](addOptSet(elements, op, vs))
  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        ) = new Human_4[A, B, C, D     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _sort      (sort: String                   ) = new Human_4[A, B, C, D     , t     ](addSort  (elements, sort  ))

  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne) = new Human_4[A, B, C, D,    t](filterAttr(elements, op, a)) with SortAttrs_4[A, B, C, D,    t, Human_4]
  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[D, t, ns1, ns2]             ) = new Human_5[A, B, C, D, D, t](filterAttr(elements, op, a)) with SortAttrs_5[A, B, C, D, D, t, Human_5]
  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ) = new Human_4[A, B, C, D,    t](filterAttr(elements, op, a))
  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ) = new Human_5[A, B, C, D, X, t](filterAttr(elements, op, a))

  trait NestedInit extends NestedOp_4[A, B, C, D] with Nested_4[A, B, C, D] { self: Molecule =>
    override protected def _nestedMan[NestedTpl](nestedElements: List[Element]): NestedInit_04[A, B, C, D, NestedTpl] = new NestedInit_04(self.elements.init :+ Nested   (self.elements.last.asInstanceOf[Ref], nestedElements))
    override protected def _nestedOpt[NestedTpl](nestedElements: List[Element]): NestedInit_04[A, B, C, D, NestedTpl] = new NestedInit_04(self.elements.init :+ NestedOpt(self.elements.last.asInstanceOf[Ref], nestedElements))
  }

  object Friends extends Character_4[A, B, C, D, t](elements :+ Model.Ref("Human", "friends", "Character", CardSet, false, Seq(2, 11, 0))) with NestedInit
}


class Human_5[A, B, C, D, E, t](override val elements: List[Element]) extends Human_base with ModelOps_5[A, B, C, D, E, t, Human_5, Human_6] {
  lazy val id           = new Human_6[A, B, C, D, E, String             , String](elements :+ id_man        ) with ExprOneMan_6        [A, B, C, D, E, String             , String, Human_6, X8] with CardOne
  lazy val name         = new Human_6[A, B, C, D, E, String             , String](elements :+ name_man      ) with ExprOneMan_6_String [A, B, C, D, E, String             , String, Human_6, X8] with CardOne
  lazy val friends      = new Human_6[A, B, C, D, E, Set[String]        , String](elements :+ friends_man   ) with ExprSetMan_6        [A, B, C, D, E, Set[String]        , String, Human_6, X8] with CardSet
  lazy val appearsIn    = new Human_6[A, B, C, D, E, Set[String]        , String](elements :+ appearsIn_man ) with ExprSetMan_6        [A, B, C, D, E, Set[String]        , String, Human_6, X8] with CardSet
  lazy val homePlanet   = new Human_6[A, B, C, D, E, String             , String](elements :+ homePlanet_man) with ExprOneMan_6_String [A, B, C, D, E, String             , String, Human_6, X8] with CardOne

  lazy val name_?       = new Human_6[A, B, C, D, E, Option[String]     , String](elements :+ name_opt      ) with ExprOneOpt_6        [A, B, C, D, E, Option[String]     , String, Human_6, X8] with CardOne
  lazy val friends_?    = new Human_6[A, B, C, D, E, Option[Set[String]], String](elements :+ friends_opt   ) with ExprSetOpt_6        [A, B, C, D, E, Option[Set[String]], String, Human_6, X8] with CardSet
  lazy val appearsIn_?  = new Human_6[A, B, C, D, E, Option[Set[String]], String](elements :+ appearsIn_opt ) with ExprSetOpt_6        [A, B, C, D, E, Option[Set[String]], String, Human_6, X8] with CardSet
  lazy val homePlanet_? = new Human_6[A, B, C, D, E, Option[String]     , String](elements :+ homePlanet_opt) with ExprOneOpt_6        [A, B, C, D, E, Option[String]     , String, Human_6, X8] with CardOne

  lazy val id_          = new Human_5[A, B, C, D, E                     , String](elements :+ id_tac        ) with ExprOneTac_5        [A, B, C, D, E                     , String, Human_5, Human_6] with CardOne
  lazy val name_        = new Human_5[A, B, C, D, E                     , String](elements :+ name_tac      ) with ExprOneTac_5_String [A, B, C, D, E                     , String, Human_5, Human_6] with CardOne
  lazy val friends_     = new Human_5[A, B, C, D, E                     , String](elements :+ friends_tac   ) with ExprSetTac_5        [A, B, C, D, E                     , String, Human_5, Human_6] with CardSet
  lazy val appearsIn_   = new Human_5[A, B, C, D, E                     , String](elements :+ appearsIn_tac ) with ExprSetTac_5        [A, B, C, D, E                     , String, Human_5, Human_6] with CardSet
  lazy val homePlanet_  = new Human_5[A, B, C, D, E                     , String](elements :+ homePlanet_tac) with ExprOneTac_5_String [A, B, C, D, E                     , String, Human_5, Human_6] with CardOne

  override protected def _aggrInt   (kw: Kw                         ) = new Human_5[A, B, C, D, Int   , Int   ](toInt    (elements, kw    )) with SortAttrs_5[A, B, C, D, Int   , Int   , Human_5]
  override protected def _aggrDouble(kw: Kw                         ) = new Human_5[A, B, C, D, Double, Double](toDouble (elements, kw    )) with SortAttrs_5[A, B, C, D, Double, Double, Human_5]
  override protected def _aggrDist  (kw: Kw                         ) = new Human_5[A, B, C, D, Set[E], t     ](asIs     (elements, kw    ))
  override protected def _aggrSet   (kw: Kw, n: Option[Int]         ) = new Human_5[A, B, C, D, Set[t], t     ](asIs     (elements, kw, n ))
  override protected def _aggrTsort (kw: Kw                         ) = new Human_5[A, B, C, D, E     , t     ](asIs     (elements, kw    )) with SortAttrs_5[A, B, C, D, E     , t     , Human_5]
  override protected def _aggrT     (kw: Kw                         ) = new Human_5[A, B, C, D, E     , t     ](asIs     (elements, kw    ))
  override protected def _exprOneMan(op: Op, vs: Seq[t]             ) = new Human_5[A, B, C, D, E     , t     ](addOne   (elements, op, vs)) with SortAttrs_5[A, B, C, D, E     , t     , Human_5] with CardOne
  override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]     ) = new Human_5[A, B, C, D, E     , t     ](addOptOne(elements, op, vs)) with SortAttrs_5[A, B, C, D, E     , t     , Human_5]
  override protected def _exprOneTac(op: Op, vs: Seq[t]             ) = new Human_5[A, B, C, D, E     , t     ](addOne   (elements, op, vs)) with CardOne
  override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        ) = new Human_5[A, B, C, D, E     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]) = new Human_5[A, B, C, D, E     , t     ](addOptSet(elements, op, vs))
  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        ) = new Human_5[A, B, C, D, E     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _sort      (sort: String                   ) = new Human_5[A, B, C, D, E     , t     ](addSort  (elements, sort  ))

  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne) = new Human_5[A, B, C, D, E,    t](filterAttr(elements, op, a)) with SortAttrs_5[A, B, C, D, E,    t, Human_5]
  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[E, t, ns1, ns2]             ) = new Human_6[A, B, C, D, E, E, t](filterAttr(elements, op, a)) with SortAttrs_6[A, B, C, D, E, E, t, Human_6]
  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ) = new Human_5[A, B, C, D, E,    t](filterAttr(elements, op, a))
  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ) = new Human_6[A, B, C, D, E, X, t](filterAttr(elements, op, a))

  trait NestedInit extends NestedOp_5[A, B, C, D, E] with Nested_5[A, B, C, D, E] { self: Molecule =>
    override protected def _nestedMan[NestedTpl](nestedElements: List[Element]): NestedInit_05[A, B, C, D, E, NestedTpl] = new NestedInit_05(self.elements.init :+ Nested   (self.elements.last.asInstanceOf[Ref], nestedElements))
    override protected def _nestedOpt[NestedTpl](nestedElements: List[Element]): NestedInit_05[A, B, C, D, E, NestedTpl] = new NestedInit_05(self.elements.init :+ NestedOpt(self.elements.last.asInstanceOf[Ref], nestedElements))
  }

  object Friends extends Character_5[A, B, C, D, E, t](elements :+ Model.Ref("Human", "friends", "Character", CardSet, false, Seq(2, 11, 0))) with NestedInit
}


class Human_6[A, B, C, D, E, F, t](override val elements: List[Element]) extends Human_base with ModelOps_6[A, B, C, D, E, F, t, Human_6, X8] {
  lazy val id_          = new Human_6[A, B, C, D, E, F, String](elements :+ id_tac        ) with ExprOneTac_6        [A, B, C, D, E, F, String, Human_6, X8] with CardOne
  lazy val name_        = new Human_6[A, B, C, D, E, F, String](elements :+ name_tac      ) with ExprOneTac_6_String [A, B, C, D, E, F, String, Human_6, X8] with CardOne
  lazy val friends_     = new Human_6[A, B, C, D, E, F, String](elements :+ friends_tac   ) with ExprSetTac_6        [A, B, C, D, E, F, String, Human_6, X8] with CardSet
  lazy val appearsIn_   = new Human_6[A, B, C, D, E, F, String](elements :+ appearsIn_tac ) with ExprSetTac_6        [A, B, C, D, E, F, String, Human_6, X8] with CardSet
  lazy val homePlanet_  = new Human_6[A, B, C, D, E, F, String](elements :+ homePlanet_tac) with ExprOneTac_6_String [A, B, C, D, E, F, String, Human_6, X8] with CardOne

  override protected def _aggrInt   (kw: Kw                         ) = new Human_6[A, B, C, D, E, Int   , Int   ](toInt    (elements, kw    )) with SortAttrs_6[A, B, C, D, E, Int   , Int   , Human_6]
  override protected def _aggrDouble(kw: Kw                         ) = new Human_6[A, B, C, D, E, Double, Double](toDouble (elements, kw    )) with SortAttrs_6[A, B, C, D, E, Double, Double, Human_6]
  override protected def _aggrDist  (kw: Kw                         ) = new Human_6[A, B, C, D, E, Set[F], t     ](asIs     (elements, kw    ))
  override protected def _aggrSet   (kw: Kw, n: Option[Int]         ) = new Human_6[A, B, C, D, E, Set[t], t     ](asIs     (elements, kw, n ))
  override protected def _aggrTsort (kw: Kw                         ) = new Human_6[A, B, C, D, E, F     , t     ](asIs     (elements, kw    )) with SortAttrs_6[A, B, C, D, E, F     , t     , Human_6]
  override protected def _aggrT     (kw: Kw                         ) = new Human_6[A, B, C, D, E, F     , t     ](asIs     (elements, kw    ))
  override protected def _exprOneMan(op: Op, vs: Seq[t]             ) = new Human_6[A, B, C, D, E, F     , t     ](addOne   (elements, op, vs)) with SortAttrs_6[A, B, C, D, E, F     , t     , Human_6] with CardOne
  override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]     ) = new Human_6[A, B, C, D, E, F     , t     ](addOptOne(elements, op, vs)) with SortAttrs_6[A, B, C, D, E, F     , t     , Human_6]
  override protected def _exprOneTac(op: Op, vs: Seq[t]             ) = new Human_6[A, B, C, D, E, F     , t     ](addOne   (elements, op, vs)) with CardOne
  override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        ) = new Human_6[A, B, C, D, E, F     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]) = new Human_6[A, B, C, D, E, F     , t     ](addOptSet(elements, op, vs))
  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        ) = new Human_6[A, B, C, D, E, F     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _sort      (sort: String                   ) = new Human_6[A, B, C, D, E, F     , t     ](addSort  (elements, sort  ))

  override protected def _attrSortTac[ns1[_], ns2[_, _]](op: Op, a: ModelOps_0[t, ns1, ns2] with CardOne) = new Human_6[A, B, C, D, E, F, t](filterAttr(elements, op, a)) with SortAttrs_6[A, B, C, D, E, F, t, Human_6]
  override protected def _attrTac    [ns1[_], ns2[_, _]](op: Op, a: ModelOps_0[t, ns1, ns2]             ) = new Human_6[A, B, C, D, E, F, t](filterAttr(elements, op, a))

  object Friends extends Character_6[A, B, C, D, E, F, t](elements :+ Model.Ref("Human", "friends", "Character", CardSet, false, Seq(2, 11, 0)))
}

