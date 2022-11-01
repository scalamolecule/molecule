package codegen.boilerplate.api

import codegen.BoilerplateGenBase

object _CompositeInit extends BoilerplateGenBase("CompositeInit", "/api") {
  val content = {
    val traits = (0 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api
       |
       |import molecule.boilerplate.api._
       |import scala.language.higherKinds
       |
       |/** Build composite molecule.
       | * <br><br>
       | * Composite molecules model entities with attributes from different namespaces that are
       | * not necessarily related. Each group of attributes is modelled by a molecule and the
       | * "sub-molecules" are tied together with `+` methods to form a composite molecule.
       | * <br><br>
       | * The attributes of the first sub-molecule are tied
       | * together in a tuple of its own before being merged with the tuple of attribute values
       | * of the second sub-molecule. If any of the sub-molecules are of arity-1, then no tuple is created:
       | * {{{
       | * for {
       | *   // Arity 1 + 1
       | *   _ <- m(Article.name + Tag.category).get.map(_ ==> List(
       | *     ("Battle of Waterloo", "History")
       | *   ))
       | *
       | *   // Arity 1 + 2
       | *   _ <- m(Article.name + Tag.category.weight).get.map(_ ==> List(
       | *     ("Battle of Waterloo", ("History", 5))
       | *   ))
       | *
       | *   // Arity 2 + 1
       | *   _ <- m(Article.name.author + Tag.category).get.map(_ ==> List(
       | *     (("Battle of Waterloo", "Ben Bridge"), "History")
       | *   ))
       | *
       | *   // Arity 2 + 2
       | *   _ <- m(Article.name.author + Tag.category.weight).get.map(_ ==> List(
       | *     (("Battle of Waterloo", "Ben Bridge"), ("History", 5))
       | *   ))
       | *
       | *   // Arity 3 + 2 etc...
       | *   _ <- m(Article.name.author.editor + Tag.category.weight).get.map(_ ==> List(
       | *     (("Battle of Waterloo", "Ben Bridge", "Joe Moe"), ("History", 5))
       | *   ))
       | * } yield ()
       | * }}}
       | */
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    override val n0   = if (arity == 0) "00" else "01"
    override val n1   = if (arity == 0) "01" else "02"
    val body =
      s"""
         |trait ${fileName}_$arity${`[A..V]`} {
         |  final def +                                                                  (nextMolecule: Molecule_00                                                                  ): Composite_$n0${`[A0]`} = ???
         |  final def +[a                                                               ](nextMolecule: Molecule_01[a                                                               ]): Composite_$n1[${`A..V,`}a                                                                 ] = ???
         |  final def +[a, b                                                            ](nextMolecule: Molecule_02[a, b                                                            ]): Composite_$n1[${`A..V,`}(a, b                                                            )] = ???
         |  final def +[a, b, c                                                         ](nextMolecule: Molecule_03[a, b, c                                                         ]): Composite_$n1[${`A..V,`}(a, b, c                                                         )] = ???
         |  final def +[a, b, c, d                                                      ](nextMolecule: Molecule_04[a, b, c, d                                                      ]): Composite_$n1[${`A..V,`}(a, b, c, d                                                      )] = ???
         |  final def +[a, b, c, d, e                                                   ](nextMolecule: Molecule_05[a, b, c, d, e                                                   ]): Composite_$n1[${`A..V,`}(a, b, c, d, e                                                   )] = ???
         |  final def +[a, b, c, d, e, f                                                ](nextMolecule: Molecule_06[a, b, c, d, e, f                                                ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f                                                )] = ???
         |  final def +[a, b, c, d, e, f, g                                             ](nextMolecule: Molecule_07[a, b, c, d, e, f, g                                             ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g                                             )] = ???
         |  final def +[a, b, c, d, e, f, g, h                                          ](nextMolecule: Molecule_08[a, b, c, d, e, f, g, h                                          ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h                                          )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i                                       ](nextMolecule: Molecule_09[a, b, c, d, e, f, g, h, i                                       ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i                                       )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j                                    ](nextMolecule: Molecule_10[a, b, c, d, e, f, g, h, i, j                                    ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j                                    )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k                                 ](nextMolecule: Molecule_11[a, b, c, d, e, f, g, h, i, j, k                                 ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k                                 )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l                              ](nextMolecule: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                              ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l                              )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m                           ](nextMolecule: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m                           ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m                           )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ](nextMolecule: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ](nextMolecule: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ](nextMolecule: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ](nextMolecule: Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ](nextMolecule: Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ](nextMolecule: Molecule_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ](nextMolecule: Molecule_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ](nextMolecule: Molecule_21[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   )] = ???
         |  final def +[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v](nextMolecule: Molecule_22[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v]): Composite_$n1[${`A..V,`}(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)] = ???
         |}""".stripMargin
  }
}
