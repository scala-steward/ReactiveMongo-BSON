package reactivemongo.api.bson

import org.typelevel.discipline.specs2.mutable.Discipline
import reactivemongo.BSONValueFixtures
import spire.algebra.{
  Additive,
  AdditiveMonoid,
  AdditiveSemigroup,
  Eq,
  Monoid,
  Semigroup
}
import spire.laws.GroupLaws

final class BSONLawsSpecs
    extends org.specs2.mutable.Specification
    with Discipline {

  "BSON laws".title

  import BSONCheck._
  import LawEvidences._

  { // Addition semigroup
    val semigroup = new Semigroup[BSONValue] {

      def combine(x: BSONValue, y: BSONValue): BSONValue =
        BSONValue.Addition(x, y)
    }
    implicit val additive: AdditiveSemigroup[BSONValue] = Additive(semigroup)

    checkAll("BSONValue", GroupLaws[BSONValue].additiveSemigroup)
  }

  { // Composition monoid
    val monoid = new Monoid[ElementProducer] {
      val empty = ElementProducer.Empty

      def combine(x: ElementProducer, y: ElementProducer): ElementProducer =
        ElementProducer.Composition(x, y)
    }

    implicit val additive: AdditiveMonoid[ElementProducer] =
      Additive(monoid)

    checkAll("ElementProducer", GroupLaws[ElementProducer].additiveMonoid)
  }
}

object LawEvidences {

  implicit def defaultEq[T]: Eq[T] = new Eq[T] {
    def eqv(x: T, y: T): Boolean = x == y
  }
}

object BSONCheck {
  import org.scalacheck._

  val bsonValueGen: Gen[BSONValue] =
    Gen.oneOf[BSONValue](BSONValueFixtures.bsonValueFixtures)

  val elementProducerGen: Gen[ElementProducer] =
    Gen.oneOf[ElementProducer](BSONValueFixtures.elementProducerFixtures)

  implicit val bsonValueArb: Arbitrary[BSONValue] = Arbitrary(bsonValueGen)

  implicit val elementProducerArb: Arbitrary[ElementProducer] =
    Arbitrary(elementProducerGen)

}
