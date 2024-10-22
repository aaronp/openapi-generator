/**
 * OpenAPI Petstore
 * This is a sample server Petstore server. For this sample, you can use the api key `special-key` to test the authorization filters.
 *
 * OpenAPI spec version: 1.0.0
 *
 * Contact: team@openapitools.org
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 *
 * https://openapi-generator.tech
 */

// this model was generated using model.mustache
package sample.cask.model
import java.time.OffsetDateTime

import scala.util.control.NonFatal

// see https://com-lihaoyi.github.io/upickle/
import upickle.default.{ReadWriter => RW, macroRW}
import upickle.default.*



case class Order(
    id: Option[Long] = None ,
    petId: Option[Long] = None ,
    quantity: Option[Int] = None ,
    shipDate: Option[OffsetDateTime] = None ,
        /* Order Status */
    status: Option[Order.StatusEnum] = None ,
    complete: Option[Boolean] = None 


) {

def asJsonString: String = asData.asJsonString
def asJson: ujson.Value = asData.asJson

def asData : OrderData = {
OrderData(
    id = id.getOrElse(0),
    petId = petId.getOrElse(0),
    quantity = quantity.getOrElse(0),
    shipDate = shipDate.getOrElse(null),
    status = status.getOrElse(null),
    complete = complete.getOrElse(false)

)
}
}

object Order {
given RW[Order] = summon[RW[ujson.Value]].bimap[Order](_.asJson, json => read[OrderData](json).asModel)

enum Fields(val fieldName : String) extends Field(fieldName) {
    case id extends Fields("id")
    case petId extends Fields("petId")
    case quantity extends Fields("quantity")
    case shipDate extends Fields("shipDate")
    case status extends Fields("status")
    case complete extends Fields("complete")
}

        // baseName=status
        // nameInCamelCase = status
        enum StatusEnum derives ReadWriter {
            case placed
            case approved
            case delivered
        }

}

