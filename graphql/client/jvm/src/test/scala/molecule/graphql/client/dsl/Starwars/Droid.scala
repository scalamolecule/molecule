/*
* AUTO-GENERATED Molecule DSL boilerplate code for namespace `Droid`
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


trait Droid_base {
  protected lazy val id_man             : AttrOneManID     = AttrOneManID    ("Droid", "id"             , coord = Seq(1, 4   ))
  protected lazy val name_man           : AttrOneManString = AttrOneManString("Droid", "name"           , coord = Seq(1, 5   ))
  protected lazy val friends_man        : AttrSetManID     = AttrSetManID    ("Droid", "friends"        , coord = Seq(1, 6, 0), refNs = Some("Character"))
  protected lazy val appearsIn_man      : AttrSetManString = AttrSetManString("Droid", "appearsIn"      , coord = Seq(1, 7   ), validator = Some(validation_appearsIn))
  protected lazy val primaryFunction_man: AttrOneManString = AttrOneManString("Droid", "primaryFunction", coord = Seq(1, 8   ))
  
  protected lazy val name_opt           : AttrOneOptString = AttrOneOptString("Droid", "name"           , coord = Seq(1, 5   ))
  protected lazy val friends_opt        : AttrSetOptID     = AttrSetOptID    ("Droid", "friends"        , coord = Seq(1, 6, 0), refNs = Some("Character"))
  protected lazy val appearsIn_opt      : AttrSetOptString = AttrSetOptString("Droid", "appearsIn"      , coord = Seq(1, 7   ), validator = Some(validation_appearsIn))
  protected lazy val primaryFunction_opt: AttrOneOptString = AttrOneOptString("Droid", "primaryFunction", coord = Seq(1, 8   ))
  
  protected lazy val id_tac             : AttrOneTacID     = AttrOneTacID    ("Droid", "id"             , coord = Seq(1, 4   ))
  protected lazy val name_tac           : AttrOneTacString = AttrOneTacString("Droid", "name"           , coord = Seq(1, 5   ))
  protected lazy val friends_tac        : AttrSetTacID     = AttrSetTacID    ("Droid", "friends"        , coord = Seq(1, 6, 0), refNs = Some("Character"))
  protected lazy val appearsIn_tac      : AttrSetTacString = AttrSetTacString("Droid", "appearsIn"      , coord = Seq(1, 7   ), validator = Some(validation_appearsIn))
  protected lazy val primaryFunction_tac: AttrOneTacString = AttrOneTacString("Droid", "primaryFunction", coord = Seq(1, 8   ))
  
  private lazy val validation_appearsIn = new ValidateString {
    override def validate(v: String): Seq[String] = {
      val ok: String => Boolean = v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)
      if (ok(v)) Nil else Seq(s"""Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")""")
    }
  }
}

private object Droid extends Droid_0[Nothing](Nil) {
  final def apply(id: String, ids: String*)                       = new Droid_0[String](List(AttrOneTacID("Droid", "id", Eq, id +: ids                  , coord = Seq(1, 4))))
  final def apply(ids: Iterable[String])                          = new Droid_0[String](List(AttrOneTacID("Droid", "id", Eq, ids.toSeq                  , coord = Seq(1, 4))))
  final def apply(id: Long, ids: Long*)                           = new Droid_0[String](List(AttrOneTacID("Droid", "id", Eq, (id +: ids).map(_.toString), coord = Seq(1, 4))))
  final def apply(ids: Iterable[Long])(implicit x: DummyImplicit) = new Droid_0[String](List(AttrOneTacID("Droid", "id", Eq, ids.toSeq.map(_.toString)  , coord = Seq(1, 4))))
}


class Droid_0[t](override val elements: List[Element]) extends Droid_base with ModelOps_0[t, Droid_0, Droid_1] {
  lazy val id                = new Droid_1[String             , String](elements :+ id_man             ) with ExprOneMan_1        [String             , String, Droid_1, Droid_2] with CardOne
  lazy val name              = new Droid_1[String             , String](elements :+ name_man           ) with ExprOneMan_1_String [String             , String, Droid_1, Droid_2] with CardOne
  lazy val friends           = new Droid_1[Set[String]        , String](elements :+ friends_man        ) with ExprSetMan_1        [Set[String]        , String, Droid_1, Droid_2] with CardSet
  lazy val appearsIn         = new Droid_1[Set[String]        , String](elements :+ appearsIn_man      ) with ExprSetMan_1        [Set[String]        , String, Droid_1, Droid_2] with CardSet
  lazy val primaryFunction   = new Droid_1[String             , String](elements :+ primaryFunction_man) with ExprOneMan_1_String [String             , String, Droid_1, Droid_2] with CardOne

  lazy val name_?            = new Droid_1[Option[String]     , String](elements :+ name_opt           ) with ExprOneOpt_1        [Option[String]     , String, Droid_1, Droid_2] with CardOne
  lazy val friends_?         = new Droid_1[Option[Set[String]], String](elements :+ friends_opt        ) with ExprSetOpt_1        [Option[Set[String]], String, Droid_1, Droid_2] with CardSet
  lazy val appearsIn_?       = new Droid_1[Option[Set[String]], String](elements :+ appearsIn_opt      ) with ExprSetOpt_1        [Option[Set[String]], String, Droid_1, Droid_2] with CardSet
  lazy val primaryFunction_? = new Droid_1[Option[String]     , String](elements :+ primaryFunction_opt) with ExprOneOpt_1        [Option[String]     , String, Droid_1, Droid_2] with CardOne

  lazy val id_               = new Droid_0[                     String](elements :+ id_tac             ) with ExprOneTac_0        [                     String, Droid_0, Droid_1] with CardOne
  lazy val name_             = new Droid_0[                     String](elements :+ name_tac           ) with ExprOneTac_0_String [                     String, Droid_0, Droid_1] with CardOne
  lazy val friends_          = new Droid_0[                     String](elements :+ friends_tac        ) with ExprSetTac_0        [                     String, Droid_0, Droid_1] with CardSet
  lazy val appearsIn_        = new Droid_0[                     String](elements :+ appearsIn_tac      ) with ExprSetTac_0        [                     String, Droid_0, Droid_1] with CardSet
  lazy val primaryFunction_  = new Droid_0[                     String](elements :+ primaryFunction_tac) with ExprOneTac_0_String [                     String, Droid_0, Droid_1] with CardOne

  override protected def _exprOneTac(op: Op, vs: Seq[t]     ) = new Droid_0[t](addOne(elements, op, vs)) with CardOne
  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]) = new Droid_0[t](addSet(elements, op, vs)) with CardSet

  override protected def _attrTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]) = new Droid_0[   t](filterAttr(elements, op, a))
  override protected def _attrMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]) = new Droid_1[X, t](filterAttr(elements, op, a))

  trait NestedInit extends NestedOp_0 with Nested_0 { self: Molecule =>
    override protected def _nestedMan[NestedTpl](nestedElements: List[Element]): NestedInit_00[NestedTpl] = new NestedInit_00(self.elements.init :+ Nested   (self.elements.last.asInstanceOf[Ref], nestedElements))
    override protected def _nestedOpt[NestedTpl](nestedElements: List[Element]): NestedInit_00[NestedTpl] = new NestedInit_00(self.elements.init :+ NestedOpt(self.elements.last.asInstanceOf[Ref], nestedElements))
  }

  object Friends extends Character_0[t](elements :+ Model.Ref("Droid", "friends", "Character", CardSet, false, Seq(1, 6, 0))) with NestedInit
}


class Droid_1[A, t](override val elements: List[Element]) extends Droid_base with ModelOps_1[A, t, Droid_1, Droid_2] {
  lazy val id                = new Droid_2[A, String             , String](elements :+ id_man             ) with ExprOneMan_2        [A, String             , String, Droid_2, Droid_3] with CardOne
  lazy val name              = new Droid_2[A, String             , String](elements :+ name_man           ) with ExprOneMan_2_String [A, String             , String, Droid_2, Droid_3] with CardOne
  lazy val friends           = new Droid_2[A, Set[String]        , String](elements :+ friends_man        ) with ExprSetMan_2        [A, Set[String]        , String, Droid_2, Droid_3] with CardSet
  lazy val appearsIn         = new Droid_2[A, Set[String]        , String](elements :+ appearsIn_man      ) with ExprSetMan_2        [A, Set[String]        , String, Droid_2, Droid_3] with CardSet
  lazy val primaryFunction   = new Droid_2[A, String             , String](elements :+ primaryFunction_man) with ExprOneMan_2_String [A, String             , String, Droid_2, Droid_3] with CardOne

  lazy val name_?            = new Droid_2[A, Option[String]     , String](elements :+ name_opt           ) with ExprOneOpt_2        [A, Option[String]     , String, Droid_2, Droid_3] with CardOne
  lazy val friends_?         = new Droid_2[A, Option[Set[String]], String](elements :+ friends_opt        ) with ExprSetOpt_2        [A, Option[Set[String]], String, Droid_2, Droid_3] with CardSet
  lazy val appearsIn_?       = new Droid_2[A, Option[Set[String]], String](elements :+ appearsIn_opt      ) with ExprSetOpt_2        [A, Option[Set[String]], String, Droid_2, Droid_3] with CardSet
  lazy val primaryFunction_? = new Droid_2[A, Option[String]     , String](elements :+ primaryFunction_opt) with ExprOneOpt_2        [A, Option[String]     , String, Droid_2, Droid_3] with CardOne

  lazy val id_               = new Droid_1[A                     , String](elements :+ id_tac             ) with ExprOneTac_1        [A                     , String, Droid_1, Droid_2] with CardOne
  lazy val name_             = new Droid_1[A                     , String](elements :+ name_tac           ) with ExprOneTac_1_String [A                     , String, Droid_1, Droid_2] with CardOne
  lazy val friends_          = new Droid_1[A                     , String](elements :+ friends_tac        ) with ExprSetTac_1        [A                     , String, Droid_1, Droid_2] with CardSet
  lazy val appearsIn_        = new Droid_1[A                     , String](elements :+ appearsIn_tac      ) with ExprSetTac_1        [A                     , String, Droid_1, Droid_2] with CardSet
  lazy val primaryFunction_  = new Droid_1[A                     , String](elements :+ primaryFunction_tac) with ExprOneTac_1_String [A                     , String, Droid_1, Droid_2] with CardOne

  override protected def _aggrInt   (kw: Kw                         ) = new Droid_1[Int   , Int   ](toInt    (elements, kw    )) with SortAttrs_1[Int   , Int   , Droid_1]
  override protected def _aggrDouble(kw: Kw                         ) = new Droid_1[Double, Double](toDouble (elements, kw    )) with SortAttrs_1[Double, Double, Droid_1]
  override protected def _aggrDist  (kw: Kw                         ) = new Droid_1[Set[A], t     ](asIs     (elements, kw    ))
  override protected def _aggrSet   (kw: Kw, n: Option[Int]         ) = new Droid_1[Set[t], t     ](asIs     (elements, kw, n ))
  override protected def _aggrTsort (kw: Kw                         ) = new Droid_1[A     , t     ](asIs     (elements, kw    )) with SortAttrs_1[A     , t     , Droid_1]
  override protected def _aggrT     (kw: Kw                         ) = new Droid_1[A     , t     ](asIs     (elements, kw    ))
  override protected def _exprOneMan(op: Op, vs: Seq[t]             ) = new Droid_1[A     , t     ](addOne   (elements, op, vs)) with SortAttrs_1[A     , t     , Droid_1] with CardOne
  override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]     ) = new Droid_1[A     , t     ](addOptOne(elements, op, vs)) with SortAttrs_1[A     , t     , Droid_1]
  override protected def _exprOneTac(op: Op, vs: Seq[t]             ) = new Droid_1[A     , t     ](addOne   (elements, op, vs)) with CardOne
  override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        ) = new Droid_1[A     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]) = new Droid_1[A     , t     ](addOptSet(elements, op, vs))
  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        ) = new Droid_1[A     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _sort      (sort: String                   ) = new Droid_1[A     , t     ](addSort  (elements, sort  ))

  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne) = new Droid_1[A,    t](filterAttr(elements, op, a)) with SortAttrs_1[A,    t, Droid_1]
  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[A, t, ns1, ns2]             ) = new Droid_2[A, A, t](filterAttr(elements, op, a)) with SortAttrs_2[A, A, t, Droid_2]
  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ) = new Droid_1[A,    t](filterAttr(elements, op, a))
  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ) = new Droid_2[A, X, t](filterAttr(elements, op, a))

  trait NestedInit extends NestedOp_1[A] with Nested_1[A] { self: Molecule =>
    override protected def _nestedMan[NestedTpl](nestedElements: List[Element]): NestedInit_01[A, NestedTpl] = new NestedInit_01(self.elements.init :+ Nested   (self.elements.last.asInstanceOf[Ref], nestedElements))
    override protected def _nestedOpt[NestedTpl](nestedElements: List[Element]): NestedInit_01[A, NestedTpl] = new NestedInit_01(self.elements.init :+ NestedOpt(self.elements.last.asInstanceOf[Ref], nestedElements))
  }

  object Friends extends Character_1[A, t](elements :+ Model.Ref("Droid", "friends", "Character", CardSet, false, Seq(1, 6, 0))) with NestedInit
}


class Droid_2[A, B, t](override val elements: List[Element]) extends Droid_base with ModelOps_2[A, B, t, Droid_2, Droid_3] {
  lazy val id                = new Droid_3[A, B, String             , String](elements :+ id_man             ) with ExprOneMan_3        [A, B, String             , String, Droid_3, Droid_4] with CardOne
  lazy val name              = new Droid_3[A, B, String             , String](elements :+ name_man           ) with ExprOneMan_3_String [A, B, String             , String, Droid_3, Droid_4] with CardOne
  lazy val friends           = new Droid_3[A, B, Set[String]        , String](elements :+ friends_man        ) with ExprSetMan_3        [A, B, Set[String]        , String, Droid_3, Droid_4] with CardSet
  lazy val appearsIn         = new Droid_3[A, B, Set[String]        , String](elements :+ appearsIn_man      ) with ExprSetMan_3        [A, B, Set[String]        , String, Droid_3, Droid_4] with CardSet
  lazy val primaryFunction   = new Droid_3[A, B, String             , String](elements :+ primaryFunction_man) with ExprOneMan_3_String [A, B, String             , String, Droid_3, Droid_4] with CardOne

  lazy val name_?            = new Droid_3[A, B, Option[String]     , String](elements :+ name_opt           ) with ExprOneOpt_3        [A, B, Option[String]     , String, Droid_3, Droid_4] with CardOne
  lazy val friends_?         = new Droid_3[A, B, Option[Set[String]], String](elements :+ friends_opt        ) with ExprSetOpt_3        [A, B, Option[Set[String]], String, Droid_3, Droid_4] with CardSet
  lazy val appearsIn_?       = new Droid_3[A, B, Option[Set[String]], String](elements :+ appearsIn_opt      ) with ExprSetOpt_3        [A, B, Option[Set[String]], String, Droid_3, Droid_4] with CardSet
  lazy val primaryFunction_? = new Droid_3[A, B, Option[String]     , String](elements :+ primaryFunction_opt) with ExprOneOpt_3        [A, B, Option[String]     , String, Droid_3, Droid_4] with CardOne

  lazy val id_               = new Droid_2[A, B                     , String](elements :+ id_tac             ) with ExprOneTac_2        [A, B                     , String, Droid_2, Droid_3] with CardOne
  lazy val name_             = new Droid_2[A, B                     , String](elements :+ name_tac           ) with ExprOneTac_2_String [A, B                     , String, Droid_2, Droid_3] with CardOne
  lazy val friends_          = new Droid_2[A, B                     , String](elements :+ friends_tac        ) with ExprSetTac_2        [A, B                     , String, Droid_2, Droid_3] with CardSet
  lazy val appearsIn_        = new Droid_2[A, B                     , String](elements :+ appearsIn_tac      ) with ExprSetTac_2        [A, B                     , String, Droid_2, Droid_3] with CardSet
  lazy val primaryFunction_  = new Droid_2[A, B                     , String](elements :+ primaryFunction_tac) with ExprOneTac_2_String [A, B                     , String, Droid_2, Droid_3] with CardOne

  override protected def _aggrInt   (kw: Kw                         ) = new Droid_2[A, Int   , Int   ](toInt    (elements, kw    )) with SortAttrs_2[A, Int   , Int   , Droid_2]
  override protected def _aggrDouble(kw: Kw                         ) = new Droid_2[A, Double, Double](toDouble (elements, kw    )) with SortAttrs_2[A, Double, Double, Droid_2]
  override protected def _aggrDist  (kw: Kw                         ) = new Droid_2[A, Set[B], t     ](asIs     (elements, kw    ))
  override protected def _aggrSet   (kw: Kw, n: Option[Int]         ) = new Droid_2[A, Set[t], t     ](asIs     (elements, kw, n ))
  override protected def _aggrTsort (kw: Kw                         ) = new Droid_2[A, B     , t     ](asIs     (elements, kw    )) with SortAttrs_2[A, B     , t     , Droid_2]
  override protected def _aggrT     (kw: Kw                         ) = new Droid_2[A, B     , t     ](asIs     (elements, kw    ))
  override protected def _exprOneMan(op: Op, vs: Seq[t]             ) = new Droid_2[A, B     , t     ](addOne   (elements, op, vs)) with SortAttrs_2[A, B     , t     , Droid_2] with CardOne
  override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]     ) = new Droid_2[A, B     , t     ](addOptOne(elements, op, vs)) with SortAttrs_2[A, B     , t     , Droid_2]
  override protected def _exprOneTac(op: Op, vs: Seq[t]             ) = new Droid_2[A, B     , t     ](addOne   (elements, op, vs)) with CardOne
  override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        ) = new Droid_2[A, B     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]) = new Droid_2[A, B     , t     ](addOptSet(elements, op, vs))
  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        ) = new Droid_2[A, B     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _sort      (sort: String                   ) = new Droid_2[A, B     , t     ](addSort  (elements, sort  ))

  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne) = new Droid_2[A, B,    t](filterAttr(elements, op, a)) with SortAttrs_2[A, B,    t, Droid_2]
  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[B, t, ns1, ns2]             ) = new Droid_3[A, B, B, t](filterAttr(elements, op, a)) with SortAttrs_3[A, B, B, t, Droid_3]
  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ) = new Droid_2[A, B,    t](filterAttr(elements, op, a))
  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ) = new Droid_3[A, B, X, t](filterAttr(elements, op, a))

  trait NestedInit extends NestedOp_2[A, B] with Nested_2[A, B] { self: Molecule =>
    override protected def _nestedMan[NestedTpl](nestedElements: List[Element]): NestedInit_02[A, B, NestedTpl] = new NestedInit_02(self.elements.init :+ Nested   (self.elements.last.asInstanceOf[Ref], nestedElements))
    override protected def _nestedOpt[NestedTpl](nestedElements: List[Element]): NestedInit_02[A, B, NestedTpl] = new NestedInit_02(self.elements.init :+ NestedOpt(self.elements.last.asInstanceOf[Ref], nestedElements))
  }

  object Friends extends Character_2[A, B, t](elements :+ Model.Ref("Droid", "friends", "Character", CardSet, false, Seq(1, 6, 0))) with NestedInit
}


class Droid_3[A, B, C, t](override val elements: List[Element]) extends Droid_base with ModelOps_3[A, B, C, t, Droid_3, Droid_4] {
  lazy val id                = new Droid_4[A, B, C, String             , String](elements :+ id_man             ) with ExprOneMan_4        [A, B, C, String             , String, Droid_4, Droid_5] with CardOne
  lazy val name              = new Droid_4[A, B, C, String             , String](elements :+ name_man           ) with ExprOneMan_4_String [A, B, C, String             , String, Droid_4, Droid_5] with CardOne
  lazy val friends           = new Droid_4[A, B, C, Set[String]        , String](elements :+ friends_man        ) with ExprSetMan_4        [A, B, C, Set[String]        , String, Droid_4, Droid_5] with CardSet
  lazy val appearsIn         = new Droid_4[A, B, C, Set[String]        , String](elements :+ appearsIn_man      ) with ExprSetMan_4        [A, B, C, Set[String]        , String, Droid_4, Droid_5] with CardSet
  lazy val primaryFunction   = new Droid_4[A, B, C, String             , String](elements :+ primaryFunction_man) with ExprOneMan_4_String [A, B, C, String             , String, Droid_4, Droid_5] with CardOne

  lazy val name_?            = new Droid_4[A, B, C, Option[String]     , String](elements :+ name_opt           ) with ExprOneOpt_4        [A, B, C, Option[String]     , String, Droid_4, Droid_5] with CardOne
  lazy val friends_?         = new Droid_4[A, B, C, Option[Set[String]], String](elements :+ friends_opt        ) with ExprSetOpt_4        [A, B, C, Option[Set[String]], String, Droid_4, Droid_5] with CardSet
  lazy val appearsIn_?       = new Droid_4[A, B, C, Option[Set[String]], String](elements :+ appearsIn_opt      ) with ExprSetOpt_4        [A, B, C, Option[Set[String]], String, Droid_4, Droid_5] with CardSet
  lazy val primaryFunction_? = new Droid_4[A, B, C, Option[String]     , String](elements :+ primaryFunction_opt) with ExprOneOpt_4        [A, B, C, Option[String]     , String, Droid_4, Droid_5] with CardOne

  lazy val id_               = new Droid_3[A, B, C                     , String](elements :+ id_tac             ) with ExprOneTac_3        [A, B, C                     , String, Droid_3, Droid_4] with CardOne
  lazy val name_             = new Droid_3[A, B, C                     , String](elements :+ name_tac           ) with ExprOneTac_3_String [A, B, C                     , String, Droid_3, Droid_4] with CardOne
  lazy val friends_          = new Droid_3[A, B, C                     , String](elements :+ friends_tac        ) with ExprSetTac_3        [A, B, C                     , String, Droid_3, Droid_4] with CardSet
  lazy val appearsIn_        = new Droid_3[A, B, C                     , String](elements :+ appearsIn_tac      ) with ExprSetTac_3        [A, B, C                     , String, Droid_3, Droid_4] with CardSet
  lazy val primaryFunction_  = new Droid_3[A, B, C                     , String](elements :+ primaryFunction_tac) with ExprOneTac_3_String [A, B, C                     , String, Droid_3, Droid_4] with CardOne

  override protected def _aggrInt   (kw: Kw                         ) = new Droid_3[A, B, Int   , Int   ](toInt    (elements, kw    )) with SortAttrs_3[A, B, Int   , Int   , Droid_3]
  override protected def _aggrDouble(kw: Kw                         ) = new Droid_3[A, B, Double, Double](toDouble (elements, kw    )) with SortAttrs_3[A, B, Double, Double, Droid_3]
  override protected def _aggrDist  (kw: Kw                         ) = new Droid_3[A, B, Set[C], t     ](asIs     (elements, kw    ))
  override protected def _aggrSet   (kw: Kw, n: Option[Int]         ) = new Droid_3[A, B, Set[t], t     ](asIs     (elements, kw, n ))
  override protected def _aggrTsort (kw: Kw                         ) = new Droid_3[A, B, C     , t     ](asIs     (elements, kw    )) with SortAttrs_3[A, B, C     , t     , Droid_3]
  override protected def _aggrT     (kw: Kw                         ) = new Droid_3[A, B, C     , t     ](asIs     (elements, kw    ))
  override protected def _exprOneMan(op: Op, vs: Seq[t]             ) = new Droid_3[A, B, C     , t     ](addOne   (elements, op, vs)) with SortAttrs_3[A, B, C     , t     , Droid_3] with CardOne
  override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]     ) = new Droid_3[A, B, C     , t     ](addOptOne(elements, op, vs)) with SortAttrs_3[A, B, C     , t     , Droid_3]
  override protected def _exprOneTac(op: Op, vs: Seq[t]             ) = new Droid_3[A, B, C     , t     ](addOne   (elements, op, vs)) with CardOne
  override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        ) = new Droid_3[A, B, C     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]) = new Droid_3[A, B, C     , t     ](addOptSet(elements, op, vs))
  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        ) = new Droid_3[A, B, C     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _sort      (sort: String                   ) = new Droid_3[A, B, C     , t     ](addSort  (elements, sort  ))

  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne) = new Droid_3[A, B, C,    t](filterAttr(elements, op, a)) with SortAttrs_3[A, B, C,    t, Droid_3]
  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[C, t, ns1, ns2]             ) = new Droid_4[A, B, C, C, t](filterAttr(elements, op, a)) with SortAttrs_4[A, B, C, C, t, Droid_4]
  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ) = new Droid_3[A, B, C,    t](filterAttr(elements, op, a))
  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ) = new Droid_4[A, B, C, X, t](filterAttr(elements, op, a))

  trait NestedInit extends NestedOp_3[A, B, C] with Nested_3[A, B, C] { self: Molecule =>
    override protected def _nestedMan[NestedTpl](nestedElements: List[Element]): NestedInit_03[A, B, C, NestedTpl] = new NestedInit_03(self.elements.init :+ Nested   (self.elements.last.asInstanceOf[Ref], nestedElements))
    override protected def _nestedOpt[NestedTpl](nestedElements: List[Element]): NestedInit_03[A, B, C, NestedTpl] = new NestedInit_03(self.elements.init :+ NestedOpt(self.elements.last.asInstanceOf[Ref], nestedElements))
  }

  object Friends extends Character_3[A, B, C, t](elements :+ Model.Ref("Droid", "friends", "Character", CardSet, false, Seq(1, 6, 0))) with NestedInit
}


class Droid_4[A, B, C, D, t](override val elements: List[Element]) extends Droid_base with ModelOps_4[A, B, C, D, t, Droid_4, Droid_5] {
  lazy val id                = new Droid_5[A, B, C, D, String             , String](elements :+ id_man             ) with ExprOneMan_5        [A, B, C, D, String             , String, Droid_5, Droid_6] with CardOne
  lazy val name              = new Droid_5[A, B, C, D, String             , String](elements :+ name_man           ) with ExprOneMan_5_String [A, B, C, D, String             , String, Droid_5, Droid_6] with CardOne
  lazy val friends           = new Droid_5[A, B, C, D, Set[String]        , String](elements :+ friends_man        ) with ExprSetMan_5        [A, B, C, D, Set[String]        , String, Droid_5, Droid_6] with CardSet
  lazy val appearsIn         = new Droid_5[A, B, C, D, Set[String]        , String](elements :+ appearsIn_man      ) with ExprSetMan_5        [A, B, C, D, Set[String]        , String, Droid_5, Droid_6] with CardSet
  lazy val primaryFunction   = new Droid_5[A, B, C, D, String             , String](elements :+ primaryFunction_man) with ExprOneMan_5_String [A, B, C, D, String             , String, Droid_5, Droid_6] with CardOne

  lazy val name_?            = new Droid_5[A, B, C, D, Option[String]     , String](elements :+ name_opt           ) with ExprOneOpt_5        [A, B, C, D, Option[String]     , String, Droid_5, Droid_6] with CardOne
  lazy val friends_?         = new Droid_5[A, B, C, D, Option[Set[String]], String](elements :+ friends_opt        ) with ExprSetOpt_5        [A, B, C, D, Option[Set[String]], String, Droid_5, Droid_6] with CardSet
  lazy val appearsIn_?       = new Droid_5[A, B, C, D, Option[Set[String]], String](elements :+ appearsIn_opt      ) with ExprSetOpt_5        [A, B, C, D, Option[Set[String]], String, Droid_5, Droid_6] with CardSet
  lazy val primaryFunction_? = new Droid_5[A, B, C, D, Option[String]     , String](elements :+ primaryFunction_opt) with ExprOneOpt_5        [A, B, C, D, Option[String]     , String, Droid_5, Droid_6] with CardOne

  lazy val id_               = new Droid_4[A, B, C, D                     , String](elements :+ id_tac             ) with ExprOneTac_4        [A, B, C, D                     , String, Droid_4, Droid_5] with CardOne
  lazy val name_             = new Droid_4[A, B, C, D                     , String](elements :+ name_tac           ) with ExprOneTac_4_String [A, B, C, D                     , String, Droid_4, Droid_5] with CardOne
  lazy val friends_          = new Droid_4[A, B, C, D                     , String](elements :+ friends_tac        ) with ExprSetTac_4        [A, B, C, D                     , String, Droid_4, Droid_5] with CardSet
  lazy val appearsIn_        = new Droid_4[A, B, C, D                     , String](elements :+ appearsIn_tac      ) with ExprSetTac_4        [A, B, C, D                     , String, Droid_4, Droid_5] with CardSet
  lazy val primaryFunction_  = new Droid_4[A, B, C, D                     , String](elements :+ primaryFunction_tac) with ExprOneTac_4_String [A, B, C, D                     , String, Droid_4, Droid_5] with CardOne

  override protected def _aggrInt   (kw: Kw                         ) = new Droid_4[A, B, C, Int   , Int   ](toInt    (elements, kw    )) with SortAttrs_4[A, B, C, Int   , Int   , Droid_4]
  override protected def _aggrDouble(kw: Kw                         ) = new Droid_4[A, B, C, Double, Double](toDouble (elements, kw    )) with SortAttrs_4[A, B, C, Double, Double, Droid_4]
  override protected def _aggrDist  (kw: Kw                         ) = new Droid_4[A, B, C, Set[D], t     ](asIs     (elements, kw    ))
  override protected def _aggrSet   (kw: Kw, n: Option[Int]         ) = new Droid_4[A, B, C, Set[t], t     ](asIs     (elements, kw, n ))
  override protected def _aggrTsort (kw: Kw                         ) = new Droid_4[A, B, C, D     , t     ](asIs     (elements, kw    )) with SortAttrs_4[A, B, C, D     , t     , Droid_4]
  override protected def _aggrT     (kw: Kw                         ) = new Droid_4[A, B, C, D     , t     ](asIs     (elements, kw    ))
  override protected def _exprOneMan(op: Op, vs: Seq[t]             ) = new Droid_4[A, B, C, D     , t     ](addOne   (elements, op, vs)) with SortAttrs_4[A, B, C, D     , t     , Droid_4] with CardOne
  override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]     ) = new Droid_4[A, B, C, D     , t     ](addOptOne(elements, op, vs)) with SortAttrs_4[A, B, C, D     , t     , Droid_4]
  override protected def _exprOneTac(op: Op, vs: Seq[t]             ) = new Droid_4[A, B, C, D     , t     ](addOne   (elements, op, vs)) with CardOne
  override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        ) = new Droid_4[A, B, C, D     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]) = new Droid_4[A, B, C, D     , t     ](addOptSet(elements, op, vs))
  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        ) = new Droid_4[A, B, C, D     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _sort      (sort: String                   ) = new Droid_4[A, B, C, D     , t     ](addSort  (elements, sort  ))

  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne) = new Droid_4[A, B, C, D,    t](filterAttr(elements, op, a)) with SortAttrs_4[A, B, C, D,    t, Droid_4]
  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[D, t, ns1, ns2]             ) = new Droid_5[A, B, C, D, D, t](filterAttr(elements, op, a)) with SortAttrs_5[A, B, C, D, D, t, Droid_5]
  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ) = new Droid_4[A, B, C, D,    t](filterAttr(elements, op, a))
  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ) = new Droid_5[A, B, C, D, X, t](filterAttr(elements, op, a))

  trait NestedInit extends NestedOp_4[A, B, C, D] with Nested_4[A, B, C, D] { self: Molecule =>
    override protected def _nestedMan[NestedTpl](nestedElements: List[Element]): NestedInit_04[A, B, C, D, NestedTpl] = new NestedInit_04(self.elements.init :+ Nested   (self.elements.last.asInstanceOf[Ref], nestedElements))
    override protected def _nestedOpt[NestedTpl](nestedElements: List[Element]): NestedInit_04[A, B, C, D, NestedTpl] = new NestedInit_04(self.elements.init :+ NestedOpt(self.elements.last.asInstanceOf[Ref], nestedElements))
  }

  object Friends extends Character_4[A, B, C, D, t](elements :+ Model.Ref("Droid", "friends", "Character", CardSet, false, Seq(1, 6, 0))) with NestedInit
}


class Droid_5[A, B, C, D, E, t](override val elements: List[Element]) extends Droid_base with ModelOps_5[A, B, C, D, E, t, Droid_5, Droid_6] {
  lazy val id                = new Droid_6[A, B, C, D, E, String             , String](elements :+ id_man             ) with ExprOneMan_6        [A, B, C, D, E, String             , String, Droid_6, X8] with CardOne
  lazy val name              = new Droid_6[A, B, C, D, E, String             , String](elements :+ name_man           ) with ExprOneMan_6_String [A, B, C, D, E, String             , String, Droid_6, X8] with CardOne
  lazy val friends           = new Droid_6[A, B, C, D, E, Set[String]        , String](elements :+ friends_man        ) with ExprSetMan_6        [A, B, C, D, E, Set[String]        , String, Droid_6, X8] with CardSet
  lazy val appearsIn         = new Droid_6[A, B, C, D, E, Set[String]        , String](elements :+ appearsIn_man      ) with ExprSetMan_6        [A, B, C, D, E, Set[String]        , String, Droid_6, X8] with CardSet
  lazy val primaryFunction   = new Droid_6[A, B, C, D, E, String             , String](elements :+ primaryFunction_man) with ExprOneMan_6_String [A, B, C, D, E, String             , String, Droid_6, X8] with CardOne

  lazy val name_?            = new Droid_6[A, B, C, D, E, Option[String]     , String](elements :+ name_opt           ) with ExprOneOpt_6        [A, B, C, D, E, Option[String]     , String, Droid_6, X8] with CardOne
  lazy val friends_?         = new Droid_6[A, B, C, D, E, Option[Set[String]], String](elements :+ friends_opt        ) with ExprSetOpt_6        [A, B, C, D, E, Option[Set[String]], String, Droid_6, X8] with CardSet
  lazy val appearsIn_?       = new Droid_6[A, B, C, D, E, Option[Set[String]], String](elements :+ appearsIn_opt      ) with ExprSetOpt_6        [A, B, C, D, E, Option[Set[String]], String, Droid_6, X8] with CardSet
  lazy val primaryFunction_? = new Droid_6[A, B, C, D, E, Option[String]     , String](elements :+ primaryFunction_opt) with ExprOneOpt_6        [A, B, C, D, E, Option[String]     , String, Droid_6, X8] with CardOne

  lazy val id_               = new Droid_5[A, B, C, D, E                     , String](elements :+ id_tac             ) with ExprOneTac_5        [A, B, C, D, E                     , String, Droid_5, Droid_6] with CardOne
  lazy val name_             = new Droid_5[A, B, C, D, E                     , String](elements :+ name_tac           ) with ExprOneTac_5_String [A, B, C, D, E                     , String, Droid_5, Droid_6] with CardOne
  lazy val friends_          = new Droid_5[A, B, C, D, E                     , String](elements :+ friends_tac        ) with ExprSetTac_5        [A, B, C, D, E                     , String, Droid_5, Droid_6] with CardSet
  lazy val appearsIn_        = new Droid_5[A, B, C, D, E                     , String](elements :+ appearsIn_tac      ) with ExprSetTac_5        [A, B, C, D, E                     , String, Droid_5, Droid_6] with CardSet
  lazy val primaryFunction_  = new Droid_5[A, B, C, D, E                     , String](elements :+ primaryFunction_tac) with ExprOneTac_5_String [A, B, C, D, E                     , String, Droid_5, Droid_6] with CardOne

  override protected def _aggrInt   (kw: Kw                         ) = new Droid_5[A, B, C, D, Int   , Int   ](toInt    (elements, kw    )) with SortAttrs_5[A, B, C, D, Int   , Int   , Droid_5]
  override protected def _aggrDouble(kw: Kw                         ) = new Droid_5[A, B, C, D, Double, Double](toDouble (elements, kw    )) with SortAttrs_5[A, B, C, D, Double, Double, Droid_5]
  override protected def _aggrDist  (kw: Kw                         ) = new Droid_5[A, B, C, D, Set[E], t     ](asIs     (elements, kw    ))
  override protected def _aggrSet   (kw: Kw, n: Option[Int]         ) = new Droid_5[A, B, C, D, Set[t], t     ](asIs     (elements, kw, n ))
  override protected def _aggrTsort (kw: Kw                         ) = new Droid_5[A, B, C, D, E     , t     ](asIs     (elements, kw    )) with SortAttrs_5[A, B, C, D, E     , t     , Droid_5]
  override protected def _aggrT     (kw: Kw                         ) = new Droid_5[A, B, C, D, E     , t     ](asIs     (elements, kw    ))
  override protected def _exprOneMan(op: Op, vs: Seq[t]             ) = new Droid_5[A, B, C, D, E     , t     ](addOne   (elements, op, vs)) with SortAttrs_5[A, B, C, D, E     , t     , Droid_5] with CardOne
  override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]     ) = new Droid_5[A, B, C, D, E     , t     ](addOptOne(elements, op, vs)) with SortAttrs_5[A, B, C, D, E     , t     , Droid_5]
  override protected def _exprOneTac(op: Op, vs: Seq[t]             ) = new Droid_5[A, B, C, D, E     , t     ](addOne   (elements, op, vs)) with CardOne
  override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        ) = new Droid_5[A, B, C, D, E     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]) = new Droid_5[A, B, C, D, E     , t     ](addOptSet(elements, op, vs))
  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        ) = new Droid_5[A, B, C, D, E     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _sort      (sort: String                   ) = new Droid_5[A, B, C, D, E     , t     ](addSort  (elements, sort  ))

  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne) = new Droid_5[A, B, C, D, E,    t](filterAttr(elements, op, a)) with SortAttrs_5[A, B, C, D, E,    t, Droid_5]
  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[E, t, ns1, ns2]             ) = new Droid_6[A, B, C, D, E, E, t](filterAttr(elements, op, a)) with SortAttrs_6[A, B, C, D, E, E, t, Droid_6]
  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ) = new Droid_5[A, B, C, D, E,    t](filterAttr(elements, op, a))
  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ) = new Droid_6[A, B, C, D, E, X, t](filterAttr(elements, op, a))

  trait NestedInit extends NestedOp_5[A, B, C, D, E] with Nested_5[A, B, C, D, E] { self: Molecule =>
    override protected def _nestedMan[NestedTpl](nestedElements: List[Element]): NestedInit_05[A, B, C, D, E, NestedTpl] = new NestedInit_05(self.elements.init :+ Nested   (self.elements.last.asInstanceOf[Ref], nestedElements))
    override protected def _nestedOpt[NestedTpl](nestedElements: List[Element]): NestedInit_05[A, B, C, D, E, NestedTpl] = new NestedInit_05(self.elements.init :+ NestedOpt(self.elements.last.asInstanceOf[Ref], nestedElements))
  }

  object Friends extends Character_5[A, B, C, D, E, t](elements :+ Model.Ref("Droid", "friends", "Character", CardSet, false, Seq(1, 6, 0))) with NestedInit
}


class Droid_6[A, B, C, D, E, F, t](override val elements: List[Element]) extends Droid_base with ModelOps_6[A, B, C, D, E, F, t, Droid_6, X8] {
  lazy val id_               = new Droid_6[A, B, C, D, E, F, String](elements :+ id_tac             ) with ExprOneTac_6        [A, B, C, D, E, F, String, Droid_6, X8] with CardOne
  lazy val name_             = new Droid_6[A, B, C, D, E, F, String](elements :+ name_tac           ) with ExprOneTac_6_String [A, B, C, D, E, F, String, Droid_6, X8] with CardOne
  lazy val friends_          = new Droid_6[A, B, C, D, E, F, String](elements :+ friends_tac        ) with ExprSetTac_6        [A, B, C, D, E, F, String, Droid_6, X8] with CardSet
  lazy val appearsIn_        = new Droid_6[A, B, C, D, E, F, String](elements :+ appearsIn_tac      ) with ExprSetTac_6        [A, B, C, D, E, F, String, Droid_6, X8] with CardSet
  lazy val primaryFunction_  = new Droid_6[A, B, C, D, E, F, String](elements :+ primaryFunction_tac) with ExprOneTac_6_String [A, B, C, D, E, F, String, Droid_6, X8] with CardOne

  override protected def _aggrInt   (kw: Kw                         ) = new Droid_6[A, B, C, D, E, Int   , Int   ](toInt    (elements, kw    )) with SortAttrs_6[A, B, C, D, E, Int   , Int   , Droid_6]
  override protected def _aggrDouble(kw: Kw                         ) = new Droid_6[A, B, C, D, E, Double, Double](toDouble (elements, kw    )) with SortAttrs_6[A, B, C, D, E, Double, Double, Droid_6]
  override protected def _aggrDist  (kw: Kw                         ) = new Droid_6[A, B, C, D, E, Set[F], t     ](asIs     (elements, kw    ))
  override protected def _aggrSet   (kw: Kw, n: Option[Int]         ) = new Droid_6[A, B, C, D, E, Set[t], t     ](asIs     (elements, kw, n ))
  override protected def _aggrTsort (kw: Kw                         ) = new Droid_6[A, B, C, D, E, F     , t     ](asIs     (elements, kw    )) with SortAttrs_6[A, B, C, D, E, F     , t     , Droid_6]
  override protected def _aggrT     (kw: Kw                         ) = new Droid_6[A, B, C, D, E, F     , t     ](asIs     (elements, kw    ))
  override protected def _exprOneMan(op: Op, vs: Seq[t]             ) = new Droid_6[A, B, C, D, E, F     , t     ](addOne   (elements, op, vs)) with SortAttrs_6[A, B, C, D, E, F     , t     , Droid_6] with CardOne
  override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]     ) = new Droid_6[A, B, C, D, E, F     , t     ](addOptOne(elements, op, vs)) with SortAttrs_6[A, B, C, D, E, F     , t     , Droid_6]
  override protected def _exprOneTac(op: Op, vs: Seq[t]             ) = new Droid_6[A, B, C, D, E, F     , t     ](addOne   (elements, op, vs)) with CardOne
  override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        ) = new Droid_6[A, B, C, D, E, F     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]) = new Droid_6[A, B, C, D, E, F     , t     ](addOptSet(elements, op, vs))
  override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        ) = new Droid_6[A, B, C, D, E, F     , t     ](addSet   (elements, op, vs)) with CardSet
  override protected def _sort      (sort: String                   ) = new Droid_6[A, B, C, D, E, F     , t     ](addSort  (elements, sort  ))

  override protected def _attrSortTac[ns1[_], ns2[_, _]](op: Op, a: ModelOps_0[t, ns1, ns2] with CardOne) = new Droid_6[A, B, C, D, E, F, t](filterAttr(elements, op, a)) with SortAttrs_6[A, B, C, D, E, F, t, Droid_6]
  override protected def _attrTac    [ns1[_], ns2[_, _]](op: Op, a: ModelOps_0[t, ns1, ns2]             ) = new Droid_6[A, B, C, D, E, F, t](filterAttr(elements, op, a))

  object Friends extends Character_6[A, B, C, D, E, F, t](elements :+ Model.Ref("Droid", "friends", "Character", CardSet, false, Seq(1, 6, 0)))
}

