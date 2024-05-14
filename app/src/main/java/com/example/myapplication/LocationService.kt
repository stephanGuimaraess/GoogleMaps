package com.example.myapplication

class LocationService{

    fun locationNearbyService(userLocation: UserLocation, radius : Double, locationRepository: LocationRepository) : ArrayList<StoreLocation>{
        val nearbyStoreLocation = ArrayList<StoreLocation>()
        val allLocation = locationRepository.getAllLocations()


        for(location in allLocation){
            val distance = calculeDistance(userLocation, location)
            if(distance <= radius){
                nearbyStoreLocation.add(location)
            }
        }

        return nearbyStoreLocation
    }

    fun preferenceService(user : User , store: LocationRepository) : ArrayList<StoreLocation>{
        val nearbyStorePreferencesLocation = ArrayList<StoreLocation>()
        val storeFood = store.getAllLocations()
        val preferencesUser = user.preferences

        for(preferences in preferencesUser){
            val preferenceNearby = checkPreference(preferences, storeFood)
            nearbyStorePreferencesLocation.addAll(preferenceNearby)
        }

        return nearbyStorePreferencesLocation
    }

    private fun checkPreference(preferences: String, storeFood: ArrayList<StoreLocation>): ArrayList<StoreLocation> {
        val storeWithPreference = ArrayList<StoreLocation>()
        for(storeLocation in storeFood){
            if(storeLocation.category.contains(preferences, true)){
                storeWithPreference.add(storeLocation)
            }
        }

        return storeWithPreference
    }

    private fun calculeDistance(userStoreLocation: UserLocation, localStoreLocation: StoreLocation): Double {
        val earthRadius = 6371 // Raio médio da Terra em quilômetros
        val dLat = Math.toRadians(localStoreLocation.latitude - userStoreLocation.latitude)
        val dLon = Math.toRadians(localStoreLocation.longitude - userStoreLocation.longitude)
        val lat1 = Math.toRadians(userStoreLocation.latitude)
        val lat2 = Math.toRadians(localStoreLocation.latitude)

        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))

        return earthRadius * c
    }
}
