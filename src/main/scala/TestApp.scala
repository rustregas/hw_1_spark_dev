import io.circe.generic.auto._
import io.circe.parser.decode
import io.circe.syntax._
import io.circe.Error._

import scala.io.Source
import scala.io.StdIn
import java.io.{File, PrintWriter}
import io.circe

import scala.language.implicitConversions

object TestApp extends App{

  case class Country(
                      region: Option[String],
                      area: Double,
                      capital: List[String],
                      name: Name
                    )

  case class Name(official: String)

  case class TargetCountry (
                             name: String,
                             capital: String,
                             area: Double
                           )

//  val countries: Decoder.Result[List[Country]] = cursor.as[List[Country]]
//  val source = Source.fromFile("src/main/resources/countries.json").mkString

  val source = Source.fromURL("https://raw.githubusercontent.com/mledoze/countries/master/countries.json").mkString

  val value: Either[circe.Error, List[Country]] = decode[List[Country]](source)

  def getResult(source: Either[circe.Error, List[Country]]):String = value match {
      case Right(value) =>
        value.filter(x => x.region.getOrElse("null") == "Africa" && x.capital.nonEmpty)
          .map(x =>
            TargetCountry(
              name = x.name.official,
              capital = x.capital.head,
              area = x.area
            )
          )
            .sortBy(_.area)(Ordering[Double].reverse)
            .take(10)
            .asJson
            .noSpaces
      case Left(value) => throw new RuntimeException(s"Parsing error $value")
    }

//  println(getResult(value))

  val fileName = StdIn.readLine("Введите название файла: ")

  val writer = new PrintWriter(new File(s"$fileName.json"))
  writer.write(getResult(value))
  writer.close
}


