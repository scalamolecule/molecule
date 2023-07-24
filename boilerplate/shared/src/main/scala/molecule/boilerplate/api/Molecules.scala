package molecule.boilerplate.api

import molecule.boilerplate.ast.Model.Element

trait Elements {
  val elements: List[Element]
}

//class Elements_(override val elements: List[Element]) extends Elements

trait Elements_00                                                                   extends Elements
trait Elements_01[A]                                                                extends Elements
trait Elements_02[A, B]                                                             extends Elements
trait Elements_03[A, B, C]                                                          extends Elements
trait Elements_04[A, B, C, D]                                                       extends Elements
trait Elements_05[A, B, C, D, E]                                                    extends Elements
trait Elements_06[A, B, C, D, E, F]                                                 extends Elements
trait Elements_07[A, B, C, D, E, F, G]                                              extends Elements
trait Elements_08[A, B, C, D, E, F, G, H]                                           extends Elements
trait Elements_09[A, B, C, D, E, F, G, H, I]                                        extends Elements
trait Elements_10[A, B, C, D, E, F, G, H, I, J]                                     extends Elements
trait Elements_11[A, B, C, D, E, F, G, H, I, J, K]                                  extends Elements
trait Elements_12[A, B, C, D, E, F, G, H, I, J, K, L]                               extends Elements
trait Elements_13[A, B, C, D, E, F, G, H, I, J, K, L, M]                            extends Elements
trait Elements_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N]                         extends Elements
trait Elements_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]                      extends Elements
trait Elements_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]                   extends Elements
trait Elements_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]                extends Elements
trait Elements_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]             extends Elements
trait Elements_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          extends Elements
trait Elements_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]       extends Elements
trait Elements_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]    extends Elements
trait Elements_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] extends Elements


trait Molecule[Tpl] extends Elements

trait Molecule_00                                                                   extends Molecule[Nothing]                                                            with Elements_00
trait Molecule_01[A]                                                                extends Molecule[A]                                                                  with Elements_01[A]
trait Molecule_02[A, B]                                                             extends Molecule[(A, B)]                                                             with Elements_02[A, B]
trait Molecule_03[A, B, C]                                                          extends Molecule[(A, B, C)]                                                          with Elements_03[A, B, C]
trait Molecule_04[A, B, C, D]                                                       extends Molecule[(A, B, C, D)]                                                       with Elements_04[A, B, C, D]
trait Molecule_05[A, B, C, D, E]                                                    extends Molecule[(A, B, C, D, E)]                                                    with Elements_05[A, B, C, D, E]
trait Molecule_06[A, B, C, D, E, F]                                                 extends Molecule[(A, B, C, D, E, F)]                                                 with Elements_06[A, B, C, D, E, F]
trait Molecule_07[A, B, C, D, E, F, G]                                              extends Molecule[(A, B, C, D, E, F, G)]                                              with Elements_07[A, B, C, D, E, F, G]
trait Molecule_08[A, B, C, D, E, F, G, H]                                           extends Molecule[(A, B, C, D, E, F, G, H)]                                           with Elements_08[A, B, C, D, E, F, G, H]
trait Molecule_09[A, B, C, D, E, F, G, H, I]                                        extends Molecule[(A, B, C, D, E, F, G, H, I)]                                        with Elements_09[A, B, C, D, E, F, G, H, I]
trait Molecule_10[A, B, C, D, E, F, G, H, I, J]                                     extends Molecule[(A, B, C, D, E, F, G, H, I, J)]                                     with Elements_10[A, B, C, D, E, F, G, H, I, J]
trait Molecule_11[A, B, C, D, E, F, G, H, I, J, K]                                  extends Molecule[(A, B, C, D, E, F, G, H, I, J, K)]                                  with Elements_11[A, B, C, D, E, F, G, H, I, J, K]
trait Molecule_12[A, B, C, D, E, F, G, H, I, J, K, L]                               extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L)]                               with Elements_12[A, B, C, D, E, F, G, H, I, J, K, L]
trait Molecule_13[A, B, C, D, E, F, G, H, I, J, K, L, M]                            extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M)]                            with Elements_13[A, B, C, D, E, F, G, H, I, J, K, L, M]
trait Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N]                         extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)]                         with Elements_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N]
trait Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]                      extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)]                      with Elements_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]
trait Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]                   extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)]                   with Elements_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]
trait Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]                extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)]                with Elements_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]
trait Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]             extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)]             with Elements_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]
trait Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)]          with Elements_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]
trait Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]       extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)]       with Elements_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]
trait Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]    extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)]    with Elements_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]
trait Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)] with Elements_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]
