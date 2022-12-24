package coreapi

class GetAllRadarsQuery();
data class GetRadarById(
    val radarId:String,
);

class GetOverSpeedsQuery();

class SubscribeToEventsQuery();
class GetAllOverSpeedsQuery();
class GetAllOverSpeedsByRegistrationNumberQuery(
    val registrationNumber : String,
);

//======================================//
//========= IMatriculation Center ===== //
//======================================//

class GetAllVehiclesQuery();
class GetAllOwners();
class GetVehicleByRegistrationNumber(
    val registrationNumber : String,
);

class GetAllInfractions(
    val page : Int,
    val size : Int
);
class GetInfractionsByNationalCardNumber(
    val ownerNationalCardId : String,
    val page : Int,
    val size : Int
);
