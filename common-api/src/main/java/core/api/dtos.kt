package coreapi

import java.time.Instant
import java.util.*

data class RadarDTO(
    var radarId : String ="",
    var longitude : Double=0.0,
    var latitude :Double=0.0,
    var maxSpeed : Int=0,
    );
data class ChangeRadarSpeedLimitRequestDTO(
    val radarId : String,
    val speed : Int
);
open class OverSpeedRequestDTO(
    var radarId : String="",
    var timeStamp : Instant= Instant.now(),
    var overSpeedId : String="",
    var vehicleRegistrationNumber : String="",
    var vehicleSpeed : Int=0,
    var radarMaxSpeed: Int=0,
    var radarLongitude : Double=0.0,
    var radarLatitude : Double=0.0,

);

data class  InfractionData(
    var infractionId : String="",
    var amount : Double=0.0,
    var vehicleOwner : String ="",
    var ownerEmail : String ="",
    var ownerPhoneNumber : String ="",
    var ownerAddress : String ="",
    var ownerNationalCardId: String="",
    var status : InfractionStatus=InfractionStatus.PENDING,
) : OverSpeedRequestDTO();

data class OverSpeedResponseDTO(
    var overSpeedId : String="",
    var timeStamp : Instant= Instant.now(),
    var radarId : String="",
    var vehicleRegistrationNumber : String="",
    var vehicleSpeed : Int=0
);

data class RadarResponseDTO(
    var radarId : String ="",
    var longitude : Double=0.0,
    var latitude :Double=0.0,
    var maxSpeed : Int=0,
);

data class RadarOverSpeedsDTO(
    var radarId : String ="",
    var longitude : Double=0.0,
    var latitude :Double=0.0,
    var maxSpeed : Int=0,
    var overSpeedDetections : List<OverSpeedResponseDTO> = listOf()
);
data class EventDataResponseDTO<T>(
    var type : String="",
    var eventData : T ,
);

data class VehicleRequestDTO(
    val registrationNumber : String,
    val brand : String,
    val model : String,
    val fiscalPower : Int,
    val ownerName : String,
    val ownerNationalIdCard : String,
    val ownerEmail : String,
    val ownerBirthDay : Date,
    val ownerPhoneNumber : String,
    val ownerAddress : String
);

data class UpdateVehicleOwnerRequestDTO(
    val registrationNumber : String,
    val ownerName : String,
    val ownerNationalIdCard : String,
    val ownerEmail : String,
    val ownerBirthDay : Date,
    val ownerPhoneNumber : String,
    val ownerAddress : String
);

