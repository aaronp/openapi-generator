//> using scala "3.3.1"
//> using lib "com.lihaoyi::cask:0.8.3"
//> using lib "com.lihaoyi::scalatags:0.12.0"
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


// this is generated from apiRoutes.mustache
package sample.cask.api

import sample.cask.model.*

import upickle.default.{ReadWriter => RW, macroRW}
import upickle.default.*
import scala.util.Try

import sample.cask.model.ApiResponse
import java.io.File
import sample.cask.model.Pet

class PetRoutes(service : PetService[Try]) extends cask.Routes {

    // route group for routeWorkAroundForPOSTPet
    @cask.post("/pet", true)
    def routeWorkAroundForPOSTPet(request: cask.Request) = {
        request.remainingPathSegments match {
            case Seq() => addPet(request)
            case Seq(petId) => updatePetWithForm(petId.toLong,request)
            case Seq(petId,"uploadImage") => uploadFile(petId.toLong,request)
            case _          => cask.Response("Not Found", statusCode = 404)
        }
    }
    // route group for routeWorkAroundForGETPet
    @cask.get("/pet", true)
    def routeWorkAroundForGETPet(request: cask.Request,status : Seq[String] = Nil,tags : Seq[String] = Nil) = {
        request.remainingPathSegments match {
            case Seq("findByStatus") => findPetsByStatus(request,status)
            case Seq("findByTags") => findPetsByTags(request,tags)
            case Seq(petId) => getPetById(petId.toLong,request)
            case _          => cask.Response("Not Found", statusCode = 404)
        }
    }

        /** Add a new pet to the store
         * 
         */
        // conflicts with [/pet/{petId}, /pet/{petId}/uploadImage, /pet] after/pet, ignoring @cask.post("/pet")
        def addPet(request: cask.Request) = {
            // auth method petstore_auth : oauth2, keyParamName: 

        def failFast = request.queryParams.keySet.contains("failFast")

        val result =         for {
              petJson <- Parsed.fromTry(request.bodyAsJson)
              petData <- Parsed.eval(PetData.fromJson(petJson)) /* not array or map */
              pet <- Parsed.fromTry(petData.validated(failFast))
            resultTry <- Parsed.eval(service.addPet(pet))
            result <- Parsed.fromTry(resultTry)
        } yield result

        (result : @unchecked) match {
          case Left(error) => cask.Response(error, 500)
          case Right(value : Pet) => cask.Response(data = write(value), 200, headers = Seq("Content-Type" -> "application/json"))
          case Right(other) => cask.Response(s"$other", 200)
        }
      }
        /** Deletes a pet
         * 
         */
        @cask.delete("/pet/:petId")
        def deletePet(petId : Long, request: cask.Request) = {
            // auth method petstore_auth : oauth2, keyParamName: 

        def failFast = request.queryParams.keySet.contains("failFast")

        val result =         for {
            petId <- Parsed(petId)
                apiKey <- request.headerSingleValueOptional("apiKey")
            resultTry <- Parsed.eval(service.deletePet(petId, apiKey))
            result <- Parsed.fromTry(resultTry)
        } yield result

        (result : @unchecked) match {
          case Left(error) => cask.Response(error, 500)
          case Right(other) => cask.Response(s"$other", 200)
        }
      }
        /** Finds Pets by status
         * 
         */
        // conflicts with [/pet/{petId}, /pet/findByStatus, /pet/findByTags] after/pet, ignoring @cask.get("/pet/findByStatus")
        def findPetsByStatus(request: cask.Request, status : Seq[String]) = {
            // auth method petstore_auth : oauth2, keyParamName: 

        def failFast = request.queryParams.keySet.contains("failFast")

        val result =         for {
            resultTry <- Parsed.eval(service.findPetsByStatus(status))
            result <- Parsed.fromTry(resultTry)
        } yield result

        (result : @unchecked) match {
          case Left(error) => cask.Response(error, 500)
          case Right(value : List[Pet]) => cask.Response(data = write(value), 200, headers = Seq("Content-Type" -> "application/json"))
          case Right(other) => cask.Response(s"$other", 200)
        }
      }
        /** Finds Pets by tags
         * 
         */
        // conflicts with [/pet/{petId}, /pet/findByStatus, /pet/findByTags] after/pet, ignoring @cask.get("/pet/findByTags")
        def findPetsByTags(request: cask.Request, tags : Seq[String]) = {
            // auth method petstore_auth : oauth2, keyParamName: 

        def failFast = request.queryParams.keySet.contains("failFast")

        val result =         for {
            resultTry <- Parsed.eval(service.findPetsByTags(tags))
            result <- Parsed.fromTry(resultTry)
        } yield result

        (result : @unchecked) match {
          case Left(error) => cask.Response(error, 500)
          case Right(value : List[Pet]) => cask.Response(data = write(value), 200, headers = Seq("Content-Type" -> "application/json"))
          case Right(other) => cask.Response(s"$other", 200)
        }
      }
        /** Find pet by ID
         * 
         */
        // conflicts with [/pet/{petId}, /pet/findByStatus, /pet/findByTags] after/pet, ignoring @cask.get("/pet/:petId")
        def getPetById(petId : Long, request: cask.Request) = {
            // auth method api_key : apiKey, keyParamName: api_key

        def failFast = request.queryParams.keySet.contains("failFast")

        val result =         for {
            petId <- Parsed(petId)
            resultTry <- Parsed.eval(service.getPetById(petId))
            result <- Parsed.fromTry(resultTry)
        } yield result

        (result : @unchecked) match {
          case Left(error) => cask.Response(error, 500)
          case Right(value : Pet) => cask.Response(data = write(value), 200, headers = Seq("Content-Type" -> "application/json"))
          case Right(other) => cask.Response(s"$other", 200)
        }
      }
        /** Update an existing pet
         * 
         */
        @cask.put("/pet")
        def updatePet(request: cask.Request) = {
            // auth method petstore_auth : oauth2, keyParamName: 

        def failFast = request.queryParams.keySet.contains("failFast")

        val result =         for {
              petJson <- Parsed.fromTry(request.bodyAsJson)
              petData <- Parsed.eval(PetData.fromJson(petJson)) /* not array or map */
              pet <- Parsed.fromTry(petData.validated(failFast))
            resultTry <- Parsed.eval(service.updatePet(pet))
            result <- Parsed.fromTry(resultTry)
        } yield result

        (result : @unchecked) match {
          case Left(error) => cask.Response(error, 500)
          case Right(value : Pet) => cask.Response(data = write(value), 200, headers = Seq("Content-Type" -> "application/json"))
          case Right(other) => cask.Response(s"$other", 200)
        }
      }
        /** Updates a pet in the store with form data
         * 
         */
        // conflicts with [/pet/{petId}, /pet/{petId}/uploadImage, /pet] after/pet, ignoring @cask.post("/pet/:petId")
        def updatePetWithForm(petId : Long, request: cask.Request) = {
            // auth method petstore_auth : oauth2, keyParamName: 

        def failFast = request.queryParams.keySet.contains("failFast")

        val result =         for {
            petId <- Parsed(petId)
        name <- request.formSingleValueOptional("name")
        status <- request.formSingleValueOptional("status")
            resultTry <- Parsed.eval(service.updatePetWithForm(petId, name, status))
            result <- Parsed.fromTry(resultTry)
        } yield result

        (result : @unchecked) match {
          case Left(error) => cask.Response(error, 500)
          case Right(other) => cask.Response(s"$other", 200)
        }
      }
        /** uploads an image
         * 
         */
        // conflicts with [/pet/{petId}, /pet/{petId}/uploadImage, /pet] after/pet, ignoring @cask.post("/pet/:petId/uploadImage")
        def uploadFile(petId : Long, request: cask.Request) = {
            // auth method petstore_auth : oauth2, keyParamName: 

        def failFast = request.queryParams.keySet.contains("failFast")

        val result =         for {
            petId <- Parsed(petId)
        additionalMetadata <- request.formSingleValueOptional("additionalMetadata")
        file <- request.formValueAsFileOptional("file")
            resultTry <- Parsed.eval(service.uploadFile(petId, additionalMetadata, file))
            result <- Parsed.fromTry(resultTry)
        } yield result

        (result : @unchecked) match {
          case Left(error) => cask.Response(error, 500)
          case Right(value : ApiResponse) => cask.Response(data = write(value), 200, headers = Seq("Content-Type" -> "application/json"))
          case Right(other) => cask.Response(s"$other", 200)
        }
      }

    initialize()
}
