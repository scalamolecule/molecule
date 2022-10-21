package molecule.base.codeGeneration.renderDsl

import molecule.base.codeGeneration.parseDataModel.parserAST.{BackRef, DefAttr, Namespace, ParseModel, Ref}
import molecule.base.codeGeneration.parseDataModel.parserAST._


case class NsArityLevel(model: ParseModel, namespace: Namespace, arity: Int)
  extends Formatting(model, namespace, arity) {


  // Attrs --------------------------------------

  val man = List.newBuilder[String]
  val opt = List.newBuilder[String]
  val tac = List.newBuilder[String]
  var one = false
  var set = false
  var map = false

  val first   = arity == 0
  val notLast = arity != maxArity

  attrs.collect {
    case a: DefAttr =>
      val (card, ns_attrM, ns_attrO, ns__ref, attr, attrO, attr_, tpe, tpB, tpO, ref, refNsPad) = formatted(a)

      if (isDatom) {
        if (notLast)
          man += s"final lazy val $attr  : Ns${_1}_OneM[$attr , $tpB${`, A`}, $tpe] = ???"
        tac += s"final lazy val $attr_ : Ns${_0}_OneT[$attr_, $tpB${`, A`}] = ???"

      } else if (isSchema) {
        if (notLast) {
          man += s"final lazy val $attr  : ${ns_1}_OneM[$attr , $tpB${`, A`}, $tpe] = ???"
          if (optSchemaAttrs.contains(a.attr))
            opt += s"final lazy val $attrO : ${ns_1}_OneO[$attrO, $tpB${`, A`}, $tpO] = ???"
        }
        tac += s"final lazy val $attr_ : ${ns_0}_OneT[$attr_, $tpB${`, A`}] = ???"

      } else if (isGeneric) {
        if (notLast)
          man += s"final lazy val $attr  : ${ns_1}_OneM[$attr , $tpB${`, A`}, $tpe] = ???"

      } else {
        card match {
          case 1 =>
            one = true
            if (notLast) {
              man += s"final lazy val $attr  : ${ns_1}_OneM[$attr , $tpB${`, A`}, $tpe] = ???"
              opt += s"final lazy val $attrO : ${ns_1}_OneO[$attrO, $tpB${`, A`}, $tpO] = ???"
            }
            tac += s"final lazy val $attr_ : ${ns_0}_OneT[$attr_, $tpB${`, A`}] = ???"
          case 2 =>
            set = true
            if (notLast) {
              man += s"final lazy val $attr  : ${ns_1}_SetM[$attr , $tpB${`, A`}, $tpe] = ???"
              opt += s"final lazy val $attrO : ${ns_1}_SetO[$attrO, $tpB${`, A`}, $tpO] = ???"
            }
            tac += s"final lazy val $attr_ : ${ns_0}_SetT[$attr_, $tpB${`, A`}] = ???"
          case 3 =>
            map = true
            if (notLast) {
              man += s"final lazy val $attr  : ${ns_1}_MapM[$attr , $tpB${`, A`}, $tpe] = ???"
              opt += s"final lazy val $attrO : ${ns_1}_MapO[$attrO, $tpB${`, A`}, $tpO] = ???"
            }
            tac += s"final lazy val $attr_ : ${ns_0}_MapT[$attr_, $tpB${`, A`}] = ???"
        }
      }
  }

  lazy val attrsNext: Seq[String] = if (arity < maxArity) {
    val optResult = opt.result()
    val opts      = if (optResult.isEmpty) Seq("") else Seq("") ++ optResult ++ Seq("")
    man.result() ++ opts
  } else Nil

  lazy val attr_body = (attrsNext ++ tac.result()).mkString("\n  ").trim


  // Refs --------------------------------------

  val defRef     = List.newBuilder[String]
  val defBackRef = List.newBuilder[String]

  attrs0.collect {
    case r: Ref      => defRef += getRef(r)
    case br: BackRef => defBackRef += getBackRef(br)
  }


  // Type traits --------------------------------------

  val typesThis = s"Attr, t${`, A`}"
  val typesBase = s"${`[A..V]`}"
  val typesPass = s"Attr, t${`, A`}"

  val OneM = s"trait ${ns_0}_OneM[$typesThis] extends $ns_0$typesBase with ExprOneM${_0}[$typesPass, ${ns_0}_OneM]"
  val OneO = s"trait ${ns_0}_OneO[$typesThis] extends $ns_0$typesBase with ExprOneO${_0}[$typesPass, ${ns_0}_OneO]"
  val OneT = s"trait ${ns_0}_OneT[$typesThis] extends $ns_0$typesBase with ExprOneT${_0}[$typesPass, ${ns_0}_OneT]"
  val SetM = s"trait ${ns_0}_SetM[$typesThis] extends $ns_0$typesBase with ExprSetM${_0}[$typesPass, ${ns_0}_SetM]"
  val SetO = s"trait ${ns_0}_SetO[$typesThis] extends $ns_0$typesBase with ExprSetO${_0}[$typesPass, ${ns_0}_SetO]"
  val SetT = s"trait ${ns_0}_SetT[$typesThis] extends $ns_0$typesBase with ExprSetT${_0}[$typesPass, ${ns_0}_SetT]"
  val MapM = s"trait ${ns_0}_MapM[$typesThis] extends $ns_0$typesBase with ExprMapM${_0}[$typesPass, ${ns_0}_MapM]"
  val MapO = s"trait ${ns_0}_MapO[$typesThis] extends $ns_0$typesBase with ExprMapO${_0}[$typesPass, ${ns_0}_MapO]"
  val MapT = s"trait ${ns_0}_MapT[$typesThis] extends $ns_0$typesBase with ExprMapT${_0}[$typesPass, ${ns_0}_MapT]"

  val typeTraitsList = {
    if (isDatom) {
      Nil // Ns type for tacit and mandatory supplied as type arg

    } else if (isSchema) {
      if (first) Seq(OneT) else Seq(OneM, OneO, OneT)

    } else if (isGeneric) {
      if (first) Seq() else Seq(OneM)

    } else {
      if (first) {
        (if (one) Seq(OneT) else Nil) ++
          (if (set) Seq(SetT) else Nil) ++
          (if (map) Seq(MapT) else Nil)
      } else {
        (if (one) Seq(OneM, OneO, OneT) else Nil) ++
          (if (set) Seq(SetM, SetO, SetT) else Nil) ++
          (if (map) Seq(MapM, MapO, MapT) else Nil)
      }
    }
  }

  val typeTraits = if (typeTraitsList.isEmpty) "" else typeTraitsList.mkString("\n\n", "\n", "")


  // Refs ------------------------------

  lazy val defRefResult     = defRef.result()
  lazy val defBackRefResult = defBackRef.result()

  lazy val refs        = if (defRefResult.nonEmpty) Seq("") ++ defRefResult else Nil
  lazy val backRefs    = if (defBackRefResult.nonEmpty) Seq("") ++ defBackRefResult else Nil
  lazy val selfJoinDef = s"final def Self: $ns_0[${`A..V`}] with SelfJoin = ???"
  lazy val selfJoin    = if (arity > 0 && !isGeneric) Seq("", selfJoinDef) else Nil

  lazy val refsList = refs ++ backRefs ++ selfJoin
  lazy val allRefs  = if (refsList.isEmpty) "" else refsList.mkString("\n  ", "\n  ", "")


  // Assemble ------------------------------

  //  lazy val baseNs  = s"$ns_0${`[A..V]`}"
  lazy val nsLevel = s"$ns_0${`[A..V]`}"

  def get: String = {
    if (isDatom) {
      val ns_OneT_ = s"Ns${_0}_OneT[_, _${`, _`}]"
      val ns_OneM_ = s"Ns${_1}_OneM[_, _${`, _`}, _]"
      val ns_OneMx = if (arity == maxArity) "Nothing" else ns_OneM_

      s"""trait $ns_0[${`A..V, `}
         |  $ns_OneMx,
         |  $ns_OneT_]
         |  extends $NS {
         |
         |  $attr_body
         |}
         |""".stripMargin

    } else if (isGeneric) {
      s"""trait $nsLevel extends $NS {
         |  $attr_body
         |}$typeTraits
         |""".stripMargin

    } else {
      //      val ns_OneM = if (arity == maxArity) "Nothing" else s"${ns_1}_OneM"
      val ns_OneM = if (arity == 0)
        s"${ns_1}_OneM"
      else if (arity == maxArity)
        ", Nothing"
      else
        s", ${ns_1}_OneM"
      val nsDatom = s"Datom${_0}[${`A..V`}$ns_OneM, ${ns_0}_OneT]"

      s"""$nsDatom {
         |
         |  $attr_body$allRefs
         |}$typeTraits
         |""".stripMargin
    }
  }
}
