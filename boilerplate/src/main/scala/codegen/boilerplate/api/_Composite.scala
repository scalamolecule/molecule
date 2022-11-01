package codegen.boilerplate.api

import codegen.BoilerplateGenBase

object _Composite extends BoilerplateGenBase("Composite", "/api") {
  val content = {
    val traits = (0 to 21).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api
       |
       |import molecule.boilerplate.api._
       |import scala.language.higherKinds
       |
       |/** Add sub-molecule to composite molecule.
       |  * <br><br>
       |  * Composite molecules model entities with attributes from different namespaces that are
       |  * not necessarily related. Each group of attributes is modelled by a molecule and these
       |  * "sub-molecules" are tied together with `+` methods to form a composite molecule.
       |  * <br><br>
       |  * `+` methods of this interface adds a new sub-molecule to the composite.
       |  * {{{
       |  * //                                          | add sub-molecule
       |  * m(Article.name.author + Tag.category.weight + Publisher.name).get.map(_ ==> List(
       |  *   (("Battle of Waterloo", "Ben Bridge"), ("History", 5), "Bestseller Publications Inc.")
       |  * ))
       |  * }}}
       |  */
       |$traits
       |
       |trait Composite_22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22]
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val Tn     = if (arity == 0) "" else (1 to arity).map(i => s"T$i").mkString(", ")
    val `[Tn]` = if (arity == 0) "" else s"[$Tn]"
    val `[T0]` = if (arity == 0) " " * 68 else s"[$Tn" + (" " * 68) + "]"
    val `T1, ` = if (arity == 0) "" else s"$Tn, "
    val body   =
      s"""
         |trait ${fileName}_$n0${`[Tn]`} {
         |  final def +                                                                  (nextMolecule: Molecule_00                                                                  ): Composite_$n0${`[T0]`} = ???
         |  final def +[a                                                               ](nextMolecule: Molecule_01[a                                                               ]): Composite_$n1[${`T1, `}a                                                                 ] = ???
         |  final def +[a, b                                                            ](nextMolecule: Molecule_02[a, b                                                            ]): Composite_$n1[${`T1, `}(a, b                                                            )] = ???
         |  final def +[a, b, c                                                         ](nextMolecule: Molecule_03[a, b, c                                                         ]): Composite_$n1[${`T1, `}(a, b, c                                                         )] = ???
         |  final def +[a, b, c, d                                                      ](nextMolecule: Molecule_04[a, b, c, d                                                      ]): Composite_$n1[${`T1, `}(a, b, c, d                                                      )] = ???
         |  final def +[a, b, c, d, e                                                   ](nextMolecule: Molecule_05[a, b, c, d, e                                                   ]): Composite_$n1[${`T1, `}(a, b, c, d, e                                                   )] = ???
         |  final def +[a, b, c, d, e, f                                                ](nextMolecule: Molecule_06[a, b, c, d, e, f                                                ]): Composite_$n1[${`T1, `}(a, b, c, d, e, f                                                )] = ???
         |  final def +[a, b, c, d, e, f, g                                             ](nextMolecule: Molecule_07[a, b, c, d, e, f, g                                             ]): Composite_$n1[${`T1, `}(a, b, c, d, e, f, g                                             )] = ???
         |  final def +[a, b, c, d, e, f, g, h                                          ](nextMolecule: Molecule_08[a, b, c, d, e, f, g, h                                          ]): Composite_$n1[${`T1, `}(a, b, c, d, e, f, g, h                                          )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i                                       ](nextMolecule: Molecule_09[a, b, c, d, e, f, g, h, i                                       ]): Composite_$n1[${`T1, `}(a, b, c, d, e, f, g, h, i                                       )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j                                    ](nextMolecule: Molecule_10[a, b, c, d, e, f, g, h, i, j                                    ]): Composite_$n1[${`T1, `}(a, b, c, d, e, f, g, h, i, j                                    )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k                                 ](nextMolecule: Molecule_11[a, b, c, d, e, f, g, h, i, j, k                                 ]): Composite_$n1[${`T1, `}(a, b, c, d, e, f, g, h, i, j, k                                 )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l                              ](nextMolecule: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                              ]): Composite_$n1[${`T1, `}(a, b, c, d, e, f, g, h, i, j, k, l                              )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m                           ](nextMolecule: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m                           ]): Composite_$n1[${`T1, `}(a, b, c, d, e, f, g, h, i, j, k, l, m                           )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ](nextMolecule: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ]): Composite_$n1[${`T1, `}(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ](nextMolecule: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ]): Composite_$n1[${`T1, `}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ](nextMolecule: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ]): Composite_$n1[${`T1, `}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ](nextMolecule: Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ]): Composite_$n1[${`T1, `}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ](nextMolecule: Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ]): Composite_$n1[${`T1, `}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ](nextMolecule: Molecule_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ]): Composite_$n1[${`T1, `}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ](nextMolecule: Molecule_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ]): Composite_$n1[${`T1, `}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ](nextMolecule: Molecule_21[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ]): Composite_$n1[${`T1, `}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v](nextMolecule: Molecule_22[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v]): Composite_$n1[${`T1, `}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)] = ???
         |}""".stripMargin
  }
}
