package com.example.myapplication

class LocationRepository {



    fun getAllLocations(): ArrayList<StoreLocation> {

        val storeLocationFromDataBases = listOf(
            StoreLocation("Marta's Restaurant",-33.867, 151.206,"Rua juripiranga","Sushi"),
            StoreLocation("Jorge's Restaurant",-33.865, 151.210,"Rua alfredo","Barbecue"),
            StoreLocation("Carla's Restaurant",-33.870, 151.211,"Rua tamarindo","Full food"),
            StoreLocation("Dalva's Restaurant",-33.868, 151.209,"Rua carmelio","Miojo"),

            )

        return ArrayList(storeLocationFromDataBases)
    }
}