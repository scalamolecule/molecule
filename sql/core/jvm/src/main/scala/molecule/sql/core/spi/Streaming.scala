package molecule.sql.core.spi

import cats.effect.IO
import fs2.Stream
import molecule.base.error.{ExecutionError, MoleculeError}
import molecule.core.action._
import molecule.core.spi._
import molecule.sql.core.javaSql.{ResultSetInterface => Row}
import zio._
import zio.stream._

trait Streaming {

  // Used by both async and io APIs
  def fs2stream[Tpl](
    q: Query[Tpl],
    chunkSize: Int,
    inspect: (Query[Tpl], Conn) => Unit,
    getRS: (Query[Tpl], Conn) => (Row, Row => Any)
  )(implicit conn: Conn): fs2.Stream[IO, Tpl] = {

    // Ensure the inspection runs in IO
    val inspectIO: IO[Unit] =
      if (q.doInspect) IO.blocking(inspect(q, conn))
      else IO.unit

    // Resource handling for Statement & ResultSet
    val acquire: IO[(Row, Row => Any)] = IO.blocking {
      val (row, row2tpl) = getRS(q, conn)
      row.setFetchSize(chunkSize)
      (row, row2tpl)
    }

    val release: ((Row, Row => Any)) => IO[Unit] = {
      case (rs, _) => IO.blocking(rs.close())
    }

    for {
      _ <- Stream.eval(inspectIO)
      (rs, row2tpl) <- Stream.bracket(acquire)(release)
      chunk <- Stream.unfoldEval(()) { _ =>
        IO.blocking {
          val buffer = new collection.mutable.ListBuffer[Tpl]()
          var count  = 0

          // Fetch up to `chunkSize` rows
          while (count < chunkSize && rs.next()) {
            buffer += row2tpl(rs).asInstanceOf[Tpl]
            count += 1
          }

          if (buffer.isEmpty) None // End of stream
          else Some((buffer.toList, ())) // Yield chunk and continue
        }
      }
      element <- Stream.emits(chunk) // Flatten chunks into elements
    } yield element
  }


  def zioStream[Tpl](
    q: Query[Tpl],
    chunkSize: Int,
    inspect: (Query[Tpl], Conn) => Unit,
    getData: (Query[Tpl], Conn) => (Row, Row => Any)
  ): ZStream[Conn, MoleculeError, Tpl] = {

    def attemptBlockingMolecule[A](block: => A): ZIO[Any, MoleculeError, A] =
      ZIO.attemptBlocking(block).mapError {
        case e: MoleculeError => e
        case other            => ExecutionError(other.getMessage)
      }

    // Ensure the inspection runs in ZIO
    def inspectZIO(conn: Conn): ZIO[Any, MoleculeError, Unit] =
      if (q.doInspect)
        attemptBlockingMolecule(inspect(q, conn))
      else
        ZIO.unit

    // Resource handling for Statement & ResultSet
    def acquire(conn: Conn): ZIO[Any, MoleculeError, (Row, Row => Any)] =
      attemptBlockingMolecule {
        val (row, row2tpl) = getData(q, conn)
        row.setFetchSize(chunkSize)
        (row, row2tpl)
      }

    val release: ((Row, Row => Any)) => UIO[Unit] = {
      case (rs, _) => ZIO.attemptBlocking(rs.close()).orDie
    }

    for {
      conn <- ZStream.fromZIO(ZIO.service[Conn])
      _ <- ZStream.fromZIO(inspectZIO(conn))
      (rs, row2tpl) <- ZStream.acquireReleaseWith(acquire(conn))(release)
      element <- ZStream.fromIterableZIO(
        attemptBlockingMolecule {
          val buffer = new collection.mutable.ListBuffer[Tpl]()
          var count  = 0

          // Fetch up to `chunkSize` rows
          while (count < chunkSize && rs.next()) {
            buffer += row2tpl(rs).asInstanceOf[Tpl]
            count += 1
          }

          buffer.toList
        }, chunkSize)
    } yield element
  }
}